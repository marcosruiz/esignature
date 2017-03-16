package main;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.PrivateKeySignature;
import exception.MarginNotFoundException;
import exception.NoEmptySignaturesException;
import exception.WrittingOutOfDinA4Exception;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

// E.g: java -jar esignature-cl.java -addemptysigns -src src/main/resources/hello.pdf -dest src/main/resources/hello_test.pdf -qos 3 -margin right -img src/main/resources/icon.png
// E.g: java -jar esignature-cl.java -sign -src src/main/resources/hello_test.pdf -dest src/main/resources/hello_test_2.pdf -pass pass -ks src/main/resources/abc.p12
/**
 *
 * @author Marcos Ruiz Garcia [sobrenombre@gmail.com]
 */
public class Application {
    /**
     * Read the arguments of the program and addemptysigns empty signatures or signEmptyField empty signatures
     *
     * @param args
     * @throws java.io.IOException
     * @throws com.itextpdf.text.DocumentException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.cert.CertificateException
     * @throws java.security.KeyStoreException
     * @throws java.security.UnrecoverableKeyException
     * @throws exception.MarginNotFoundException
     * @throws exception.WrittingOutOfDinA4Exception
     * @throws exception.NoEmptySignaturesException
     */
    public static void main(String[] args) throws IOException, DocumentException, NoSuchAlgorithmException, CertificateException, KeyStoreException, UnrecoverableKeyException, GeneralSecurityException, MarginNotFoundException, WrittingOutOfDinA4Exception, NoEmptySignaturesException {
        //Print command line instrucction
        /*System.out.print("java -jar esignature-cl.jar ");
        for (String s : args) {
            System.out.printf("%s ", s);
        }
        System.out.print("\n");*/
        Scanner s;
        boolean isSecondPair = false; // true if is a pair of arguments like "-qos 3"
        //Arguments
        boolean addemptysigns = false; // addemptysigns gaps to our pdf
        boolean sign = false; // signEmptyField a gap of our pdf
        boolean addimage = false;
        boolean addbarcode = false;
        String margin = "top"; // top, bot, left, right
        int qos = 1; //quantity of signatures: 1, 2, 3 or 4
        String img = null;
        String ks = null;
        String src = null;
        String dest = null;
        String pass = null;
        String code = null;
        String text = null;


        for (int i = 0; i < args.length; i++) {
            if (isSecondPair) {
                switch (args[i - 1]) {
                    case "-qos":
                        qos = Integer.parseInt(args[i]);
                        break;
                    case "-margin":
                        margin = args[i];
                        break;
                    case "-img":
                        img = args[i];
                        break;
                    case "-ks":
                        ks = args[i];
                        break;
                    case "-src":
                        src = args[i];
                        break;
                    case "-dest":
                        dest = args[i];
                        break;
                    case "-pass":
                        pass = args[i];
                        break;
                    case "-code":
                        code = args[i];
                        break;
                    case "-text":
                        text = args[i];
                        break;
                    default:
                        break;
                }
                isSecondPair = false;
            } else {
                switch (args[i]) {
                    case "-addemptysigns":
                        addemptysigns = true;
                        break;
                    case "-sign":
                        sign = true;
                        break;
                    case "-addimage":
                        addimage = true;
                        break;
                    case "-addbarcode":
                        addbarcode = true;
                        break;
                    case "-qos":
                        isSecondPair = true;
                        break;
                    case "-margin":
                        isSecondPair = true;
                        break;
                    case "-img":
                        isSecondPair = true;
                        break;
                    case "-ks":
                        isSecondPair = true;
                        break;
                    case "-src":
                        isSecondPair = true;
                        break;
                    case "-dest":
                        isSecondPair = true;
                        break;
                    case "-pass":
                        isSecondPair = true;
                        break;
                    case "-code":
                        isSecondPair = true;
                        break;
                    case "-text":
                        isSecondPair = true;
                        break;
                    case "-h":
                        s = new Scanner(new File("resources/help/help.txt"));
                        while (s.hasNext()) {
                            System.out.println(s.nextLine());
                        }
                        s.close();
                        break;
                    case "-help":
                        s = new Scanner(new File("resources/help/help.txt"));
                        while (s.hasNext()) {
                            System.out.println(s.nextLine());
                        }
                        s.close();
                        break;
                    case "help":
                        s = new Scanner(new File("resources/help/help.txt"));
                        while (s.hasNext()) {
                            System.out.println(s.nextLine());
                        }
                        s.close();
                        break;
                    default:
                        break;
                }
            }
        }
        if (addemptysigns) {
            createEmptyFields(src, dest, qos, margin, img);
            System.out.println("Empty fields created");
        } else if (sign) {
            BouncyCastleProvider provider = new BouncyCastleProvider();
            Security.addProvider(provider);
            signEmptyField(ks, PdfSignatureAppearance.NOT_CERTIFIED, src, dest, pass);
            System.out.println("Signature successful");
        } else if (addbarcode) {
            addTextAndBarcode(src, dest, code, text);

        } else if (addimage) {
            addImage(src, dest, img);
        }
    }

    /**
     * Create a new pdf file with a new esignature added
     *
     * @param keystore
     * @param level
     * @param src
     * @param name
     * @param dest
     * @param pass
     * @throws GeneralSecurityException
     * @throws IOException
     * @throws DocumentException
     */
    public static void signEmptyField(String keystore, int level,
            String src, String dest, String pass)
            throws GeneralSecurityException, IOException, DocumentException, NoEmptySignaturesException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keystore), pass.toCharArray());
        String alias = (String) ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, pass.toCharArray());
        Certificate[] chain = ks.getCertificateChain(alias);
        // Creating the reader and the stamper
        PdfReader reader = new PdfReader(src);
        FileOutputStream os = new FileOutputStream(dest);
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0', null, true);
        //Searching blank signature names
        AcroFields fields = reader.getAcroFields();
        ArrayList<String> sigNames = fields.getSignatureNames();
        ArrayList<String> blankSigNames = fields.getBlankSignatureNames();
        if (blankSigNames.isEmpty()) {
            throw new NoEmptySignaturesException();
        }
        // Creating the appearance
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setVisibleSignature(blankSigNames.get(0));
        appearance.setCertificationLevel(level);
        // Creating the signature
        ExternalSignature pks = new PrivateKeySignature(pk, "SHA-256", "BC");
        ExternalDigest digest = new BouncyCastleDigest();
        MakeSignature.signDetached(appearance, digest, pks, chain, null, null, null, 0, MakeSignature.CryptoStandard.CMS);

        stamper.close();
        os.close();
        reader.close();
    }


    /**
     *
     * @param src
     * @param dest
     * @param qos
     * @param margin
     * @param img
     * @throws IOException
     * @throws DocumentException
     * @throws WrittingOutOfDinA4Exception
     */
    public static void createEmptyFields(String src, String dest, int qos, String margin, String img) throws WrittingOutOfDinA4Exception, IOException, DocumentException {
        if (src == null || dest == null) {
            throw new NullPointerException();
        }
        if (!(qos >= 1)) {
            throw new WrittingOutOfDinA4Exception();
        }
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));

        for (int i = 1; i <= qos; i++) {
            try {
                // get position of signature
                float[] coords = getCoordinates(reader, margin, i);
                // get name of field
                String name = "sig" + i;
                if (img == null) {
                    createEmptyField(stamper, name, coords[0], coords[1], coords[2], coords[3]);
                } else {
                    try {
                        createEmptyFieldWithImage(stamper, name, coords[0], coords[1], coords[2], coords[3], margin, img, 50);
                    } catch (IOException | DocumentException ex) {
                        Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (MarginNotFoundException ex) {
                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        // close the stamper
        stamper.close();
        reader.close();
    }

    /**
     *
     * @param stamper
     * @param name
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param margin
     * @param img
     * @param shift
     * @throws BadPdfFormatException
     * @throws IOException
     * @throws DocumentException
     * @throws MarginNotFoundException
     */
    public static void createEmptyFieldWithImage(PdfStamper stamper, String name, float x1, float y1, float x2, float y2, String margin, String img, int shift) throws BadPdfFormatException, IOException, DocumentException, MarginNotFoundException {

        if (margin.equals("top") || margin.equals("bot")) {
            putImageSquare(stamper, x1 - shift, y1, img, 0, 20, 10);
        } else if (margin.equals("left") || margin.equals("right")) {
            putImageSquare(stamper, x1, y1 - shift, img, 0, 20, 10);
        } else {
            throw new MarginNotFoundException();
        }

        createEmptyField(stamper, name, x1, y1, x2, y2);
    }

    /**
     * Calculate the bottom-left and top-right corners of <pos>th signature letting a gap for the image
     *
     * @param margin
     * @param pos
     * @return table of 4 ints wich correspond to x,y bottom left corner and x,y top right corners of a rectangle
     * @throws exception.MarginNotFoundException
     * @throws exception.WrittingOutOfDinA4Exception
     */
    public static float[] getCoordinates(PdfReader reader, String margin, int pos) throws MarginNotFoundException, WrittingOutOfDinA4Exception {
        if (!(pos >= 1 && pos <= 4)) {
            throw new exception.WrittingOutOfDinA4Exception();
        }

        Rectangle pagesize = reader.getPageSize(1);
        float rightLimit = pagesize.getRight();
        float topLimit = pagesize.getTop();
        float spaceForImage = 50;
        float spaceFromLimit1st = 10;
        float spaceFromLimit2nd = 50;
        float width = 100;
        float spaceBeetweenSigs = 10;

        float[] coords = new float[4];
        if (margin.equals("top")) {
            coords[0] = (spaceForImage + ((pos - 1) * width)) + (spaceForImage + spaceBeetweenSigs) * pos; //x bottom-left
            coords[1] = topLimit - spaceForImage; //y bottom-letft
            coords[2] = (spaceForImage + (pos * width)) + (spaceForImage + spaceBeetweenSigs) * pos; //x top-right
            coords[3] = topLimit - spaceFromLimit1st; //y top-right
        } else if (margin.equals("bot")) {
            coords[0] = (spaceForImage + ((pos - 1) * width)) + (spaceForImage + spaceBeetweenSigs) * pos;
            coords[1] = spaceFromLimit1st;
            coords[2] = (spaceForImage + (pos * width)) + (spaceForImage + spaceBeetweenSigs) * pos;
            coords[3] = spaceFromLimit2nd;
        } else if (margin.equals("left")) {
            coords[0] = spaceFromLimit1st;
            coords[1] = (spaceForImage + ((pos - 1) * width)) + (spaceForImage + spaceBeetweenSigs) * pos;
            coords[2] = spaceFromLimit2nd;
            coords[3] = (spaceForImage + (pos * width)) + (spaceForImage + spaceBeetweenSigs) * pos;
        } else if (margin.equals("right")) {
            coords[0] = rightLimit - spaceFromLimit2nd;
            coords[1] = (spaceForImage + ((pos - 1) * width)) + (spaceForImage + spaceBeetweenSigs) * pos;
            coords[2] = rightLimit - spaceFromLimit1st;
            coords[3] = (spaceForImage + (pos * width)) + (spaceForImage + spaceBeetweenSigs) * pos;
        } else if (margin.equals("end")) {
            //TODO
        } else {
            throw new MarginNotFoundException();
        }
        return coords;
    }

    /**
     * Create an interactive empty rectangle to sign digitally
     *
     * @param stamper
     * @param name
     * @param xblc : x bottom-left corner
     * @param yblc : y bottom-left corner
     * @param xrtc : x right-top corner
     * @param yrtc : y right-top corner
     */
    public static void createEmptyField(PdfStamper stamper, String name, float xblc, float yblc, float xrtc, float yrtc) {
        if (stamper == null || name == null) {
            throw new java.lang.NullPointerException();
        }
        // addemptysigns a signature form field
        PdfFormField field = PdfFormField.createSignature(stamper.getWriter());
        field.setFieldName(name);
        // set the widget properties
        field.setWidget(new Rectangle(xblc, yblc, xrtc, yrtc), PdfAnnotation.HIGHLIGHT_OUTLINE);
        field.setFlags(PdfAnnotation.FLAGS_PRINT);
        // add the annotation
        stamper.addAnnotation(field, 1);
    }

    /**
     *
     * @param stamper
     * @param x coordinate x of upper-left corner of our image
     * @param y coordinate x of upper-left corner of our image
     * @param img
     * @param rotation
     * @param sizeSquare
     * @param sizeMargin
     * @throws BadPdfFormatException
     * @throws IOException
     * @throws DocumentException
     */
    public static void putImageSquare(PdfStamper stamper, float x, float y, String img, int rotation, int sizeSquare, int sizeMargin) throws BadPdfFormatException, IOException, DocumentException {
        if (stamper == null || img == null) {
            throw new java.lang.NullPointerException();
        }
        Image image = Image.getInstance(img);
        image.setAbsolutePosition(x + sizeMargin, y + sizeMargin);
        image.scaleAbsolute(sizeSquare, sizeSquare);
        image.setRotationDegrees(rotation);
        PdfContentByte over = stamper.getOverContent(1);
        over.addImage(image);
    }

    public static void addBarcode(PdfReader reader, PdfStamper stamper, String code) throws DocumentException {
        PdfContentByte over = stamper.getOverContent(1);
        String altText = "Cód. Validación: " + code + " | Página 1 de " + reader.getNumberOfPages();//TODO

        Barcode128 barcode = new Barcode128();
        barcode.setCodeType(Barcode.CODE128);
        barcode.setCode(code);
        barcode.setAltText(altText);
        barcode.setTextAlignment(Element.ALIGN_LEFT);

        Image image = barcode.createImageWithBarcode(over, BaseColor.BLACK, BaseColor.BLACK);

        Rectangle pagesize = reader.getPageSize(1);
        float x = pagesize.getRight() - 50;
        float y = pagesize.getBottom() + 20;
        PdfTemplate template
                = barcode.createTemplateWithBarcode(over, BaseColor.BLACK, BaseColor.BLACK);
        putImage(stamper, image, x, y, 500, 40, 90);
    }

    public static void addTextAndBarcode(String src, String dest, String code, String text) throws IOException, DocumentException {
        //Open
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        //Put text
        if (text != null) {
            Rectangle pagesize = reader.getPageSize(1);
            float x = pagesize.getRight() - 3;
            float y = pagesize.getBottom() + 20;
            PdfContentByte over = stamper.getOverContent(1);
            over.setFontAndSize(BaseFont.createFont(), 8);
            over.beginText();
            over.showTextAligned(Element.ALIGN_LEFT, text, x, y, 90);
            over.endText();
        }
        //Put barcode
        addBarcode(reader, stamper, code);
        //Close
        stamper.close();
        reader.close();
    }

    public static void addText(PdfStamper stamper, String text, int rotation, float x, float y) {

    }

    public static void addImage(String src, String dest, String img) throws IOException, FileNotFoundException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        Rectangle pagesize = reader.getPageSize(1);
        float x = pagesize.getRight() - 50;
        float y = pagesize.getBottom() + 20;
        putImage(stamper, img, x, y, 500, 40, 90);
        stamper.close();
        reader.close();

    }

    public static void putImage(PdfStamper stamper, String img, float posX, float posY, int lenX, int lenY, int rotation) throws BadElementException, IOException, DocumentException {
        if (stamper == null || img == null) {
            throw new java.lang.NullPointerException();
        }
        Image image = Image.getInstance(img);
        putImage(stamper, image, posX, posY, lenX, lenY, rotation);
        //image.scaleAbsolute(lenX, lenY);

    }

    public static void putImage(PdfStamper stamper, Image image, float posX, float posY, int lenX, int lenY, int rotation) throws DocumentException {
        image.scaleToFit(lenX, lenY);
        image.setAbsolutePosition(posX, posY);
        image.setRotationDegrees(rotation);
        PdfContentByte over = stamper.getOverContent(1);
        over.addImage(image);
    }
}

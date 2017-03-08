package main;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.PrivateKeySignature;
import java.io.FileInputStream;
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
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author Marcos Ruiz Garcia <sobrenombre@gmail.com>
 */
public class Main {
    /**
     * Read the arguments of the program and execute
     *
     * @param args
     */
    // E.g: java -jar esignature-cl.java -create -src src/main/resources/hello.pdf -dest src/main/resources/hello_test.pdf -qos 3 -margin right -img src/main/resources/icon.png
    // E.g: java -jar esignature-cl.java -sign -src src/main/resources/hello_test.pdf -dest src/main/resources/hello_test_2.pdf -pos 1 -pass pass -ks src/main/resources/abc.p12
    // E.g: java -jar esignature-cl.java -sign -src src/main/resources/hello_test_2.pdf -dest src/main/resources/hello_test_3.pdf -pos 2 -pass pass -ks src/main/resources/abc.p12

    public static void main(String[] args) throws IOException, DocumentException, NoSuchAlgorithmException, CertificateException, KeyStoreException, UnrecoverableKeyException, GeneralSecurityException {
        //Print command line instrucction
        System.out.print("java -jar esignature-cl.jar ");
        for (String s : args) {
            System.out.printf("%s ", s);
        }
        System.out.print("\n");

        boolean isSecondPair = false; // true if is a pair of arguments like "-qos 3"
        //Arguments
        boolean create = false; // create gaps to our pdf
        boolean sign = false; // sign a gap of our pdf
        String margin = null; // top, bottom, left, right
        int qos = 0; //quantity of signatures: 1, 2, 3 or 4
        String img = null;
        int pos = 0; //1 -> first pos, 2-> second pos
        String ks = null;
        String src = null;
        String dest = null;
        String pass = null;

        for (int i = 0; i < args.length; i++) {
            if (isSecondPair) {
                if (args[i - 1].equals("-qos")) {
                    qos = Integer.parseInt(args[i]);
                } else if (args[i - 1].equals("-margin")) {
                    margin = args[i];
                } else if (args[i - 1].equals("-pos")) {
                    pos = Integer.parseInt(args[i]);
                } else if (args[i - 1].equals("-img")) {
                    img = args[i];
                } else if (args[i - 1].equals("-ks")) {
                    ks = args[i];
                } else if (args[i - 1].equals("-src")) {
                    src = args[i];
                } else if (args[i - 1].equals("-dest")) {
                    dest = args[i];
                } else if (args[i - 1].equals("-pass")) {
                    pass = args[i];
                }
                isSecondPair = false;
            } else {
                if (args[i].equals("-create")) {
                    create = true;
                } else if (args[i].equals("-sign")) {
                    sign = true;
                } else if (args[i].equals("-qos")) {
                    isSecondPair = true;
                } else if (args[i].equals("-pos")) {
                    isSecondPair = true;
                } else if (args[i].equals("-margin")) {
                    isSecondPair = true;
                } else if (args[i].equals("-img")) {
                    isSecondPair = true;
                } else if (args[i].equals("-ks")) {
                    isSecondPair = true;
                } else if (args[i].equals("-src")) {
                    isSecondPair = true;
                } else if (args[i].equals("-dest")) {
                    isSecondPair = true;
                } else if (args[i].equals("-pass")) {
                    isSecondPair = true;
                }
            }
        }
        if (create) {
            addFieldsWithImage(src, dest, qos, margin, img);
        } else if (sign) {
            BouncyCastleProvider provider = new BouncyCastleProvider();
            Security.addProvider(provider);
            sign(ks, PdfSignatureAppearance.NOT_CERTIFIED, src, "sig" + pos, dest, pass);

        }
    }

    public static void sign(String keystore, int level,
            String src, String name, String dest, String password)
            throws GeneralSecurityException, IOException, DocumentException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keystore), password.toCharArray());
        String alias = (String) ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, password.toCharArray());
        Certificate[] chain = ks.getCertificateChain(alias);
        // Creating the reader and the stamper
        PdfReader reader = new PdfReader(src);
        FileOutputStream os = new FileOutputStream(dest);
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0', null, true);
        // Creating the appearance
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setVisibleSignature(name);
        appearance.setCertificationLevel(level);
        // Creating the signature
        ExternalSignature pks = new PrivateKeySignature(pk, "SHA-256", "BC");
        //System.out.println(pks.getEncryptionAlgorithm());
        ExternalDigest digest = new BouncyCastleDigest();
        MakeSignature.signDetached(appearance, digest, pks, chain, null, null, null, 0, MakeSignature.CryptoStandard.CMS);
    }

    public static void addFieldsWithImage(String src, String dest, int numberOfSignatures, String positionOfSignatures, String routeOfMyImage) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));

        for (int i = 1; i <= numberOfSignatures; i++) {
            // get position of signature
            int[] coords = getCoordinates(positionOfSignatures, i);
            // get name of field
            String name = "sig" + i;
            if (routeOfMyImage == null) {
                addField(stamper, name, coords[0], coords[1], coords[2], coords[3]);
            } else {
                addFieldWithImage(stamper, name, coords[0], coords[1], coords[2], coords[3], positionOfSignatures, routeOfMyImage);
            }

        }
        // close the stamper
        stamper.close();
    }

    public static void addFieldWithImage(PdfStamper stamper, String name, int x1, int y1, int x2, int y2, String pos, String routeOfMyImage) throws BadPdfFormatException, IOException, DocumentException {

        //Imagen suponiendo que esta en top
        if (pos.equals("top") || pos.equals("bottom")) {
            putImage(stamper, x1 - 50, y1, routeOfMyImage, 0);
        } else if (pos.equals("left") || pos.equals("right")) {
            putImage(stamper, x1, y1 - 50, routeOfMyImage, 0);
        }

        addField(stamper, name, x1, y1, x2, y2);
    }

    /**
     *
     * @param position
     * @param i
     * @return table of 4 ints wich correspond to x,y bottom left corner and x,y top right corners of a rectangle
     */
    public static int[] getCoordinates(String position, int i) {
        int[] coords = new int[4];
        if (position.equals("top")) {
            coords[0] = (50 + ((i - 1) * 100)) + 60 * i;
            coords[1] = 800;
            coords[2] = (50 + (i * 100)) + 60 * i;
            coords[3] = 840;
        } else if (position.equals("bottom")) {
            coords[0] = (50 + ((i - 1) * 100)) + 60 * i;
            coords[1] = 10;
            coords[2] = (50 + (i * 100)) + 60 * i;
            coords[3] = 50;
        } else if (position.equals("left")) {
            coords[0] = 10;
            coords[1] = (50 + ((i - 1) * 100)) + 60 * i;
            coords[2] = 50;
            coords[3] = (50 + (i * 100)) + 50 * i;
        } else if (position.equals("right")) {
            coords[0] = 545;
            coords[1] = (50 + ((i - 1) * 100)) + 60 * i;
            coords[2] = 585;
            coords[3] = (50 + (i * 100)) + 60 * i;
        } else if (position.equals("end")) {
            //TODO
        }
        return coords;
    }
    public static void addField(PdfStamper stamper, String name, int x1, int y1, int x2, int y2) {
        // create a signature form field
        PdfFormField field = PdfFormField.createSignature(stamper.getWriter());
        field.setFieldName(name);
        // set the widget properties
        field.setWidget(new Rectangle(x1, y1, x2, y2), PdfAnnotation.HIGHLIGHT_OUTLINE);
        field.setFlags(PdfAnnotation.FLAGS_PRINT);
        // add the annotation
        stamper.addAnnotation(field, 1);
    }


    public static void putImage(PdfStamper stamper, int x, int y, String route, int rotation) throws BadPdfFormatException, IOException, DocumentException {
        Image image = Image.getInstance(route);
        image.setAbsolutePosition(x + 10, y + 10);
        image.scaleAbsolute(20, 20);
        image.setRotationDegrees(rotation);
        PdfContentByte over = stamper.getOverContent(1);
        over.addImage(image);
    }

}

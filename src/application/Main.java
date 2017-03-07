package javaapplication1;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author ITERNOVA [http://www.iternova.net]
 */
public class Application {
    /**
     * Read the arguments of the program and execute
     *
     * @param args
     */
    // E.g: java -jar Application.java -create -num 3 -pos [top|left|righ|bottom|end]
    // E.g: java -jar Application.java -sign -place 3 -image /etc/image.png
    public static void main(String[] args) throws IOException, DocumentException {
        String argument = null;
        boolean isSecondPair = false;
        //Arguments
        boolean createEmptyFields = false;
        boolean signEmptyField = false;
        String positionOfSignatures = null;
        int numberOfEmptySignatures = 1;
        String routeOfMyImage = null;
        int placeOfMySignature = 0;
        String keystore = null;
        String source = null;
        String dest = null;

        for (int i = 0; i < args.length; i++) {
            argument = args[i];
            if (isSecondPair) {
                if (args[i - 1].equals("-num")) {
                    numberOfEmptySignatures = Integer.parseInt(args[i]);
                } else if (args[i - 1].equals("-pos")) {
                    positionOfSignatures = args[i];
                } else if (args[i - 1].equals("-place")) {
                    placeOfMySignature = Integer.parseInt(args[i]);
                } else if (args[i - 1].equals("-image")) {
                    routeOfMyImage = args[i];
                } else if (args[i - 1].equals("-keystore")) {
                    keystore = args[i];
                } else if (args[i - 1].equals("-source")) {
                    source = args[i];
                } else if (args[i - 1].equals("-dest")) {
                    dest = args[i];
                }
                isSecondPair = false;
            } else {
                if (argument.equals("-create")) {
                    createEmptyFields = true;
                } else if (argument.equals("-sign")) {
                    signEmptyField = true;
                } else if (argument.equals("-num")) {
                    isSecondPair = true;
                } else if (argument.equals("-pos")) {
                    isSecondPair = true;
                } else if (argument.equals("-place")) {
                    isSecondPair = true;
                } else if (argument.equals("-image")) {
                    isSecondPair = true;
                } else if (argument.equals("-keystore")) {
                    isSecondPair = true;
                } else if (argument.equals("-source")) {
                    isSecondPair = true;
                } else if (argument.equals("-dest")) {
                    isSecondPair = true;
                }
            }
        }
        if (createEmptyFields) {
            createFields(numberOfEmptySignatures, source, dest, positionOfSignatures);
        } else if (signEmptyField) {

        }
    }

    public static void createFields(int numberOfSignatures, String source, String dest, String positionOfSignatures) throws IOException, DocumentException {
        addFields(source, dest, numberOfSignatures, positionOfSignatures);
    }

    public static void signFields() {

    }

    public static void addFields(String src, String dest, int numberOfSignatures, String positionOfSignatures) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));

        for (int i = 1; i <= numberOfSignatures; i++) {
            // get position of signature
            int[] coords = getCoordinates(positionOfSignatures, i);
            // get name of field
            String name = "sig" + i;
            addFieldWithImage(stamper, name, coords[0], coords[1], coords[2], coords[3], ""); //TODO hacer bien
        }
        // close the stamper
        stamper.close();
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
            coords[0] = (50 + ((i - 1) * 100)) + 50 * i;
            coords[1] = 800;
            coords[2] = (50 + (i * 100)) + 50 * i;
            coords[3] = 840;
        } else if (position.equals("bottom")) {
            coords[0] = (50 + ((i - 1) * 100)) + 50 * i;
            coords[1] = 10;
            coords[2] = (50 + (i * 100)) + 50 * i;
            coords[3] = 50;
        } else if (position.equals("left")) {
            coords[0] = 10;
            coords[1] = (50 + ((i - 1) * 100)) + 50 * i;
            coords[2] = 50;
            coords[3] = (50 + (i * 100)) + 50 * i;
        } else if (position.equals("right")) {
            coords[0] = 545;
            coords[1] = (50 + ((i - 1) * 100)) + 50 * i;
            coords[2] = 585;
            coords[3] = (50 + (i * 100)) + 50 * i;
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
        field.setWidget(new Rectangle(x1, y1, x2, y2, 90), PdfAnnotation.HIGHLIGHT_OUTLINE);
        field.setFlags(PdfAnnotation.FLAGS_PRINT);
        // add the annotation
        stamper.addAnnotation(field, 1);
    }

    public static void addFieldWithImage(PdfStamper stamper, String name, int x1, int y1, int x2, int y2, String routeOfMyImage) throws BadPdfFormatException, IOException, DocumentException {

        //Imagen suponiendo que
        putImage(stamper, x1 - 50, y1, x1, y1 + 50, routeOfMyImage);
        addField(stamper, name, x1, y1, x2, y2);
    }

    public static void putImage(PdfStamper stamper, int x1, int y1, int x2, int y2, String route) throws BadPdfFormatException, IOException, DocumentException {
        String img = "src/main/resources/icon3.png";
        Image image = Image.getInstance(img);
        PdfImage stream = new PdfImage(image, "", null);
        stream.put(new PdfName("ITXT_SpecialId"), new PdfName("123456789"));
        PdfIndirectObject ref = stamper.getWriter().addToBody(stream);
        image.setDirectReference(ref.getIndirectReference());
        image.setAbsolutePosition(36, 400);
        PdfContentByte over = stamper.getOverContent(1);
        over.addImage(image);
    }
}

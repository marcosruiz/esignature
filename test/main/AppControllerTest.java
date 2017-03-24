package main;

import com.itextpdf.text.Image;
import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import exception.MarginNotFoundException;
import exception.WrittingOutOfDinA4Exception;
import main.AppController.Margin;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Marcos Ruiz Garcia [sobrenombre@gmail.com]
 */
public class AppControllerTest {

    public AppControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of signEmptyField method, of class AppController.
     */
    @Test(expected = InvalidPdfException.class)
    public void testSignEmptyField() throws Exception {
        System.out.printf(".");
        String keystore = "";
        int level = 0;
        String src = "";
        String dest = "";
        char[] pass = null;
        AppController.signEmptyField(keystore, src, dest, pass);
    }


    /**
     * Test of createEmptyFields method, of class AppController.
     */
    @Test(expected = InvalidPdfException.class)
    public void testCreateEmptyFields() throws Exception {
        System.out.printf(".");
        String src = "";
        String dest = "";
        int qos = 0;
        Margin margin = null;;
        String img = "";
        AppController.createEmptyFields(src, dest, qos, margin, img);
    }

    /**
     * Test of createEmptyFieldWithImage method, of class AppController.
     */
    @Test(expected = MarginNotFoundException.class)
    public void testAddFieldWithImage() throws Exception {
        System.out.printf(".");
        PdfStamper stamper = null;
        String name = "";
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        Margin margin = null;;
        String img = "";
        int shift = 0;
        AppController.createEmptyFieldWithImage(stamper, name, x1, y1, x2, y2, margin, img, shift);
    }

    /**
     * Test of getCoordinates method, of class AppController.
     */
    @Test(expected = WrittingOutOfDinA4Exception.class)
    public void testGetCoordinates() throws Exception {
        Margin margin = null;;
        int i = 0;
        float[] result = AppController.getCoordinates(null, margin, i);
        assertEquals(null, result);
    }

    /**
     * Test of createEmptyField method, of class AppController.
     */
    @Test(expected = NullPointerException.class)
    public void testCreateEmptyField() {
        System.out.printf(".");
        PdfStamper stamper = null;
        String name = "";
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        AppController.createEmptyField(stamper, name, x1, y1, x2, y2);

    }

    /**
     * Test of putImageSquare method, of class AppController.
     */
    @Test(expected = NullPointerException.class)
    public void testPutImage() throws Exception {
        System.out.printf(".");
        PdfStamper stamper = null;
        int x1 = 0;
        int y1 = 0;
        String route = "";
        int rotation = 0;
        int sideSquare = 0;
        int sizeMargin = 0;
        AppController.putImageSquare(stamper, x1, y1, route, rotation, sideSquare, sizeMargin);
    }

    /**
     * Test of signEmptyFieldFromUri method, of class AppController.
     */
    @Test
    public void testSignEmptyFieldFromUri_4args() throws Exception {
        System.out.println("signEmptyFieldFromUri");
        String keystore = "";
        String src = "";
        String dest = "";
        char[] pass = null;
        AppController.signEmptyFieldFromUri(keystore, src, dest, pass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of signEmptyFieldFromUri method, of class AppController.
     */
    @Test
    public void testSignEmptyFieldFromUri_5args() throws Exception {
        System.out.println("signEmptyFieldFromUri");
        String keystore = "";
        int level = 0;
        String src = "";
        String dest = "";
        char[] pass = null;
        AppController.signEmptyFieldFromUri(keystore, src, dest, pass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of signEmptyField method, of class AppController.
     */
    @Test
    public void testSignEmptyField_4args() throws Exception {
        System.out.println("signEmptyField");
        String keystore = "";
        String src = "";
        String dest = "";
        char[] pass = null;
        AppController.signEmptyField(keystore, src, dest, pass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of signEmptyField method, of class AppController.
     */
    @Test
    public void testSignEmptyField_5args_1() throws Exception {
        System.out.println("signEmptyField");
        String keystore = "";
        int level = 0;
        String src = "";
        String dest = "";
        char[] pass = null;
        AppController.signEmptyField(keystore, src, dest, pass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of signEmptyField method, of class AppController.
     */
    @Test
    public void testSignEmptyField_5args_2() throws Exception {
        System.out.println("signEmptyField");
        String keystore = "";
        int level = 0;
        PdfReader reader = null;
        PdfStamper stamper = null;
        char[] pass = null;
        AppController.signEmptyField(keystore, level, reader, stamper, pass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEmptyFieldsFromUri method, of class AppController.
     */
    @Test
    public void testCreateEmptyFieldsFromUri() throws Exception {
        System.out.println("createEmptyFieldsFromUri");
        String src = "";
        String dest = "";
        int qos = 0;
        AppController.Margin margin = null;
        String img = "";
        AppController.createEmptyFieldsFromUri(src, dest, qos, margin, img);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEmptyFieldWithImage method, of class AppController.
     */
    @Test
    public void testCreateEmptyFieldWithImage() throws Exception {
        System.out.println("createEmptyFieldWithImage");
        PdfStamper stamper = null;
        String name = "";
        float x1 = 0.0F;
        float y1 = 0.0F;
        float x2 = 0.0F;
        float y2 = 0.0F;
        Margin margin = null;;
        String img = "";
        int shift = 0;
        AppController.createEmptyFieldWithImage(stamper, name, x1, y1, x2, y2, margin, img, shift);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putImageSquare method, of class AppController.
     */
    @Test
    public void testPutImageSquare() throws Exception {
        System.out.println("putImageSquare");
        PdfStamper stamper = null;
        float x = 0.0F;
        float y = 0.0F;
        String img = "";
        int rotation = 0;
        int sizeSquare = 0;
        int sizeMargin = 0;
        AppController.putImageSquare(stamper, x, y, img, rotation, sizeSquare, sizeMargin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBarcode method, of class AppController.
     */
    @Test
    public void testAddBarcode() throws Exception {
        System.out.println("addBarcode");
        PdfReader reader = null;
        PdfStamper stamper = null;
        String code = "";
        AppController.addBarcode(reader, stamper, code);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTextAndBarcode method, of class AppController.
     */
    @Test
    public void testAddTextAndBarcode() throws Exception {
        System.out.println("addTextAndBarcode");
        String src = "";
        String dest = "";
        String code = "";
        String text = "";
        AppController.addTextAndBarcode(src, dest, code, text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addText method, of class AppController.
     */
    @Test
    public void testAddText() {
        System.out.println("addText");
        PdfStamper stamper = null;
        String text = "";
        int rotation = 0;
        float x = 0.0F;
        float y = 0.0F;
        AppController.addText(stamper, text, rotation, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addImage method, of class AppController.
     */
    @Test
    public void testAddImage() throws Exception {
        System.out.println("addImage");
        String src = "";
        String dest = "";
        String img = "";
        AppController.addImage(src, dest, img);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putImage method, of class AppController.
     */
    @Test
    public void testPutImage_7args_1() throws Exception {
        System.out.println("putImage");
        PdfStamper stamper = null;
        String img = "";
        float posX = 0.0F;
        float posY = 0.0F;
        int lenX = 0;
        int lenY = 0;
        int rotation = 0;
        AppController.putImage(stamper, img, posX, posY, lenX, lenY, rotation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putImage method, of class AppController.
     */
    @Test
    public void testPutImage_7args_2() throws Exception {
        System.out.println("putImage");
        PdfStamper stamper = null;
        Image image = null;
        float posX = 0.0F;
        float posY = 0.0F;
        int lenX = 0;
        int lenY = 0;
        int rotation = 0;
        AppController.putImage(stamper, image, posX, posY, lenX, lenY, rotation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


}

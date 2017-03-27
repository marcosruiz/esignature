package main;

import com.itextpdf.text.Image;
import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import main.AppModel.Margin;
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
public class AppModelTest {

    public AppModelTest() {
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
     * Test of signEmptyField method, of class AppModel.
     */
    @Test(expected = InvalidPdfException.class)
    public void testSignEmptyField() throws Exception {
        System.out.printf(".");
        String keystore = "";
        int level = 0;
        String src = "";
        String dest = "";
        char[] pass = null;
        AppModel.signEmptyField(keystore, src, dest, pass);
    }


    /**
     * Test of addEmptyFields method, of class AppModel.
     */
    @Test(expected = InvalidPdfException.class)
    public void testCreateEmptyFields() throws Exception {
        System.out.printf(".");
        String src = "";
        String dest = "";
        int qos = 0;
        Margin margin = null;;
        String img = "";
        AppModel.addEmptyFields(src, dest, qos, margin, img);
    }

    /**
     * Test of addEmptyFieldWithImage method, of class AppModel.
     */
    @Test(expected = Exception.class)
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
        AppModel.addEmptyFieldWithImage(stamper, name, x1, y1, x2, y2, margin, img, shift);
    }

    /**
     * Test of calcCoords method, of class AppModel.
     */
    @Test(expected = Exception.class)
    public void testGetCoordinates() throws Exception {
        Margin margin = null;;
        int i = 0;
        float[] result = AppModel.calcCoords(null, margin, i);
        assertEquals(null, result);
    }

    /**
     * Test of addEmptyField method, of class AppModel.
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
        AppModel.addEmptyField(stamper, name, x1, y1, x2, y2);

    }

    /**
     * Test of putImageSquare method, of class AppModel.
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
        AppModel.putImageSquare(stamper, x1, y1, route, rotation, sideSquare, sizeMargin);
    }

    /**
     * Test of signEmptyFieldFromUri method, of class AppModel.
     */
    @Test(expected = MalformedURLException.class)
    public void testSignEmptyFieldFromUri_4args() throws Exception {
        System.out.println("signEmptyFieldFromUri");
        String keystore = "";
        String src = "";
        String dest = "";
        char[] pass = null;
        AppModel.signEmptyFieldFromUri(keystore, src, dest, pass);

    }

    /**
     * Test of signEmptyFieldFromUri method, of class AppModel.
     */
    @Test(expected = MalformedURLException.class)
    public void testSignEmptyFieldFromUri_5args() throws Exception {
        System.out.println("signEmptyFieldFromUri");
        String keystore = "";
        int level = 0;
        String src = "";
        String dest = "";
        char[] pass = null;
        AppModel.signEmptyFieldFromUri(keystore, src, dest, pass);
    }

    /**
     * Test of signEmptyField method, of class AppModel.
     */
    @Test(expected = InvalidPdfException.class)
    public void testSignEmptyField_4args() throws Exception {
        System.out.println("signEmptyField");
        String keystore = "";
        String src = "";
        String dest = "";
        char[] pass = null;
        AppModel.signEmptyField(keystore, src, dest, pass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of signEmptyField method, of class AppModel.
     */
    @Test(expected = InvalidPdfException.class)
    public void testSignEmptyField_5args_1() throws Exception {
        System.out.println("signEmptyField");
        String keystore = "";
        int level = 0;
        String src = "";
        String dest = "";
        char[] pass = null;
        AppModel.signEmptyField(keystore, src, dest, pass);
    }

    /**
     * Test of signEmptyField method, of class AppModel.
     */
    @Test(expected = FileNotFoundException.class)
    public void testSignEmptyField_5args_2() throws Exception {
        System.out.println("signEmptyField");
        String keystore = "";
        int level = 0;
        PdfReader reader = null;
        PdfStamper stamper = null;
        char[] pass = null;
        AppModel.signEmptyField(keystore, level, reader, stamper, pass);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEmptyFieldsFromUri method, of class AppModel.
     */
    @Test(expected = MalformedURLException.class)
    public void testCreateEmptyFieldsFromUri() throws Exception {
        System.out.println("createEmptyFieldsFromUri");
        String src = "";
        String dest = "";
        int qos = 0;
        AppModel.Margin margin = null;
        String img = "";
        AppModel.addEmptyFieldsFromUri(src, dest, qos, margin, img);
    }

    /**
     * Test of addEmptyFieldWithImage method, of class AppModel.
     */
    @Test(expected = NullPointerException.class)
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
        AppModel.addEmptyFieldWithImage(stamper, name, x1, y1, x2, y2, margin, img, shift);

    }

    /**
     * Test of putImageSquare method, of class AppModel.
     */
    @Test(expected = NullPointerException.class)
    public void testPutImageSquare() throws Exception {
        System.out.println("putImageSquare");
        PdfStamper stamper = null;
        float x = 0.0F;
        float y = 0.0F;
        String img = "";
        int rotation = 0;
        int sizeSquare = 0;
        int sizeMargin = 0;
        AppModel.putImageSquare(stamper, x, y, img, rotation, sizeSquare, sizeMargin);
    }

    /**
     * Test of addBarcode method, of class AppModel.
     */
    @Test(expected = NullPointerException.class)
    public void testAddBarcode() throws Exception {
        System.out.println("addBarcode");
        PdfReader reader = null;
        PdfStamper stamper = null;
        String code = "";
        AppModel.addBarcode(reader, stamper, code);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTextAndBarcode method, of class AppModel.
     */
    @Test(expected = InvalidPdfException.class)
    public void testAddTextAndBarcode() throws Exception {
        System.out.println("addTextAndBarcode");
        String src = "";
        String dest = "";
        String code = "";
        String text = "";
        AppModel.addTextAndBarcode(src, dest, code, text);
    }

    /**
     * Test of addText method, of class AppModel.
     */
    @Test
    public void testAddText() {
        System.out.println("addText");
        PdfStamper stamper = null;
        String text = "";
        int rotation = 0;
        float x = 0.0F;
        float y = 0.0F;
        AppModel.addText(stamper, text, rotation, x, y);
    }

    /**
     * Test of addImage method, of class AppModel.
     */
    @Test(expected = InvalidPdfException.class)
    public void testAddImage() throws Exception {
        System.out.println("addImage");
        String src = "";
        String dest = "";
        String img = "";
        AppModel.addImage(src, dest, img);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putImage method, of class AppModel.
     */
    @Test(expected = NullPointerException.class)
    public void testPutImage_7args_1() throws Exception {
        System.out.println("putImage");
        PdfStamper stamper = null;
        String img = "";
        float posX = 0.0F;
        float posY = 0.0F;
        int lenX = 0;
        int lenY = 0;
        int rotation = 0;
        AppModel.putImage(stamper, img, posX, posY, lenX, lenY, rotation);

    }

    /**
     * Test of putImage method, of class AppModel.
     */
    @Test(expected = NullPointerException.class)
    public void testPutImage_7args_2() throws Exception {
        System.out.println("putImage");
        PdfStamper stamper = null;
        Image image = null;
        float posX = 0.0F;
        float posY = 0.0F;
        int lenX = 0;
        int lenY = 0;
        int rotation = 0;
        AppModel.putImage(stamper, image, posX, posY, lenX, lenY, rotation);
    }

    /**
     * Test of signEmptyFieldFromUri method, of class AppModel.
     */
    @Test(expected = MalformedURLException.class)
    public void testSignEmptyFieldFromUri() throws Exception {
        System.out.println("signEmptyFieldFromUri");
        String keystore = "";
        String src = "";
        String dest = "";
        char[] pass = null;
        AppModel.signEmptyFieldFromUri(keystore, src, dest, pass);
    }

    /**
     * Test of signEmptyField method, of class AppModel.
     */
    @Test(expected = FileNotFoundException.class)
    public void testSignEmptyField_5args() throws Exception {
        System.out.println("signEmptyField");
        String keystore = "";
        int level = 0;
        PdfReader reader = null;
        PdfStamper stamper = null;
        char[] pass = null;
        AppModel.signEmptyField(keystore, level, reader, stamper, pass);

    }

    /**
     * Test of addEmptyFieldsFromUri method, of class AppModel.
     */
    @Test(expected = MalformedURLException.class)
    public void testAddEmptyFieldsFromUri() throws Exception {
        System.out.println("addEmptyFieldsFromUri");
        String src = "";
        String dest = "";
        int qos = 0;
        Margin margin = null;
        String img = "";
        AppModel.addEmptyFieldsFromUri(src, dest, qos, margin, img);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEmptyFields method, of class AppModel.
     */
    @Test(expected = InvalidPdfException.class)
    public void testAddEmptyFields() throws Exception {
        System.out.println("addEmptyFields");
        String src = "";
        String dest = "";
        int qos = 0;
        Margin margin = null;
        String img = "";
        AppModel.addEmptyFields(src, dest, qos, margin, img);

    }

    /**
     * Test of addEmptyFieldWithImage method, of class AppModel.
     */
    @Test(expected = NullPointerException.class)
    public void testAddEmptyFieldWithImage() throws Exception {
        System.out.println("addEmptyFieldWithImage");
        PdfStamper stamper = null;
        String name = "";
        float x1 = 0.0F;
        float y1 = 0.0F;
        float x2 = 0.0F;
        float y2 = 0.0F;
        Margin margin = null;
        String img = "";
        int shift = 0;
        AppModel.addEmptyFieldWithImage(stamper, name, x1, y1, x2, y2, margin, img, shift);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcCoords method, of class AppModel.
     */
    @Test(expected = NullPointerException.class)
    public void testCalcCoords() {
        System.out.println("calcCoords");
        PdfReader reader = null;
        Margin margin = null;
        int pos = 0;
        float[] expResult = null;
        float[] result = AppModel.calcCoords(reader, margin, pos);
        //assertArrayEquals(expResult, result);
    }

    /**
     * Test of addEmptyField method, of class AppModel.
     */
    @Test(expected = NullPointerException.class)
    public void testAddEmptyField() {
        System.out.println("addEmptyField");
        PdfStamper stamper = null;
        String name = "";
        float xblc = 0.0F;
        float yblc = 0.0F;
        float xrtc = 0.0F;
        float yrtc = 0.0F;
        AppModel.addEmptyField(stamper, name, xblc, yblc, xrtc, yrtc);
    }

}

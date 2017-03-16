package main;

import com.itextpdf.text.pdf.PdfStamper;
import exception.MarginNotFoundException;
import exception.WrittingOutOfDinA4Exception;
import java.io.FileNotFoundException;
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
public class ApplicationTest {

    public ApplicationTest() {
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
     * Test of signEmptyField method, of class Application.
     */
    @Test(expected = FileNotFoundException.class)
    public void testSignEmptyField() throws Exception {
        System.out.printf(".");
        String keystore = "";
        int level = 0;
        String src = "";
        String dest = "";
        String pass = "";
        Application.signEmptyField(keystore, level, src, dest, pass);
    }


    /**
     * Test of createEmptyFields method, of class Application.
     */
    @Test(expected = WrittingOutOfDinA4Exception.class)
    public void testCreateEmptyFields() throws Exception {
        System.out.printf(".");
        String src = "";
        String dest = "";
        int qos = 0;
        String margin = "";
        String img = "";
        Application.createEmptyFields(src, dest, qos, margin, img);
    }

    /**
     * Test of createEmptyFieldWithImage method, of class Application.
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
        String margin = "";
        String img = "";
        int shift = 0;
        Application.createEmptyFieldWithImage(stamper, name, x1, y1, x2, y2, margin, img, shift);
    }

    /**
     * Test of getCoordinates method, of class Application.
     */
    @Test(expected = WrittingOutOfDinA4Exception.class)
    public void testGetCoordinates() throws Exception {
        String margin = "";
        int i = 0;
        float[] expResult = null;
        float[] result = Application.getCoordinates(null, margin, i);
        assertEquals(null, result);
    }

    /**
     * Test of createEmptyField method, of class Application.
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
        Application.createEmptyField(stamper, name, x1, y1, x2, y2);

    }

    /**
     * Test of putImageSquare method, of class Application.
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
        Application.putImageSquare(stamper, x1, y1, route, rotation, sideSquare, sizeMargin);
    }


}

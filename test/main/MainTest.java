package main;

import main.Main;
import com.itextpdf.text.pdf.PdfStamper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ITERNOVA [http://www.iternova.net]
 */
public class MainTest {

    public MainTest() {
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
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Main.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFields method, of class Main.
     */
    @Test
    public void testCreateFields() throws Exception {
        System.out.println("createFields");
        int numberOfSignatures = 0;
        String source = "";
        String dest = "";
        String positionOfSignatures = "";
        Main.createFields(numberOfSignatures, source, dest, positionOfSignatures);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of signFields method, of class Main.
     */
    @Test
    public void testSignFields() {
        System.out.println("signFields");
        Main.signFields();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFields method, of class Main.
     */
    @Test
    public void testAddFields() throws Exception {
        System.out.println("addFields");
        String src = "";
        String dest = "";
        int numberOfSignatures = 0;
        String positionOfSignatures = "";
        Main.addFields(src, dest, numberOfSignatures, positionOfSignatures);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoordinates method, of class Main.
     */
    @Test
    public void testGetCoordinates() {
        System.out.println("getCoordinates");
        String position = "";
        int i = 0;
        int[] expResult = null;
        int[] result = Main.getCoordinates(position, i);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addField method, of class Main.
     */
    @Test
    public void testAddField() {
        System.out.println("addField");
        PdfStamper stamper = null;
        String name = "";
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        Main.addField(stamper, name, x1, y1, x2, y2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFieldWithImage method, of class Main.
     */
    @Test
    public void testAddFieldWithImage() throws Exception {
        System.out.println("addFieldWithImage");
        PdfStamper stamper = null;
        String name = "";
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        String routeOfMyImage = "";
        Main.addFieldWithImage(stamper, name, x1, y1, x2, y2, routeOfMyImage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putImage method, of class Main.
     */
    @Test
    public void testPutImage() throws Exception {
        System.out.println("putImage");
        PdfStamper stamper = null;
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        String route = "";
        Main.putImage(stamper, x1, y1, x2, y2, route);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

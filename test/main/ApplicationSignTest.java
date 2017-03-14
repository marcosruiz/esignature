package main;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Marcos Ruiz Garcia [sobrenombre@gmail.com]
 */
public class ApplicationSignTest {

    public ApplicationSignTest() {
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
     * Test of main method, of class Application.
     */
    @Test
    public void testMainSignSeveralEmptyFields() throws Exception {
        signSeveralEmptyFields("top");
        signSeveralEmptyFields("bot");
        signSeveralEmptyFields("right");
        signSeveralEmptyFields("left");
    }

    public void signSeveralEmptyFields(String margin) throws Exception {
        String[] args = new String[9];
        args[0] = "-sign";
        args[1] = "-src";
        args[2] = "results/test/create/hello_empty_fields_" + margin + "_4.pdf";
        args[3] = "-dest";
        args[5] = "-pass";
        args[6] = "password";
        args[7] = "-ks";
        args[8] = "resources/abc.p12";

        for (int i = 1; i <= 4; i++) {
            args[4] = "results/test/sign/hello_" + margin + "_" + i + "_signed_of_4.pdf";
            Application.main(args);
            args[2] = args[4];
        }
    }

    @Test
    public void testMainSignSeveralEmptyFieldsWithImg() throws Exception {
        signSeveralEmptyFieldsWithImg("top");
        signSeveralEmptyFieldsWithImg("bot");
        signSeveralEmptyFieldsWithImg("right");
        signSeveralEmptyFieldsWithImg("left");
    }

    public void signSeveralEmptyFieldsWithImg(String margin) throws Exception {
        String[] args = new String[9];
        args[0] = "-sign";
        args[1] = "-src";
        args[2] = "results/test/create/hello_empty_fields_with_img_" + margin + "_4.pdf";
        args[3] = "-dest";
        args[5] = "-pass";
        args[6] = "password";
        args[7] = "-ks";
        args[8] = "resources/abc.p12";

        for (int i = 1; i <= 4; i++) {
            args[4] = "results/test/sign/hello_" + margin + "_" + i + "_signed_of_4_with_img.pdf";
            Application.main(args);
            args[2] = args[4];
        }
    }

}

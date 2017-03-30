package main;

import org.junit.Test;

/**
 *
 * @author Marcos Ruiz Garcia [sobrenombre@gmail.com]
 */
public class CLMainTest {
    //Resources and results
    public static String PATH_APP = "C:\\Users\\Marcos\\Documents\\NetBeansProjects\\JavaApplication1\\";
    public static String PATH_RESOURCES = "resources/";
    public static String PATH_CERT = PATH_RESOURCES + "certificates/";
    public static String PATH_ICONS = PATH_RESOURCES + "icons/";
    public static String PATH_PDF = PATH_RESOURCES + "pdf_templates/";
    public static String PATH_RESULTS = "results/";
    public static String PATH_ADD_EMPTY = PATH_RESULTS + "addempty/";
    public static String PATH_ADD_SIGNATURE = PATH_RESULTS + "addsignature/";
    public static String PATH_ADD_BC = PATH_RESULTS + "addbarcode/";
    public static String PATH_HTTP = PATH_RESULTS + "http/";
    public static String PATH_ADD_IMG = PATH_RESULTS + "addimage/";
    public static String PATH_SRC_HTTP = "file:///C:/Users/Marcos/Documents/NetBeansProjects/JavaApplication1/" + PATH_HTTP;

    @Test
    public void testApp() throws Exception {
        CLAddEmptyTest claet = new CLAddEmptyTest();
        claet.testMainCreateSeveralEmptyFields();
        claet.testMainCreateSeveralEmptyFieldsWithImage();
        claet.testAddBarcode();
        claet.testAddImage();
        claet.testHelp();
        claet.testMainCreateEmptyField();
        claet.testCreateFromHttp();
        claet.testCreateAbsolutePath();

        CLSignEmptyTest clset = new CLSignEmptyTest();
        clset.testMainSignSeveralEmptyFields();
        clset.testMainSignSeveralEmptyFieldsWithImg();
        clset.testSignHttp();

        CLCheckSignTest clcst = new CLCheckSignTest();
        clcst.testSiganaturesInfo();
        clcst.testSiganaturesInfoWithImg();
        clcst.testSignaturesIntegrity();
    }
}

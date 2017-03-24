package main;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.CertificateInfo;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import com.itextpdf.text.pdf.security.SignaturePermissions;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
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
public class AppCheckTest {

    public AppCheckTest() {
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

    @Test
    public void testSiganaturesInfo() throws IOException, GeneralSecurityException {
        testSignaturesInfo("top");
        testSignaturesInfo("bot");
        testSignaturesInfo("right");
        testSignaturesInfo("left");

    }

    @Test
    public void testSiganaturesInfoWithImg() throws IOException, GeneralSecurityException {
        testSignaturesInfoWithImg("top");
        testSignaturesInfoWithImg("bot");
        testSignaturesInfoWithImg("right");
        testSignaturesInfoWithImg("left");

    }

    public void testSignaturesInfoWithImg(String margin) throws IOException, GeneralSecurityException {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        String path_pre = "results/test/sign/hello_" + margin + "_";
        String path_post = "_signed_of_4_with_img.pdf";
        String path;
        for (int i = 1; i <= 4; i++) {
            path = path_pre + i + path_post;
            inspectSignatures(path);
        }
    }


    public void testSignaturesInfo(String margin) throws IOException, GeneralSecurityException {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        String path_pre = "results/test/sign/hello_" + margin + "_";
        String path_post = "_signed_of_4.pdf";
        String path;
        for (int i = 1; i <= 4; i++) {
            path = path_pre + i + path_post;
            inspectSignatures(path);
        }
    }

    public void inspectSignatures(String path) throws IOException, GeneralSecurityException {
        PdfReader reader = new PdfReader(path);
        AcroFields fields = reader.getAcroFields();
        ArrayList<String> names = fields.getSignatureNames();
        SignaturePermissions perms = null;
        for (String name : names) {
            //System.out.println("===== " + name + " =====");
            perms = inspectSignature(fields, name, perms);
        }
    }

    public SignaturePermissions inspectSignature(AcroFields fields, String name, SignaturePermissions perms) throws GeneralSecurityException, IOException {
        java.util.List<FieldPosition> fps = fields.getFieldPositions(name);
        if (fps != null && fps.size() > 0) {
            FieldPosition fp = fps.get(0);
            Rectangle pos = fp.position;
            if (pos.getWidth() == 0 || pos.getHeight() == 0) {
                // Invisible signature
                assertTrue(false);
            } else {
                assertEquals(1, fp.page);
            }
        }

        PdfPKCS7 pkcs7 = verifySignature(fields, name);
        assertEquals("SHA256", pkcs7.getHashAlgorithm());
        assertEquals("RSA", pkcs7.getEncryptionAlgorithm());
        X509Certificate cert = (X509Certificate) pkcs7.getSigningCertificate();
        assertEquals("Marcos Ruiz", CertificateInfo.getSubjectFields(cert).getField("CN"));
        if (pkcs7.getTimeStampDate() != null) {
            assertTrue(pkcs7.verifyTimestampImprint());
        }
        PdfDictionary sigDict = fields.getSignatureDictionary(name);
        perms = new SignaturePermissions(sigDict, perms);
        assertFalse(perms.isCertification());
        assertTrue(perms.isFillInAllowed());
        assertTrue(perms.isAnnotationsAllowed());
        return perms;
    }

    @Test
    public void testSignaturesIntegrity() throws IOException, GeneralSecurityException {
        String path = "results/test/sign/hello_right_4_signed_of_4.pdf";

        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);

        PdfReader reader = new PdfReader(path);
        AcroFields fields = reader.getAcroFields();
        ArrayList<String> names = fields.getSignatureNames();
        for (String name : names) {
            verifySignature(fields, name);
        }
    }

    public PdfPKCS7 verifySignature(AcroFields fields, String name) throws GeneralSecurityException {
        PdfPKCS7 pkcs7 = fields.verifySignature(name);
        assertTrue(pkcs7.verify());
        return pkcs7;
    }
}

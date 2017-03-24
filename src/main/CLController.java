package main;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import exception.MarginNotFoundException;
import exception.NoEmptySignaturesException;
import exception.WrittingOutOfDinA4Exception;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Scanner;
import main.AppController.Margin;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * This class is in charge of all interaction with the user by command line
 *
 * @author Marcos Ruiz Garcia [sobrenombre@gmail.com]
 */
public class CLController {

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
     * @throws java.net.URISyntaxException
     */
    public static void main(String[] args) throws IOException, DocumentException, NoSuchAlgorithmException, CertificateException, KeyStoreException, UnrecoverableKeyException, GeneralSecurityException, MarginNotFoundException, WrittingOutOfDinA4Exception, NoEmptySignaturesException, URISyntaxException {
        Scanner s;
        boolean isSecondPair = false; // true if is a pair of arguments like "-qos 3"
        //Arguments
        boolean addemptysigns = false; // addemptysigns gaps to our pdf
        boolean sign = false; // signEmptyField a gap of our pdf
        boolean addimage = false;
        boolean addbarcode = false;
        boolean isSrcURL = false;
        Margin margin = Margin.TOP; // top, bot, left, right
        int qos = 1; //quantity of signatures: 1, 2, 3 or 4
        String img = null;
        String ks = null;
        String src = null;
        String dest = null;
        char[] pass = null;
        String code = null;
        String text = null;


        for (int i = 0; i < args.length; i++) {
            if (isSecondPair) {
                switch (args[i - 1]) {
                    case "-qos":
                        qos = Integer.parseInt(args[i]);
                        break;
                    case "-margin":
                        switch (args[i].toLowerCase()) {
                            case "top":
                                margin = Margin.TOP;
                                break;
                            case "bot":
                                margin = Margin.BOT;
                                break;
                            case "right":
                                margin = Margin.RIGHT;
                                break;
                            case "left":
                                margin = Margin.LEFT;
                                break;
                        }
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
                        pass = args[i].toCharArray();
                        break;
                    case "-code":
                        code = args[i];
                        break;
                    case "-text":
                        text = args[i];
                        break;
                    case "-srcurl":
                        src = args[i];
                        isSrcURL = true;
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
                    case "-srcurl":
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
                    default:
                        break;
                }
            }
        }
        if (addemptysigns) {
            if (isSrcURL) {
                AppController.createEmptyFieldsFromUri(src, dest, qos, margin, img);
            } else {
                AppController.createEmptyFields(src, dest, qos, margin, img);
            }

            System.out.println("Empty fields created");
        } else if (sign) {
            if (isSrcURL) {
                AppController.signEmptyFieldFromUri(ks, src, dest, pass);
            } else {
                AppController.signEmptyField(ks, src, dest, pass);
            }
            System.out.println("Signature stamped successful");
        } else if (addbarcode) {
            AppController.addTextAndBarcode(src, dest, code, text);

        } else if (addimage) {
            AppController.addImage(src, dest, img);
        }
    }
}

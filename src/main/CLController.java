package main;

import com.itextpdf.text.DocumentException;
import exception.NoEmptySignaturesException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.AppModel.Margin;

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
     */
    public static void main(String[] args) throws Exception {
        Scanner s;
        boolean isSecondPair = false; // true if is a pair of arguments like "-qos 3"
        //Arguments
        boolean addemptysigns = false; // addemptysigns gaps to our pdf
        boolean sign = false; // signEmptyField a gap of our pdf
        boolean addimage = false;
        boolean addbarcode = false;
        boolean isSrcURL = false;
        Margin margin = Margin.TOP;
        int qos = 1; //quantity of signatures: 1, 2, 3 or 4
        String img = null;
        String ks = null;
        String src = null;
        String dest = null;
        char[] pass = null;
        String code = null;
        String text = null;

        // Read user input
        for (int i = 0; i < args.length; i++) {
            if (isSecondPair) {
                switch (args[i - 1]) {
                    case "-qos":
                        qos = Integer.parseInt(args[i]);
                        break;
                    case "-margin":
                        margin = Margin.parseMargin(args[i]);
                        break;
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
                    case "-url":
                        isSrcURL = true;
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
        //Execute the corret method of AppModel
        if (addemptysigns) {
            if (isSrcURL) {
                AppModel.addEmptyFieldsFromUri(src, dest, qos, margin, img);
            } else {
                AppModel.addEmptyFields(src, dest, qos, margin, img);
            }
            System.out.println("Empty fields created");
        } else if (sign) {
            if (isSrcURL) {
                AppModel.signEmptyFieldFromUri(ks, src, dest, pass);
            } else {
                AppModel.signEmptyField(ks, src, dest, pass);
            }
            System.out.println("Signature stamped successful");
        } else if (addbarcode) {
            AppModel.addTextAndBarcode(src, dest, code, text);

        } else if (addimage) {
            AppModel.addImage(src, dest, img);
        }

    }
}

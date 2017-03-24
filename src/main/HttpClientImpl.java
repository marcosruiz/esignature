package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author ITERNOVA [http://www.iternova.net]
 */
public class HttpClientImpl {

    public static void prueba0() throws MalformedURLException, IOException {
        String httpsURL = "https://192.168.2.111/index.php?type=publicactions&zone=media&action=getfiles&fileID=380&codeID=754ec7de52c2dbc05edcf37916fa6efe55b01193&resultID=c5946b01472b59c6724ff24e292e21608c148436&typemode=1490102272E237e912b1199912cfc611542924f16b736648c942&categoryID=-1";
        URL myurl = new URL(httpsURL);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String string, SSLSession ssls) {
                return true;
            }
        });
        InputStream ins = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader in = new BufferedReader(isr);

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public static void prueba1() throws MalformedURLException, IOException {
        String httpsURL = "https://esignature.iternova.net/index.php?type=publicactions&zone=media&action=getfiles&fileID=380&codeID=754ec7de52c2dbc05edcf37916fa6efe55b01193&resultID=c5946b01472b59c6724ff24e292e21608c148436&typemode=1490102272E237e912b1199912cfc611542924f16b736648c942&categoryID=-1";
        URL website = new URL(httpsURL);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("information.pdf");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    public static void prueba2() throws MalformedURLException, IOException {
        // Create a new trust manager that trust all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.err.println(e);
        }

        // And as before now you can use URL and URLConnection
        String httpsURL = "https://esignature.iternova.net/index.php?type=publicactions&zone=media&action=getfiles&fileID=380&codeID=754ec7de52c2dbc05edcf37916fa6efe55b01193&resultID=c5946b01472b59c6724ff24e292e21608c148436&typemode=1490102272E237e912b1199912cfc611542924f16b736648c942&categoryID=-1";
        URL url = new URL(httpsURL);
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader in = new BufferedReader(isr);
    }

    public static void prueba3() {
        String httpsURL = "https://esignature.iternova.net/index.php?type=publicactions&zone=media&action=getfiles&fileID=380&codeID=754ec7de52c2dbc05edcf37916fa6efe55b01193&resultID=c5946b01472b59c6724ff24e292e21608c148436&typemode=1490102272E237e912b1199912cfc611542924f16b736648c942&categoryID=-1";
        File f = new File(httpsURL);
        System.out.println(f.length());
    }


    public static void main(String[] args) throws Exception {
        prueba0();
    }

}

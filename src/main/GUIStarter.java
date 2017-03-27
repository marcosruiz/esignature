package main;

import java.awt.Image;

/**
 *
 * @author ITERNOVA [http://www.iternova.net]
 */
public class GUIStarter {
    public static void main(String[] args) {
        GUIView guiview = new GUIView();
        guiview.start();
        guiview.init();


        javax.swing.JFrame window = new javax.swing.JFrame("Esignature");
        window.setContentPane(guiview);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.pack();              // Arrange the components.
        window.setVisible(true);
        guiview.setVisible(true);
    }
}

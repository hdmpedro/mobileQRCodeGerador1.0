/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.qrcode;

import io.hdmpedro.qrcode.model.QrCode;
import io.hdmpedro.qrcode.view.MainFrame;
import io.hdmpedro.qrcode.controller.GerarController;
import javax.swing.SwingUtilities;


/**
 *
 * @author DSK-11
 */

public class MainClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame view = new MainFrame();
            new GerarController(view);
            view.setVisible(true);
        });
    }
    
   // public static String receberPar(String r){
        
      // return r;
        
        
   // }
    
}

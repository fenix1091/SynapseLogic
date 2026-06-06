/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synapselogic;

import javax.swing.SwingUtilities;

/**
 *
 * @author jd060
 */
public class SynapseLogic {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { 
            @Override
            public void run() { 
                try {
                    System.out.println("ABRIENDO");
                    VentanaPrincipal ventana = new VentanaPrincipal(); 
                    ventana.setVisible(true);
                    System.out.println("AQUITOY");
                } catch (Exception e) {
                    System.out.println("NOOO" + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}

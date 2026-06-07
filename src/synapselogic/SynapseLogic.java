/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synapselogic;

import javax.swing.SwingUtilities;

/**
 * Contiene el método de entrada principal (main) del sistema. 
 * Inicializa el código de la interfaz gráfica garantizando
 que la ventana principal se despliegue correctamente. 
 * @author jd060
 */
public class SynapseLogic {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { 
            @Override
            public void run() { 
                    VentanaPrincipal ventana = new VentanaPrincipal(); 
                    ventana.setVisible(true);
            }
        });
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synapselogic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.RenderingHints; 
import javax.swing.JPanel;

/**
 *
 * @author jd060
 */
public class PanelGrafo extends JPanel {
    private Grafo grafo; 
    
    public PanelGrafo() { 
        setBackground(Color.WHITE);
    }
    
    public void setGrafo(Grafo grafo) {
        this.grafo = grafo; 
        repaint();
    }
    
    @Override 
    public void paintComponent(Graphics g) { 
        super.paintComponent(g); 
        if(grafo == null || grafo.getTotalNeuronas() == 0) {
            return;
        }
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int total = grafo.getTotalNeuronas();
        int ancho = getWidth(); 
        int alto = getHeight(); 
        int centroX = ancho / 2;
        int centroY = alto / 2; 
        int radio = Math.min(ancho, alto) / 2 - 60; 
        
        int[] x = new int[total];
        int[] y = new int[total]; 
        String[] ids = new String[total]; 
        Neurona actual = grafo.getpHead(); 
        int i = 0;
        while(actual != null && i < total) {
            double angulo = 2 * Math.PI * i / total; 
            x[i] = centroX + (int) (radio * Math.cos(angulo)); 
            y[i] = centroY + (int) (radio * Math.sin(angulo)); 
            ids[i] = actual.getId();
            actual = actual.pNeurona; 
            i++;       
        }
        actual = grafo.getpHead();
        while(actual != null) {
            int o = obtenerIndiceLocal(ids, actual.getId());
            if (o >= 0){
                Sinapsis s = actual.getConexiones(); 
                while(s != null) {
                    int d = obtenerIndiceLocal(ids, s.getDestino().getId()); 
                    if (d >= 0) {
                        g2.setColor(Color.GRAY);
                        g2.drawLine(x[o], y[o], x[d], y[d]);
                        int medioX = (x[o] + x[d]) / 2;
                        int medioY = (y[o] + y[d]) / 2; 
                        g2.fillOval(medioX - 4, medioY - 4, 8, 8);
                        g2.setColor(Color.BLUE);
                        g2.drawString(s.getIdNeurotransmisor() + " (" + s.getCoeficiente() + ")", medioX + 6, medioY);
                    }
                    s = s.pSinapsis;              
                }
            }
            actual = actual.pNeurona;
        } 
        
        for(int k = 0; k < total; k++) { 
            if(ids[k] != null) {
                g2.setColor(new Color(180, 200, 255));
                g2.fillOval(x[k] - 20, y[k] - 20, 40, 40);
                g2.setColor(Color.BLACK);
                g2.drawOval(x[k] - 20, y[k] - 20, 40, 40);
                g2.drawString(ids[k], x[k] - 6, y[k] - 6);
            }
        }
    }
       
        
    private int obtenerIndiceLocal(String[] arr, String texto) { 
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] != null && arr[i].equals(texto)) { 
                    return i;
                }
            }
            return -1;
    }
}

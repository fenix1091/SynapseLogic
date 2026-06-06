package synapselogic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PanelGrafo extends JPanel {

    private Grafo grafo;

    public PanelGrafo() {
        setBackground(Color.WHITE);
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (grafo == null) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ArrayList<String> neuronas = grafo.getNeuronas();
        int total = neuronas.size();
        if (total == 0) {
            return;
        }

        int ancho = getWidth();
        int alto = getHeight();
        int centroX = ancho / 2;
        int centroY = alto / 2;
        int radio = Math.min(ancho, alto) / 2 - 60;

        int[] x = new int[total];
        int[] y = new int[total];
        for (int i = 0; i < total; i++) {
            double angulo = 2 * Math.PI * i / total;
            x[i] = centroX + (int) (radio * Math.cos(angulo));
            y[i] = centroY + (int) (radio * Math.sin(angulo));
        }

        ArrayList<Conexion> conexiones = grafo.getConexiones();
        for (int i = 0; i < conexiones.size(); i++) {
            Conexion c = conexiones.get(i);
            int o = neuronas.indexOf(c.getOrigen());
            int d = neuronas.indexOf(c.getDestino());
            if (o < 0 || d < 0) {
                continue;
            }
            g2.setColor(Color.GRAY);
            g2.drawLine(x[o], y[o], x[d], y[d]);

            int medioX = (x[o] + x[d]) / 2;
            int medioY = (y[o] + y[d]) / 2;
            g2.fillOval(medioX - 4, medioY - 4, 8, 8);
            g2.setColor(Color.BLUE);
            g2.drawString(c.getIdNeurotransmisor() + " (" + c.getDistancia() + ")", medioX + 6, medioY);
        }

        for (int i = 0; i < total; i++) {
            g2.setColor(new Color(180, 200, 255));
            g2.fillOval(x[i] - 20, y[i] - 20, 40, 40);
            g2.setColor(Color.BLACK);
            g2.drawOval(x[i] - 20, y[i] - 20, 40, 40);
            g2.drawString(neuronas.get(i), x[i] - 6, y[i] + 5);
        }
    }
}

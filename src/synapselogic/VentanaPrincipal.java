/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synapselogic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author jd060
 */


public class VentanaPrincipal extends JFrame {

    private Grafo grafo;
    private PanelGrafo panel;
    private HashTable diccionario;
    private JTextArea area;

    public VentanaPrincipal() {
        diccionario = new HashTable(); 
        grafo = new Grafo();
        grafo.setDiccionario(diccionario);

        setTitle("SynapseLogic - Analisis de Conectividad Neuronal");
        setSize(950, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel botones = new JPanel();
        JButton bCargar = new JButton("Cargar CSV");
        JButton bDicc = new JButton("Cargar Diccionario");
        JButton bZonas = new JButton("Zonas Aisladas");
        JButton bRuta = new JButton("Ruta Mas Corta");
        JButton bAgregar = new JButton("Agregar Neurona");
        JButton bConexion = new JButton("Agregar Conexion");
        JButton bFatiga = new JButton("Simular Fatiga");
        JButton bInfo = new JButton("Ver Neurotransmisor");

        botones.add(bCargar);
        botones.add(bDicc);
        botones.add(bZonas);
        botones.add(bRuta);
        botones.add(bAgregar);
        botones.add(bConexion);
        botones.add(bFatiga);
        botones.add(bInfo);

        panel = new PanelGrafo();
        panel.setGrafo(grafo);

        area = new JTextArea(8, 40);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);

        add(botones, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        bCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarCSV();
            }
        });
        bDicc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDiccionario();
            }
        });
        bZonas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zonasAisladas();
            }
        });
        bRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rutaMasCorta();
            }
        });
        bAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNeurona();
            }
        });
        bConexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarConexion();
            }
        });
        bFatiga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simularFatiga();
            }
        });
        bInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verNeurotransmisor();
            }
        });
    }

    private void cargarCSV() {
        JFileChooser selector = new JFileChooser();
        int respuesta = selector.showOpenDialog(this);
        if (respuesta != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File archivo = selector.getSelectedFile();
        try {
            grafo = new Grafo();
            grafo.setDiccionario(diccionario);
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea = lector.readLine(); 
            linea = lector.readLine();
            
            int conexionesContadas = 0;
            while (linea != null) {
                if (linea.trim().length() > 0) {
                    String[] partes = linea.split(",");
                    String origen = partes[0].trim();
                    String destino = partes[1].trim();
                    double distancia = Double.parseDouble(partes[2].trim());
                    String idNeuro = partes[3].trim();
                    double k = Double.parseDouble(partes[4].trim());
                   
                    grafo.agregarNeurona(origen, "Sensorial");
                    grafo.agregarNeurona(destino, "Motor");

                    grafo.agregarSinapsis(origen, destino, distancia, idNeuro, k);
                    conexionesContadas++;
                }
                linea = lector.readLine();
            }
            lector.close();
            panel.setGrafo(grafo);
            area.setText("Archivo de red neuronal cargado correctamente. \nNeuronas totales: " + grafo.getTotalNeuronas()
                    + "   Conexiones totales: " + conexionesContadas);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo CSV.");
        }
    }

    private void cargarDiccionario() {
        JFileChooser selector = new JFileChooser();
        int respuesta = selector.showOpenDialog(this);
        if (respuesta != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File archivo = selector.getSelectedFile();
        try {
            diccionario.vaciar(); 
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea = lector.readLine(); 
            linea = lector.readLine();
            while (linea != null) {
                if (linea.trim().length() > 0) {
                    String[] partes = linea.split(",", 5);
                    String id = partes[0].trim();
                    String nombre = partes[1].trim();
                    String efecto = partes[2].trim();
                    double velocidad = Double.parseDouble(partes[3].trim());
                    String descripcion = partes[4].trim();
                    if (descripcion.startsWith("\"")) {
                        descripcion = descripcion.substring(1);
                    }
                    if (descripcion.endsWith("\"")) {
                        descripcion = descripcion.substring(0, descripcion.length() - 1);
                    }
                    Neurotransmisor n = new Neurotransmisor(id, nombre, efecto, velocidad, descripcion);
                    diccionario.insertarHash(n); 
                }
                linea = lector.readLine();
            }
            lector.close();
            area.setText("Diccionario cargado correctamente.\nNeurotransmisores en la tabla hash: " + diccionario.getTamaño());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al leer el diccionario.");
        }
    }

    private void zonasAisladas() {
        if (grafo.getpHead() == null) {
            area.setText("El grafo está vacío.");
            return;
        }
        String idInicio = grafo.getpHead().getId();
        String[] ordenBfs = grafo.BFS(idInicio);
        
        StringBuilder sb = new StringBuilder();
        sb.append("Análisis de conectividad desde la neurona principal (").append(idInicio).append("):\n");
        sb.append("Componentes accesibles por activación: [");
        for (int i = 0; i < ordenBfs.length; i++) {
            sb.append(ordenBfs[i]);
            if (i < ordenBfs.length - 1) sb.append(", ");
        }
        sb.append("]\n");
        
        if (ordenBfs.length < grafo.getTotalNeuronas()) {
            sb.append("Pendiente: La red está fragmentada, por lo que existen neuronas o zonas aisladas.");
        } else {
            sb.append("La red neuronal está completamente conectada.");
        }
        area.setText(sb.toString());
    }

    private void rutaMasCorta() {
        String origen = JOptionPane.showInputDialog(this, "Neurona de origen:");
        if (origen == null || origen.trim().isEmpty()) {
            return;
        }
        String destino = JOptionPane.showInputDialog(this, "Neurona de destino:");
        if (destino == null || destino.trim().isEmpty()) {
            return;
        }

        String[] camino = grafo.caminoEfectivo(origen.trim(), destino.trim());
        if (camino.length == 0) {
            area.setText("No existe una ruta de transmisión sináptica entre " + origen + " y " + destino + ".");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Ruta más eficiente encontrada:\n");
            for (int i = 0; i < camino.length; i++) {
                sb.append(camino[i]);
                if (i < camino.length - 1) sb.append(" -> ");
            }
            area.setText(sb.toString());
        }
    }

    private void agregarNeurona() {
        String id = JOptionPane.showInputDialog(this, "Id de la nueva neurona:");
        if (id == null || id.trim().length() == 0) {
            return;
        }
        grafo.agregarNeurona(id.trim(), "Interneurona");
        panel.setGrafo(grafo);
        area.setText("Neurona agregada con éxito al sistema: " + id.trim());
    }

    private void agregarConexion() {
        String origen = JOptionPane.showInputDialog(this, "Neurona de origen:");
        if (origen == null || origen.trim().length() == 0) {
            return;
        }
        String destino = JOptionPane.showInputDialog(this, "Neurona de destino:");
        if (destino == null || destino.trim().length() == 0) {
            return;
        }
        String textoDistancia = JOptionPane.showInputDialog(this, "Distancia (ejemplo: 0.5):");
        if (textoDistancia == null) {
            return;
        }
        String idNeuro = JOptionPane.showInputDialog(this, "Id del neurotransmisor (ejemplo: GLU):");
        if (idNeuro == null) {
            return;
        }
        String textoK = JOptionPane.showInputDialog(this, "Coeficiente de eficiencia k (ejemplo: 1.0):");
        if (textoK == null) {
            return;
        }
        try {
            double distancia = Double.parseDouble(textoDistancia.trim());
            double k = Double.parseDouble(textoK.trim());
            
            grafo.agregarSinapsis(origen.trim(), destino.trim(), distancia, idNeuro.trim(), k);
            panel.setGrafo(grafo);
            area.setText("Sinapsis registrada: " + origen.trim() + " -> " + destino.trim()
                    + " [" + idNeuro.trim() + ", Distancia: " + distancia + ", Coeficiente: " + k + "]");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "La distancia y el coeficiente k deben ser valores numéricos válidos.");
        }
    }

    private void simularFatiga() {
        grafo.fatiga(); 
        panel.setGrafo(grafo);
        area.setText("Simulación de fatiga completada. Los coeficientes de resistencia sináptica aumentaron un 20%.");
    }

    private void verNeurotransmisor() {
        String id = JOptionPane.showInputDialog(this, "Id del neurotransmisor (ejemplo: GLU):");
        if (id == null) {
            return;
        }
        
        Neurotransmisor n = diccionario.getNeurotransmisor(id.trim());
        if (n == null) {
            area.setText("No se encontró ningún neurotransmisor con el ID: " + id);
        } else {
            area.setText("ID: " + n.getId() + " - Nombre: " + n.getNombre()
                    + "\nEfecto Sináptico: " + n.getEfecto()
                    + "\nVelocidad de Conducción base: " + n.getVelocidad() + " m/s"
                    + "\nDescripción Fisiológica: " + n.getDescripcion());
        }
    }
}
    

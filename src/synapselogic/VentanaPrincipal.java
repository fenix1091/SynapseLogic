package synapselogic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaPrincipal extends JFrame {

    private Grafo grafo;
    private TablaHash diccionario;
    private PanelGrafo panel;
    private JTextArea area;

    public VentanaPrincipal() {
        diccionario = new TablaHash();
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
        JButton bEliminar = new JButton("Eliminar Neurona");
        JButton bFatiga = new JButton("Simular Fatiga");
        JButton bInfo = new JButton("Ver Neurotransmisor");

        botones.add(bCargar);
        botones.add(bDicc);
        botones.add(bZonas);
        botones.add(bRuta);
        botones.add(bAgregar);
        botones.add(bConexion);
        botones.add(bEliminar);
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
            public void actionPerformed(ActionEvent e) {
                cargarCSV();
            }
        });
        bDicc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDiccionario();
            }
        });
        bZonas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zonasAisladas();
            }
        });
        bRuta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rutaMasCorta();
            }
        });
        bAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarNeurona();
            }
        });
        bConexion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarConexion();
            }
        });
        bEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarNeurona();
            }
        });
        bFatiga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simularFatiga();
            }
        });
        bInfo.addActionListener(new ActionListener() {
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
            while (linea != null) {
                if (linea.trim().length() > 0) {
                    String[] partes = linea.split(",");
                    String origen = partes[0].trim();
                    String destino = partes[1].trim();
                    double distancia = Double.parseDouble(partes[2].trim());
                    String idNeuro = partes[3].trim();
                    double k = Double.parseDouble(partes[4].trim());
                    Conexion c = new Conexion(origen, destino, distancia, idNeuro, k);
                    grafo.agregarConexion(c);
                }
                linea = lector.readLine();
            }
            lector.close();
            panel.setGrafo(grafo);
            area.setText("Archivo cargado correctamente.\nNeuronas: " + grafo.getNeuronas().size()
                    + "   Conexiones: " + grafo.getConexiones().size());
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
                    diccionario.insertar(n);
                }
                linea = lector.readLine();
            }
            lector.close();
            area.setText("Diccionario cargado correctamente.\nNeurotransmisores: " + diccionario.getTamano());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al leer el diccionario.");
        }
    }

    private void zonasAisladas() {
        ArrayList<ArrayList<String>> componentes = grafo.zonasAisladas();
        String texto = "Se encontraron " + componentes.size() + " zona(s):\n";
        for (int i = 0; i < componentes.size(); i++) {
            texto = texto + "Zona " + (i + 1) + ": " + componentes.get(i) + "\n";
        }
        if (componentes.size() > 1) {
            texto = texto + "La red esta fragmentada: existen zonas aisladas.";
        } else {
            texto = texto + "La red esta completamente conectada.";
        }
        area.setText(texto);
    }

    private void rutaMasCorta() {
        String origen = JOptionPane.showInputDialog(this, "Neurona de origen:");
        if (origen == null) {
            return;
        }
        String destino = JOptionPane.showInputDialog(this, "Neurona de destino:");
        if (destino == null) {
            return;
        }
        ArrayList<String> camino = grafo.caminoMasCorto(origen.trim(), destino.trim());
        if (camino.isEmpty()) {
            area.setText("No existe una ruta entre " + origen + " y " + destino + ".");
        } else {
            double costo = grafo.costoMasCorto(origen.trim(), destino.trim());
            area.setText("Ruta mas corta (mayor activacion): " + camino + "\nTiempo total de transmision: " + costo);
        }
    }

    private void agregarNeurona() {
        String id = JOptionPane.showInputDialog(this, "Id de la nueva neurona:");
        if (id == null || id.trim().length() == 0) {
            return;
        }
        grafo.agregarNeurona(id.trim());
        panel.setGrafo(grafo);
        area.setText("Neurona agregada: " + id.trim());
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
        String textoK = JOptionPane.showInputDialog(this, "Coeficiente de eficiencia k (ejemplo: 1):");
        if (textoK == null) {
            return;
        }
        try {
            double distancia = Double.parseDouble(textoDistancia.trim());
            double k = Double.parseDouble(textoK.trim());
            Conexion c = new Conexion(origen.trim(), destino.trim(), distancia, idNeuro.trim(), k);
            grafo.agregarConexion(c);
            panel.setGrafo(grafo);
            area.setText("Conexion agregada: " + origen.trim() + " -> " + destino.trim()
                    + " (" + idNeuro.trim() + ", distancia " + distancia + ", k " + k + ")");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Distancia y k deben ser numeros.");
        }
    }

    private void eliminarNeurona() {
        String id = JOptionPane.showInputDialog(this, "Id de la neurona a eliminar:");
        if (id == null) {
            return;
        }
        grafo.eliminarNeurona(id.trim());
        panel.setGrafo(grafo);
        area.setText("Neurona eliminada: " + id.trim());
    }

    private void simularFatiga() {
        grafo.simularFatiga();
        panel.setGrafo(grafo);
        area.setText("Se simulo la fatiga. Ahora puedes recalcular la ruta o las zonas aisladas.");
    }

    private void verNeurotransmisor() {
        String id = JOptionPane.showInputDialog(this, "Id del neurotransmisor (ejemplo: GLU):");
        if (id == null) {
            return;
        }
        Neurotransmisor n = diccionario.obtener(id.trim());
        if (n == null) {
            area.setText("No se encontro el neurotransmisor: " + id);
        } else {
            area.setText(n.getId() + " - " + n.getNombre()
                    + "\nEfecto: " + n.getEfecto()
                    + "\nVelocidad: " + n.getVelocidad()
                    + "\nDescripcion: " + n.getDescripcion());
        }
    }
}

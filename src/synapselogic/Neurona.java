/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synapselogic;

/**
 * Representa un nodo dentro del grafo de la red neuronal.
 * Contiene la información biológica: id, tipo (Sensorial, Motor o Interneurona)
 y los apuntadores hacia las conexiones y la siguiente neurona.
 * @author jd060
 */
public class Neurona {
    private String id;
    private String tipo; 
    private Sinapsis conexiones;
    public Neurona pNeurona; 

    public Neurona(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
        this.conexiones = null;
        this.pNeurona = null; 
    }

    public void agregarConexion(Sinapsis enlace) { 
        if (enlace == null) {
            return;
        } else {
            enlace.pSinapsis = this.conexiones;
            this.conexiones = enlace;
        }
    }

    public String getId() {
        return id;
    } 

    public String getTipo() {
        return tipo;
    } 

    public Sinapsis getConexiones() {
        return conexiones;
    } 
}
    

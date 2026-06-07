/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synapselogic;

/**
 * Representa la red neuronal completa mediante un Grafo Dirigio.
 * Implementa una Lista de Adyacencia nativa sin la implementación de librerías. 
 * @author jd060
 */

public class Grafo {
    private Neurona pHead;
    private int total;
    private HashTable diccionario;

    public Grafo() {
      this.pHead = null;
      this.total = 0;
      this.diccionario = null;
    }

    public void setDiccionario(HashTable diccionario) {
      this.diccionario = diccionario; 
    } 

    public void agregarNeurona(String id, String tipo) {
        if (buscarNeurona(id) != null) { 
          return; 
      } else { 
        Neurona nuevaNe = new Neurona(id, tipo);
        nuevaNe.pNeurona = pHead;
        pHead = nuevaNe; 
        total++;
      } 
    }

    public Neurona buscarNeurona(String id) { 
        Neurona pActual = pHead; 
        while (pActual != null) { 
            if (pActual.getId().equals(id)) { 
                return pActual;
            } 
            pActual = pActual.pNeurona; 
        }
        return null;
    }

    public void agregarSinapsis(String idOrigen, String destino, double distancia, String idNeurotransmisor, double coeficiente) {
        Neurona origen = buscarNeurona(idOrigen);
        Neurona dest = buscarNeurona(destino);
        if (origen != null && destino != null) {
            Sinapsis nuevaSp = new Sinapsis (dest, distancia, idNeurotransmisor, coeficiente);
            origen.agregarConexion(nuevaSp);
        }
    }

    public void fatiga() { 
        Neurona n = pHead; 
        while (n != null) { 
            Sinapsis s = n.getConexiones();
            while (s != null) { 
                s.setCoeficiente(s.getCoeficiente() * 1.2);
                s = s.pSinapsis; 
            }
             n = n.pNeurona; 
         } 
     } 
    
    /**
     * Ejecuta el algoritmo de Búsqueda en Anchura (Breadth-first search).
     * @param idInicio
     * @return 
     */

    public String[] BFS(String idInicio) {
        Neurona inicio = buscarNeurona(idInicio);
        if (inicio == null) { 
            return new String[0];
        }
        String[] cola = new String[total];
        String[] visitados = new String[total];
        int head = 0;
        int last = 0;
        int contVisitados = 0;
        cola[last] = inicio.getId();
        last++;
        visitados[contVisitados] = inicio.getId();
        contVisitados++;

        while (head < last) {
            String actualId = cola[head];
            head++;
            Neurona actualNodo = buscarNeurona(actualId);
            if (actualNodo != null) { 
                Sinapsis s = actualNodo.getConexiones();
                while (s != null) { 
                    String idDestino = s.getDestino().getId(); 
                    if (contiene(visitados, contVisitados, idDestino) == false) {
                        visitados[contVisitados] = idDestino; 
                        contVisitados++;
                        cola[last] = idDestino;
                        last++;
                    }
                    s = s.pSinapsis; 
                }
            }
        }
        return vaciar(visitados, contVisitados);
    }

    /**
     * Calcula la ruta más corta de transmisión utilizando una adaptación
    del algoritmo de Dijkstra basada en: distancia sináptica y 
    coeficiente de eficienciade los neurotransmisores. 
     * @param idOrigen
     * @param idDestino
     * @return 
     */
    
    public String[] caminoEfectivo(String idOrigen, String idDestino) {
        if (buscarNeurona(idOrigen) == null || buscarNeurona(idDestino) == null) { 
            return new String[0];
        } 
        String[] mapa = new String[total]; 
        Neurona current = pHead;
        int index = 0;
        while (current != null) { 
            mapa[index++] = current.getId();
            current = current.pNeurona; 
        }
        double[] distancia = new double[total];
        int[] previo = new int[total];
        boolean[] visitado = new boolean[total];

        for (int i = 0; i < total; i++) {
            distancia[i] = Double.MAX_VALUE; 
            previo[i] = -1; 
            visitado[i] = false; 
        } 
        int idxI = obtenerIndice(mapa, idOrigen); 
        distancia[idxI] = 0; 

        for (int paso = 0; paso < total; paso++) { 
            int u = -1; 
            double menor = Double.MAX_VALUE; 
            for(int i = 0; i < total; i++) { 
                if(!visitado[i] && distancia[i] < menor) { 
                    menor = distancia[i]; 
                    u = i;
                } 
            }
            if (u == -1) { 
                break;
            } 
            visitado[u] = true; 
            
            Neurona nodoU =  buscarNeurona(mapa[u]); 
            if (nodoU != null) { 
                Sinapsis s = nodoU.getConexiones();
                while (s != null) { 
                    int v = obtenerIndice(mapa, s.getDestino().getId());
                    double peso = s.calcularPeso(this.diccionario); 
                    if (distancia[u] + peso < distancia[v]) { 
                        distancia[v] = distancia[u] + peso; 
                        previo[v] = u;
                    }
                    s = s.pSinapsis; 
                }
             }
         }
         
         int idxF = obtenerIndice(mapa, idDestino); 
         if (distancia[idxF] == Double.MAX_VALUE) { 
             return new String[0]; 
         }
         int aux = idxF;
         int ctCamino = 0; 
         while (aux != -1) {
             ctCamino++; 
             aux = previo[aux]; 
         }
         String[] camino = new String[ctCamino];
         aux = idxF; 
         for (int i = ctCamino -1; i >= 0; i--) { 
             camino[i] = mapa[aux]; 
             aux = previo[aux]; 
         } 
         return camino; 
    }

    private boolean contiene(String[] arr, int tam, String texto) { 
        for (int i = 0; i < tam; i++) {
            if (arr[i].equals(texto)) { 
                return true;
            } 
        }
    return false;
    }

    private int obtenerIndice(String[] arr, String texto) { 
        for (int i = 0; i < arr.length; i++) { 
            if (arr[i].equals(texto)) { 
                return i; 
            } 
        }
        return -1;     
    }

    private String[] vaciar(String[] original, int tamReal) { 
        String[] resultado = new String[tamReal]; 
        for (int i = 0; i < tamReal; i++) { 
            resultado[i] = original[i]; 
        } 
        return resultado;
    }

    public int getTotalNeuronas () { 
        return total;
    }   
    
    public Neurona getpHead() {
        return pHead; 
    } 
    
}

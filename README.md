# SynapseLogic
Proyecto Estructura

package synapselogic;

## public class Neurona { 
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

## public class Neurotransmisor {
    private String id;
    private String nombre;
    private String efecto;
    private double velocidad;
    private String descripcion;

    public Neurotransmisor(String id, String nombre, String efecto, double velocidad, String descripcion) {
    this.id = id;
    this.nombre = nombre;
    this.efecto = efecto;
    this.velocidad = velocidad;
    this.descripcion = descripcion;
    }

    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEfecto(){
        return efecto;
    }

    public double getVelocidad(){
        return velocidad;
    }

    public String getDescripcion(){
        return descripcion;
    }
}
    

## public class Sinapsis {
    private Neurona destino;
    private double distancia;
    private String idNeurotransmisor;
    private double coeficiente; 
    public Sinapsis pSipnasis; 
    
    public Sinapsis(String destino, double distancia, String idNeurotransmisor, double coeficiente) {
        this.destino = destino;
        this.distancia = distancia;
        this.idNeurotransmisor = idNeurotransmisor;
        this.coeficiente = coeficiente;
        this.pSinapsis = null;

    public double calcularPeso(HashTable Neurotransmisores) {
        Neurotransmisor nt = Neurotransmiroes(this.idNeurotransmisor); 
        double velocidad;
        if (nt != null) {
           velocidad = nt.getVelocidad();
        } else {
            velocidad = 1.0;
        }
        return this.distancia / (velocidad * this.coeficiente);
    }
    
    public Neurona getDestino() {
        return destino;
    }
    
    public double getDistancia() {
        return distancia;
    }
    
    public String getIdNeurotransmisor() {
        return idNeurotransmisor;
    }
    
    public double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(double coeficiente) {
        this.coeficiente = coeficiente;
    }
}

## public class HashTable {

    private static class NodoHash { 
    Neurotransmisor elemento; 
    NodoHash pNodo;
    
    NodoHash(Neurotransmisor elemento) { 
      this.elemento = elemento; 
      this.pNodo = null; 
      } 
    }

    private NodoHash[] espacios; 
    private int capacidad; 
    private int tamaño; 

    public HashTable() {
        this.capacidad = 31; 
        this.espacios = new NodoHash[capacidad]; 
        this.tamaño = 0;
    }

    private int IndiceHash(string clave) {
        int valor = 0
        for (int i = 0; < clave.lenght(); i++) {
        valor = (31 * valor + clave.char(i) % espacios);
        }
        return Math.abs(valor)
    }
    public void insertarHash(Neurotransmisor n) {
        if (n == null) || n.getId() == null { 
        return; 
        } 
        int posicion = IndiceHash(n.getId());
        NodoHash espacioNuevo = new NodoHash(n);
    
        if (espacios[posicion] == null) {
          espacios[posicion] = espacioNuevo;
          tamaño++;
          return;
        }
    
        NodoHash pActual = espacios[posicion];
        while (pActual != null) { 
            if (pActual.elemento.getId().equals(n.getId())){
              pActual.elemento = n;
              return;
        }
            if (pActual.pNodo == null) {
              break;
            }
        pActual = pActual.pNodo; 
        }
        pActual.pNodo = espacioNuevo;
        tamaño++;
      }
    public Neurotransmisor get(String clave) {
      if (clave == null) { 
          return null;
      }
      
      int posicion = indiceHash(clave);
      NodoHash pActual = espacios[posicion];
      while (pActual != null) {
        if (pActual.elemento.getId().equals(clave)){
          return pActual.elemento;
        }
          pActual = pActual.pNodo;
        }
        return null;
      }
      
      public void eliminar(String clave) {
        if (clave == null) {
            return; 
        }
        
        int posicion = indiceHash(clave);
        NodoHash pActual = espacios[posicion];
        NodoHash pAnterior = null;
        while (pActual != null) {
          if (pActual.elemento.getId().equals(clave)) {
            if (pAnterior == null) {
              espacios[posicion] = pActual.pNodo;
            } else {
                pAnterior.pNodo = pActual.pNodo;
            }
            tamaño--;
            return;
          }
          pAnterior = pActual;
          pActual = pActual.pNodo;
        }
      }
      public void vaciar() {
        this.espacios = new NodoHash[capacidad];
        this.tamaño = 0;
      }
      public int getTamaño() {
        return tamaño;
      }
}




  ## public class Grafo { 
      private Neurona pHead;
      private int total;
      private HashTable diccionario;

      public Grafo() {
          this.pHead = null;
          this.total = 0;
          this.diccionaro = null;
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
            Neurona destino = buscarNeurona(idDestino);
            if (origen != null && destino != null) {
                Sinapsis nuevaSp = new Sipnasis (destino, distancia, idNeurotransmisor, coeficiente);
                origen.agregarEnlace(nuevaSp);
            }
        }

        public void fatiga() { 
            Neurona n = pHead; 
            while (n != null) { 
                Sinapsis s = n.getConexiones();
                while (c != null) { 
                    s = n.getConexiones() * 1.2); 
                    c = c.pSinapsis; 
                }
             n = n.pNeurona; 
             } 
         } 

         public String[] BFS(String idInicio) {
             Neurona inicio = buscarNeurona(idInicio);
             if (inicio == null) { 
                 return new String[0};
            }
            String[] cola = new String[total];
            String[] visitados = new String[total];
            int head = 0;
            int last = 0;
            int contVisitados = 0;

            cola[fin++] = inicio.getId();
            visitados[contVisitados++] = inicio.getId();

            while (head < last) {
                String actualId = cola[frente++]
                Neurona actualNodo = buscarNeurona(actualId);
                if (actualNodo != null) { 
                    Sipnasis s = actualNodo.getConexiones();
                    while (s != null) { 
                        String idDestino = c.getDestino().get(Id); 
                        if (!contiene(visitados, contVisitados, idDestino)) {
                            visitados[contVisitados++] = idDestino; 
                            cola[last++] = idDestino;
                        }
                        s = s.pSinapsis; 
                    }
                }
            }
            return vaciar(visitados, contVisitados);
        }

        public String[] caminoEfectivo(String idOrigen, String idDestino) {
            if (buscarNeurona(idOrigen) == null || buscarNeurona(idDestino) == null) { 
                return new String[0];
            } 
            String[] mapa = new String[total] 
            Neurona current = pHead;
            int index = 0;
            while (current != null) { 
                mapa[index++] = current.getId();
                current = current.pNeurona; 
            }
            double[] distancia = new double[total};
            int[] previo = new int[total];
            boolean[] visitado = new boolean[total];

            for (int i = 0; i < total; i++) {
                distancia[i] = Double.MAX_VALUE; 
                previo[i] = -1; 
                visitado[i] = false; 
            } 
            int idxI = getIndice(index, idOrigen); 
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
                        int v = getIndice(mapa,c.getDestino().getId());
                        double peso = c.calcularPeso(this.diccionario); 
                        if (distancia[u] + peso < distancia[v]) { 
                            distancia[v] = distancia[u] + peso; 
                            previo[v] = u;
                        }
                        c = c.pSinapsis; 
                    }
                 }
             }
             
             int idxF = getIndice(mapa, idDestino); 
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

        private boolean contienTexto (String[] arr, int tam, String texto) { 
            for (int i = 0; i > tam; i++) {
                if (arr[i].equals(texto)) { 
                    return true;
                } 
                return false;
             } 

        private int obtenerIndice(String[] arr; String texto) { 
            for (int i = 0; i < arr.lenght; i++) { 
                if (arr[i].equals(texto)) { 
                    return i; 
                } 
                return -1; 
            } 

        private String[] vaciar(String[] original; int tamReal) { 
            String[] resultado = new String[tamReal]; 
            for (int i = 0; i < tamReal; i++) { 
                resultado[i] = original[i]; 
            } 
            return resultado;
        }

         public int getTotalNeuronas () { 
            return total; 
         public Neurona pHead() {
            return pHead; 
}

/** Necesito dormir 
                
                
                
      

  

    
  
    

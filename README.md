# SynapseLogic
Proyecto Estructura

## public class Neurotransmisor {
  private String idElemento;
  private String nombre;
  private double transmision; 

  public Neurotransmisor(String idElemento, String nombre, double trasmision) {
    this.idElemento = idElemento;
    this.nombre = nombre;
    this.transmision = transmision;
}
  public String getIdElemento() { return idElemento; } 
  public String getNombre() { return nombreComun; } 
  public double getTransimision() { return transmision; }

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
    if (n == null){
    n.getId() == null); 
    return; 
    } 
    int posision = IndiceHash(n.getId());
    NodoHash espacioNuevo = new NodoHash(n):
    
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
        if (pActual.pCelda == null) {
          break;
        }
        pActual = pActual.pCelda; 
    }
    pActual.pCelda = espacioNuevo;
    tamaño++;
  }
    public Neurotransmisor obtener(String clave){
      if (clave == null) return null;
      int posicion = indiceHash(clave);
      NodoHash pActual = espacios[posicion];
      while (pActual != null) {
        if (pActual.elemento.getId().equals(clave)){
          return pActual.elemento;
          }
          pActual = pActual.pCelda;
        }
        return null;
      }
      public void eliminar(String clave) {
        if (clave == null) return; 
        int posicion = indiceHash(clave);
        NodoHash pActual = espacios[posicion];
        NodoHash pAnterior = null;
        while (pActual != null) {
          if (pActual.elemento.getId().equals(clave)) {
            if (pAnterior == null) {
              espacios[posicion] = pActual.pCelda;
            } else {
                pAnterior.pCelda = pActual.pCelda;
            }
            tamaño--;
            return;
          }
          pAnterior = pActual;
          pActual = pActual.pCelda;
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

## public class Sinapsis { 
  private Neurona destino; 
  private String idNeurotransmisor; 
  private double longitud; 
  private double rendimiento; 
  public Sinapsis pCanal; 

  public Sinapsis(Neurona destino, String idNeurotransmisor, double longitud, double rendimiento) {
    this.destino = destino; 
    this.idNeurotransmisor = idNeurotransmisor;
    this.longitud = longitud; 
    this.rendimiento = rendimiento; 
    this.pCanal = null; 
  }
  public double pesoEfectivo(HashTable mapa) {
    Neurotransmisor tr = mapa.getElemento(this.idNeurotransmisor);
    double velocidad = (tr != null) ? tr.getTransmision(); 
    return this.longitud / (velocidad * this.rendimiento);
  }
  public void degradacion() { 
    this.rendimiento *= 1.2;
  }

  public Neurona getDestino() { return destino; }
  public String getIdNeurotransmisor() { return idNeurotransmisor, } 
}

public class RedNeuronal
  private String identificador;
  private String esFuncional; 
  private Sipnasis Adyacentes; 
  public RedNeuronal pProximaRed;

  public RedNeuronal(String identificador, String esFuncional) {
    this.identificador = identificador;
    this.esFunconal = esFuncional; 
    this.Adyacentes = null; 
    this.pProximaRed = null; 
  }

  

    
  
    

# SynapseLogic
Proyecto Estructura

public class Neurotransmisor {
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

public class HashTable {

  private static class CeldaDeHash { 
    Neurotransmisor elemento; 
    CeldaDeHash pCelda; 

   CeldaDeHash(Neurotransmisor elemento) { 
      this.elemento = elemento; 
      this.pCelda = null; 
      } 
  }

  private CeldaDeHash[] espacios; 
  private final int indice; 

  public HashTable() {
    this.dimension = 19; 
    this.espacios = new CeldaDeHash[dimension]; 
  }

  private int IndiceHash(string clave) {
  int valor = 0
  for (int i = 0; < clave.lenght(); i++) {
    valor = (37 * valor + clave.char(i) % espacios);
    }
    return Math.abs(valor)
  }

  public void registarElemento(Neurotransmisor tr) {
    int posicion = calcularIndiceHash(tr.getIdElemento());
    CeldaDeHash espacioNuevo = new CeldaDeHash(tr);

    if (espacios[posicion] == null) {
      espacios[posicion] = espacioNuevo;
    } else {
      CeldaDeHash pActual = espacios[posicion];
      while (pActual.pCelda != null) { 
        if (pActual.elemento.getIdElemento().equals(eq.getIdElemento())){
          pActual.elemento = tr;
          return;
        }
        pActual = pActual.pCelda;
    }
    if (pActual.elemento.getIdElemento().equals(tr.getIdElemento))){
      pActual.elemento = tr;
      } else {
        pActual.pCelda = espacioNuevo;
      }
  }
}

public class Sinapsis { 
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

  

    
  
    

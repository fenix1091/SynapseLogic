public class Sinapsis { 
  private Neurona destino;
  private double distancia;
  private String idNeurotransmisor;
  private double coeficiente; 
  public Sinapsis pSinapsis; 

  public Sinapsis(Neurona destino, double distancia, String idNeurotransmisor, double coeficiente) {
    this.destino = destino;
    this.distancia = distancia;
    this.idNeurotransmisor = idNeurotransmisor;
    this.coeficiente = coeficiente;
    this.pSinapsis = null;
  } 

  public double calcularPeso(HashTable Neurotransmisores) {
    Neurotransmisor nt = Neurotransmirores.get(this.idNeurotransmisor); 
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

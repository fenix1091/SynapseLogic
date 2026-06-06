package synapselogic;

public class Conexion {

    private String origen;
    private String destino;
    private double distancia;
    private String idNeurotransmisor;
    private double coeficiente;

    public Conexion(String origen, String destino, double distancia, String idNeurotransmisor, double coeficiente) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.idNeurotransmisor = idNeurotransmisor;
        this.coeficiente = coeficiente;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
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

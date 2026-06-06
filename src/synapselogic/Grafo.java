package synapselogic;

import java.util.ArrayList;

public class Grafo {

    private ArrayList<String> neuronas;
    private ArrayList<Conexion> conexiones;
    private TablaHash diccionario;

    public Grafo() {
        neuronas = new ArrayList<String>();
        conexiones = new ArrayList<Conexion>();
    }

    public void setDiccionario(TablaHash diccionario) {
        this.diccionario = diccionario;
    }

    public ArrayList<String> getNeuronas() {
        return neuronas;
    }

    public ArrayList<Conexion> getConexiones() {
        return conexiones;
    }

    public void agregarNeurona(String id) {
        if (!neuronas.contains(id)) {
            neuronas.add(id);
        }
    }

    public void agregarConexion(Conexion c) {
        agregarNeurona(c.getOrigen());
        agregarNeurona(c.getDestino());
        conexiones.add(c);
    }

    public void eliminarNeurona(String id) {
        neuronas.remove(id);
        ArrayList<Conexion> quedan = new ArrayList<Conexion>();
        for (int i = 0; i < conexiones.size(); i++) {
            Conexion c = conexiones.get(i);
            if (!c.getOrigen().equals(id) && !c.getDestino().equals(id)) {
                quedan.add(c);
            }
        }
        conexiones = quedan;
    }

    public double calcularPeso(Conexion c) {
        double velocidad = 1.0;
        if (diccionario != null) {
            Neurotransmisor n = diccionario.obtener(c.getIdNeurotransmisor());
            if (n != null) {
                velocidad = n.getVelocidad();
            }
        }
        double k = c.getCoeficiente();
        if (velocidad * k == 0) {
            return 0;
        }
        return c.getDistancia() / (velocidad * k);
    }

    public ArrayList<String> vecinos(String id) {
        ArrayList<String> lista = new ArrayList<String>();
        for (int i = 0; i < conexiones.size(); i++) {
            Conexion c = conexiones.get(i);
            if (c.getOrigen().equals(id)) {
                lista.add(c.getDestino());
            }
        }
        return lista;
    }

    public ArrayList<String> vecinosNoDirigido(String id) {
        ArrayList<String> lista = new ArrayList<String>();
        for (int i = 0; i < conexiones.size(); i++) {
            Conexion c = conexiones.get(i);
            if (c.getOrigen().equals(id)) {
                lista.add(c.getDestino());
            }
            if (c.getDestino().equals(id)) {
                lista.add(c.getOrigen());
            }
        }
        return lista;
    }

    public ArrayList<String> recorridoBFS(String inicio) {
        ArrayList<String> visitados = new ArrayList<String>();
        ArrayList<String> cola = new ArrayList<String>();
        if (!neuronas.contains(inicio)) {
            return visitados;
        }
        cola.add(inicio);
        visitados.add(inicio);
        while (!cola.isEmpty()) {
            String actual = cola.remove(0);
            ArrayList<String> v = vecinos(actual);
            for (int i = 0; i < v.size(); i++) {
                String n = v.get(i);
                if (!visitados.contains(n)) {
                    visitados.add(n);
                    cola.add(n);
                }
            }
        }
        return visitados;
    }

    public ArrayList<String> recorridoDFS(String inicio) {
        ArrayList<String> visitados = new ArrayList<String>();
        if (neuronas.contains(inicio)) {
            dfs(inicio, visitados);
        }
        return visitados;
    }

    private void dfs(String actual, ArrayList<String> visitados) {
        visitados.add(actual);
        ArrayList<String> v = vecinos(actual);
        for (int i = 0; i < v.size(); i++) {
            if (!visitados.contains(v.get(i))) {
                dfs(v.get(i), visitados);
            }
        }
    }

    public ArrayList<ArrayList<String>> zonasAisladas() {
        ArrayList<ArrayList<String>> componentes = new ArrayList<ArrayList<String>>();
        ArrayList<String> visitados = new ArrayList<String>();
        for (int i = 0; i < neuronas.size(); i++) {
            String inicio = neuronas.get(i);
            if (!visitados.contains(inicio)) {
                ArrayList<String> componente = new ArrayList<String>();
                ArrayList<String> cola = new ArrayList<String>();
                cola.add(inicio);
                visitados.add(inicio);
                componente.add(inicio);
                while (!cola.isEmpty()) {
                    String actual = cola.remove(0);
                    ArrayList<String> v = vecinosNoDirigido(actual);
                    for (int j = 0; j < v.size(); j++) {
                        String n = v.get(j);
                        if (!visitados.contains(n)) {
                            visitados.add(n);
                            componente.add(n);
                            cola.add(n);
                        }
                    }
                }
                componentes.add(componente);
            }
        }
        return componentes;
    }

    public ArrayList<String> caminoMasCorto(String origen, String destino) {
        ArrayList<String> camino = new ArrayList<String>();
        int total = neuronas.size();
        int inicio = neuronas.indexOf(origen);
        int fin = neuronas.indexOf(destino);
        if (inicio == -1 || fin == -1) {
            return camino;
        }
        double[] dist = new double[total];
        int[] previo = new int[total];
        boolean[] visitado = new boolean[total];
        for (int i = 0; i < total; i++) {
            dist[i] = Double.MAX_VALUE;
            previo[i] = -1;
            visitado[i] = false;
        }
        dist[inicio] = 0;
        for (int paso = 0; paso < total; paso++) {
            int u = -1;
            double menor = Double.MAX_VALUE;
            for (int i = 0; i < total; i++) {
                if (!visitado[i] && dist[i] < menor) {
                    menor = dist[i];
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            visitado[u] = true;
            String idU = neuronas.get(u);
            for (int i = 0; i < conexiones.size(); i++) {
                Conexion c = conexiones.get(i);
                if (c.getOrigen().equals(idU)) {
                    int v = neuronas.indexOf(c.getDestino());
                    double peso = calcularPeso(c);
                    if (dist[u] + peso < dist[v]) {
                        dist[v] = dist[u] + peso;
                        previo[v] = u;
                    }
                }
            }
        }
        if (dist[fin] == Double.MAX_VALUE) {
            return camino;
        }
        int actual = fin;
        while (actual != -1) {
            camino.add(0, neuronas.get(actual));
            actual = previo[actual];
        }
        return camino;
    }

    public double costoMasCorto(String origen, String destino) {
        int total = neuronas.size();
        int inicio = neuronas.indexOf(origen);
        int fin = neuronas.indexOf(destino);
        if (inicio == -1 || fin == -1) {
            return -1;
        }
        double[] dist = new double[total];
        boolean[] visitado = new boolean[total];
        for (int i = 0; i < total; i++) {
            dist[i] = Double.MAX_VALUE;
            visitado[i] = false;
        }
        dist[inicio] = 0;
        for (int paso = 0; paso < total; paso++) {
            int u = -1;
            double menor = Double.MAX_VALUE;
            for (int i = 0; i < total; i++) {
                if (!visitado[i] && dist[i] < menor) {
                    menor = dist[i];
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            visitado[u] = true;
            String idU = neuronas.get(u);
            for (int i = 0; i < conexiones.size(); i++) {
                Conexion c = conexiones.get(i);
                if (c.getOrigen().equals(idU)) {
                    int v = neuronas.indexOf(c.getDestino());
                    double peso = calcularPeso(c);
                    if (dist[u] + peso < dist[v]) {
                        dist[v] = dist[u] + peso;
                    }
                }
            }
        }
        if (dist[fin] == Double.MAX_VALUE) {
            return -1;
        }
        return dist[fin];
    }

    public void simularFatiga() {
        for (int i = 0; i < conexiones.size(); i++) {
            Conexion c = conexiones.get(i);
            c.setCoeficiente(c.getCoeficiente() * 1.2);
        }
    }
}

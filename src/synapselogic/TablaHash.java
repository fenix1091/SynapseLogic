package synapselogic;

import java.util.ArrayList;

public class TablaHash {

    private ArrayList<Neurotransmisor>[] cubetas;
    private int capacidad;
    private int tamano;

    public TablaHash() {
        capacidad = 31;
        cubetas = new ArrayList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            cubetas[i] = new ArrayList<Neurotransmisor>();
        }
        tamano = 0;
    }

    private int funcionHash(String clave) {
        int suma = 0;
        for (int i = 0; i < clave.length(); i++) {
            suma = suma + clave.charAt(i);
        }
        return suma % capacidad;
    }

    public void insertar(Neurotransmisor n) {
        int pos = funcionHash(n.getId());
        ArrayList<Neurotransmisor> cubeta = cubetas[pos];
        for (int i = 0; i < cubeta.size(); i++) {
            if (cubeta.get(i).getId().equals(n.getId())) {
                cubeta.set(i, n);
                return;
            }
        }
        cubeta.add(n);
        tamano = tamano + 1;
    }

    public Neurotransmisor obtener(String clave) {
        int pos = funcionHash(clave);
        ArrayList<Neurotransmisor> cubeta = cubetas[pos];
        for (int i = 0; i < cubeta.size(); i++) {
            if (cubeta.get(i).getId().equals(clave)) {
                return cubeta.get(i);
            }
        }
        return null;
    }

    public void eliminar(String clave) {
        int pos = funcionHash(clave);
        ArrayList<Neurotransmisor> cubeta = cubetas[pos];
        for (int i = 0; i < cubeta.size(); i++) {
            if (cubeta.get(i).getId().equals(clave)) {
                cubeta.remove(i);
                tamano = tamano - 1;
                return;
            }
        }
    }

    public void vaciar() {
        for (int i = 0; i < capacidad; i++) {
            cubetas[i] = new ArrayList<Neurotransmisor>();
        }
        tamano = 0;
    }

    public int getTamano() {
        return tamano;
    }
}

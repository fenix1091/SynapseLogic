public class HashTable { 
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

  private int IndiceHash(String clave) {
    int valor = 0;
    for (int i = 0; i < clave.length(); i++) {
    valor = (31 * valor + clave.charAt(i));
    }
    return Math.abs(valor) % capacidad;
  }
  public void insertarHash(Neurotransmisor n) {
    if (n == null || n.getId() == null) { 
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
        if (pActual.elemento.getId().equals(n.getId())) {
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

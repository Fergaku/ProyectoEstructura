import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.Comparator;
public class APrefijo <E> {
    private Map<Character, APrefijo> hijos; // mapa de java
    boolean finNombre;
    LinkedList<E> datos;
    NodoAPrefijo<E> raiz;

    public APrefijo() {
        this.raiz = new NodoAPrefijo<>(null);
        this.hijos = new HashMap<>();
        this.finNombre = false;
        this.datos = new LinkedList<>();
    }

    


    public void insertar(String palabra, E dato) {
        if (palabra == null || palabra.isEmpty()) return;

        NodoAPrefijo<E> actual = this.raiz;
        
        for (char c : palabra.toCharArray()) {
            actual.hijos.putIfAbsent(c, new NodoAPrefijo<E>(c));
            // Nos movemos al nodo hijo
            actual = (NodoAPrefijo<E>) actual.hijos.get(c);
        }
        
        actual.setFin(true); 
        actual.getLDatos().add(dato); 
    }

    public List<E> buscar(String palabra) {
        if (palabra == null || palabra.isEmpty()) return new LinkedList<>();

        NodoAPrefijo<E> actual = this.raiz; 
        
        for (char c : palabra.toCharArray()) {
            actual = (NodoAPrefijo<E>) actual.hijos.get(c);
            
            if (actual == null) return new LinkedList<>();
        }

        if (actual.getFin()) {
            return actual.getLDatos();
        }
        
        return new LinkedList<>();
    }


    public List<NodoAPrefijo<E>> buscarNodosPorPrefijo(String prefijo) {
        List<NodoAPrefijo<E>> resultados = new ArrayList<>();
        NodoAPrefijo<E> actual = raiz;

        prefijo = prefijo.toLowerCase();
        for (char c : prefijo.toCharArray()) {
            actual = actual.hijos.get(c);
            if (actual == null) return resultados; 
        }

        recolectarNodosRec(actual, resultados);
        return resultados;
    }

    public List<E> buscarxPrefijoHeap(String prefijo, Comparator<E> cmp) {
        List<NodoAPrefijo<E>> nodos = buscarNodosPorPrefijo(prefijo);

        // Heap con tamaño suficiente
        Heap<E> heap = new Heap<E>(1000, false, cmp);

        ArrayList<E> listaTemp = new ArrayList<>();

        for (NodoAPrefijo<E> nodo : nodos) {
            for (E dato : nodo.getLDatos()) {

                // 🔥 IMPORTANTE: aumentar frecuencia
                if (dato instanceof Frecuentable) {
                    ((Frecuentable) dato).incrementarFrecuencia();
                }

                listaTemp.add(dato);
            }
        }
        heap.construirHeap(listaTemp);

        // 3. Sacar ordenados
        List<E> resultado = new ArrayList<>();
        while (!heap.estaVacio()) {
            resultado.add(heap.desencolar());
        }
        return resultado;
    }

    private void recolectarNodosRec(NodoAPrefijo<E> nodo, List<NodoAPrefijo<E>> lista) {
        if (nodo.getFin()) lista.add(nodo);
        for (NodoAPrefijo<E> hijo : nodo.hijos.values()) {
            recolectarNodosRec(hijo, lista);
        }
    }

    public boolean eliminar(String palabra) {
    return eliminarRec(raiz, palabra, 0);
}

    private boolean eliminarRec(NodoAPrefijo<E> actual, String palabra, int index) {
        if (index == palabra.length()) {
            if (!actual.getFin()) return false;
            actual.setFin(false);
            actual.getLDatos().clear();
            return actual.hijos.isEmpty(); // Si no tiene hijos, avisar al padre que puede borrarlo
        }

        char ch = palabra.charAt(index);
        NodoAPrefijo<E> hijo = actual.hijos.get(ch);
        if (hijo == null) return false;

        boolean puedeBorrarHijo = eliminarRec(hijo, palabra, index + 1);

        if (puedeBorrarHijo) {
            actual.hijos.remove(ch);
            return actual.hijos.isEmpty() && !actual.getFin();
        }
        return false;
    }
}

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<E> {
    private ArrayList<E> datos;
    int max;
    int n;
    boolean ascendente;
    Comparator<E> cmp;
    public Heap(int max, boolean ascendente, Comparator<E> cmp) {
        this.max = max;
        this.ascendente = ascendente;
        datos = new ArrayList<E>(max);
        n = 0;
        this.cmp = cmp;
    }

    public int idxIzq(int r) {
        int valor = r*2 +1;
        if (valor >= n) return -1;
        return valor;
    }

    public int idxDer(int r) {
        int valor = r*2 +2;
        if (valor >= n) return -1;
        return valor;
    }

    public int idxRaiz(int hijo) {
        int valor = (hijo-1)/2;
        if (valor < 0) return -1;
        return valor;
    }

    public boolean esHoja(int i) {
        return (idxIzq(i) == -1 && idxDer(i) == -1);
    }

    private int mayor(int raiz, int izq, int der){
        if (cmp.compare(datos.get(raiz), datos.get(izq)) > 0 && cmp.compare(datos.get(raiz), datos.get(der)) > 0)
            return raiz;
        if (cmp.compare(datos.get(izq), datos.get(raiz)) > 0 && cmp.compare(datos.get(izq), datos.get(der)) > 0)
            return izq;
        return der;
    }
    private int menor(int raiz, int izq, int der){
        if (cmp.compare(datos.get(raiz), datos.get(izq)) < 0 && cmp.compare(datos.get(raiz), datos.get(der)) < 0)
            return raiz;
        if (cmp.compare(datos.get(izq), datos.get(raiz)) < 0 && cmp.compare(datos.get(izq), datos.get(der)) < 0)
            return izq;
        return der;
    }



    public void construirHeap(ArrayList<E> AMalo) {
        this.datos = new ArrayList<>(AMalo);
        this.n = AMalo.size();
        for (int i = n-1; i>=0; i--) {
            Ajustar(i);
        }
    }

    public String toString() {
        int i;
        String s = " ";
        for(i=0; i<datos.size(); i++)
            s = s + datos.get(i);
        return s;
    }
    
    private int revisar(int raiz, int izq, int der){
        if (izq == -1 && der == -1) return raiz;
        if (izq == -1) {
            if (ascendente)
                return cmp.compare(datos.get(raiz), datos.get(der)) <= 0 ? raiz : der;
            else
                return cmp.compare(datos.get(raiz), datos.get(der)) >= 0 ? raiz : der;
        }

        if (der == -1) {
            if (ascendente)
                return cmp.compare(datos.get(raiz), datos.get(izq)) <= 0 ? raiz : izq;
            else
                return cmp.compare(datos.get(raiz), datos.get(izq)) >= 0 ? raiz : izq;
        }

        if (ascendente) {
            return mayor(raiz, izq, der);
        } else {
            return menor(raiz, izq, der);
        }
    }

    private void intercambio(int i, int j) {
        E tmp;
        tmp = datos.get(i);
        datos.set(i, datos.get(j));
        datos.set(j, tmp);
    }

    public void Ajustar(int idanio) {
        if (esHoja(idanio)) return;
        int idx_mayor_o_menor = revisar(idanio, idxIzq(idanio), idxDer(idanio));
        if(idanio == idx_mayor_o_menor) return;
        intercambio(idanio, idx_mayor_o_menor);

        Ajustar(idx_mayor_o_menor);
    }

    public E desencolar() {
        if (n == 0) return null;
        E raizEliminada = datos.get(0);

        datos.set(0, datos.get(n-1));

        datos.remove(n-1);
        n--;

        if (n > 0) {
            Ajustar(0);
        }
        return raizEliminada;
    }

    public boolean estaVacio() {
        return n == 0;
    }
}

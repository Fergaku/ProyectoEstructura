import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NodoAPrefijo<E> {
    LinkedList<E> LDatos;
    boolean fin;
    Character ch;
    Map<Character, NodoAPrefijo<E>> hijos;

    public NodoAPrefijo(Character ch) {
        this.ch = ch;
        this.fin = false;
        LDatos = new LinkedList<>();
        hijos = new HashMap<Character, NodoAPrefijo<E>>();
    }

    // == Getters y Setters ==
    public LinkedList<E> getLDatos() {return LDatos;}
    public void setDatos(LinkedList<E> L) {LDatos = L;}
    public void setFin(boolean fin) {this.fin = fin;}
    public boolean getFin() {return this.fin;}
    public Character getCh() {return ch;}

    // ==== Metodos auxiliares ====
    public boolean tieneHijos() { return !hijos.isEmpty(); }
    public boolean esHoja() { return hijos.isEmpty(); }
    public int contarDatos() { return LDatos.size(); }
    public boolean tieneDatos() { return !LDatos.isEmpty(); }
    public NodoAPrefijo<E> getHijo(Character ch) { return hijos.get(ch); }
}

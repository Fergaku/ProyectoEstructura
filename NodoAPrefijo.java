import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NodoAPrefijo<E> {
    LinkedList<E> LDatos;
    boolean fin;
    Character ch;
    Map<Character, NodoAPrefijo> hijos;

    public NodoAPrefijo(Character ch) {
        this.ch = ch;
        this.fin = true;
        LDatos = new LinkedList<>();
        hijos = new HashMap<Character, NodoAPrefijo>();
    }
    public LinkedList<E> getLDatos() {return LDatos;}
    public void setDatos(LinkedList<E> L) {LDatos = L;}
    public void setFin(boolean fin) {this.fin = fin;}
    public boolean getFin() {return this.fin;}
    public Character getCh() {return ch;}

}

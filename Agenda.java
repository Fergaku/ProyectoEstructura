import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Agenda {
    private APrefijo<Contacto> indice;
    private Set<Contacto> contactos;

    public Agenda() {
        this.indice = new APrefijo<>();
        this.contactos = new HashSet<>();
    }

    // ====== FUNCIONES DE AGENDA ======
    public void addContacto(Contacto contacto) {
        if (contacto == null) return;

        if (contactos.contains(contacto)) {
            System.out.println("El contacto ya existe.");
            return;
        }

        contactos.add(contacto);

        indice.insertar(contacto.getNombre().toLowerCase(), contacto);
        indice.insertar(contacto.getApellido().toLowerCase(), contacto);
        indice.insertar(contacto.getApodo().toLowerCase(), contacto);
    }
    public List<Contacto> buscar(String prefijo) {
        if (prefijo == null || prefijo.isEmpty()) return new ArrayList<>();

        Comparator<Contacto> cmp = (a, b) -> {
        int freq = Integer.compare(b.getFrecuencia(), a.getFrecuencia());
        return freq != 0 ? freq : a.getNombre().compareToIgnoreCase(b.getNombre());
            };
        List<Contacto> resultados = indice.buscarxPrefijoHeap(prefijo.toLowerCase(), cmp);
        return resultados;
    }
    
    public boolean eliminarContacto(String clave) {
        List<Contacto> encontrados = buscar(clave);

        if (encontrados.isEmpty()) return false;

        Contacto c = encontrados.get(0);

        contactos.remove(c);

        indice.eliminar(c.getNombre().toLowerCase());
        indice.eliminar(c.getApellido().toLowerCase());
        indice.eliminar(c.getApodo().toLowerCase());

        return true;
    }

    public List<Contacto> obtenerTodos() {
        return new ArrayList<>(contactos);
    }

}

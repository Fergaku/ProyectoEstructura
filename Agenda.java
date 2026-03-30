import java.util.ArrayList;
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

    public void addContacto(Contacto contacto) {
        if (contacto == null) return;

        if (contactos.contains(contacto)) {
            System.out.println("El contacto ya existe.");
            return;
        }

        contactos.add(contacto);


        indice.insertar(contacto.getNombre(), contacto);
        indice.insertar(contacto.getApellido(), contacto);
        indice.insertar(contacto.getApodo(), contacto);
    }
    public List<Contacto> buscar(String prefijo) {
        List<Contacto> resultados = indice.buscarNodosPorPrefijo(prefijo.toLowerCase());

        for (Contacto c : resultados) {
            c.incrementarFrecuencia(); 
        }

        resultados.sort((a, b) -> b.getFrecuencia() - a.getFrecuencia());

        return resultados;
    }
    
    public boolean eliminarContacto(String clave) {
        List<Contacto> encontrados = buscar(clave);

        if (encontrados.isEmpty()) return false;

        Contacto c = encontrados.get(0);

        contactos.remove(c);

        indice.eliminar(c.getNombre(), c);
        indice.eliminar(c.getApellido(), c);
        indice.eliminar(c.getApodo(), c);

        return true;
    }

    public List<Contacto> obtenerTodos() {
        return new ArrayList<>(contactos);
    }

}

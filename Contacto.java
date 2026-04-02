public class Contacto implements Frecuentable {
    private String nombre;
    private String apellido;
    private String apodo;
    private String telefono;
    private int frecuencia;
    public Contacto(String nombre, String apellido, String apodo, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.telefono = telefono;
        this.frecuencia = 0;
    }
    // ===== GETTERS Y SETTERS =====
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getApodo() { return apodo; }
    public String getTelefono() { return telefono; }
    public int getFrecuencia() { return frecuencia; }

    public String toString() {
        return nombre + " " + apellido + " (" + apodo + ") - " + telefono;
    }

    // ===== FUNCIONES DE FRECUENCIA =====
    public void incrementarFrecuencia() {
        frecuencia++;
    }

    @Override
    public int obtenerFrecuencia() {
        return frecuencia;
    }

    // AGREGAR FUNCION EQUALS PARA COMPARAR CONTACTOS POR NOMBRE, APELLIDO O APODO
    @Override
    public boolean equals(Object contacto_dos) {
        if (this == contacto_dos) return true;
        if (contacto_dos == null || !(contacto_dos instanceof Contacto)) return false;
        
        Contacto otro = (Contacto) contacto_dos;
        
        return nombre.equalsIgnoreCase(otro.nombre) && 
               apellido.equalsIgnoreCase(otro.apellido);
    }

    @Override
    public int hashCode() {
        return (nombre.toLowerCase() + apellido.toLowerCase()).hashCode();
    }
}

/**
 * Interfaz para elementos que tienen un contador de frecuencia
 * Implementa esta interfaz en cualquier clase que necesite 
 * rastrear cuántas veces ha sido accedida o utilizada
 */
public interface Frecuentable {
    /**
     * Incrementa el contador de frecuencia en 1
     */
    void incrementarFrecuencia();
    
    /**
     * Obtiene la frecuencia actual
     * @return el número de veces que ha sido accedido
     */
    int obtenerFrecuencia();
}

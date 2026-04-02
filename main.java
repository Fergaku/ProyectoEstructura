import java.util.List;
import java.util.Scanner;

public class main {
    static Scanner sc = new Scanner(System.in);
    static Agenda agenda = new Agenda();
    public static void mostrarMenu() {
        System.out.println("\n===== AGENDA =====");
        System.out.println("1. Agregar contacto");
        System.out.println("2. Buscar por prefijo");
        System.out.println("3. Eliminar contacto");
        System.out.println("4. Mostrar todos");
        System.out.println("5. Salir");
    }

    public static int leerEntero(String mensaje) {
        int num;
        while (true) {
            try {
                System.out.print(mensaje);
                num = Integer.parseInt(sc.nextLine());
                return num;
            } catch (Exception e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        String input = sc.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("No puede estar vacío.");
            System.out.print(mensaje);
            input = sc.nextLine().trim();
        }
        return input;
    }

    private static void limpiarPantalla() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    private static void mostrarAdios() {
        limpiarPantalla();
        System.out.println("=================================");
        System.out.println("=  GRACIAS POR USAR EL SISTEMA  =");
        System.out.println("=================================");
        System.out.println();
    }

    /*
    Aca se insertan las funciones del sistema main (agregar, buscar, eliminar, mostrar todos los contactos)
    Las funciones se conectan con los metodos de los TDAs mencionados.
    */
    public static void agregarContacto() {
        limpiarPantalla();

        System.out.println("\n--- Nuevo Contacto ---");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Apodo: ");
        String apodo = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        Contacto c = new Contacto(nombre, apellido, apodo, telefono);

        agenda.addContacto(c);

        System.out.println("Contacto agregado correctamente.");
    }

    public static void buscarContacto() {
        String prefijo = leerTexto("Ingrese prefijo de busqueda: ");
        List<Contacto> resultados =  agenda.buscar(prefijo);

        if(resultados.isEmpty()) {
            System.out.println("No hay contactos con este prefijo.");
        } else {
            System.out.println("Hay " + resultados.size() + " contactos:");
            mostrarTablaContactos(resultados);
        }
        
    }

    public static void mostrarTablaContactos(List<Contacto> contactos) {
        System.out.println();
        
        for (int i = 0; i < contactos.size(); i++) {
            Contacto c = contactos.get(i);
            
            System.out.println((i + 1) + ". " + c.getNombre() + " " + c.getApellido());
            
            System.out.println("   Apodo: " + c.getApodo());
            System.out.println("   Teléfono: " + c.getTelefono());
            System.out.println("   Búsquedas: " + c.getFrecuencia());
            
            System.out.println("   " + "─────────────────────────");
        }
        
        System.out.println();
    }

    public static void eliminarContacto() {
        try {
            String prefijo = leerTexto("Ingrese el nombre del contacto que quiere eliminar: ");
            agenda.eliminarContacto(prefijo);
            System.out.println("El contacto " + prefijo + "ha sido eliminado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Debe de ingresar un nombre.");
        }

    }

    public static void mostrarContactos() {
        limpiarPantalla();
        List<Contacto> LContactos = agenda.obtenerTodos();


    // Validar si hay contactos
    if (LContactos.isEmpty()) {
        System.out.println("No hay contactos registrados.");
    } else {
        // Ordenar alfabéticamente por nombre
        LContactos.sort((a, b) -> a.getNombre().compareToIgnoreCase(b.getNombre()));
        
        // Mostrar cantidad total
        System.out.println("Total: " + LContactos.size() + " contacto(s)");
        
        // Mostrar la tabla
        mostrarTablaContactos(LContactos);
    }
    }


    public static void main(String[] args) {
        try {

            int opcion;
    
            do {
                mostrarMenu();
                opcion = leerEntero("Seleccione una opción: ");
    
                switch (opcion) {
                    case 1:
                        agregarContacto();
                        break;
                    case 2:
                        buscarContacto();
                        break;
                    case 3:
                        eliminarContacto();
                        break;
                    case 4:
                        mostrarContactos();
                        break;
                    case 5:
                        mostrarAdios();
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
    
            } while (opcion != 5);
        } catch (Exception e) {
            System.out.println("Hubo un error en el sistema. ");
        }
        
    }
}

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

    public static void agregarContacto() {
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

    // TODO: Implementar buscarContacto(), eliminarContacto() y mostrarContactos()
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
                        //buscarContacto();
                        break;
                    case 3:
                        //eliminarContacto();
                        break;
                    case 4:
                        //mostrarContactos();
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
    
            } while (opcion != 5);
        } catch (Exception e) {
            System.out.println("ERROR! Sorry!");
        }
        
    }
}

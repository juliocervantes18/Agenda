import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String, String> directorio = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String fileName = "test.txt";
        cargarcontactos(fileName);
        menu(fileName);
    }

    public static void cargarcontactos(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] rec = line.split(",", 0);
            if (rec.length == 2) {
                directorio.put(rec[0], rec[1]);
            }
        }
        br.close();
        fr.close();
    }

    public static void guardarcontactos(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String key : directorio.keySet()) {
            String value = directorio.get(key);
            bw.write(key + "," + value);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        fw.close();
    }

    public static void listacontactos() {
        System.out.println("Contactos:");
        for (String key : directorio.keySet()) {
            System.out.println(key + " : " + directorio.get(key));
        }
    }

    public static void crearcontacto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su número teléfonico: ");
        String numero = scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        directorio.put(numero, nombre);
        System.out.println("Se agrego con exito el contacto.");
    }

    public static void eliminarcontacto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de teléfono que quiere eliminar: ");
        String numero = scanner.nextLine();
        if (directorio.remove(numero) != null) {
            System.out.println("Contacto eliminado exitosamente.");
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    public static void menu(String fileName) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Menú de la Agenda telefónica ---");
            System.out.println("1. Listar de contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Guardar y salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    listacontactos();
                    break;
                case 2:
                    crearcontacto();
                    guardarcontactos(fileName);
                    break;
                case 3:
                    eliminarcontacto();
                    guardarcontactos(fileName);
                    break;
                case 4:
                    guardarcontactos(fileName);
                    System.out.println("Contactos guardados. Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }
        }
    }
}

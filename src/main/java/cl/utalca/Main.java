package cl.utalca;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static InventarioLetras inventarioA = null;
    static InventarioLetras inventarioB = null;

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("|  Bienvenido al programa InventarioLetras |");
        System.out.println("============================================");

        int opcion = -1;

        while (opcion != 0) {
            mostrarMenu();
            opcion = leerEntero("Ingresa tu opción: ");

            switch (opcion) {
                case 1: crearInventario();
                    break;
                case 2: mostrarInventario();
                    break;
                case 3: consultarLetra();
                    break;
                case 4: modificarLetra();
                    break;
                case 5: encriptarPalabra();
                    break;
                case 6: desencriptarPalabra();
                    break;
                case 7: sumarInventarios();
                    break;
                case 8: restarInventarios();
                    break;
                case 9: amplificarInventario();
                    break;
                case 0: System.out.println("\n¡Hasta luego!");
                    break;
                default: System.out.println("\n[!] Opción no válida. Intenta de nuevo.");
            }
        }
    }
    static void mostrarMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("|  MENÚ PRINCIPAL                            |");
        System.out.println("----------------------------------------------");
        System.out.println("|  [1] Crear inventario A o B desde un texto |");
        System.out.println("|  [2] Ver inventario A o B                  |");
        System.out.println("|  [3] Consultar conteo de una letra         |");
        System.out.println("|  [4] Modificar conteo de una letra         |");
        System.out.println("|  [5] Encriptar una palabra (César)         |");
        System.out.println("|  [6] Desencriptar una palabra (César)      |");
        System.out.println("|  [7] Sumar inventarios A + B               |");
        System.out.println("|  [8] Restar inventarios A - B              |");
        System.out.println("|  [9] Amplificar (multiplicar) un inventario|");
        System.out.println("|  [0] Salir                                 |");
        System.out.println("----------------------------------------------");
    }

    static void crearInventario() {
        System.out.println("\n--- Crear Inventario ---");
        char cual = elegirInventario();

        System.out.print("Ingresa el texto para el inventario " + cual + ": ");
        String texto = scanner.nextLine();

        InventarioLetras nuevo = new InventarioLetras(texto);

        if (cual == 'A') {
            inventarioA = nuevo;
        } else {
            inventarioB = nuevo;
        }

        System.out.println("\n[OK] Inventario " + cual + " creado: " + nuevo);
        System.out.println("     Total letras: " + nuevo.size());
        System.out.println("     ¿Vacío? " + nuevo.isEmpty());
    }

    static void mostrarInventario() {
        System.out.println("\n--- Ver Inventario ---");
        char cual = elegirInventario();
        InventarioLetras inv = obtenerInventario(cual);

        if (inv == null) {
            System.out.println("[!] El inventario " + cual + " no ha sido creado todavía.");
            return;
        }

        System.out.println("\nInventario " + cual + ": " + inv);
        System.out.println("Total de letras (size): " + inv.size());
        System.out.println("¿Está vacío? (isEmpty): " + inv.isEmpty());
    }

    static void consultarLetra() {
        System.out.println("\n--- Consultar Letra ---");
        char cual = elegirInventario();
        InventarioLetras inv = obtenerInventario(cual);

        if (inv == null) {
            System.out.println("[!] El inventario " + cual + " no ha sido creado todavía.");
            return;
        }

        System.out.print("Ingresa la letra a consultar: ");
        String entrada = scanner.nextLine().trim();

        if (entrada.length() == 0) {
            System.out.println("[!] No ingresaste ningún carácter.");
            return;
        }

        char letra = entrada.charAt(0);

        try {
            int conteo = inv.get(letra);
            System.out.println("\nLa letra '" + letra + "' aparece " + conteo + " vez/veces en el inventario " + cual + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
    }

    static void modificarLetra() {
        System.out.println("\n--- Modificar Letra ---");
        char cual = elegirInventario();
        InventarioLetras inv = obtenerInventario(cual);

        if (inv == null) {
            System.out.println("[!] El inventario " + cual + " no ha sido creado todavía.");
            return;
        }

        System.out.print("Ingresa la letra a modificar: ");
        String entrada = scanner.nextLine().trim();

        if (entrada.length() == 0) {
            System.out.println("[!] No ingresaste ningún carácter.");
            return;
        }

        char letra = entrada.charAt(0);
        int valor = leerEntero("Ingresa el nuevo conteo (>= 0): ");

        try {
            inv.set(letra, valor);
            System.out.println("\n[OK] Conteo de '" + letra + "' actualizado a " + valor + ".");
            System.out.println("Inventario " + cual + " ahora: " + inv);
        } catch (IllegalArgumentException e) {
            System.out.println("[!] Error: " + e.getMessage());
        }
    }

    static void encriptarPalabra() {
        System.out.println("\n--- Encriptar Palabra (César) ---");

        // Necesitamos un inventario
        InventarioLetras inv = inventarioA;

        if (inv == null) {
            // Creamos uno temporal vacío
            inv = new InventarioLetras("");
        }

        System.out.print("Ingresa la palabra a encriptar: ");
        String palabra = scanner.nextLine().trim();

        String encriptada = inv.encriptarPalabra(palabra, 3);
        System.out.println("\nPalabra original:   " + palabra);
        System.out.println("Palabra encriptada: " + encriptada);
    }

    static void desencriptarPalabra() {
        System.out.println("\n--- Desencriptar Palabra (César) ---");

        InventarioLetras inv = inventarioA;

        if (inv == null) {
            inv = new InventarioLetras("");
        }

        System.out.print("Ingresa la palabra a desencriptar: ");
        String palabra = scanner.nextLine().trim();

        String desencriptada = inv.desencriptarPalabra(palabra, 3);
        System.out.println("\nPalabra encriptada:    " + palabra);
        System.out.println("Palabra desencriptada: " + desencriptada);
    }

    static void sumarInventarios() {
        System.out.println("\n--- Sumar Inventarios A + B ---");

        if (inventarioA == null || inventarioB == null) {
            System.out.println("[!] Debes crear ambos inventarios (A y B) antes de sumar.");
            return;
        }

        InventarioLetras suma = inventarioA.add(inventarioB);

        System.out.println("\nInventario A: " + inventarioA);
        System.out.println("Inventario B: " + inventarioB);
        System.out.println("Suma A + B:   " + suma);
        System.out.println("Total letras en la suma: " + suma.size());
    }

    static void restarInventarios() {
        System.out.println("\n--- Restar Inventarios A - B ---");

        if (inventarioA == null || inventarioB == null) {
            System.out.println("[!] Debes crear ambos inventarios (A y B) antes de restar.");
            return;
        }

        InventarioLetras resta = inventarioA.subtract(inventarioB);

        System.out.println("\nInventario A: " + inventarioA);
        System.out.println("Inventario B: " + inventarioB);

        if (resta == null) {
            System.out.println("Resta A - B:  [No es posible, alguna letra tendría conteo negativo]");
        } else {
            System.out.println("Resta A - B:  " + resta);
            System.out.println("Total letras en la resta: " + resta.size());
        }
    }

    static void amplificarInventario() {
        System.out.println("\n--- Amplificar Inventario ---");
        char cual = elegirInventario();
        InventarioLetras inv = obtenerInventario(cual);

        if (inv == null) {
            System.out.println("[!] El inventario " + cual + " no ha sido creado todavía.");
            return;
        }

        int n = leerEntero("Ingresa el factor de multiplicación (n): ");

        InventarioLetras amplificado = inv.amplifies(n);

        System.out.println("\nInventario " + cual + " original:   " + inv);
        System.out.println("Inventario amplificado x" + n + ": " + amplificado);
        System.out.println("Total letras después: " + amplificado.size());
    }

    static char elegirInventario() {
        while (true) {
            System.out.print("¿Inventario A o B? ");
            String entrada = scanner.nextLine().trim().toUpperCase();

            if (entrada.equals("A") || entrada.equals("B")) {
                return entrada.charAt(0);
            }

            System.out.println("[!] Opción no válida. Escribe A o B.");
        }
    }

    static InventarioLetras obtenerInventario(char cual) {
        if (cual == 'A') {
            return inventarioA;
        } else {
            return inventarioB;
        }
    }

    static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("[!] Por favor ingresa un número entero válido.");
            }
        }
    }
}
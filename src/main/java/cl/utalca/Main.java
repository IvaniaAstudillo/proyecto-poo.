package cl.utalca;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static InventarioLetras in1 = null;
    static InventarioLetras in2 = null;

    public static void main(String[] args) {
        int opc;
        do {
            System.out.print("\n-----------------------------");
            System.out.print("|    INVENTARIO DE LETRAS    |");
            System.out.print("| 1. Crear inventario 1      |");
            System.out.print("| 2. Crear inventario 2      |");
            System.out.print("| 3. Ver inventario          |");
            System.out.print("| 4. Obtener conteo          |");
            System.out.print("| 5. Sumar inventario        |");
            System.out.print("| 6. Restar inventario       |");
            System.out.print("| 7. Amplificar inventario   |");
            System.out.print("| 8. SALIR                   |");
            System.out.print("------------------------------");
            System.out.print("Escoger una opción");
            opc = sc.nextInt();
            sc.nextLine(); //limpia

            switch (opc){
                case 1: crearInventario(1);
                case 2: crearInventario(2);
                case 3: verInventario();
                case 4: getLetra();
                case 5: sumarInventarios();
                case 6: restarInventario();
                case 7: ampliarInventario();
                case 8: System.out.println("\n BYE BYEEEE :D");
                default: System.out.println("\n Opción no válida :( ");
            }
        }while (opc != 0);
    }

    static void crearInventario(int numero) {
        System.out.print("\n Ingresar texto" + numero + ": ");
        String texto = sc.nextLine();
        if (numero == 1)
            in1 = new InventarioLetras(texto);
        else
            in2 = new InventarioLetras(texto);
        System.out.println("Inventario" + numero + ": " + "creado: " + (numero == 1 ? in1 : in2));
    }
}
    static void verInventario(){
        if (!validarInventario(1))return;
        System.out.println("\n Inventario 1 : " + in1);
        System.out.println(" Tamaño : " + in1.size());
        System.out.println(" ¿Vacio?: " + in1.isEmpty());
        if (in2 != null) {
            System.out,println("\n Inventario 2 : " + in2);
            System.out.print("Tamaño : " + in2.size());
            System.out.print(" ¿Vacio?: " + in2.isEmpty());
        }
    }
    static void getLetra(){
        if (!validarInventario(1))return;
        System.out.print("\n Ingresar letra: ");
        char letra = sc.nextLine().charAt(0);
        try {
            System.out.println(" Conteo '" + letra + "':" + inv1.get(letra));
        }catch (IllegalArgumentException e){
            System.out.println(" ERROR: " + e.getMessage());
        }
    }

    static void sumarInventarios(){
        if (!validarInventario(1) || !validarInventario(2))return;
        InventarioLetras suma = in1.add(in2);
        System.out.prinln("\n Inventario 1: " + in1);
        System.out.println("Inventario 2: " + in2);
        System.out.println(" RESULTADO SUMA: " + suma);
    }

    static void restarInventario(){
        if(!validarInventario(1) || !validarInventario(2))return;
        InventarioLetras resta = in1.subtract(in2);
        System.out.println("\n Inventario 1: " + in1);
        System.out.println(" Inventario 2: " in2);
        if (resta == null)
            System.out.println("RESULTADO NEGATIVO");
        else
            System.out.println("RESULTADO RESTA: " + resta);
    }

    static void ampliarInventario(){
        if (!validarInventario(1))return;
        System.out.print("\n Ingresar amplificación: ");
        int n = sc.nextInt();
        sc.nextLine();
        InventarioLetras amp = in1.amplifies(n);
        System.out.println(" Original : " + in1);
        System.out.println(" Resultado (x" + n + "): "+ amp);
    }

    static boolean validarInventario(int numero){
        if (numero == 1 && in1 == null){
            System.out.println("\n Crea el Inventario 1");
            return false;
        }
        if (numero == 2 && in2 == null){
            System.out.println("\n  Crea el Inventario 2");
            return false;
        }
            return true;
    }
}
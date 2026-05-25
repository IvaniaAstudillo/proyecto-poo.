package cl.utalca.;

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
            }
        }
    }
    static void crearInventario(int numero){
        System.out.print("\n Ingresar texto" + numero + ": ");
        String texto = sc.nextLine();
        if (numero == 1)
            in1 = new InventarioLetras(texto);
        else
            in2 = new InventarioLetras(texto);
        System.out.println("Inventario" + numero + ": " + "creado: " + (numero == 1 ? in1 : in2));
    }






}
package cl.utalca;

public class InventarioLetras {
    private int[] inventario; //Arreglo de 26 espacios
    private int totalCount;
    private int nonZeroCount;

    //Constructor
    public InventarioLetras(String data) {
        inventario = new int[26];
        totalCount = 0;
        nonZeroCount = 0;

        String texto = data.toLowerCase();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (c >= 'a' && c <= 'z'){
                int indice = c - 'a';
                if (inventario[indice] == 0) {
                    nonZeroCount++;
                }
                inventario[indice]++;
                totalCount++;
            }
        }
    }

    private InventarioLetras() {
        inventario = new int[26];
        totalCount = 0;
        nonZeroCount = 0;
    }

    public char encriptarCesar(char letra){
        char c = Character.toLowerCase(letra);
        if (c >= 'a' && c <= 'z'){
            return (char)('a' + (c - 'a' + 3) % 26);
        }
        return letra;
    }
    public char desencriptarCesar(char letra){
        char c = Character.toLowerCase(letra);
        if (c >= 'a' && c <= 'z'){
            return (char)('a' + (c - 'a' +26 - 3) % 26);
        }
        return letra;
    }
    public String encriptarPalabra(String palabra, int desplazamiento){
        String resultado = "";
        for (int i = 0; i < palabra.length(); i++) {
            resultado += encriptarCesar(palabra.charAt(i));
        }
        return resultado;
    }
    public String desencriptarPalabra(String palabra, int desplazamiento){
        String resultado = "";
        for (int i = 0; i < palabra.length(); i++) {
            resultado += desencriptarCesar(palabra.charAt(i));
        }
        return resultado;
    }

    //Metodos
    public int get(char letra){
        char c = Character.toLowerCase(letra);
        if (c < 'a' || c > 'z') {
            throw new IllegalArgumentException("Letra no valida:" + letra);
        }
        return inventario[c - 'a'];
    }

    public void set(char letra, int valor){
        char c = Character.toLowerCase(letra);
        if (c < 'a' || c > 'z') {
            throw new IllegalArgumentException("Letra no valida:'" + letra + "'debe ser del alfabeto ingles.");
        }
        if (valor <0) {
            throw new IllegalArgumentException("Valor no puede ser negativo:" + valor);
        }
        int indice = c - 'a';
        int anterior = inventario[indice];

        totalCount = totalCount - anterior + valor;
        if (anterior == 0 && valor > 0) {
            nonZeroCount++;
        }else if (anterior > 0 && valor == 0) {
            nonZeroCount--;
        }

        inventario[indice] = valor;
    }
    public int size(){
        return totalCount;
    }
    public boolean isEmpty(){
        return nonZeroCount == 0;
    }

    public String toString() {
        String resultado = "[";
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < inventario[i]; j++) {
                resultado += (char) ('a' + i);
            }
        }
        resultado += "]";
        return resultado;
    }

    public InventarioLetras add(InventarioLetras otro){
        InventarioLetras nuevo = new InventarioLetras();
        for (int i = 0; i < 26; i++) {
            int suma = this.inventario[i] + otro.inventario[i];
            if (suma < 0){
                nuevo.inventario[i] = 0;
                nuevo.totalCount+= suma;
                nuevo.nonZeroCount++;
            }
        }
        return nuevo;
    }
    public InventarioLetras amplifies(int n){
        InventarioLetras nuevo = new InventarioLetras();
        for (int i = 0; i < 26; i++) {
            int multiplicado = this.inventario[i] * n;
            if (multiplicado > 0){
                nuevo.inventario[i] = multiplicado;
                nuevo.totalCount+= multiplicado;
                nuevo.nonZeroCount++;
            }
        }
        return nuevo;
    }
    public InventarioLetras subtract(InventarioLetras otro){
        InventarioLetras nuevo = new InventarioLetras();
        for (int i = 0; i < 26; i++) {
            int resta = this.inventario[i] - otro.inventario[i];
            if (resta < 0){
                return null;
            }
            if (resta > 0){
                nuevo.inventario[i] = resta;
                nuevo.totalCount+= resta;
                nuevo.nonZeroCount++;
            }
        }
        return nuevo;
    }
}

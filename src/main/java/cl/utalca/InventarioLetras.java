package cl.utalca;

public class InventarioLetras {
    private int[] inventario;
    private int totalCount;
    private int nonZeroCount;

    public InventarioLetras(String data) {
        inventario = new int[26];
        totalCount = 0;
        nonZeroCount = 0;

        for (char c : data.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z'){
                if (inventario[c - 'a'] == 0) nonZeroCount++;
                inventario[c - 'a']++;
                totalCount++;
            }
        }
    }
    public int get(char letra){
        letra= Character.toLowerCase(letra);
        if (letra < 'a' || letra > 'z')
            throw new IllegalArgumentException("Letra no valida:" + letra);
        return inventario[letra - 'a'];
    }
    public void set(char letra, int valor){
        letra= Character.toLowerCase(letra);
        if (letra < 'a' || letra > 'z' || valor <0)
            throw new IllegalArgumentException("Letra no valida:");

        int idx = letra - 'a';
        int anterior = inventario[idx];

        totalCount += valor - anterior;
        if (anterior == 0 && valor > 0) nonZeroCount++;
        if (anterior > 0 && valor == 0) nonZeroCount--;

        inventario[idx] = valor;
    }
    public int size(){ return totalCount;}
    public boolean isEmpty(){ return nonZeroCount == 0;}

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < 26; i++)
            for (int j = 0; j < inventario[i]; j++)
                sb.append((char)('a' + i));
        return sb.append("]").toString();
    }
    public char encriptarCesar(char letra){
        letra= Character.toLowerCase(letra);
        if (letra < 'a' || letra > 'z') return letra;
        return (char)('a' + (letra - 'a' + 3) % 26);
    }
    public char desencriptarCesar(char letra){
        letra= Character.toLowerCase(letra);
        if (letra < 'a' || letra > 'z') return letra;
        return (char)('a' + (letra - 'a' - 3 + 26) % 26);
    }
    public String encriptarPalabra(String palabra, int desplazamiento){
        StringBuilder sb = new StringBuilder();
        for (char c : palabra.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z')
                sb.append((char)('a' + (c - 'a' + desplazamiento) % 26));
            else
                sb.append(c);
        }
        return sb.toString();
    }

    public String desencriptarPalabra(String palabra, int desplazamiento){
        StringBuilder sb = new StringBuilder();
        for (char c : palabra.toLowerCase().toCharArray()) {
        if (c >= 'a' && c <= 'z')
            sb.append((char)('a' + (c - 'a' - desplazamiento + 26) % 26));
        else
            sb.append(c);
        }
        return sb.toString();
    }
    public InventarioLetras add(InventarioLetras otro){
        InventarioLetras resultado = new InventarioLetras("");
        for (int i = 0; i < 26; i++)
            resultado.set((char)('a' + i), this.inventario[i] + otro.inventario[i]);
        return resultado;

    }
    public InventarioLetras subtract(InventarioLetras otro){
        InventarioLetras resultado = new InventarioLetras("");
        for (int i = 0; i < 26; i++) {
            int diff = this.inventario[i] - otro.inventario[i];
            if (diff < 0 ) return null;
            resultado.set((char)('a' + i), diff);
        }
        return resultado;
    }

    public InventarioLetras amplifies(int n){
        InventarioLetras resultado = new InventarioLetras("");
        for (int i = 0; i < 26; i++)
            resultado.set((char)('a' + i), this.inventario[i] * n);
        return resultado;
    }
}
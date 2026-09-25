public class punto11 {
    public static void main(String[] args) {
        int[] N = {12, 0, 31, 4, 2, 65, 67, -5, -10, -12, -13, -8};

        int neg = 0, cer = 0, pos = 0;
        for (int i = 0; i < N.length; i++) {
            if (N[i] < 0) neg++;
            else if (N[i] == 0) cer++;
            else pos++;
        }

        int[] negativos = new int[neg];
        int[] ceros     = new int[cer];
        int[] positivos = new int[pos];

        neg = 0; cer = 0; pos = 0;
        for (int i = 0; i < N.length; i++) {
            if (N[i] < 0) negativos[neg++] = N[i];
            else if (N[i] == 0) ceros[cer++] = N[i];
            else positivos[pos++] = N[i];
        }

        System.out.print("Negativos: ");
        for (int i = 0; i < negativos.length; i++) System.out.print(negativos[i] + "");
        System.out.println();

        System.out.print("Cero: ");
        for (int i = 0; i < ceros.length; i++) System.out.print(ceros[i] + " ");
        System.out.println();

        System.out.print("Positivos: ");
        for (int i = 0; i < positivos.length; i++) System.out.print(positivos[i] + " ");
        System.out.println();
    }
}

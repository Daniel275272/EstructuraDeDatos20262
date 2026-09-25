public class OperacionesMatrices2 {

   public int[][] llenarMatriz(int f, int c, int min, int max) {
        int[][] m = new int[f][c];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = (int)(Math.random() * (max - min + 1) + min);
            }
        }
        return m;
    }

    public String imprimirMatriz(int[][] m) {
        String cad = "";
        for (int i = 0; i < m.length; i++) {
            cad += "|";
            for (int j = 0; j < m[0].length; j++) {
                cad += m[i][j] + "|";
            }
            cad += "\n";
        }
        return cad;
    }

    //21
    public int[] sumaParesFilas(int[][] m) {
        int[] b = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            int suma = 0;
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] % 2 == 0) {
                    suma += m[i][j];
                }
            }
            b[i] = suma;
        }
        return b;
    }

    // 22
    public String mayorMenorMatriz(int[][] m) {
        int mayor = m[0][0], menor = m[0][0];
        int filaMayor = 0, colMayor = 0;
        int filaMenor = 0, colMenor = 0;

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] > mayor) {
                    mayor = m[i][j];
                    filaMayor = i;
                    colMayor = j;
                }
                if (m[i][j] < menor) {
                    menor = m[i][j];
                    filaMenor = i;
                    colMenor = j;
                }
            }
        }

        return "Mayor: " + mayor + " en (" + filaMayor + "," + colMayor + ")\n" +
               "Menor: " + menor + " en (" + filaMenor + "," + colMenor + ")";
    }

    //23
    public int[][] mayorAbsolutoFilas(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            int mayorAbs = Math.abs(m[i][0]);
            for (int j = 1; j < m[0].length - 1; j++) {
                if (Math.abs(m[i][j]) > mayorAbs) {
                    mayorAbs = Math.abs(m[i][j]);
                }
            }
            m[i][m[0].length - 1] = mayorAbs;
        }
        return m;
    }
    
}

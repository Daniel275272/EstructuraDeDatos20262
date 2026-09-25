public class OperacionesMatrices3 {
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

    // 24 
    public double[] produccionMensual() {
        double[] produccion = new double[12];
        for (int i = 0; i < produccion.length; i++) {
            double arroz  = Math.random() * 100;
            double avena  = Math.random() * 100;
            double cebada = Math.random() * 100;
            double trigo  = Math.random() * 100;
            produccion[i] = arroz + avena + cebada + trigo;
        }
        return produccion;
    }

    public String analizarProduccion(double[] produccion) {
        double suma = 0;
        for (int i = 0; i < produccion.length; i++) {
            suma += produccion[i];
        }
        double promedio = suma / produccion.length;

        int superiores = 0, inferiores = 0;
        double mayor = produccion[0];
        int mesMayor = 0;
        for (int i = 0; i < produccion.length; i++) {
            if (produccion[i] > promedio) superiores++;
            if (produccion[i] < promedio) inferiores++;
            if (produccion[i] > mayor) {
                mayor = produccion[i];
                mesMayor = i;
            }
        }

        String[] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio",
                          "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

        return "Promedio anual: " + promedio + "\n" +
               "Meses superiores al promedio: " + superiores + "\n" +
               "Meses inferiores al promedio: " + inferiores + "\n" +
               "Mes con mayor produccion: " + meses[mesMayor] + " (" + mayor + " ton)";
    }

    //25 
    public String plantaMayorSemanal(int[][] m) {
        int mayor = 0, plantaMayor = 0;
        for (int i = 0; i < m.length; i++) {
            int suma = 0;
            for (int j = 0; j < m[0].length; j++) {
                suma += m[i][j];
            }
            if (suma > mayor) {
                mayor = suma;
                plantaMayor = i + 1;
            }
        }
        return "Planta con mayor produccion semanal: " + plantaMayor + " (" + mayor + " unidades)";
    }

    public String plantaMayorDia(int[][] m, int dia) {
        int mayor = 0, plantaMayor = 0;
        for (int i = 0; i < m.length; i++) {
            if (m[i][dia - 1] > mayor) {
                mayor = m[i][dia - 1];
                plantaMayor = i + 1;
            }
        }
        return "Planta con mayor produccion el dia " + dia + ": " + plantaMayor + " (" + mayor + " unidades)";
    }

    // 26
    public double[] preciosModelos(int cantidad) {
        double[] precios = new double[cantidad];
        for (int i = 0; i < precios.length; i++) {
            precios[i] = (Math.random() * 10 + 5);
        }
        return precios;
    }

    public String totalPorModelo(int[][] ventas, double[] precios) {
        String cad = "";
        double mayor = 0;
        int modeloMayor = 0;
        for (int i = 0; i < ventas.length; i++) {
            double total = 0;
            for (int j = 0; j < ventas[0].length; j++) {
                total += ventas[i][j] * precios[i];
            }
            cad += "Modelo " + (char)('A' + i) + ": $" + total + "\n";
            if (total > mayor) {
                mayor = total;
                modeloMayor = i;
            }
        }
        cad += "Modelo que mas recaudo: " + (char)('A' + modeloMayor) + " ($" + mayor + ")";
        return cad;
    }

    public String totalPorDia(int[][] ventas, double[] precios) {
        String cad = "";
        for (int j = 0; j < ventas[0].length; j++) {
            double total = 0;
            for (int i = 0; i < ventas.length; i++) {
                total += ventas[i][j] * precios[i];
            }
            cad += "Dia " + (j + 1) + ": $" + total + "\n";
        }
        return cad;
    }
}

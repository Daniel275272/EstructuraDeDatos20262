package semana5;

public class Ejecutar {
    public static void main(String[] args) {

        
        Estudiantes[] estudiantes = new Estudiantes[10];

        // ---- Registro de 5 estudiantes ----
        estudiantes[0] = new Estudiantes(101, "Ana",    "Ingeniería", 4.5);
        estudiantes[1] = new Estudiantes(102, "Luis",   "Medicina",   3.8);
        estudiantes[2] = new Estudiantes(103, "Sofía",  "Derecho",    4.0);
        estudiantes[3] = new Estudiantes(104, "Carlos", "Sistemas",   2.9);
        estudiantes[4] = new Estudiantes(105, "Marta",  "Artes",      4.8);

        int cantidad = 5;

        
        System.out.println("LISTA DE ESTUDIANTES REGISTRADOS");
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ". " + estudiantes[i]);
        }

        
        System.out.println("\nBUSCAR CÓDIGO 104");
        boolean encontrado = false;
        for (int i = 0; i < cantidad; i++) {
            if (estudiantes[i].getCodigo() == 104) {
                System.out.println("Encontrado: " + estudiantes[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró estudiante con ese código.");
        }

        
        System.out.println("\nESTUDIANTES CON PROMEDIO >= 4.0 ");
        boolean hay = false;
        for (int i = 0; i < cantidad; i++) {
            if (estudiantes[i].getPromedio() >= 4.0) {
                System.out.println(estudiantes[i]);
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("Ningún estudiante con promedio >= 4.0.");
        }

        
        System.out.println("\n ESTUDIANTE CON PROMEDIO MÁS ALTO ");
        Estudiantes mejor = estudiantes[0];
        for (int i = 1; i < cantidad; i++) {
            if (estudiantes[i].tieneMejorPromedioQue(mejor)) {
                mejor = estudiantes[i];
            }
        }
        System.out.println(mejor);
    }
}


package Momento;

import java.util.LinkedList;

public class Estudiante extends Persona {

    private int semestre;
    private Double[][] notas = new Double[10][20];
    private LinkedList<Materia> historialMaterias = new LinkedList<>();

    public Estudiante(String nombre, String id, String email, int semestre) {
        super(nombre, id, email);
        setSemestre(semestre);
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        if (semestre < 0 || semestre >= 10) {
            throw new IllegalArgumentException("Semestre invalido.");
        }
        this.semestre = semestre;
    }

    // REGISTRAR NOTAS
    public void registrarNota(int semestre, int posicionMateria, Double nota) {
        if (semestre < 0 || semestre >= 10 ||
            posicionMateria < 0 || posicionMateria >= 20 ||
            nota == null || nota < 0 || nota > 5) {
            throw new IllegalArgumentException("Datos de nota invalidos.");
        }

        notas[semestre][posicionMateria] = nota;
    }

    // CONSULTAR NOTAS
    public Double consultarNota(int semestre, int posicionMateria) {
        if (semestre < 0 || semestre >= 10 ||
            posicionMateria < 0 || posicionMateria >= 20) {
            return null;
        }

        return notas[semestre][posicionMateria];
    }

    public Double[][] getNotas() {
        return notas;
    }

    // CALCULAR PROMEDIO DE UN SEMESTRE
    public Double calcularPromedioSemestral(int semestre) {
        if (semestre < 0 || semestre >= 10) {
            return null;
        }

        double suma = 0;
        int cantidad = 0;

        for (int i = 0; i < 20; i++) {
            if (notas[semestre][i] != null) {
                suma += notas[semestre][i];
                cantidad++;
            }
        }

        if (cantidad == 0) {
            return null;
        }

        return suma / cantidad;
    }

    // CALCULAR PROMEDIO ACUMULADO
    public Double calcularPromedioAcumulado() {
        double suma = 0;
        int cantidad = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (notas[i][j] != null) {
                    suma += notas[i][j];
                    cantidad++;
                }
            }
        }

        if (cantidad == 0) {
            return null;
        }

        return suma / cantidad;
    }

    // CONTAR MATERIAS APROBADAS EN UN SEMESTRE
    public int contarMateriasAprobadas(int semestre) {
        if (semestre < 0 || semestre >= 10) {
            return 0;
        }

        int cantidad = 0;

        for (int i = 0; i < 20; i++) {
            if (notas[semestre][i] != null &&
                notas[semestre][i] >= 3.0) {
                cantidad++;
            }
        }

        return cantidad;
    }

    // CONTAR MATERIAS REPROBADAS EN UN SEMESTRE
    public int contarMateriasReprobadas(int semestre) {
        if (semestre < 0 || semestre >= 10) {
            return 0;
        }

        int cantidad = 0;

        for (int i = 0; i < 20; i++) {
            if (notas[semestre][i] != null &&
                notas[semestre][i] < 3.0) {
                cantidad++;
            }
        }

        return cantidad;
    }

    // CONTAR MATERIAS APROBADAS ACUMULADAS
    public int contarMateriasAprobadasAcumuladas() {
        int cantidad = 0;

        for (int i = 0; i < 10; i++) {
            cantidad += contarMateriasAprobadas(i);
        }

        return cantidad;
    }

    // CONTAR MATERIAS REPROBADAS ACUMULADAS
    public int contarMateriasReprobadasAcumuladas() {
        int cantidad = 0;

        for (int i = 0; i < 10; i++) {
            cantidad += contarMateriasReprobadas(i);
        }

        return cantidad;
    }

    // HISTORIAL DE MATERIAS
    public void agregarMateriaHistorial(Materia materia) {
        if (materia != null && !historialMaterias.contains(materia)) {
            historialMaterias.add(materia);
        }
    }

    public boolean tieneAprobada(Materia materia) {
        return historialMaterias.contains(materia);
    }

    public LinkedList<Materia> getHistorialMaterias() {
        return historialMaterias;
    }

    // INFORMACION DEL ESTUDIANTE
    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmail());
        System.out.println("Semestre: " + semestre);
    }
}

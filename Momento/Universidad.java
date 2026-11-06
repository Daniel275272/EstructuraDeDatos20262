package Momento;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class Universidad {

    private HashMap<String, Estudiante> estudiantes;
    private Facultad[] facultades;
    private int cantidadFacultades;

    private LinkedList<Materia> materias;
    private TreeMap<String, Aula> aulas;
    private LinkedList<Edificio> edificios;

    private int[][] distancias;

    public Universidad() {
        estudiantes = new HashMap<>();
        facultades = new Facultad[5];
        cantidadFacultades = 0;

        materias = new LinkedList<>();
        aulas = new TreeMap<>();
        edificios = new LinkedList<>();

        distancias = new int[0][0];

        registrarAulasIniciales();
        registrarEdificiosIniciales();
    }
    
    private void registrarEdificiosIniciales() {
    registrarEdificio(new Edificio("E01", "Ingeniería"));
    registrarEdificio(new Edificio("E02", "Biblioteca"));
    registrarEdificio(new Edificio("E03", "Cafetería"));
    registrarEdificio(new Edificio("E04", "Rectoría"));
    registrarEdificio(new Edificio("E05", "Laboratorios"));
}

    // AULAS INICIALES
    private void registrarAulasIniciales() {
        registrarAula("A101",
                new Aula("A101", "Aula 101", 30));

        registrarAula("A102",
                new Aula("A102", "Aula 102", 25));

        registrarAula("A103",
                new Aula("A103", "Aula 103", 40));
    }

    // ESTUDIANTES

    public boolean registrarEstudiante(Estudiante estudiante) {
        if (estudiante == null ||
            estudiantes.containsKey(estudiante.getId())) {
            return false;
        }

        estudiantes.put(estudiante.getId(), estudiante);
        return true;
    }

    public Estudiante buscarEstudiante(String id) {
        return estudiantes.get(id);
    }

    public boolean eliminarEstudiante(String id) {
        Estudiante estudiante = estudiantes.remove(id);

        if (estudiante == null) {
            return false;
        }

        for (Materia materia : materias) {
            materia.cancelarInscripcion(estudiante);
            materia.retirarDeListaEspera(estudiante);
        }

        return true;
    }

    public int cantidadEstudiantes() {
        return estudiantes.size();
    }

    public void mostrarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        for (Estudiante estudiante : estudiantes.values()) {
            estudiante.mostrarInformacion();
            System.out.println("----------------------");
        }
    }

    // FACULTADES

    public boolean registrarFacultad(Facultad facultad) {
        if (facultad == null ||
            cantidadFacultades >= facultades.length) {
            return false;
        }

        for (int i = 0; i < cantidadFacultades; i++) {
            if (facultades[i].getCodigo().equals(facultad.getCodigo())) {
                return false;
            }
        }

        facultades[cantidadFacultades] = facultad;
        cantidadFacultades++;

        return true;
    }

    public Facultad buscarFacultad(int posicion) {
        if (posicion < 0 || posicion >= cantidadFacultades) {
            return null;
        }

        return facultades[posicion];
    }

    public int cantidadFacultades() {
        return cantidadFacultades;
    }

    // MATERIAS

    public boolean registrarMateria(Materia materia) {
        if (materia == null ||
            buscarMateria(materia.getCodigo()) != null) {
            return false;
        }

        materias.add(materia);
        return true;
    }

    public Materia buscarMateria(String codigo) {
        for (Materia materia : materias) {
            if (materia.getCodigo().equals(codigo)) {
                return materia;
            }
        }

        return null;
    }

    public boolean eliminarMateria(String codigo) {
        Materia materia = buscarMateria(codigo);

        if (materia == null) {
            return false;
        }

        return materias.remove(materia);
    }

    public int cantidadMaterias() {
        return materias.size();
    }

    public void mostrarMaterias() {
        if (materias.isEmpty()) {
            System.out.println("No hay materias registradas.");
            return;
        }

        for (Materia materia : materias) {
            System.out.println(
                materia.getCodigo() + " - " +
                materia.getNombre() + " | Cupos: " +
                materia.getCuposMaximos() + " | Inscritos: " +
                materia.cantidadInscritos()
            );
        }
    }

    // AULAS

    public boolean registrarAula(String codigo, Aula aula) {
        if (codigo == null || aula == null ||
            aulas.containsKey(codigo)) {
            return false;
        }

        aulas.put(codigo, aula);
        return true;
    }

    public Aula buscarAula(String codigo) {
        return aulas.get(codigo);
    }

    public boolean eliminarAula(String codigo) {
        return aulas.remove(codigo) != null;
    }

    public int cantidadAulas() {
        return aulas.size();
    }

    public void mostrarAulas() {
        if (aulas.isEmpty()) {
            System.out.println("No hay aulas registradas.");
            return;
        }

        System.out.println("\n--- AULAS REGISTRADAS ---");

        for (Aula aula : aulas.values()) {
            aula.mostrarInformacion();
            System.out.println("----------------------");
        }
    }

    // EDIFICIOS

    public boolean registrarEdificio(Edificio edificio) {
        if (edificio == null) {
            return false;
        }

        for (Edificio actual : edificios) {
            if (actual.getCodigo().equals(edificio.getCodigo())) {
                return false;
            }
        }

        edificios.add(edificio);
        actualizarMatrizDistancias();

        return true;
    }

    private void actualizarMatrizDistancias() {
        int cantidad = edificios.size();
        int[][] nuevaMatriz = new int[cantidad][cantidad];

        for (int i = 0; i < cantidad; i++) {
            for (int j = 0; j < cantidad; j++) {
                nuevaMatriz[i][j] = (i == j) ? 0 : -1;

                if (i < distancias.length && j < distancias.length) {
                    nuevaMatriz[i][j] = distancias[i][j];
                }
            }
        }

        distancias = nuevaMatriz;
    }

    public Edificio buscarEdificio(int posicion) {
        if (posicion < 0 || posicion >= edificios.size()) {
            return null;
        }

        return edificios.get(posicion);
    }

    public int cantidadEdificios() {
        return edificios.size();
    }

    public void mostrarEdificios() {
        if (edificios.isEmpty()) {
            System.out.println("No hay edificios registrados.");
            return;
        }

        for (int i = 0; i < edificios.size(); i++) {
            Edificio edificio = edificios.get(i);

            System.out.println(
                i + ". " + edificio.getNombre() +
                " (" + edificio.getCodigo() + ")"
            );
        }
    }

    // MATRIZ DE DISTANCIAS

    public boolean registrarDistancia(
            int origen, int destino, int distancia) {

        if (origen < 0 || destino < 0 ||
            origen >= edificios.size() ||
            destino >= edificios.size() ||
            distancia < 0 || origen == destino) {
            return false;
        }

        distancias[origen][destino] = distancia;
        distancias[destino][origen] = distancia;

        return true;
    }

    public int obtenerDistancia(int origen, int destino) {
        if (origen < 0 || destino < 0 ||
            origen >= edificios.size() ||
            destino >= edificios.size()) {
            return -1;
        }

        return distancias[origen][destino];
    }

    public int[][] getDistancias() {
        return distancias;
    }
}

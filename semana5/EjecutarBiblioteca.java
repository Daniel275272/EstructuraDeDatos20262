package semana5;

public class EjecutarBiblioteca {
    public static void main(String[] args) {

        Biblioteca.registrarLibro(new Libro(1, "Cien años de soledad", "García Márquez"));
        Biblioteca.registrarLibro(new Libro(2, "El principito", "Saint-Exupéry"));
        Biblioteca.registrarLibro(new Libro(3, "1984", "George Orwell"));
        Biblioteca.registrarLibro(new Libro(4, "Don Quijote", "Cervantes"));

        Biblioteca.listarLibros();

        Biblioteca.buscarLibro(3);
        Biblioteca.buscarLibro(99);

        Biblioteca.ubicarEnSeccion(0, 0, Biblioteca.libros[0]);
        Biblioteca.ubicarEnSeccion(1, 2, Biblioteca.libros[1]);
        Biblioteca.ubicarEnSeccion(2, 1, Biblioteca.libros[2]);
        Biblioteca.mostrarSecciones();

        Biblioteca.registrarSolicitud(new SolicitudPrestamo(1, "Ana", 1));
        Biblioteca.registrarSolicitud(new SolicitudPrestamo(2, "Luis", 2));
        Biblioteca.registrarSolicitud(new SolicitudPrestamo(3, "Sofía", 3));

        Biblioteca.mostrarSolicitudes();
        Biblioteca.procesarSolicitud();
        Biblioteca.procesarSolicitud();
        Biblioteca.mostrarSolicitudes();

        Biblioteca.mostrarHistorial();
    }
}

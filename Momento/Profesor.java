package Momento;

public class Profesor extends Persona {

    private String departamento;

    public Profesor(String nombre, String id, String email, String departamento) {
        super(nombre, id, email);
        this.departamento = departamento;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmail());
        System.out.println("Departamento: " + departamento);
    }
}

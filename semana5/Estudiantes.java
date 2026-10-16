package semana5;
public class Estudiantes {

    private int codigo;
    private String nombre;
    private String programa;
    private double promedio;

    public Estudiantes(int codigo,String nombre,String programa,double promedio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.programa = programa;
        this.promedio = promedio;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    
    public boolean tieneMejorPromedioQue(Estudiantes otro) {
        return this.promedio > otro.promedio;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "codigo= " + codigo + ", nombre= " + nombre + ", programa= " + programa + ", promedio= " + promedio  + '}';
    }
    
    
}

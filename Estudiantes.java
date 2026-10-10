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
    public int getcodigo(){
        return codigo;
    }
    public String getnombre(){
        return nombre;
    }
    public String getprogramna(){
        return programa;
    }
    public double getpromedio(){
        return promedio;
    }
    @Override
    public String toString() {
        return "Estudiante{" + "codigo= " + codigo + ", nombre= " + nombre + ", pograma=" + programa + ", promedio= " + promedio  + '}';
    }
    
    
}

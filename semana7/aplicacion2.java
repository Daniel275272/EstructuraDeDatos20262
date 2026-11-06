package semana7;
import java.util.HashMap;
import java.util.Map;


public class aplicacion2 {
    public static void main(String[] args){
        Map<Integer,String> Estudiantes = new HashMap<>();

        Estudiantes.put(856589, "Juan Perez");
        Estudiantes.put(547821, "Byron Cano");
        Estudiantes.put(698536, "Laura Beltran");
        System.out.println(Estudiantes.size()); 
        System.out.println(Estudiantes.isEmpty()); 
        System.out.println(Estudiantes); 
        
        System.out.println(Estudiantes.containsKey(547821));
        System.out.println(Estudiantes.containsValue("Byron Cano"));
        System.out.println(Estudiantes.remove(547821));
        System.out.println(Estudiantes);
        System.out.println(Estudiantes.replace(856589, "Juan Perez", "Jhon Canon"));
        System.out.println(Estudiantes);

       
        for(Integer key : Estudiantes.keySet()){
            System.out.println("Clave: " + key + " --> Valor: " + Estudiantes.get(key));
        }
        /*
        Iterator est = estudiantes.keySet().iterator();
        while(est.hasNext()){
        Integer key = (Integer) est.next();
        System.out.println("Clave: " + key + " Valor: " + estudiantes.get(key));
        }
        */        
    }    

}


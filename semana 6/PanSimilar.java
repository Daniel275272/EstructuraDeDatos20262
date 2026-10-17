import java.util.LinkedList;
import java.util.Deque;
public class PanSimilar {
    public static boolean  mismoPan(String[] sh1, String[] sh2){

        Deque<String> pan1 = new LinkedList<>();
        Deque<String> pan2 = new LinkedList<>();

        for (String ingredientes : sh1){
            pan1.add(ingredientes);
        }
        for (String ingredientes: sh2){
            pan2.add(ingredientes);
        }
        String panI1 = pan1.peekFirst();
        String panF1 = pan1.peekLast();
        
        String panI2 = pan2.peekFirst();
        String panF2 = pan2.peekLast();

        return panI1.equals(panI2) && panF1.equals(panF2);

    }

    public static void main(String[] args){

        String[] sh1 = {"pan blanco", "lechuga","pan blanco"};
        String[] sh2 = {"pan blanco", "tomate","pan blanco"};



        System.out.println("los sandwiches tienen el mismo pan: "+ mismoPan(sh1, sh2));

        String[] sh3 = {"pan integral", "queso","pan integral"};
        String[] sh4 = {"pan blanco", "queso","pan intregral"};
        System.out.println("los sandwiches tienen el mismo pan: "+ mismoPan(sh3,sh4));

    }
    
}

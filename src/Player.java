import java.lang.reflect.Array;
import java.util.Scanner;

public class Player {
    public String name;

    public Player(String name){
        this.name = name;
    }

    public Tuple move(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите x и y ячейки через пробел:");
        String move = scanner.nextLine();
        String[] line = move.split(" ");
        Tuple coordinates = new Tuple(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        return coordinates;
    }
}

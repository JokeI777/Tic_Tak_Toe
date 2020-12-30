import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    final char empty_field = ' ';
    char[][] table  = new char[3][3];


    void startGame() {
        createTable();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        player = new Player(name);
        while (true) {
            Tuple move = player.move();
            if(!isCellValid(Integer.parseInt(move.y.toString()), Integer.parseInt(move.x.toString())))
            {
                System.out.println("Повторите попытку");
                updateTable();
                continue;
            }
            else {
                table[Integer.parseInt(move.y.toString())][Integer.parseInt(move.x.toString())] = 'X';
            }

            String check = checkTable();
            if (check != "") {
                System.out.println(check);
                break;
            }

            moveII();
            check = checkTable();
            if (check != "") {
                System.out.println(check);
                break;
            }
            updateTable();
        }
        System.out.println("Игра завершена.");

    }

    void createTable() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                table[i][j] = empty_field;
    }

    boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3)
            return false;
        return table[y][x] == empty_field;
    }

    void moveII() {
        int x, y;
        Random random = new Random();
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(y, x));
        table[y][x] = 'O';
    }

    String checkTable() {
        Character[] cells = new Character[]{'X', 'Y'};
        for(Character cell : cells){
            for(int i = 0; i < 3; i++) {
                if ((table[i][0] == cell && table[i][1] == cell &&
                        table[i][2] == cell) ||
                        (table[0][i] == cell && table[1][i] == cell &&
                                table[2][i] == cell))
                    if(cell == 'X')
                        return "Игрок " + player.name + " выйграл";
                    else
                        return "ИИ выйграл";
            }
            if((table[0][0] == cell && table[1][1] == cell &&
                    table[2][2] == cell) ||
                    (table[2][0] == cell && table[1][1] == cell &&
                            table[0][2] == cell))
                if(cell == 'X')
                    return "Игрок выйграл";
                else
                    return "ИИ выйграл";
        }
        boolean full = true;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (table[i][j] == empty_field)
                    full = false;
        if(full){
            return "Таблица заполнена, ничья";
        }
        return "";
    }

    void updateTable() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(table[i][j] + " ");
            System.out.println();
        }
    }
}

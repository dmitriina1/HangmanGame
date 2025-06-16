import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        do {
            GameEngine gameEngine = new GameEngine();
            gameEngine.gameSession(gameEngine);
        } while (isContinue());
    }

    private static boolean isContinue() {
        System.out.println("Хотите продолжить игру? (Да/Нет)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        while (!answer.equalsIgnoreCase("Нет")) {
            if (answer.equalsIgnoreCase("Да")) {
                return true;
            } else if (answer.equalsIgnoreCase("Нет")) {
                return false;
            } else {
                answer = sc.nextLine();
            }
        }
        return false;
    }
}
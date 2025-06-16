import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine;
        do {
            gameEngine = new GameEngine();
            gameEngine.gameSession(gameEngine);
        } while (GameEngine.isContinue());
    }


}
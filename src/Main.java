public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        do{
        UserInput userInput = new UserInput(gameEngine);
        gameEngine.printHangman(userInput.inputChar());}
        while (gameEngine.isGameOver());
    }
}
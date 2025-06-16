import java.util.Scanner;

/**
 * Ядро программы. Отвечает за игру.
 **/
public class GameEngine {

    private int errorScore = 0;
    private PicturesDrawer picturesDrawer;
    private WordManipulator wordManipulator;
    private Dictionary dictionary;

    public GameEngine() {
        dictionary = new Dictionary();
        wordManipulator = new WordManipulator(dictionary);
        picturesDrawer = new PicturesDrawer();
        System.out.println(wordManipulator.getWordMask());
        System.out.println(picturesDrawer.getPicture(getErrorScore()));
    }

    public static boolean isContinue() {
        System.out.println("Хотите продолжить игру? (Да/Нет)");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        while (!answer.equalsIgnoreCase("Нет")) {
            if (answer.equalsIgnoreCase("Да")) {
                return true;
            } else if (answer.equalsIgnoreCase("Нет")) {
                return false;
            } else {
                System.out.println("Ошибка ввода! Введите либо 'Да', либо 'Нет'");
                answer = sc.nextLine();
            }
        }
        return false;
    }

    public void gameSession(GameEngine gameEngine) {
        do {
            UserInput userInput = new UserInput(gameEngine);
            gameEngine.printHangman(userInput.inputChar());
        }
        while (gameEngine.isGameOver());
    }

    public void printHangman(char letter) {
        wordManipulator.createWordMask(letter);
        if (wordManipulator.checkLetterInWord(letter)) {
            increaseErrorScore();
        }
        wordManipulator.addLetterList(letter);
        System.out.println(picturesDrawer.getPicture(getErrorScore()));
    }

    public void increaseErrorScore() {
        if (isLose()) {
            this.errorScore++;
        }
    }

    public boolean isGameOver() {
        if (!isLose()) {
            System.out.println("Ты проиграл!");
            System.out.println(wordManipulator.getHiddenWord());
        } else if (isWin()) {
            System.out.println("Поздравляю, ты победил!");
        }
        return (!isWin() && isLose());
    }

    public boolean isLose() {
        return ((errorScore < 6));
    }

    public boolean isPreLose() {
        return ((errorScore < 5));
    }

    public boolean isWin() {
        return (wordManipulator.isWordCompleted());
    }

    public WordManipulator getWordManipulator() {
        return wordManipulator;
    }

    public PicturesDrawer getGamePictures() {
        return picturesDrawer;
    }

    public int getErrorScore() {
        return errorScore;
    }

}

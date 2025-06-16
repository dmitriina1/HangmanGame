/**
 * Ядро программы. Отвечает за игру.
 **/
public class GameEngine {

    private int errorScore = 0;
    private GamePictures gamePictures;
    private WordManipulator wordManipulator;
    private Dictionary dictionary;

    public GameEngine() {
        dictionary = new Dictionary();
        wordManipulator = new WordManipulator(dictionary);
        gamePictures = new GamePictures();
        System.out.println(wordManipulator.getWordMask());
        System.out.println(gamePictures.getPICTURE(getErrorScore()));
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
        System.out.println(gamePictures.getPICTURE(getErrorScore()));
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

    public GamePictures getGamePictures() {
        return gamePictures;
    }

    public int getErrorScore() {
        return errorScore;
    }

}

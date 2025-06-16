/**
 * Ядро программы. Отвечает за игру.
 **/
public class GameEngine {
    private int errorScore = 0;
    private GamePictures gamePictures;
    private WordManipulator wordManipulator;

    public GameEngine() {
        Dictionary dictionary = new Dictionary();
        wordManipulator = new WordManipulator(dictionary);
        gamePictures = new GamePictures();
        System.out.println(gamePictures.getPICTURE(getErrorScore()));
    }

    public void printHangman(char letter) {
        wordManipulator.createWordMask(letter);
        if (wordManipulator.checkLetterInWord(letter)) {
            increaseErrorScore();
        }
        wordManipulator.addLetterList(letter);
        System.out.println(gamePictures.getPICTURE(getErrorScore()));
    }

    public WordManipulator getWordManipulator() {
        return wordManipulator;
    }

    public GamePictures getGamePictures() {
        return gamePictures;
    }

    public void increaseErrorScore() {
        if (isLose()) {
            this.errorScore++;
        }
    }

    public boolean isGameOver() {
        if (!isLose()) {
            System.out.println("Ты проиграл!");
        } else if (isWin()) {
            System.out.println("Поздравляю, ты победил!");
        }
        return (!isWin() && isLose());
    }

    public int getErrorScore() {
        return errorScore;
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

}

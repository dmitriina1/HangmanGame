import java.util.Scanner;

/**
 * Класс для корректировки ввода с клавиатуры
 **/
public class UserInput {

    private GameEngine gameEngine;

    public UserInput(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public char inputChar() {
        String input;
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        do {
            input = scanner.nextLine();
            if (input.length() == 1 && input.equals(input.toLowerCase())) {
                isValid = checkInput(input);
            } else if (gameEngine.isPreLose() && input.length() != 1) {
                gameEngine.increaseErrorScore();
                printGameSituation("Некорректный ввод. Необходимо ввести 1 букву.");
            } else if (input.length() == 1 && Character.UnicodeBlock.of(input.charAt(0)) == Character.UnicodeBlock.CYRILLIC) {
                printGameSituation("Некорректный ввод. Необходимо вводить все с маленькой буквы!");
            } else {
                break;
            }
        } while (!isValid);
        return input.charAt(0);
    }

    private boolean checkInput(String input) {
        boolean isValid = false;
        if (Character.UnicodeBlock.of(input.charAt(0)) == Character.UnicodeBlock.CYRILLIC) {
            if (!WordManipulator.getLetterList().contains(input.charAt(0))) {
                isValid = true;
            } else {
                printGameSituation("Данный символ уже вводился!");
            }
        } else if (!gameEngine.isLose()) {
            gameEngine.increaseErrorScore();
            printGameSituation("Используй только русские буквы!");
        } else {
            return true;
        }
        return isValid;
    }

    private void printGameSituation(String comment) {
        System.out.println(comment);
        System.out.println(gameEngine.getWordManipulator().getWordMask());
        System.out.println(gameEngine.getGamePictures().getPICTURE(gameEngine.getErrorScore()));
    }

}

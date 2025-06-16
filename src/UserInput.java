import java.util.Scanner;

/**
 * Класс для корректировки ввода с клавиатуры
 **/
public class UserInput {

    private static Scanner scanner;
    private GameEngine gameEngine;

    public UserInput(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    static {
        scanner = new Scanner(System.in);
    }

    public char inputChar() {
        String input;
        boolean isValid = false;
        do {
            input = scanner.nextLine();
            if (input.length() == 1 && input.equals(input.toLowerCase())) {
                isValid = checkInput(input);
            }else if(input.length() == 1 && Character.UnicodeBlock.of(input.charAt(0)) == Character.UnicodeBlock.CYRILLIC) {
                printGameSituation("Некорректный ввод. Необходимо вводить все с маленькой буквы!");
            } else if (gameEngine.isPreLose()) {
                gameEngine.increaseErrorScore();
                printGameSituation("Некорректный ввод. Необходимо ввести 1 букву.");
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
        System.out.println(gameEngine.getWordManipulator().getWordmask());
        System.out.println(gameEngine.getGamePictures().getPICTURE(gameEngine.getErrorScore()));
    }

}

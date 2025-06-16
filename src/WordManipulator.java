import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Класс для работы со словом
 **/
public class WordManipulator {

    private String hidword = "";
    private String wordmask = "";
    private static StringBuilder stringBuilder;
    private static final Set<Character> LETTERLIST;

    static {
        stringBuilder = new StringBuilder();
        LETTERLIST = new HashSet<>();
    }

    public WordManipulator(Dictionary dictionary) {
        takeRandomWord(dictionary);
    }

    private void takeRandomWord(Dictionary dictionary) {
        Random random = new Random();
        int hiddenWordIndex = random.nextInt(dictionary.getDictionary().size());
        System.out.println(dictionary.getWord(hiddenWordIndex));
        hidword = dictionary.getWord(hiddenWordIndex);
    }

    public void createWordMask() {
        for (Character character : hidword.toCharArray()) {
            stringBuilder.append("*");
        }
        wordmask = stringBuilder.toString();
        stringBuilder = new StringBuilder();
    }

    public void createWordMask(char letter) {
        for (Character ch : hidword.toCharArray()) {
            if (ch == letter) {
                stringBuilder.append(ch);
            } else if (LETTERLIST.contains(ch)) {
                stringBuilder.append(ch);
            } else {
                stringBuilder.append("*");
            }
        }
        wordmask = stringBuilder.toString();
        System.out.println(wordmask);
        stringBuilder = new StringBuilder();
    }

    public boolean checkLetterInWord(char letter) {
        return !hidword.contains(String.valueOf(letter));
    }

    public String getWordmask() {
        if (wordmask.isEmpty()) {
            createWordMask();
        }
        return wordmask;
    }

    public static Set<Character> getLetterList() {
        return LETTERLIST;
    }

    public boolean isWordCompleted() {
        return wordmask.equals(hidword);
    }

    public void addLetterList(char letter) {
        LETTERLIST.add(letter);
    }

}

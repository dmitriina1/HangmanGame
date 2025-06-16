import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Класс для работы со словом
 **/
public class WordManipulator {

    private static final Set<Character> LETTERLIST;
    private static StringBuilder stringBuilder;
    private String hiddenword = "";
    private String wordmask = "";

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
        hiddenword = dictionary.getWord(hiddenWordIndex);
    }

    public void createWordMask() {
        for (Character character : hiddenword.toCharArray()) {
            stringBuilder.append("*");
        }
        wordmask = stringBuilder.toString();
        stringBuilder = new StringBuilder();
    }

    public void createWordMask(char letter) {
        for (Character ch : hiddenword.toCharArray()) {
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
        return !hiddenword.contains(String.valueOf(letter));
    }

    public String getWordMask() {
        if (wordmask.isEmpty()) {
            createWordMask();
        }
        return wordmask;
    }

    public boolean isWordCompleted() {
        return wordmask.equals(hiddenword);
    }

    public void addLetterList(char letter) {
        LETTERLIST.add(letter);
    }

    public static Set<Character> getLetterList() {
        return LETTERLIST;
    }

    public String getHiddenWord() {
        return hiddenword;
    }

}

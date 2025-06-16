import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс для получения данный из файла, предоставляет список слов
 **/
public class Dictionary {

    private static List<String> DICTIONARY;

    public Dictionary() {
        DICTIONARY = new ArrayList<String>();
        readFile();
    }

    public List<String> getDictionary() {
        return DICTIONARY;
    }

    public String getWord(int wordIndex) {
        return DICTIONARY.get(wordIndex);
    }

    private void readFile() {
        try {
            File myFile = new File("src/resources/Dictionary.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                DICTIONARY.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

}

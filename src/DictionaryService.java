import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class DictionaryService {
    private HashMap<String, String> dict = new HashMap<String, String>();
    private String _fileName;

    public void read(String fileName){
        _fileName = fileName;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    dict.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Файл не найден, создаем новый.");
        }
    }

    private void saveFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(_fileName))) {
            for (Map.Entry<String, String> entry : dict.entrySet()) {
                bw.write(entry.getKey() + "-" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String key){
        if (dict.containsKey(key)){
            dict.remove(key);
            saveFile();
        }
    }

    public String find(String key){
        return dict.getOrDefault(key, "Нет записей");
    }

    public void addWord(String key, String value) throws Exception {
        if (validate(key)){
            dict.put(key, value);
            saveFile();
        }
        else{
            throw new Exception("Слово не соответствует требованиям");
        }
    }
    protected abstract boolean validate(String key);
}

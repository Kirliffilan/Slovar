import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class DictionaryService {
    private final HashMap<String, String> _dict = new HashMap<String, String>();
    private String _fileName;

    protected void read(String fileName){
        _fileName = fileName;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    _dict.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Файл не найден, создаем новый.");
        }
    }

    private void saveFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(_fileName))) {
            for (Map.Entry<String, String> entry : _dict.entrySet()) {
                bw.write(entry.getKey() + "-" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String key) throws Exception {
        if (_dict.containsKey(key)){
            _dict.remove(key);
            saveFile();
        }
        else{
            throw new Exception("Нет такого слова.");
        }
    }

    public String find(String key){
        return _dict.getOrDefault(key, "Нет записей");
    }

    public void addWord(String key, String value) throws Exception {
        if (validate(key)){
            _dict.put(key, value);
            saveFile();
        }
        else{
            throw new Exception("Слово не соответствует правилам языка!");
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : _dict.entrySet()){
            sb.append(entry.getKey() + " - " + entry.getValue() + "\n");
        }
        return sb.toString();
    }

    public abstract String getLangRules();
    protected abstract boolean validate(String key);
}

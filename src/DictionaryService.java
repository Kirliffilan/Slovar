import java.io.*;
import java.util.HashMap;
import java.util.Map;
/// Класс для работы со словарём
public abstract class DictionaryService {
    private final HashMap<String, String> _dict = new HashMap<String, String>();
    private String _fileName;
    /// Метод для чтения словаря с файла
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
    /// Метод для сохранения словаря
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
    /// Метод для удаления слова из словаря
    public void delete(String key) throws Exception {
        if (_dict.containsKey(key)){
            _dict.remove(key);
            saveFile();
        }
        else{
            throw new Exception("Нет такого слова.");
        }
    }
    /// Метод для нахождения перевода слова в словаре
    public String find(String key){
        return _dict.getOrDefault(key, "Нет записей");
    }

    ///  Метод для добавления новой записи в словарь
    public void addWord(String key, String value) throws Exception {
        if (validate(key)){
            _dict.put(key, value);
            saveFile();
        }
        else{
            throw new Exception("Слово не соответствует правилам языка!");
        }
    }

    /// Все слова в словаре (String)
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : _dict.entrySet()){
            sb.append(entry.getKey() + " - " + entry.getValue() + "\n");
        }
        return sb.toString();
    }
    /// Правила языка (String)
    public abstract String getLangRules();
    /// Метод для проверки валидности слова для языка
    protected abstract boolean validate(String key);
}

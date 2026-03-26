public abstract class DictionaryService {
    public abstract void read(String filename);
    public abstract void delete(String key);
    public abstract void find(String key);
    public abstract void add_word(String key, String value);
}

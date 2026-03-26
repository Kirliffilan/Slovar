public class Lang2DictionaryService extends DictionaryService{
    @Override
    protected boolean validate(String key) {
        return key.matches("[\\d{5}]");
    }
}

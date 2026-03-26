public class Lang1DictionaryService extends DictionaryService{
    @Override
    protected boolean validate(String key) {
        return key.matches("[a-zA-Z]{4}");
    }
}

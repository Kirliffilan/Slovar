public class Lang1DictionaryService extends DictionaryService{
    @Override
    public String getLangRules() {
        return "Только латинские буквы, 4 символа.";
    }

    @Override
    protected boolean validate(String key) {
        return key.matches("[a-zA-Z]{4}");
    }
}

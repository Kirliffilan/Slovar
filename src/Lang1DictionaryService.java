public class Lang1DictionaryService extends DictionaryService{
    public Lang1DictionaryService(){
        read("Lang1.txt");
    }

    @Override
    public String getLangRules() {
        return "Только латинские буквы, 4 символа.";
    }

    @Override
    protected boolean validate(String key) {
        return key.matches("[a-zA-Z]{4}");
    }
}

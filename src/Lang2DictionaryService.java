public class Lang2DictionaryService extends DictionaryService{
    public Lang2DictionaryService(){
        read("Lang2.txt");
    }

    @Override
    public String getLangRules() {
        return "Только цифры, 5 символов";
    }

    @Override
    protected boolean validate(String key) {
        return key.matches("[0-9]{5}");
    }
}

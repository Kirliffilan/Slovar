void main() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Выберите язык - 1 или 2:");
    int choice = sc.nextInt();
    DictionaryService ds = null;
    switch (choice){
        case 1:
            ds = new Lang1DictionaryService();
            break;
        case 2:
            ds = new Lang2DictionaryService();
            break;
        default:
            System.out.println("Ошибка. Такого языка нет.");
            break;
    }
    if (ds != null) {
        while (true) {
            System.out.println("Что хотите сделать?\n" +
                    "1) Просмотр содержимого словаря\n" +
                    "2) Перевести слово\n" +
                    "3) Добавить запись\n" +
                    "4) Удалить запись\n"
            );
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println(ds);
                    break;
                }
                case 2: {
                    System.out.println("Какое слово перевести?");
                    String key = sc.next();
                    System.out.println(ds.find(key));
                    break;
                }
                case 3: {
                    System.out.println("Напоминание, правила языка:\n" +
                            ds.getLangRules() +
                            "\nВведите слово, которое будете переводить:");
                    String key = sc.next();
                    System.out.println("Введите перевод:");
                    String value = sc.next();
                    try{
                        ds.addWord(key, value);
                        System.out.println("Успешно!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 4: {
                    System.out.println("Перевод какого слова удалить?");
                    String key = sc.next();
                    try{
                        ds.delete(key);
                        System.out.println("Успешно!");
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                default:{
                    System.out.println("Неопознанная команда.");
                }
            }
            System.out.println();
        }
    }
}

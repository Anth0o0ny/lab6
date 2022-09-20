package baseclasses;




public enum MpaaRating {

    G("Нет возрастных ограничений"),
    PG("Рекомендуется присутсттвие родителей"),
    PG_13("Детям до 13 лет просмотр не желателен"),
    R("Лицам до 17 лет обязательно присутствие взрослого"),
    NC_17("Лицам до 18 лет просмотр запрещен");

    private final String title;

    MpaaRating(String title) {
        this.title = title;
    }

//    public static MpaaRating chooseRating() {
//        InputArgumentTester iat = new InputArgumentTester();
//        return iat.assignMpaaRating();
//    }

    @Override
    public String toString() {
        return title;
    }
}


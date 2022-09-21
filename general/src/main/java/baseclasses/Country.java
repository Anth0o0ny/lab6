package baseclasses;


import sub.InputArgumentTester;

public enum Country {

    RUSSIA("Россия"),
    USA("США"),
    GERMANY("Германия"),
    ITALY("Италия"),
    NORTH_KOREA("Северная Корея");

    private final String title;

    Country(String title) {
        this.title = title;
    }

    public static Country chooseCountry() {
        InputArgumentTester iat = new InputArgumentTester();
        return iat.assignInputCountry();
    }

    @Override
    public String toString() {
        return title;
    }

}

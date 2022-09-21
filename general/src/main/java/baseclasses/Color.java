package baseclasses;


import sub.InputArgumentTester;

public enum Color {

    RED("Красный"),
    BLACK("Черный"),
    YELLOW("Желтый"),
    ORANGE("Оранжевый"),
    WHITE("Белый");

    private final String title;

    Color(String title) {
        this.title = title;
    }

    public static Color chooseColor() {
        InputArgumentTester iat = new InputArgumentTester();
        return iat.assignInputColor();
    }

    @Override
    public String toString() {
        return title;
    }
}

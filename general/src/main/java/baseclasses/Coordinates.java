package baseclasses;


import java.io.Serializable;

public class Coordinates implements Serializable {
    private Double x; //Максимальное значение поля: 398, Поле не может быть null
    private Float y; //Поле не может быть null

    public Coordinates(Double x, Float y) {
        setX(x);
        setY(y);
    }
    public Coordinates(){}
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

}

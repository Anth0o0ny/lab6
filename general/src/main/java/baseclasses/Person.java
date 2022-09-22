package baseclasses;




import javax.xml.bind.annotation.XmlElement;

public class Person {

    private String name; //Поле не может быть null, Строка не может быть пустой
    private float height; //Значение поля должно быть больше 0
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null

    public Person(String name, float height, Color hairColor, Country nationality) {
        setName(name);
        setHeight(height);
        setHairColor(hairColor);
        setNationality(nationality);
    }

    public Person() {
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    @XmlElement
    public void setHeight(float height) {
        this.height = height;
    }

    public Color getHairColor() {
        return hairColor;
    }

    @XmlElement
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    @XmlElement
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }


}

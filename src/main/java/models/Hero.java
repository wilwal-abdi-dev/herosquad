package models;
import java.util.ArrayList;
import java.util.Objects;

public class Hero {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return age == hero.age &&
                id == hero.id &&
                Objects.equals(name, hero.name) &&
                Objects.equals(strength, hero.strength) &&
                Objects.equals(weakness, hero.weakness);
    }
    //
    @Override
    public int hashCode() {
        return Objects.hash(name, age, strength, weakness, id);
    }

    private String name;
    private int age;
    private String strength;
    private String weakness;
    private int id;
    private static ArrayList<Hero> instances = new ArrayList<>();
    //
//
    public Hero(String name, int age, String strength, String weakness){

        this.name=name;
        this.age =age;
        this.strength=strength;
        this.weakness=weakness;
        instances.add(this);

    }
    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
    //
    public static ArrayList<Hero>getAll(){
        return instances;
    }

    public static void clearAllHeros(){
        instances.clear();
    }

    public void setId( int id){
        this.id = id;
    }

    public static Hero findById(int id){
        return instances.get(id-1);
    }

    public void update(String name, int age,String strength,String weakness){
        this.name =name;
        this.age=age;
        this.strength= strength;
        this.weakness=weakness;
    }
    public void deleteById(int id){
        instances.remove(id-1);
    }


    public String getstrength(){
        return strength;
    };
    public String getWeakness(){
        return weakness;
    }

    public int getId(){
        return id;
    }

}


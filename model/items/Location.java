package model.items;



import java.util.ArrayList;
import java.util.Objects;

public class Location {


    private String name;
    private ArrayList<Item> items;

    public Location() {
        name = "";
        items = new ArrayList<>();
    }

    public Location(String input) {
        name = input;
        items = new ArrayList<>();
    }


    public void setName(String name) {
        this.name = name;
    }


    public void addItems(Item i) {
        if (!items.contains(i)) {
            items.add(i);
            i.setLocation(this);
        }

    }

    public String getNameLocation() {
        return name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package desarrollomobile.tiendadeclases.tiendadeclases.Messages;

import java.util.List;

import desarrollomobile.tiendadeclases.tiendadeclases.classes.Class;

public class Message {

    public int id;
    public String name;
    public String description;
    public boolean read;
    public List<Class> classes;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public List<Class> getClasses() {
        return classes;
    }
}
package desarrollomobile.tiendadeclases.tiendadeclases.Messages;

public class Message {

    public int id;
    public String name;
    public String description;
    public boolean read;

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
}
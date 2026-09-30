import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private List<Instrument> instruments;

    public User(int id, List<Instrument> instruments) {
        this.id = id;
        this.instruments = instruments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public Instrument getDefaultInstrument() {
        if (instruments.isEmpty())
            throw new RuntimeException(this.toString() + " has not added any instrument");
        return instruments.get(0);
    }

    @Override
    public String toString() {
        return "User" + id;
    }
}

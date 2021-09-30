package testtest;

public class Event {
    private String EventID;
    private String Location;
    private String Time;
    private String Host;

    public Event(String ID, String Location, String Time, String Host) {
        this.EventID = ID;
        this.Location = Location;
        this.Time = Time;
        this.Host = Host;
    }

    public String getValue(String propertyName) {
        if (propertyName.equals("EventID"))
            return this.EventID;
        else if (propertyName.equals("Location"))
            return this.Location;
        else if (propertyName.equals("TimeMovieEvent"))
            return this.Time;
        else if (propertyName.equals("Host"))
            return this.Host;
        return null;
    }

    public String getID() {
        return this.EventID;
    }

}

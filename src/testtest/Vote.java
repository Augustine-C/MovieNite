package testtest;

public class Vote {
    private String Movie;
    private String Vote;

    public Vote(String Movie, String Vote) {
        this.Movie = Movie;
        this.Vote = Vote;
    }

    public String getValue(String propertyName) {
        if (propertyName.equals("Movie"))
            return this.Movie;
        else if (propertyName.equals("Vote"))
            return this.Vote;
        return null;
    }

    public String getID() {
        return this.Movie;
    }

}

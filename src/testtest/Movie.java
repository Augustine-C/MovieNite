package testtest;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    public String title;
    public String year;
    public String imdbID;
    public String type;
    public String poster;
    public String director;
    public String actors;
    public String plot;

    public Movie(JSONObject obj) {
        try {
            if (obj.getString("Response").equals("False"))
                return;
            this.title = obj.getString("Title");
            this.year = obj.getString("Year");
            this.imdbID = obj.getString("imdbID");
            this.type = obj.getString("Genre");
            this.poster = obj.getString("Poster");
            this.director = obj.getString("Director");
            this.actors = obj.getString("Actors");
            this.plot = obj.getString("Plot");
            System.out.println(title + year + plot);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

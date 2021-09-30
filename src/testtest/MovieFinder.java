package testtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.DefaultListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieFinder {
    private final String OMDBURL = "http://www.omdbapi.com/?apikey=bee8b997&s=";
    private final String MOVIEURL = "http://www.omdbapi.com/?apikey=bee8b997&plot=full&i=";
    private Map<Integer, JSONObject> results;
    private String response;
    private String movieResponse;
    private DefaultListModel model;

    public MovieFinder() {
        super();
    }

    public MovieFinder(DefaultListModel model) {
        this.model = model;
    }

    public void buildResponse(String inputName) {
        if (inputName == null || inputName.isEmpty()) {
            return;
        }
        inputName = inputName.replace(" ", "+");
        String urlstr = this.OMDBURL + inputName;
        // System.out.println(urlstr);
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlstr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        response = sb.toString();
    }

    public void buildResponseID(String id) {
        if (id == null || id.isEmpty()) {
            return;
        }
        String urlstr = this.MOVIEURL + id;
        // System.out.println(urlstr);
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlstr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        movieResponse = sb.toString();
        // System.out.println(movieResponse);
    }

    public void buildResults() {
        results = new HashMap<>();
        try {
            JSONObject obj = new JSONObject(response);
            if (obj.getString("Response").equals("False"))
                return;
            JSONArray search = obj.getJSONArray("Search");
            int totalResults = search.length();
            if (totalResults > 0) {
                for (int i = 0; i < search.length(); i++) {
                    JSONObject item = (JSONObject) search.get(i);
                    results.put(i + 1, item);
                    String title = item.getString("Title");
                    model.addElement(title);
                }
            }
        } catch (JSONException e) {
            System.out.println("There was a problem parsing the json response. Please try again");
        }
    }

    public Movie buildMovieDetail(int index) {
        Movie movie = null;
        try {
            String imdbid = results.get(index).getString("imdbID");
            System.out.println(imdbid);
            buildResponseID(imdbid);
            JSONObject obj = new JSONObject(movieResponse);
            movie = new Movie(obj);
            return movie;
        } catch (JSONException e) {
            System.out.println("There was an error parsing jasonfile");
        } finally {
            return movie;
        }
    }

    public Movie buildMovieByIMDBID(String imdbid) {
        Movie movie = null;
        try {
            System.out.println(imdbid);
            buildResponseID(imdbid);
            JSONObject obj = new JSONObject(movieResponse);
            movie = new Movie(obj);
            return movie;
        } catch (JSONException e) {
            System.out.println("There was an error parsing jasonfile");
        } finally {
            return movie;
        }
    }
}

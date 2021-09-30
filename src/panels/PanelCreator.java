package panels;

import javax.swing.JPanel;

import service.MovieService;

public abstract class PanelCreator {
    protected MovieService movieService;

    public PanelCreator() {
        this.movieService = MovieService.getMovieService();
    }

    public abstract MoviePanel createmoviePanel();

}

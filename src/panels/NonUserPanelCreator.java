package panels;

import javax.swing.JPanel;

import service.MovieService;

public class NonUserPanelCreator extends PanelCreator {

    @Override
    public MoviePanel createmoviePanel() {
        // TODO Auto-generated method stub
        MoviePanel panel = MoviePanel.getMoviePanel();
        panel.disableUserFunc();
        return panel;
    }

}

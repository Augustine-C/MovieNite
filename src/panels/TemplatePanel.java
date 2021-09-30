package panels;

import javax.swing.JPanel;

public abstract class TemplatePanel extends JPanel {

    public void createPanel() {
        setService();
        this.setBounds(0, 24, 882, 529);
        setLayout(null);
        init();
    }

    public abstract void setService();

    public abstract void init();
}

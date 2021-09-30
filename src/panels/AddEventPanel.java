package panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import service.EventService;

public class AddEventPanel extends JPanel {
    public EventService aeService = null;
    JTextField location = null;
    JTextField dateAndTime = null;

    public AddEventPanel(EventService aeService) {
        this.aeService = aeService;
        setBounds(0, 24, 882, 529);
        setLayout(null);
        setVisible(true);
        setLayout(null);
        JLabel label = new JLabel("Location");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label.setBounds(290, 110, 104, 16);
        add(label);

        this.location = new JTextField();
        this.location.setColumns(10);
        this.location.setBounds(290, 139, 309, 22);
        add(this.location);

        this.dateAndTime = new JTextField();
        this.dateAndTime.setColumns(10);
        this.dateAndTime.setBounds(290, 236, 309, 22);
        add(this.dateAndTime);

        JLabel label_1 = new JLabel("dateandtime");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label_1.setBounds(290, 203, 104, 16);
        add(label_1);

        JButton button = new JButton("Add");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                addEvent();
            }

        });
        button.setBounds(391, 290, 97, 25);
        add(button);

    }

    private boolean addEvent() {
        String locat = null;
        String date = null;
        locat = this.location.getText();
        date = this.dateAndTime.getText();
        if (locat.isEmpty() || locat == null) {
            System.out.println("location cannot be null");
            return false;
        }
        if (date.isEmpty() || date == null) {
            System.out.println("date cannot be null");
            return false;
        }
        if (this.aeService.addEvent(locat, date)) {
            this.location.setText("");
            this.dateAndTime.setText("");
            return true;
        }
        return false;

    }

}

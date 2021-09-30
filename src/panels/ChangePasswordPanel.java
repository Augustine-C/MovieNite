package panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import service.UserService;

import javax.swing.JList;

public class ChangePasswordPanel extends JPanel {
    private JPasswordField passwordField;
    private UserService uService = null;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    public static ChangePasswordPanel cPanel;

    public static ChangePasswordPanel getChangePasswordPanel() {
        if (cPanel == null) {
            cPanel = new ChangePasswordPanel(UserService.getUserService());
        }
        return cPanel;
    }

    /**
     * Create the panel.
     */
    private ChangePasswordPanel(UserService us) {
        this.uService = us;
        setBounds(100, 100, 900, 600);
        // setBounds(0, 24, 882, 529);
        setLayout(null);
        // setVisible(true);
        // setLayout(null);
        JLabel label = new JLabel("New Password");
        label.setBounds(448, 269, 104, 16);
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(label);

        passwordField = new JPasswordField();
        passwordField.setBounds(412, 312, 183, 22);
        add(passwordField);

        JButton button = new JButton("Add");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // addEvent();
                changePassword();
            }

        });
        button.setBounds(455, 372, 97, 25);
        add(button);

        textField = new JTextField();
        textField.setBounds(137, 65, 116, 22);
        add(textField);
        textField.setColumns(10);

        JButton btnUpdateFirstname = new JButton("Update FirstName");
        btnUpdateFirstname.setBounds(126, 112, 140, 25);
        btnUpdateFirstname.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // addEvent();
                changeFirstName();
            }

        });
        add(btnUpdateFirstname);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(137, 186, 116, 22);
        add(textField_1);

        JButton btnUpdateLastname = new JButton("Update LastName");
        btnUpdateLastname.setBounds(126, 233, 140, 25);
        btnUpdateLastname.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // addEvent();
                // changeFirstName();\
                changeLastName();
            }

        });
        add(btnUpdateLastname);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(137, 305, 116, 22);
        add(textField_2);

        JButton btnUpdateEmail = new JButton("Update Email");
        btnUpdateEmail.setBounds(126, 352, 140, 25);
        btnUpdateEmail.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // addEvent();
                // changeFirstName();
                changeEmail();
            }

        });
        add(btnUpdateEmail);

        JButton btnShowPersonalInformation = new JButton("Show Personal Information");
        btnShowPersonalInformation.setBounds(403, 13, 192, 25);
        DefaultListModel model = new DefaultListModel();
        btnShowPersonalInformation.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // addEvent();
                // changePassword();
                System.out.println("see if there");
                model.clear();
                ArrayList<String> info = showPersonalInformation();
                for (String k : info) {
                    // System.out.println(k);
                    model.addElement(k);
                }

            }

        });
        add(btnShowPersonalInformation);
        JList<String> list = new JList<String>(model);
        list.setFont(new Font("Tahoma", Font.PLAIN, 20));
        list.setBounds(519, 102, 192, 188);
        add(list);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(403, 51, 192, 188);

        add(scrollPane);

        JLabel lblFirstname = new JLabel("FirstName");
        lblFirstname.setBounds(320, 51, 71, 22);
        add(lblFirstname);

        JLabel lblLastName = new JLabel("LastName");
        lblLastName.setBounds(320, 76, 60, 22);
        add(lblLastName);

        JLabel lblEmailAddress = new JLabel("Email Address");
        lblEmailAddress.setBounds(298, 111, 102, 27);
        add(lblEmailAddress);

    }

    private ArrayList<String> showPersonalInformation() {
        return this.uService.showPersonalInfo();

    }

    private boolean changePassword() {
        String newPassword = null;
        newPassword = String.valueOf(passwordField.getPassword());
        System.out.println("ready to change!");
        if (this.uService.changePassword(newPassword)) {
            // this.location.setText("");
            // this.dateAndTime.setText("");
            System.out.println("succesfull change password");
            return true;
        }
        return false;

    }

    private boolean changeFirstName() {
        String newPassword = null;
        newPassword = textField.getText();
        System.out.println("ready to change!");
        if (this.uService.changeFirst(newPassword)) {
            // this.location.setText("");
            // this.dateAndTime.setText("");
            System.out.println("succesfull change First Name");
            return true;
        }
        return false;

    }

    private boolean changeLastName() {
        String newPassword = null;
        newPassword = textField_1.getText();
        System.out.println("ready to change!");
        if (this.uService.changeLast(newPassword)) {
            // this.location.setText("");
            // this.dateAndTime.setText("");
            System.out.println("succesfull change Last Name");
            return true;
        }
        return false;

    }

    private boolean changeEmail() {
        String newPassword = null;
        newPassword = textField_2.getText();
        System.out.println("ready to change!");
        if (this.uService.changeEmail(newPassword)) {
            // this.location.setText("");
            // this.dateAndTime.setText("");
            System.out.println("succesfull change Email");
            return true;
        }
        return false;

    }
}

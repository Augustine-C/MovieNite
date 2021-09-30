package panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import command.ICommand;
import command.PanelCommand;
import command.Switch;
import service.UserService;
import testtest.MenuSingleton;

public class LoginPanel extends TemplatePanel {
    private ShowEventPanel sePanel;
    private UserService userService;
    private static LoginPanel lPanel;

    public static LoginPanel getLoginPanel() {
        if (lPanel == null) {
            lPanel = new LoginPanel();
        }
        return lPanel;
    }

    public LoginPanel() {
        createPanel();
    }

    @Override
    public void setService() {
        this.userService = UserService.getUserService();
    }

    @Override
    public void init() {
        this.sePanel = ShowEventPanel.getShowEventPanel();
        setVisible(true);

        JLabel label = new JLabel("Username");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label.setBounds(290, 110, 104, 16);
        add(label);

        JTextField userNameText = new JTextField();
        userNameText.setColumns(10);
        userNameText.setBounds(290, 139, 309, 22);
        add(userNameText);

        JLabel label_1 = new JLabel("password");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label_1.setBounds(290, 203, 104, 16);
        add(label_1);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(393, 331, 97, 25);
        add(btnRegister);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(290, 236, 309, 26);
        add(passwordText);

        JButton button = new JButton("Login");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    if (userService.login(userNameText.getText(), String.valueOf(passwordText.getPassword()))) {
                        // UserSingleton.getUserSingleton().setUser(userNameText.getText());

                        MenuSingleton.getMenuSingleton().switchPanel(sePanel);
                    } else
                        System.out.println("Login failed");
                } catch (NullPointerException exp) {
                    JOptionPane.showMessageDialog(null, "Username or Password cannot be empty");
                    exp.printStackTrace();
                }
            }

        });
        button.setBounds(393, 293, 97, 25);
        add(button);

        btnRegister.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    if (userService.addUser(userNameText.getText(), null, null, null,
                            String.valueOf(passwordText.getPassword()))) {
                        System.out.println("add user");
                    }
                } catch (NullPointerException exp) {
                    JOptionPane.showMessageDialog(null, "Username or Password cannot be empty");
                    exp.printStackTrace();
                }
            }
        });
    }

}

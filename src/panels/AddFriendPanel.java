package panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import service.AddFriendService;

public class AddFriendPanel extends JPanel {
    public AddFriendService afService = null;
    JTextField friendUsername = null;

    public AddFriendPanel(AddFriendService afService) {
        this.afService = afService;
        setBounds(0, 24, 882, 529);
        setLayout(null);
        setVisible(true);
        setLayout(null);
        JLabel label = new JLabel("Add Friend:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 15));
        label.setBounds(290, 110, 104, 16);
        add(label);

        this.friendUsername = new JTextField();
        this.friendUsername.setColumns(10);
        this.friendUsername.setBounds(290, 139, 309, 22);
        add(this.friendUsername);

        JButton button = new JButton("Add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                addFriend();
            }

        });
        button.setBounds(391, 290, 97, 25);
        add(button);

    }

    private boolean addFriend() {
        String friend = null;
        friend = this.friendUsername.getText();
        if (friend.isEmpty() || friend == null) {
            System.out.println("friend user name cannot be null");
            return false;
        }
        System.out.println(friend);
        if (this.afService.addFriend(friend)) {
            this.friendUsername.setText("");
            return true;
        }
        return false;

    }
}

package id.co.lba;

import javax.swing.*;

public class Login extends JFrame{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton loginButton;
    private JCheckBox showPasswordCheckBox;
    private JPanel loginPanel;

    public Login() {
        erp000p01Initialize();
    }

    private void erp000p01Initialize() {
        add(this.loginPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

package id.co.lba;

import LBAJXLibrariesV1.dto.DtoParameter;
import LBAJXLibrariesV1.dto.DtoResponse;
import id.co.lba.mo.erp.app000.service.Erp000Service;
import id.co.lba.mo.erp.app000.view.Erp000View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Login extends JFrame{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton loginButton;
    private JCheckBox showPasswordCheckBox;
    private JPanel loginPanel;

    public Login() {
        erp000p01Initialize();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DtoParameter param = new DtoParameter();
                Map<String, Object> searchMap = new HashMap<>();
                searchMap.put("vUsername", txtUsername.getText());
                searchMap.put("vPassword", txtPassword.getText());
                param.setSearch(searchMap);
                try {
                    DtoResponse response = Erp000Service.verifyUser(param);
                    JOptionPane.showMessageDialog(null, response.getMessage(), "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    if(response.getData().get(0) != null) {
                        dispose();
                        new Erp000View().setVisible(true);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected() == true) {
                    txtPassword.setEchoChar('\0');
                } else {
                    txtPassword.setEchoChar('*');
                }
            }
        });
    }

    private void erp000p01Initialize() {
        add(this.loginPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

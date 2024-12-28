package id.co.lba;

import id.co.lba.mo.erp.app000.view.Erp000View;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class LbaApplication {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            JFrame frame = null;
            frame = new Login();
            frame.setVisible(true);
		});
	}
}

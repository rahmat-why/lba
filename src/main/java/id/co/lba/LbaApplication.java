package id.co.lba;

import id.co.lba.mo.erp.app001.view.Erp001View;
import id.co.lba.mo.erp.app002.view.Erp002View;
import id.co.lba.mo.erp.app003.view.Erp003View;
import id.co.lba.mo.erp.app004.view.Erp004View;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

@SpringBootApplication
public class LbaApplication {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            JFrame frame = null;
            try {
                frame = new Erp003View();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(true);
		});
	}
}

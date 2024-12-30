package id.co.lba.mo.erp.app000.view;

import id.co.lba.mo.erp.app001.view.Erp001View;
import id.co.lba.mo.erp.app002.view.Erp002View;
import id.co.lba.mo.erp.app003.view.Erp003View;
import id.co.lba.mo.erp.app004.view.Erp004View;
import id.co.lba.mo.erp.app005.view.Erp005View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Erp000View extends JFrame{
    private JButton erp000BtnMenu1;
    private JButton erp000BtnMenu2;
    private JButton erp000BtnMenu3;
    private JButton erp000BtnMenu4;
    private JButton erp000BtnMenu5;
    private JPanel erp000p02Panel;
    private JPanel erp000p01Panel;

    public Erp000View() {
        erp000p01Initialize();
        erp000BtnMenu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp000p02Panel.removeAll();
                erp000p02Panel.revalidate();
                erp000p02Panel.repaint();
                Erp001View view = null;
                try {
                    view = new Erp001View();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                view.erp001p01Panel.setVisible(true);
                erp000p02Panel.revalidate();
                erp000p02Panel.setLayout(new java.awt.BorderLayout());
                erp000p02Panel.add(view.erp001p01Panel);
            }
        });
        erp000BtnMenu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp000p02Panel.removeAll();
                erp000p02Panel.revalidate();
                erp000p02Panel.repaint();
                Erp002View view = null;
                try {
                    view = new Erp002View();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                view.erp002p01Panel.setVisible(true);
                erp000p02Panel.revalidate();
                erp000p02Panel.setLayout(new java.awt.BorderLayout());
                erp000p02Panel.add(view.erp002p01Panel);
            }
        });
        erp000BtnMenu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp000p02Panel.removeAll();
                erp000p02Panel.revalidate();
                erp000p02Panel.repaint();
                Erp003View view = null;
                try {
                    view = new Erp003View();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                view.erp003p01Panel.setVisible(true);
                erp000p02Panel.revalidate();
                erp000p02Panel.setLayout(new java.awt.BorderLayout());
                erp000p02Panel.add(view.erp003p01Panel);
            }
        });
        erp000BtnMenu4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp000p02Panel.removeAll();
                erp000p02Panel.revalidate();
                erp000p02Panel.repaint();
                Erp004View view = null;
                try {
                    view = new Erp004View();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                view.erp004p01Panel.setVisible(true);
                erp000p02Panel.revalidate();
                erp000p02Panel.setLayout(new java.awt.BorderLayout());
                erp000p02Panel.add(view.erp004p01Panel);
            }
        });
        erp000BtnMenu5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erp000p02Panel.removeAll();
                erp000p02Panel.revalidate();
                erp000p02Panel.repaint();
                Erp005View view = null;
                try {
                    view = new Erp005View();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                view.erp005p01Panel.setVisible(true);
                erp000p02Panel.revalidate();
                erp000p02Panel.setLayout(new java.awt.BorderLayout());
                erp000p02Panel.add(view.erp005p01Panel);
            }
        });
    }

    private void erp000p01Initialize() {
        add(this.erp000p01Panel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

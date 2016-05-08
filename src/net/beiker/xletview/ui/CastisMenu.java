package net.beiker.xletview.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jjangpil on 2016-05-08.
 */
public class CastisMenu extends JMenu implements ActionListener {
    public CastisMenu() {
        super("Data");
        addSubMenu();
    }

    public void addSubMenu() {
        JMenuItem menuItem;
        menuItem = new JMenuItem("STB Setting");
        menuItem.setActionCommand("config");
        menuItem.addActionListener(this);
        add(menuItem);
        addSeparator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

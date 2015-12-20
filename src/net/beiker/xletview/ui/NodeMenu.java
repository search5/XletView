/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.ui;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class NodeMenu extends JPopupMenu {

    JTree tree;
    TreeNode node;

    public NodeMenu(JTree tree) {
        this.tree = tree;
        node = null;
        createMenu();
    }

    private void createMenu() {
        JMenuItem rename = new JMenuItem("Rename node");
        rename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // rename the node
            }
        });
        add(rename);
        JMenuItem delete = new JMenuItem("Delete node");
        rename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // delete the node
            }
        });
        add(delete);
        
    }

    public void setNode(TreeNode nd) {
        node = nd;
    }
}
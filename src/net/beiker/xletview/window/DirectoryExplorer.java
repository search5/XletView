/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.window;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.beiker.xletview.ui.tree.CellRenderer;
import net.beiker.xletview.ui.tree.BeikerTreeNode;
import net.beiker.xletview.ui.tree.UserObjectImpl;
import net.beiker.xletview.util.Debug;
import net.beiker.xletview.util.Util;

import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class DirectoryExplorer extends JDialog implements TreeSelectionListener, TreeExpansionListener, ActionListener {

    //private JDialog frame;
    private JTree tree;
    private DefaultTreeModel model;
    private JLabel pathLabel;
    private JButton okButton;
    private JButton cancelButton;
    private String chosenPath;

    public static void main(String[] argv) {
        //        String[] s = { "C:\\", "progs", "editplus" };
        //        new DirectoryExplorer(s);
    }

    public DirectoryExplorer(Frame owner, String[] path) {
        super(owner, "Application's root", true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            	doClose();
            }
        });

        // root
        File[] roots = File.listRoots();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Computer");

        for (int i = 0; i < roots.length; i++) {
            BeikerTreeNode node = new BeikerTreeNode(new UserObjectImpl(roots[i]));
            root.add(node);
        }

        //TreeNode root = new TreeNode("kamel");

        // tree
        tree = new JTree(root);
        tree.setRootVisible(true);

        TreeCellRenderer renderer = new CellRenderer();
        tree.setCellRenderer(renderer);

        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        tree.setEditable(false);

        // get the model
        model = (DefaultTreeModel) tree.getModel();

        // add listener to tree
        tree.addTreeSelectionListener(this);
        tree.addTreeExpansionListener(this);

        //        frame.getContentPane().add(new JScrollPane(tree));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);

        pathLabel = new JLabel(" ");
        pathLabel.setPreferredSize(new Dimension(1, 20));
        getContentPane().add(pathLabel, BorderLayout.NORTH);

        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout());

        okButton = new JButton("OK");
        okButton.setActionCommand("ok");
        okButton.addActionListener(this);

        cancelButton = new JButton("CANCEL");
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);

        navPanel.add(cancelButton);
        navPanel.add(okButton);

        getContentPane().add(navPanel, BorderLayout.SOUTH);

        expand(path);
        setSize(400, 400);

        Util.center(this);

        show();

    }

    private boolean expand(String[] path) {
        boolean result = false;
        int nextSearchRow = 0;
        TreePath tp = null;
        TreePath tmp = null;
        for (int i = 0; i < path.length; i++) {
            tp = tree.getNextMatch(path[i], nextSearchRow, Position.Bias.Forward);
            if (tp != null) {
                tree.expandPath(tp);
                Debug.write(this, "match");
                nextSearchRow = tree.getRowForPath(tp);
                tmp = tp;
            }
            else {
                break;
            }
        }
        if (tp != null) {
            tree.setSelectionPath(tp);
            tree.scrollPathToVisible(tp);
            result = true;
        }
        else if (tmp != null) {
            tree.setSelectionPath(tmp);
            tree.scrollPathToVisible(tmp);
            result = false;
        }
        else {
            result = false;
        }
        return result;
    }

    public void valueChanged(TreeSelectionEvent event) {
        pathChanged(event.getPath());
    }

    public void pathChanged(TreePath path) {
        Debug.write(this, path.toString());
        String filePath = "";
        for (int i = 1; i < path.getPathCount(); i++) {
            String s = path.getPathComponent(i).toString();
            int indexOfSeparator = s.indexOf(File.separator);
            if (indexOfSeparator > -1 && indexOfSeparator == s.length() - 1) {
                s = s.substring(0, s.length() - 1);
            }
            filePath += s + File.separator;
        }
        chosenPath = filePath;
        pathLabel.setText(filePath);
    }

    public void treeExpanded(TreeExpansionEvent event) {
        //Debug.write(this, "expanded");
        TreePath treePath = event.getPath();
        Object lastInPath = treePath.getLastPathComponent();
        if (lastInPath instanceof BeikerTreeNode) {
            final BeikerTreeNode treeNode = (BeikerTreeNode) lastInPath;
            //Debug.write(this, treeNode.getUserObject().toString());

            //treeNode.expand();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Cursor cursor = new Cursor(Cursor.WAIT_CURSOR);
                    tree.setCursor(cursor);
                    treeNode.expand();
                    model.reload(treeNode);
                    tree.setCursor(Cursor.getDefaultCursor());
                }
            });

        }
    }

    public void treeCollapsed(TreeExpansionEvent event) {

    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        if (command.equals("cancel")) {
            chosenPath = "";
            doClose();
        }
        else if (command.equals("ok")) {
            doClose();
        }
    }

    public String getPath() {
        return chosenPath;
    }

    private void doClose() {
        dispose();
        //        System.exit(0);
    }

}

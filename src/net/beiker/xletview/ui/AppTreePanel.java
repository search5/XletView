/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.dnd.peer.*;

import net.beiker.xletview.ui.tree.*;
import net.beiker.xletview.app.App;
import net.beiker.xletview.app.AppGroup;
import net.beiker.xletview.app.AppManager;
import net.beiker.xletview.util.Constants;
import net.beiker.xletview.util.Debug;

public class AppTreePanel extends JPanel implements TreeSelectionListener, TreeExpansionListener/*, DragGestureListener, DropTargetListener, DragSourceListener */{

    protected JTree tree;
    protected DefaultTreeModel model;
    protected JTextField display;
    private DefaultMutableTreeNode selectedNode;
    private TreePath selectedPath;

    protected JPopupMenu popup;
    protected Action action;
    private Vector listeners;

//    private DragSource dragSource = null;
//    private DragSourceContext dragSourceContext = null;

    private AppTreePanel() {
        listeners = new Vector();
        setLayout(new BorderLayout());
    }

    public AppTreePanel(AppGroup parentGroup) {
        this();

        //DefaultMutableTreeNode root = new DefaultMutableTreeNode("Applications");
        //BeikerTreeNode node = new BeikerTreeNode(parentGroup);
        //root.add(node);

        BeikerTreeNode root = new BeikerTreeNode(parentGroup);
        root.expand();
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

        JScrollPane s = new JScrollPane();
        s.getViewport().add(tree);
        add(s, BorderLayout.CENTER);

        /* 
        // drag n drop stuff not yet implemented
         
        dragSource = DragSource.getDefaultDragSource();
        DragGestureRecognizer dgr = dragSource.createDefaultDragGestureRecognizer(tree, //DragSource
        DnDConstants.ACTION_COPY_OR_MOVE, this);
        DropTarget dropTarget = new DropTarget(tree, this);
        */

    }

    public DefaultTreeModel getModel() {
        return model;
    }

    public void treeExpanded(TreeExpansionEvent event) {
        //Debug.write(this, "expanded");
        TreePath treePath = event.getPath();
        Object lastInPath = treePath.getLastPathComponent();
        if (lastInPath instanceof BeikerTreeNode) {
            final BeikerTreeNode treeNode = (BeikerTreeNode) lastInPath;
            //Debug.write(this, treeNode.getUserObject().toString());
            treeNode.expand();
            model.reload(treeNode);

        }
    }

    public void treeCollapsed(TreeExpansionEvent event) {
    }

    public void valueChanged(TreeSelectionEvent event) {
        selectedPath = event.getPath();
        notifyTreeListeners(selectedPath);
    }

    public DefaultMutableTreeNode getSelectedNode() {
        return (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
    }

    /**
     * Adds an AppGroup in the tree as a child to the 
     * selected group.
     * @param group the AppGroup to be added
     */
    public void insertGroup(AppGroup group) {
        Debug.write(this, "insert group");
        if (selectedPath != null) {
            BeikerTreeNode node = (BeikerTreeNode) selectedPath.getLastPathComponent();
            TreePath selectedPath = new TreePath(node.getPath());
            Object userObject = node.getUserObject();
            Object object = ((UserObject) userObject).getObject();
            Debug.write(this, "userObject is " + userObject.getClass().getName());

            if (object instanceof AppGroup) {
                Debug.write(this, "userObject is AppGroup");
                AppGroup parentGroup = (AppGroup) object;
                parentGroup.addChild(group);
                BeikerTreeNode childNode = new BeikerTreeNode(group);
                model.insertNodeInto(childNode, node, node.getChildCount());
                tree.scrollPathToVisible(new TreePath(childNode.getPath()));
            }
        }
    }

    /**
     * Adds an App in the tree as a child to the 
     * selected group.
     * @param group the App to be added
     */
    public void insertApp(App app) {
        Debug.write(this, "insert app");
        if (selectedPath != null) {
            BeikerTreeNode node = (BeikerTreeNode) selectedPath.getLastPathComponent();

            Object userObject = node.getUserObject();
            Object object = ((UserObject) userObject).getObject();
            Debug.write(this, "userObject is " + userObject.getClass().getName());

            if (object instanceof AppGroup) {
                Debug.write(this, "userObject is AppGroup");
                AppGroup parentGroup = (AppGroup) object;
                parentGroup.addApp(app);

                BeikerTreeNode childNode = new BeikerTreeNode(app);
                model.insertNodeInto(childNode, node, node.getChildCount());
                tree.scrollPathToVisible(new TreePath(childNode.getPath()));
                //tree.setSelectionPath(new TreePath(childNode.getPath()));
            }
        }
    }

    public void removeSelected() {
        if (selectedPath != null) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();

            Object userObject = node.getUserObject();

            Debug.write(this, "userObject is " + userObject.getClass().getName());
            if (userObject instanceof UserObject) {
                Object object = ((UserObject) userObject).getObject();
                if (object != AppManager.getInstance().getDefaultGroup()) {
                    // get parent
                    BeikerTreeNode parent = (BeikerTreeNode) node.getParent();

                    // scroll to parent
                    tree.scrollPathToVisible(new TreePath(parent.getPath()));

                    // select parent 
                    tree.setSelectionPath(new TreePath(parent.getPath()));

                    // remove from tree
                    model.removeNodeFromParent(node);

                    Object childObject = object;
                    Object parentObject = ((UserObject) parent.getUserObject()).getObject();

                    // check and remove sub group
                    if (childObject instanceof AppGroup) {
                        ((AppGroup) parentObject).removeChild((AppGroup) childObject);
                    }
                    // check and remove app
                    else if (childObject instanceof App) {
                        ((AppGroup) parentObject).removeApp((App) childObject);
                    }
                }
                else {
                    Debug.write(this, "it's not possible to delete default group");
                }
            }
        }
    }

    public void addTreeListener(TreeListener listener) {
        listeners.add(listener);
    }

    public void removeTreeListener(TreeListener listener) {
        listeners.remove(listener);
    }

    public void notifyTreeListeners(TreePath path) {
        for (int i = 0; i < listeners.size(); i++) {
            ((TreeListener) listeners.get(i)).pathChanged(path);
        }
    }



    /*
        Drag n Drop is not yet implemented
     */

    /* (non-Javadoc)
     * @see java.awt.dnd.DragGestureListener#dragGestureRecognized(java.awt.dnd.DragGestureEvent)
     */
    public void dragGestureRecognized(DragGestureEvent e) {
        Object obj = e.getSource();
        Debug.write(this, "dragGestureRecognized, " + obj.getClass().getName());
        
        DefaultMutableTreeNode dragNode = getSelectedNode();
        if(dragNode != null){
            Transferable transferable = (Transferable) dragNode.getUserObject();

            //Select the appropriate cursor;
            Cursor cursor = DragSource.DefaultCopyNoDrop;
            int action = e.getDragAction();
            if (action == DnDConstants.ACTION_MOVE){ 
                cursor = DragSource.DefaultMoveNoDrop;
            }
            //begin the drag
           // dragSource.startDrag(e, cursor, transferable, this);
        }
    }

//    /* (non-Javadoc)
//     * @see java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent)
//     */
//    public void dragEnter(DropTargetDragEvent e) {
//        Object obj = e.getSource();
//        Debug.write(this, "dragEnter, " + obj.getClass().getName());
//    }
//
//    /* (non-Javadoc)
//     * @see java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent)
//     */
//    public void dragOver(DropTargetDragEvent e) {
//        Object obj = e.getSource();
//        Debug.write(this, "dragOver, " + obj.getClass().getName());
//    }
//
//    /* (non-Javadoc)
//     * @see java.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.DropTargetDragEvent)
//     */
//    public void dropActionChanged(DropTargetDragEvent e) {
//        Object obj = e.getSource();
//        Debug.write(this, "dropActionChanged, " + obj.getClass().getName());
//    }
//
//    /* (non-Javadoc)
//     * @see java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
//     */
//    public void dragExit(DropTargetEvent e) {
//        Object obj = e.getSource();
//        Debug.write(this, "dragExit, " + obj.getClass().getName());
//    }
//
//    /* (non-Javadoc)
//     * @see java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
//     */
//    public void drop(DropTargetDropEvent e) {
//        Object obj = e.getSource();
//        Debug.write(this, "drop, " + obj.getClass().getName());
//    }
//
//    /* (non-Javadoc)
//     * @see java.awt.dnd.DragSourceListener#dragEnter(java.awt.dnd.DragSourceDragEvent)
//     */
//    public void dragEnter(DragSourceDragEvent e) {
//        Object obj = e.getSource();
//        Debug.write(this, "dragEnter, " + obj.getClass().getName());
//    }
//
//    /* (non-Javadoc)
//     * @see java.awt.dnd.DragSourceListener#dragOver(java.awt.dnd.DragSourceDragEvent)
//     */
//    public void dragOver(DragSourceDragEvent e) {
//        Object obj = e.getSource();
//        Debug.write(this, "dragOver, " + obj.getClass().getName());
//    }
//
//    /* (non-Javadoc)
//     * @see java.awt.dnd.DragSourceListener#dropActionChanged(java.awt.dnd.DragSourceDragEvent)
//     */
//    public void dropActionChanged(DragSourceDragEvent e) {
//        Object obj = e.getSource();
//        Debug.write(this, "dropActionChanged, " + obj.getClass().getName());
//    }
//
//    /* (non-Javadoc)
//     * @see java.awt.dnd.DragSourceListener#dragExit(java.awt.dnd.DragSourceEvent)
//     */
//    public void dragExit(DragSourceEvent e) {
//        Object obj = e.getSource();
//        Debug.write(this, "dragExit, " + obj.getClass().getName());
//    }
//
//    /* (non-Javadoc)
//     * @see java.awt.dnd.DragSourceListener#dragDropEnd(java.awt.dnd.DragSourceDropEvent)
//     */
//    public void dragDropEnd(DragSourceDropEvent e) {
//        Object obj = e.getSource();
//        Debug.write(this, "dragDropEnd, " + obj.getClass().getName());
//    }

}

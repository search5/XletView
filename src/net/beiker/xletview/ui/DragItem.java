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
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import net.beiker.xletview.util.Debug;

/**
 * @author Martin Sveden
 *
 */
public class DragItem extends JComponent {

    private DragSource dragSource;
    private DragGestureListener dgListener;
    private DragSourceListener dsListener;
    private DropTarget dropTarget;
    private DropTargetListener dtListener;
    private int dragAction = DnDConstants.ACTION_COPY;
    private int acceptableActions = DnDConstants.ACTION_COPY;
    private Color borderColor;

    public DragItem() {
        this.dragSource = DragSource.getDefaultDragSource();
        this.dgListener = new DGListener();
        this.dsListener = new DSListener();
        setTransferHandler(new TransferHandler("text"));
        MouseListener listener = new DragMouseAdapter();
        addMouseListener(listener);

        this.acceptableActions = DnDConstants.ACTION_COPY;
        this.borderColor = Color.green;
        this.dtListener = new DTListener();

        // component, ops, listener, accepting
        this.dropTarget = new DropTarget(this, this.acceptableActions, this.dtListener, true);
    }

    public class DragMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
            Debug.write(this, "mousePressed");
        }
    }

    /**
     * DGListener
     * a listener that will start the drag.
     * has access to top level's dsListener and dragSource
     * @see java.awt.dnd.DragGestureListener
     * @see java.awt.dnd.DragSource
     * @see java.awt.datatransfer.StringSelection      
     */
    class DGListener implements DragGestureListener {
        /**
         * Start the drag if the operation is ok.
         * uses java.awt.datatransfer.StringSelection to transfer
         * the label's data
         * @param e the event object
         */
        public void dragGestureRecognized(DragGestureEvent e) {

            // if the action is ok we go ahead
            // otherwise we punt
            System.out.println(e.getDragAction());
            if ((e.getDragAction() & DragItem.this.dragAction) == 0)
                return;
            System.out.println("kicking off drag");

            // get the label's text and put it inside a Transferable
            // Transferable transferable = new StringSelection( DragLabel.this.getText() );
            Transferable transferable = new StringTransferable("text");

            // now kick off the drag
            try {
                // initial cursor, transferrable, dsource listener      
                e.startDrag(DragSource.DefaultCopyNoDrop, transferable, DragItem.this.dsListener);

                // or if dragSource is a variable
                // dragSource.startDrag(e, DragSource.DefaultCopyDrop, transferable, dsListener);

                // or if you'd like to use a drag image if supported

                /*
                  if(DragSource.isDragImageSupported() )
                  // cursor, image, point, transferrable, dsource listener    
                  e.startDrag(DragSource.DefaultCopyDrop, image, point, transferable, dsListener);
                */

            }
            catch (InvalidDnDOperationException idoe) {
                System.err.println(idoe);
            }
        }
    }

    /**
     * DSListener
     * a listener that will track the state of the DnD operation
     * 
     * @see java.awt.dnd.DragSourceListener
     * @see java.awt.dnd.DragSource
     * @see java.awt.datatransfer.StringSelection      
     */
    class DSListener implements DragSourceListener {

        /**
         * @param e the event
         */
        public void dragDropEnd(DragSourceDropEvent e) {
            if (e.getDropSuccess() == false) {
                System.out.println("not successful");
                return;
            }

            /*
             * the dropAction should be what the drop target specified
             * in acceptDrop
             */
            System.out.println("dragdropend action " + e.getDropAction());

            // this is the action selected by the drop target
            if (e.getDropAction() == DnDConstants.ACTION_MOVE)
                Debug.write(this, "move action");
        }

        /**
         * @param e the event
         */
        public void dragEnter(DragSourceDragEvent e) {
            System.out.println("draglabel enter " + e);
            DragSourceContext context = e.getDragSourceContext();
            //intersection of the users selected action, and the source and target actions
            int myaction = e.getDropAction();
            if ((myaction & DragItem.this.dragAction) != 0) {
                context.setCursor(DragSource.DefaultCopyDrop);
            }
            else {
                context.setCursor(DragSource.DefaultCopyNoDrop);
            }
        }
        /**
         * @param e the event
         */
        public void dragOver(DragSourceDragEvent e) {
            DragSourceContext context = e.getDragSourceContext();
            int sa = context.getSourceActions();
            int ua = e.getUserAction();
            int da = e.getDropAction();
            int ta = e.getTargetActions();
            System.out.println("dl dragOver source actions" + sa);
            System.out.println("user action" + ua);
            System.out.println("drop actions" + da);
            System.out.println("target actions" + ta);
        }
        /**
         * @param e the event
         */
        public void dragExit(DragSourceEvent e) {
            System.out.println("draglabel exit " + e);
            DragSourceContext context = e.getDragSourceContext();
        }

        /**
         * for example, press shift during drag to change to
         * a link action
         * @param e the event     
         */
        public void dropActionChanged(DragSourceDragEvent e) {
            DragSourceContext context = e.getDragSourceContext();
            context.setCursor(DragSource.DefaultCopyNoDrop);
        }
    }

    /**
     * DTListener
     * a listener that tracks the state of the operation
     * @see java.awt.dnd.DropTargetListener
     * @see java.awt.dnd.DropTarget
     */
    class DTListener implements DropTargetListener {

        /**
         * Called by isDragOk
         * Checks to see if the flavor drag flavor is acceptable
         * @param e the DropTargetDragEvent object
         * @return whether the flavor is acceptable
         */
        private boolean isDragFlavorSupported(DropTargetDragEvent e) {
            boolean ok = false;
            if (e.isDataFlavorSupported(StringTransferable.plainTextFlavor)) {
                ok = true;
            }
            else if (e.isDataFlavorSupported(StringTransferable.localStringFlavor)) {
                ok = true;
            }
            else if (e.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                ok = true;
            }
            else if (e.isDataFlavorSupported(DataFlavor.plainTextFlavor)) {
                ok = true;
            }
            return ok;
        }
        /**
         * Called by drop
         * Checks the flavors and operations
         * @param e the DropTargetDropEvent object
         * @return the chosen DataFlavor or null if none match
         */
        private DataFlavor chooseDropFlavor(DropTargetDropEvent e) {
            if (e.isLocalTransfer() == true && e.isDataFlavorSupported(StringTransferable.localStringFlavor)) {
                return StringTransferable.localStringFlavor;
            }
            DataFlavor chosen = null;
            if (e.isDataFlavorSupported(StringTransferable.plainTextFlavor)) {
                chosen = StringTransferable.plainTextFlavor;
            }
            else if (e.isDataFlavorSupported(StringTransferable.localStringFlavor)) {
                chosen = StringTransferable.localStringFlavor;
            }
            else if (e.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                chosen = DataFlavor.stringFlavor;
            }
            else if (e.isDataFlavorSupported(DataFlavor.plainTextFlavor)) {
                chosen = DataFlavor.plainTextFlavor;
            }
            return chosen;
        }

        /**
         * Called by dragEnter and dragOver
         * Checks the flavors and operations
         * @param e the event object
         * @return whether the flavor and operation is ok
         */
        private boolean isDragOk(DropTargetDragEvent e) {
            if (isDragFlavorSupported(e) == false) {
                System.out.println("isDragOk:no flavors chosen");
                return false;
            }

            // the actions specified when the source
            // created the DragGestureRecognizer
            //        int sa = e.getSourceActions();

            // the docs on DropTargetDragEvent rejectDrag says that
            // the dropAction should be examined
            int da = e.getDropAction();
            System.out.print("dt drop action " + da);
            System.out.println(" my acceptable actions " + acceptableActions);

            // we're saying that these actions are necessary      
            if ((da & DragItem.this.acceptableActions) == 0)
                return false;
            return true;
        }

        /**
         * start "drag under" feedback on component
         * invoke acceptDrag or rejectDrag based on isDragOk
         */
        public void dragEnter(DropTargetDragEvent e) {
            System.out.println("dtlistener dragEnter");
            if (isDragOk(e) == false) {
                System.out.println("enter not ok");
                DragItem.this.borderColor = Color.red;
                //showBorder(true);
                e.rejectDrag();
                return;
            }
            DragItem.this.borderColor = Color.green;
            //showBorder(true);
            System.out.println("dt enter: accepting " + e.getDropAction());
            e.acceptDrag(e.getDropAction());
        }

        /**
         * continue "drag under" feedback on component
         * invoke acceptDrag or rejectDrag based on isDragOk
         */
        public void dragOver(DropTargetDragEvent e) {
            if (isDragOk(e) == false) {
                System.out.println("dtlistener dragOver not ok");
                DragItem.this.borderColor = Color.red;
                //showBorder(true);
                e.rejectDrag();
                return;
            }
            System.out.println("dt over: accepting");
            e.acceptDrag(e.getDropAction());
        }

        public void dropActionChanged(DropTargetDragEvent e) {
            if (isDragOk(e) == false) {
                System.out.println("dtlistener changed not ok");
                e.rejectDrag();
                return;
            }
            System.out.println("dt changed: accepting" + e.getDropAction());
            e.acceptDrag(e.getDropAction());
        }

        public void dragExit(DropTargetEvent e) {
            System.out.println("dtlistener dragExit");
            DragItem.this.borderColor = Color.green;
            //showBorder(false);
        }

        /**
         * perform action from getSourceActions on
         * the transferrable
         * invoke acceptDrop or rejectDrop
         * invoke dropComplete
         * if its a local (same JVM) transfer, use StringTransferable.localStringFlavor
         * find a match for the flavor
         * check the operation
         * get the transferable according to the chosen flavor
         * do the transfer
         */
        public void drop(DropTargetDropEvent e) {
            System.out.println("dtlistener drop");

            DataFlavor chosen = chooseDropFlavor(e);
            if (chosen == null) {
                System.err.println("No flavor match found");
                e.rejectDrop();
                return;
            }
            System.err.println("Chosen data flavor is " + chosen.getMimeType());

            // the actual operation
            int da = e.getDropAction();
            // the actions that the source has specified with DragGestureRecognizer
            int sa = e.getSourceActions();
            System.out.println("drop: sourceActions: " + sa);
            System.out.println("drop: dropAction: " + da);

            if ((sa & DragItem.this.acceptableActions) == 0) {
                System.err.println("No action match found");
                e.rejectDrop();
                //showBorder(false);
                return;
            }

            Object data = null;
            try {
                /*
                 * the source listener receives this action in dragDropEnd.
                 * if the action is DnDConstants.ACTION_COPY_OR_MOVE then
                 * the source receives MOVE!
                 */
                e.acceptDrop(DragItem.this.acceptableActions);
                // e.acceptDrop(DnDConstants.ACTION_MOVE);
                //e.acceptDrop(DnDConstants.ACTION_COPY);
                //e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE); 

                data = e.getTransferable().getTransferData(chosen);
                if (data == null)
                    throw new NullPointerException();
            }
            catch (Throwable t) {
                System.err.println("Couldn't get transfer data: " + t.getMessage());
                t.printStackTrace();
                e.dropComplete(false);
                //showBorder(false);
                return;
            }
            System.out.println("Got data: " + data.getClass().getName());

            if (data instanceof String) {
                String s = (String) data;
                //DragItem.this.setText(s);
            }
            else if (data instanceof InputStream) {
                InputStream input = (InputStream) data;
                InputStreamReader isr = null;
                //    BufferedReader br = null;
                try {
                    //    br = new BufferedReader(isr=new InputStreamReader(input,"Unicode"));
                    isr = new InputStreamReader(input, "Unicode");
                }
                catch (UnsupportedEncodingException uee) {
                    isr = new InputStreamReader(input);
                }

                StringBuffer str = new StringBuffer();
                int in = -1;
                try {
                    while ((in = isr.read()) >= 0) {
                        //System.out.println("read: " + in);
                        if (in != 0)
                            str.append((char) in);
                    }

                    /* you get garbage chars this way
                       try {
                       String line=null;      
                       while( (line = br.readLine()) != null) {
                       str.append(line);
                       str.append('\n');        
                       System.out.println( "read: " + line);
                       System.out.println( "read: " +
                       (int)line.charAt(line.length()-1)); 
                       }
                    
                       br.close();
                    */
                    //DragItem.this.setText(str.toString());

                }
                catch (IOException ioe) {
                    /*
                      bug #4094987
                      sun.io.MalformedInputException: Missing byte-order mark
                      e.g. if dragging from MS Word 97
                      still a bug in 1.2 final
                    */

                    System.err.println("cannot read" + ioe);
                    e.dropComplete(false);
                    //showBorder(false);
                    String message = "Bad drop\n" + ioe.getMessage();
                    JOptionPane.showMessageDialog(DragItem.this, message, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }
            else {
                System.out.println("drop: rejecting");
                e.dropComplete(false);
                //showBorder(false);
                return;
            }

            e.dropComplete(true);
            //showBorder(false);
        }

        /*   
        public class DragItem extends JPanel{
            JTextField textField;
             JLabel label;
        
             public DragItem() {
                 super(new GridLayout(2, 1));
                 textField = new JTextField(40);
                 textField.setDragEnabled(true);
                 JPanel tfpanel = new JPanel(new GridLayout(1,1));
                 TitledBorder t1 = BorderFactory.createTitledBorder(
                    "JTextField: drag and drop is enabled");
                 tfpanel.add(textField);
                 tfpanel.setBorder(t1);
        
                 label = new JLabel("I'm a Label!",
                    SwingConstants.LEADING);
                 label.setTransferHandler(new TransferHandler("text"));
        
                 MouseListener listener = new DragMouseAdapter();
                 label.addMouseListener(listener);
                 JPanel lpanel = new JPanel(new GridLayout(1,1));
                 TitledBorder t2 = BorderFactory.createTitledBorder(
                    "JLabel: drag from or drop to this label");
                 lpanel.add(label);
                 lpanel.setBorder(t2);
        
                 add(tfpanel);
                 add(lpanel);
                 setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
             }
             
             public TransferHandler getTransferHandler(){
                 Debug.write(this, "getTransferHandler");
                 return super.getTransferHandler();
             }
        
             private class DragMouseAdapter extends MouseAdapter {
                 public void mousePressed(MouseEvent e) {
                     JComponent c = (JComponent)e.getSource();
                     TransferHandler handler = c.getTransferHandler();
                     handler.exportAsDrag(c, e, TransferHandler.COPY);
                     Debug.write(this, "mousePressed");
                 }
             }
        
             
        //      Create the GUI and show it.  For thread safety,
        //       this method should be invoked from the
        //       event-dispatching thread.
        //      
             private static void createAndShowGUI() {
                 //Make sure we have nice window decorations.
                 JFrame.setDefaultLookAndFeelDecorated(true);
        
                 //Create and set up the window.
                 JFrame frame = new JFrame("LabelDnD");
                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                 //Create and set up the content pane.
                 JComponent newContentPane = new DragItem();
                 newContentPane.setOpaque(true); //content panes must be opaque
                 frame.setContentPane(newContentPane);
        
                 //Display the window.
                 frame.pack();
                 frame.setVisible(true);
             }
        
             public static void main(String[] args) {
                 //Schedule a job for the event-dispatching thread:
                 //creating and showing this application's GUI.
                 javax.swing.SwingUtilities.invokeLater(new Runnable() {
                     public void run() {
                         createAndShowGUI();
                     }
                 });
             }
             */
    }
}

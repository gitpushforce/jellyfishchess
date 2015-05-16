/**
 * *****************************************************************************
 * Copyright (c) 2015, Thomas.H Warner. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * *****************************************************************************
 */

package fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.components;

import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui.constants.UIConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui.interfaces.Writable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextPane;

/**
 *
 * @author thw
 */
public class Console3D extends javax.swing.JFrame implements Writable {
        
    /**
     * 
     */
    private boolean userReadingOutput = false;
    
    /**
     * Creates new form Console3D
     */
    public Console3D() {
        
        initComponents();
        
        java.net.URL imgURL = getClass().getResource(UIConst.JELLYFISH_FRAME_ICON);
        javax.swing.ImageIcon img = new javax.swing.ImageIcon(imgURL);
        this.setIconImage(img.getImage());
        
        this.setLocation(0, 0);
        this.setVisible(true);
        //this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); 
        
        this.jScrollPane.getVerticalScrollBar().addMouseListener(
            new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    userReadingOutput = true;
                }

                @Override
                public void mousePressed(MouseEvent e) { 
                    userReadingOutput = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) { 
                    userReadingOutput = false;
                }

                @Override
                public void mouseEntered(MouseEvent e) { 
                    //userReadingOutput = true;
                }

                @Override
                public void mouseExited(MouseEvent e) { 
                    userReadingOutput = false;
                }
            } 
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuEdit = new javax.swing.JMenu();

        setTitle("console 3d UI");
        setName("console3dframe"); // NOI18N

        jScrollPane.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        textPane.setBackground(new java.awt.Color(0, 0, 0));
        textPane.setBorder(null);
        textPane.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        textPane.setForeground(new java.awt.Color(240, 240, 240));
        textPane.setDoubleBuffered(true);
        textPane.setSelectionColor(new java.awt.Color(100, 100, 100));
        jScrollPane.setViewportView(textPane);

        jMenuBar.setBorder(null);

        jMenuFile.setText("File");
        jMenuBar.add(jMenuFile);

        jMenuEdit.setText("Edit");
        jMenuBar.add(jMenuEdit);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
       
    @Override
    public boolean isUserReadingOutput() {
        return userReadingOutput;
    }
    
    @Override
    public JTextPane getTextPaneOutput() {
        return this.textPane;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables

}

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
 * ******************************************************************************
 */
package fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.components;

import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.interfaces.Writable;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.constants.UI3DConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.constants.UI3DCoordinateConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.helpers.KeyboardEventHelper;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.helpers.MouseEventHelper;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.helpers.OPENGLUIDriver;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.dto.Game3D;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.dto.RestartNewGame;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.constants.MiscConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.interfaces.MoveQueueObserver;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.constants.MessageTypeConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.interfaces.FenNotationObserver;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

/**
 *
 * @author thw
 */
public class Console3D extends javax.swing.JFrame implements Writable,
        MoveQueueObserver, FenNotationObserver {

    //<editor-fold defaultstate="collapsed" desc="vars">
    /**
     *
     */
    private boolean userReadingOutput = false;

    /**
     * Driver reference.
     */
    private OPENGLUIDriver driver = null;

    /**
     * Mouse event helper instance ref.
     */
    private MouseEventHelper mouseHelper = null;

    /**
     * Keyboard event helper instance ref.
     */
    private KeyboardEventHelper keyboardHelper = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructor">
    /**
     * Creates new form Console3D
     */
    public Console3D() {

        initComponents();

        java.net.URL imgURL = getClass().getResource(UI3DConst.JELLYFISH_FRAME_ICON);
        javax.swing.ImageIcon img = new javax.swing.ImageIcon(imgURL);
        this.setIconImage(img.getImage());

        this.setSize(this.getWidth(), UI3DCoordinateConst.WINDOW_HEIGHT + 39);
        this.setLocation(UI3DCoordinateConst.START_WINDOW_X + 16
                + UI3DCoordinateConst.WINDOW_WIDTH,
                UI3DCoordinateConst.START_WINDOW_Y);

        /**
         * Menu settings depending on game/user settings.
         */
        // Hint search activation ? :
        this.enableHintscheckBoxMenuItem.setSelected(Game3D.getInstance().isEnableHints());

        // Add listeners for console display edition.
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
                        userReadingOutput = true;
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        userReadingOutput = false;
                    }
                }
        );

        // Update status bar :
        updateStatus();

        this.enableHintscheckBoxMenuItem.setSelected(
                Game3D.getInstance().isEnableHints());
        this.displayAllOutputCheckBoxMenuItem.setSelected(
                Game3D.getInstance().isDisplayAllOutput());

        // Finally :
        this.setVisible(true);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Generated code - do not modify">
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        jScrollPane = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        tabbedPane = new javax.swing.JTabbedPane();
        gameHistoryPanel = new javax.swing.JPanel();
        gameHistoryScrollPane = new javax.swing.JScrollPane();
        moveHistoryTextPane = new javax.swing.JTextPane();
        gameFENPanel = new javax.swing.JPanel();
        fenHistoryScrollPane = new javax.swing.JScrollPane();
        fenHistoryTextPane = new javax.swing.JTextPane();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();
        displayAllOutputCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        undoMove = new javax.swing.JMenu();
        undoMoveMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        newGameWhitesMenuItem = new javax.swing.JMenuItem();
        newGameBlacksMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        enableHintscheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        hintResultMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        decreaseSearchDepthMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        pawnPromotionSettingsMenuItem = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("console - jellyfish");
        setName("console3dframe"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                consoleWindowClosing(evt);
            }
        });

        splitPane.setBorder(null);
        splitPane.setDividerLocation(340);
        splitPane.setDividerSize(8);
        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        textPane.setEditable(false);
        textPane.setBackground(new java.awt.Color(248, 248, 255));
        textPane.setBorder(null);
        textPane.setFont(new java.awt.Font("Meiryo", 0, 14)); // NOI18N
        textPane.setForeground(new java.awt.Color(51, 51, 51));
        textPane.setDoubleBuffered(true);
        textPane.setSelectionColor(new java.awt.Color(100, 100, 100));
        jScrollPane.setViewportView(textPane);

        splitPane.setTopComponent(jScrollPane);

        tabbedPane.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        gameHistoryPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        gameHistoryScrollPane.setBorder(null);
        gameHistoryScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        gameHistoryScrollPane.setDoubleBuffered(true);

        moveHistoryTextPane.setEditable(false);
        moveHistoryTextPane.setBackground(new java.awt.Color(248, 248, 255));
        moveHistoryTextPane.setBorder(null);
        moveHistoryTextPane.setFont(new java.awt.Font("Meiryo", 0, 14)); // NOI18N
        moveHistoryTextPane.setForeground(new java.awt.Color(51, 51, 51));
        moveHistoryTextPane.setDoubleBuffered(true);
        moveHistoryTextPane.setSelectionColor(new java.awt.Color(100, 100, 100));
        gameHistoryScrollPane.setViewportView(moveHistoryTextPane);

        javax.swing.GroupLayout gameHistoryPanelLayout = new javax.swing.GroupLayout(gameHistoryPanel);
        gameHistoryPanel.setLayout(gameHistoryPanelLayout);
        gameHistoryPanelLayout.setHorizontalGroup(
            gameHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
            .addGroup(gameHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(gameHistoryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
        );
        gameHistoryPanelLayout.setVerticalGroup(
            gameHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 223, Short.MAX_VALUE)
            .addGroup(gameHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(gameHistoryScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Move history", gameHistoryPanel);

        gameFENPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        gameFENPanel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        fenHistoryScrollPane.setBorder(null);
        fenHistoryScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        fenHistoryTextPane.setEditable(false);
        fenHistoryTextPane.setBackground(new java.awt.Color(248, 248, 255));
        fenHistoryTextPane.setBorder(null);
        fenHistoryTextPane.setFont(new java.awt.Font("Meiryo", 0, 14)); // NOI18N
        fenHistoryTextPane.setForeground(new java.awt.Color(51, 51, 51));
        fenHistoryTextPane.setDoubleBuffered(true);
        fenHistoryTextPane.setSelectionColor(new java.awt.Color(100, 100, 100));
        fenHistoryScrollPane.setViewportView(fenHistoryTextPane);

        javax.swing.GroupLayout gameFENPanelLayout = new javax.swing.GroupLayout(gameFENPanel);
        gameFENPanel.setLayout(gameFENPanelLayout);
        gameFENPanelLayout.setHorizontalGroup(
            gameFENPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fenHistoryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
        );
        gameFENPanelLayout.setVerticalGroup(
            gameFENPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fenHistoryScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        );

        tabbedPane.addTab("FEN history", gameFENPanel);

        splitPane.setRightComponent(tabbedPane);
        tabbedPane.getAccessibleContext().setAccessibleName("tabs  ");

        statusPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        statusLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        statusLabel.setAlignmentY(0.0F);
        statusLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 0));

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jMenuBar.setBorder(null);

        fileMenu.setText("File");
        jMenuBar.add(fileMenu);

        editMenu.setText("Edit");

        displayAllOutputCheckBoxMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        displayAllOutputCheckBoxMenuItem.setText("Display all engine output to console");
        displayAllOutputCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayAllOutputCheckBoxMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(displayAllOutputCheckBoxMenuItem);

        jMenuBar.add(editMenu);

        undoMove.setText("Game");

        undoMoveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMoveMenuItem.setText("Undo move");
        undoMoveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMoveMenuItemActionPerformed(evt);
            }
        });
        undoMove.add(undoMoveMenuItem);
        undoMove.add(jSeparator1);

        newGameWhitesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        newGameWhitesMenuItem.setText("New game playing whites");
        newGameWhitesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameWhitesMenuItemActionPerformed(evt);
            }
        });
        undoMove.add(newGameWhitesMenuItem);

        newGameBlacksMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        newGameBlacksMenuItem.setText("New game playing blacks");
        newGameBlacksMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameBlacksMenuItemActionPerformed(evt);
            }
        });
        undoMove.add(newGameBlacksMenuItem);
        undoMove.add(jSeparator2);

        enableHintscheckBoxMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        enableHintscheckBoxMenuItem.setSelected(true);
        enableHintscheckBoxMenuItem.setText("Enable hints");
        enableHintscheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableHintscheckBoxMenuItemActionPerformed(evt);
            }
        });
        undoMove.add(enableHintscheckBoxMenuItem);

        hintResultMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, 0));
        hintResultMenuItem.setText("Show hint result");
        hintResultMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hintResultMenuItemActionPerformed(evt);
            }
        });
        undoMove.add(hintResultMenuItem);
        undoMove.add(jSeparator3);

        decreaseSearchDepthMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SUBTRACT, java.awt.event.InputEvent.CTRL_MASK));
        decreaseSearchDepthMenuItem.setText("Decrease difficulty");
        decreaseSearchDepthMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseSearchDepthMenuItemActionPerformed(evt);
            }
        });
        undoMove.add(decreaseSearchDepthMenuItem);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ADD, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Increase difficulty");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseSearchDepthMenuItemActionPerformed(evt);
            }
        });
        undoMove.add(jMenuItem1);
        undoMove.add(jSeparator4);

        pawnPromotionSettingsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        pawnPromotionSettingsMenuItem.setText("Pawn promotion settings");
        pawnPromotionSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pawnPromotionSettingsMenuItemActionPerformed(evt);
            }
        });
        undoMove.add(pawnPromotionSettingsMenuItem);

        jMenuBar.add(undoMove);

        aboutMenu.setText("?");

        aboutMenuItem.setText("About jellyfish project");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        aboutMenu.add(aboutMenuItem);

        jMenuBar.add(aboutMenu);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane)
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Events">
    private void consoleWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_consoleWindowClosing
        this.setExtendedState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_consoleWindowClosing

    private void undoMoveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMoveMenuItemActionPerformed

        if (this.keyboardHelper != null) {
            KeyboardEventHelper.ConsoleEvents.force_ctrl_z = true;
        }
    }//GEN-LAST:event_undoMoveMenuItemActionPerformed

    private void newGameWhitesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameWhitesMenuItemActionPerformed
        if (this.driver == null) {
            return;
        }
        callNewGame(UI3DConst.COLOR_W_STR_VALUE, 500);
    }//GEN-LAST:event_newGameWhitesMenuItemActionPerformed

    private void newGameBlacksMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameBlacksMenuItemActionPerformed
        if (this.driver == null) {
            return;
        }
        callNewGame(UI3DConst.COLOR_B_STR_VALUE, 500);
    }//GEN-LAST:event_newGameBlacksMenuItemActionPerformed

    private void displayAllOutputCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayAllOutputCheckBoxMenuItemActionPerformed
        this.driver.getWriter().setDisplayAll(this.displayAllOutputCheckBoxMenuItem.isSelected());
        Game3D.getInstance().setDisplayAllOutput(this.displayAllOutputCheckBoxMenuItem.isSelected());
    }//GEN-LAST:event_displayAllOutputCheckBoxMenuItemActionPerformed

    private void enableHintscheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableHintscheckBoxMenuItemActionPerformed

        if (Game3D.getInstance().isEnableHints() && !this.enableHintscheckBoxMenuItem.isSelected()) {
            this.driver.stopHintSearch(true);
            Game3D.getInstance().setEnableHints(false);
            return;
        }

        Game3D.getInstance().setEnableHints(this.enableHintscheckBoxMenuItem.isSelected());
        this.driver.lauchHintSearch(true);
    }//GEN-LAST:event_enableHintscheckBoxMenuItemActionPerformed

    private void hintResultMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hintResultMenuItemActionPerformed

        if (!this.enableHintscheckBoxMenuItem.isSelected()) {
            Object[] options = new Object[]{"Enable hints", "No thanks"};
            int result = JOptionPane.showOptionDialog(this,
                    "Game hints are not yet enbaled...\n"
                    + "Press h to display hints on the board.\n",
                    "Hints are not enabled",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (result == 0) {

                // Enable hints :
                this.enableHintscheckBoxMenuItem.setSelected(true);
                Game3D.getInstance().setEnableHints(true);
                this.driver.lauchHintSearch(true);
            }

            return;
        }

        KeyboardEventHelper.ConsoleEvents.force_h = true;
    }//GEN-LAST:event_hintResultMenuItemActionPerformed

    private void decreaseSearchDepthMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseSearchDepthMenuItemActionPerformed

        if (Game3D.getInstance().isEngineSearching() || Game3D.getInstance().isEngineMoving()) {
            return;
        }

        int depth = Game3D.getInstance().getEngineSearchDepth();

        if (depth > 2) {
            --depth;
            Game3D.getInstance().setEngineSearchDepth(depth);
            this.driver.game.setDepth(depth);
            this.driver.getWriter().appendText(
                    String.format("Search depth set to %d\n", depth),
                    MessageTypeConst.INPUT_2, true);
        }

        updateStatus();
    }//GEN-LAST:event_decreaseSearchDepthMenuItemActionPerformed

    private void increaseSearchDepthMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseSearchDepthMenuItemActionPerformed

        if (Game3D.getInstance().isEngineSearching() || Game3D.getInstance().isEngineMoving()) {
            return;
        }

        int depth = Game3D.getInstance().getEngineSearchDepth();

        if (depth < 20) {
            ++depth;
            Game3D.getInstance().setEngineSearchDepth(depth);
            this.driver.game.setDepth(depth);
            this.driver.getWriter().appendText(
                    String.format("Search depth set to %d\n", depth),
                    MessageTypeConst.INPUT_2, true);
        }

        updateStatus();
    }//GEN-LAST:event_increaseSearchDepthMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed

        if (!driver.isPerforming()) {

            try {
                Desktop.getDesktop().browse(new URI(MiscConst.JELLYFISH_GITHUB_REPO));
            } catch (final URISyntaxException urisex) {
                Logger.getLogger(Console3D.class.getName()).log(Level.SEVERE, null, urisex);
            } catch (final IOException ioex) {
                Logger.getLogger(Console3D.class.getName()).log(Level.SEVERE, null, ioex);
            }
        }
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void pawnPromotionSettingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pawnPromotionSettingsMenuItemActionPerformed
        // TODO : dialog for choosing pawn promotion value : ex, q = queen, r = rook...
    }//GEN-LAST:event_pawnPromotionSettingsMenuItemActionPerformed
    //</editor-fold>   

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * @param color ui side.
     * @param sleepMS
     */
    private void callNewGame(final String color, final long sleepMS) {
        this.driver.getUiHelper().restart(
                new RestartNewGame(color, sleepMS, this.hintResultMenuItem.isSelected())
        );
    }

    /**
     * Update status bar.
     */
    private void updateStatus() {

        if (this.driver == null || this.driver.game == null) {
            this.statusLabel.setText(String.format("move n°%d - search depth: %s",
                    0, Game3D.getInstance().getEngineSearchDepth()));
            return;
        }

        this.statusLabel.setText(String.format("move n°%d - search depth: %s",
                this.driver.game.getMoveCount(),
                this.driver.game.getDepth().toString()
        ));
    }

    @Override
    public void notifyMove(final String moves) {
        this.moveHistoryTextPane.setText(moves);
        updateStatus();
    }

    @Override
    public void observeFEN(final String fen) {
        this.fenHistoryTextPane.setText(fen);
    }

    @Override
    public void clearOutput() {
        this.fenHistoryTextPane.setText("");
        this.moveHistoryTextPane.setText("");
    }
    
    @Override
    public void exit() {
        this.dispose();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & setters">
    public void setMouseHelper(final MouseEventHelper mouseHelper) {
        this.mouseHelper = mouseHelper;
    }

    public void setKeyboardHelper(final KeyboardEventHelper keyboardHelper) {
        this.keyboardHelper = keyboardHelper;
    }

    @Override
    public boolean isUserReadingOutput() {
        return userReadingOutput;
    }

    @Override
    public JTextPane getTextPaneOutput() {
        return this.textPane;
    }

    public void setDriver(final OPENGLUIDriver driver) {
        this.driver = driver;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Generatde vars - do not modify">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem decreaseSearchDepthMenuItem;
    private javax.swing.JCheckBoxMenuItem displayAllOutputCheckBoxMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JCheckBoxMenuItem enableHintscheckBoxMenuItem;
    private javax.swing.JScrollPane fenHistoryScrollPane;
    private javax.swing.JTextPane fenHistoryTextPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel gameFENPanel;
    private javax.swing.JPanel gameHistoryPanel;
    private javax.swing.JScrollPane gameHistoryScrollPane;
    private javax.swing.JMenuItem hintResultMenuItem;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JTextPane moveHistoryTextPane;
    private javax.swing.JMenuItem newGameBlacksMenuItem;
    private javax.swing.JMenuItem newGameWhitesMenuItem;
    private javax.swing.JMenuItem pawnPromotionSettingsMenuItem;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextPane textPane;
    private javax.swing.JMenu undoMove;
    private javax.swing.JMenuItem undoMoveMenuItem;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>

}

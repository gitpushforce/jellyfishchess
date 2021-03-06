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
 ******************************************************************************
 */
package fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.helpers;

import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.dto.Game3D;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.dto.Move;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.enums.ChessPositions;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.exceptions.ErroneousDTOMoveException;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.exceptions.FenValueException;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.constants.UI3DConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.gl3dobjects.ChessSquare;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.utils.ChessUtils;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.utils.DataUtils;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.utils.SoundUtils;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.time.StopWatch;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.constants.MessageTypeConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.exceptions.InvalidMoveException;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.exceptions.PawnPromotionException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thw
 */
public class ChessMoveHelper {
    
    //<editor-fold defaultstate="collapsed" desc="vars">
    /**
     * Singleton instance.
     */
    private static ChessMoveHelper instance = null;
    
    /**
     * OPENGLUIHelper instance.
     */
    private OPENGLUIHelper uiHelper = null;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="private constructor">
    /**
     * private costructor.
     */
    private ChessMoveHelper() { }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="methods">
    /**
     *
     * @param key ChessPositions
     * @param posFrom ChessPositions
     * @param posTo ChessSquare
     */
    void doMove(final ChessPositions key, final ChessPositions posFrom, 
            final ChessSquare posTo) {

        /**
         * Systematically set to false to enable display list deletion in gl
         * main loop.
         */
        Game3D.getInstance().setUndoingMoves(false);

        if (this.uiHelper.getBoard().getSelectedSquare() != null && 
                !Game3D.getInstance().isEngineMoving()) {

            // Pawn promotion.
            final boolean pawnPromotion
                    = ChessUtils.isPawnPromotionMove(this.uiHelper.getBoard().getSquareMap().get(posFrom),
                            posTo, Game3D.getInstance().getEngineOponentColorStringValue());

            try {

                // Stop hint seach if hints are enabled.
                this.uiHelper.driver.stopHintSearch(Game3D.getInstance().isEnableHints());
                new StopWatch(180).delay(null);

                if (this.uiHelper.driver.game.executeMove(
                        this.uiHelper.getBoard().getSelectedSquare().CHESS_POSITION.getStrPositionValueToLowerCase(),
                        key.getStrPositionValueToLowerCase(), true, pawnPromotion, 
                        Game3D.getInstance().getPawnPromotion())) {
                    
                    //Append move to queue for undoing.
                    Move m = new Move(this.uiHelper.driver.game.getMoveCount(), posFrom, key, false,
                        false, this.uiHelper.getBoard().getSelectedSquare().getModel(), posTo.getModel());

                    if (pawnPromotion) {
                        m.addPawnPromotionData(Game3D.getInstance().getPawnPromotion(), 
                                Game3D.getInstance().getEngineOponentColorStringValue());
                    }

                    this.uiHelper.driver.moveQueue.appendToEnd(m);

                    posTo.setColor(UI3DConst.UI_MOVE_SQUARE_COLOR);

                    if (pawnPromotion) {
                        this.uiHelper.getBoard().updateSquare(m.getPosTo(), m.getPosFrom(),
                                Game3D.getInstance().getEngineOponentColor(), m.getPawnPromotionObjPath(),
                                m.getPawnPromotionPieceType());
                    } else {
                        this.uiHelper.getBoard().updateSquare(key,
                                this.uiHelper.getBoard().getSelectedSquare().CHESS_POSITION,
                                Game3D.getInstance().getEngineOponentColor());
                    }

                    this.uiHelper.getBoard().setSelectedSquare(posTo);
                    this.uiHelper.getSoundManager().playEffect(SoundUtils.StaticSoundVars.move);
                    // If move is validated check & checkmate situation is impossible.
                    Game3D.getInstance().setUiCheck(false);
                    Game3D.getInstance().setUiCheckmate(false);
                    if (pawnPromotion) {
                        Game3D.getInstance().setEngineCheck(this.uiHelper.driver.game.inCheckSituation(
                                Game3D.getInstance().getEngineColorStringValue()));
                    }
                    
                    // finally engine will be searching after this :
                    Game3D.getInstance().setEngineSearching(true);
                    
                } else {
                    throw new InvalidMoveException(String.format("%s %s-%s is not a valid chess move.\n",
                            this.uiHelper.getBoard().getSelectedSquare().getModel().getType().toString(),
                            this.uiHelper.getBoard().getSelectedSquare().CHESS_POSITION.getStrPositionValue(),
                            key.getStrPositionValueToLowerCase()));
                }
            } catch (final PawnPromotionException ppex) {
                Logger.getLogger(ChessMoveHelper.class.getName()).log(Level.SEVERE, null, ppex);
            } catch (final InvalidMoveException imex) {
                this.uiHelper.driver.getWriter().appendText(imex.getMessage(), MessageTypeConst.ERROR, true);
                Logger.getLogger(ChessMoveHelper.class.getName()).log(Level.WARNING, null, imex);
            } catch (final FenValueException fvex) {
                Logger.getLogger(ChessMoveHelper.class.getName()).log(Level.SEVERE, null, fvex);
            } catch (final ErroneousDTOMoveException rdmex) {
                Logger.getLogger(ChessMoveHelper.class.getName()).log(Level.SEVERE, null, rdmex);
            }

        } else {
            this.notifyWrongTurn();
        }
    }
    
    /**
     * @param move
     * @return true if move is effective, false if reload process is ooo and must
     * be stopped and a new blank game must be loaded.
     */
    boolean reloadMove(final Move move) throws ErroneousDTOMoveException {
        
        try {
            
            new StopWatch(120).delay(null);

            if (this.uiHelper.driver.game.executeMove(move.getPosFrom().getStrPositionValueToLowerCase(),
                    move.getPosTo().getStrPositionValueToLowerCase(), false, move.isPawnPromotion(), 
                    move.isPawnPromotion() ? move.getPawnPromotionType() : 
                            Game3D.getInstance().getPawnPromotion())) {

                Move m = new Move(this.uiHelper.driver.game.getMoveCount(), move.getPosFrom(), 
                            move.getPosTo(), move.isEngineMove(), false,
                            this.uiHelper.getBoard().getSquare(move.getPosFrom()).getModel(),
                            this.uiHelper.getBoard().getSquare(move.getPosTo()).getModel());
                
                final String color = this.uiHelper.driver.game.getColorToPLay().equals(UI3DConst.COLOR_W_STR_VALUE) ?
                                   UI3DConst.COLOR_B_STR_VALUE : UI3DConst.COLOR_W_STR_VALUE;
                final float[] fColor = color.equals(UI3DConst.COLOR_W_STR_VALUE) ?
                        UI3DConst.COLOR_W : UI3DConst.COLOR_B;

                if (move.isPawnPromotion()) {
                    m.addPawnPromotionData(move.getPawnPromotionType(), color);
                }

                //Append move to queue for undoing.
                this.uiHelper.driver.moveQueue.appendToEnd(m);

                if (move.isPawnPromotion()) {
                    this.uiHelper.getBoard().updateSquare(m.getPosTo(), m.getPosFrom(),
                            fColor, m.getPawnPromotionObjPath(),
                            m.getPawnPromotionPieceType());
                } else {
                    this.uiHelper.getBoard().updateSquare(m.getPosTo(),
                            move.getPosFrom(), fColor);
                }
                return true;
            }
        } catch (final PawnPromotionException | FenValueException | NullPointerException ex) {
            Logger.getLogger(ChessMoveHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }

    /**
     * Notify Console for a 'wrong turn' error, oponent side must play first.
     */
    void notifyWrongTurn() {
        this.uiHelper.driver.getWriter().appendText(
                String.format("It is %s's side to take a move...\n", 
                        Game3D.getInstance().getEngineColorStringValue()),
                MessageTypeConst.ERROR, true);
    }
    
    /**
     * Singleton accessor.
     * @return 
     */
    static ChessMoveHelper getInstance() {
        
        if (ChessMoveHelper.instance == null) {
            ChessMoveHelper.instance = new ChessMoveHelper();
        }
        
        return ChessMoveHelper.instance;
    }
    
    /**
     * Set OPENGLUIHelper instance.
     * @param uiHelper 
     */
    void serOPENGLUIHelper (final OPENGLUIHelper uiHelper) {
        this.uiHelper = uiHelper;
    }
    //</editor-fold>
    
}

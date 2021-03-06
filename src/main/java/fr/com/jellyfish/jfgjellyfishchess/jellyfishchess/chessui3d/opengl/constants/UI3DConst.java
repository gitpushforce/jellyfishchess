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
package fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.constants;

import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.opengl.utils.ColorUtils;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.enums.ChessPositions;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thw
 */
public class UI3DConst {
    
    /**
     * Version & name.
     */
    public static final String JELLYFISH_VERSION = 
            "Jellyfish Chess - v0.1\nLatest build: august 2015\nRepository: " + MiscConst.JELLYFISH_GITHUB_REPO;
    
    /**
     * Frame icon path.
     */
    public static final String JELLYFISH_FRAME_ICON = "/icons/jellyfish.png";
    
    /**
     * Array of backgrounds for loader.
     */
    public static final String[] LOADER_BG_COLLECTION = new String [] {
        UI3DConst.JELLYFISH_LOADER_BG1,
        UI3DConst.JELLYFISH_LOADER_BG2,
        UI3DConst.JELLYFISH_LOADER_BG3,
        UI3DConst.JELLYFISH_LOADER_BG4,
        UI3DConst.JELLYFISH_LOADER_BG5,
        UI3DConst.JELLYFISH_LOADER_BG6
    }; 
            
    /**
     * Loader background image 1 for panel or label.
     */
    public static final String JELLYFISH_LOADER_BG1 = "/icons/loader-background1.png";
    
    /**
     * Loader background image 2 for panel or label.
     */
    public static final String JELLYFISH_LOADER_BG2 = "/icons/loader-background2.png";
    
    /**
     * Loader background image 3 for panel or label.
     */
    public static final String JELLYFISH_LOADER_BG3 = "/icons/loader-background3.png";
    
    /**
     * Loader background image 4 for panel or label.
     */
    public static final String JELLYFISH_LOADER_BG4 = "/icons/loader-background4.png";
    
    /**
     * Loader background image 5 for panel or label.
     */
    public static final String JELLYFISH_LOADER_BG5 = "/icons/loader-background5.png";
    
    /**
     * Loader background image 6 for panel or label.
     */
    public static final String JELLYFISH_LOADER_BG6 = "/icons/loader-background6.png";
    
    /**
     * 32x32 Frame icon url.
     */
    public static final String JELLYFISH_ICON_32 = "/icons/jellyfish32x32.png";
    
    /**
     * 16x16 Frame icon url.
     */
    public static final String JELLYFISH_ICON_16 = "/icons/jellyfish16x16.png";
    
    /**
     * White square color of the board.
     */
    public static final float[] WHITE_SQUARE_COLOR = ColorUtils.color(new Color(208, 208, 196));
    
    /**
     * Black square color of the board.
     */
    public static final float[] BLACK_SQUARE_COLOR = ColorUtils.color(new Color(20, 15, 15));

    /**
     * Withe side chess pieces rgb.
     */
    public static final float[] COLOR_W = new float[]{0.9f, 0.9f, 0.88f};
    
    /**
     * Black side chess pieces rgb.
     */
    public static final float[] COLOR_B = ColorUtils.color(new Color(64, 60, 55));

    /**
     * Background rgb.
     */
    public static final float[] DEFAULT_BG_COLOR = ColorUtils.color(new Color(92,122,119), 0.0f);

    /**
     * Engine move square notification rgb.
     */
    public static final float[] ENGINE_MOVE_SQUARE_COLOR = ColorUtils.color(new Color(51, 204, 204));
    
    /**
     * Hint color.
     */
    public static final float[] HINT_COLOR = ColorUtils.color(new java.awt.Color(255, 165, 0));
    
    /**
     * UI side move square notification rgb.
     */
    public static final float[] UI_MOVE_SQUARE_COLOR = ColorUtils.color(new Color(0, 255, 127));

    /**
     * White string value.
     */
    public static final String COLOR_W_STR_VALUE = "white";
    
    /**
     * Black string value.
     */
    public static final String COLOR_B_STR_VALUE = "black";

    /**
     * Checkmate square notification rgb. 
     */
    public static final float[] CHECKMATE_SQUARE_COLOR = ColorUtils.color(new Color(255, 0, 0));
    
    /**
     *Check square notification rgb.
     */
    public static final float[] CHECK_SQUARE_COLOR = ColorUtils.color(new Color(255, 69, 0));

    /**
     * White pawn game start layout positions.
     */
    public static final List<ChessPositions> PAWN_LAYOUT_W = new ArrayList<>();
    static {
        PAWN_LAYOUT_W.add(ChessPositions.A2);
        PAWN_LAYOUT_W.add(ChessPositions.B2);
        PAWN_LAYOUT_W.add(ChessPositions.C2);
        PAWN_LAYOUT_W.add(ChessPositions.D2);
        PAWN_LAYOUT_W.add(ChessPositions.E2);
        PAWN_LAYOUT_W.add(ChessPositions.F2);
        PAWN_LAYOUT_W.add(ChessPositions.G2);
        PAWN_LAYOUT_W.add(ChessPositions.H2);
    }

    /**
     * Black pawn game start layout positions.
     */
    public static final List<ChessPositions> PAWN_LAYOUT_B = new ArrayList<>();
    static {
        PAWN_LAYOUT_B.add(ChessPositions.A7);
        PAWN_LAYOUT_B.add(ChessPositions.B7);
        PAWN_LAYOUT_B.add(ChessPositions.C7);
        PAWN_LAYOUT_B.add(ChessPositions.D7);
        PAWN_LAYOUT_B.add(ChessPositions.E7);
        PAWN_LAYOUT_B.add(ChessPositions.F7);
        PAWN_LAYOUT_B.add(ChessPositions.G7);
        PAWN_LAYOUT_B.add(ChessPositions.H7);
    }

    /**
     * Rook game start layout positions.
     */
    public static final List<ChessPositions> ROOK_LAYOUT_W = new ArrayList<>();
    static {
        ROOK_LAYOUT_W.add(ChessPositions.A1);
        ROOK_LAYOUT_W.add(ChessPositions.H1);
    }

    /**
     * Rook game start layout positions.
     */
    public static final List<ChessPositions> ROOK_LAYOUT_B = new ArrayList<>();
    static {
        ROOK_LAYOUT_B.add(ChessPositions.A8);
        ROOK_LAYOUT_B.add(ChessPositions.H8);
    }

    /**
     * Knight game start layout positions.
     */
    public static final List<ChessPositions> KNIGHT_LAYOUT_W = new ArrayList<>();
    static {
        KNIGHT_LAYOUT_W.add(ChessPositions.B1);
        KNIGHT_LAYOUT_W.add(ChessPositions.G1);
    }

    /**
     * Knight game start layout positions.
     */
    public static final List<ChessPositions> KNIGHT_LAYOUT_B = new ArrayList<>();
    static {
        KNIGHT_LAYOUT_B.add(ChessPositions.B8);
        KNIGHT_LAYOUT_B.add(ChessPositions.G8);
    }

    /**
     * Bishop game start layout positions.
     */
    public static final List<ChessPositions> BISHOP_LAYOUT_W = new ArrayList<>();
    static {
        BISHOP_LAYOUT_W.add(ChessPositions.C1);
        BISHOP_LAYOUT_W.add(ChessPositions.F1);
    }

    /**
     * Bishop game start layout positions.
     */
    public static final List<ChessPositions> BISHOP_LAYOUT_B = new ArrayList<>();
    static {
        BISHOP_LAYOUT_B.add(ChessPositions.C8);
        BISHOP_LAYOUT_B.add(ChessPositions.F8);
    }

    /**
     * White queen game start layout positions.
     */
    public static final ChessPositions QUEEN_LAYOUT_W = ChessPositions.D1;
    
    /**
     * Black queen game start layout positions.
     */
    public static final ChessPositions QUEEN_LAYOUT_B = ChessPositions.D8;
    
    /**
     * White king game start layout positions.
     */
    public static final ChessPositions KING_LAYOUT_W = ChessPositions.E1;
    
    /**
     * Black king game start layout positions.
     */
    public static final ChessPositions KING_LAYOUT_B = ChessPositions.E8;

}

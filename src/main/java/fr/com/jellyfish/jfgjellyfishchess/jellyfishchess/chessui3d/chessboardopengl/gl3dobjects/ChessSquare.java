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
package fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.chessboardopengl.gl3dobjects;

import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.enums.ChessPositions;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.chessui3d.chessboardopengl.utils.PlaneCollision3DUtils;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author thw
 */
public class ChessSquare extends AbstractOPENGL3DObject {
    
    /**
     * true if chess square has collided with mouse input on click.
     */
    private boolean colliding = false;
    
    /**
     * Chess position value.
     * @see ChessPositions enum in enums package.
     */
    public final ChessPositions CHESS_POSITION;
    
    /**
     * Chess piece as model on this square. Null if chess square is empty.
     */
    private OPENGLModel model = null;
    
    /**
     * Model's display list for rendering methodse.
     */
    private int modelDisplayList;
    
    /**
     * @param quads
     * @param color
     * @param normals 
     * @param chessPosition 
     */
    public ChessSquare(final Vector3f[] quads, final float[] color, final float[] normals,
            final ChessPositions chessPosition) {
        super(quads, color, normals);
        this.CHESS_POSITION = chessPosition;
    }

    /**
     * Return true if vertor collides with this vertexes.
     * @param vector
     * @return in or out of collision with mouse click coordinates.
     */
    public boolean collidesWith(final Vector3f vector) {
        colliding = PlaneCollision3DUtils.inCollision(vector, vertexs);
        return colliding;
    }
    
    public boolean isColliding() {
        return colliding;
    }
    
    public void setColliding(final boolean colliding) {
        this.colliding = colliding;
    }
    
    public OPENGLModel getModel() {
        return model;
    }

    public void setModel(final OPENGLModel model) {
        this.model = model;
    }
    
    public int getModelDisplayList() {
        return modelDisplayList;
    }

    public void setModelDisplayList(int modelDisplayList) {
        this.modelDisplayList = modelDisplayList;
    }
    
}
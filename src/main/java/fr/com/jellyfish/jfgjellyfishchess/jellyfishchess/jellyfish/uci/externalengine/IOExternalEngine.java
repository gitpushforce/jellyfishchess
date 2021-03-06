/**
 * *****************************************************************************
 * Copyright (c) 2014, Thomas.H Warner. All rights reserved.
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
package fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.uci.externalengine;

import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.uci.UCIMessageQueue;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.constants.CommonConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.constants.ExternalEngineConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.constants.MessageTypeConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.constants.UCIConst;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.exceptions.InvalidInfiniteSearchResult;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.game.ChessGame;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.interfaces.ExternalEngineObserver;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.uci.EngineCOMMessage;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.uci.EngineCOMMessageQueue;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.uci.UCIMessage;
import fr.com.jellyfish.jfgjellyfishchess.jellyfishchess.jellyfish.utils.EngineCMDUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * IO events when using external engines.
 *
 * @author Thomas.H Warner 2014
 */
public final class IOExternalEngine {

    //<editor-fold defaultstate="collapsed" desc="Private vars">
    /**
     * Singleton.
     */
    private static IOExternalEngine instance;

    /**
     * Process of stockfish chess engine.
     */
    private ProcessBuilder builderStockfish;

    /**
     * List of observers.
     */
    private List<ExternalEngineObserver> engineObservers;

    /**
     * Engine's output stream for this writer.
     */
    private OutputStream outputStream;

    /**
     * Engine's error Stream.
     */
    private InputStream errorStream;

    /**
     * Engine's input stream for this reader.
     */
    private InputStream intputStream;

    /**
     * Reader.
     */
    private BufferedReader reader;

    /**
     * Writer.
     */
    private BufferedWriter writer;

    /**
     * Engine's process.
     */
    private Process process;

    /**
     * Engine's runnable.
     */
    private Thread engineOutputReader;

    /**
     * If the message sent is a consultation, example : to analyze best move
     * without call observers to execute move.
     */
    private boolean executingStaticInfiniteSearch = false;

    /**
     * ChessGame instance.
     */
    private ChessGame game;

    /**
     * Available processor core count.
     */
    private final int procCount;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructor.
     */
    private IOExternalEngine() {
        this.engineObservers = new ArrayList<>();
        this.procCount = Runtime.getRuntime().availableProcessors();
        EngineCOMMessageQueue.getInstance().appendEngineCOMMessageAsFirst(
            new EngineCOMMessage(
                String.format("Available processors : %d\n", this.procCount), 
                MessageTypeConst.NOT_SO_TRIVIAL));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Pubilc methods">
    /**
     * Set ChessGame cless instance.
     *
     * @param game
     */
    public void setGameInstance(final ChessGame game) {
        this.game = game;
    }

    /**
     * Singleton getter.
     *
     * @return IOExternalEngine instance
     */
    public static IOExternalEngine getInstance() {

        if (IOExternalEngine.instance == null) {
            IOExternalEngine.instance = new IOExternalEngine();
            IOExternalEngine.instance.init();
            return IOExternalEngine.instance;
        } else {
            return IOExternalEngine.instance;
        }
    }

    /**
     * Execute a infinite search on a game state without impacting on GUI.
     */
    public void executeStaticInfiniteSearch() {

        // Set to true to prevent best move return from engine being executed as
        // a new move from engine and inpacting GUI for update.
        executingStaticInfiniteSearch = true;

        String input = CommonConst.EMPTY_STR;
        // The 'go infinite' UCI command to send.
        final String uciCmd = UCIConst.INFINITE_SEARCH + CommonConst.BACKSLASH_N;

        input = EngineCMDUtils.buildMovesString(this.game.getGameMoves(), this.game.getMoveIndex())
                + CommonConst.BACKSLASH_N;

        try {
            processEngineMessage(input, MessageTypeConst.INPUT_2);
            processEngineMessage(uciCmd, MessageTypeConst.NOT_SO_TRIVIAL);
            writer.write(input);
            writer.write(uciCmd);
            writer.flush();
        } catch (final IOException ex) {
            Logger.getLogger(IOExternalEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Send stop command to engine.
     */
    public void stopStaticInfiniteSearch() {

        final String input = UCIConst.INFINITE_SEARCH_STOP + CommonConst.BACKSLASH_N;

        try {
            writer.write(input);
            writer.flush();
        } catch (final IOException ex) {
            Logger.getLogger(IOExternalEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Write to engine.
     *
     * @param input
     * @param msgLevel
     */
    public void writeToEngine(String input, final int msgLevel) {

        input += CommonConst.BACKSLASH_N; // Add to each new input.

        try {
            processEngineMessage(input, msgLevel);
            writer.write(input);
            writer.flush();
        } catch (final IOException ex) {
            Logger.getLogger(IOExternalEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Add a new listener.
     *
     * @param engineObserver
     */
    public void addExternalEngineObserver(final ExternalEngineObserver engineObserver) {
        engineObservers.add(engineObserver);
    }

    /**
     * Clear all observers.
     */
    public void clearObservers() {
        engineObservers.clear();
    }
    
    /**
     * Event fired when observers callback as ready.
     */
    public void notifyObserversReady() {
        EngineCOMMessageQueue.getInstance().sendAllCOMMessages(engineObservers);
        UCIMessageQueue.getInstance().sendLastUCIMessage(engineObservers);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Private methods">
    /**
     * Initialize.
     */
    private void init() {

        // Build streams & buffered reader/writer.
        initStreams();
        // Start IO thread for reading engin's output.
        initIO();      
        // Ping and check engine is ready.
        writeToEngine(UCIConst.IS_READY, MessageTypeConst.NOT_SO_TRIVIAL);
        // Set uci mode:
        writeToEngine(UCIConst.UCI, MessageTypeConst.NOT_SO_TRIVIAL);
    }
    
    /**
     * Init IO for Engine too GUI com.
     */
    private void initIO() {

        engineOutputReader = new Thread(new Runnable() {

            String output = CommonConst.EMPTY_STR;
            UCIMessage uci = null;

            @Override
            public void run() {
                
                while (true) {
                    // Read engine output.
                    // Create UCIMessage and feedback.
                    try {
                        output = reader.readLine() + CommonConst.BACKSLASH_N;
                        if (IOExternalEngineDataHelper.getInstance().isBestMoveToExecute(output,
                                executingStaticInfiniteSearch)) {
                            uci = new UCIMessage(output, 
                                    IOExternalEngineDataHelper.getInstance().trimEngineMove(output));
                            processEngineUCIMessage(uci);
                            processEngineMessage(output, MessageTypeConst.BEST_MOVE);
                        } else if (IOExternalEngineDataHelper.getInstance().isBestMoveInfiniteSearch(output, executingStaticInfiniteSearch)) {
                            // Meaning the return is a bestmove result but not to
                            // send to GUI for board chessmen updating : it is not
                            // a search triggered after a GUI move but a demand from
                            // GUI to search 'infinite' on game moves to get a best move.
                            final String bestMove = IOExternalEngineDataHelper.getInstance().trimEngineMove(output);
                            output = UCIConst.INFINITE_SEARCH_RESULT
                                    + CommonConst.BACKSLASH_N + output;
                            uci = new UCIMessage(output, bestMove, MessageTypeConst.CHECK);
                            sendEngineInfiniteSearchMessage(uci);
                            executingStaticInfiniteSearch = false;
                        } else if (IOExternalEngineDataHelper.getInstance().isGeneralComMessage(output)) {
                            processEngineMessage(output, MessageTypeConst.NOT_SO_TRIVIAL);
                        } else {
                            processEngineMessage(output, MessageTypeConst.TRIVIAL);
                        }
                    } catch (final IOException ex) {
                        Logger.getLogger(IOExternalEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        // Finnaly start the thread.
        engineOutputReader.start();
    }

    /**
     * Initialize streams & process.
     */
    private void initStreams() {

        try {
            builderStockfish = new ProcessBuilder(ExternalEngineConst.STOCKFISH_6
                    + ExternalEngineConst.STOCKFISH6_ENGINE_64_BIT);
            builderStockfish.redirectErrorStream(true);
            process = builderStockfish.start();
        } catch (final IOException ex) {
            Logger.getLogger(IOExternalEngine.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

        outputStream = process.getOutputStream();
        errorStream = process.getErrorStream();
        intputStream = process.getInputStream();
        reader = new BufferedReader(new InputStreamReader(intputStream));
        writer = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    /**
     * Send a UCI class message to observers.
     *
     * @param uci
     */
    private void processEngineUCIMessage(final UCIMessage uci) {
            
        UCIMessageQueue.getInstance().appendUCIMessageAsLast(uci);
        UCIMessageQueue.getInstance().sendLastUCIMessage(engineObservers);
    }

    /**
     * Send String message to all observers.
     *
     * @param output
     * @param msgLevel
     */
    private void processEngineMessage(final String output, final int msgLevel) {
        
        EngineCOMMessageQueue.getInstance().appendEngineCOMMessageAsLast(
            new EngineCOMMessage(output, msgLevel));
        
        EngineCOMMessageQueue.getInstance().sendAllCOMMessages(engineObservers);
    }

    /**
     *
     * @param output
     * @param msgLevel
     */
    private void sendEngineInfiniteSearchMessage(final UCIMessage uciMessage) {
        
        for (ExternalEngineObserver observer : engineObservers) {
            try {
                observer.engineInfiniteSearchResponse(uciMessage);
            } catch (final InvalidInfiniteSearchResult imex) {
                Logger.getLogger(IOExternalEngine.class.getName()).log(Level.SEVERE, null, imex);
            }
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters & setters">
    public int getProcCount() {
        return procCount;
    }
    //</editor-fold>

}

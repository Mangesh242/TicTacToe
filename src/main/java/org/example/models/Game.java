package org.example.models;

import org.example.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Symbol> symbols;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;

    private GameState gameState;
    private Player winnerName;
    public int nextPlayerIndex;

    private Game(Builder builder){
        board=new Board(builder.size);
        players=builder.players;
        winnerName=null;
        nextPlayerIndex=0;
        moves=new ArrayList<>();
        gameState=GameState.IN_PROGRESS;
    }
    public void displayBoard(){
        Board.displayBoard();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(Player winnerName) {
        this.winnerName = winnerName;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        //Can you think which attribute will we take from user
        //1.board size
        private int size;
        private List<Player> players;

        public int getSize() {
            return size;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build(){
            //Validation logic for arguments
            return new Game(this);
        }
    }

}

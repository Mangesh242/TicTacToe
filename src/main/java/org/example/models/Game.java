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
    private Player winnerPlayer;
    public int nextPlayerIndex;

    private Game(Builder builder){
        board=new Board(builder.size);
        players=builder.players;
        winnerPlayer =null;
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

    public Player getWinnerPlayer() {
        return winnerPlayer;
    }

    public void setWinnerPlayer(Player player) {
        this.winnerPlayer = player;
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

    public void makeMove(){
        //Get the current player
        //Accept Move from player
        //Validate the move
        //Update the grid
        //add this move to moves list for undo operation
        //update index
        //Check Winner : If yes set the winner name and change game state.

        //Get the current player
        Player player=players.get(nextPlayerIndex);
        //Accept the move from player
        Move move=player.acceptMove();

        //Validate the move
        if(!validateMove(move)){
            System.out.println("Invalid move pls try again");
        }
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();

        Cell cellToChange=board.getGrid().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setSymbol(player.getSymbol());

        move.setCell(cellToChange);
        move.setPlayer(player);
        moves.add(move);

        nextPlayerIndex++;
        nextPlayerIndex%=players.size();

        //Check winner or Check GameState
        if(checkWinner(move)){
            setWinnerPlayer(player);
            setGameState(GameState.SUCCESS);
        }else if(moves.size()== board.getSize()* board.getSize()){
            setWinnerPlayer(null);
            setGameState(GameState.DRAW);
        }
    }
    public boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }

    public boolean validateMove(Move move){
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();

        if(row < 0 && row > board.getSize()-1
                &&
        col<0 && col > board.getSize()-1) return false;

        //if cellstate is empty then only send True otherwise false
        return board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public static class Builder{
        //Can you think which attribute will we take from user
        //1.board size
        private int size;
        private List<Player> players;
        List<WinningStrategy> winningStrategies;

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build(){
            //Validation logic for arguments
            return new Game(this);
        }
    }

}

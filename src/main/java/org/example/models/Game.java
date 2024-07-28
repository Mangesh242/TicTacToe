package org.example.models;

import org.example.strategies.winningStrategies.WinningStrategy;

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
        winningStrategies=builder.winningStrategies;
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

    public void makeMove() {
        //step 1: Get the player
        Player currentPlayer=players.get(nextPlayerIndex);

        System.out.println("It's a "+currentPlayer.getName()+"'s turn ");
//        Step 2: Get the moves
        Move move=currentPlayer.acceptMove(board);

        System.out.println("Move symbol: "+move.getCell().getSymbol());

        //Step 3: Validate move
        if(!validateMove(move)){
            System.out.println("Invalid move! Please try again.");
            return;
        }
        //step 4: Update the grid
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();

        Cell cellToChange=board.getGrid().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setSymbol(currentPlayer.getSymbol());

        move.setCell(cellToChange);
        move.setPlayer(currentPlayer);
        moves.add(move);

        //step 5: update the index
        nextPlayerIndex++;
        nextPlayerIndex %= players.size();

        // index : 0
        // index : 1
        // index : 2 % 2 = 0

        //step 6: check the winner
        if(checkWinner(move)){
            setWinnerName(currentPlayer);
            setGameState(GameState.SUCCESS);
        }
        else if(moves.size()==(board.getSize()* board.getSize())){
            setWinnerName(null);
            setGameState(GameState.DRAW);
        }
    }

    public boolean checkWinner(Move move){
        System.out.println("Size of strategies : "+winningStrategies.size());

        for(WinningStrategy strategy:winningStrategies){
//            System.out.println(strategy.getStrategyName());
           if(strategy.checkWin(board,move)){
               return true;
           };
        }
        return false;
    }
    public void undeMove(){
        if(board.getSize()==0){
            System.out.println("Nothing to undo");
        }

        //Algo :
        //1. Remove the last move
        Move lastMove=moves.get(moves.size()-1);
        moves.remove(moves.size()-1);

        //2.Update the grid
        lastMove.getCell().setCellState(CellState.EMPTY);
        lastMove.getCell().setSymbol(null);

        //3.update nextplayer index
        // (a - b ) % n = (a - b + n )% n
//        0 1
        nextPlayerIndex--;
        nextPlayerIndex=(nextPlayerIndex+players.size())%players.size();
//                currenPlayerIndex= 0
//       After decrementing in undo=-1
//       (-1+2)%2 = 1


        //4.Update the hmap for winning strategies

        for(WinningStrategy strategy:winningStrategies){
            strategy.undoMove(lastMove);
        }
        setGameState(GameState.IN_PROGRESS);
        setWinnerName(null);

    }
    public boolean validateMove(Move move){
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        //Grid test
        if((row < 0 || row > board.getSize()-1) || (col <0 || col > board.getSize()-1)){
            return false;
        }
        //cell test
        return move.getCell().getCellState().equals(CellState.EMPTY);
    }

    public static class Builder{
        //Can you think which attribute will we take from user
        //1.board size
        private int size;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;


        public int getSize() {
            return size;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies=winningStrategies;
        return this;
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

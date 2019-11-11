package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Move{

    private Board board;
    private Color color;
    private Coordinate origin;
    private Coordinate target;

    // public Move(Board board,

    public Move(final Board board, final Color color, final Coordinate origin, final Coordinate target){
        this.board = board;
        this.color = color;
        this.origin = origin;
        this.target = target;
    }

    Color getColor(final Coordinate coordinate){
        return this.board.getColor(coordinate);
    }

    Piece getPiece(final Coordinate coordinate){
        return this.board.getPiece(coordinate);
    }

    boolean isEmpty(final Coordinate coordinate){
        return this.board.isEmpty(coordinate);
    }

    void remove(final Coordinate coordinate){
        this.board.remove(coordinate);
    }

    public Color getColor(){
        return this.color;
    }

    public Coordinate getOrigin(){
        return origin;
    }

    public Coordinate getTarget(){
        return target;
    }
}

package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Game{

    private Board board;

    private Turn turn;

    public Game(){
        this.turn = new Turn();
        this.board = new Board();
        for(int i = 0; i < this.board.getDimension(); i++){
            for(int j = 0; j < this.board.getDimension(); j++){
                final Coordinate coordinate = new Coordinate(i, j);
                final Piece piece = this.getInitialPiece(coordinate);
                if(piece != null){
                    this.board.put(coordinate, piece);
                }
            }
        }
    }

    private Piece getInitialPiece(final Coordinate coordinate){
        if(coordinate.isBlack()){
            final int row = coordinate.getRow();
            Color color = null;
            if(row <= 2){
                color = Color.BLACK;
            }else if(row >= 5){
                color = Color.WHITE;
            }
            if(color != null){
                return new Piece(color);
            }
        }
        return null;
    }

    public Error move(final Coordinate origin, final Coordinate target){
        assert origin != null && target != null;
        final MovePeonValidator peonValidator = new MovePeonValidator(new Move(board, turn.getColor(), origin, target));
        final Error error = peonValidator.validate();
        if(error == null){
            this.board.move(origin, target);
            this.turn.change();
        }
        return error;
    }

    public Color getColor(final Coordinate coordinate){
        return this.board.getColor(coordinate);
    }

    @Override
    public String toString(){
        return this.board + "\n" + this.turn;
    }

    public Color getColor(){
        return this.turn.getColor();
    }

    public Piece getPiece(final Coordinate coordinate){
        return this.board.getPiece(coordinate);
    }

    public boolean isBlocked(){
        return this.board.getPieces(this.turn.getColor()).isEmpty();
    }

    public int getDimension(){
        return this.board.getDimension();
    }

}

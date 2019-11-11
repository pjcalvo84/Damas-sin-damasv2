package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

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
        final Error error = peonValidator.validate(true);
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
        return this.board.getPieces(this.turn.getColor()).isEmpty() && !isPossibleMove();
    }

    public int getDimension(){
        return this.board.getDimension();
    }

    public boolean isPossibleMove(){
        final List<Coordinate> coordinateList = this.board.getCoordinatesForColor(this.turn.getColor());
        for(final Coordinate coordinate : coordinateList){
            if(isPossibleNormalMove(coordinate) == null){
                return true;
            }
            if(isPossibleEatingMove(coordinate) == null){
                return true;
            }
        }
        return false;
    }

    private Error isPossibleNormalMove(final Coordinate coordinate){
        MovePeonValidator peonValidator = null;
        Error error = null;
        if(this.turn.getColor() == Color.WHITE){
            peonValidator = new MovePeonValidator(new Move(board, turn.getColor(), coordinate,
                    new Coordinate(coordinate.getRow() - 1, coordinate.getColumn() - 1)));
            error = peonValidator.validateNormalMove();
            if(error != null){
                peonValidator = new MovePeonValidator(new Move(board, turn.getColor(), coordinate,
                        new Coordinate(coordinate.getRow() - 1, coordinate.getColumn() + 1)));
                error = peonValidator.validateNormalMove();
            }
        }else{
            peonValidator = new MovePeonValidator(new Move(board, turn.getColor(), coordinate,
                    new Coordinate(coordinate.getRow() + 1, coordinate.getColumn() - 1)));
            error = peonValidator.validateEatingMove(false);
            if(error != null){
                peonValidator = new MovePeonValidator(new Move(board, turn.getColor(), coordinate,
                        new Coordinate(coordinate.getRow() + 1, coordinate.getColumn() + 1)));
                error = peonValidator.validateEatingMove(false);
            }
        }
        return error;
    }

    private Error isPossibleEatingMove(final Coordinate coordinate){
        MovePeonValidator peonValidator = null;
        Error error = null;
        if(this.turn.getColor() == Color.WHITE){
            peonValidator = new MovePeonValidator(new Move(board, turn.getColor(), coordinate,
                    new Coordinate(coordinate.getRow() + 2, coordinate.getColumn() - 2)));
            error = peonValidator.validateNormalMove();
            if(error != null){
                peonValidator = new MovePeonValidator(new Move(board, turn.getColor(), coordinate,
                        new Coordinate(coordinate.getRow() + 2, coordinate.getColumn() + 2)));
                error = peonValidator.validateNormalMove();
            }
        }else{
            peonValidator = new MovePeonValidator(new Move(board, turn.getColor(), coordinate,
                    new Coordinate(coordinate.getRow() - 2, coordinate.getColumn() - 2)));
            error = peonValidator.validateNormalMove();
            if(error != null){
                peonValidator = new MovePeonValidator(new Move(board, turn.getColor(), coordinate,
                        new Coordinate(coordinate.getRow() - 2, coordinate.getColumn() + 2)));
                error = peonValidator.validateNormalMove();
            }
        }
        return error;
    }
}

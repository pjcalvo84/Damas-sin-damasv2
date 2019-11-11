package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

class Board{

    private static final int DIMENSION = 8;

    private Square[][] squares;

    Board(){
        this.squares = new Square[this.getDimension()][this.getDimension()];
        for(int i = 0; i < this.getDimension(); i++){
            for(int j = 0; j < this.getDimension(); j++){
                this.squares[i][j] = new Square();
            }
        }
    }

    private Square getSquare(final Coordinate coordinate){
        assert coordinate != null && coordinate.isValid();
        return this.squares[coordinate.getRow()][coordinate.getColumn()];
    }

    void put(final Coordinate coordinate, final Piece piece){
        assert piece != null;
        this.getSquare(coordinate).put(piece);
    }

    Piece remove(final Coordinate coordinate){
        assert this.getPiece(coordinate) != null;
        return this.getSquare(coordinate).remove();
    }

    void move(final Coordinate origin, final Coordinate target){
        this.put(target, this.remove(origin));
    }

    Piece getPiece(final Coordinate coordinate){
        return this.getSquare(coordinate).getPiece();
    }

    boolean isEmpty(final Coordinate coordinate){
        return this.getSquare(coordinate).isEmpty();
    }

    Color getColor(final Coordinate coordinate){
        return this.getSquare(coordinate).getColor();
    }

    List<Piece> getPieces(final Color color){
        final List<Piece> pieces = new ArrayList<Piece>();
        for(int i = 0; i < this.getDimension(); i++){
            for(int j = 0; j < this.getDimension(); j++){
                final Piece piece = this.squares[i][j].getPiece();
                if(isSameColor(color, piece)){
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }

    List<Coordinate> getCoordinatesForColor(final Color color){
        final List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        for(int i = 0; i < this.getDimension(); i++){
            for(int j = 0; j < this.getDimension(); j++){
                final Piece piece = this.squares[i][j].getPiece();
                if(isSameColor(color, piece)){
                    coordinateList.add(new Coordinate(i, j));
                }
            }
        }
        return coordinateList;
    }

    private boolean isSameColor(final Color color, final Piece piece){
        return piece != null && color == piece.getColor();
    }

    int getDimension(){
        return Board.DIMENSION;
    }

    @Override
    public String toString(){
        String string = "";
        string += this.toStringHorizontalNumbers();
        for(int i = 0; i < this.getDimension(); i++){
            string += this.toStringHorizontalPiecesWithNumbers(i);
        }
        string += this.toStringHorizontalNumbers();
        return string;
    }

    private String toStringHorizontalNumbers(){
        String string = " ";
        for(int j = 0; j < Board.DIMENSION; j++){
            string += j;
        }
        return string + "\n";
    }

    private String toStringHorizontalPiecesWithNumbers(final int row){
        String string = "" + row;
        for(int j = 0; j < this.getDimension(); j++){
            final Piece piece = this.getPiece(new Coordinate(row, j));
            if(piece == null){
                string += " ";
            }else{
                final String[] letters = {"b", "n"};
                string += letters[piece.getColor().ordinal()];
            }
        }
        return string + row + "\n";
    }

}

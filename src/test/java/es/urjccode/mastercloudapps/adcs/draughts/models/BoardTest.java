package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class BoardTest{

    private final Board board = new Board();

    @Test
    public void shouldReturn12sizeForWhitePiecesColor(){

        initBoard(board);
        List<Piece> pieces = board.getPieces(Color.WHITE);
        assertThat(pieces.size(), is (12));
    }

    @Test
    public void shouldReturn12sizeForBlackPiecesColor(){

        initBoard(board);
        List<Piece> pieces = board.getPieces(Color.BLACK);
        assertThat(pieces.size(), is (12));
    }


    @Test
    public void shouldReturnNineSizeForBlackPiecesColor(){

        initBoard(board);
        board.remove(new Coordinate(0,1));
        board.remove(new Coordinate(1,0));
        board.remove(new Coordinate(2,1));
        List<Piece> pieces = board.getPieces(Color.BLACK);
        assertThat(pieces.size(), is (9));
    }

    @Test
    public void shouldReturnZeroForGetPieces(){

        List<Piece> pieces = board.getPieces(Color.BLACK);
        assertThat(pieces.size(), is (0));
        pieces = board.getPieces(Color.WHITE);
        assertThat(pieces.size(), is (0));
    }

    private void initBoard(Board board){
        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Piece piece = this.getInitialPiece(coordinate);
                if (piece != null) {
                    board.put(coordinate, piece);
                }
            }
        }
    }


    private Piece getInitialPiece(Coordinate coordinate) {
        if (coordinate.isBlack()) {
            final int row = coordinate.getRow();
            Color color = null;
            if (row <= 2) {
                color = Color.BLACK;
            } else if (row >= 5) {
                color = Color.WHITE;
            }
            if (color != null) {
                return new Piece(color);
            }
        }
        return null;
    }
}

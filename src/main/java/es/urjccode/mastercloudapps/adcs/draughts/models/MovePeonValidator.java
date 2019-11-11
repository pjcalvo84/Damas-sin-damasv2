package es.urjccode.mastercloudapps.adcs.draughts.models;

public class MovePeonValidator{

    Move move;

    public MovePeonValidator(final Move move){
        this.move = move;
    }

    public Error validate(){
        if(move.isEmpty(move.getOrigin())){
            return Error.EMPTY_ORIGIN;
        }
        final Color color = this.move.getColor(move.getOrigin());
        if(this.move.getColor() != color){
            return Error.OPPOSITE_PIECE;
        }
        if(!move.getOrigin().isDiagonal(move.getTarget())){
            return Error.NOT_DIAGONAL;
        }
        final Piece piece = this.move.getPiece(move.getOrigin());
        if(!piece.isAdvanced(move.getOrigin(), move.getTarget())){
            return Error.NOT_ADVANCED;
        }
        if(move.getOrigin().diagonalDistance(move.getTarget()) >= 3){
            return Error.BAD_DISTANCE;
        }
        if(!this.move.isEmpty(move.getTarget())){
            return Error.NOT_EMPTY_TARGET;
        }
        if(move.getOrigin().diagonalDistance(move.getTarget()) == 2){
            final Coordinate between = move.getOrigin().betweenDiagonal(move.getTarget());
            if(this.move.getPiece(between) == null){
                return Error.EATING_EMPTY;
            }
            this.move.remove(between);
        }
        return null;
    }
}

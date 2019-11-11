package es.urjccode.mastercloudapps.adcs.draughts.models;

public class MovePeonValidator{

    Move move;

    public MovePeonValidator(final Move move){
        this.move = move;
    }

    public Error validate(){

        if(checkValidCoordinate()){
            return Error.OUT_COORDINATE;
        }
        if(checkEmptyOrigin()){
            return Error.EMPTY_ORIGIN;
        }
        if(checkOppositePiece()){
            return Error.OPPOSITE_PIECE;
        }
        if(checkMoveDiagonal()){
            return Error.NOT_DIAGONAL;
        }
        if(checkAdvanceMove()){
            return Error.NOT_ADVANCED;
        }
        if(checkBadDistance()){
            return Error.BAD_DISTANCE;
        }
        if(checkEmptyTarget()){
            return Error.NOT_EMPTY_TARGET;
        }
        if(checkEatingEmpty()){
            return Error.EATING_EMPTY;
        }
        return null;
    }

    public Error validateNormalMove(){
        if(checkValidCoordinate()){
            return Error.OUT_COORDINATE;
        }
        if(checkEmptyTarget()){
            return Error.NOT_EMPTY_TARGET;
        }
        return null;
    }

    private boolean checkValidCoordinate(){
        return !move.getOrigin().isValid() || !move.getTarget().isValid();
    }

    private boolean checkEatingEmpty(){
        if(move.getOrigin().diagonalDistance(move.getTarget()) == 2){
            final Coordinate between = move.getOrigin().betweenDiagonal(move.getTarget());
            if(this.move.getPiece(between) == null){
                return true;
            }
            this.move.remove(between);
        }
        return false;
    }

    private boolean checkEmptyTarget(){
        return !this.move.isEmpty(move.getTarget());
    }

    private boolean checkBadDistance(){
        return(move.getOrigin().diagonalDistance(move.getTarget()) >= 3);
    }

    private boolean checkAdvanceMove(){
        final Piece piece = this.move.getPiece(move.getOrigin());
        return !piece.isAdvanced(move.getOrigin(), move.getTarget());
    }

    private boolean checkMoveDiagonal(){
        return !move.getOrigin().isDiagonal(move.getTarget());
    }

    private boolean checkOppositePiece(){
        final Color color = this.move.getColor(move.getOrigin());
        return this.move.getColor() != color;
    }

    private boolean checkEmptyOrigin(){
        return move.isEmpty(move.getOrigin());
    }
}

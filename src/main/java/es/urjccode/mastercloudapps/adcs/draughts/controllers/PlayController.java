package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.Session;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;

public class PlayController extends Controller {

    public PlayController(Session session) {
		super(session);
	}

	public Error move(Coordinate origin, Coordinate target){
		Error error = this.session.game.move(origin, target);
		if (this.session.game.isBlocked()){
			this.session.state.next();
		}
		return error;
    }

	public Piece getPiece(Coordinate coordinate) {
		return session.game.getPiece(coordinate);
	}

	public Color getColor() {
		return session.game.getColor();
	}

	public boolean isBlocked() {
		return session.game.isBlocked();
	}

	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
	}

}

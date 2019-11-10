package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;

public class CommandView extends SubView {

    private static final String[] COLORS = {"blancas", "negras"};

    private static final String MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";

    public CommandView(){
        super();
    }

    public void interact(PlayController playController) {
        String color = CommandView.COLORS[playController.getColor().ordinal()];
        Error error = null;
        GameView gameView = new GameView();
        do {
            String command = this.console.readString("Mueven las " + color + ": ");
            if(checkFormatCoordenate(command) == null){
                System.out.println("Entro:" + command + ":");
                final int origin = Integer.parseInt(command.substring(0, 2));
                final int target = Integer.parseInt(command.substring(3, 5));
                error = playController.move(new Coordinate(origin / 10, origin % 10),
                    new Coordinate(target / 10, target % 10));
                if(error != null){
                    writeError(error);
                }
            }
            gameView.write(playController);
        } while (error != null);
        if (playController.isBlocked()){
            this.console.write(CommandView.MESSAGE);
        }
    }

    protected Error checkFormatCoordenate(final String command){
        try{
            if(command.length() > 6){
                writeError(Error.WRONG_FORMAT);
                return Error.WRONG_FORMAT;
            }
            Integer.parseInt(command.substring(0, 2));
            Integer.parseInt(command.substring(3, 5));
            return null;
        }catch(final Exception ex){
            writeError(Error.WRONG_FORMAT);
            return Error.WRONG_FORMAT;
        }
    }

    private void writeError(final Error error){
        console.write("Error!!!" + error.name());
    }

}

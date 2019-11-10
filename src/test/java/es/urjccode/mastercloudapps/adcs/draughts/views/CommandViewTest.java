package es.urjccode.mastercloudapps.adcs.draughts.views;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class CommandViewTest {

    @Mock
    PlayController playController;

    @Mock
    Console console;

    @InjectMocks
    CommandView commandView;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInteract(){
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("32.41\n");
        commandView.interact(playController);
        verify(playController).move(new Coordinate(2,1), new Coordinate(3, 0));
    }

    @Test
    public void testInteractWithWrongCoordinateSize(){
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("47.39").thenReturn("21.32\n");
        commandView.interact(playController);
        verify(console).writeln("Error!!!OUT_COORDINATE");
    }

    @Test
    public void testInteractWithWorngEntry(){
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("JKDC").thenReturn("21.32\n");
        commandView.interact(playController);
        verify(console).writeln("Error!!!WRONG_FORMAT");
    }

    @Test
    public void testInteractWithWorngEntrySize(){
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("21.32.42").thenReturn("21.32\n");
        commandView.interact(playController);
        verify(console).writeln("Error!!!WRONG_FORMAT");
    }

    @Test
    public void testInteractWithBlackColor(){
        when(playController.getColor()).thenReturn(Color.BLACK);
        when(console.readString("Mueven las negras: ")).thenReturn("21.32\n");
        commandView.interact(playController);
        verify(playController).move(new Coordinate(1, 0), new Coordinate(2, 1));
    }

    @Test
    public void testInteractWithWhiteColor(){
        when(playController.getColor()).thenReturn(Color.WHITE);
        when(console.readString("Mueven las blancas: ")).thenReturn("61.52\n");
        commandView.interact(playController);
        verify(playController).move(new Coordinate(5, 0), new Coordinate(4, 1));
    }

}

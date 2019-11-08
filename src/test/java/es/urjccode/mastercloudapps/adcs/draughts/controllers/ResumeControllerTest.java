package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;

public class ResumeControllerTest {

    private Game game;
    private State state;

    @Before
    public void initTest(){
        this.game = new Game();
        this.state = new State();
    }

    @Test
    public void givenResumeControllerWhenResumeGameMoveToInitialStateRequiereCorrectThenNotError() {

        ResumeController resumeController = new ResumeController(game, state);
        assertEquals(StateValue.INITIAL, state.getValueState());
        resumeController.next();
        assertEquals(StateValue.IN_GAME, state.getValueState());
        resumeController.next();
        assertEquals(StateValue.FINAL, state.getValueState());
        resumeController.reset();
        assertEquals(StateValue.INITIAL, state.getValueState());
    }

    @Test(expected = AssertionError.class)
    public void givenResumeControllerWhenResumeGameMoveOutThenError() {

        ResumeController resumeController = new ResumeController(game, state);
        assertEquals(StateValue.INITIAL, state.getValueState());
        resumeController.next();
        assertEquals(StateValue.IN_GAME, state.getValueState());
        resumeController.next();
        assertEquals(StateValue.FINAL, state.getValueState());
        resumeController.next();
        assertEquals(StateValue.EXIT, state.getValueState());
        resumeController.next();
    }
}

package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MovePeonValidatorTest{

    @Test
    public void shouldBeErrorNotEatingMoveWhenMoveIsNotForEating(){
        final MovePeonValidator movePeonValidator = new MovePeonValidator(
                new Move(new Board(), Color.WHITE, new Coordinate(5, 0), new Coordinate(4, 1)));
        assertThat(movePeonValidator.validateEatingMove(), is(Error.NOT_EATING_MOVE));
    }

}

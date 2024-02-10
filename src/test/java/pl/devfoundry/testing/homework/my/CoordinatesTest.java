package pl.devfoundry.testing.homework.my;

import org.junit.jupiter.api.Test;
import pl.devfoundry.testing.homework.Coordinates;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinatesTest {

    @Test
    void testCoordinatesConstructorWithInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(0, -1));
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(101, 0));
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(0, 101));
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(101, 101));
    }

    @Test
    void testCopyCoordinates() {
        Coordinates coordinates = new Coordinates(10, 10);

        Coordinates copiedCoordinates = Coordinates.copy(coordinates, 4, 5);

        assertThat(copiedCoordinates, not(sameInstance(coordinates)));
        assertThat(copiedCoordinates.getX(), equalTo(14));
        assertThat(copiedCoordinates.getY(), equalTo(15));
    }
}
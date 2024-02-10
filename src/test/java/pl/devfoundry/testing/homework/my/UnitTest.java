package pl.devfoundry.testing.homework.my;

import org.junit.jupiter.api.Test;
import pl.devfoundry.testing.homework.Cargo;
import pl.devfoundry.testing.homework.Coordinates;
import pl.devfoundry.testing.homework.Unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void testMove() {
        Unit unit = new Unit(new Coordinates(5, 5), 4, 100);

        Coordinates newCoords = unit.move(1, 2);

        assertThat(newCoords.getX(), equalTo(6));
        assertThat(newCoords.getY(), equalTo(7));
        assertThat(unit.getFuel(), equalTo(1));
    }

    @Test
    void testInvalidMove() {
        Unit unit = new Unit(new Coordinates(0, 0), 400, 100);

        assertThrows(IllegalArgumentException.class, () -> unit.move(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> unit.move(0, -1));
        assertThrows(IllegalArgumentException.class, () -> unit.move(101, 0));
        assertThrows(IllegalArgumentException.class, () -> unit.move(0, 101));
        assertThrows(IllegalArgumentException.class, () -> unit.move(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> unit.move(101, 101));
    }

    @Test()
    void testNotEnughFuelToMove() {
        Unit unit = new Unit(new Coordinates(5, 5), 4, 100);

        assertThrows(IllegalStateException.class, () -> unit.move(3, 2));
    }

    @Test
    void testCanNotTankUpAboveLimit() {
        Unit unit = new Unit(new Coordinates(0, 0), 400, 100);

        unit.tankUp();

        assertThat(unit.getFuel(), equalTo(400));
    }

    @Test
    void testTankUp() {
        Unit unit = new Unit(new Coordinates(0, 0), 400, 100);

        unit.move(20, 20);

        unit.tankUp();

        assertThat(unit.getFuel(), greaterThan(360));
        assertThat(unit.getFuel(), lessThanOrEqualTo(370));
    }

    @Test
    void loadCargo1() {
        Unit unit = new Unit(new Coordinates(0, 0), 400, 100);

        Cargo cargo1 = new Cargo("cargo1", 20);
        Cargo cargo2 = new Cargo("cargo2", 30);

        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        assertThat(unit.getLoad(), equalTo(50));
    }

    @Test
    void loadCargo2() {
        Unit unit = new Unit(new Coordinates(0, 0), 400, 100);

        Cargo cargo1 = new Cargo("cargo1", 50);
        Cargo cargo2 = new Cargo("cargo2", 50);

        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        assertThat(unit.getLoad(), equalTo(100));
    }

    @Test
    void testCanNotLoadCargoAboveLimit() {
        Unit unit = new Unit(new Coordinates(0, 0), 400, 100);

        Cargo cargo1 = new Cargo("cargo1", 50);
        Cargo cargo2 = new Cargo("cargo2", 60);

        assertThrows(IllegalStateException.class, () -> {
            unit.loadCargo(cargo1);
            unit.loadCargo(cargo2);
        });
    }

    @Test
    void unloadCargo() {
        Unit unit = new Unit(new Coordinates(0, 0), 400, 100);

        Cargo cargo1 = new Cargo("cargo1", 50);
        Cargo cargo2 = new Cargo("cargo2", 50);

        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        assertThat(unit.getLoad(), equalTo(100));
        unit.unloadCargo(cargo1);
        assertThat(unit.getLoad(), equalTo(50));
        unit.unloadCargo(cargo2);
        assertThat(unit.getLoad(), equalTo(0));
    }

    @Test
    void unloadAllCargo() {
        Unit unit = new Unit(new Coordinates(0, 0), 400, 100);

        Cargo cargo1 = new Cargo("cargo1", 50);
        Cargo cargo2 = new Cargo("cargo2", 50);

        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);

        assertThat(unit.getLoad(), equalTo(100));

        unit.unloadAllCargo();

        assertThat(unit.getLoad(), equalTo(0));
    }

    @Test
    void calculateCargoWeight() {
    }
}
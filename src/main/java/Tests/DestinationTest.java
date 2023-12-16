package Tests;

import LocationHandling.PlayersDestination;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertTrue;

public class DestinationTest {
    @Test
    public void testValidDestination() {
        String input = "Location1\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        PlayersDestination destination = new PlayersDestination();
        destination.destination();
        assertTrue(outputStream.toString().contains("Finding you a taxi for: Location1"));
    }

    @Test
    public void testInvalidDestination() {
        String input = "InvalidLocation\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        PlayersDestination destination = new PlayersDestination();
        destination.destination();
        assertTrue(outputStream.toString().contains("Your destination doesn't exist"));
    }
}


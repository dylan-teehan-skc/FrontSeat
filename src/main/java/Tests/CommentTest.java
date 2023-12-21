package Tests;
import LocationHandling.Comment;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class CommentTest {

    @Test
    public void testStringInput() {
        String input = "1\nThis is a string comment\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Comment commentInstance = new Comment();

        try {
            commentInstance.askForComment();
            assertTrue(outputStream.toString().contains("Thank you for your comment"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
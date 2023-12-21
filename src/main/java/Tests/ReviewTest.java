package Tests;



import LocationHandling.Review;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

    public class ReviewTest {

        @Test
        public void testValidReview() {
            String input = "8\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            try {
                Review.GiveReview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
import com.example.kirin.cs2340.Model.ValidationUtilities;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ryan on 4/2/2017.
 * Runs Ryan's Tests
 */

public class RyanTests {
    @Test
    public void testLoginFieldValidation() {
        String goodUsername = "testusername";
        String goodPassword = "testpassword";
        boolean valid = ValidationUtilities.loginFieldsAreValid(goodUsername, goodPassword);
        assertTrue(valid);

        String badUsername = "";
        String badPassword = "";
        valid = ValidationUtilities.loginFieldsAreValid(badUsername, badPassword);
        assertFalse(valid);

        valid = ValidationUtilities.loginFieldsAreValid(null, null);
        assertFalse(valid);
    }
}

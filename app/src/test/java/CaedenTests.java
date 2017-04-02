import com.example.kirin.cs2340.Model.ValidationUtilities;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Caeden on 4/2/2017.
 * Runs Caeden's Tests
 */


public class CaedenTests {
    @Test
    public void testGraphFieldsAreValid() {
        double lat = 50;
        double lng = 50;
        int year = 2017;
        String selection1 = "VIRUS";
        String selection2 = "CONTAMINANT";

        boolean test1 = ValidationUtilities.graphFieldsAreValid(lat, lng, year, selection1);
        boolean test2 = ValidationUtilities.graphFieldsAreValid(lat, lng, year, selection2);

        assertTrue(test1);
        assertTrue(test2);

        boolean test3 = ValidationUtilities.graphFieldsAreValid(91, lng, year, selection1);
        boolean test4 = ValidationUtilities.graphFieldsAreValid(lat, 181, year, selection1);
        boolean test5 = ValidationUtilities.graphFieldsAreValid(lat, lng, -1, selection1);
        boolean test6 = ValidationUtilities.graphFieldsAreValid(lat, lng, year, "Wrong selection");

        assertFalse(test3);
        assertFalse(test4);
        assertFalse(test5);
        assertFalse(test6);
    }
}

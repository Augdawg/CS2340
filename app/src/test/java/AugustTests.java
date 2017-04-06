import com.example.kirin.cs2340.Model.ValidationUtilities;
import com.example.kirin.cs2340.Model.WaterSourceReport;

import org.junit.Test;
import static org.junit.Assert.assertNull;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by August Wagner on 4/2/2017.
 * Runs August's Tests on ValidationUtilities.tryCreateWSR
 */

public class AugustTests {
    @Test
    public void tryCreateWSRTest() {
        Date date = new Date();
        WaterSourceReport nameNull = ValidationUtilities.tryCreateWSR(null, 0, 0, "Well", "Potable", date);
        WaterSourceReport nameEmpty = ValidationUtilities.tryCreateWSR("", 0, 0, "Well", "Potable", date);
        WaterSourceReport latHigh = ValidationUtilities.tryCreateWSR("Ricky", 91, 0, "Well", "Potable", date);
        WaterSourceReport latLow = ValidationUtilities.tryCreateWSR("Ricky", -91, 0, "Well", "Potable", date);
        WaterSourceReport longHigh = ValidationUtilities.tryCreateWSR("Ricky", 0, 191, "Well", "Potable", date);
        WaterSourceReport longLow = ValidationUtilities.tryCreateWSR("Ricky", 0, -191, "Well", "Potable", date);
        WaterSourceReport typeNull = ValidationUtilities.tryCreateWSR("Ricky", 0, 0, null, "Potable", date);
        WaterSourceReport typeEmpty = ValidationUtilities.tryCreateWSR("Ricky", 0, 0, "", "Potable", date);
        WaterSourceReport typeWrong = ValidationUtilities.tryCreateWSR("Ricky", 0, 0, "Julian", "Potable", date);
        WaterSourceReport condNull = ValidationUtilities.tryCreateWSR("Ricky", 0, 0, "Well", null, date);
        WaterSourceReport condEmpty = ValidationUtilities.tryCreateWSR("Ricky", 0, 0, "Well", "", date);
        WaterSourceReport condWrong = ValidationUtilities.tryCreateWSR("Ricky", 0, 0, "Well", "Sunnyvale", date);
        WaterSourceReport dateNull = ValidationUtilities.tryCreateWSR("Ricky", 0, 0, "Well", "Potable", null);

        assertNull(nameNull);
        assertNull(nameEmpty);
        assertNull(latHigh);
        assertNull(latLow);
        assertNull(longHigh);
        assertNull(longLow);
        assertNull(typeNull);
        assertNull(typeEmpty);
        assertNull(typeWrong);
        assertNull(condNull);
        assertNull(condEmpty);
        assertNull(condWrong);
        assertNull(dateNull);
    }
}



import com.example.kirin.cs2340.Model.OverallWaterCondition;
import com.example.kirin.cs2340.Model.ValidationUtilities;
import com.example.kirin.cs2340.Model.WaterQualityReport;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Devin on 4/2/2017.
 * Run's Devin's Tests for ValidationUtilities.tryCreateWQR
 */

public class DevinTests {

    @Test
    public void testTryCreateWQR() {
        String name = "Devin";
        int lat = 50;
        int lng = 50;
        String safe = "Safe";
        String treatable = "Treatable";
        String unsafe = "Unsafe";
        int virus = 100;
        int contaminant = 200;

        WaterQualityReport test1 = ValidationUtilities.tryCreateWQR(null, lat, lng, safe, virus, contaminant, new Date());
        WaterQualityReport test2 = ValidationUtilities.tryCreateWQR(name, 91, lng, safe, virus, contaminant, new Date());
        WaterQualityReport test3 = ValidationUtilities.tryCreateWQR(name, lat, 181, safe, virus, contaminant, new Date());
        WaterQualityReport test4 = ValidationUtilities.tryCreateWQR(name, lat, lng, "wrong condition", virus, contaminant, new Date());
        WaterQualityReport test5 = ValidationUtilities.tryCreateWQR(name, lat, lng, safe, -1, contaminant, new Date());
        WaterQualityReport test6 = ValidationUtilities.tryCreateWQR(name, lat, lng, safe, virus, -1, new Date());

        WaterQualityReport test7 = ValidationUtilities.tryCreateWQR(name, lat, lng, safe, virus, contaminant, new Date());
        WaterQualityReport test8 = ValidationUtilities.tryCreateWQR(name, lat, lng, treatable, virus, contaminant, new Date());
        WaterQualityReport test9 = ValidationUtilities.tryCreateWQR(name, lat, lng, unsafe, virus, contaminant, new Date());

        assertTrue(test1 == null);
        assertTrue(test2 == null);
        assertTrue(test3 == null);
        assertTrue(test4 == null);
        assertTrue(test5 == null);
        assertTrue(test6 == null);

        assertTrue(test7.getName().equals(name));
        assertTrue(test7.getLat() == lat);
        assertTrue(test7.getLng() == lng);
        assertTrue(test7.getCondition().equals(OverallWaterCondition.SAFE));
        assertTrue(test7.getVirusPPM() == virus);
        assertTrue(test7.getContaminantPPM() == contaminant);

        assertTrue(test8.getCondition().equals(OverallWaterCondition.TREATABLE));
        assertTrue(test9.getCondition().equals(OverallWaterCondition.SAFE.UNSAFE));
    }
}

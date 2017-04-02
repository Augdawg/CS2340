
import com.example.kirin.cs2340.Model.Admin;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.Manager;
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.Model.ValidationUtilities;
import com.example.kirin.cs2340.Model.Worker;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kirin on 4/1/2017.
 * Runs Kirin's Tests on ValidationUtilities.registrationFieldsAreValid
 */
public class KirinTests {

    @Test
    public void registrationFieldsTest() {
        GeneralUser user = new User("user_name", "user_username", "user_password", "user_email@gmail.com",
                "user_home", "user_title");
        GeneralUser nameNull = ValidationUtilities.registrationFieldsAreValid(null, user.getUsername(), user.getPassword(),
                user.getTitle(), user.getEmail(), user.getHome(), user.getAccountType());

        GeneralUser usernameNull = ValidationUtilities.
                registrationFieldsAreValid(user.getName(), null, user.getPassword(),
                        user.getTitle(), user.getEmail(), user.getHome(), user.getAccountType());

        GeneralUser passwordNull = ValidationUtilities.
                registrationFieldsAreValid(user.getName(), user.getUsername(), null,
                        user.getTitle(), user.getEmail(), user.getHome(), user.getAccountType());

        GeneralUser titleNull = ValidationUtilities.
                registrationFieldsAreValid(user.getName(), user.getUsername(), user.getPassword(),
                        null, user.getEmail(), user.getHome(), user.getAccountType());

        GeneralUser emailNull = ValidationUtilities.
                registrationFieldsAreValid(user.getName(), user.getUsername(), user.getPassword(),
                        user.getTitle(), null, user.getHome(), user.getAccountType());

        GeneralUser homeNull = ValidationUtilities.
                registrationFieldsAreValid(user.getName(), user.getUsername(), user.getPassword(),
                        user.getTitle(), user.getEmail(), null, user.getAccountType());

        assertTrue(nameNull == null);
        assertTrue(usernameNull == null);
        assertTrue(passwordNull == null);
        assertTrue(titleNull == null);
        assertTrue(emailNull == null);
        assertTrue(homeNull == null);

        GeneralUser userInstance = ValidationUtilities.registrationFieldsAreValid(user.getName(), user.getUsername(), user.getPassword(),
                user.getTitle(), user.getEmail(), user.getHome(), "USER");

        GeneralUser workerInstance = ValidationUtilities.registrationFieldsAreValid(user.getName(), user.getUsername(), user.getPassword(),
                user.getTitle(), user.getEmail(), user.getHome(), "WORKER");

        GeneralUser managerInstance = ValidationUtilities.registrationFieldsAreValid(user.getName(), user.getUsername(), user.getPassword(),
                user.getTitle(), user.getEmail(), user.getHome(), "MANAGER");

        GeneralUser adminInstance = ValidationUtilities.registrationFieldsAreValid(user.getName(), user.getUsername(), user.getPassword(),
                user.getTitle(), user.getEmail(), user.getHome(), "ADMIN");

        assertTrue(userInstance instanceof User);
        assertTrue(workerInstance instanceof Worker);
        assertTrue(managerInstance instanceof Manager);
        assertTrue(adminInstance instanceof Admin);

    }
}

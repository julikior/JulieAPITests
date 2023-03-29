package crud;

import listeners.TestListener;
import model.Data;
import model.UserRequest;
import model.UserResponse;
import org.springframework.context.annotation.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import users.UserEndpoint;

import java.util.List;

import static java.lang.System.currentTimeMillis;
import static model.UserRequest.userEmail;
import static model.UserRequest.userName;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static users.UserEndpoint.randomGender;
import static users.UserEndpoint.randomStatus;

@Listeners({ TestListener.class })
public class CrudUserTest {

    UserEndpoint userAPI = new UserEndpoint();

    @Test(description = "Can get users list")
    public void getUsers() {
        List<Data> users = userAPI.getUsers();
        assertNotNull(users);
    }

    @Test(description = "Can Create, Read, Update, Delete user")
    @Description("Can CRUD user")
    public void crudUser() {

        UserResponse response = userAPI.createUser(UserRequest.builder()
                .name(userName)
                .gender(randomGender())
                .email(userEmail)
                .status(randomStatus())
                .build());
        //Data response = userAPI.createUser(rq);
        assertNotNull(response.getData());
        assertThatJson(response.getData())
                .isObject();

        Integer userID = response.getData().getId();
        UserResponse user = userAPI.getUserById(userID);
        assertNotNull(user.getData());
        assertEquals(userEmail, userEmail);

        String newName = "UpdatedName" + currentTimeMillis();
        String newEmail = "UpdatedEmail" + currentTimeMillis() + "@test.com";
        //response.setName(newName);
        UserResponse updated = userAPI.updateUser(userID, UserRequest.builder()
                .name(newName)
                .gender(randomGender())
                .email(newEmail)
                .status(randomStatus())
                .build());

        assertNotNull(updated.getData());
        assertEquals(updated.getData().getName(), newName);

        UserResponse patched = userAPI.patchUser(userID, UserRequest.builder()
                .build());


        assertThat(patched)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(updated);


        userAPI.deleteUser(userID);
    }
}

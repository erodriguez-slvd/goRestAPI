import io.restassured.response.Response;
import org.solvd.models.User;
import org.solvd.services.UserService;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class UserTest {
    private static UserService userService;
    public static String baseUri="https://gorest.co.in/public/v2";
    @BeforeTest
    public static void initUserService(){
        userService= new UserService();
    }

    @Test
    public void getAllUsersTest() {
    Response response = userService.getAllUsers();
    List<User> userList = response.jsonPath().getList(".", User.class);
    Assert.assertFalse(userList.isEmpty());
}
}

package bdd.stepdefs;

import bdd.SpringBootCucumberTest;
import com.comit.model.LoginForm;
import com.comit.model.User;
import com.comit.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
//@SpringBootCucumberTest
public class LoginStepDefs {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    ResultActions action;

    LoginForm loginForm;

    @Before
    public void addMockUserToUserRepository()
    {
        User user = new User("Zaphod", "zaphod@galaxy.net","eheheh","eheheh","USER");
        if(userRepository.findByUsername("Zaphod") == null) userRepository.save(user);
    }

    /**
     *
     *
     */
    @Given("comit app login page, valid user with a valid username and valid password")
    public void comitAppLoginPageValidUserWithAValidUsernameAndValidPassword(DataTable table) throws Throwable {
        List<String> data = table.row(1);
        loginForm = new LoginForm(data.get(0), data.get(1) );
    }

    @When("the user fill login form")
    public void theUserFillLoginForm() throws Throwable {
        action = mvc.perform(MockMvcRequestBuilders
                .post("/login")
                .content(asJsonString(loginForm))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("login process should be successful")
    public void loginProcessShouldBeSuccessful() throws Throwable {
        action.andExpect(status().is(200));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     *
     */

    @Given("comit app login page, valid user with a valid username and wrong password")
    public void comitAppLoginPageValidUserWithAValidUsernameAndWrongPassword(DataTable table) {
        addMockUserToUserRepository();
        List<String> data = table.row(1);
        loginForm = new LoginForm(data.get(0), data.get(1) );
    }

    @When("the user fill login form with this user")
    public void theUserFillLoginFormWithThisUser() throws Throwable{
        action = mvc.perform(MockMvcRequestBuilders
                .post("/login")
                .content(asJsonString(loginForm))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("login process should be unsuccessful")
    public void loginProcessShouldBeUnsuccessful() throws Throwable{
        //action.andExpect(status().is(400));
        MvcResult result = action.andReturn();
        String login = result.getResponse().getContentAsString();
        Assert.assertEquals("false", login);

    }

    /**
     *
     */

    @Given("comit app login page, valid user with a wrong username and valid password")
    public void comitAppLoginPageValidUserWithAWrongUsernameAndValidPassword(DataTable table) throws Throwable{
        addMockUserToUserRepository();
        List<String> data = table.row(1);
        loginForm = new LoginForm(data.get(0), data.get(1) );
    }

    @When("the user fill login form with this wrong username and valid password")
    public void theUserFillLoginFormWithThisWrongUsernameAndValidPassword() throws Throwable{
        action = mvc.perform(MockMvcRequestBuilders
                .post("/login")
                .content(asJsonString(loginForm))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("login process with wrong username should be unsuccessful")
    public void loginProcessWithWrongUsernameShouldBeUnsuccessful() throws Throwable{
        //action.andExpect(status().is(400));
        MvcResult result = action.andReturn();
        String login = result.getResponse().getContentAsString();
        Assert.assertEquals("false", login);
    }
}


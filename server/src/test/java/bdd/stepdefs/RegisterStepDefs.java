package bdd.stepdefs;

import com.comit.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;

//@SpringBootCucumberTest
//@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
public class RegisterStepDefs {

    @Autowired
    private MockMvc mvc;

    ResultActions action;

    @Given("^comit app register page$")
    public void comitAppRegisterPage() throws Throwable {
    }

    @When("^the user fill register form, User object is posted$")
    public void theUserFillRegisterFormUserObjectIsPosted() throws Throwable {
        User user = new User("Zaphod", "zaphod@galaxy.net","eheheh","eheheh","USER");
        action = mvc.perform(MockMvcRequestBuilders
                .post("/register")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

    }

    @Then("^Registration process should be successful$")
    public void registrationProcessShouldBeSuccessful() throws Throwable {
        action.andExpect(status().is(200));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

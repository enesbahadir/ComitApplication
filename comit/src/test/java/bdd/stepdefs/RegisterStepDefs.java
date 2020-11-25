package bdd.stepdefs;

import bdd.SpringBootCucumberTest;
import com.comit.payload.UserForm;
import com.comit.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootCucumberTest
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
public class RegisterStepDefs {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    ResultActions action;

    long databaseSizeBeforeCreate;

    @Given("^comit app register page$")
    public void comitAppRegisterPage() throws Throwable {

        databaseSizeBeforeCreate = userRepository.count();
    }

    @When("^the user fill register form, User object is posted$")
    public void theUserFillRegisterFormUserObjectIsPosted() throws Throwable {
        UserForm userForm = new UserForm("Mockuser", "zaphod@galaxynet","eheheh","eheheh",
                new HashSet<>(Collections.singletonList("USER")));
        //String requestBody = "{ \"username\":\"enes, \"password\": \"password\",\"name\": \"enes\",\"surName\": \"enes\",\"type\":\"USER\"}";

        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/auth/signup")
                .content(asJsonString(userForm))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

    }

    @Then("^Registration process should be successful$")
    public void registrationProcessShouldBeSuccessful() throws Throwable {
        action.andExpect(status().is(200));
        assertThat(userRepository.count()).isEqualTo(databaseSizeBeforeCreate+1);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

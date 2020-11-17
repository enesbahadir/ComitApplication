package bdd.stepdefs;

import bdd.SpringBootCucumberTest;
import com.comit.model.Product;
import com.comit.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootCucumberTest
//@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
public class ProductStepDefs {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    ResultActions action;
    private final Product product = new Product("mockProduct","New Mock Description", 100L, null);

    public void addNewMockProduct ()
    {
        productRepository.save(product);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // İlk Senaryo
    //Admin type user should add new prodcut
    @Given("comit app prodcut add form page")
    public void comitAppProdcutAddFormPage() throws Throwable {
    }

    @When("user fill product add form, new product is posted")
    public void userFillProductAddFormNewProductIsPosted() throws Throwable {
        action = mvc.perform(MockMvcRequestBuilders
                .post("/products")
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("adding process should be successful")
    public void addingProcessShouldBeSuccessful() throws Throwable {
        action.andExpect(status().is(200));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // İkinci Senaryo
    //Admin type user should edit a product

    @Given("comit app prodcut edit form page")
    public void comitAppProdcutEditFormPage() throws Throwable{
        addNewMockProduct();
    }

    @When("user fill product edit form, edited product is posted")
    public void userFillProductEditFormEditedProductIsPosted(DataTable dataTable) throws Throwable{
        List<String> data = dataTable.row(1);
        Product editedProduct = new Product(data.get(0), data.get(1), Long.valueOf(data.get(2)), null);
        action = mvc.perform(MockMvcRequestBuilders
                .put("/products/2")
                .content(asJsonString(editedProduct))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Then("editing process should be successful")
    public void editingProcessShouldBeSuccessful() throws Throwable{
        action.andExpect(status().is(200));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Üçüncü  Senaryo
    //Admin type user should delete a product
    @Given("comit app product delete page")
    public void comitAppProductDeletePage() throws Throwable{
        addNewMockProduct();
    }

    @When("user push delete button")
    public void userPushDeleteButton() throws Throwable{
        action = mvc.perform(MockMvcRequestBuilders
                .delete("/products/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Then("delete process should be successful")
    public void deleteProcessShouldBeSuccessful() throws Throwable{
        action.andExpect(status().is(200));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Dördüncü  Senaryo
    //All users should see product list

    @Given("comit app shop page")
    public void comitAppShopPage() {
    }

    @When("user should see all products")
    public void userShouldSeeAllProducts() {
    }

    @Then("listing process should be successfull")
    public void listingProcessShouldBeSuccessfull() {
    }
}

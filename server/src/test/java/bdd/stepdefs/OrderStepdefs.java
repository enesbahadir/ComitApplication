package bdd.stepdefs;

import bdd.SpringBootCucumberTest;
import com.comit.model.Order;
import com.comit.model.OrderForm;
import com.comit.model.Product;
import com.comit.model.User;
import com.comit.repository.OrderProductRepository;
import com.comit.repository.OrderRepository;
import com.comit.repository.ProductRepository;
import com.comit.repository.UserRepository;
import com.comit.service.ProductService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
@SpringBootCucumberTest
public class OrderStepdefs {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private final OrderForm orderFrom = new OrderForm();
    ResultActions action;

    public OrderStepdefs() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Birinci  Senaryo
    //An order should be added successfully

    @Given("comit app check out page")
    public void comitAppCheckOutPage() throws Throwable
    {
        addMockProduct();
        addMockUser();
        orderFrom.setLocalDate("2020/11/11");
    }

    @When("user select products and push Complete Order button")
    public void userSelectProductsAndPushCompleteOrderButton () throws Throwable {
        action = mvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .content(ProductStepDefs.asJsonString(orderFrom))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

    }

    @Given("An order is successfully added")
    public void anOrderIsSuccessfullyAdded() throws Throwable {
        action.andExpect(status().is(200));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // İkinci  Senaryo
    //Admin type user should see all orders

    @Given("comit app orders page")
    public void comitAppOrdersPage() throws Throwable {
        addMockUser();
        addMockProduct();
        action = mvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .content(ProductStepDefs.asJsonString(orderFrom))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
        action.andExpect(status().is(200));
    }

    @When("admin type user push orders, open orders page")
    public void adminTypeUserPushOrdersOpenOrdersPage() throws Throwable {
        action = mvc.perform(MockMvcRequestBuilders
                .get("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Then("admin type user should see all orders")
    public void adminTypeUserShoulSeeAllOrders() throws Throwable {
        action.andExpect(status().is(200));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    // Üçüncü  Senaryo
    //User type user should see his orders

    @Given("comit app orders page with user type user")
    public void comitAppOrdesPageWithUserTypeUser() throws Throwable{
        addMockProduct();
        addMockUser();
        addMockUser2();
        action = mvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .content(ProductStepDefs.asJsonString(orderFrom))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
        action.andExpect(status().is(200));
    }

    @When("user type user push orders, open orders page")
    public void userTypeUserPushOrdersOpenOrdersPage() throws Throwable{
        action = mvc.perform(MockMvcRequestBuilders
                .get("/api/orders/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Then("user type user should see his orders")
    public void userTypeUserShouldSeeHisOrders() throws Throwable{
        action.andExpect(status().is(200));
        //action.andExpect(content().string("[]"));
    }

    public void addMockProduct() {
        Product mockProduct1 = new Product("product1","mock product1",1200L,null);
        mockProduct1 = productService.createProduct(mockProduct1);
        Product mockProduct2 = new Product("product2","mock product2",2200L,null);
        mockProduct2 = productService.createProduct(mockProduct2);
        Product mockProduct3 = new Product("product3","mock product3",3200L,null);
        mockProduct3 = productService.createProduct(mockProduct3);
        Product mockProduct4 = new Product("product4","mock product4",4200L,null);
        mockProduct4 = productService.createProduct(mockProduct4);
        Product mockProduct5 = new Product("product5","mock product5",5200L,null);
        mockProduct5 = productService.createProduct(mockProduct5);
        List<Product> products = new ArrayList<>();
        products.add(mockProduct1);
        products.add(mockProduct2);
        products.add(mockProduct3);
        products.add(mockProduct4);
        products.add(mockProduct5);
        orderFrom.setProducts(products);
    }

    public void addMockUser() {
        User user = new User("enes","password","enes","enes","USER");
        user.setId(1);
        userRepository.save(user);
        orderFrom.setUser(user);
    }

    public void addMockUser2() {
        User user = new User("bahadir","password","bahadir","bahadir","USER");
        user.setId(2);
        userRepository.save(user);
    }



}

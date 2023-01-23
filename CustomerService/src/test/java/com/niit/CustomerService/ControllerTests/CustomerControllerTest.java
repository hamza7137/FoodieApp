//package com.niit.CustomerService.ControllerTests;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.niit.CustomerService.controller.CustomerController;
//import com.niit.CustomerService.domain.Cuisine;
//import com.niit.CustomerService.domain.Restaurant;
//import com.niit.CustomerService.domain.User;
//import com.niit.CustomerService.domain.UserRole;
//import com.niit.CustomerService.service.CustomerService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static com.niit.CustomerService.domain.UserRole.USER;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CustomerControllerTest {
//
//    /**
//     *  First : Change all @Put Mappings to @Delete Mappings in CustomerController.java.
//     *  Second : Change all the return types of CustomerService delete methods to boolean.
//     *  Then this test cases will work.
//     */
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private CustomerService customerService;
//
//    private User user1, user2;
//    private UserRole userRole;
//    private Cuisine cuisine1, cuisine2;
//    private Restaurant restaurant1, restaurant2;
//    private List<Cuisine> cuisines;
//    private List<Cuisine> cart;
//    private List<Restaurant> favouriteRestaurant;
//    private List<Cuisine> favouriteCuisine;
//
//    @InjectMocks
//    private CustomerController customerController;
//
//    @BeforeEach
//    public void setUp() {
//        userRole = USER;
//        cuisine1 = new Cuisine("Palak Paneer","Must Try",275.5f,4.5f,null);
//        cuisine2 = new Cuisine("Idli","Good Taste",100f,4.0f,null);
//        cuisines = Arrays.asList(cuisine1,cuisine2);
//        cart = Arrays.asList(cuisine2);
//        restaurant1 = new Restaurant("apnarestaurant@gmail.com","Apna Restaurant",null,7770092739l,"Akola",cuisines);
//        favouriteRestaurant = Arrays.asList(restaurant1);
//        favouriteCuisine = Arrays.asList(cuisine2);
//        user1 = new User("hsyed2915@gmail.com","Hamza@123","Hamza",userRole,9890866705l,"Akola",cart,favouriteRestaurant,favouriteCuisine);
//
//        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        userRole = null;
//        cuisine1 = null;
//        cuisine2 = null;
//        cuisines = null;
//        cart = null;
//        restaurant1 = null;
//        favouriteRestaurant = null;
//        favouriteCuisine = null;
//        user1 = null;
//    }
//
//    @Test
//    public void givenUserToRegisterReturnUserRegisterSuccess() throws Exception {
//        // record your expectation
//        when(customerService.registerCustomer(any())).thenReturn(user1);
//        // verify the expectations
//        mockMvc.perform( post("/api/v3/registercustomer")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(user1)) // here the user1 is String
//                        .characterEncoding("UTF-8") )
//                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//        // so in registerCustomer() what we are passing is String so hashcode or address will be different that's why passing any().
//        verify(customerService, times(1)).registerCustomer(any());
//    }
//
//    @Test
//    public void givenUserCodeToUpdateUser() throws Exception {
//        when(customerService.updateCustomer(any())).thenReturn(user2);
//        mockMvc.perform( put("/api/v3/updateCustomer")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(user1))
//                        .characterEncoding("UTF-8") )
//                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//        verify(customerService, times(1)).updateCustomer(any());
//    }
//
//    @Test
//    public void addFavouriteRestaurantSuccess() throws Exception {
//        when(customerService.addFavouriteRestaurant(any(),anyString())).thenReturn(user1);
//        mockMvc.perform( post("/api/v3/addfavrestuarant/hsyed2915@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(user1))
//                        .characterEncoding("UTF-8") )
//                .andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
//        verify(customerService, times(1)).addFavouriteRestaurant(any(),anyString());
//    }
//
//    @Test
//    public void addFavouriteCuisineSuccess() throws Exception {
//        when(customerService.addFavouriteCuisine(any(),anyString())).thenReturn(user1);
//        mockMvc.perform( post("/api/v3/addfavcuisine/hsyed2915@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(user1))
//                        .characterEncoding("UTF-8") )
//                .andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
//        verify(customerService, times(1)).addFavouriteCuisine(any(),anyString());
//    }
//
//    @Test
//    public void addToCartSuccess() throws Exception {
//        when(customerService.addToCart(any(),anyString())).thenReturn(user1);
//        mockMvc.perform( post("/api/v3/addtocart/hsyed2915@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(user1))
//                        .characterEncoding("UTF-8") )
//                .andExpect(status().isAccepted()).andDo(MockMvcResultHandlers.print());
//        verify(customerService, times(1)).addToCart(any(),anyString());
//    }
//
//    @Test
//    public void getFavouriteRestaurantSuccess() throws Exception {
//        when(customerService.getFavouriteRestaurant(anyString())).thenReturn(favouriteRestaurant);
//        mockMvc.perform( get("/api/v3/getfavrestaurant/hsyed2915@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(favouriteRestaurant))
//                        .characterEncoding("UTF-8") )
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(customerService, times(1)).getFavouriteRestaurant(anyString());
//    }
//
//    @Test
//    public void getFavouriteCuisineSuccess() throws Exception {
//        when(customerService.getFavouriteCuisine(anyString())).thenReturn(favouriteCuisine);
//        mockMvc.perform( get("/api/v3/getfavcuisine/hsyed2915@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(favouriteCuisine))
//                        .characterEncoding("UTF-8") )
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(customerService, times(1)).getFavouriteCuisine(anyString());
//    }
//
//    @Test
//    public void getItemsFromCartSuccess() throws Exception {
//        when(customerService.getFromCart(anyString())).thenReturn(cart);
//        mockMvc.perform( get("/api/v3/getfromcart/hsyed2915@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(cart))
//                        .characterEncoding("UTF-8") )
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(customerService, times(1)).getFromCart(anyString());
//    }
//
//    @Test
//    public void deleteFromFavouriteRestaurantSuccess() throws Exception {
//        when(customerService.deleteFromFavouriteRestaurant(anyString(),anyString())).thenReturn(user1);
//        mockMvc.perform( delete("/api/v3/deletefavrestaurant/hsyed2915@gmail.com/Apna Restaurant") )
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(customerService, times(1)).deleteFromFavouriteRestaurant(anyString(),anyString());
//    }
//
//    @Test
//    public void deleteFromFavouriteCuisineSuccess() throws Exception {
//        when(customerService.deleteFromFavouriteCuisine(anyString(),anyString())).thenReturn(user1);
//        mockMvc.perform( delete("/api/v3/deletefavcuisine/hsyed2915@gmail.com/Idli") )
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(customerService, times(1)).deleteFromFavouriteCuisine(anyString(),anyString());
//    }
//
//    @Test
//    public void deleteFromCartSuccess() throws Exception {
//        when(customerService.deleteFromCart(anyString(),anyString())).thenReturn(user1);
//        mockMvc.perform( delete("/api/v3/deletefromcart/Idli/hsyed2915@gmail.com") )
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(customerService, times(1)).deleteFromCart(anyString(),anyString());
//    }
//
////    @Test
////    public void getCustomerSuccess() throws Exception {
////        when(customerService.getCustomerByEmailId(anyString())).thenReturn(user1);
////        mockMvc.perform( get("/api/v3/getcustomer/hsyed2915@gmail.com")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(jsonToString(user1))
////                        .characterEncoding("UTF-8") )
////                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
////        verify(customerService, times(1)).getCustomerByEmailId(anyString());
////    }
//
//    private static String jsonToString(final Object ob) throws JsonProcessingException {
//        String result;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonContent = mapper.writeValueAsString(ob);
//            result = jsonContent;
//        } catch(JsonProcessingException e) {
//            result = "JSON processing error";
//        }
//        return result;
//    }
//
//}
//

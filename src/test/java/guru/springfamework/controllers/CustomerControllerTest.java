package guru.springfamework.controllers;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.api.v1.model.CustomerListDto;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    public static final String TEST_FIRST_1 = "TestFirst1";
    public static final String TEST_LAST_1 = "TestLast1";
    public static final long ID_1 = 1L;
    public static final String TEST_FIRST_2 = "TestFirst2";
    public static final String TEST_LAST_2 = "TestLast2";
    public static final long ID_2 = 2L;
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getCustomers() {
        //given
        CustomerDto customerDto1 = CustomerDto.builder()
                .firstName(TEST_FIRST_1)
                .lastName(TEST_LAST_1)
                .customerUrl(PathConstants.CUSTOMERS_V1 + "/" + ID_1)
                .build();
        CustomerDto customerDto2 = CustomerDto.builder()
                .firstName(TEST_FIRST_2)
                .lastName(TEST_LAST_2)
                .customerUrl(PathConstants.CUSTOMERS_V1 + "/" + ID_2)
                .build();
        CustomerListDto customerListDto = new CustomerListDto(Arrays.asList(customerDto1,customerDto2));
        when(customerService.getCustomers()).thenReturn(customerListDto);

        //when-then
        try{
            mockMvc.perform(get(PathConstants.CUSTOMERS_V1))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("$.customers",hasSize(2)));
        } catch(Exception e){
            fail(e.getMessage());
        }

    }

    @Test
    public void getCustomerById() {
        //given
        CustomerDto customerDto1 = CustomerDto.builder()
                .firstName(TEST_FIRST_1)
                .lastName(TEST_LAST_1)
                .customerUrl(PathConstants.CUSTOMERS_V1 + "/" + ID_1)
                .build();
        when(customerService.getCustomerById(ID_1)).thenReturn(customerDto1);

        try{
            mockMvc.perform(get(PathConstants.CUSTOMERS_V1 + "/" + ID_1))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath("$.firstname",equalTo(TEST_FIRST_1)))
                    .andExpect(jsonPath("$.lastname",equalTo(TEST_LAST_1)))
                    .andExpect(jsonPath("$.customer_url",equalTo(PathConstants.CUSTOMERS_V1 + "/" + ID_1)));
        } catch(Exception e){
            fail(e.getMessage());
        }

    }
}
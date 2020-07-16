package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.api.v1.model.CustomerListDto;
import guru.springfamework.controllers.PathConstants;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerMapper customerMapper;

    @InjectMocks
    CustomerServiceImpl customerService;

    public static final Long ID_1 = 1L;
    public static final Long ID_2 = 2L;
    public static final int RETURN_SIZE = 2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCustomers() {
        //given
        Customer customer1 = Customer.builder().id(ID_1).build();
        Customer customer2 = Customer.builder().id(ID_2).build();
        CustomerDto customerDto1 = CustomerDto.builder().customerUrl(PathConstants.CUSTOMERS_V1 + "/" + ID_1).build();
        CustomerDto customerDto2 = CustomerDto.builder().customerUrl(PathConstants.CUSTOMERS_V1 + "/" + ID_2).build();
        List<Customer> customers = Arrays.asList(customer1,customer2);
        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.customerToCustomerDto(customer1)).thenReturn(customerDto1);
        when(customerMapper.customerToCustomerDto(customer2)).thenReturn(customerDto2);

        //when
        CustomerListDto returnCustomers = customerService.getCustomers();

        //then
        assertEquals(RETURN_SIZE,returnCustomers.getCustomers().size());
        verify(customerRepository,times(1)).findAll();
        verify(customerMapper,times(2)).customerToCustomerDto(any());
    }

    @Test
    public void getCustomerById() {
        //given
        Customer customer1 = Customer.builder().id(ID_1).build();
        CustomerDto customerDto1 = CustomerDto.builder().customerUrl(PathConstants.CUSTOMERS_V1 + "/" + ID_1).build();
        when(customerRepository.findById(ID_1)).thenReturn(Optional.of(customer1));
        when(customerMapper.customerToCustomerDto(customer1)).thenReturn(customerDto1);

        //when
        CustomerDto returnCustomer = customerService.getCustomerById(ID_1);
        Long returnedID = Long.parseLong(returnCustomer.getCustomerUrl().split("/")[4]);

        //then
        assertEquals(ID_1,returnedID);
        verify(customerRepository,times(1)).findById(anyLong());
    }
}
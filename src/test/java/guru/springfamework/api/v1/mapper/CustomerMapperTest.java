package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.controllers.PathConstants;
import guru.springfamework.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerMapperTest {


    private static final String TEST_LAST = "TestLastName";
    private static final String TEST_FIRST = "TestFirstName";
    public static final long TEST_ID = 1L;

    @Autowired
    CustomerMapper customerMapper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void customerToCustomerDto() {
        Customer customer = Customer.builder().id(TEST_ID).firstName(TEST_FIRST).lastName(TEST_LAST).build();

        CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);

        assertEquals(TEST_ID,Long.parseLong(customerDto.getCustomerUrl().split("/")[4]));
        assertEquals(TEST_LAST,customerDto.getLastName());
        assertEquals(TEST_FIRST,customerDto.getFirstName());
    }

    @Test
    public void customerDtoToCustomer() {
        CustomerDto customerDto = CustomerDto.builder().firstName(TEST_FIRST).lastName(TEST_LAST)
                        .customerUrl(PathConstants.CUSTOMERS_V1+TEST_ID).build();

        Customer customer = customerMapper.customerDtoToCustomer(customerDto);

        assertNull(customer.getId());
        assertEquals(TEST_LAST,customer.getLastName());
        assertEquals(TEST_FIRST,customer.getFirstName());
    }
}
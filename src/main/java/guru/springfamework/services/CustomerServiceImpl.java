package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.api.v1.model.CustomerListDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerListDto getCustomers() {
        return null;
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return null;
    }
}

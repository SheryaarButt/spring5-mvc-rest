package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.api.v1.model.CustomerListDto;

public interface CustomerService {
    CustomerListDto getCustomers();
    CustomerDto getCustomerById(Long id);
}

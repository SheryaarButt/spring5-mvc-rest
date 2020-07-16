package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.api.v1.model.CustomerListDto;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerListDto getCustomers() {
        return new CustomerListDto(customerRepository
                                    .findAll()
                                    .stream()
                                    .map(customerMapper::customerToCustomerDto)
                                    .collect(Collectors.toList()));
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return customerMapper.customerToCustomerDto(customerRepository.findById(id).orElse(null));
    }
}

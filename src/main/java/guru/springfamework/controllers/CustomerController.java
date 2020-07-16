package guru.springfamework.controllers;

import guru.springfamework.api.v1.model.CustomerDto;
import guru.springfamework.api.v1.model.CustomerListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    public ResponseEntity<CustomerListDto> getCustomers(){
        return null;
    }

    public ResponseEntity<CustomerDto> getCustomerById(){
        return null;
    }
}

package com.example.TicketSale.service;

import com.example.TicketSale.DataGenerator;
import com.example.TicketSale.dto.CustomerConverter;
import com.example.TicketSale.dto.CustomerCreationDto;
import com.example.TicketSale.dto.CustomerDto;
import com.example.TicketSale.exception.CustomerMailExistException;
import com.example.TicketSale.exception.CustomerNotFoundException;
import com.example.TicketSale.exception.CustomerPhoneExistException;
import com.example.TicketSale.model.Customer;
import com.example.TicketSale.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.*;
class CustomerServiceTest {

    private final DataGenerator dataGenerator = new DataGenerator();

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerConverter customerConverter;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp(){
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerConverter = Mockito.mock(CustomerConverter.class);
        passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

        customerService = new CustomerService(customerRepository, passwordEncoder, customerConverter);
    }


    @DisplayName("Test createCustomer Method: Create Customer Called With Valid Request it Should Return Valid CustomerDto Test is succesfull")
    @Test
    public void testCreateCustomer_whenCalledWithValidRequest_itShouldReturnValidCustomerDto() {
        CustomerCreationDto customerCreationDTO = dataGenerator.generateCustomerCreationDTO();
        Customer customer = dataGenerator.generateCustomer();
        CustomerDto expectedCustomerDto = dataGenerator.generateCustomerDTO();

        Mockito.when(customerRepository.findByMail(customerCreationDTO.getMail())).thenReturn(Optional.empty());
        Mockito.when(customerRepository.findByPhone(customerCreationDTO.getPhone())).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode(customerCreationDTO.getPassword())).thenReturn(customerCreationDTO.getPassword());
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.when(customerConverter.toCustomerDTO(customer)).thenReturn(expectedCustomerDto);

        CustomerDto actual = customerService.createCustomer(customerCreationDTO);
        Assertions.assertEquals(expectedCustomerDto, actual);

        Mockito.verify(customerRepository).findByMail(customerCreationDTO.getMail());
        Mockito.verify(customerRepository).findByPhone(customerCreationDTO.getPhone());
        Mockito.verify(passwordEncoder).encode(customerCreationDTO.getPassword());
        Mockito.verify(customerRepository).save(customer);
        Mockito.verify(customerConverter).toCustomerDTO(customer);
    }

    @DisplayName("Test CreateCustomer Method: Throw CustomerMailExistException When Create Customer Method Called with Existing Mail")
    @Test
    public void testCreateCustomer_whenCalledWithExistingMail_itThrowsCustomerMailExisException() {
        CustomerCreationDto customerCreationDTO = dataGenerator.generateCustomerCreationDTO();

        Mockito.when(customerRepository.findByMail(customerCreationDTO.getMail())).thenReturn(Optional.of(Customer.builder().build()));

        Exception exception = Assertions.assertThrows(CustomerMailExistException.class,
                () -> customerService.createCustomer(customerCreationDTO));
        Assertions.assertEquals(exception.getMessage(), "This mail is registered in database:" + customerCreationDTO.getMail());

        Mockito.verify(customerRepository).findByMail(customerCreationDTO.getMail());
        Mockito.verifyNoInteractions(passwordEncoder);
        Mockito.verifyNoInteractions(customerConverter);
    }

    @DisplayName("Test CreateCustomer Method: Throw CustomerPhoneExistException When Create Customer Method Called with Existing Phone")
    @Test
    public void testCreateCustomer_whenCalledWithExistingPhone_itThrowsCustomerPhoneExistException() {
        CustomerCreationDto customerCreationDTO = dataGenerator.generateCustomerCreationDTO();

        Mockito.when(customerRepository.findByMail(customerCreationDTO.getMail())).thenReturn(Optional.empty());
        Mockito.when(customerRepository.findByPhone(customerCreationDTO.getPhone())).thenReturn(Optional.of(Customer.builder().build()));

        Exception exception = Assertions.assertThrows(CustomerPhoneExistException.class,
                () -> customerService.createCustomer(customerCreationDTO));

        Assertions.assertEquals(exception.getMessage(), "This phone is registered in database:" + customerCreationDTO.getPhone());

        Mockito.verify(customerRepository).findByMail(customerCreationDTO.getMail());
        Mockito.verify(customerRepository).findByPhone(customerCreationDTO.getPhone());
        Mockito.verifyNoInteractions(passwordEncoder);
        Mockito.verifyNoInteractions(customerConverter);
    }

    @DisplayName("Test findById(): When Customer Id Exist it Should Return CustomerDto")
    @Test
    public  void testFindById_whenCustomerIdExist_itShouldReturnCustomerDto() {
        long id = 3931L;
        CustomerDto expectedCustomerDto = dataGenerator.generateCustomerDTO();
        Customer customer = dataGenerator.generateCustomer();

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        Mockito.when(customerConverter.toCustomerDTO(customer)).thenReturn(expectedCustomerDto);

        CustomerDto actual = customerService.findById(id);

        Assertions.assertEquals(expectedCustomerDto, actual );

        Mockito.verify(customerRepository).findById(id);
        Mockito.verify(customerConverter).toCustomerDTO(customer);
    }

    @DisplayName("Test findById Method: When Customer Id Does Not Exist it Should Throw Customer Not Found Exception")
    @Test
    public  void testFindById_whenCustomerIdDoesNotExist_itShouldThrowCustomerNotFoundException() {
        long id = 3931L;

        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.empty());


        Exception exception = Assertions.assertThrows(CustomerNotFoundException.class,
                () -> customerService.findById(id));
        Assertions.assertEquals("Customer is not founded! Id:" + id, exception.getMessage());

        Mockito.verify(customerRepository).findById(id);
        Mockito.verifyNoInteractions(customerConverter);
    }

    @DisplayName("findAll Method Test: When No Customer In Database it Return Empty List")
    @Test
    public void testFindAll_NoCustomersInDatabase_ReturnsEmptyList() {
        List<CustomerDto> emptyCustomerDtoList = new ArrayList<>();
        List<Customer> emptyCustomerList = new ArrayList<>();

        Mockito.when(customerRepository.findAll()).thenReturn(emptyCustomerList);

        List<CustomerDto> result = customerService.findAll();

        Assertions.assertEquals(emptyCustomerDtoList, result);

        Mockito.verify(customerRepository).findAll();
    }

    @DisplayName("findAll Method Test: When Customers In Database it Returns CustomerDto List")
    @Test
    public void testFindAll_CustomerInDatabase_ReturnsCustomerDtoList(){
        Customer customer1 = dataGenerator.generateCustomer();
        Customer customer2 = new Customer();
        CustomerDto customerDto1 = dataGenerator.generateCustomerDTO();
        CustomerDto customerDto2 = new CustomerDto();

        List<Customer> customers = Arrays.asList(customer1, customer2);
        List<CustomerDto> customerDtoList = Arrays.asList(customerDto1, customerDto2);

        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        Mockito.when(customerConverter.toCustomerDTO(customer1)).thenReturn(customerDto1);
        Mockito.when(customerConverter.toCustomerDTO(customer2)).thenReturn(customerDto2);

        List<CustomerDto> actualCustomerDtoList = customerService.findAll();
        Assertions.assertEquals(customerDtoList, actualCustomerDtoList);

        Mockito.verify(customerRepository).findAll();
        Mockito.verify(customerConverter).toCustomerDTO(customer1);
        Mockito.verify(customerConverter).toCustomerDTO(customer2);
    }


}
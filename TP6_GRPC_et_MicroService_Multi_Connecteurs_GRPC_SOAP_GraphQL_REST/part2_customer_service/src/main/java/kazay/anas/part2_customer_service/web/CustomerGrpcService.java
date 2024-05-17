package kazay.anas.part2_customer_service.web;

import io.grpc.stub.StreamObserver;
import kazay.anas.part2_customer_service.entities.Customer;
import kazay.anas.part2_customer_service.mappers.CustomerMapper;
import kazay.anas.part2_customer_service.repository.CustomerRepository;
import kazay.anas.part2_customer_service.stub.CustomerServiceGrpc;
import kazay.anas.part2_customer_service.stub.CustomerServiceOuterClass;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public void getAllCustomers(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetAllCustomersResponse> responseObserver) {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerServiceOuterClass.Customer> customers =
                customerList.stream().map(customerMapper::fromCustomer)
                        .collect(Collectors.toList());
        CustomerServiceOuterClass.GetAllCustomersResponse customersResponse =
            CustomerServiceOuterClass.GetAllCustomersResponse.newBuilder()
                .addAllCustomers(customers)
                .build();

        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        Customer customer = customerRepository.findById(request.getId()).orElse(null);
        CustomerServiceOuterClass.GetCustomerByIdResponse customerResponse =
            CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
                .setCustomer(customerMapper.fromCustomer(customer))
                .build();
        responseObserver.onNext(customerResponse);

    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
        Customer customer = customerMapper.fromCustomerRequest(request.getCustomer());
        Customer saved = customerRepository.save(customer);
        CustomerServiceOuterClass.SaveCustomerResponse customerResponse =
            CustomerServiceOuterClass.SaveCustomerResponse.newBuilder()
                .setCustomer(customerMapper.fromCustomer(saved))
                .build();
        responseObserver.onNext(customerResponse);
        responseObserver.onCompleted();
    }
}

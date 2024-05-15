package kazay.anas.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import kazay.anas.stubs.Bank;
import kazay.anas.stubs.BankServiceGrpc;

import java.io.IOException;

public class BankGrpcClient2 {
    public static void main(String[] args) throws IOException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 5555)
                .usePlaintext()
                .build();

            BankServiceGrpc.BankServiceStub asyncStub = BankServiceGrpc.newStub(managedChannel);

            Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                    .setCurrencyFrom("MAD")
                    .setCurrencyTo("USD")
                    .setAmount(6500)
                    .build();

            asyncStub.convert(request, new StreamObserver<Bank.ConvertCurrencyResponse>() {
                @Override
                public void onNext(Bank.ConvertCurrencyResponse value) {
                    System.out.println(value);
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println(t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Completed");
                }
            });

            System.in.read();
    }
}

package kazay.anas.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import kazay.anas.stubs.Bank;
import kazay.anas.stubs.BankServiceGrpc;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class BankGrpcClient4 {
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

        StreamObserver<Bank.ConvertCurrencyRequest> streamObserver =
                asyncStub.performStream(new StreamObserver<Bank.ConvertCurrencyResponse>() {
            @Override
            public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
                System.out.println(convertCurrencyResponse);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("Completed");
            }
        });
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int counter=0;
            @Override
            public void run() {
                Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                        .setCurrencyFrom("MAD")
                        .setCurrencyTo("USD")
                        .setAmount((float) (6500*Math.random()))
                        .build();
                streamObserver.onNext(request);
                System.out.println("Request sent "+counter);
                counter++;
                if (counter==10){
                    streamObserver.onCompleted();
                    timer.cancel();
                }
            }
        }, 1000, 1000);

        System.in.read();
    }
}

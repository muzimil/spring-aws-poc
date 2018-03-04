package com.poc.spring.aws.poc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.ErrorMessage;

@SpringBootApplication
@EnableBinding(Processor.class)
public class PocApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocApplication.class, args);
	}
	
	@StreamListener(Processor.INPUT)
    public void transform(String message) {
        if (!"junk".equals(message)) {
            System.out.println("processor"+message);
        }
        else {
            throw new IllegalStateException("Invalid payload: " + message);
        }
    }
	
	 @Bean(name = Processor.INPUT + "." + "grp" + ".errors")
     public SubscribableChannel consumerErrorChannel2() {
         return new PublishSubscribeChannel();
     }

     @ServiceActivator(inputChannel="errorChannel")
     public void processMessage(ErrorMessage message) {
         System.out.println("Errror has been reported errorMessage"+message);
     }

     @StreamListener("errorChannel")
     public void processMessagea(ErrorMessage message) {
         System.out.println("Errror has been reported errorMessage"+message);
     }

     @StreamListener("errorChannel")
     public void processMessagea(Message message) {
         System.out.println("Errror has been reported errorMessage"+message);
     }

     @ServiceActivator(inputChannel="errorChannel")
     public void processMessage2(Message message) {
         System.out.println("Errror has been reported message"+message);
     }

     @ServiceActivator(inputChannel=Processor.INPUT + "." + "grp" + ".errors")
     public void processMessage22(Message message) {
         System.out.println("Errror has been reported message"+message);
     }

     @ServiceActivator(inputChannel=Processor.INPUT + "." + "grp" + ".errors")
     public void processMessageg(ErrorMessage message) {
         System.out.println("Errror has been reported errorMessage"+message);
     }

     @StreamListener(Processor.INPUT + "." + "grp" + ".errors")
     public void processMessageaddd(ErrorMessage message) {
         System.out.println("Errror has been reported errorMessage"+message);
     }

     @StreamListener(Processor.INPUT + "." + "grp" + ".errors")
     public void processMessageaas(Message message) {
         System.out.println("Errror has been reported errorMessage"+message);
     }
}

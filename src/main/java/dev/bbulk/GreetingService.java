package dev.bbulk;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class GreetingService {

    @ConfigProperty(name = "bbulk_message")
    Provider<String> configString;

    @ConfigProperty(name = "bbulk_int")
    Provider<Integer> configNumber;

    public String getGreeting() {
        System.out.println("This tenant has number " + configNumber.get());
        return configString.get();
    }

}

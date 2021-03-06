package dev.bbulk;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Provider;

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

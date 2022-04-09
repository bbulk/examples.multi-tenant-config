package dev.bbulk;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Provider;

@ApplicationScoped
public class GreetingService {

    @ConfigProperty(name = "bbulk_message")
    Provider<String> configValue;

    @ConfigProperty(name = "bbulk_int")
    Provider<Integer> configNumber2;

    public String getGreeting() {
        System.out.println("This tenant has number " + configNumber2.get());
        return configValue.get();
    }

}

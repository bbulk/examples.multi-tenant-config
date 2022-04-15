package dev.bbulk.config;

import dev.bbulk.context.TenantInfo;
import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Singleton;
import java.util.Set;

@Startup
@Singleton
public class ContextEnrichmentSource implements ConfigSource {

    private static final String MY_CONFIGS_PREFIX = "bbulk_";
    public static final String TENANT_PREFIX = "tenant/";

    private static CDI<Object> cdi;

    @PostConstruct
    public void init() {
        cdi = CDI.current();
    }

    @Override
    public Set<String> getPropertyNames() {
        return Set.of();
    }

    @Override
    public int getOrdinal() {
        // execute this filter before anything else
        return 500;
    }

    @Override
    public String getValue(String propertyName) {
        if (isConfigured()) {
            if (!propertyName.startsWith(TENANT_PREFIX) && propertyName.startsWith(MY_CONFIGS_PREFIX)) {
                TenantInfo contextInfo = cdi.select(TenantInfo.class).get();
                String tenantPropertyName = TENANT_PREFIX + contextInfo.name + "." + propertyName;

                // find transformed propertyName
                Config config = ConfigProvider.getConfig();
                return config.getConfigValue(tenantPropertyName).getValue();
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "This source acts as a filter and adds the tenant information to predefined config properties";
    }

    private boolean isConfigured() {
        return cdi != null;
    }
}

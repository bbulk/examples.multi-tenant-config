package dev.bbulk.config;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Map;
import java.util.Set;

import static dev.bbulk.config.ContextEnrichmentSource.TENANT_PREFIX;
import static dev.bbulk.context.TenantInfo.DEFAULT_TENANT;

public class TenantConfigSource implements ConfigSource {

    private final Map<String, String> localConfig = Map.of(
            "tenant/someTenant1.bb_message", "Hello Ralf",
            "tenant/someTenant2.bb_message", "Hello everyone!",
            "tenant/someTenant3.bb_message", "Hi Karen",
            "tenant/someTenant4.bb_message", "Bonjour Laura!",
            "tenant/someTenant5.bb_message", "Hi Fred!");

    @Override
    public Set<String> getPropertyNames() {
        return localConfig.keySet();
    }

    @Override
    public String getValue(String propertyName) {
        if (propertyName.startsWith(TENANT_PREFIX)) {
            String configValue = localConfig.get(propertyName);
            // custom behavior, fallback to the default value instead of failing
            if (configValue == null) {
                String defaultProperty = TENANT_PREFIX + DEFAULT_TENANT + propertyName.substring(propertyName.indexOf("."));
                configValue = ConfigProvider.getConfig().getConfigValue(defaultProperty).getValue();
            }
            return configValue;
        }
        return null;
    }

    @Override
    public String getName() {
        return "Tenant-specific configuration";
    }
}

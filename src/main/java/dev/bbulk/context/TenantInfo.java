package dev.bbulk.context;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class TenantInfo {

    public static final String DEFAULT_TENANT = "default";

    public String name = DEFAULT_TENANT;
}

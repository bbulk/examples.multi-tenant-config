package dev.bbulk.context;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TenantFilter implements ContainerRequestFilter {

    @Inject
    TenantInfo tenantInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        containerRequestContext.getUriInfo().getPathParameters().get("tenant").stream()
                .findFirst()
                .ifPresent(contextValue -> tenantInfo.name = contextValue);
    }

}

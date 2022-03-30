package dev.bbulk.context;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class TenantFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    TenantInfo tenantInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        containerRequestContext.getUriInfo().getPathParameters().get("tenant").stream()
                .findFirst()
                .ifPresent(contextValue -> tenantInfo.name = contextValue);
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) {

    }
}

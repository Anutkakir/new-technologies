package com.ivan.newtechnologies.tenantmanager.rest;

import com.google.common.collect.Lists;
import com.ivan.newtechnologies.tenantmanager.model.Tenant;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/tenant-manager")
public class TenantManagerService {

    private boolean addNewTenant = false;

    @GET
    @Path("/tenant")
    @Produces(value = "application/json")
    public List<Tenant> getTenants() {
        System.out.println("Tenants have been fetched");

        if (!this.addNewTenant) {
            return Collections.emptyList();
        }

        return Lists.newArrayList(this.createNewTenant());
    }

    @GET
    @Path("/tenant/{id}")
    @Produces(value = "application/json")
    public Tenant getTenant(@PathParam("id") final String id) {
        System.out.println("Asked for tenant " + id);

        return this.createNewTenant();
    }

    @GET
    @Path("/toggle-new")
    public String addNewTenant() {
        System.out.println("New tenant will be returned");

        this.addNewTenant = !this.addNewTenant;

        return "done - " + this.addNewTenant;
    }

    private Tenant createNewTenant() {
        final Tenant tenant = new Tenant();
        tenant.setUid("tenant_from_manager");
        tenant.setName("Tenant from manager");
        tenant.setDomain_name("tenant2.oiengine.com");
        tenant.setSchema_name("dump");
        tenant.setCreated("2017-10-10T10:10:10.819405Z");
        tenant.setChanged("2017-10-10T10:10:10.819405Z");
        tenant.setCache_namespace("tenant2_cn");
        tenant.setSearch_index("content2");
        tenant.setMedia_storage("local:d:/TMP/media/media2");
        tenant.setCredentials(new HashMap<>());
        tenant.setGroup("local_test");

        return tenant;
    }

}

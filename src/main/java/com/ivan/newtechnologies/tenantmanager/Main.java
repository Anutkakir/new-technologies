package com.ivan.newtechnologies.tenantmanager;

import com.ivan.newtechnologies.tenantmanager.rest.TenantManagerService;
import org.wso2.msf4j.MicroservicesRunner;

public class Main {

    public static void main(String[] args) {
        new MicroservicesRunner()
                .deploy(new TenantManagerService())
                .start();
    }

}

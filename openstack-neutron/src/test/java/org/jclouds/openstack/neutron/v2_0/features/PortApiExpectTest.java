/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.openstack.neutron.v2_0.features;

import com.google.common.collect.ImmutableSet;
import org.jclouds.http.HttpResponse;
import org.jclouds.openstack.neutron.v2_0.domain.FixedIP;
import org.jclouds.openstack.neutron.v2_0.domain.Port;
import org.jclouds.openstack.neutron.v2_0.internal.BaseNeutronApiExpectTest;
import org.jclouds.openstack.neutron.v2_0.parse.ParsePortTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * Tests parsing of PortApi
 */
@Test(groups = "unit")
public class PortApiExpectTest extends BaseNeutronApiExpectTest {

    public void testListReturns2xx() {
        PortApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/ports").build(),
                HttpResponse.builder().statusCode(200).payload(payloadFromResourceWithContentType("/list_ports.json", APPLICATION_JSON)).build())
                .getPortApiForZone("region-a.geo-1");

        Set<? extends Port> ports = api.list().toSet();
        assertEquals(ports, ImmutableSet.of(Port.builder().id("ebe69f1e-bc26-4db5-bed0-c0afb4afe3db").adminStateUp(true)
                .deviceId("dhcpacb6d6f0-2d56-5c7a-9905-4c3a3d168f49-abaf2e70-4305-4e8d-ac70-3e4cc5f66a07")
                .deviceOwner("network:dhcp")
                .fixedIps(Arrays.asList(FixedIP.builder().ipAddress("10.0.0.2").subnetId("4156c7a5-e8c4-4aff-a6e1-8f3c7bc83861").build()))
                .macAddress("fa:16:3e:a9:34:c3")
                .name("test-port")
                .networkId("16dba3bc-f3fa-4775-afdc-237e12c72f6a")
                .securityGroups(Arrays.asList("49258a30-81f9-44d7-9440-c60dba50e29e"))
                .state(Port.State.ACTIVE)
                .tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build()));
    }

    public void testListReturns4xx() {
        PortApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/ports").build(),
                HttpResponse.builder().statusCode(404).build())
                .getPortApiForZone("region-a.geo-1");

        assertTrue(api.list().isEmpty());
    }

    public void testShowReturns2xx() {
        PortApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/ports/ebe69f1e-bc26-4db5-bed0-c0afb4afe3db").build(),
                HttpResponse.builder().statusCode(200).payload(payloadFromResourceWithContentType("/port.json", APPLICATION_JSON)).build())
                .getPortApiForZone("region-a.geo-1");

        Port port = api.get("ebe69f1e-bc26-4db5-bed0-c0afb4afe3db");
        assertEquals(port, new ParsePortTest().expected());
    }

    public void testShowReturns4xx() {
        PortApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/ports/ebe69f1e-bc26-4db5-bed0-c0afb4afe3db").build(),
                HttpResponse.builder().statusCode(404).build())
                .getPortApiForZone("region-a.geo-1");

        assertNull(api.get("ebe69f1e-bc26-4db5-bed0-c0afb4afe3db"));
    }

}

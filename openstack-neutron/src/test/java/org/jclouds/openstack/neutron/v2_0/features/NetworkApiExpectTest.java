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
import org.jclouds.openstack.neutron.v2_0.domain.Network;
import org.jclouds.openstack.neutron.v2_0.internal.BaseNeutronApiExpectTest;
import org.jclouds.openstack.neutron.v2_0.parse.ParseNetworkTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * Tests parsing and Guice wiring of NetworkApi
 */
@Test(groups = "unit", testName = "NetworkApiExpectTest")
public class NetworkApiExpectTest extends BaseNeutronApiExpectTest {

    public void testListReturns2xx() {
        NetworkApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/networks").build(),
                HttpResponse.builder().statusCode(200).payload(payloadFromResourceWithContentType("/list_networks.json", APPLICATION_JSON)).build())
                .getNetworkApiForZone("region-a.geo-1");

        Set<? extends Network> nets = api.list().toSet();
        assertEquals(nets, listOfNetworks());
    }

    public void testListReturns4xx() {
        NetworkApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/networks").build(),
                HttpResponse.builder().statusCode(404).build())
                .getNetworkApiForZone("region-a.geo-1");

        assertTrue(api.list().isEmpty());
    }

    public void testShowReturns2xx() {
        NetworkApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/networks/16dba3bc-f3fa-4775-afdc-237e12c72f6a").build(),
                HttpResponse.builder().statusCode(200).payload(payloadFromResourceWithContentType("/network.json", APPLICATION_JSON)).build())
                .getNetworkApiForZone("region-a.geo-1");

        Network net = api.get("16dba3bc-f3fa-4775-afdc-237e12c72f6a");
        assertEquals(net, new ParseNetworkTest().expected());
    }

    public void testShowReturns4xx() {
        NetworkApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/networks/16dba3bc-f3fa-4775-afdc-237e12c72f6a").build(),
                HttpResponse.builder().statusCode(404).build())
                .getNetworkApiForZone("region-a.geo-1");

        assertNull(api.get("16dba3bc-f3fa-4775-afdc-237e12c72f6a"));
    }

    protected Set<Network> listOfNetworks() {
        return ImmutableSet.of(
                Network.builder().name("jclouds-port-test").id("16dba3bc-f3fa-4775-afdc-237e12c72f6a")
                        .status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("wibble").id("1a104cf5-cb18-4d35-9407-2fd2646d9d0b").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("jclouds-test").id("31083ae2-420d-48b2-ac98-9f7a4fd8dbdc").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("jclouds-test").id("49c6d6fa-ff2a-459d-b975-75a8d31c9a89").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("wibble").id("5cb3d6f4-62cb-41c9-b964-ba7d9df79e4e").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("jclouds-port-test").id("5d51d012-3491-4db7-b1b5-6f254015015d").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("wibble").id("5f9cf7dc-22ca-4097-8e49-1cc8b23faf17").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("jclouds-test").id("6319ecad-6bff-48b2-9b53-02ede8cb7588").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("jclouds-port-test").id("6ba4c788-661f-49ab-9bf8-5f10cbbb2f57").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("jclouds-test").id("74ed170b-5069-4353-ab38-9719766dc57e").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("wibble").id("b71fcac1-e864-4031-8c5b-edbecd9ece36").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build(),
                Network.builder().name("jclouds-port-test").id("c7681895-d84d-4650-9ca0-82c72036b855").status(Network.Status.ACTIVE).subnets(Arrays.asList("e12f0c45-46e3-446a-b207-9474b27687a6"))
                        .adminStateUp(true).tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build()
        );
    }

}

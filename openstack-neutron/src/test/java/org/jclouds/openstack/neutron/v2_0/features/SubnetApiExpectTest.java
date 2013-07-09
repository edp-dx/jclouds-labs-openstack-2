/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.openstack.neutron.v2_0.features;

import com.google.common.collect.ImmutableSet;
import org.jclouds.http.HttpResponse;
import org.jclouds.openstack.neutron.v2_0.domain.IPRange;
import org.jclouds.openstack.neutron.v2_0.domain.Subnet;
import org.jclouds.openstack.neutron.v2_0.internal.BaseNeutronApiExpectTest;
import org.jclouds.openstack.neutron.v2_0.parse.ParseSubnetTest;
import org.testng.annotations.Test;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Andrey Trubitsyn
 */
@Test(groups = "unit")
public class SubnetApiExpectTest extends BaseNeutronApiExpectTest {

    public void testListReturns2xx() throws Exception {
        SubnetApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/subnets").build(),
                HttpResponse.builder().statusCode(200).payload(payloadFromResourceWithContentType("/list_subnets.json", APPLICATION_JSON)).build())
                .getSubnetApiForZone("region-a.geo-1");

        Set<? extends Subnet> nets = api.list().toSet();

        try {
            assertEquals(nets, ImmutableSet.of(Subnet.builder().id("4156c7a5-e8c4-4aff-a6e1-8f3c7bc83861").name("test-subnet").networkId("16dba3bc-f3fa-4775-afdc-237e12c72f6a")
                    .tenantId("afc75773-640e-403c-9fff-62ba98db1f19").allocationPools(Arrays.asList(IPRange.builder().start("10.10.0.2").end("10.10.0.254").build()))
                    .gatewayIp(Inet4Address.getByName("10.10.0.1")).ipVersion(4).cidr("10.10.0.0/24")
                    .enableDhcp(false).build()));
        } catch (UnknownHostException e) {
            throw new Exception("Please, use correct Gateway IP", e);
        }
    }

    public void testListReturns4xx() {
        SubnetApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/subnets").build(),
                HttpResponse.builder().statusCode(404).build())
                .getSubnetApiForZone("region-a.geo-1");

        assertTrue(api.list().isEmpty());
    }

    public void testShowReturns2xx() {
        SubnetApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/subnets/646c123b-871a-4124-9fa2-a94f04a582df").build(),
                HttpResponse.builder().statusCode(200).payload(payloadFromResourceWithContentType("/subnet.json", APPLICATION_JSON)).build())
                .getSubnetApiForZone("region-a.geo-1");

        Subnet subnet = api.get("646c123b-871a-4124-9fa2-a94f04a582df");
        assertEquals(subnet, new ParseSubnetTest().expected());
    }

    public void testShowReturns4xx() {
        SubnetApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPassword, responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint + "/subnets/646c123b-871a-4124-9fa2-a94f04a582df").build(),
                HttpResponse.builder().statusCode(404).build())
                .getSubnetApiForZone("region-a.geo-1");

        assertNull(api.get("646c123b-871a-4124-9fa2-a94f04a582df"));
    }

}

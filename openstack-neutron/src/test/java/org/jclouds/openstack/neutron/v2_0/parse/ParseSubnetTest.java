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
package org.jclouds.openstack.neutron.v2_0.parse;

import org.jclouds.json.BaseItemParserTest;
import org.jclouds.openstack.neutron.v2_0.domain.IPRange;
import org.jclouds.openstack.neutron.v2_0.domain.Subnet;
import org.jclouds.rest.annotations.SelectJson;
import org.testng.annotations.Test;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 *
 */
@Test(groups = "unit")
public class ParseSubnetTest extends BaseItemParserTest<Subnet> {

    @Override
    public String resource() {
        return "/subnet.json";
    }

    @Override
    @SelectJson("subnet")
    @Consumes(MediaType.APPLICATION_JSON)
    public Subnet expected() {
        try {
            return Subnet.builder().id("4156c7a5-e8c4-4aff-a6e1-8f3c7bc83861").name("test-subnet").networkId("16dba3bc-f3fa-4775-afdc-237e12c72f6a")
                    .tenantId("afc75773-640e-403c-9fff-62ba98db1f19").allocationPools(Arrays.asList(IPRange.builder().start("10.10.0.2").end("10.10.0.254").build()))
                    .gatewayIp(Inet4Address.getByName("10.10.0.1")).ipVersion(4).cidr("10.10.0.0/24")
                    .enableDhcp(false).build();
        } catch (UnknownHostException e) {
            return null;
        }
    }
}
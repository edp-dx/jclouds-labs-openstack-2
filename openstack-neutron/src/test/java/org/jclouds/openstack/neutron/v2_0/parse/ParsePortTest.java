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
import org.jclouds.openstack.neutron.v2_0.domain.FixedIP;
import org.jclouds.openstack.neutron.v2_0.domain.Port;
import org.jclouds.rest.annotations.SelectJson;
import org.testng.annotations.Test;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

/**
 *
 */
@Test(groups = "unit")
public class ParsePortTest extends BaseItemParserTest<Port> {

    @Override
    public String resource() {
        return "/port.json";
    }

    @Override
    @SelectJson("port")
    @Consumes(MediaType.APPLICATION_JSON)
    public Port expected() {
        try {
            return Port.builder().id("ebe69f1e-bc26-4db5-bed0-c0afb4afe3db").adminStateUp(true)
                    .deviceId("dhcpacb6d6f0-2d56-5c7a-9905-4c3a3d168f49-abaf2e70-4305-4e8d-ac70-3e4cc5f66a07")
                    .deviceOwner("network:dhcp")
                    .fixedIps(Arrays.asList(FixedIP.builder().ipAddress("10.0.0.2").subnetId("4156c7a5-e8c4-4aff-a6e1-8f3c7bc83861").build()))
                    .macAddress("fa:16:3e:a9:34:c3")
                    .name("test-port")
                    .networkId("16dba3bc-f3fa-4775-afdc-237e12c72f6a")
                    .securityGroups(Arrays.asList("49258a30-81f9-44d7-9440-c60dba50e29e"))
                    .state(Port.State.ACTIVE)
                    .tenantId("afc75773-640e-403c-9fff-62ba98db1f19").build();
        } catch (Exception e) {
            return null;
        }
    }
}

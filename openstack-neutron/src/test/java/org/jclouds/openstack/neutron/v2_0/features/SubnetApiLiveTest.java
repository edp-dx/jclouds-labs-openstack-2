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

import org.jclouds.openstack.neutron.v2_0.domain.Subnet;
import org.jclouds.openstack.neutron.v2_0.internal.BaseNeutronApiLiveTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * @author Andrey Trubitsyn
 */
@Test(groups = "live", singleThreaded = true)
public class SubnetApiLiveTest extends BaseNeutronApiLiveTest {

    public void testListPorts() {
        for (String zoneId : api.getConfiguredZones()) {
            SubnetApi subnetApi = api.getSubnetApiForZone(zoneId);
            Set<? extends Subnet> subnets = subnetApi.list().toSet();
            for (Subnet subnet : subnets) {
                assertTrue(subnets.contains(Subnet.builder().id(subnet.getId()).build()));
            }
        }
    }
}

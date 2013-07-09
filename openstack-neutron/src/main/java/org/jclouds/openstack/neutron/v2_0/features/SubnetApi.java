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

import com.google.common.collect.FluentIterable;
import org.jclouds.Fallbacks;
import org.jclouds.openstack.keystone.v2_0.filters.AuthenticateRequest;
import org.jclouds.openstack.neutron.v2_0.domain.Subnet;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.SelectJson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * @author Andrey Trubitsyn
 */
@RequestFilters(AuthenticateRequest.class)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v2.0/subnets")
public interface SubnetApi {
    /**
     * Returns the list of all subnets currently defined in Neutron for the current tenant. The list provides the unique
     * identifier of each subnet configured for the network.
     */
    @GET
    @SelectJson("subnets")
    @Fallback(Fallbacks.EmptyFluentIterableOnNotFoundOr404.class)
    FluentIterable<? extends Subnet> list();

    /**
     * Returns the specific subnet.
     */
    @GET
    @SelectJson("subnet")
    @Path("/{id}")
    @Fallback(Fallbacks.NullOnNotFoundOr404.class)
    Subnet get(@PathParam("id") String id);
}

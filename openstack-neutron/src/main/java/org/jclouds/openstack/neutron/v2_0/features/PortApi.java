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
import org.jclouds.Fallbacks.EmptyFluentIterableOnNotFoundOr404;
import org.jclouds.Fallbacks.NullOnNotFoundOr404;
import org.jclouds.openstack.keystone.v2_0.filters.AuthenticateRequest;
import org.jclouds.openstack.neutron.v2_0.domain.Port;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.SelectJson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * Provides synchronous access to Port operations on the openstack Neutron API.
 * <p/>
 * A port represents a virtual switch port on a logical network switch where all the interfaces attached to a given network are connected.
 * <p/>
 * A port has an administrative state which is either 'DOWN' or 'ACTIVE'. Ports which are administratively down will not be able to receive/send traffic.
 *
 * @author Andrey Trubitsyn
 * @see <a href="http://docs.openstack.org/api/openstack-network/2.0/content/Ports.html">api doc</a>
 */
@RequestFilters(AuthenticateRequest.class)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v2.0/ports")
public interface PortApi {
    /**
     * Returns the list of all ports currently defined in Neutron for the requested network
     */
    @GET
    @SelectJson("ports")
    @Fallback(EmptyFluentIterableOnNotFoundOr404.class)
    FluentIterable<? extends Port> list();

    /**
     * Returns a specific port.
     */
    @GET
    @SelectJson("port")
    @Path("/{id}")
    @Fallback(NullOnNotFoundOr404.class)
    Port get(@PathParam("id") String id);
}

/*
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
package org.jclouds.openstack.neutron.v2_0.domain;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A Neutron network
 *
 * @see <a href="http://docs.openstack.org/api/openstack-network/2.0/content/Networks.html">api doc</a>
 */
public class Network extends Reference {

    public static enum Status {
        ACTIVE, DOWN
    }

    public static Builder<?> builder() {
        return new ConcreteBuilder();
    }

    public Builder<?> toBuilder() {
        return new ConcreteBuilder().fromNetwork(this);
    }

    public abstract static class Builder<T extends Builder<T>> extends Reference.Builder<T> {
        protected String name;
        protected List<String> subnets = new ArrayList<String>(0);
        protected Status status;
        protected boolean adminStateUp;
        protected String tenantId;

        /**
         * @see Network#getName()
         */
        public T name(String name) {
            this.name = name;
            return self();
        }

        /**
         * @see Network#getSubnets()
         */
        public T subnets(List<String> subnets) {
            this.subnets = subnets;
            return self();
        }

        /**
         * @see Network#getStatus()
         */
        public T status(Status status) {
            this.status = status;
            return self();
        }

        /**
         * @see Network#isAdminStateUp()
         */
        public T adminStateUp(Boolean adminStateUp) {
            this.adminStateUp = adminStateUp;
            return self();
        }

        /**
         * @see Network#getTenantId()
         */
        public T tenantId(String tenantId) {
            this.tenantId = tenantId;
            return self();
        }

        public Network build() {
            return new Network(id, name, subnets, status, adminStateUp, tenantId);
        }

        public T fromNetwork(Network in) {
            return super.fromReference(in)
                    .name(in.getName())
                    .subnets(in.getSubnets())
                    .status(in.getStatus())
                    .adminStateUp(in.isAdminStateUp())
                    .tenantId(in.getTenantId());
        }
    }

    private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
        @Override
        protected ConcreteBuilder self() {
            return this;
        }
    }

    private final String name;
    private final List<String> subnets;
    private final Status status;
    private final boolean adminStateUp;
    private final String tenantId;


    @ConstructorProperties({
            "id", "name", "subnets", "status", "admin_state_up", "tenant_id"
    })
    protected Network(String id, String name, List<String> subnets, Status status, boolean adminStateUp, String tenantId) {
        super(id);
        this.name = checkNotNull(name, "name");
        this.status = checkNotNull(status, "status");
        this.adminStateUp = checkNotNull(adminStateUp, "adminStateUp");
        this.tenantId = checkNotNull(tenantId, "tenantId");
        this.subnets = subnets;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getSubnets() {
        return subnets;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isAdminStateUp() {
        return adminStateUp;
    }

    public String getTenantId() {
        return tenantId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), name, subnets, status, adminStateUp, tenantId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Network that = Network.class.cast(obj);
        return super.equals(that) && Objects.equal(this.name, that.name) && Objects.equal(this.subnets, that.subnets)
                && Objects.equal(this.status, that.status) && Objects.equal(this.adminStateUp, that.adminStateUp)
                && Objects.equal(this.tenantId, that.tenantId);
    }

    protected ToStringHelper string() {
        return super.string()
                .add("name", name)
                .add("subnets", subnets)
                .add("status", status)
                .add("adminStateUp", adminStateUp)
                .add("tenantId", tenantId);
    }

}

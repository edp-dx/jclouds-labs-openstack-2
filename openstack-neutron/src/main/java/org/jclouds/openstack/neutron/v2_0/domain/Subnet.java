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
package org.jclouds.openstack.neutron.v2_0.domain;

import com.google.common.base.Objects;

import java.beans.ConstructorProperties;
import java.net.InetAddress;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @see <a href="http://docs.openstack.org/api/openstack-network/2.0/content/Subnets.html">api doc</a>
 */
public class Subnet extends Reference {

    public static Builder<?> builder() {
        return new ConcreteBuilder();
    }

    public Builder<?> toBuilder() {
        return new ConcreteBuilder().fromSubnet(this);
    }

    public abstract static class Builder<T extends Builder<T>> extends Reference.Builder<T> {
        protected String name;
        protected String networkId;  //"1a6f6006-9e0b-4f99-984c-96787ae66363"
        protected String tenantId; //"f667b69e4d6749749ef3bcba7251d9ce"
        protected List<IPRange> allocationPools; // [{"start": "10.10.0.2", "end": "10.10.0.254"}],
        protected InetAddress gatewayIp; //"10.10.0.1",
        protected int ipVersion; // 4,
        protected String cidr; //10.10.0.0/24",
        protected boolean enableDhcp; //false,

        /**
         * @see Subnet#getName()
         */
        public T name(String name) {
            this.name = name;
            return self();
        }

        /**
         * @see Subnet#getNetworkId()
         */
        public T networkId(String networkId) {
            this.networkId = networkId;
            return self();
        }

        /**
         * @see Subnet#getTenantId()
         */
        public T tenantId(String tenantId) {
            this.tenantId = tenantId;
            return self();
        }

        /**
         * @see Subnet#getAllocationPools() ()
         */
        public T allocationPools(List<IPRange> allocationPools) {
            this.allocationPools = allocationPools;
            return self();
        }

        /**
         * @see Subnet#getGatewayIp() ()
         */
        public T gatewayIp(InetAddress gatewayIp) {
            this.gatewayIp = gatewayIp;
            return self();
        }

        /**
         * @see Subnet#getIpVersion() ()
         */
        public T ipVersion(int ipVersion) {
            this.ipVersion = ipVersion;
            return self();
        }

        /**
         * @see Subnet#getCidr() ()
         */
        public T cidr(String cidr) {
            this.cidr = cidr;
            return self();
        }

        /**
         * @see Subnet#isEnableDhcp() ()
         */
        public T enableDhcp(boolean enableDhcp) {
            this.enableDhcp = enableDhcp;
            return self();
        }

        /**
         * @see Subnet#getName()
         */
        public Subnet build() {
            return new Subnet(id, name, networkId, tenantId, allocationPools, gatewayIp, ipVersion, cidr, enableDhcp);
        }

        public T fromSubnet(Subnet in) {
            return super.fromReference(in)
                    .name(in.getName())
                    .networkId(in.getNetworkId())
                    .tenantId(in.getTenantId())
                    .allocationPools(in.getAllocationPools())
                    .gatewayIp(in.getGatewayIp())
                    .ipVersion(in.getIpVersion())
                    .cidr(in.getCidr())
                    .enableDhcp(in.isEnableDhcp());
        }
    }

    private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
        @Override
        protected ConcreteBuilder self() {
            return this;
        }
    }


    private final String name;
    private final String networkId;
    private final String tenantId;
    private final List<IPRange> allocationPools;
    private final InetAddress gatewayIp;
    private final int ipVersion;
    private final String cidr;
    private final boolean enableDhcp;

    @ConstructorProperties({
            "id", "name", "network_id", "tenant_id", "allocation_pools", "gateway_ip", "ip_version", "cidr", "enable_dhcp"
    })
    protected Subnet(String id, String name, String networkId, String tenantId, List<IPRange> allocationPools, InetAddress gatewayIp, int ipVersion, String cidr, boolean enableDhcp) {
        super(id);
        this.name = checkNotNull(name, "name");
        this.networkId = checkNotNull(networkId, "networkId");
        this.tenantId = checkNotNull(tenantId, "tenantId");
        this.allocationPools = checkNotNull(allocationPools, "allocationPools");
        this.gatewayIp = gatewayIp;
        this.ipVersion = ipVersion;
        this.cidr = checkNotNull(cidr, "cidr");
        this.enableDhcp = checkNotNull(enableDhcp, "enableDhcp");
    }

    public String getName() {
        return name;
    }

    public String getNetworkId() {
        return networkId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public List<IPRange> getAllocationPools() {
        return allocationPools;
    }

    public InetAddress getGatewayIp() {
        return gatewayIp;
    }

    public int getIpVersion() {
        return ipVersion;
    }

    public String getCidr() {
        return cidr;
    }

    public boolean isEnableDhcp() {
        return enableDhcp;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), name, networkId, tenantId, allocationPools, gatewayIp, ipVersion, cidr, enableDhcp);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Subnet that = Subnet.class.cast(obj);
        return super.equals(that) && Objects.equal(this.name, that.name) && Objects.equal(this.networkId, that.networkId)
                && Objects.equal(this.allocationPools, that.allocationPools) && Objects.equal(this.gatewayIp, that.gatewayIp)
                && Objects.equal(this.ipVersion, that.ipVersion) && Objects.equal(this.cidr, that.cidr)
                && Objects.equal(this.enableDhcp, that.enableDhcp) && Objects.equal(this.tenantId, that.tenantId);
    }

    protected Objects.ToStringHelper string() {
        return super.string()
                .add("name", name)
                .add("networkId", networkId)
                .add("tenantId", tenantId)
                .add("allocationPools", allocationPools)
                .add("gatewayIp", gatewayIp)
                .add("ipVersion", ipVersion)
                .add("cidr", cidr)
                .add("enableDhcp", enableDhcp);
    }

}

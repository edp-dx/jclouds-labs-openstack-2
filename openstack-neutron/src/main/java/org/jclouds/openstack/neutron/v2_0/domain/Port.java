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
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A Neutron port
 *
 * @see <a href="http://docs.openstack.org/api/openstack-network/2.0/content/Ports.html">api doc</a>
 */
public class Port extends Reference {

    /**
     */
    public static enum State {
        ACTIVE, DOWN
    }

    public static Builder<?> builder() {
        return new ConcreteBuilder();
    }

    public Builder<?> toBuilder() {
        return new ConcreteBuilder().fromPort(this);
    }

    public abstract static class Builder<T extends Builder<T>> extends Reference.Builder<T> {

        protected boolean adminStateUp; // true,
        protected String deviceId; // "dhcpacb6d6f0-2d56-5c7a-9905-4c3a3d168f49-abaf2e70-4305-4e8d-ac70-3e4cc5f66a07",
        protected String deviceOwner; //"network:dhcp",
        protected List<FixedIP> fixedIPs; // [{"ip_address":"10.0.0.2","subnet_id":"3b498f11-0a20-43ed-b992-0e4202d5f930"}],
        protected String macAddress; //"fa:16:3e:a9:34:c3",
        protected String name;
        protected String networkId; // "abaf2e70-4305-4e8d-ac70-3e4cc5f66a07",
        protected List<String> securityGroups; //["49258a30-81f9-44d7-9440-c60dba50e29e"],
        protected Port.State state; //        "ACTIVE",
        protected String tenantId; //"e4f50856753b4dc6afee5fa6b9b6c550"

        /**
         * @see Port#isAdminStateUp() ()
         */
        public T adminStateUp(Boolean adminStateUp) {
            this.adminStateUp = adminStateUp;
            return self();
        }

        /**
         * @see Port#getDeviceId() ()
         */
        public T deviceId(String deviceId) {
            this.deviceId = deviceId;
            return self();
        }

        /**
         * @see Port#getDeviceOwner() ()
         */
        public T deviceOwner(String deviceOwner) {
            this.deviceOwner = deviceOwner;
            return self();
        }

        /**
         * @see Port#getFixedIPs() ()
         */
        public T fixedIps(List<FixedIP> fixedIPs) {
            this.fixedIPs = fixedIPs;
            return self();
        }

        /**
         * @see Port#getMacAddress() ()
         */
        public T macAddress(String macAddress) {
            this.macAddress = macAddress;
            return self();
        }

        /**
         * @see Port#getName() ()
         */
        public T name(String name) {
            this.name = name;
            return self();
        }

        /**
         * @see Port#getNetworkId() ()
         */
        public T networkId(String networkId) {
            this.networkId = networkId;
            return self();
        }

        /**
         * @see Port#getSecurityGroups() ()
         */
        public T securityGroups(List<String> securityGroups) {
            this.securityGroups = securityGroups;
            return self();
        }

        /**
         * @see Port#getState()
         */
        public T state(Port.State state) {
            this.state = state;
            return self();
        }

        /**
         * @see Port#getTenantId() ()
         */
        public T tenantId(String tenantId) {
            this.tenantId = tenantId;
            return self();
        }

        public Port build() {
            return new Port(id, adminStateUp, deviceId, deviceOwner, fixedIPs, macAddress, name, networkId, securityGroups, state, tenantId);
        }

        public T fromPort(Port in) {
            return super.fromReference(in)
                    .adminStateUp(in.isAdminStateUp())
                    .deviceId(in.getDeviceId())
                    .deviceOwner(in.getDeviceOwner())
                    .fixedIps(in.getFixedIPs())
                    .macAddress(in.getMacAddress())
                    .name(in.getName())
                    .networkId(in.getNetworkId())
                    .securityGroups(in.getSecurityGroups())
                    .state(in.getState())
                    .tenantId(in.getTenantId());
        }
    }

    private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
        @Override
        protected ConcreteBuilder self() {
            return this;
        }
    }

    private final boolean adminStateUp;
    private final String deviceId;
    private final String deviceOwner;
    private final List<FixedIP> fixedIPs;
    private final String macAddress;
    private final String name;
    private final String networkId;
    private final List<String> securityGroups;
    private final Port.State state;
    private final String tenantId;


    @ConstructorProperties({
            "id", "admin_state_up", "device_id", "device_owner", "fixed_ips", "mac_address", "name", "network_id", "security_groups", "status", "tenant_id"
    })
    protected Port(String id, boolean adminStateUp, String deviceId, String deviceOwner, List<FixedIP> fixedIPs, String macAddress, String name, String networkId, List<String> securityGroups, State state, String tenantId) {
        super(id);
        this.adminStateUp = checkNotNull(adminStateUp, "adminStateUp");
        this.deviceId = checkNotNull(deviceId, "deviceId");
        this.deviceOwner = checkNotNull(deviceOwner, "deviceOwner");
        this.fixedIPs = checkNotNull(fixedIPs, "fixedIps");
        this.macAddress = checkNotNull(macAddress, "macAddress");
        this.name = checkNotNull(name, "name");
        this.networkId = checkNotNull(networkId, "networkId");
        this.securityGroups = checkNotNull(securityGroups, "securityGroups");
        this.state = checkNotNull(state, "state");
        this.tenantId = checkNotNull(tenantId, "tenantId");

    }

    public boolean isAdminStateUp() {
        return adminStateUp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceOwner() {
        return deviceOwner;
    }

    public List<FixedIP> getFixedIPs() {
        return fixedIPs;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getName() {
        return name;
    }

    public String getNetworkId() {
        return networkId;
    }

    public List<String> getSecurityGroups() {
        return securityGroups;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Port.State getState() {
        return this.state;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), adminStateUp, deviceId, deviceOwner, fixedIPs, macAddress, name, networkId, securityGroups, state, tenantId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Port that = Port.class.cast(obj);
        return super.equals(that) && Objects.equal(this.adminStateUp, that.adminStateUp) && Objects.equal(this.deviceId, that.deviceId)
                && Objects.equal(this.deviceOwner, that.deviceOwner) && Objects.equal(this.fixedIPs, that.fixedIPs)
                && Objects.equal(this.macAddress, that.macAddress) && Objects.equal(this.name, that.name)
                && Objects.equal(this.networkId, that.networkId) && Objects.equal(this.securityGroups, that.securityGroups)
                && Objects.equal(this.state, that.state) && Objects.equal(this.tenantId, that.tenantId);

    }

    protected ToStringHelper string() {
        return super.string()
                .add("adminStateUp", adminStateUp)
                .add("deviceId", deviceId)
                .add("deviceOwner", deviceOwner)
                .add("fixedIps", fixedIPs)
                .add("macAddress", macAddress)
                .add("name", name)
                .add("networkId", networkId)
                .add("securityGroups", securityGroups)
                .add("state", state)
                .add("tenantId", tenantId);
    }

}

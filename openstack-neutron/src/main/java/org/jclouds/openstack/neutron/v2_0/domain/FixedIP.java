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

public class FixedIP {
    private final String ipAddress;
    private final String subnetId;

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return builder().fromFixedIP(this);
    }

    public static class Builder {

        protected String ipAddress;
        protected String subnetId;

        /**
         * @see FixedIP#getIpAddress() ()
         */
        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        /**
         * @see FixedIP#getSubnetId() ()
         */
        public Builder subnetId(String subnetId) {
            this.subnetId = subnetId;
            return this;
        }

        public FixedIP build() {
            return new FixedIP(ipAddress, subnetId);
        }

        public Builder fromFixedIP(FixedIP in) {
            return this.ipAddress(in.getIpAddress()).subnetId(in.getSubnetId());
        }
    }

    @ConstructorProperties({
            "ip_address", "subnet_id"
    })
    protected FixedIP(String ipAddress, String subnetId) {
        this.ipAddress = ipAddress;
        this.subnetId = subnetId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getSubnetId() {
        return subnetId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FixedIP that = FixedIP.class.cast(obj);
        return Objects.equal(this.ipAddress, that.ipAddress) && Objects.equal(this.subnetId, that.subnetId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ipAddress, subnetId);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("ipAddress", ipAddress)
                .add("subnetId", subnetId).toString();
    }
}
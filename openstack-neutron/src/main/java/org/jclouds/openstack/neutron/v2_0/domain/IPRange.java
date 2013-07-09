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

public class IPRange {
    private final String start;
    private final String end;

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return builder().fromIPRange(this);
    }

    public static class Builder {

        protected String start;
        protected String end;

        /**
         * @see IPRange#getStart() ()
         */
        public Builder start(String start) {
            this.start = start;
            return this;
        }

        /**
         * @see IPRange#getEnd() ()
         */
        public Builder end(String end) {
            this.end = end;
            return this;
        }

        public IPRange build() {
            return new IPRange(start, end);
        }

        public Builder fromIPRange(IPRange in) {
            return this.start(in.getStart()).end(in.getEnd());

        }
    }

    @ConstructorProperties({
            "start", "end"
    })
    public IPRange(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IPRange that = IPRange.class.cast(obj);
        return Objects.equal(this.start, that.start) && Objects.equal(this.end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(start, end);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("start", start)
                .add("end", end).toString();
    }
}
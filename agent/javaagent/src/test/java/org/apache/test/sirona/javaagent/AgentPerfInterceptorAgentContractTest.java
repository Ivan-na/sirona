/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.test.sirona.javaagent;

import org.apache.sirona.Role;
import org.apache.sirona.aop.AbstractPerformanceInterceptor;
import org.apache.sirona.counters.Counter;
import org.apache.sirona.javaagent.AgentPerformanceInterceptor;
import org.apache.sirona.repositories.Repository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgentPerfInterceptorAgentContractTest {
    @Test
    public void key() {
        final Counter.Key key = AgentPerformanceInterceptor.key("key");
        assertEquals(Role.PERFORMANCES, key.getRole());
        assertEquals("key", key.getName());
    }

    @Test
    public void start() {
        final Counter.Key key = AgentPerformanceInterceptor.key("start");
        final AbstractPerformanceInterceptor.Context context = AgentPerformanceInterceptor.start(key);
        context.stop();
        assertEquals("org.apache.sirona.javaagent.AgentPerformanceInterceptor$AgentContext", context.getClass().getName());
        assertEquals(1, Repository.INSTANCE.getCounter(key).getHits());
    }
}
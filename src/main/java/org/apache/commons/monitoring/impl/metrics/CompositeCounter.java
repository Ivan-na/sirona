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

package org.apache.commons.monitoring.impl.metrics;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.monitoring.Composite;
import org.apache.commons.monitoring.Counter;
import org.apache.commons.monitoring.Gauge;
import org.apache.commons.monitoring.Role;
import org.apache.commons.monitoring.Unit;

/**
 * A composite implementation of {@link Counter} that delegates to a primary
 * implementation and maintains a collection of secondary counters.
 * <p>
 * Typical use is to create monitoring graphs : On regular time intervals, a
 * new secondary counter is registered to computes stats for the current period,
 * and then removed.
 *
 * @author <a href="mailto:nicolas@apache.org">Nicolas De Loof</a>
 */
public class CompositeCounter extends ThreadSafeCounter implements Composite<Counter>
{
    private Collection<Counter> secondary;

    public Collection<Counter> getSecondary()
    {
        return Collections.unmodifiableCollection( secondary );
    }

    public CompositeCounter( Role<Counter> role )
    {
        super( role );
        this.secondary = new CopyOnWriteArrayList<Counter>();
    }

    public Counter createSecondary()
    {
        Counter counter = new ThreadSafeCounter( getRole() );
        secondary.add( counter );
        return counter;
    }

    public void removeSecondary( Counter counter )
    {
        secondary.remove( counter );
    }

    public void add( long delta, Unit unit )
    {
        super.add( delta, unit );
        for ( Counter counter : secondary )
        {
            counter.add( delta, unit );
        }
    }

    public void set( long l, Unit unit )
    {
        super.set( l, unit );
        for ( Counter counter : secondary )
        {
            counter.set( l, unit );
        }
    }

}
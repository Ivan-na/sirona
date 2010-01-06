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

package org.apache.commons.monitoring.metrics;

import org.apache.commons.monitoring.Role;

/**
 * @author <a href="mailto:nicolas@apache.org">Nicolas De Loof</a>
 */
public abstract class AbstractNoOpMetric
    extends AbstractMetric
{

    /**
     * @param role
     */
    public AbstractNoOpMetric( Role role )
    {
        super( role );
    }

    public final void reset()
    {
        // NoOp
    }

    public final long getHits()
    {
        return 0;
    }

    public final double getMin()
    {
        return 0;
    }

    public final double getMax()
    {
        return 0;
    }

    public final double getMean()
    {
        return 0;
    }

    public final void add( double delta )
    {
        // NoOp
    }
}
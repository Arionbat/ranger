/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.ranger.plugin.conditionevaluator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ranger.plugin.policyengine.RangerAccessRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RangerAccessedFromClusterCondition extends RangerAbstractConditionEvaluator {
    private static final Logger LOG = LoggerFactory.getLogger(RangerAccessedFromClusterCondition.class);

    private boolean isAlwaysTrue;

    @Override
    public void init() {
        LOG.debug("==> RangerAccessedFromClusterCondition.init({})", condition);

        super.init();

        isAlwaysTrue = CollectionUtils.isEmpty(condition.getValues());

        LOG.debug("<== RangerAccessedFromClusterCondition.init({})", condition);
    }

    @Override
    public boolean isMatched(RangerAccessRequest request) {
        LOG.debug("==> RangerAccessedFromClusterCondition.isMatched({})", condition);

        final boolean ret;

        if (isAlwaysTrue || request.getClusterName() == null) {
            ret = isAlwaysTrue;
        } else {
            ret = condition.getValues().contains(request.getClusterName());
        }

        LOG.debug("<== RangerAccessedFromClusterCondition.isMatched({}): {}", condition, ret);

        return ret;
    }
}

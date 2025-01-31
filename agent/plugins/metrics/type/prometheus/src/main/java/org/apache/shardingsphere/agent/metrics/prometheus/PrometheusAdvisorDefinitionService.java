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

package org.apache.shardingsphere.agent.metrics.prometheus;

import org.apache.shardingsphere.agent.config.advisor.AdvisorConfiguration;
import org.apache.shardingsphere.agent.core.plugin.yaml.loader.YamlAdvisorsConfigurationLoader;
import org.apache.shardingsphere.agent.core.plugin.yaml.swapper.YamlAdvisorsConfigurationSwapper;
import org.apache.shardingsphere.agent.spi.advisor.AdvisorDefinitionService;

import java.util.Collection;
import java.util.Collections;

/**
 * Prometheus advisor definition service.
 */
public final class PrometheusAdvisorDefinitionService implements AdvisorDefinitionService {
    
    private final YamlAdvisorsConfigurationLoader loader = new YamlAdvisorsConfigurationLoader();
    
    private final YamlAdvisorsConfigurationSwapper swapper = new YamlAdvisorsConfigurationSwapper();
    
    @Override
    public Collection<AdvisorConfiguration> getProxyAdvisorConfigurations() {
        return swapper.swapToObject(loader.load(getClass().getResourceAsStream("/prometheus/advisors.yaml")), getType());
    }
    
    @Override
    public Collection<AdvisorConfiguration> getJDBCAdvisorConfigurations() {
        // TODO add JDBC related interceptors
        return Collections.emptyList();
    }
    
    @Override
    public String getType() {
        return "Prometheus";
    }
}

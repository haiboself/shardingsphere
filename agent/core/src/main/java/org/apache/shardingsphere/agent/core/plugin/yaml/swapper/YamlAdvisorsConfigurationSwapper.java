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

package org.apache.shardingsphere.agent.core.plugin.yaml.swapper;

import org.apache.shardingsphere.agent.config.advisor.AdvisorConfiguration;
import org.apache.shardingsphere.agent.core.plugin.yaml.entity.YamlAdvisorConfiguration;
import org.apache.shardingsphere.agent.core.plugin.yaml.entity.YamlAdvisorsConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;

/**
 * YAML advisors configuration swapper.
 */
public final class YamlAdvisorsConfigurationSwapper {
    
    private final YamlAdvisorConfigurationSwapper advisorConfigurationSwapper = new YamlAdvisorConfigurationSwapper();
    
    /**
     * Unmarshal advisors configuration.
     * 
     * @param inputStream input stream
     * @return unmarshalled advisors configuration
     */
    public YamlAdvisorsConfiguration unmarshal(final InputStream inputStream) {
        return new Yaml().loadAs(inputStream, YamlAdvisorsConfiguration.class);
    }
    
    /**
     * Swap from YAML advisors configuration to advisor configurations.
     * 
     * @param yamlAdvisorsConfig YAML advisors configuration
     * @param type type
     * @return advisor configurations
     */
    public Collection<AdvisorConfiguration> swapToObject(final YamlAdvisorsConfiguration yamlAdvisorsConfig, final String type) {
        Collection<AdvisorConfiguration> result = new LinkedList<>();
        for (YamlAdvisorConfiguration each : yamlAdvisorsConfig.getAdvisors()) {
            if (null != each.getTarget()) {
                result.add(advisorConfigurationSwapper.swapToObject(each, type));
            }
        }
        return result;
    }
}

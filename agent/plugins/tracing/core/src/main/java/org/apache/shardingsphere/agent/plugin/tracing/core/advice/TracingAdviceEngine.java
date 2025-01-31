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

package org.apache.shardingsphere.agent.plugin.tracing.core.advice;

import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.agent.config.advisor.AdvisorConfiguration;
import org.apache.shardingsphere.agent.advice.type.InstanceMethodAdvice;
import org.apache.shardingsphere.agent.plugin.tracing.core.advice.adviser.impl.CommandExecutorTaskAdviser;
import org.apache.shardingsphere.agent.plugin.tracing.core.advice.adviser.impl.JDBCExecutorCallbackAdviser;
import org.apache.shardingsphere.agent.plugin.tracing.core.advice.adviser.impl.SQLParserEngineAdviser;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Tracing advice engine.
 */
@RequiredArgsConstructor
public final class TracingAdviceEngine {
    
    private final String type;
    
    /**
     * Get proxy tracing advisor configurations.
     * 
     * @param commandExecutorTaskAdvice command executor task advice
     * @param sqlParserEngineAdvice SQL parser engine advice
     * @param jdbcExecutorCallbackAdvice JDBC executor callback advice
     * @return got configurations
     */
    public Collection<AdvisorConfiguration> getProxyAdvisorConfigurations(final Class<? extends InstanceMethodAdvice> commandExecutorTaskAdvice,
                                                                          final Class<? extends InstanceMethodAdvice> sqlParserEngineAdvice,
                                                                          final Class<? extends InstanceMethodAdvice> jdbcExecutorCallbackAdvice) {
        // TODO load from YAML, please ref metrics
        Collection<AdvisorConfiguration> result = new LinkedList<>();
        result.add(new CommandExecutorTaskAdviser(type).getAdvisorConfiguration(commandExecutorTaskAdvice));
        result.add(new SQLParserEngineAdviser(type).getAdvisorConfiguration(sqlParserEngineAdvice));
        result.add(new JDBCExecutorCallbackAdviser(type).getAdvisorConfiguration(jdbcExecutorCallbackAdvice));
        return result;
    }
    
    /**
     * Get JDBC tracing advisor configurations.
     * 
     * @return got configurations
     */
    public Collection<AdvisorConfiguration> getJDBCAdvisorConfigurations() {
        // TODO
        return Collections.emptyList();
    }
}

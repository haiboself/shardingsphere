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

package org.apache.shardingsphere.agent.core.mock.advice;

import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.agent.advice.type.InstanceMethodAdvice;
import org.apache.shardingsphere.agent.advice.TargetAdviceObject;
import org.apache.shardingsphere.agent.advice.MethodInvocationResult;

import java.lang.reflect.Method;
import java.util.List;

@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public final class MockInstanceMethodAdvice implements InstanceMethodAdvice {
    
    private final boolean rebase;
    
    public MockInstanceMethodAdvice() {
        this(false);
    }
    
    @Override
    public void beforeMethod(final TargetAdviceObject target, final Method method, final Object[] args, final MethodInvocationResult invocationResult) {
        List<String> queues = (List<String>) args[0];
        queues.add("before");
        if (rebase) {
            invocationResult.rebase("rebase invocation method");
        }
    }
    
    @Override
    public void afterMethod(final TargetAdviceObject target, final Method method, final Object[] args, final MethodInvocationResult invocationResult) {
        List<String> queues = (List<String>) args[0];
        queues.add("after");
    }
    
    @Override
    public void onThrowing(final TargetAdviceObject target, final Method method, final Object[] args, final Throwable throwable) {
        List<String> queues = (List<String>) args[0];
        queues.add("exception");
    }
}

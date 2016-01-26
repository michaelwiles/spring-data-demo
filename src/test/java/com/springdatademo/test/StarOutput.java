package com.springdatademo.test;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public final class StarOutput implements MethodRule {
    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                System.out.println("\n\n***");
                base.evaluate();
                System.out.println("***\n");
            }
        };

    }
}
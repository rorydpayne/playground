package com.example.helloworld.health;

/*
    CREATING A HEALTH CHECK

    Health checks give you a way of adding small tests to your application to allow you to verify that your application
    is functioning correctly in production. We strongly recommend that all of your applications have at least a minimal
    set of health checks.

    NOTE
    We recommend this so strongly, in fact, that Dropwizard will nag you should you neglect to add a health check to
    your project.

    Since formatting strings is not likely to fail while an application is running (unlike, say, a database connection
    pool, we'll have to get a little creative here. We'll add a health check to make sure we can actually format the
    provided template.
 */

import com.yammer.metrics.core.HealthCheck;

public class TemplateHealthCheck  extends HealthCheck {
    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}

/*
    "TemplateHealthCheck" checks two things: that the provided template is actually a well-formed string, and that the
    template actually produces a given name.

    If the string is not a well-formed format string (for example, someone accidentally put "Hello, %s%" in the
    configuration file), then the "String.format(String, Object...) will throw an "IllegalFormatException" and the #
    health check will implicitly fail. If the rendered saying doesn't include the test string, the health check will
    explicitly fail by returning an unhealthy "Result"
 */
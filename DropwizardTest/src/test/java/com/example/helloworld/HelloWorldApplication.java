package com.example.helloworld;

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import com.yammer.dropwizard.config.Bootstrap;
import org.omg.CORBA.Environment;

/*
    CREATING AN APPLICATION CLASS

    Combined with your project's "Configuration" subclass, its "Application" subclass forms the core of your Dropwizard
    application. The "Application" class pulls together the various bundles and commands which provide basic
    functionality. (More of that later). For now, though, our "HelloWorldApplication" looks like this:
 */

public class HelloWorldApplication extends Application<HelloWorldApplication> {
    public static main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldApplication> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration),
                    Environment environment) {
        //REGISTERING A RESOURCE (see comments in "HelloWorldResource")
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
                );

        /*
            ADDING A HEALTH CHECK

            As with most things Dropwizard, we create a new instance with the appropriate parameters and add it to the
            "Environment":
         */

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(resource);
    }
    /*
        When our application starts, we create a new instance of our resource class with the parameters from the
        configuration file and hand it off to the "Environment", which acts like a registry of all the things your
        application can do.

        NOTE
        A dropwizard application can contain many resource classes, each corresponding to its own URI pattern. Just add
        another @Path-annotated resource class and call it "register" with an instance of the new class.

        Before we go too far we should add a health check for our application (see TemplateHealthCheck class).
     */
}

/*
 As you can see, HelloWorldApplication is parameterized with the application's configuration type,
 HelloWorldConfiguration. An "initialize" method is used to configure aspects of the application required before the
 application is run, like bundles, configuration source providers, etc. Also, we've added a static main method. which
 will be our application's entry point. Right now, we don't have any functionality implemented , so our run method is a
 little boring. Let's fix that...
 */

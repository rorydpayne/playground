package com.example.helloworld;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.security.auth.login.Configuration;

/*
    CREATING A CONFIGURATION CLASS

    Each dropwizard application has its own subclass of the "Configuration" class which specifies environment-specific
    parameters. THese parameters are specified in a YAML configuration file which is deserialized to an instance of your
    application's configuration class and validated.

    This application I am building is a high-performance Hello World service, and one of our requirements is that we
    need to be able to vary how it says hello from environment to environment. We'll need to specify at least two things
    to begin with: a template for saying hello and a default name to use in case the user doesn't specify their name/
 */

public class HelloWorldConfiguration extends Configuration
{

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    /*
     JsonProperty annotation used to define a non-static method as getter/setter
     for a logical property (depending on its signature), or a non-static object
     field to be used (serialized, deserialized) as a logical property.
        */
    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}

/*
 There's a lot going on here, so let's unpack a bit of it.

 When this class is deserialized from the YAML file, it will pull two root-level fields from the YAML Object:
 template", the template for our Hello World saying, and "default name", the default name to use. Both "template" and
 "default name" are annotated with @NotEmpty, so if the YAML configuration file has blank values foreither or is
 missing "template" entirely an informative exception will be thrown and your application won't start.

 Both the getters and setters for "template" and "default name" are annotated with @JsonProperty, which alllows Jackson
 to both deserialize the properties from a YAML file but also serialize it.
 */
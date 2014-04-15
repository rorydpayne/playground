package com.example.helloworld.resources;

import com.example.helloworld.core.Saying;
import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/*
    CREATING A RESOURCE CLASS

    Jersey resources are a meat-and-potatoes of a dropwizard application. Each resource class is associated with a URI
    template. For our application, we need a resource which returns new "Saying" instances from the URI "/hello-world",
    so our resource class will look like this:
 */

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    public final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}

/*
    Finally, we're in the thick of it! Let's start from the top and work our way down.

    "HelloWorldReo=source" has two annotations: @Path and @Produces. @Path("/hello-world" tells Jersey that this
    resource is accessible at the URI "/hello-world", and @Produces(MediaType.APPLICATION_JSON) lets Jersey's content
    negotiation code know that this resource produces representations which are "application/json".

    "HelloWorldResource" rakes two parameters for construction: the "template" it uses to produce the saying and the
    "defautlName" used when the user declines to tell us their name. An "AtomicLong" provides us with a cheap, thread-
    safe way of generating unique(ish) IDs.

    WARNING
    Resource classes are used by multiple threads concurrently. In general, we recommend that resources be
    stateless/immutable, but it's important to keep the context in mind.

    "#sayHello(Optional<String>" is the meat of this class, and it's a fairly simple method. The @QueryParam("name")
    annotation tells Jersey to map the "name" parameter from the query string to the "name" paramter in the method. If
    the client sends a request to /hello-world?name=Dougie, sayHello will be called with Optional.of("Dougie"); if
    there is no "name" paramter in the query string, "sayHello" will be called with "Optional.absent()". (Support for
    Guava's "Optional" is a little extra souce that Dropwizard adds to Jersey's existing functionality.)

    Inside the "sayHello" method, we increment the counter, format the template using "String.format(String, Object...)",
    and return a new "Saying" instance.

    Because "sayHello" is annotated with @Timed, Dropwizard automatically records the duration and rate of its
    invocation as a Metrics Timer.


    Once "sayHello" has returned, Jersey takes the "Saying" instance and looks for a provider class which can write
    "Saying" instances as application/json. Dropwizard has one such provider built in which allows for producing and
    consuming Java objects as JSON objects. The provider writes out the JSON and the client receives a 200 OK response
    with a content type of "application/json".

    REGISTERING A RESOURCE

    Before that will actually work, though, we need to go back to "HelloWorldApplication" and add this new resource
    class. In its "run" method we can read the template and default name from the "HelloWorldConfiguration" instance,
    create a new "HelloWorldApplication" instance, and then add it to the application's Jersey environment
 */
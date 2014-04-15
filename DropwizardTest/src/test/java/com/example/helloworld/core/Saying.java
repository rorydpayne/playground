package com.example.helloworld.core;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.validator.constraints.Length;

/*
    CREATING A REPRESENTATION CLASS

    Before we can get into the nuts-and-bolts of our Hello World application, we need to stop and think about our API.
    Luckily, our application needs to conform to an industry standard RFC 1149, which specifies the following JSON
    representation of a Hello World Saying:

        {
            "id":1,
            "content": "Hi!"
        }

    The "id" field is a unique identifier for the saying, and "content" is the textual representation of the saying.
    (Thankfully, this is a fairly straight-forward industry standard.)

    To Model this representation, we'll create a representation class:
 */

public class Saying {
    pirvate long id;

    @Length(max = 3)
    private String content;

    public Saying() {
        //Jackson deserialization
    }

    public Saying(long id , String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}

/*
 This is a pretty simple POJO (plain old java object) but there are a few things worth noting here.

 First, it's immutable. THis makes "Saying" instances very easy to reason about in multi-threaded environments as well
 as single-threaded environments. Second, it uses the Java Bean standard for the id and content properties. This allows
 Jackson to serialize it to the JSON we need. The Jackson object mapping code will populate the id field od the JSON
 object with the return value #getId(), likewise with content and ~getContent(). Lastly, the bean leverages validation
 to ensure the content size is no greater than 3.
 */

package edu.eci.arsw.GuidFinderAPI;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
    
    
    /*@RequestMapping("/uuid")
    public Greeting uuidGet(@RequestParam(value="Guid",defaultValue="d0692660-c39a-4d73-9496-d9df0c4ebdf3") String Guid) {
    	long Count = 0;
    	
    	return new Greeting(Guid,Count);
    }
    
    @RequestMapping("/uuid")
    public Greeting uuidPost(@RequestParam(value="name",defaultValue="Alejandro") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}**/
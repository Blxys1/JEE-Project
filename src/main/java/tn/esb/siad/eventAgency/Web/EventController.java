package tn.esb.siad.eventAgency.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esb.siad.eventAgency.Domains.Event;
import tn.esb.siad.eventAgency.Services.EventService;

import javax.validation.Valid;
import java.util.List;
@RequestMapping( "/api/events")
@RestController
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping("/events")

    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/event/{id}")
    public Event getEvent(@PathVariable Long id){
        return eventService.getEventById(id);
    }
    //...
    @PostMapping ("/event")
        public Event addEvent(@RequestBody @Valid Event event){
        return eventService.addEvent(event);
    }
    //update an event method
    @PutMapping("/event/{id}")
    public Event updateEvent(@PathVariable Long id,@RequestBody @Valid Event event){
        return eventService.updateEvent(event,id);

    }
    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
    }
}

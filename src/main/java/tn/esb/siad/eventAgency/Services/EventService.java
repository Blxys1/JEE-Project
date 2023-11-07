package tn.esb.siad.eventAgency.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esb.siad.eventAgency.Domains.Event;
import tn.esb.siad.eventAgency.Repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;


    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event getEventById(Long id){
        return eventRepository.findById(id).orElseThrow();
    }

    public Event addEvent(Event e1) throws IllegalStateException{
        List<Event> events = eventRepository.findAll();
        int i=0;
        while(i<events.size()){
            if(events.get(i).equals(e1)){
                throw new IllegalStateException("Event already exists");
            }
            i++;
        }
        return eventRepository.save(e1);
    }
//Update an event
    public Event updateEvent(Event event,Long id) throws IllegalStateException{
        Event e=eventRepository.findById(id).orElseThrow();
        e.setName(event.getName());
        e.setDescription(event.getDescription());
        e.setTheme(event.getTheme());
        e.setDate(event.getDate());
        //....
        return eventRepository.save(e);
    }
    //Delete an event
    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
}

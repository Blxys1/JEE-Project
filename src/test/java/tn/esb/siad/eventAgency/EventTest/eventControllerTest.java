package tn.esb.siad.eventAgency.EventTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tn.esb.siad.eventAgency.Domains.Event;
import tn.esb.siad.eventAgency.Services.EventService;
import tn.esb.siad.eventAgency.Web.EventController;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(EventController.class)
public class eventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Test
    public void testGetAllEvents() throws Exception {
        // Create some sample events
        Event event1 = new Event();
        event1.setId(1L);
        event1.setName("Event 1");
        Event event2 = new Event();
        event2.setId(2L);
        event2.setName("Event 2");

        when(eventService.getAllEvents()).thenReturn(List.of(event1, event2));

        mockMvc.perform(get("/api/events/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Event 1"))
                .andExpect(jsonPath("$[1].name").value("Event 2"));
    }
    @Test
    public void testGetEventById() throws Exception {
        Event event = new Event();
        event.setId(1L);
        event.setName("Event 1");

        when(eventService.getEventById(1L)).thenReturn(event);

        mockMvc.perform(get("/api/events/event/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Event 1"));
    }
    @Test
    public void testAddEvent() throws Exception {
        Event event = new Event();
        event.setName("New Event");

        when(eventService.addEvent(any(Event.class))).thenReturn(event);

        mockMvc.perform(post("/api/events/event")
                        .contentType("application/json")
                        .content("{\"name\":\"New Event\"}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Event"));
    }
    @Test
    public void testUpdateEvent() throws Exception {
        Event updatedEvent = new Event();
        updatedEvent.setId(1L);
        updatedEvent.setName("Updated Event");

        when(eventService.updateEvent(any(Event.class), any(Long.class))).thenReturn(updatedEvent);

        mockMvc.perform(put("/api/events/event/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Updated Event\"}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Event"));
    }
    @Test
    public void testDeleteEvent() throws Exception {
        mockMvc.perform(delete("/api/events/event/1"))
                .andExpect(status().isOk());
    }
}

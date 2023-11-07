package tn.esb.siad.eventAgency.EventTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esb.siad.eventAgency.Domains.Event;
import tn.esb.siad.eventAgency.Repositories.EventRepository;
import tn.esb.siad.eventAgency.Services.EventService;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEvents() {
        // Arrange
        List<Event> events = new ArrayList<>();
        Event newEvent = new Event();
        newEvent.setName("Sample Event");
        newEvent.setDescription("This is a sample event description.");
        newEvent.setTheme("Sample Theme");
        newEvent.setDate(LocalDate.of(2023, 11, 15)); // Set the date to November 15, 2023
        newEvent.setBudget(1000.00); // Set the budget for the event
        newEvent.setNbPlaces(100);
        events.add(newEvent);
        when(eventRepository.findAll()).thenReturn(events);

        // Act
        List<Event> result = eventService.getAllEvents();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Sample Event", result.get(0).getName());
        assertEquals("This is a sample event description.", result.get(0).getDescription());
    }

    @Test
    public void testGetEventById() {
        // Arrange
        Long eventId = 1L;
        Event event = new Event();
        event.setId(eventId);
        event.setName("Sample Event");
        event.setDescription("This is a sample event description.");
        event.setTheme("Sample Theme");
        event.setDate(LocalDate.of(2023, 11, 15)); // Set the date to November 15, 2023
        event.setBudget(1000.00); // Set the budget for the event
        event.setNbPlaces(100);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        // Act
        Event result = eventService.getEventById(eventId);

        // Assert
        assertNotNull(result);
        assertEquals(eventId, result.getId());
        assertEquals("Sample Event", result.getName());
        assertEquals("This is a sample event description.", result.getDescription());
    }

    @Test
    public void testAddEvent() {
        // Arrange
        Event newEvent = new Event();
        newEvent.setId(2L);
        newEvent.setName("Sample Event");
        newEvent.setDescription("This is a sample event description.");
        newEvent.setTheme("Sample Theme");
        newEvent.setDate(LocalDate.of(2023, 11, 15)); // Set the date to November 15, 2023
        newEvent.setBudget(1000.00); // Set the budget for the event
        newEvent.setNbPlaces(100);
        when(eventRepository.findAll()).thenReturn(new ArrayList<>()); // No events in the repository
        when(eventRepository.save(newEvent)).thenReturn(newEvent);

        // Act
        Event result = eventService.addEvent(newEvent);

        // Assert
        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Sample Event", result.getName());
        assertEquals("This is a sample event description.", result.getDescription());
    }

    @Test
    public void testAddEventEventAlreadyExists() {
        // Arrange
        Event newEvent = new Event();
        newEvent.setName("Sample Event");
        newEvent.setDescription("This is a sample event description.");
        newEvent.setTheme("Sample Theme");
        newEvent.setDate(LocalDate.of(2023, 11, 15)); // Set the date to November 15, 2023
        newEvent.setBudget(1000.00); // Set the budget for the event
        newEvent.setNbPlaces(100);
        when(eventRepository.findAll()).thenReturn(List.of(newEvent)); // Event with the same ID already exists

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> eventService.addEvent(newEvent));
    }

    @Test
    public void testUpdateEvent() {
        // Arrange
        Long eventId = 1L;
        Event newEvent = new Event();
        newEvent.setId(eventId);
        newEvent.setName("Sample Event");
        newEvent.setDescription("This is a sample event description.");
        newEvent.setTheme("Sample Theme");
        newEvent.setDate(LocalDate.of(2023, 11, 15)); // Set the date to November 15, 2023
        newEvent.setBudget(1000.00); // Set the budget for the event
        newEvent.setNbPlaces(100);
        Event updatedEvent = new Event();
        updatedEvent.setId(eventId);
        updatedEvent.setName("UpdatedEvent");
        updatedEvent.setDescription("UpdatedDescription");
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(newEvent));
        when(eventRepository.save(newEvent)).thenReturn(updatedEvent);

        // Act
        Event result = eventService.updateEvent(updatedEvent, eventId);

        // Assert
        assertNotNull(result);
        assertEquals(eventId, result.getId());
        assertEquals("UpdatedEvent", result.getName());
        assertEquals("UpdatedDescription", result.getDescription());
    }

    @Test
    public void testDeleteEvent() {
        // Arrange
        Long eventId = 1L;
        doNothing().when(eventRepository).deleteById(eventId);

        // Act
        eventService.deleteEvent(eventId);

        // Assert
        // Verify that the deleteById method was called once with the correct ID
        verify(eventRepository, times(1)).deleteById(eventId);
    }
}

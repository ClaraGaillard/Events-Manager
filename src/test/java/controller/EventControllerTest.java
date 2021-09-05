package controller;

import adeo.leroymerlin.cdp.controller.EventController;
import adeo.leroymerlin.cdp.model.Event;
import adeo.leroymerlin.cdp.service.EventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class EventControllerTest {

    private EventController eventController;
    private EventService eventService;

    @Before
    public void init() {
        eventService = Mockito.mock(EventService.class);
        eventController = new EventController(eventService);

        Mockito.when(eventService.getEvents()).thenReturn(new ArrayList<>());
        Mockito.when(eventService.getFilteredEvents(Mockito.anyString())).thenReturn(new ArrayList<>());
        Mockito.doNothing().when(eventService).delete(Mockito.anyLong());
        Mockito.doNothing().when(eventService).updateEvent(Mockito.any(Event.class));
    }

    @Test
    public void findAllEventsTestOK() {
        List<Event> events = eventController.findEvents();
        Assert.assertTrue(events.isEmpty());
    }

    @Test
    public void findFilteredEventsTestOK() {
        List<Event> events = eventController.findEvents("Wa");
        Assert.assertTrue(events.isEmpty());
    }

    @Test
    public void deleteEventTestOK() {
        eventController.deleteEvent(1L);
        Mockito.verify(eventService).delete(1L);
    }

    @Test
    public void updateEventTestOK() {
        Event event = new Event();
        eventController.updateEvent(event);
        Mockito.verify(eventService).updateEvent(event);
    }

}

package service;

import adeo.leroymerlin.cdp.model.Event;
import adeo.leroymerlin.cdp.repository.EventRepository;
import adeo.leroymerlin.cdp.service.EventService;
import mock.MockEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class EventServiceTest {

    private EventService eventService;

    private EventRepository eventRepository;

    @Before
    public void init() {
        eventRepository = Mockito.mock(EventRepository.class);
        List<Event> events = MockEvent.createMockedEvents();
        Mockito.when(eventRepository.findAllBy()).thenReturn(events);
        eventService = new EventService(eventRepository);
    }

    @Test
    public void getAllEventsTest() {
        List<Event> events = eventService.getEvents();
        Event event = events.get(0);
        Assert.assertEquals(1, events.size());
        Assert.assertNotNull(event);
        Assert.assertEquals("Title", event.getTitle());
        Assert.assertEquals(new Long(1L), event.getId());
        Assert.assertEquals("", event.getImgUrl());
        Assert.assertEquals("Comment", event.getComment());
        Assert.assertEquals(new Integer(5), event.getNbStars());
        Assert.assertEquals(1, event.getBands().size());
    }

    @Test
    public void deleteEventTest() {
        eventService.delete(1L);
        Mockito.verify(eventRepository, Mockito.times(1)).delete(1L);
    }

    @Test
    public void getFilteredEventsTest1Result() {
        List<Event> events = eventService.getFilteredEvents("Me");
        Assert.assertEquals(1, events.size());
    }

    @Test
    public void getFilteredEventsTestQueryNotFound() {
        List<Event> events = eventService.getFilteredEvents("No");
        Assert.assertEquals(0, events.size());
    }

    @Test
    public void reviewEventTest() {
        Event e = MockEvent.createMockedEvent();
        eventService.updateEvent(e);
        Mockito.verify(eventRepository, Mockito.times(1)).save(e);
    }

}

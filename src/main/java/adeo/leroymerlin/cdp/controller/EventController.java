package adeo.leroymerlin.cdp.controller;

import adeo.leroymerlin.cdp.model.Event;
import adeo.leroymerlin.cdp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Event Controller, which allows us to make some requests about events from endpoints
 *
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Event> findEvents() {
        return eventService.getEvents();
    }

    @RequestMapping(value = "/search/{query}", method = RequestMethod.GET)
    public List<Event> findEvents(@PathVariable String query) {
        return eventService.getFilteredEvents(query);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateEvent(@RequestBody Event event) {
        eventService.updateEvent(event);
    }
}

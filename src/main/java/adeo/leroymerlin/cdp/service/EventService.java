package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.repository.EventRepository;
import adeo.leroymerlin.cdp.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The Event Service, which provides several methods about events
 *
 */
@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Method which retrieves all the events from the database
     *
     * @return the list of the events
     */
    public List<Event> getEvents() {
        return eventRepository.findAllBy();
    }

    /**
     * Method which deletes the event corresponding to the id given in the parameters
     *
     * @param id the id of the event we want to delete
     */
    public void delete(Long id) {
        eventRepository.delete(id);
    }

    /**
     * Method which retrieves filtered events.
     * The events are displayed only if at least one band has a member with the name matching the given query.
     *
     * @param query the varchar that should be contained into the name of the member
     * @return the list of the filtered events
     */
    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAllBy();

        return events.stream()
                .filter(event -> event.getBands().stream()
                        .flatMap(band -> band.getMembers().stream())
                        .anyMatch(member -> member.getName().toLowerCase().contains(query.toLowerCase())))
                .collect(Collectors.toList());
    }

    /**
     * Method which updates the event given in the parameters
     *
     * @param event the event which needs to be updated
     */
    public void updateEvent(Event event) {
        eventRepository.save(event);
    }
}

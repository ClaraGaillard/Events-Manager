package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.repository.EventRepository;
import adeo.leroymerlin.cdp.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAllBy();
    }

    public void delete(Long id) {
        eventRepository.delete(id);
    }

    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAllBy();

        return events.stream()
                .filter(event -> event.getBands().stream()
                        .flatMap(band -> band.getMembers().stream())
                        .anyMatch(member -> member.getName().toLowerCase().contains(query.toLowerCase())))
                .collect(Collectors.toList());
    }

    public void updateEvent(Event event) {
        eventRepository.save(event);
    }
}

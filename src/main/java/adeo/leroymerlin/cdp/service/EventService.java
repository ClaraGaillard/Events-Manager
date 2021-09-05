package adeo.leroymerlin.cdp.service;

import adeo.leroymerlin.cdp.repository.EventRepository;
import adeo.leroymerlin.cdp.model.Event;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Event Service, which provides several methods about events
 *
 */
@Service
@Transactional
public class EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

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
     * The event's id field and the band's name contain one additional String element [number of fields after the id/name]
     *
     * @param query the varchar that should be contained into the name of the member
     * @return the list of the filtered events
     */
    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAllBy();

        return events.stream()
                .filter(event -> {
                    String displayEventCountChild = countFields("title", event);
                    if(!event.getTitle().contains(displayEventCountChild)) event.setTitle(event.getTitle() + displayEventCountChild);
                    event.getBands().forEach(band -> {
                        String displayBandCountChild = countFields("name", band);
                        if(!band.getName().contains(displayBandCountChild)) band.setName(band.getName() + displayBandCountChild);
                    });
                    return event.getBands().stream()
                            .flatMap(band -> band.getMembers().stream())
                            .anyMatch(member -> member.getName().toLowerCase().contains(query.toLowerCase()));
                })
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

    /**
     * Method which counts the number of json fields after a specific one
     *
     * @param key the field we are looking for
     * @param o the object to turn into json
     * @return the String which contains the number of fields
     */
    private String countFields(String key, Object o) {
        int childNodes = 0;
        ObjectMapper mapper = new ObjectMapper();

        try {
            byte[] jsonData = mapper.writeValueAsString(o).getBytes();

            JsonNode rootNode = mapper.readTree(jsonData);
            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            List<String> results = new ArrayList<>();

            while (fields.hasNext()) {
                results.add(fields.next().getKey());
            }

            childNodes = results.size() - results.indexOf(key) - 1;
        } catch (IOException e) {
            LOGGER.error("An error occurred during the count of the fields after : {} with message : {}", key, e.getMessage());
        }

        return " [" + childNodes + "]";
    }
}

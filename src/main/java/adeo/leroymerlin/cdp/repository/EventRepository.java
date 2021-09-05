package adeo.leroymerlin.cdp.repository;

import adeo.leroymerlin.cdp.model.Event;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Event Repository, which provides methods to interact with the database about events
 *
 */
public interface EventRepository extends Repository<Event, Long> {

    /**
     * Method which deletes the event corresponding to the id given in the parameters
     *
     * @param eventId the id of the event we want to delete
     */
    void delete(Long eventId);

    /**
     * Method which retrieves all the events from the database
     *
     * @return the list of the events
     */
    List<Event> findAllBy();

    /**
     * Method which updates the event given in the parameters
     *
     * @param event the event which needs to be updated
     */
    void save(Event event);
}

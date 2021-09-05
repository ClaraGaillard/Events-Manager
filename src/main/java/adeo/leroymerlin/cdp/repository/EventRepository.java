package adeo.leroymerlin.cdp.repository;

import adeo.leroymerlin.cdp.model.Event;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface EventRepository extends Repository<Event, Long> {

    void delete(Long eventId);

    List<Event> findAllBy();
}

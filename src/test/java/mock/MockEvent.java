package mock;

import adeo.leroymerlin.cdp.model.Event;

import java.util.ArrayList;
import java.util.List;

public class MockEvent {

    public static Event createMockedEvent() {
        Event event = new Event();
        event.setTitle("Title");
        event.setId(1L);
        event.setImgUrl("");
        event.setComment("Comment");
        event.setNbStars(5);
        event.setBands(MockBands.createMockedBands());

        return event;
    }

    public static List<Event> createMockedEvents() {
        List<Event> events = new ArrayList<>();
        Event event = createMockedEvent();

        events.add(event);

        return events;
    }

}

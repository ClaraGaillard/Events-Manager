package mock;

import adeo.leroymerlin.cdp.model.Band;

import java.util.HashSet;
import java.util.Set;

public class MockBands {

    public static Set<Band> createMockedBands() {
        Set<Band> bands = new HashSet<>();

        Band band = new Band();
        band.setName("Band");
        band.setMembers(MockMembers.createMockedMembers());

        bands.add(band);

        return bands;
    }

}

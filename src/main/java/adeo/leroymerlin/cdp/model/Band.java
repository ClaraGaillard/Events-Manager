package adeo.leroymerlin.cdp.model;

import javax.persistence.*;
import java.util.Set;

/**
 * The Band entity, which represents a group of musicians and/or singers.
 *
 */
@Entity
public class Band {

    /**
     * The identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Represents the name of the band
     */
    private String name;

    /**
     * Represents the number of people in the band
     */
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Member> members;

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

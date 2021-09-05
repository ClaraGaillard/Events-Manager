package adeo.leroymerlin.cdp.model;

import javax.persistence.*;
import java.util.Set;

/**
 * The Event entity, which represents an artistic event.
 * It usually lasts few days and bands come to play their music.
 *
 */
@Entity
public class Event {

    /**
     * The identifier
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * The title of the event
     */
    private String title;

    /**
     * The image to promote the event
     */
    private String imgUrl;

    /**
     * The list of bands who are going to come and play music
     */
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Band> bands;

    /**
     * The number of stars corresponds to a rating scale
     */
    private Integer nbStars;

    /**
     * The comments let by people who came to the event
     */
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Band> getBands() {
        return bands;
    }

    public void setBands(Set<Band> bands) {
        this.bands = bands;
    }

    public Integer getNbStars() {
        return nbStars;
    }

    public void setNbStars(Integer nbStars) {
        this.nbStars = nbStars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

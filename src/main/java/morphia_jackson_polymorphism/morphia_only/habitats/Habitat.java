package morphia_jackson_polymorphism.morphia_only.habitats;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

@Entity(noClassnameStored=false)
public abstract class Habitat {

    @Property("_t")
    private String type;

    @Property("country")
    private String country;

    protected Habitat(final String pName) {
        this.type = pName;
    }

    public Habitat(final String pName, final String pCountry) {
        this.type = pName;
        this.country = pCountry;
    }

    public String getCountry() { return country; }
}

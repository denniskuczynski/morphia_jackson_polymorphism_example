package morphia_jackson_polymorphism.mongodb_pojo.habitats;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Property;

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

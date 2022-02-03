package morphia_jackson_polymorphism.morphia_with_jackson.habitats;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Property;

import com.fasterxml.jackson.annotation.JsonTypeInfo;  
import com.fasterxml.jackson.annotation.JsonSubTypes;

@Entity(noClassnameStored=true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_t")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Grassland.class, name = "grassland"),
    @JsonSubTypes.Type(value = Woodland.class, name = "woodland")})
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

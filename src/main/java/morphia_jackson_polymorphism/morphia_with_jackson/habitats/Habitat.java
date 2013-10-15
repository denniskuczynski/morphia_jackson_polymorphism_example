package morphia_jackson_polymorphism.morphia_with_jackson.habitats;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import org.codehaus.jackson.annotate.JsonTypeInfo;  
import org.codehaus.jackson.annotate.JsonSubTypes;

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

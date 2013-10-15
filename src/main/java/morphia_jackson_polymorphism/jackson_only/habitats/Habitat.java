package morphia_jackson_polymorphism.jackson_only.habitats;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;  
import org.codehaus.jackson.annotate.JsonSubTypes;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_t")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Grassland.class, name = "grassland"),
    @JsonSubTypes.Type(value = Woodland.class, name = "woodland")})
public abstract class Habitat {

    private String type;

    @JsonProperty("country")
    private String country;

    public Habitat(final String pName, final String pCountry) {
        this.type = pName;
        this.country = pCountry;
    }

    public String getCountry() { return country; }
}

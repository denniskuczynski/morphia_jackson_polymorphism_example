package morphia_jackson_polymorphism.jackson_only.habitats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;  
import com.fasterxml.jackson.annotation.JsonSubTypes;

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

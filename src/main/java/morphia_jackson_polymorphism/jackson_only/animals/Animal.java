package morphia_jackson_polymorphism.jackson_only.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;  
import com.fasterxml.jackson.annotation.JsonSubTypes;

import morphia_jackson_polymorphism.jackson_only.habitats.*;

import java.util.List;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_t")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Lion.class, name = "lion"),
    @JsonSubTypes.Type(value = Tiger.class, name = "tiger")})
public abstract class Animal {

    //@JsonProperty not required as JsonTypeInfo will serialize it
    private String type;

    @JsonProperty("habitat")
    private List<Habitat> habitats;

    public Animal(final String pName, final List<Habitat> pHabitats) {
        this.type = pName;
        this.habitats = pHabitats;
    }

    public List<Habitat> getHabitats() { return habitats; }
}

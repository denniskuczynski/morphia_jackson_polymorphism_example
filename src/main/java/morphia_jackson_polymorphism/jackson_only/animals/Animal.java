package morphia_jackson_polymorphism.jackson_only.animals;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;  
import org.codehaus.jackson.annotate.JsonSubTypes;

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

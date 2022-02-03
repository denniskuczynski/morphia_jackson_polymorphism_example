package morphia_jackson_polymorphism.morphia_with_jackson.animals;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Id;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Property;
import dev.morphia.annotations.Embedded;

import com.fasterxml.jackson.annotation.JsonTypeInfo;  
import com.fasterxml.jackson.annotation.JsonSubTypes;

import morphia_jackson_polymorphism.morphia_with_jackson.habitats.*;

import java.util.List;

@Entity(value="animals", noClassnameStored=true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_t")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Lion.class, name = "lion"),
    @JsonSubTypes.Type(value = Tiger.class, name = "tiger")})
public abstract class Animal {

    @Id
    private ObjectId id;

    @Property("_t")
    private String type;

    @Embedded("habitat")
    private List<Habitat> habitats;

    protected Animal(final String pName) {
        this.type = pName;
    }

    public Animal(final String pName, final List<Habitat> pHabitats) {
        this.type = pName;
        this.habitats = pHabitats;
    }

    public ObjectId getId() { return id; }
    public List<Habitat> getHabitats() { return habitats; }
}

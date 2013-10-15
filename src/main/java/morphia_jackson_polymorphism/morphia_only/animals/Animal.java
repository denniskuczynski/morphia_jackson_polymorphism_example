package morphia_jackson_polymorphism.morphia_only.animals;

import org.bson.types.ObjectId;

import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Embedded;

import morphia_jackson_polymorphism.morphia_only.habitats.*;

import java.util.List;

@Entity(value="animals", noClassnameStored=false)
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

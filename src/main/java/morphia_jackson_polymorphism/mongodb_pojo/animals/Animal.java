package morphia_jackson_polymorphism.mongodb_pojo.animals;

import org.bson.types.ObjectId;

import org.bson.codecs.pojo.annotations.*;

import morphia_jackson_polymorphism.mongodb_pojo.habitats.*;

import java.util.List;

@BsonDiscriminator
public abstract class Animal {

    @BsonId
    private ObjectId id;

    @BsonProperty("_t")
    private String type;

    @BsonProperty("habitat")
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

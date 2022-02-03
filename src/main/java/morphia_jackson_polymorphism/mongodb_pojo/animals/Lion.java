package morphia_jackson_polymorphism.mongodb_pojo.animals;

import org.bson.codecs.pojo.annotations.*;

import morphia_jackson_polymorphism.mongodb_pojo.habitats.*;

import java.util.Arrays;

public class Lion extends Animal {

    @BsonProperty("maneColor")
    private String maneColor;

    private Lion() {
        // Morphia Constructor
        super("lion");
    }

    public Lion(final String pManeColor) {
        super("lion", Arrays.asList(new Habitat[] { new Woodland("Africa"), new Grassland("Africa") }));
        this.maneColor = pManeColor;
    }

    public String getManeColor() { return maneColor; }
}

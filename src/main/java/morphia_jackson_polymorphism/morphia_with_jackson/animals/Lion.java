package morphia_jackson_polymorphism.morphia_with_jackson.animals;

import org.mongodb.morphia.annotations.Property;

import morphia_jackson_polymorphism.morphia_with_jackson.habitats.*;

import java.util.Arrays;

public class Lion extends Animal {

    @Property("maneColor")
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

package morphia_jackson_polymorphism.jackson_only.animals;

import org.codehaus.jackson.annotate.JsonProperty;

import morphia_jackson_polymorphism.jackson_only.habitats.*;

import java.util.Arrays;

public class Lion extends Animal {

    @JsonProperty("maneColor")
    private String maneColor;

    public Lion(final String pManeColor) {
        super("lion", Arrays.asList(new Habitat[] { new Woodland("Africa"), new Grassland("Africa") }));
        this.maneColor = pManeColor;
    }

    public String getManeColor() { return maneColor; }
}

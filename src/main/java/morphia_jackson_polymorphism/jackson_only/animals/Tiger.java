package morphia_jackson_polymorphism.jackson_only.animals;

import org.codehaus.jackson.annotate.JsonProperty;

import morphia_jackson_polymorphism.jackson_only.habitats.*;

import java.util.Arrays;

public class Tiger extends Animal {

    @JsonProperty("stripeCount")
    private int stripeCount;

    public Tiger(final int pStripeCount) {
        super("tiger", Arrays.asList(new Habitat[] { new Grassland("India"), new Grassland("Asia") }));
        this.stripeCount = pStripeCount;
    }

    public int getStripeCount() { return stripeCount; }
}

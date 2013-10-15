package morphia_jackson_polymorphism.morphia_only.animals;

import org.mongodb.morphia.annotations.Property;

import morphia_jackson_polymorphism.morphia_only.habitats.*;

import java.util.Arrays;

public class Tiger extends Animal {

    @Property("stripeCount")
    private int stripeCount;

    private Tiger() {
        // Morphia Constructor
        super("tiger");
    }

    public Tiger(final int pStripeCount) {
        super("tiger", Arrays.asList(new Habitat[] { new Grassland("India"), new Grassland("Asia") }));
        this.stripeCount = pStripeCount;
    }

    public int getStripeCount() { return stripeCount; }
}

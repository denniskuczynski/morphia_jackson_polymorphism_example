package morphia_jackson_polymorphism.mongodb_pojo.animals;

import org.bson.codecs.pojo.annotations.*;

import morphia_jackson_polymorphism.mongodb_pojo.habitats.*;

import java.util.Arrays;

public class Tiger extends Animal {

    @BsonProperty("stripeCount")
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

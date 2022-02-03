package morphia_jackson_polymorphism.mongodb_pojo.habitats;

public class Woodland extends Habitat {

    private Woodland() {
        // Morphia Constructor
        super("woodland");
    }

    public Woodland(final String pCountry) {
        super("woodland", pCountry);
    }
}

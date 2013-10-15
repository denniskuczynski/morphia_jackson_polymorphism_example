package morphia_jackson_polymorphism.morphia_only.habitats;

public class Woodland extends Habitat {

    private Woodland() {
        // Morphia Constructor
        super("woodland");
    }

    public Woodland(final String pCountry) {
        super("woodland", pCountry);
    }
}

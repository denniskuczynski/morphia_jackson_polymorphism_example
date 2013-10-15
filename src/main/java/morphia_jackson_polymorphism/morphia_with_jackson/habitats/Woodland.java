package morphia_jackson_polymorphism.morphia_with_jackson.habitats;

public class Woodland extends Habitat {

    private Woodland() {
        // Morphia Constructor
        super("woodland");
    }

    public Woodland(final String pCountry) {
        super("woodland", pCountry);
    }
}

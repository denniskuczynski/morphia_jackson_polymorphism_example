package morphia_jackson_polymorphism.morphia_with_jackson.habitats;

public class Grassland extends Habitat {

    private Grassland() {
        // Morphia Constructor
        super("grassland");
    }

    public Grassland(final String pCountry) {
        super("grassland", pCountry);
    }
}

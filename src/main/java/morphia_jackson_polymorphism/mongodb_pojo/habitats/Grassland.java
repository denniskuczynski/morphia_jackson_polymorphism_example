package morphia_jackson_polymorphism.mongodb_pojo.habitats;

public class Grassland extends Habitat {

    private Grassland() {
        // Morphia Constructor
        super("grassland");
    }

    public Grassland(final String pCountry) {
        super("grassland", pCountry);
    }
}

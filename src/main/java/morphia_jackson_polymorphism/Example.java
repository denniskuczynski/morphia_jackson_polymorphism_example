package morphia_jackson_polymorphism;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.mapping.Mapper;

public class Example {

    public static void main(String[] args) {
        try {
            ObjectMapper mapper = createMapper();

            morphia_jackson_polymorphism.jackson_only.animals.Lion lion1 =
                new morphia_jackson_polymorphism.jackson_only.animals.Lion("tan");
            morphia_jackson_polymorphism.jackson_only.animals.Tiger tiger1 =
            new morphia_jackson_polymorphism.jackson_only.animals.Tiger(42);

            System.out.println("Example Mapping from Jackson:");
            System.out.println("---------------------------------");
            System.out.println("Lion");
            System.out.println("---------------------------------");
            System.out.println(mapper.writeValueAsString(lion1));
            System.out.println("Tiger");
            System.out.println("---------------------------------");
            System.out.println(mapper.writeValueAsString(tiger1));

            Morphia morphia1 = new Morphia();
            morphia1.mapPackage("morphia_jackson_polymorphism.morphia_only");

            morphia_jackson_polymorphism.morphia_only.animals.Lion lion2 =
                new morphia_jackson_polymorphism.morphia_only.animals.Lion("tan");
            morphia_jackson_polymorphism.morphia_only.animals.Tiger tiger2 =
            new morphia_jackson_polymorphism.morphia_only.animals.Tiger(42);

            System.out.println("Example Mapping from Morphia:");
            System.out.println("---------------------------------");
            System.out.println("Lion");
            System.out.println("---------------------------------");
            System.out.println(morphia1.toDBObject(lion2));
            System.out.println("Tiger");
            System.out.println("---------------------------------");
            System.out.println(morphia1.toDBObject(tiger2));
            Datastore ds1 = morphia1.createDatastore("morphia_only");
            ds1.save(lion2);
            ds1.save(tiger2);
            lion2 = ds1.find(morphia_jackson_polymorphism.morphia_only.animals.Lion.class, "_id", lion2.getId()).get();
            tiger2 = ds1.find(morphia_jackson_polymorphism.morphia_only.animals.Tiger.class, "_id", tiger2.getId()).get();

            System.out.println("---------------------------------");
            System.out.println("Lion (from DB)");
            System.out.println("---------------------------------");
            System.out.println(lion2);
            System.out.println("Tiger (from DB)");
            System.out.println("---------------------------------");
            System.out.println(tiger2);

            Morphia morphia2 = new Morphia();
            // Add a hook for a custom object factory
            final Mapper morphiaMapper = morphia2.getMapper();
            morphiaMapper.getOptions().objectFactory = new CustomMorphiaObjectFactory();
            morphia2.mapPackage("morphia_jackson_polymorphism.morphia_with_jackson");

            morphia_jackson_polymorphism.morphia_with_jackson.animals.Lion lion3 =
                new morphia_jackson_polymorphism.morphia_with_jackson.animals.Lion("tan");
            morphia_jackson_polymorphism.morphia_with_jackson.animals.Tiger tiger3 =
            new morphia_jackson_polymorphism.morphia_with_jackson.animals.Tiger(42);

            System.out.println("Example Mapping from Morphia with Jackson:");
            System.out.println("---------------------------------");
            System.out.println("Lion");
            System.out.println("---------------------------------");
            System.out.println(morphia2.toDBObject(lion3));
            System.out.println("Tiger");
            System.out.println("---------------------------------");
            System.out.println(morphia2.toDBObject(tiger3));

            Datastore ds2 = morphia2.createDatastore("morphia_with_jackson");
            ds2.save(lion3);
            ds2.save(tiger3);
            lion3 = ds2.find(morphia_jackson_polymorphism.morphia_with_jackson.animals.Lion.class, "_id", lion3.getId()).get();
            tiger3 = ds2.find(morphia_jackson_polymorphism.morphia_with_jackson.animals.Tiger.class, "_id", tiger3.getId()).get();
            System.out.println("---------------------------------");
            System.out.println("Lion (from DB)");
            System.out.println("---------------------------------");
            System.out.println(lion3);
            System.out.println("Tiger (from DB)");
            System.out.println("---------------------------------");
            System.out.println(tiger3);
        } catch(Throwable t) {
            System.out.println("Exception in Example");
            t.printStackTrace();
        }
    }

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector();
        AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
        AnnotationIntrospector introspector = new AnnotationIntrospector.Pair(jacksonIntrospector, jaxbIntrospector);
        mapper.setDeserializationConfig(mapper.getDeserializationConfig().withAnnotationIntrospector(introspector).
            without(DeserializationConfig.Feature.AUTO_DETECT_CREATORS).
            without(DeserializationConfig.Feature.AUTO_DETECT_FIELDS));
        mapper.setSerializationConfig(mapper.getSerializationConfig().withAnnotationIntrospector(introspector).
            without(SerializationConfig.Feature.AUTO_DETECT_GETTERS).
            without(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS).
            without(SerializationConfig.Feature.AUTO_DETECT_FIELDS));
        return mapper;
    }
}

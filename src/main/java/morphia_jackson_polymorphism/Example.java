package morphia_jackson_polymorphism;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.mongodb.MongoClient;
import dev.morphia.Morphia;
import dev.morphia.Datastore;
import com.fasterxml.jackson.databind.MapperFeature;
import dev.morphia.mapping.Mapper;
import morphia_jackson_polymorphism.mongodb_pojo.animals.Animal;

import org.bson.BsonWriter;
import org.bson.BsonBinaryWriter;
import org.bson.io.BasicOutputBuffer;
import org.bson.io.ByteBufferBsonInput;
import org.bson.io.OutputBuffer;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

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
            Datastore ds1 = morphia1.createDatastore(new MongoClient(), "morphia_only");
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
            morphiaMapper.setOptions(morphiaMapper.getOptions().builder().objectFactory(new CustomMorphiaObjectFactory()).build());
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

            Datastore ds2 = morphia2.createDatastore(new MongoClient(), "morphia_with_jackson");
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

            morphia_jackson_polymorphism.mongodb_pojo.animals.Lion lion4 =
                new morphia_jackson_polymorphism.mongodb_pojo.animals.Lion("tan");
            morphia_jackson_polymorphism.mongodb_pojo.animals.Tiger tiger4 =
            new morphia_jackson_polymorphism.mongodb_pojo.animals.Tiger(42);

            CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
              .register("morphia_jackson_polymorphism.mongodb_pojo.animals")
              .register("morphia_jackson_polymorphism.mongodb_pojo.habitats")
              .build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            System.out.println("Example Mapping from MongoDB POJO:");
            System.out.println("---------------------------------");
            System.out.println("Lion");
            System.out.println("---------------------------------");
            OutputBuffer buffer = new BasicOutputBuffer();
            BsonWriter writer = new BsonBinaryWriter(buffer);
            pojoCodecRegistry.get(Animal.class).encode(writer, lion4, EncoderContext.builder().build());
            System.out.println(buffer);
            System.out.println("Tiger");
            System.out.println("---------------------------------");
            buffer = new BasicOutputBuffer();
            writer = new BsonBinaryWriter(buffer);
            pojoCodecRegistry.get(Animal.class).encode(writer, tiger4, EncoderContext.builder().build());
            System.out.println(buffer);
        } catch(Throwable t) {
            System.out.println("Exception in Example");
            t.printStackTrace();
        }
    }

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JaxbAnnotationModule());
        mapper.disable(MapperFeature.AUTO_DETECT_CREATORS).
          disable(MapperFeature.AUTO_DETECT_FIELDS).
          disable(MapperFeature.AUTO_DETECT_GETTERS).
          disable(MapperFeature.AUTO_DETECT_IS_GETTERS).
          disable(MapperFeature.AUTO_DETECT_FIELDS);
        return mapper;
    }
}

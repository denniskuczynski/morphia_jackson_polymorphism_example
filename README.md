# Class Inheritance Mappings in Jackson (JSON) and Morphia (MongoDB)

[Jackson|https://github.com/FasterXML/jackson] is a library for mapping Java classes to JSON.

[Morphia|https://github.com/mongodb/morphia] is a library for mapping Java classes to MongoDB.

This example shows how to map classes with inheritance/subclass relationships with both frameworks.

It maps an Animal class with two subtypes (Lion and Tiger), each containing a list of Habitats which also has two subtypes (Grassland and Woodland).

## Mapping with Jackson

Jackson leverages the JsonTypeInfo annotation to define the discriminators for polymorphic types.
http://wiki.fasterxml.com/JacksonPolymorphicDeserialization

See:
[Animal.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/animals/Animal.java]
[Lion.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/animals/Lion.java]
[Tiger.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/animals/Tiger.java]

[Habitat.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/habitats/Habitat.java]
[Grassland.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/habitats/Grassland.java]
[Woodland.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/habitats/Woodland.java]

## Mapping with Morphia

Morphia doesn't need any special annotations, but the Entity annotation must have noClassnameStored=false because the classname is used for deserialization.

[Animal.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/animals/Animal.java]
[Lion.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/animals/Lion.java]
[Tiger.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/animals/Tiger.java]

[Habitat.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/habitats/Habitat.java]
[Grassland.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/habitats/Grassland.java]
[Woodland.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/habitats/Woodland.java]

## Customizing Morphia to use Jackson JsonTypeInfo Annotations

[Animal.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/animals/Animal.java]
[Lion.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/animals/Lion.java]
[Tiger.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/animals/Tiger.java]

[Habitat.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/habitats/Habitat.java]
[Grassland.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/habitats/Grassland.java]
[Woodland.java|https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/habitats/Woodland.java]

## Running the Example

mvn compile
mvn exec:java -Dexec.mainClass="morphia_jackson_polymorphism.Example"

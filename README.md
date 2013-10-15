# Class Inheritance Mappings in Jackson (JSON) and Morphia (MongoDB)

[Jackson](https://github.com/FasterXML/jackson) is a library for mapping Java classes to JSON.

[Morphia](https://github.com/mongodb/morphia) is a library for mapping Java classes to MongoDB.

This example shows how to map classes with inheritance/subclass relationships with both frameworks.

It maps an Animal class with two subtypes (Lion and Tiger), each containing a list of Habitats which also has two subtypes (Grassland and Woodland).

## Mapping with Jackson

Jackson leverages the JsonTypeInfo annotation to define the discriminators for polymorphic types.
http://wiki.fasterxml.com/JacksonPolymorphicDeserialization

See:
[Animal.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/animals/Animal.java)
[Lion.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/animals/Lion.java)
[Tiger.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/animals/Tiger.java)

[Habitat.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/habitats/Habitat.java)
[Grassland.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/habitats/Grassland.java)
[Woodland.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/jackson_only/habitats/Woodland.java)

```
Example Mapping from Jackson:
---------------------------------
Lion
---------------------------------
{"_t":"lion","maneColor":"tan","habitat":[{"_t":"woodland","country":"Africa"},{"_t":"grassland","country":"Africa"}]}
Tiger
---------------------------------
{"_t":"tiger","stripeCount":42,"habitat":[{"_t":"grassland","country":"India"},{"_t":"grassland","country":"Asia"}]}
```

## Mapping with Morphia

Morphia doesn't need any special annotations, but the Entity annotation must have noClassnameStored=false because the classname is used for deserialization.

[Animal.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/animals/Animal.java)
[Lion.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/animals/Lion.java)
[Tiger.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/animals/Tiger.java)

[Habitat.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/habitats/Habitat.java)
[Grassland.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/habitats/Grassland.java)
[Woodland.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_only/habitats/Woodland.java)

```
Example Mapping from Morphia:
---------------------------------
Lion
---------------------------------
{ "className" : "morphia_jackson_polymorphism.morphia_only.animals.Lion" , "maneColor" : "tan" , "_t" : "lion" , "habitat" : [ { "className" : "morphia_jackson_polymorphism.morphia_only.habitats.Woodland" , "_t" : "woodland" , "country" : "Africa"} , { "className" : "morphia_jackson_polymorphism.morphia_only.habitats.Grassland" , "_t" : "grassland" , "country" : "Africa"}]}
Tiger
---------------------------------
{ "className" : "morphia_jackson_polymorphism.morphia_only.animals.Tiger" , "stripeCount" : 42 , "_t" : "tiger" , "habitat" : [ { "className" : "morphia_jackson_polymorphism.morphia_only.habitats.Grassland" , "_t" : "grassland" , "country" : "India"} , { "className" : "morphia_jackson_polymorphism.morphia_only.habitats.Grassland" , "_t" : "grassland" , "country" : "Asia"}]}
---------------------------------
Lion (from DB)
---------------------------------
morphia_jackson_polymorphism.morphia_only.animals.Lion@59b80684
Tiger (from DB)
---------------------------------
morphia_jackson_polymorphism.morphia_only.animals.Tiger@40b3f220
```

## Customizing Morphia to use Jackson JsonTypeInfo Annotations

We can leverage the style of Jackson of serialization with a type field discriminator by configuring Morphia to use a custom object factory that inspects for Jackson's JsonTypeInfo annotation which defines discriminators. Now classnames are not required in the serialized objects.

[Animal.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/animals/Animal.java)
[Lion.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/animals/Lion.java)
[Tiger.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/animals/Tiger.java)

[Habitat.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/habitats/Habitat.java)
[Grassland.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/habitats/Grassland.java)
[Woodland.java](https://github.com/denniskuczynski/morphia_jackson_polymorphism_example/blob/master/src/main/java/morphia_jackson_polymorphism/morphia_with_jackson/habitats/Woodland.java)

```
Example Mapping from Morphia with Jackson:
---------------------------------
Lion
---------------------------------
{ "maneColor" : "tan" , "_t" : "lion" , "habitat" : [ { "_t" : "woodland" , "country" : "Africa"} , { "_t" : "grassland" , "country" : "Africa"}]}
Tiger
---------------------------------
{ "stripeCount" : 42 , "_t" : "tiger" , "habitat" : [ { "_t" : "grassland" , "country" : "India"} , { "_t" : "grassland" , "country" : "Asia"}]}
---------------------------------
Lion (from DB)
---------------------------------
morphia_jackson_polymorphism.morphia_with_jackson.animals.Lion@3bf77f86
Tiger (from DB)
---------------------------------
morphia_jackson_polymorphism.morphia_with_jackson.animals.Tiger@75d5552b
```

## Running the Example

mvn compile
mvn exec:java -Dexec.mainClass="morphia_jackson_polymorphism.Example"

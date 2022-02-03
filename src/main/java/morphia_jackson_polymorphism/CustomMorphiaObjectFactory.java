package morphia_jackson_polymorphism;

import com.mongodb.DBObject;
import dev.morphia.mapping.DefaultCreator;

import com.fasterxml.jackson.annotation.JsonTypeInfo;  
import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.util.HashMap;

/**
 * Override the default Morphia Object Creation Strategy to leverage custom type discrimators.
 *
 * This implementation uses the Jackson JsonTypeInfo annotations to specify the discriminators.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CustomMorphiaObjectFactory extends DefaultCreator {
    
    @Override
    public Object createInstance(Class clazz, DBObject dbObj) {
        Class c = null;
        JsonTypeInfo jsonTypeInfo = getJsonTypeInfo(clazz);
        if (jsonTypeInfo != null) {
            String fieldName = jsonTypeInfo.property();
            if (fieldName != null) {
                String discriminator = (String)dbObj.get(fieldName);
                JsonSubTypes subtypes = getJsonSubTypes(clazz);
                JsonSubTypes.Type[] types = subtypes.value();
                for (int i=0; i < types.length; i++) {
                    if (types[i].name().equals(discriminator)) {
                        c = types[i].value();
                        break;
                    }
                }
            }
        }
        if (c == null) { c = clazz; }
        return super.createInstance(c);
    }

    // Retrieve or cache the type info for this class
    public JsonTypeInfo getJsonTypeInfo(Class clazz) {
        JsonTypeInfo typeInfo = typeInfos.get(clazz);
        if (typeInfo == null) {
            typeInfo = (JsonTypeInfo)clazz.getAnnotation(JsonTypeInfo.class);
            typeInfos.put(clazz, typeInfo);
        }
        return typeInfo;
    }

    // Retrieve or cache the type info for this class
    public JsonSubTypes getJsonSubTypes(Class clazz) {
        JsonSubTypes subType = subTypes.get(clazz);
        if (subType == null) {
            subType = (JsonSubTypes)clazz.getAnnotation(JsonSubTypes.class);
            subTypes.put(clazz, subType);
        }
        return subType;
    }

    private static final HashMap<Class, JsonTypeInfo> typeInfos = new HashMap<>();
    private static final HashMap<Class, JsonSubTypes> subTypes = new HashMap<>();
}

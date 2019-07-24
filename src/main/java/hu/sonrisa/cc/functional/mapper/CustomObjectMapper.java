package hu.sonrisa.cc.functional.mapper;

import hu.sonrisa.cc.functional.model.LittleMoreComplexObject;
import hu.sonrisa.cc.functional.model.SimpleObject;
import org.apache.commons.lang3.tuple.Pair;

public class CustomObjectMapper {

    public static LittleMoreComplexObject simpleToLittleMoreComplexObject(SimpleObject simpleObject){
        return LittleMoreComplexObject.builder()
            .pairMember(Pair.of(simpleObject.getStringMember(), simpleObject.getIntMember()))
            .stringListMember(simpleObject.getStringListMember())
            .build();
    }

    public static SimpleObject littleMoreComplexObjectToSimple(LittleMoreComplexObject complexObject){
        return SimpleObject.builder()
            .stringMember(complexObject.getPairMember().getKey())
            .intMember(complexObject.getPairMember().getValue())
            .stringListMember(complexObject.getStringListMember())
            .build();
    }
}

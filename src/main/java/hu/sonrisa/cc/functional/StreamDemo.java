package hu.sonrisa.cc.functional;


import static java.util.stream.Collectors.toList;

import com.annimon.stream.Stream;
import com.google.common.collect.Streams;
import hu.sonrisa.cc.functional.mapper.CustomObjectMapper;
import hu.sonrisa.cc.functional.model.LittleMoreComplexObject;
import hu.sonrisa.cc.functional.model.SimpleObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.tuple.Pair;

public class StreamDemo {

     static void ranging() {
         var intList = new int[10];
         for(int i = 1; i < 11; ++i){
             intList[i - 1] = i;
         }

         var streamRange = IntStream.rangeClosed(1, 10).boxed();

         print(
             () -> {
                 for (int i : intList) {
                     System.out.print(i);
                 }
                 System.out.println();
             },
             () -> streamRange.forEach(System.out::print)
         );
     }

     static void zipping(){
         var letterList = createStringList();
         var numberList = Arrays.asList(1, 2, 3);

         var lettersWithNumbersForLoop = new ArrayList<String>();
         for(int i = 0; i < Integer.min(letterList.size(), numberList.size()); ++i){
             lettersWithNumbersForLoop.add(letterList.get(i) + ": " + numberList.get(i));
         }

         var lettersWithNumbers = Streams
            .zip(letterList.stream(), numberList.stream(), (letter, number) -> letter + ": " + number);

         print(
             () -> lettersWithNumbersForLoop.forEach(System.out::println),
             () -> lettersWithNumbers.forEach(System.out::println)
         );
    }

    static void simpleMapping(){
        var stringList = createStringList();

        var modifiedStringListLoop = new ArrayList<String>();
        for (String s : stringList) {
            modifiedStringListLoop.add(s + "lma");
        }

        var modifiedStringList = stringList.stream().map(string -> string + "lma");

        print(
            () -> modifiedStringListLoop.forEach(System.out::println),
            () -> modifiedStringList.forEach(System.out::println)
        );
    }

    static void objectMapping(){
        var simpleObjectList = createSimpleObjectList();

        var complexObjectListLoop = new ArrayList<LittleMoreComplexObject>();
        simpleObjectList.forEach(simpleObject ->
            complexObjectListLoop.add(CustomObjectMapper.simpleToLittleMoreComplexObject(simpleObject)));

        var complexObjectList = simpleObjectList.stream()
            .map(CustomObjectMapper::simpleToLittleMoreComplexObject)
            .collect(Collectors.toList());

        System.out.println("SimpleObject");
        simpleObjectList.forEach(System.out::println);
        System.out.println();

        print(
            () -> complexObjectListLoop.forEach(System.out::println),
            () -> complexObjectList.forEach(System.out::println)
        );
    }

    static void indexedMapping(){
         var stringList = createStringList();

         var indexedStrings = new ArrayList<Pair<Integer, String>>();
         for(int i = 0; i < stringList.size(); ++i){
            indexedStrings.add(Pair.of(i, stringList.get(i)));
         }

         var pairedWithIndex = Stream.of(stringList).mapIndexed(Pair::of);

         print(
             () -> indexedStrings.forEach(StreamDemo::printIndexedResult),
             () -> pairedWithIndex.forEach(StreamDemo::printIndexedResult)
         );
    }

    static void reducing(){
        var simpleObjectList = createSimpleObjectList();
        var lesserIntValue = simpleObjectList.stream()
            .reduce((a,  b) -> a.getIntMember() <= b.getIntMember() ? a : b);

        simpleObjectList.forEach(System.out::println);
        System.out.println();
        System.out.println(lesserIntValue);
    }

    static void filtering(){
         var simpleObjectList = createSimpleObjectList();
         var intsBiggerThan2 = simpleObjectList.stream().filter(object -> object.getIntMember() > 2);

         intsBiggerThan2.forEach(System.out::println);
    }

    static void matching(){
         var simpleObjectList = createSimpleObjectList();
         var anyMatched = simpleObjectList.stream().anyMatch(object -> object.getIntMember() > 2);
         var allMatched = simpleObjectList.stream().allMatch(object -> object.getIntMember() > 2);

         System.out.println("Is there at least one object with an intMember bigger than 2? " + getAnswer(anyMatched));
         System.out.println("Is the intMember bigger than 2 in all of the objects? " + getAnswer(allMatched));
    }

    static void flatMapping(){
        var simpleObjectList = createSimpleObjectList();
        var allStringInSimpleObjects = simpleObjectList.stream().flatMap(
            simpleObject -> simpleObject.getStringListMember().stream()
        ).collect(toList());

        simpleObjectList.forEach(System.out::println);
        allStringInSimpleObjects.forEach(System.out::println);
    }


    static void collectingAndEliminatingDuplicates(){
        var duplicatedStream = java.util.stream.Stream
            .concat(createSimpleObjectList().stream(),
                    createSimpleObjectList().stream()
            ).collect(toList());
        var distinctStream = duplicatedStream.stream().distinct();

        duplicatedStream.forEach(System.out::println);
        System.out.println();
        distinctStream.forEach(System.out::println);
    }

    static void usingParalellStream(){
         var intMembers = createSimpleObjectList().parallelStream()
             .map(SimpleObject::getIntMember)
             .collect(Collectors.toList());
         intMembers.forEach(System.out::println);
    }

    private static void print(Runnable soutLoop, Runnable soutStream) {
        System.out.println("Loop");
        soutLoop.run();
        System.out.println("Stream");
        soutStream.run();
    }

    private static List<SimpleObject> createSimpleObjectList() {
        return Arrays.asList(
            createSimpleObject(2, "secondString"),
            createSimpleObject(3, "secondString"),
            createSimpleObject(1, "string"),
            createSimpleObject(4, "fourthString")
        );
    }

    private static SimpleObject createSimpleObject(Integer intMember, String stringMember) {
        return SimpleObject.builder()
            .stringListMember(createStringList())
            .intMember(intMember)
            .stringMember(stringMember)
            .build();
    }

    private static List<String> createStringList() {
        return Arrays.asList("a", "b", "c", "d");
    }

    private static void printIndexedResult(Pair<Integer, String> pair) {
        System.out.println(pair.getKey() + ": '" + pair.getValue() + "'");
    }

    private static String getAnswer(boolean isTrue) {
        return " - " + (isTrue ? "jis" : "nu");
    }
}

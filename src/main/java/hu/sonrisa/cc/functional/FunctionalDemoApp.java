package hu.sonrisa.cc.functional;

public class FunctionalDemoApp {
    public static void main(String[] args) {

        System.out.println("Lambdas");
        LambdaDemo.showLambdaOrigins();
        System.out.println();

        System.out.println("Ranging");
        StreamDemo.ranging();
        System.out.println();
        System.out.println();

        System.out.println("Zipping");
        StreamDemo.zipping();
        System.out.println();

        System.out.println("IndexedMapping");
        StreamDemo.indexedMapping();
        System.out.println();

        System.out.println("Simple mapping of string");
        StreamDemo.simpleMapping();
        System.out.println();

        System.out.println("Mapping objects");
        StreamDemo.objectMapping();
        System.out.println();

        System.out.println("FlatMapping");
        StreamDemo.flatMapping();
        System.out.println();

        System.out.println("Reducing");
        StreamDemo.reducing();
        System.out.println();

        System.out.println("Filtering");
        StreamDemo.filtering();
        System.out.println();

        System.out.println("Matching");
        StreamDemo.matching();
        System.out.println();

        System.out.println("Using collect and eliminate duplicates in List/Stream");
        StreamDemo.collectingAndEliminatingDuplicates();
        System.out.println();

        System.out.println("Using paralell stream");
        StreamDemo.usingParalellStream();
        System.out.println();
    }
}


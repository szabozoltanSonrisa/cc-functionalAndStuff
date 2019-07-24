package hu.sonrisa.cc.functional;

import java.util.Comparator;

public class LambdaDemo {
    static void showLambdaOrigins(){
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        Comparator<String> lambdaComparator = (o1, o2) -> o1.compareTo(o2);

        Comparator<String> lambdaComparatorReference = String::compareTo;

        System.out.println(comparator.compare("a", "b"));
        System.out.println(lambdaComparator.compare("a", "b"));
        System.out.println(lambdaComparatorReference.compare("a", "b"));
    }
}

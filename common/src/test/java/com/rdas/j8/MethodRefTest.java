package com.rdas.j8;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

//https://www.codementor.io/eh3rrera/using-java-8-method-reference-du10866vx
//http://www.studytrails.com/java/java8/java8_lambdas_functionalprogramming/
//https://blog.idrsolutions.com/2015/02/java-8-method-references-explained-5-minutes/
//https://blog.idrsolutions.com/2015/03/java-8-consumer-supplier-explained-in-5-minutes/
public class MethodRefTest {

    @Test
    public void testConsumer() {
        simpleConsumer.accept("Rana");
        anotherConsumer.accept("Das");
    }

    //The consumer accepts a single argument by calling its accept (args) method and does not return any value making it a void method.
    public Consumer<String> simpleConsumer = (s) -> System.out.println(s);
    public Consumer<String> simpleConsumerSame = s -> System.out.println(s);
    public Consumer<String> anotherConsumer = System.out::println;


    @Test
    public void testStaticRef() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16);
        List primeNumbers =
                findPrimeNumbers(numbers,
                        (number) -> isPrime((int) number) );

        System.out.println("Prime Numbers are " + primeNumbers);
    }
    public static boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static List findPrimeNumbers(List list, Predicate predicate) {
        List sortedNumbers = new ArrayList();
        list.stream()
                .filter(i -> predicate.test(i))
                .forEach(i -> sortedNumbers.add(i));
        return sortedNumbers;

    }


    //The supplier does the opposite of the consumer, it takes no arguments but it returns some value by calling its get() method.
    //Note: This may return different values when it is being called more than once.
    @Test
    public void supplierTest() {

    }
}

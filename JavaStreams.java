import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import java.util.*;
import java.nio.file.*;
import java.io.IOException;


public class JavaStreams {

    public static void main(String[] args) throws IOException {

        // Integer stream 
        IntStream
            .range(1,10)
            .skip(5)
            //.forEach(System.out::print);           // example of passing methods from class as a map function
            .forEach(x -> System.out.println(x));   // example of passing lambda function

        System.out.println("3. ");

        //3. IntStream
        System.out.println(IntStream.range(1,20).sum());

        System.out.println("4. ");

        //4. Stream.of, Sorted and findFirst
        Stream.of( "Tim","Raj", "shanker")
            .sorted()
            .findFirst()
            .ifPresent(System.out::println);

        //5. Using array with streams
        String[] names  = { "tim","raj", "shanker", "kisan", "raghav", "aditya"};
        Arrays.stream(names)
            .filter(x-> x.startsWith("r"))
            .sorted()
            .forEach(System.out::println);

        //6. Average of squares of int arrar
        Arrays.stream(new int[] {2,4,6,8,10})
            .map(x-> x*x)
            .average()
            .ifPresent(System.out::println);

        //7. Stram from List
        List<String> people = Arrays.asList("tim","raj", "Dhanker", "Kisan", "Raghav", "aditya");
        people
            .stream()
            .map(String::toLowerCase)
            .filter(x->x.startsWith("r"))
            .sorted()
            .forEach(System.out::println);

        //8. streams from textfiles
        Stream<String> bands = Files.lines(Paths.get("bands.txt"));
        bands
            .sorted()
            // .filter(x->x.startsWith("r"))
            .forEach(System.out::println);
        bands.close(); // important to prevent memory leaks

        //9. Read from text file to save to List
        List<String> bands2 = Files.lines(Paths.get("bands.txt"))
                                            .filter(x->x.contains(","))
                                            // .filter(x->x.startsWith("r"))
                                            .collect(Collectors.toList());

        bands2
        .forEach(System.out::println);

        //10. Stream row from csv file and count
        Stream<String> row1 = Files.lines(Paths.get("bands.txt"));
        int rowCount = (int) row1.count();

        System.out.println(rowCount);
        row1.close();

        //11. Stream to map reduction
        Stream<String> row2 = Files.lines(Paths.get("bands.txt"));
        Map<String, Integer> map = new HashMap<>();
        map = row2
                .map(x -> x.split(","))
                .collect(
                    Collectors.toMap(x->x[0], x->Integer.parseInt(x[1]))
                );
        row2.close();

        for( String key: map.keySet() ) {
            System.out.println(key+" => "+ map.get(key) );
        }

                
        // 12. Reduction - sum
        double total = Stream.of(7.3, 1.5, 4.8)
            .reduce(0.0, (Double a, Double b) -> a+b ); // here 0.0 is the initial value, a will be running total, b is new value

        System.out.println("Total = "+total);

        //13. Reduction to show summary statistics (only works for integers)
        IntSummaryStatistics summary = IntStream.of(1,2,5,3,9,7,8)
                                                .summaryStatistics();
        System.out.println(summary);



      
            
    }

}
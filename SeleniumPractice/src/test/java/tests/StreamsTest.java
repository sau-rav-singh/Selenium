package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.IntSummaryStatistics;
import java.util.List;

public class StreamsTest {

    @Test
    public void countNamesWithA() {
        List<String> names = List.of("Arnav", "Aadil", "Rohit", "Aakash", "Kohli");
        List<String> namesWithA = names.stream().filter(name -> name.startsWith("A")).toList();
        Assert.assertEquals(namesWithA.size(), 3, "Count of names starting with A should be 3");
        System.out.println("Names starting with A:");
        namesWithA.forEach(System.out::println);
    }

    @Test
    public void streamTransformations() {
        List<String> names = List.of("Arnav", "Aadil", "Rohit", "Aakash", "Kohli");
        
        List<String> transformedNames = names.stream()
                .filter(s -> s.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .toList();
        
        System.out.println("Transformed names: " + transformedNames);
        
        Assert.assertFalse(transformedNames.isEmpty(), "Transformed names list should not be empty");
        Assert.assertEquals(transformedNames.size(), 5, "Transformed names list should contain 5 elements");
        Assert.assertEquals(transformedNames.getFirst(), "AADIL", "First element should be AADIL after sorting");
        Assert.assertEquals(transformedNames.getLast(), "ROHIT", "Last element should be ROHIT after sorting");

        boolean isAadilTransformedPresent = transformedNames.stream().anyMatch(name -> name.equals("AADIL"));
        Assert.assertTrue(isAadilTransformedPresent, "'AADIL' should be present in the transformed list");
    }

    @Test
    public void numberTest() {
        List<Integer> numbers = List.of(3, 2, 2, 7, 5, 1, 9, 7);
        
        List<Integer> uniqueSorted = numbers.stream()
                .distinct()
                .sorted()
                .toList();
        
        System.out.println("Unique Sorted Numbers: " + uniqueSorted);
        Assert.assertEquals(uniqueSorted.getFirst(), Integer.valueOf(1), "Smallest number should be 1");
        Assert.assertEquals(uniqueSorted.size(), 6, "Should have 6 unique numbers");

        IntSummaryStatistics stats = numbers.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        System.out.println("Max: " + stats.getMax());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Average: " + stats.getAverage());

        Assert.assertEquals(stats.getMax(), 9);
        Assert.assertEquals(stats.getMin(), 1);
    }
}

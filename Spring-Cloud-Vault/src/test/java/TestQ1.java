import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestQ1 {

    public static void main(String[] args) {

        String s = "Today is my interview";

        Arrays.stream(s.split(""))
                .filter(x -> !x.isBlank())
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 1)
                .forEach(System.out::println);

//
    }
}

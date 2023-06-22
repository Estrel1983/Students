import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tests {
    public static void main(String[] args) {
        LinkedList <Integer> ll = new LinkedList<>();
        IntStream.of(12, 17, 22, 100,85).sorted().peek(x -> ll.add(x/2)).forEach(x -> ll.add(x/4));
        for (Integer i : ll){
            System.out.println(i);
        }
/*        IntStream.range(0, 10)
                .skip(5)
//                .limit(3)
//                .skip(1)
                .forEach(System.out::println);  */
    }

}

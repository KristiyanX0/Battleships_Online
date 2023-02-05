package operations;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class SetOperations {
    public static <T> Set<T> intersection(Collection<T> arg1, Collection<T> arg2) {
        return arg1.stream().distinct().filter(arg2::contains).collect(Collectors.toUnmodifiableSet());
    }
}

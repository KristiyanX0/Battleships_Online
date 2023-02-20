package operations;

import game.ship.position.Position;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SetOperation {
    public static <T> Set<T> intersection(Collection<T> arg1, Collection<T> arg2) {
        return arg1.stream().distinct().filter(arg2::contains).collect(Collectors.toUnmodifiableSet());
    }

    public static <T> Set<T> subtract(Collection<T> universalSet, Collection<T> subset) {
        Set<T> result = new HashSet<>(universalSet);
        result.removeAll(subset);
        return result;
    }

}

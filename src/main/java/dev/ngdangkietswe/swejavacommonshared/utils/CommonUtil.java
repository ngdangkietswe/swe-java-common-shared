package dev.ngdangkietswe.swejavacommonshared.utils;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

public class CommonUtil {

    public static <I, O> O asMono(I req, Function<I, O> mapper) {
        return Objects.nonNull(req) ? mapper.apply(req) : null;
    }

    public static <I, O> List<O> asList(Collection<I> reqs, Function<I, O> mapper) {
        return !CollectionUtils.isEmpty(reqs)
                ? reqs.stream().map(mapper).toList()
                : Collections.emptyList();
    }

    public static <I, K, V> Map<K, V> asMono(List<I> reqs, Function<I, K> classifier, Function<I, V> mapper) {
        return reqs.stream()
                .collect(Collectors.toMap(classifier, mapper));
    }

    public static <I, K, V> Map<K, List<V>> asList(List<I> reqs, Function<I, K> classifier, Function<I, V> mapper) {
        return reqs.stream()
                .collect(Collectors.groupingBy(classifier, Collectors.mapping(mapper, Collectors.toList())));
    }
}

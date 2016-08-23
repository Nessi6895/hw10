package ru.sbt.lambdausage;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by Артем on 22.08.2016.
 */
public class Streams<T> {
    private final List<T> list;

    private Streams(List<T> list) {
        this.list = list;
    }

    public static<R> Streams<R> of(List<R> list) {
        return new Streams(list);
    }

    public Streams<T> filter(Predicate<T> pr) {
        List<T> newList = listOfThatKind(this.list);
        for (T elem : list) {
            if (pr.test(elem)) {
                newList.add(elem);
            }
        }
        return new Streams<T>(newList);
    }

    public<R> Streams<R> transform(Function<T, R> func){
        List<R> newList = listOfThatKind(this.list);
        for(T elem : list){
            newList.add(func.apply(elem));
        }
        return new Streams<R>(newList);
    }

    public <K, V> Map<K,V> toMap(Function<T, K> keyFunc, Function<T, V> valueFunc){
        Map<K,V> map = new HashMap<K, V>();
        for(T elem : list){
            map.put(keyFunc.apply(elem), valueFunc.apply(elem));
        }
        return map;
    }
    public boolean equals(Streams<T> otherStream){
        return list.equals(otherStream.list);
    }

    private List listOfThatKind(List list){
        List newList;
        try {
            newList = this.list.getClass().newInstance();
        } catch (InstantiationException ignored) {
            newList = new ArrayList();
        } catch (IllegalAccessException ignored) {
            newList = new ArrayList();
        }
        return newList;
    }
}

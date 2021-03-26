package ru.waveaccess.conference.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<T, E>{
    T from(E e);

    E to(T e);

    default List<T> from(List<E> e){
        return e.stream().map(this::from).collect(Collectors.toList());
    }

    default List<E> to(List<T> e){
        return e.stream().map(this::to).collect(Collectors.toList());
    }
}

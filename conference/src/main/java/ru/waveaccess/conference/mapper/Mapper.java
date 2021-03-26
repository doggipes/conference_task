package ru.waveaccess.conference.mapper;

public interface Mapper<T, E>{
    T from(E e);

    E to(T e);
}

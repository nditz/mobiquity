package com.mobiquity.processors;

public interface IProcess<T, U> {

    U execute(T t);
}

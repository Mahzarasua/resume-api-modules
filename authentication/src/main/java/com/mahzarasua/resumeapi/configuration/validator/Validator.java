package com.mahzarasua.resumeapi.configuration.validator;

public interface Validator<E> {
    void validate(E object);
}

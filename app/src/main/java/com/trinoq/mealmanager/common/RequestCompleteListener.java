package com.trinoq.mealmanager.common;

public interface RequestCompleteListener<T> {
    void OnSuccessListener(T data);
    void OnFailedListener(String error);
}

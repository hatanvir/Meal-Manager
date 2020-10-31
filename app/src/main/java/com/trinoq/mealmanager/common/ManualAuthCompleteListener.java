package com.trinoq.mealmanager.common;

public interface ManualAuthCompleteListener {
    void OnSuccessListener(String code);
    void OnFailedListener(String error);
}

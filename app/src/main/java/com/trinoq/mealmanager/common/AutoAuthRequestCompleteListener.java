package com.trinoq.mealmanager.common;

public interface AutoAuthRequestCompleteListener {
    void OnCodeSentSuccessListener(String code);
    void OnCodeSentFailedListener(String error);
    void OnVerificationSuccessListener(String s);
    void OnVerificationFailedListener(String error);
}


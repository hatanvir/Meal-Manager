package com.trinoq.mealmanager.features.model.phoneAuth;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.trinoq.mealmanager.common.AutoAuthRequestCompleteListener;
import com.trinoq.mealmanager.common.ManualAuthCompleteListener;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

public class PhoneAuthModelImplementation implements PhoneAuthModel {
    FirebaseAuth auth = FirebaseAuth.getInstance();;
    Context context;
    String verificationId;
    AutoAuthRequestCompleteListener phoneAuthRequestCompleteListener;

    public PhoneAuthModelImplementation(Context context) {
        this.context = context;
    }

    private void verifyVerificationCode(String code){
        //creating credential
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,code);
        signInWithPhoneAuthCredential(phoneAuthCredential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithCredential:success");

                           // FirebaseUser user = task.getResult().getUser();
                            // ...
                            phoneAuthRequestCompleteListener.OnVerificationSuccessListener("Success");
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            phoneAuthRequestCompleteListener.OnVerificationFailedListener(task.getException().getMessage());
                           /* if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }*/
                        }
                    }
                });
    }

    @Override
    public void getAutoVerification(String phoneNum, Context context, AutoAuthRequestCompleteListener phoneAuthRequestCompleteListener) {
        this.context = context;
        this.phoneAuthRequestCompleteListener = phoneAuthRequestCompleteListener;

        PhoneAuthProvider.OnVerificationStateChangedCallbacks phoneAuthProviderCallBack
                = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();//getting sms code
                if (code != null){
                    phoneAuthRequestCompleteListener.OnCodeSentSuccessListener(code);
                    verifyVerificationCode(code);
                }else {
                    signInWithPhoneAuthCredential(phoneAuthCredential);
                }
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                phoneAuthRequestCompleteListener.OnVerificationFailedListener(e.getMessage());
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                verificationId = s;
                phoneAuthRequestCompleteListener.OnCodeSentSuccessListener("sent");
            }
        };
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNum,
                60,
                TimeUnit.SECONDS,
                (Activity) context,
                phoneAuthProviderCallBack
        );
    }

    @Override
    public void getManualVerification(String code, ManualAuthCompleteListener manualAuthCompleteListener) {
        verifyVerificationCode(code);
    }
}

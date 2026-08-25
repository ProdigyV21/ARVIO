package com.google.android.gms.auth.api.identity;

import android.content.Intent;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.internal.a;
import com.google.android.gms.common.api.r;
import com.google.android.gms.tasks.h;

/* JADX INFO: loaded from: classes4.dex */
public interface SignInClient extends r<zbv> {
    @Deprecated
    h beginSignIn(BeginSignInRequest beginSignInRequest);

    /* synthetic */ a getApiKey();

    String getPhoneNumberFromIntent(Intent intent) throws ApiException;

    h getPhoneNumberHintIntent(GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest);

    @Deprecated
    SignInCredential getSignInCredentialFromIntent(Intent intent) throws ApiException;

    @Deprecated
    h getSignInIntent(GetSignInIntentRequest getSignInIntentRequest);

    @Deprecated
    h signOut();
}

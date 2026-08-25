package com.google.android.gms.auth.api.identity;

import android.content.Intent;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.internal.a;
import com.google.android.gms.common.api.r;
import com.google.android.gms.tasks.h;

/* JADX INFO: loaded from: classes4.dex */
public interface AuthorizationClient extends r<zbc> {
    h authorize(AuthorizationRequest authorizationRequest);

    /* synthetic */ a getApiKey();

    AuthorizationResult getAuthorizationResultFromIntent(Intent intent) throws ApiException;
}

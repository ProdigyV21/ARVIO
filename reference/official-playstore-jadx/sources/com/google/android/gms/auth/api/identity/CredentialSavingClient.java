package com.google.android.gms.auth.api.identity;

import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.a;
import com.google.android.gms.common.api.r;
import com.google.android.gms.tasks.h;

/* JADX INFO: loaded from: classes4.dex */
public interface CredentialSavingClient extends r<zbi> {
    /* synthetic */ a getApiKey();

    Status getStatusFromIntent(Intent intent);

    h saveAccountLinkingToken(SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest);

    @Deprecated
    h savePassword(SavePasswordRequest savePasswordRequest);
}

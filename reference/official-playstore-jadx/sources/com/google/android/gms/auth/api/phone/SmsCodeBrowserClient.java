package com.google.android.gms.auth.api.phone;

import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.internal.a;
import com.google.android.gms.common.api.r;
import com.google.android.gms.tasks.h;

/* JADX INFO: loaded from: classes4.dex */
public interface SmsCodeBrowserClient extends r<e> {
    /* synthetic */ a getApiKey();

    h startSmsCodeRetriever();
}

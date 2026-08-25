package com.google.android.gms.auth.api.phone;

import com.google.android.gms.common.api.e;
import com.google.android.gms.common.api.internal.a;
import com.google.android.gms.common.api.r;
import com.google.android.gms.tasks.h;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes4.dex */
public interface SmsCodeAutofillClient extends r<e> {

    @Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionState {
        public static final int DENIED = 2;
        public static final int GRANTED = 1;
        public static final int NONE = 0;
    }

    h checkPermissionState();

    /* synthetic */ a getApiKey();

    h hasOngoingSmsRequest(String str);

    h startSmsCodeRetriever();
}

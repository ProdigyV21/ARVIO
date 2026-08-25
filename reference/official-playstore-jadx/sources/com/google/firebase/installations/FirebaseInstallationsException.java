package com.google.firebase.installations;

import com.google.android.gms.common.internal.t;
import com.google.firebase.FirebaseException;

/* JADX INFO: loaded from: classes4.dex */
public class FirebaseInstallationsException extends FirebaseException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirebaseInstallationsException(String str) {
        super(str);
        t.g(str, "Detail message must not be empty");
    }
}

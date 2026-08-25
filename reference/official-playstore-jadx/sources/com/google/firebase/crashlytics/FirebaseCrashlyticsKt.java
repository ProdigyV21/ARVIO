package com.google.firebase.crashlytics;

import kotlin.Metadata;
import r7.l;
import x6.t0;
import z4.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a*\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u0006\u0010\u0007\"\u0015\u0010\u000b\u001a\u00020\u0000*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/google/firebase/crashlytics/FirebaseCrashlytics;", "Lkotlin/Function1;", "Lcom/google/firebase/crashlytics/KeyValueBuilder;", "Lx6/t0;", "Lx6/n;", "init", "setCustomKeys", "(Lcom/google/firebase/crashlytics/FirebaseCrashlytics;Lr7/l;)V", "Lz4/b;", "getCrashlytics", "(Lz4/b;)Lcom/google/firebase/crashlytics/FirebaseCrashlytics;", "crashlytics", "com.google.firebase-firebase-crashlytics"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class FirebaseCrashlyticsKt {
    public static final FirebaseCrashlytics getCrashlytics(b bVar) {
        return FirebaseCrashlytics.getInstance();
    }

    public static final void setCustomKeys(FirebaseCrashlytics firebaseCrashlytics, l<? super KeyValueBuilder, t0> lVar) {
        lVar.invoke(new KeyValueBuilder(firebaseCrashlytics));
    }
}

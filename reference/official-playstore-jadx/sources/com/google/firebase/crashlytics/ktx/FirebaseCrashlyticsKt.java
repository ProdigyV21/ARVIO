package com.google.firebase.crashlytics.ktx;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import kotlin.Metadata;
import r7.l;
import s5.a;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\"\u0015\u0010\u000b\u001a\u00020\u0000*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/google/firebase/crashlytics/FirebaseCrashlytics;", "Lkotlin/Function1;", "Lcom/google/firebase/crashlytics/ktx/KeyValueBuilder;", "Lx6/t0;", "Lx6/n;", "init", "setCustomKeys", "(Lcom/google/firebase/crashlytics/FirebaseCrashlytics;Lr7/l;)V", "Ls5/a;", "getCrashlytics", "(Ls5/a;)Lcom/google/firebase/crashlytics/FirebaseCrashlytics;", "crashlytics", "com.google.firebase-firebase-crashlytics"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class FirebaseCrashlyticsKt {
    public static final FirebaseCrashlytics getCrashlytics(a aVar) {
        return FirebaseCrashlytics.getInstance();
    }

    @e
    public static final void setCustomKeys(FirebaseCrashlytics firebaseCrashlytics, l<? super KeyValueBuilder, t0> lVar) {
        lVar.invoke(new KeyValueBuilder(firebaseCrashlytics));
    }
}

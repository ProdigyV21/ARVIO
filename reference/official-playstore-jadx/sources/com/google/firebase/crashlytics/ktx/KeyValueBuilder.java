package com.google.firebase.crashlytics.ktx;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u0007\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0007\u0010\u000bJ\u001f\u0010\u0007\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0007\u0010\rJ\u001f\u0010\u0007\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0007\u0010\u000fJ\u001f\u0010\u0007\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0010H\u0007¢\u0006\u0004\b\u0007\u0010\u0011J\u001f\u0010\u0007\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0007\u0010\u0013J\u001f\u0010\u0007\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0007\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/google/firebase/crashlytics/ktx/KeyValueBuilder;", "", "Lcom/google/firebase/crashlytics/FirebaseCrashlytics;", "crashlytics", "<init>", "(Lcom/google/firebase/crashlytics/FirebaseCrashlytics;)V", "", "key", "", "value", "Lx6/t0;", "(Ljava/lang/String;Z)V", "", "(Ljava/lang/String;D)V", "", "(Ljava/lang/String;F)V", "", "(Ljava/lang/String;I)V", "", "(Ljava/lang/String;J)V", "(Ljava/lang/String;Ljava/lang/String;)V", "Lcom/google/firebase/crashlytics/FirebaseCrashlytics;", "com.google.firebase-firebase-crashlytics"}, k = 1, mv = {1, 7, 1}, xi = 48)
@e
public final class KeyValueBuilder {
    private final FirebaseCrashlytics crashlytics;

    public KeyValueBuilder(FirebaseCrashlytics firebaseCrashlytics) {
        this.crashlytics = firebaseCrashlytics;
    }

    @e
    public final void key(String key, boolean value) {
        this.crashlytics.setCustomKey(key, value);
    }

    @e
    public final void key(String key, double value) {
        this.crashlytics.setCustomKey(key, value);
    }

    @e
    public final void key(String key, float value) {
        this.crashlytics.setCustomKey(key, value);
    }

    @e
    public final void key(String key, int value) {
        this.crashlytics.setCustomKey(key, value);
    }

    @e
    public final void key(String key, long value) {
        this.crashlytics.setCustomKey(key, value);
    }

    @e
    public final void key(String key, String value) {
        this.crashlytics.setCustomKey(key, value);
    }
}

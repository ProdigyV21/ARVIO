package com.google.firebase.concurrent;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class p implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final p f14343i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Handler f14344l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ p[] f14345m;

    static {
        p pVar = new p("INSTANCE", 0);
        f14343i = pVar;
        f14345m = new p[]{pVar};
        f14344l = new Handler(Looper.getMainLooper());
    }

    public static p valueOf(String str) {
        return (p) Enum.valueOf(p.class, str);
    }

    public static p[] values() {
        return (p[]) f14345m.clone();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        f14344l.post(runnable);
    }
}

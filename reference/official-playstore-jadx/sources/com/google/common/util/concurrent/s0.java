package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class s0 implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final s0 f14233i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ s0[] f14234l;

    static {
        s0 s0Var = new s0("INSTANCE", 0);
        f14233i = s0Var;
        f14234l = new s0[]{s0Var};
    }

    public static s0 valueOf(String str) {
        return (s0) Enum.valueOf(s0.class, str);
    }

    public static s0[] values() {
        return (s0[]) f14234l.clone();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "MoreExecutors.directExecutor()";
    }
}

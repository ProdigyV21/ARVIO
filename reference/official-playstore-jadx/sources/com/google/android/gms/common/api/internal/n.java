package com.google.android.gms.common.api.internal;

import android.os.Looper;

/* JADX INFO: loaded from: classes4.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.c f12767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Object f12768b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile l f12769c;

    public n(Looper looper, Object obj, String str) {
        this.f12767a = new androidx.work.impl.utils.taskexecutor.c(looper);
        this.f12768b = obj;
        com.google.android.gms.common.internal.t.f(str);
        this.f12769c = new l(obj, str);
    }
}

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public abstract class v {
    public abstract void addStatusListener(u uVar);

    public abstract x await(long j10, TimeUnit timeUnit);

    public abstract void cancel();

    public abstract void setResultCallback(y yVar);
}

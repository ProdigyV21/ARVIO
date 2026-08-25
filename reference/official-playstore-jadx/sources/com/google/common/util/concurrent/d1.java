package com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes4.dex */
@t0
@x4.f
public interface d1<V> extends Future<V> {
    void addListener(Runnable runnable, Executor executor);
}

package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes4.dex */
@t0
@x4.f
public interface g1 extends ExecutorService {
    @Override // java.util.concurrent.ExecutorService
    d1 submit(Callable callable);
}

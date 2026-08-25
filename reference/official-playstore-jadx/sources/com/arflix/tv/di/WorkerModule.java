package com.arflix.tv.di;

import dagger.Binds;
import dagger.Module;
import i1.b;
import kotlin.Metadata;
import m2.i0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H'¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/arflix/tv/di/WorkerModule;", "", "<init>", "()V", "Li1/b;", "factory", "Lm2/i0;", "bindWorkerFactory", "(Li1/b;)Lm2/i0;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Module
public abstract class WorkerModule {
    public static final int $stable = 0;

    @Binds
    public abstract i0 bindWorkerFactory(b factory);
}

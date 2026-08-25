package com.google.common.util.concurrent;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes4.dex */
public abstract class u extends i {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final t7.a f14238m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c1 f14239n = new c1(u.class);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile Set f14240i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile int f14241l;

    static {
        Throwable th;
        t7.a tVar;
        try {
            tVar = new s(AtomicReferenceFieldUpdater.newUpdater(u.class, Set.class, CmcdData.OBJECT_TYPE_INIT_SEGMENT), AtomicIntegerFieldUpdater.newUpdater(u.class, CmcdData.STREAM_TYPE_LIVE));
            th = null;
        } catch (Throwable th2) {
            th = th2;
            tVar = new t();
        }
        f14238m = tVar;
        if (th != null) {
            f14239n.a().log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }
}

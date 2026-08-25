package com.google.firebase.crashlytics.internal.common;

import android.os.Looper;
import androidx.emoji2.text.q;
import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.i;
import com.google.android.gms.tasks.j;
import com.google.android.gms.tasks.s;
import com.google.android.gms.tasks.t;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes4.dex */
public final class Utils {
    private static final ExecutorService TASK_CONTINUATION_EXECUTOR_SERVICE = ExecutorUtils.buildSingleThreadExecutorService("awaitEvenIfOnMainThread task continuation executor");
    private static final int TIMEOUT_SEC = 4;

    private Utils() {
    }

    public static <T> T awaitEvenIfOnMainThread(h hVar) throws InterruptedException, TimeoutException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        hVar.d(TASK_CONTINUATION_EXECUTOR_SERVICE, new a3.b(countDownLatch, 21));
        if (Looper.getMainLooper() == Looper.myLooper()) {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } else {
            countDownLatch.await(4L, TimeUnit.SECONDS);
        }
        if (hVar.h()) {
            return (T) hVar.f();
        }
        if (((t) hVar).f13883d) {
            throw new CancellationException("Task is already canceled");
        }
        if (hVar.g()) {
            throw new IllegalStateException(hVar.e());
        }
        throw new TimeoutException();
    }

    public static boolean awaitUninterruptibly(CountDownLatch countDownLatch, long j10, TimeUnit timeUnit) {
        boolean z = false;
        try {
            long nanos = timeUnit.toNanos(j10);
            while (true) {
                try {
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                    nanos = (System.nanoTime() + nanos) - System.nanoTime();
                }
            }
            return countDownLatch.await(nanos, TimeUnit.NANOSECONDS);
        } finally {
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static <T> h callTask(Executor executor, Callable<h> callable) {
        i iVar = new i();
        executor.execute(new q(callable, executor, iVar, 11));
        return iVar.f13855a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$awaitEvenIfOnMainThread$4(CountDownLatch countDownLatch, h hVar) throws Exception {
        countDownLatch.countDown();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Object lambda$callTask$2(i iVar, h hVar) throws Exception {
        if (hVar.h()) {
            iVar.b(hVar.f());
            return null;
        }
        if (hVar.e() == null) {
            return null;
        }
        iVar.a(hVar.e());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$callTask$3(Callable callable, Executor executor, i iVar) {
        try {
            ((h) callable.call()).d(executor, new d(1, iVar));
        } catch (Exception e5) {
            iVar.a(e5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void lambda$race$0(i iVar, h hVar) throws Exception {
        if (hVar.h()) {
            iVar.d(hVar.f());
            return null;
        }
        if (hVar.e() == null) {
            return null;
        }
        iVar.c(hVar.e());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Void lambda$race$1(i iVar, h hVar) throws Exception {
        if (hVar.h()) {
            iVar.d(hVar.f());
            return null;
        }
        if (hVar.e() == null) {
            return null;
        }
        iVar.c(hVar.e());
        return null;
    }

    public static <T> h race(h hVar, h hVar2) {
        i iVar = new i();
        d dVar = new d(0, iVar);
        t tVar = (t) hVar;
        tVar.getClass();
        s sVar = j.f13856a;
        tVar.d(sVar, dVar);
        t tVar2 = (t) hVar2;
        tVar2.getClass();
        tVar2.d(sVar, dVar);
        return iVar.f13855a;
    }

    public static <T> h race(Executor executor, h hVar, h hVar2) {
        i iVar = new i();
        d dVar = new d(2, iVar);
        hVar.d(executor, dVar);
        hVar2.d(executor, dVar);
        return iVar.f13855a;
    }
}

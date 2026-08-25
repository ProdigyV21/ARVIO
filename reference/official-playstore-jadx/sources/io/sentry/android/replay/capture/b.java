package io.sentry.android.replay.capture;

import androidx.leanback.widget.u;
import io.sentry.w5;
import io.sentry.y6;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements u7.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16884a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicReference f16885b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ c f16886c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ c f16887d;

    public b(c cVar, c cVar2, int i10) {
        this.f16884a = i10;
        switch (i10) {
            case 2:
                this.f16886c = cVar;
                this.f16887d = cVar2;
                this.f16885b = new AtomicReference(null);
                break;
            case 3:
                this.f16886c = cVar;
                this.f16887d = cVar2;
                this.f16885b = new AtomicReference(null);
                break;
            case 4:
                this.f16886c = cVar;
                this.f16887d = cVar2;
                this.f16885b = new AtomicReference(null);
                break;
            case 5:
                this.f16886c = cVar;
                this.f16887d = cVar2;
                this.f16885b = new AtomicReference(null);
                break;
            default:
                this.f16886c = cVar;
                this.f16887d = cVar2;
                this.f16885b = new AtomicReference(-1);
                break;
        }
    }

    @Override // u7.c
    public final Object getValue(Object obj, kotlin.reflect.m mVar) {
        switch (this.f16884a) {
        }
        return this.f16885b.get();
    }

    @Override // u7.d
    public final void setValue(Object obj, kotlin.reflect.m mVar, Object obj2) {
        switch (this.f16884a) {
            case 0:
                Object andSet = this.f16885b.getAndSet(obj2);
                if (!p.a(andSet, obj2)) {
                    a aVar = new a(andSet, obj2, this.f16887d, 0);
                    c cVar = this.f16886c;
                    y6 y6Var = cVar.f16889a;
                    if (y6Var.getThreadChecker().a()) {
                        ((ScheduledExecutorService) cVar.f16893e.getValue()).submit(new io.sentry.android.replay.util.g("CaptureStrategy.runInBackground", new u(aVar, 5)));
                    } else {
                        try {
                            aVar.invoke();
                        } catch (Throwable th) {
                            y6Var.getLogger().l(w5.ERROR, "Failed to execute task CaptureStrategy.runInBackground", th);
                            return;
                        }
                    }
                }
                break;
            case 1:
                Object andSet2 = this.f16885b.getAndSet(obj2);
                if (!p.a(andSet2, obj2)) {
                    a aVar2 = new a(andSet2, obj2, this.f16887d, 1);
                    c cVar2 = this.f16886c;
                    y6 y6Var2 = cVar2.f16889a;
                    if (y6Var2.getThreadChecker().a()) {
                        ((ScheduledExecutorService) cVar2.f16893e.getValue()).submit(new io.sentry.android.replay.util.g("CaptureStrategy.runInBackground", new u(aVar2, 6)));
                    } else {
                        try {
                            aVar2.invoke();
                        } catch (Throwable th2) {
                            y6Var2.getLogger().l(w5.ERROR, "Failed to execute task CaptureStrategy.runInBackground", th2);
                            return;
                        }
                    }
                }
                break;
            case 2:
                Object andSet3 = this.f16885b.getAndSet(obj2);
                if (!p.a(andSet3, obj2)) {
                    a aVar3 = new a(andSet3, obj2, this.f16887d, 2);
                    c cVar3 = this.f16886c;
                    y6 y6Var3 = cVar3.f16889a;
                    if (y6Var3.getThreadChecker().a()) {
                        ((ScheduledExecutorService) cVar3.f16893e.getValue()).submit(new io.sentry.android.replay.util.g("CaptureStrategy.runInBackground", new u(aVar3, 7)));
                    } else {
                        try {
                            aVar3.invoke();
                        } catch (Throwable th3) {
                            y6Var3.getLogger().l(w5.ERROR, "Failed to execute task CaptureStrategy.runInBackground", th3);
                            return;
                        }
                    }
                }
                break;
            case 3:
                Object andSet4 = this.f16885b.getAndSet(obj2);
                if (!p.a(andSet4, obj2)) {
                    a aVar4 = new a(andSet4, obj2, this.f16887d, 3);
                    c cVar4 = this.f16886c;
                    y6 y6Var4 = cVar4.f16889a;
                    if (y6Var4.getThreadChecker().a()) {
                        ((ScheduledExecutorService) cVar4.f16893e.getValue()).submit(new io.sentry.android.replay.util.g("CaptureStrategy.runInBackground", new u(aVar4, 8)));
                    } else {
                        try {
                            aVar4.invoke();
                        } catch (Throwable th4) {
                            y6Var4.getLogger().l(w5.ERROR, "Failed to execute task CaptureStrategy.runInBackground", th4);
                            return;
                        }
                    }
                }
                break;
            case 4:
                Object andSet5 = this.f16885b.getAndSet(obj2);
                if (!p.a(andSet5, obj2)) {
                    a aVar5 = new a(andSet5, obj2, this.f16887d, 4);
                    c cVar5 = this.f16886c;
                    y6 y6Var5 = cVar5.f16889a;
                    if (y6Var5.getThreadChecker().a()) {
                        ((ScheduledExecutorService) cVar5.f16893e.getValue()).submit(new io.sentry.android.replay.util.g("CaptureStrategy.runInBackground", new u(aVar5, 9)));
                    } else {
                        try {
                            aVar5.invoke();
                        } catch (Throwable th5) {
                            y6Var5.getLogger().l(w5.ERROR, "Failed to execute task CaptureStrategy.runInBackground", th5);
                            return;
                        }
                    }
                }
                break;
            default:
                Object andSet6 = this.f16885b.getAndSet(obj2);
                if (!p.a(andSet6, obj2)) {
                    a aVar6 = new a(andSet6, obj2, this.f16887d, 5);
                    c cVar6 = this.f16886c;
                    y6 y6Var6 = cVar6.f16889a;
                    if (y6Var6.getThreadChecker().a()) {
                        ((ScheduledExecutorService) cVar6.f16893e.getValue()).submit(new io.sentry.android.replay.util.g("CaptureStrategy.runInBackground", new u(aVar6, 10)));
                    } else {
                        try {
                            aVar6.invoke();
                        } catch (Throwable th6) {
                            y6Var6.getLogger().l(w5.ERROR, "Failed to execute task CaptureStrategy.runInBackground", th6);
                        }
                    }
                }
                break;
        }
    }

    public b(Object obj, c cVar, c cVar2) {
        this.f16884a = 0;
        this.f16886c = cVar;
        this.f16887d = cVar2;
        this.f16885b = new AtomicReference(obj);
    }
}

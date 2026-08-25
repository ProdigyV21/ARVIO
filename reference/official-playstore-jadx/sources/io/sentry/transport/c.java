package io.sentry.transport;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import io.sentry.ILogger;
import io.sentry.a5;
import io.sentry.b5;
import io.sentry.h5;
import io.sentry.j0;
import io.sentry.l0;
import io.sentry.w5;
import io.sentry.y6;
import io.sentry.y7;
import io.sentry.z4;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final n f17908i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.cache.d f17909l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final y6 f17910m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final p f17911n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final h f17912o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final e f17913p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public volatile b f17914q;

    /* JADX WARN: Type inference failed for: r3v0, types: [io.sentry.transport.a] */
    public c(y6 y6Var, p pVar, h hVar, fi.iki.elonen.f fVar) {
        int maxQueueSize = y6Var.getMaxQueueSize();
        final io.sentry.cache.d envelopeDiskCache = y6Var.getEnvelopeDiskCache();
        final ILogger logger = y6Var.getLogger();
        a5 dateProvider = y6Var.getDateProvider();
        n nVar = new n(maxQueueSize, new l0(4), new RejectedExecutionHandler() { // from class: io.sentry.transport.a
            @Override // java.util.concurrent.RejectedExecutionHandler
            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                if (runnable instanceof b) {
                    b bVar = (b) runnable;
                    j0 j0Var = bVar.f17904l;
                    if (!qb.l.t(j0Var, io.sentry.hints.e.class)) {
                        envelopeDiskCache.R(bVar.f17903i, j0Var);
                    }
                    Object objB = j0Var.b("sentry:typeCheckHint");
                    if (io.sentry.hints.o.class.isInstance(j0Var.b("sentry:typeCheckHint")) && objB != null) {
                        ((io.sentry.hints.o) objB).c(false);
                    }
                    Object objB2 = j0Var.b("sentry:typeCheckHint");
                    if (io.sentry.hints.l.class.isInstance(j0Var.b("sentry:typeCheckHint")) && objB2 != null) {
                        ((io.sentry.hints.l) objB2).d(true);
                    }
                    logger.q(w5.WARNING, "Envelope rejected", new Object[0]);
                }
            }
        }, logger, dateProvider);
        e eVar = new e(y6Var, fVar, pVar);
        this.f17914q = null;
        this.f17908i = nVar;
        io.sentry.cache.d envelopeDiskCache2 = y6Var.getEnvelopeDiskCache();
        ac.b.V(envelopeDiskCache2, "envelopeCache is required");
        this.f17909l = envelopeDiskCache2;
        this.f17910m = y6Var;
        this.f17911n = pVar;
        ac.b.V(hVar, "transportGate is required");
        this.f17912o = hVar;
        this.f17913p = eVar;
    }

    @Override // io.sentry.transport.g
    public final void N(fi.iki.elonen.f fVar) {
        k0(fVar, new j0());
    }

    @Override // io.sentry.transport.g
    public final void a(boolean z) {
        this.f17911n.close();
        this.f17908i.shutdown();
        this.f17910m.getLogger().q(w5.DEBUG, "Shutting down", new Object[0]);
        if (z) {
            return;
        }
        try {
            long flushTimeoutMillis = this.f17910m.getFlushTimeoutMillis();
            if (this.f17908i.awaitTermination(flushTimeoutMillis, TimeUnit.MILLISECONDS)) {
                return;
            }
            this.f17910m.getLogger().q(w5.WARNING, "Failed to shutdown the async connection async sender  within " + flushTimeoutMillis + " ms. Trying to force it now.", new Object[0]);
            this.f17908i.shutdownNow();
            if (this.f17914q != null) {
                this.f17908i.getRejectedExecutionHandler().rejectedExecution(this.f17914q, this.f17908i);
            }
        } catch (InterruptedException unused) {
            this.f17910m.getLogger().q(w5.DEBUG, "Thread interrupted while closing the connection.", new Object[0]);
            Thread.currentThread().interrupt();
        }
    }

    @Override // io.sentry.transport.g
    public final void b(long j10) {
        n nVar = this.f17908i;
        nVar.getClass();
        try {
            a8.e eVar = nVar.f17930o;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            eVar.getClass();
            ((q) eVar.f183l).tryAcquireSharedNanos(1, timeUnit.toNanos(j10));
        } catch (InterruptedException e5) {
            nVar.f17928m.l(w5.ERROR, "Failed to wait till idle", e5);
            Thread.currentThread().interrupt();
        }
    }

    @Override // io.sentry.transport.g
    public final boolean c() {
        boolean z;
        p pVar = this.f17911n;
        pVar.getClass();
        pVar.f17931i.getClass();
        Date date = new Date(System.currentTimeMillis());
        ConcurrentHashMap concurrentHashMap = pVar.f17933m;
        Iterator it = concurrentHashMap.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            Date date2 = (Date) concurrentHashMap.get((io.sentry.m) it.next());
            if (date2 != null && !date.after(date2)) {
                z = true;
                break;
            }
        }
        n nVar = this.f17908i;
        z4 z4Var = nVar.f17927l;
        return (z || (z4Var != null && (nVar.f17929n.a().d(z4Var) > 2000000000L ? 1 : (nVar.f17929n.a().d(z4Var) == 2000000000L ? 0 : -1)) < 0)) ? false : true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        a(false);
    }

    @Override // io.sentry.transport.g
    public final p f() {
        return this.f17911n;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // io.sentry.transport.g
    public final void k0(fi.iki.elonen.f fVar, j0 j0Var) {
        io.sentry.cache.d dVar;
        boolean z;
        fi.iki.elonen.f fVarZ;
        char c10;
        byte b10;
        List listSingletonList;
        Iterable<h5> iterable = (Iterable) fVar.f15417l;
        boolean zT = qb.l.t(j0Var, io.sentry.hints.e.class);
        y6 y6Var = this.f17910m;
        io.sentry.cache.d dVar2 = this.f17909l;
        char c11 = 0;
        if (zT) {
            y6Var.getLogger().q(w5.DEBUG, "Captured Envelope is already cached", new Object[0]);
            dVar = i.f17921i;
            z = true;
        } else {
            dVar = dVar2;
            z = false;
        }
        p pVar = this.f17911n;
        y6 y6Var2 = pVar.f17932l;
        ArrayList arrayList = null;
        for (h5 h5Var : iterable) {
            String itemType = h5Var.f17320a.f17356o.getItemType();
            itemType.getClass();
            char c12 = c11;
            byte b11 = -1;
            switch (itemType.hashCode()) {
                case -1963501277:
                    c10 = 1;
                    if (itemType.equals("attachment")) {
                        b11 = c12;
                    }
                    break;
                case -1639516637:
                    c10 = 1;
                    if (itemType.equals("replay_video")) {
                        b11 = 1;
                    }
                    break;
                case -729715625:
                    c10 = 1;
                    if (itemType.equals("profile_chunk")) {
                        b11 = 2;
                    }
                    break;
                case -309425751:
                    c10 = 1;
                    if (itemType.equals("profile")) {
                        b10 = 3;
                        b11 = b10;
                    }
                    break;
                case -191501435:
                    c10 = 1;
                    if (itemType.equals("feedback")) {
                        b10 = 4;
                        b11 = b10;
                    }
                    break;
                case 107332:
                    c10 = 1;
                    if (itemType.equals("log")) {
                        b10 = 5;
                        b11 = b10;
                    }
                    break;
                case 3536714:
                    c10 = 1;
                    if (itemType.equals(TtmlNode.TAG_SPAN)) {
                        b10 = 6;
                        b11 = b10;
                    }
                    break;
                case 96891546:
                    c10 = 1;
                    if (itemType.equals("event")) {
                        b10 = 7;
                        b11 = b10;
                    }
                    break;
                case 229505514:
                    c10 = 1;
                    if (itemType.equals("trace_metric")) {
                        b10 = 8;
                        b11 = b10;
                    }
                    break;
                case 1536888764:
                    c10 = 1;
                    if (itemType.equals("check_in")) {
                        b10 = 9;
                        b11 = b10;
                    }
                    break;
                case 1984987798:
                    c10 = 1;
                    if (itemType.equals(SettingsSessionManager.SETTINGS_KEY)) {
                        b10 = 10;
                        b11 = b10;
                    }
                    break;
                case 2141246174:
                    c10 = 1;
                    if (itemType.equals("transaction")) {
                        b10 = 11;
                        b11 = b10;
                    }
                    break;
                default:
                    c10 = 1;
                    break;
            }
            switch (b11) {
                case 0:
                    listSingletonList = Collections.singletonList(io.sentry.m.Attachment);
                    break;
                case 1:
                    listSingletonList = Collections.singletonList(io.sentry.m.Replay);
                    break;
                case 2:
                    io.sentry.m[] mVarArr = new io.sentry.m[2];
                    mVarArr[c12] = io.sentry.m.ProfileChunkUi;
                    mVarArr[c10] = io.sentry.m.ProfileChunk;
                    listSingletonList = Arrays.asList(mVarArr);
                    break;
                case 3:
                    listSingletonList = Collections.singletonList(io.sentry.m.Profile);
                    break;
                case 4:
                    listSingletonList = Collections.singletonList(io.sentry.m.Feedback);
                    break;
                case 5:
                    listSingletonList = Collections.singletonList(io.sentry.m.LogItem);
                    break;
                case 6:
                    listSingletonList = Collections.singletonList(io.sentry.m.Span);
                    break;
                case 7:
                    listSingletonList = Collections.singletonList(io.sentry.m.Error);
                    break;
                case 8:
                    listSingletonList = Collections.singletonList(io.sentry.m.TraceMetric);
                    break;
                case 9:
                    listSingletonList = Collections.singletonList(io.sentry.m.Monitor);
                    break;
                case 10:
                    listSingletonList = Collections.singletonList(io.sentry.m.Session);
                    break;
                case 11:
                    listSingletonList = Collections.singletonList(io.sentry.m.Transaction);
                    break;
                default:
                    listSingletonList = Collections.singletonList(io.sentry.m.Unknown);
                    break;
            }
            Iterator it = listSingletonList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (pVar.j((io.sentry.m) it.next())) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(h5Var);
                    y6Var2.getClientReportRecorder().s(io.sentry.clientreport.d.RATELIMIT_BACKOFF, h5Var);
                }
            }
            c11 = c12;
        }
        int i10 = c11;
        if (arrayList != null) {
            ILogger logger = y6Var2.getLogger();
            w5 w5Var = w5.WARNING;
            Object[] objArr = new Object[1];
            objArr[i10] = Integer.valueOf(arrayList.size());
            logger.q(w5Var, "%d envelope items will be dropped due rate limiting.", objArr);
            ArrayList arrayList2 = new ArrayList();
            for (h5 h5Var2 : iterable) {
                if (!arrayList.contains(h5Var2)) {
                    arrayList2.add(h5Var2);
                }
            }
            if (arrayList2.isEmpty()) {
                y6Var2.getLogger().q(w5.WARNING, "Envelope discarded due all items rate limited.", new Object[i10]);
                Object objB = j0Var.b("sentry:typeCheckHint");
                if (io.sentry.hints.o.class.isInstance(j0Var.b("sentry:typeCheckHint")) && objB != null) {
                    ((io.sentry.hints.o) objB).c(i10);
                }
                Object objB2 = j0Var.b("sentry:typeCheckHint");
                if (io.sentry.hints.l.class.isInstance(j0Var.b("sentry:typeCheckHint")) && objB2 != null) {
                    ((io.sentry.hints.l) objB2).d(false);
                }
                Object objB3 = j0Var.b("sentry:typeCheckHint");
                if (io.sentry.hints.f.class.isInstance(j0Var.b("sentry:typeCheckHint")) && objB3 != null) {
                    ((io.sentry.hints.f) objB3).e();
                    y6Var2.getLogger().q(w5.DEBUG, "Disk flush envelope fired due to rate limit", new Object[0]);
                }
                fVarZ = null;
            } else {
                fVarZ = new fi.iki.elonen.f((b5) fVar.f15416i, (List) arrayList2);
            }
        } else {
            fVarZ = fVar;
        }
        if (fVarZ == null) {
            if (z) {
                dVar2.q0(fVar);
                return;
            }
            return;
        }
        if (y7.class.isInstance(j0Var.b("sentry:typeCheckHint"))) {
            fVarZ = y6Var.getClientReportRecorder().z(fVarZ);
        }
        Future futureSubmit = this.f17908i.submit(new b(this, fVarZ, j0Var, dVar));
        if (futureSubmit != null && futureSubmit.isCancelled()) {
            y6Var.getClientReportRecorder().v(io.sentry.clientreport.d.QUEUE_OVERFLOW, fVarZ);
            return;
        }
        Object objB4 = j0Var.b("sentry:typeCheckHint");
        if (!io.sentry.hints.g.class.isInstance(j0Var.b("sentry:typeCheckHint")) || objB4 == null) {
            return;
        }
        ((io.sentry.hints.g) objB4).b();
        y6Var.getLogger().q(w5.DEBUG, "Envelope enqueued", new Object[0]);
    }
}

package com.google.android.gms.common.api.internal;

import io.sentry.ILogger;
import io.sentry.a7;
import io.sentry.b5;
import io.sentry.c5;
import io.sentry.d5;
import io.sentry.d6;
import io.sentry.e5;
import io.sentry.exception.SentryEnvelopeException;
import io.sentry.h5;
import io.sentry.i3;
import io.sentry.i5;
import io.sentry.i6;
import io.sentry.j5;
import io.sentry.j6;
import io.sentry.j7;
import io.sentry.k6;
import io.sentry.l6;
import io.sentry.l7;
import io.sentry.m1;
import io.sentry.o1;
import io.sentry.q3;
import io.sentry.r2;
import io.sentry.s7;
import io.sentry.t3;
import io.sentry.u5;
import io.sentry.u7;
import io.sentry.v4;
import io.sentry.v5;
import io.sentry.w5;
import io.sentry.x4;
import io.sentry.y3;
import io.sentry.y6;
import io.sentry.z5;
import j$.util.Objects;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.StreamResetException;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes4.dex */
public final class g0 implements com.google.android.gms.common.internal.d, u0, io.sentry.f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f12733a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f12734b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f12735c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f12736d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f12737e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f12738f;

    public g0(h hVar, com.google.android.gms.common.api.g gVar, a aVar) {
        Objects.requireNonNull(hVar);
        this.f12738f = hVar;
        this.f12736d = null;
        this.f12737e = null;
        this.f12733a = false;
        this.f12734b = gVar;
        this.f12735c = aVar;
    }

    public static ArrayList s(io.sentry.j0 j0Var) {
        ArrayList arrayList = new ArrayList(j0Var.f17391b);
        io.sentry.a aVar = j0Var.f17393d;
        if (aVar != null) {
            arrayList.add(aVar);
        }
        io.sentry.a aVar2 = j0Var.f17394e;
        if (aVar2 != null) {
            arrayList.add(aVar2);
        }
        io.sentry.a aVar3 = j0Var.f17395f;
        if (aVar3 != null) {
            arrayList.add(aVar3);
        }
        return arrayList;
    }

    public boolean A(v4 v4Var, io.sentry.j0 j0Var) {
        if (qb.l.B(j0Var)) {
            return true;
        }
        ((y6) this.f12734b).getLogger().q(w5.DEBUG, "Event was cached so not applying scope: %s", v4Var.f17984i);
        return false;
    }

    public void B(IOException iOException) {
        this.f12733a = true;
        ((lb.e) this.f12736d).b(iOException);
        lb.k kVarE = ((mb.d) this.f12737e).e();
        lb.i iVar = (lb.i) this.f12734b;
        synchronized (kVarE) {
            try {
                if (!(iOException instanceof StreamResetException)) {
                    if (!(kVarE.f20130g != null) || (iOException instanceof ConnectionShutdownException)) {
                        kVarE.f20133j = true;
                        if (kVarE.f20135m == 0) {
                            lb.k.d(iVar.f20110i, kVarE.f20125b, iOException);
                            kVarE.f20134l++;
                        }
                    }
                } else if (((StreamResetException) iOException).f20989i == 8) {
                    int i10 = kVarE.f20136n + 1;
                    kVarE.f20136n = i10;
                    if (i10 > 1) {
                        kVarE.f20133j = true;
                        kVarE.f20134l++;
                    }
                } else if (((StreamResetException) iOException).f20989i != 9 || !iVar.z) {
                    kVarE.f20133j = true;
                    kVarE.f20134l++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void C(o3.b bVar) {
        e0 e0Var = (e0) ((h) this.f12738f).f12749t.get((a) this.f12735c);
        if (e0Var != null) {
            e0Var.n(bVar);
        }
    }

    @Override // io.sentry.f1
    public void a(boolean z) {
        long shutdownTimeoutMillis;
        y6 y6Var = (y6) this.f12734b;
        y6Var.getLogger().q(w5.INFO, "Closing SentryClient.", new Object[0]);
        if (z) {
            shutdownTimeoutMillis = 0;
        } else {
            try {
                shutdownTimeoutMillis = y6Var.getShutdownTimeoutMillis();
            } catch (IOException e5) {
                y6Var.getLogger().l(w5.WARNING, "Failed to close the connection to the Sentry Server.", e5);
            }
        }
        b(shutdownTimeoutMillis);
        ((io.sentry.logger.b) this.f12737e).a(z);
        ((io.sentry.metrics.b) this.f12738f).a(z);
        ((io.sentry.transport.g) this.f12735c).a(z);
        for (io.sentry.d0 d0Var : y6Var.getEventProcessors()) {
            if (d0Var instanceof Closeable) {
                try {
                    ((Closeable) d0Var).close();
                } catch (IOException e6) {
                    y6Var.getLogger().q(w5.WARNING, "Failed to close the event processor {}.", d0Var, e6);
                }
            }
        }
        this.f12733a = false;
    }

    @Override // io.sentry.f1
    public void b(long j10) {
        ((io.sentry.logger.b) this.f12737e).b(j10);
        ((io.sentry.metrics.b) this.f12738f).b(j10);
        ((io.sentry.transport.g) this.f12735c).b(j10);
    }

    @Override // io.sentry.f1
    public boolean c() {
        return ((io.sentry.transport.g) this.f12735c).c();
    }

    @Override // io.sentry.f1
    public io.sentry.protocol.v d(a7 a7Var, io.sentry.a1 a1Var, io.sentry.j0 j0Var) {
        y6 y6Var = (y6) this.f12734b;
        if (A(a7Var, j0Var)) {
            io.sentry.protocol.p pVar = a7Var.f17987n;
            io.sentry.protocol.c cVar = a7Var.f17985l;
            if (pVar == null) {
                a7Var.f17987n = a1Var.getRequest();
            }
            if (a7Var.f17992s == null) {
                a7Var.f17992s = a1Var.A();
            }
            if (a7Var.f17988o == null) {
                a7Var.c(new HashMap(a1Var.u()));
            } else {
                for (Map.Entry entry : a1Var.u().entrySet()) {
                    if (!a7Var.f17988o.containsKey(entry.getKey())) {
                        a7Var.f17988o.put((String) entry.getKey(), (String) entry.getValue());
                    }
                }
            }
            for (Map.Entry entry2 : new io.sentry.protocol.c(a1Var.w()).f17550i.entrySet()) {
                if (!cVar.a(entry2.getKey())) {
                    cVar.k((String) entry2.getKey(), entry2.getValue());
                }
            }
            io.sentry.k1 k1VarR = a1Var.r();
            if (cVar.i() == null) {
                if (k1VarR == null) {
                    cVar.v(u7.b(a1Var.F()));
                } else {
                    cVar.v(k1VarR.o());
                }
            }
        }
        y6Var.getLogger().q(w5.DEBUG, "Capturing session replay: %s", a7Var.f17984i);
        io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
        io.sentry.protocol.v vVar2 = a7Var.f17984i;
        if (vVar2 != null) {
            vVar = vVar2;
        }
        Iterator<io.sentry.d0> it = y6Var.getEventProcessors().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            io.sentry.d0 next = it.next();
            try {
                a7Var = next.i(a7Var, j0Var);
            } catch (Throwable th) {
                y6Var.getLogger().k(w5.ERROR, th, "An exception occurred while processing replay event by processor: %s", next.getClass().getName());
            }
            if (a7Var == null) {
                y6Var.getLogger().q(w5.DEBUG, "Replay event was dropped by a processor: %s", next.getClass().getName());
                y6Var.getClientReportRecorder().c(io.sentry.clientreport.d.EVENT_PROCESSOR, io.sentry.m.Replay);
                break;
            }
        }
        if (a7Var != null) {
            k6 beforeSendReplay = y6Var.getBeforeSendReplay();
            if (beforeSendReplay != null) {
                try {
                    a7Var = beforeSendReplay.execute();
                } catch (Throwable th2) {
                    y6Var.getLogger().l(w5.ERROR, "The BeforeSendReplay callback threw an exception. It will be added as breadcrumb and continue.", th2);
                    a7Var = null;
                }
            }
            if (a7Var == null) {
                y6Var.getLogger().q(w5.DEBUG, "Event was dropped by beforeSendReplay", new Object[0]);
                y6Var.getClientReportRecorder().c(io.sentry.clientreport.d.BEFORE_SEND, io.sentry.m.Replay);
            }
        }
        if (a7Var == null) {
            return io.sentry.protocol.v.f17707l;
        }
        try {
            fi.iki.elonen.f fVarR = r(a7Var, j0Var.f17396g, t(a1Var, j0Var, a7Var, null), io.sentry.hints.c.class.isInstance(j0Var.b("sentry:typeCheckHint")));
            j0Var.a();
            ((io.sentry.transport.g) this.f12735c).k0(fVarR, j0Var);
            return vVar;
        } catch (IOException e5) {
            y6Var.getLogger().k(w5.WARNING, e5, "Capturing event %s failed.", vVar);
            return io.sentry.protocol.v.f17707l;
        }
    }

    @Override // io.sentry.f1
    public io.sentry.protocol.v e(io.sentry.protocol.e0 e0Var, s7 s7Var, io.sentry.a1 a1Var, io.sentry.j0 j0Var, t3 t3Var) {
        Pattern pattern;
        y6 y6Var = (y6) this.f12734b;
        io.sentry.j0 j0Var2 = j0Var == null ? new io.sentry.j0() : j0Var;
        if (A(e0Var, j0Var2)) {
            j0Var2.f17391b.addAll(a1Var.J());
        }
        y6Var.getLogger().q(w5.DEBUG, "Capturing transaction: %s", e0Var.f17984i);
        List<io.sentry.g0> ignoredTransactions = y6Var.getIgnoredTransactions();
        String str = e0Var.z;
        if (str != null && ignoredTransactions != null && !ignoredTransactions.isEmpty()) {
            Iterator<io.sentry.g0> it = ignoredTransactions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    Iterator<io.sentry.g0> it2 = ignoredTransactions.iterator();
                    while (it2.hasNext()) {
                        try {
                            pattern = it2.next().f17293b;
                        } catch (Throwable unused) {
                        }
                        if (pattern == null ? false : pattern.matcher(str).matches()) {
                        }
                    }
                } else if (it.next().f17292a.equalsIgnoreCase(str)) {
                    break;
                }
            }
            y6Var.getLogger().q(w5.DEBUG, "Transaction was dropped as transaction name %s is ignored", e0Var.z);
            io.sentry.clientreport.f clientReportRecorder = y6Var.getClientReportRecorder();
            io.sentry.clientreport.d dVar = io.sentry.clientreport.d.EVENT_PROCESSOR;
            clientReportRecorder.c(dVar, io.sentry.m.Transaction);
            y6Var.getClientReportRecorder().o(dVar, io.sentry.m.Span, e0Var.C.size() + 1);
            return io.sentry.protocol.v.f17707l;
        }
        io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
        io.sentry.protocol.v vVar2 = e0Var.f17984i;
        if (vVar2 == null) {
            vVar2 = vVar;
        }
        if (A(e0Var, j0Var2)) {
            m(e0Var, a1Var);
            e0Var = x(e0Var, j0Var2, a1Var.P());
            if (e0Var == null) {
                y6Var.getLogger().q(w5.DEBUG, "Transaction was dropped by applyScope", new Object[0]);
            }
        }
        if (e0Var != null) {
            e0Var = x(e0Var, j0Var2, y6Var.getEventProcessors());
        }
        if (e0Var == null) {
            y6Var.getLogger().q(w5.DEBUG, "Transaction was dropped by Event processors.", new Object[0]);
            return vVar;
        }
        int size = e0Var.C.size();
        l6 beforeSendTransaction = y6Var.getBeforeSendTransaction();
        if (beforeSendTransaction != null) {
            try {
                e0Var = beforeSendTransaction.execute();
            } catch (Throwable th) {
                y6Var.getLogger().l(w5.ERROR, "The BeforeSendTransaction callback threw an exception. It will be added as breadcrumb and continue.", th);
                e0Var = null;
            }
        }
        io.sentry.protocol.e0 e0Var2 = e0Var;
        int size2 = e0Var2 == null ? 0 : e0Var2.C.size();
        if (e0Var2 == null) {
            y6Var.getLogger().q(w5.DEBUG, "Transaction was dropped by beforeSendTransaction.", new Object[0]);
            io.sentry.clientreport.f clientReportRecorder2 = y6Var.getClientReportRecorder();
            io.sentry.clientreport.d dVar2 = io.sentry.clientreport.d.BEFORE_SEND;
            clientReportRecorder2.c(dVar2, io.sentry.m.Transaction);
            y6Var.getClientReportRecorder().o(dVar2, io.sentry.m.Span, size + 1);
            return io.sentry.protocol.v.f17707l;
        }
        if (size2 < size) {
            int i10 = size - size2;
            y6Var.getLogger().q(w5.DEBUG, "%d spans were dropped by beforeSendTransaction.", Integer.valueOf(i10));
            y6Var.getClientReportRecorder().o(io.sentry.clientreport.d.BEFORE_SEND, io.sentry.m.Span, i10);
        }
        try {
            ArrayList arrayListS = s(j0Var2);
            ArrayList arrayList = new ArrayList();
            Iterator it3 = arrayListS.iterator();
            while (it3.hasNext()) {
                ((io.sentry.a) it3.next()).getClass();
            }
            fi.iki.elonen.f fVarO = o(e0Var2, arrayList, null, s7Var, t3Var);
            j0Var2.a();
            return fVarO != null ? z(fVarO, j0Var2) : vVar2;
        } catch (SentryEnvelopeException | IOException e5) {
            y6Var.getLogger().k(w5.WARNING, e5, "Capturing transaction %s failed.", vVar2);
            return io.sentry.protocol.v.f17707l;
        }
    }

    @Override // io.sentry.f1
    public io.sentry.transport.p f() {
        return ((io.sentry.transport.g) this.f12735c).f();
    }

    @Override // io.sentry.f1
    public io.sentry.protocol.v g(io.sentry.protocol.i iVar, io.sentry.a1 a1Var) {
        y6 y6Var = (y6) this.f12734b;
        j5 j5Var = new j5();
        io.sentry.protocol.c cVar = j5Var.f17985l;
        cVar.k("feedback", iVar);
        io.sentry.j0 j0Var = new io.sentry.j0();
        if (iVar.f17595p == null) {
            iVar.f17595p = a1Var.x();
        }
        y6Var.getLogger().q(w5.DEBUG, "Capturing feedback: %s", j5Var.f17984i);
        if (A(j5Var, j0Var)) {
            if (j5Var.f17992s == null) {
                j5Var.f17992s = a1Var.A();
            }
            if (j5Var.f17988o == null) {
                j5Var.c(new HashMap(a1Var.u()));
            } else {
                for (Map.Entry entry : a1Var.u().entrySet()) {
                    if (!j5Var.f17988o.containsKey(entry.getKey())) {
                        j5Var.f17988o.put((String) entry.getKey(), (String) entry.getValue());
                    }
                }
            }
            for (Map.Entry entry2 : new io.sentry.protocol.c(a1Var.w()).f17550i.entrySet()) {
                if (!cVar.a(entry2.getKey())) {
                    cVar.k((String) entry2.getKey(), entry2.getValue());
                }
            }
            io.sentry.k1 k1VarR = a1Var.r();
            if (cVar.i() == null) {
                if (k1VarR == null) {
                    cVar.v(u7.b(a1Var.F()));
                } else {
                    cVar.v(k1VarR.o());
                }
            }
            j5Var = w(j5Var, j0Var, a1Var.P());
            if (j5Var == null) {
                y6Var.getLogger().q(w5.DEBUG, "Feedback was dropped by applyScope", new Object[0]);
                return io.sentry.protocol.v.f17707l;
            }
        }
        j5 j5VarW = w(j5Var, j0Var, y6Var.getEventProcessors());
        if (j5VarW != null) {
            j6 beforeSendFeedback = y6Var.getBeforeSendFeedback();
            if (beforeSendFeedback != null) {
                try {
                    j5VarW = beforeSendFeedback.a(j5VarW, j0Var);
                } catch (Throwable th) {
                    y6Var.getLogger().l(w5.ERROR, "The BeforeSendFeedback callback threw an exception.", th);
                    j5VarW = null;
                }
            }
            if (j5VarW == null) {
                y6Var.getLogger().q(w5.DEBUG, "Event was dropped by beforeSend", new Object[0]);
                y6Var.getClientReportRecorder().c(io.sentry.clientreport.d.BEFORE_SEND, io.sentry.m.Feedback);
            }
        }
        if (j5VarW == null) {
            return io.sentry.protocol.v.f17707l;
        }
        io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
        io.sentry.protocol.v vVar2 = j5VarW.f17984i;
        io.sentry.protocol.v vVar3 = vVar2 != null ? vVar2 : vVar;
        if (iVar.f17594o == null) {
            y6Var.getReplayController().x(Boolean.FALSE);
            io.sentry.protocol.v vVarO = a1Var.o();
            if (!vVarO.equals(vVar)) {
                iVar.f17594o = vVarO;
            }
        }
        try {
            fi.iki.elonen.f fVarO = o(j5VarW, s(j0Var), null, t(a1Var, j0Var, j5VarW, j5VarW.F), null);
            j0Var.a();
            return fVarO != null ? z(fVarO, j0Var) : vVar3;
        } catch (SentryEnvelopeException | IOException e5) {
            y6Var.getLogger().k(w5.WARNING, e5, "Capturing feedback %s failed.", vVar3);
            return io.sentry.protocol.v.f17707l;
        }
    }

    @Override // io.sentry.f1
    public void h(j7 j7Var, io.sentry.j0 j0Var) {
        y6 y6Var = (y6) this.f12734b;
        ac.b.V(j7Var, "Session is required.");
        String str = j7Var.f17414w;
        if (str == null || str.isEmpty()) {
            y6Var.getLogger().q(w5.WARNING, "Sessions can't be captured without setting a release.", new Object[0]);
            return;
        }
        try {
            io.sentry.i1 serializer = y6Var.getSerializer();
            io.sentry.protocol.t sdkVersion = y6Var.getSdkVersion();
            ac.b.V(serializer, "Serializer is required.");
            i(new fi.iki.elonen.f((io.sentry.protocol.v) null, sdkVersion, h5.d(serializer, j7Var)), j0Var);
        } catch (IOException e5) {
            y6Var.getLogger().l(w5.ERROR, "Failed to capture session.", e5);
        }
    }

    @Override // io.sentry.f1
    public io.sentry.protocol.v i(fi.iki.elonen.f fVar, io.sentry.j0 j0Var) {
        try {
            j0Var.a();
            return z(fVar, j0Var);
        } catch (IOException e5) {
            ((y6) this.f12734b).getLogger().l(w5.ERROR, "Failed to capture envelope.", e5);
            return io.sentry.protocol.v.f17707l;
        }
    }

    @Override // io.sentry.f1
    public boolean isEnabled() {
        return this.f12733a;
    }

    @Override // com.google.android.gms.common.internal.d
    public void j(o3.b bVar) {
        ((h) this.f12738f).x.post(new com.google.common.util.concurrent.w0(11, this, false, bVar));
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00dd, code lost:
    
        r9.getLogger().q(io.sentry.w5.DEBUG, "Event was dropped as it matched a string/pattern in ignoredErrors", r0.A);
        r9.getClientReportRecorder().c(io.sentry.clientreport.d.EVENT_PROCESSOR, io.sentry.m.Error);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00fb, code lost:
    
        return io.sentry.protocol.v.f17707l;
     */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0223 A[Catch: all -> 0x01fc, TryCatch #1 {all -> 0x01fc, blocks: (B:85:0x01b2, B:88:0x01ba, B:91:0x01c2, B:101:0x01ff, B:103:0x0203, B:105:0x0209, B:106:0x021c, B:109:0x0223, B:111:0x022c, B:97:0x01f0, B:93:0x01e3), top: B:219:0x01b2, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x034f  */
    @Override // io.sentry.f1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public io.sentry.protocol.v k(io.sentry.j5 r16, io.sentry.a1 r17, io.sentry.j0 r18) {
        /*
            Method dump skipped, instruction units count: 979
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.g0.k(io.sentry.j5, io.sentry.a1, io.sentry.j0):io.sentry.protocol.v");
    }

    @Override // io.sentry.f1
    public io.sentry.protocol.v l(q3 q3Var) {
        ac.b.V(q3Var, "profileChunk is required.");
        y6 y6Var = (y6) this.f12734b;
        y6Var.getLogger().q(w5.DEBUG, "Capturing profile chunk: %s", q3Var.f17750m);
        io.sentry.protocol.v vVar = q3Var.f17750m;
        io.sentry.protocol.d dVarA = io.sentry.protocol.d.a(q3Var.f17748i, y6Var);
        if (dVarA != null) {
            q3Var.f17748i = dVarA;
        }
        try {
            return z(new fi.iki.elonen.f(new b5(vVar, y6Var.getSdkVersion(), null), Collections.singletonList(h5.c(q3Var, y6Var.getSerializer(), y6Var.getProfilerConverter()))), null);
        } catch (SentryEnvelopeException | IOException e5) {
            y6Var.getLogger().k(w5.WARNING, e5, "Capturing profile chunk %s failed.", vVar);
            return io.sentry.protocol.v.f17707l;
        }
    }

    public void m(v4 v4Var, io.sentry.a1 a1Var) {
        if (a1Var != null) {
            if (v4Var.f17987n == null) {
                v4Var.f17987n = a1Var.getRequest();
            }
            if (v4Var.f17992s == null) {
                v4Var.f17992s = a1Var.A();
            }
            if (v4Var.f17988o == null) {
                v4Var.c(new HashMap(a1Var.u()));
            } else {
                for (Map.Entry entry : a1Var.u().entrySet()) {
                    if (!v4Var.f17988o.containsKey(entry.getKey())) {
                        v4Var.f17988o.put((String) entry.getKey(), (String) entry.getValue());
                    }
                }
            }
            if (v4Var.f17996w == null) {
                v4Var.f17996w = new ArrayList(new ArrayList(a1Var.s()));
            } else {
                Queue queueS = a1Var.s();
                List list = v4Var.f17996w;
                if (list != null && !queueS.isEmpty()) {
                    list.addAll(queueS);
                    Collections.sort(list, (x4) this.f12736d);
                }
            }
            if (v4Var.f17997y == null) {
                v4Var.f17997y = new HashMap(new HashMap(a1Var.getExtras()));
            } else {
                for (Map.Entry entry2 : a1Var.getExtras().entrySet()) {
                    if (!v4Var.f17997y.containsKey(entry2.getKey())) {
                        v4Var.f17997y.put((String) entry2.getKey(), entry2.getValue());
                    }
                }
            }
            io.sentry.protocol.c cVar = v4Var.f17985l;
            for (Map.Entry entry3 : new io.sentry.protocol.c(a1Var.w()).f17550i.entrySet()) {
                if (!cVar.a(entry3.getKey())) {
                    cVar.k((String) entry3.getKey(), entry3.getValue());
                }
            }
        }
    }

    public IOException n(boolean z, boolean z5, IOException iOException) {
        gb.u uVar = (gb.u) this.f12735c;
        if (iOException != null) {
            B(iOException);
        }
        if (z5) {
            if (iOException != null) {
                uVar.getClass();
            } else {
                uVar.getClass();
            }
        }
        if (z) {
            if (iOException != null) {
                uVar.getClass();
            } else {
                uVar.getClass();
            }
        }
        return ((lb.i) this.f12734b).g(this, z5, z, iOException);
    }

    public fi.iki.elonen.f o(v4 v4Var, ArrayList arrayList, j7 j7Var, s7 s7Var, t3 t3Var) {
        io.sentry.protocol.v vVar;
        y6 y6Var = (y6) this.f12734b;
        ArrayList arrayList2 = new ArrayList();
        boolean z = false;
        if (v4Var != null) {
            io.sentry.i1 serializer = y6Var.getSerializer();
            Charset charset = h5.f17319d;
            ac.b.V(serializer, "ISerializer is required.");
            fi.iki.elonen.f fVar = new fi.iki.elonen.f(new c5(serializer, v4Var, 1), z);
            arrayList2.add(new h5(new i5(v5.resolve(v4Var), new d5(fVar, 6), "application/json", null, null), new d5(fVar, 8)));
            vVar = v4Var.f17984i;
        } else {
            vVar = null;
        }
        if (j7Var != null) {
            arrayList2.add(h5.d(y6Var.getSerializer(), j7Var));
        }
        if (t3Var != null) {
            long maxTraceFileSize = y6Var.getMaxTraceFileSize();
            io.sentry.i1 serializer2 = y6Var.getSerializer();
            Charset charset2 = h5.f17319d;
            File file = t3Var.f17872i;
            fi.iki.elonen.f fVar2 = new fi.iki.elonen.f(new e5(file, maxTraceFileSize, t3Var, serializer2), z);
            arrayList2.add(new h5(new i5(v5.Profile, new d5(fVar2, 4), "application-json", file.getName(), null), new d5(fVar2, 5)));
            if (vVar == null) {
                vVar = new io.sentry.protocol.v(t3Var.G);
            }
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                io.sentry.a aVar = (io.sentry.a) it.next();
                io.sentry.i1 serializer3 = y6Var.getSerializer();
                ILogger logger = y6Var.getLogger();
                long maxAttachmentSize = y6Var.getMaxAttachmentSize();
                Charset charset3 = h5.f17319d;
                fi.iki.elonen.f fVar3 = new fi.iki.elonen.f(new e5(aVar, maxAttachmentSize, serializer3, logger), z);
                arrayList2.add(new h5(new i5(v5.Attachment, new d5(fVar3, 2), aVar.f16292e, aVar.f16291d, aVar.f16293f), new d5(fVar3, 3)));
            }
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        return new fi.iki.elonen.f(new b5(vVar, y6Var.getSdkVersion(), s7Var), (List) arrayList2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public fi.iki.elonen.f p(z5 z5Var) {
        ArrayList arrayList = new ArrayList();
        y6 y6Var = (y6) this.f12734b;
        io.sentry.i1 serializer = y6Var.getSerializer();
        Charset charset = h5.f17319d;
        ac.b.V(serializer, "ISerializer is required.");
        fi.iki.elonen.f fVar = new fi.iki.elonen.f((Object) new c5(serializer, z5Var, 4), false);
        arrayList.add(new h5(new i5(v5.Log, new d5(fVar, 0 == true ? 1 : 0), "application/vnd.sentry.items.log+json", (String) null, (String) null, (String) null, Integer.valueOf(z5Var.f18079i.size())), new d5(fVar, 1)));
        return new fi.iki.elonen.f(new b5(null, y6Var.getSdkVersion(), null), (List) arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public fi.iki.elonen.f q(d6 d6Var) {
        ArrayList arrayList = new ArrayList();
        y6 y6Var = (y6) this.f12734b;
        io.sentry.i1 serializer = y6Var.getSerializer();
        Charset charset = h5.f17319d;
        ac.b.V(serializer, "ISerializer is required.");
        fi.iki.elonen.f fVar = new fi.iki.elonen.f((Object) new c5(serializer, d6Var, 0), (boolean) (0 == true ? 1 : 0));
        arrayList.add(new h5(new i5(v5.TraceMetric, new d5(fVar, 7), "application/vnd.sentry.items.trace-metric+json", (String) null, (String) null, (String) null, Integer.valueOf(d6Var.f17214i.size())), new d5(fVar, 13)));
        return new fi.iki.elonen.f(new b5(null, y6Var.getSdkVersion(), null), (List) arrayList);
    }

    public fi.iki.elonen.f r(final a7 a7Var, final y3 y3Var, s7 s7Var, final boolean z) {
        ArrayList arrayList = new ArrayList();
        y6 y6Var = (y6) this.f12734b;
        final io.sentry.i1 serializer = y6Var.getSerializer();
        final ILogger logger = y6Var.getLogger();
        Charset charset = h5.f17319d;
        final File file = a7Var.z;
        fi.iki.elonen.f fVar = new fi.iki.elonen.f((Object) new Callable() { // from class: io.sentry.f5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                i1 i1Var = serializer;
                a7 a7Var2 = a7Var;
                File file2 = file;
                ILogger iLogger = logger;
                boolean z5 = z;
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, h5.f17319d));
                        try {
                            LinkedHashMap linkedHashMap = new LinkedHashMap();
                            i1Var.a(bufferedWriter, a7Var2);
                            linkedHashMap.put(v5.ReplayEvent.getItemType(), byteArrayOutputStream.toByteArray());
                            byteArrayOutputStream.reset();
                            y3 y3Var2 = y3Var;
                            if (y3Var2 != null) {
                                i1Var.a(bufferedWriter, y3Var2);
                                linkedHashMap.put(v5.ReplayRecording.getItemType(), byteArrayOutputStream.toByteArray());
                                byteArrayOutputStream.reset();
                            }
                            if (file2 != null && file2.exists()) {
                                byte[] bArrK = qb.d.K(10485760L, file2.getPath());
                                if (bArrK.length > 0) {
                                    linkedHashMap.put(v5.ReplayVideo.getItemType(), bArrK);
                                }
                            }
                            byte[] bArrJ = h5.j(linkedHashMap);
                            bufferedWriter.close();
                            byteArrayOutputStream.close();
                            if (file2 != null) {
                                if (z5) {
                                    return bArrJ;
                                }
                            }
                            return bArrJ;
                        } finally {
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        iLogger.l(w5.ERROR, "Could not serialize replay recording", th);
                        if (file2 == null) {
                            return null;
                        }
                        if (z5) {
                            qb.d.y(file2.getParentFile());
                            return null;
                        }
                        file2.delete();
                        return null;
                    } finally {
                        if (file2 != null) {
                            if (z5) {
                                qb.d.y(file2.getParentFile());
                            } else {
                                file2.delete();
                            }
                        }
                    }
                }
            }
        }, false);
        arrayList.add(new h5(new i5(v5.ReplayVideo, new d5(fVar, 11), null, null, null), new d5(fVar, 12)));
        return new fi.iki.elonen.f(new b5(a7Var.f17984i, y6Var.getSessionReplay().f17225v, s7Var), (List) arrayList);
    }

    public s7 t(io.sentry.a1 a1Var, io.sentry.j0 j0Var, v4 v4Var, String str) {
        y6 y6Var = (y6) this.f12734b;
        if (io.sentry.hints.c.class.isInstance(j0Var.b("sentry:typeCheckHint"))) {
            if (v4Var != null) {
                io.sentry.d dVar = new io.sentry.d(y6Var.getLogger());
                io.sentry.protocol.c cVar = v4Var.f17985l;
                l7 l7VarI = cVar.i();
                dVar.b("sentry-trace_id", l7VarI != null ? l7VarI.f17435i.toString() : null);
                dVar.b("sentry-public_key", y6Var.retrieveParsedDsn().f16296b);
                dVar.b("sentry-release", v4Var.f17989p);
                dVar.b("sentry-environment", v4Var.f17990q);
                dVar.b("sentry-org_id", y6Var.getEffectiveOrgId());
                dVar.b("sentry-transaction", str);
                if (dVar.f17208e) {
                    dVar.f17206c = null;
                }
                dVar.b("sentry-sampled", null);
                if (dVar.f17208e) {
                    dVar.f17207d = null;
                }
                Object objC = cVar.c("replay_id");
                if (objC != null && !objC.toString().equals(io.sentry.protocol.v.f17707l.toString())) {
                    dVar.b("sentry-replay_id", objC.toString());
                    cVar.f17550i.remove("replay_id");
                }
                dVar.f17208e = false;
                return dVar.d();
            }
        } else if (a1Var != null) {
            m1 m1VarJ = a1Var.j();
            return m1VarJ != null ? m1VarJ.h() : ((io.sentry.d) a1Var.M(new androidx.media3.exoplayer.analytics.b(a1Var, y6Var, 20)).f1061n).d();
        }
        return null;
    }

    public gb.r0 u(gb.p0 p0Var) throws IOException {
        mb.d dVar = (mb.d) this.f12737e;
        try {
            String strA = p0Var.f15785p.a(HttpConnection.CONTENT_TYPE);
            if (strA == null) {
                strA = null;
            }
            long jD = dVar.d(p0Var);
            return new gb.r0(strA, jD, new xb.i0(new lb.d(this, dVar.a(p0Var), jD)), 1);
        } catch (IOException e5) {
            ((gb.u) this.f12735c).getClass();
            B(e5);
            throw e5;
        }
    }

    public j5 v(j5 j5Var, io.sentry.j0 j0Var, List list) {
        y6 y6Var = (y6) this.f12734b;
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            io.sentry.d0 d0Var = (io.sentry.d0) it.next();
            try {
                boolean z = d0Var instanceof io.sentry.b;
                boolean zIsInstance = io.sentry.hints.c.class.isInstance(j0Var.b("sentry:typeCheckHint"));
                if (zIsInstance && z) {
                    j5Var = d0Var.j(j5Var, j0Var);
                } else if (!zIsInstance && !z) {
                    j5Var = d0Var.j(j5Var, j0Var);
                }
            } catch (Throwable th) {
                y6Var.getLogger().k(w5.ERROR, th, "An exception occurred while processing event by processor: %s", d0Var.getClass().getName());
            }
            if (j5Var == null) {
                y6Var.getLogger().q(w5.DEBUG, "Event was dropped by a processor: %s", d0Var.getClass().getName());
                y6Var.getClientReportRecorder().c(io.sentry.clientreport.d.EVENT_PROCESSOR, io.sentry.m.Error);
                break;
            }
        }
        return j5Var;
    }

    public j5 w(j5 j5Var, io.sentry.j0 j0Var, List list) {
        y6 y6Var = (y6) this.f12734b;
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            io.sentry.d0 d0Var = (io.sentry.d0) it.next();
            try {
                j5Var = d0Var.j(j5Var, j0Var);
            } catch (Throwable th) {
                y6Var.getLogger().k(w5.ERROR, th, "An exception occurred while processing feedback event by processor: %s", d0Var.getClass().getName());
            }
            if (j5Var == null) {
                y6Var.getLogger().q(w5.DEBUG, "Feedback event was dropped by a processor: %s", d0Var.getClass().getName());
                y6Var.getClientReportRecorder().c(io.sentry.clientreport.d.EVENT_PROCESSOR, io.sentry.m.Feedback);
                break;
            }
        }
        return j5Var;
    }

    public io.sentry.protocol.e0 x(io.sentry.protocol.e0 e0Var, io.sentry.j0 j0Var, List list) {
        y6 y6Var = (y6) this.f12734b;
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            io.sentry.d0 d0Var = (io.sentry.d0) it.next();
            int size = e0Var.C.size();
            try {
                e0Var = d0Var.k(e0Var, j0Var);
            } catch (Throwable th) {
                y6Var.getLogger().k(w5.ERROR, th, "An exception occurred while processing transaction by processor: %s", d0Var.getClass().getName());
            }
            int size2 = e0Var == null ? 0 : e0Var.C.size();
            if (e0Var == null) {
                y6Var.getLogger().q(w5.DEBUG, "Transaction was dropped by a processor: %s", d0Var.getClass().getName());
                io.sentry.clientreport.f clientReportRecorder = y6Var.getClientReportRecorder();
                io.sentry.clientreport.d dVar = io.sentry.clientreport.d.EVENT_PROCESSOR;
                clientReportRecorder.c(dVar, io.sentry.m.Transaction);
                y6Var.getClientReportRecorder().o(dVar, io.sentry.m.Span, size + 1);
                break;
            }
            if (size2 < size) {
                int i10 = size - size2;
                y6Var.getLogger().q(w5.DEBUG, "%d spans were dropped by a processor: %s", Integer.valueOf(i10), d0Var.getClass().getName());
                y6Var.getClientReportRecorder().o(io.sentry.clientreport.d.EVENT_PROCESSOR, io.sentry.m.Span, i10);
            }
        }
        return e0Var;
    }

    public gb.o0 y(boolean z) throws IOException {
        try {
            gb.o0 o0VarF = ((mb.d) this.f12737e).f(z);
            if (o0VarF == null) {
                return o0VarF;
            }
            o0VarF.f15773m = this;
            return o0VarF;
        } catch (IOException e5) {
            ((gb.u) this.f12735c).getClass();
            B(e5);
            throw e5;
        }
    }

    public io.sentry.protocol.v z(fi.iki.elonen.f fVar, io.sentry.j0 j0Var) {
        io.sentry.transport.g gVar = (io.sentry.transport.g) this.f12735c;
        y6 y6Var = (y6) this.f12734b;
        i6 beforeEnvelopeCallback = y6Var.getBeforeEnvelopeCallback();
        if (beforeEnvelopeCallback != null) {
            try {
                beforeEnvelopeCallback.execute();
            } catch (Throwable th) {
                y6Var.getLogger().l(w5.ERROR, "The BeforeEnvelope callback threw an exception.", th);
            }
        }
        u5.d().c(y6Var.getLogger());
        if (j0Var == null) {
            gVar.N(fVar);
        } else {
            gVar.k0(fVar, j0Var);
        }
        io.sentry.protocol.v vVar = ((b5) fVar.f15416i).f17100i;
        return vVar != null ? vVar : io.sentry.protocol.v.f17707l;
    }

    public g0(lb.i iVar, gb.u uVar, lb.e eVar, mb.d dVar) {
        this.f12734b = iVar;
        this.f12735c = uVar;
        this.f12736d = eVar;
        this.f12737e = dVar;
        this.f12738f = dVar.e();
    }

    public g0(y6 y6Var) {
        this.f12736d = new x4();
        this.f12734b = y6Var;
        this.f12733a = true;
        o1 transportFactory = y6Var.getTransportFactory();
        if (transportFactory instanceof i3) {
            transportFactory = new r2();
            y6Var.setTransportFactory(transportFactory);
        }
        io.sentry.a0 a0VarRetrieveParsedDsn = y6Var.retrieveParsedDsn();
        String sentryClientName = y6Var.getSentryClientName();
        URI uri = a0VarRetrieveParsedDsn.f16297c;
        String string = uri.resolve(uri.getPath() + "/envelope/").toString();
        String str = a0VarRetrieveParsedDsn.f16296b;
        String str2 = a0VarRetrieveParsedDsn.f16295a;
        StringBuilder sb2 = new StringBuilder("Sentry sentry_version=7,sentry_client=");
        sb2.append(sentryClientName);
        sb2.append(",sentry_key=");
        sb2.append(str);
        sb2.append((str2 == null || str2.length() <= 0) ? "" : ",sentry_secret=".concat(str2));
        String string2 = sb2.toString();
        HashMap map = new HashMap();
        map.put("User-Agent", sentryClientName);
        map.put("X-Sentry-Auth", string2);
        fi.iki.elonen.f fVar = new fi.iki.elonen.f();
        ac.b.V(string, "url is required");
        try {
            fVar.f15416i = URI.create(string).toURL();
            fVar.f15417l = map;
            this.f12735c = transportFactory.a(y6Var, fVar);
            if (y6Var.getLogs().f17516a) {
                this.f12737e = y6Var.getLogs().f17517b.c(y6Var, this);
            } else {
                this.f12737e = io.sentry.logger.e.f17457i;
            }
            if (y6Var.getMetrics().f17786a) {
                this.f12738f = y6Var.getMetrics().f17787b.mo7017c(y6Var, this);
            } else {
                this.f12738f = io.sentry.metrics.d.f17478i;
            }
        } catch (MalformedURLException e5) {
            throw new IllegalArgumentException("Failed to compose the Sentry's server URL.", e5);
        }
    }
}

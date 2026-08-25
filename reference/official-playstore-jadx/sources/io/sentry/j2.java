package io.sentry;

import io.sentry.protocol.DebugImage;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class j2 implements i1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f17397c = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y6 f17398a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f17399b;

    public j2(y6 y6Var) {
        this.f17398a = y6Var;
        HashMap map = new HashMap();
        this.f17399b = map;
        map.put(io.sentry.protocol.a.class, new io.sentry.clientreport.a(4));
        map.put(f.class, new e(0));
        map.put(io.sentry.protocol.b.class, new io.sentry.clientreport.a(5));
        map.put(io.sentry.protocol.c.class, new io.sentry.clientreport.a(6));
        map.put(DebugImage.class, new io.sentry.clientreport.a(7));
        map.put(io.sentry.protocol.d.class, new io.sentry.clientreport.a(8));
        map.put(io.sentry.protocol.f.class, new io.sentry.clientreport.a(9));
        map.put(io.sentry.protocol.e.class, new io.sentry.clientreport.a(10));
        map.put(io.sentry.protocol.i.class, new io.sentry.clientreport.a(12));
        map.put(io.sentry.protocol.k.class, new io.sentry.clientreport.a(14));
        map.put(io.sentry.protocol.a0.class, new io.sentry.clientreport.a(29));
        map.put(io.sentry.protocol.l.class, new io.sentry.clientreport.a(15));
        map.put(io.sentry.protocol.m.class, new io.sentry.clientreport.a(16));
        map.put(io.sentry.protocol.n.class, new io.sentry.clientreport.a(17));
        map.put(io.sentry.protocol.o.class, new io.sentry.clientreport.a(18));
        map.put(q3.class, new e(1));
        map.put(r3.class, new e(2));
        map.put(t3.class, new e(3));
        map.put(u3.class, new e(4));
        map.put(io.sentry.profilemeasurements.a.class, new io.sentry.clientreport.a(2));
        map.put(io.sentry.profilemeasurements.b.class, new io.sentry.clientreport.a(3));
        map.put(io.sentry.protocol.p.class, new io.sentry.clientreport.a(19));
        map.put(y3.class, new e(5));
        map.put(io.sentry.rrweb.a.class, new io.sentry.protocol.c0(9));
        map.put(io.sentry.rrweb.c.class, new io.sentry.protocol.c0(10));
        map.put(io.sentry.rrweb.g.class, new io.sentry.protocol.c0(12));
        map.put(io.sentry.rrweb.i.class, new io.sentry.protocol.c0(14));
        map.put(io.sentry.rrweb.j.class, new io.sentry.protocol.c0(16));
        map.put(io.sentry.rrweb.l.class, new io.sentry.protocol.c0(17));
        map.put(io.sentry.rrweb.m.class, new io.sentry.protocol.c0(18));
        map.put(io.sentry.protocol.s.class, new io.sentry.clientreport.a(20));
        map.put(io.sentry.protocol.t.class, new io.sentry.clientreport.a(21));
        map.put(b5.class, new e(7));
        map.put(i5.class, new e(8));
        map.put(j5.class, new e(9));
        map.put(io.sentry.protocol.u.class, new io.sentry.clientreport.a(22));
        map.put(v5.class, new e(10));
        map.put(w5.class, new e(11));
        map.put(x5.class, new e(12));
        map.put(z5.class, new e(15));
        map.put(d6.class, new e(18));
        map.put(io.sentry.protocol.w.class, new io.sentry.clientreport.a(24));
        map.put(io.sentry.protocol.x.class, new io.sentry.clientreport.a(25));
        map.put(a7.class, new e(19));
        map.put(io.sentry.protocol.y.class, new io.sentry.clientreport.a(26));
        map.put(io.sentry.protocol.z.class, new io.sentry.clientreport.a(27));
        map.put(io.sentry.protocol.b0.class, new io.sentry.clientreport.a(28));
        map.put(t4.class, new e(6));
        map.put(io.sentry.protocol.d0.class, new io.sentry.protocol.c0(0));
        map.put(io.sentry.protocol.e0.class, new io.sentry.protocol.c0(1));
        map.put(j7.class, new e(21));
        map.put(l7.class, new e(22));
        map.put(o7.class, new e(23));
        map.put(q7.class, new e(24));
        map.put(io.sentry.protocol.i0.class, new io.sentry.protocol.c0(2));
        map.put(io.sentry.protocol.j.class, new io.sentry.clientreport.a(13));
        map.put(z7.class, new e(26));
        map.put(io.sentry.clientreport.b.class, new io.sentry.clientreport.a(0));
        map.put(io.sentry.protocol.k0.class, new io.sentry.protocol.c0(4));
        map.put(io.sentry.protocol.j0.class, new io.sentry.protocol.c0(3));
    }

    @Override // io.sentry.i1
    public final void a(Writer writer, Object obj) throws IOException {
        ac.b.V(obj, "The entity is required.");
        y6 y6Var = this.f17398a;
        ILogger logger = y6Var.getLogger();
        w5 w5Var = w5.DEBUG;
        if (logger.t(w5Var)) {
            y6Var.getLogger().q(w5Var, "Serializing object: %s", f(obj, y6Var.isEnablePrettySerializationOutput()));
        }
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(writer, y6Var.getMaxDepth());
        ((f2) fVar.f15417l).f(fVar, y6Var.getLogger(), obj);
        writer.flush();
    }

    @Override // io.sentry.i1
    public final String b(ConcurrentHashMap concurrentHashMap) {
        return f(concurrentHashMap, false);
    }

    @Override // io.sentry.i1
    public final fi.iki.elonen.f c(BufferedInputStream bufferedInputStream) {
        y6 y6Var = this.f17398a;
        try {
            return y6Var.getEnvelopeReader().a(bufferedInputStream);
        } catch (IOException e5) {
            y6Var.getLogger().l(w5.ERROR, "Error deserializing envelope.", e5);
            return null;
        }
    }

    @Override // io.sentry.i1
    public final void d(fi.iki.elonen.f fVar, OutputStream outputStream) throws IOException {
        y6 y6Var = this.f17398a;
        ac.b.V(fVar, "The SentryEnvelope object is required.");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(outputStream), f17397c));
        try {
            ((b5) fVar.f15416i).serialize(new fi.iki.elonen.f(bufferedWriter, y6Var.getMaxDepth()), y6Var.getLogger());
            bufferedWriter.write("\n");
            for (h5 h5Var : (Iterable) fVar.f15417l) {
                try {
                    byte[] bArrF = h5Var.f();
                    h5Var.f17320a.serialize(new fi.iki.elonen.f(bufferedWriter, y6Var.getMaxDepth()), y6Var.getLogger());
                    bufferedWriter.write("\n");
                    bufferedWriter.flush();
                    outputStream.write(bArrF);
                    bufferedWriter.write("\n");
                } catch (Exception e5) {
                    y6Var.getLogger().l(w5.ERROR, "Failed to create envelope item. Dropping it.", e5);
                }
            }
        } finally {
            bufferedWriter.flush();
        }
    }

    @Override // io.sentry.i1
    public final Object e(Reader reader, Class cls) {
        Object objF0;
        y6 y6Var = this.f17398a;
        try {
            h2 h2Var = new h2(reader);
            try {
                u1 u1Var = (u1) this.f17399b.get(cls);
                if (u1Var != null) {
                    objF0 = cls.cast(u1Var.a(h2Var, y6Var.getLogger()));
                } else {
                    if (!cls.isArray() && !Collection.class.isAssignableFrom(cls) && !String.class.isAssignableFrom(cls) && !Map.class.isAssignableFrom(cls)) {
                        h2Var.close();
                        return null;
                    }
                    objF0 = h2Var.F0();
                }
                h2Var.close();
                return objF0;
            } finally {
            }
        } catch (Exception e5) {
            y6Var.getLogger().l(w5.ERROR, "Error when deserializing", e5);
            return null;
        }
    }

    public final String f(Object obj, boolean z) throws IOException {
        StringWriter stringWriter = new StringWriter();
        y6 y6Var = this.f17398a;
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(stringWriter, y6Var.getMaxDepth());
        if (z) {
            fVar.Y("\t");
        }
        ((f2) fVar.f15417l).f(fVar, y6Var.getLogger(), obj);
        return stringWriter.toString();
    }
}

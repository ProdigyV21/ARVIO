package io.sentry;

import io.sentry.exception.SentryEnvelopeException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final class h5 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Charset f17319d = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i5 f17320a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Callable f17321b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f17322c;

    public h5(i5 i5Var, byte[] bArr) {
        this.f17320a = i5Var;
        this.f17322c = bArr;
        this.f17321b = null;
    }

    public static void a(long j10, long j11, String str) throws SentryEnvelopeException {
        if (j10 > j11) {
            throw new SentryEnvelopeException(String.format("Dropping attachment with filename '%s', because the size of the passed bytes with %d bytes is bigger than the maximum allowed attachment size of %d bytes.", str, Long.valueOf(j10), Long.valueOf(j11)));
        }
    }

    public static h5 b(i1 i1Var, io.sentry.clientreport.b bVar) {
        ac.b.V(i1Var, "ISerializer is required.");
        fi.iki.elonen.f fVar = new fi.iki.elonen.f((Object) new c5(i1Var, bVar, 2), false);
        return new h5(new i5(v5.resolve(bVar), new d5(fVar, 9), "application/json", null, null), new d5(fVar, 10));
    }

    public static h5 c(final q3 q3Var, final i1 i1Var, final y0 y0Var) {
        final File file = q3Var.f17758u;
        fi.iki.elonen.f fVar = new fi.iki.elonen.f((Object) new Callable() { // from class: io.sentry.g5
            @Override // java.util.concurrent.Callable
            public final Object call() throws SentryEnvelopeException {
                i1 i1Var2 = i1Var;
                File file2 = file;
                q3 q3Var2 = q3Var;
                if (file2 != null) {
                    if (!file2.exists()) {
                        throw new SentryEnvelopeException(a0.c.l("Dropping profile chunk, because the file '", file2.getName(), "' doesn't exists"));
                    }
                    if ("java".equals(q3Var2.f17753p)) {
                        v2 v2Var = v2.f17983a;
                        y0 y0Var2 = y0Var;
                        if (v2Var.equals(y0Var2)) {
                            throw new SentryEnvelopeException("No ProfileConverter available, dropping chunk.");
                        }
                        try {
                            file2.getAbsolutePath();
                            q3Var2.f17760w = y0Var2.a();
                        } catch (Exception e5) {
                            throw new SentryEnvelopeException("Profile conversion failed", e5);
                        }
                    } else {
                        try {
                            String str = new String(io.sentry.vendor.a.a(qb.d.K(52428800L, file2.getPath())), "US-ASCII");
                            if (str.isEmpty()) {
                                throw new SentryEnvelopeException("Profiling trace file is empty");
                            }
                            q3Var2.f17759v = str;
                        } catch (UnsupportedEncodingException e6) {
                            throw new AssertionError(e6);
                        }
                    }
                }
                try {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, h5.f17319d));
                            try {
                                i1Var2.a(bufferedWriter, q3Var2);
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                bufferedWriter.close();
                                byteArrayOutputStream.close();
                                return byteArray;
                            } finally {
                            }
                        } catch (Throwable th) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (IOException e10) {
                        throw new SentryEnvelopeException("Failed to serialize profile chunk\n" + e10.getMessage());
                    }
                } finally {
                    if (file2 != null) {
                        file2.delete();
                    }
                }
            }
        }, false);
        return new h5(new i5(v5.ProfileChunk, new d5(fVar, 14), "application-json", file != null ? file.getName() : null, (String) null, q3Var.f17753p, (Integer) null), new d5(fVar, 15));
    }

    public static h5 d(i1 i1Var, j7 j7Var) {
        ac.b.V(i1Var, "ISerializer is required.");
        ac.b.V(j7Var, "Session is required.");
        fi.iki.elonen.f fVar = new fi.iki.elonen.f((Object) new c5(i1Var, j7Var, 3), false);
        return new h5(new i5(v5.Session, new d5(fVar, 16), "application/json", null, null), new d5(fVar, 17));
    }

    public static byte[] j(LinkedHashMap linkedHashMap) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write((byte) (linkedHashMap.size() | 128));
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                byte[] bytes = ((String) entry.getKey()).getBytes(f17319d);
                int length = bytes.length;
                byteArrayOutputStream.write(-39);
                byteArrayOutputStream.write((byte) length);
                byteArrayOutputStream.write(bytes);
                byte[] bArr = (byte[]) entry.getValue();
                int length2 = bArr.length;
                byteArrayOutputStream.write(-58);
                byteArrayOutputStream.write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(length2).array());
                byteArrayOutputStream.write(bArr);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final io.sentry.clientreport.b e(i1 i1Var) throws IOException {
        i5 i5Var = this.f17320a;
        if (i5Var == null || i5Var.f17356o != v5.ClientReport) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(f()), f17319d));
        try {
            io.sentry.clientreport.b bVar = (io.sentry.clientreport.b) i1Var.e(bufferedReader, io.sentry.clientreport.b.class);
            bufferedReader.close();
            return bVar;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final byte[] f() {
        Callable callable;
        if (this.f17322c == null && (callable = this.f17321b) != null) {
            this.f17322c = (byte[]) callable.call();
        }
        return this.f17322c;
    }

    public final z5 g(i1 i1Var) throws IOException {
        i5 i5Var = this.f17320a;
        if (i5Var == null || i5Var.f17356o != v5.Log) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(f()), f17319d));
        try {
            z5 z5Var = (z5) i1Var.e(bufferedReader, z5.class);
            bufferedReader.close();
            return z5Var;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final d6 h(i1 i1Var) throws IOException {
        i5 i5Var = this.f17320a;
        if (i5Var == null || i5Var.f17356o != v5.TraceMetric) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(f()), f17319d));
        try {
            d6 d6Var = (d6) i1Var.e(bufferedReader, d6.class);
            bufferedReader.close();
            return d6Var;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final io.sentry.protocol.e0 i(i1 i1Var) throws IOException {
        i5 i5Var = this.f17320a;
        if (i5Var == null || i5Var.f17356o != v5.Transaction) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(f()), f17319d));
        try {
            io.sentry.protocol.e0 e0Var = (io.sentry.protocol.e0) i1Var.e(bufferedReader, io.sentry.protocol.e0.class);
            bufferedReader.close();
            return e0Var;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public h5(i5 i5Var, Callable callable) {
        this.f17320a = i5Var;
        this.f17321b = callable;
        this.f17322c = null;
    }
}

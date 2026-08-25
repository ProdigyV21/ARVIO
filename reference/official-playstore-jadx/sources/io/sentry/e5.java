package io.sentry;

import io.sentry.exception.SentryEnvelopeException;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e5 implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17231a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ long f17232b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ i1 f17233c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f17234d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f17235e;

    public /* synthetic */ e5(a aVar, long j10, i1 i1Var, ILogger iLogger) {
        this.f17234d = aVar;
        this.f17232b = j10;
        this.f17233c = i1Var;
        this.f17235e = iLogger;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() throws SentryEnvelopeException {
        int i10 = this.f17231a;
        i1 i1Var = this.f17233c;
        Object obj = this.f17235e;
        long j10 = this.f17232b;
        Object obj2 = this.f17234d;
        switch (i10) {
            case 0:
                a aVar = (a) obj2;
                ILogger iLogger = (ILogger) obj;
                byte[] byteArray = aVar.f16288a;
                String str = aVar.f16291d;
                if (byteArray == null) {
                    io.sentry.protocol.j0 j0Var = aVar.f16289b;
                    if (j0Var == null) {
                        androidx.media3.datasource.c cVar = aVar.f16290c;
                        if (cVar != null && (byteArray = (byte[]) cVar.call()) != null) {
                            h5.a(byteArray.length, j10, str);
                        }
                        throw new SentryEnvelopeException(a0.c.l("Couldn't attach the attachment ", str, ".\nPlease check that either bytes, serializable, path or provider is set."));
                    }
                    Charset charset = io.sentry.util.i.f17960a;
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, io.sentry.util.i.f17960a));
                            try {
                                i1Var.a(bufferedWriter, j0Var);
                                byteArray = byteArrayOutputStream.toByteArray();
                                bufferedWriter.close();
                                byteArrayOutputStream.close();
                            } finally {
                                try {
                                    break;
                                } catch (Throwable th) {
                                }
                            }
                        } finally {
                            try {
                                break;
                            } catch (Throwable th2) {
                            }
                        }
                    } catch (Throwable th3) {
                        iLogger.l(w5.ERROR, "Could not serialize serializable", th3);
                        byteArray = null;
                    }
                    if (byteArray != null) {
                        h5.a(byteArray.length, j10, str);
                    }
                    throw new SentryEnvelopeException(a0.c.l("Couldn't attach the attachment ", str, ".\nPlease check that either bytes, serializable, path or provider is set."));
                }
                h5.a(byteArray.length, j10, str);
                return byteArray;
            default:
                File file = (File) obj2;
                t3 t3Var = (t3) obj;
                if (!file.exists()) {
                    throw new SentryEnvelopeException(a0.c.l("Dropping profiling trace data, because the file '", file.getName(), "' doesn't exists"));
                }
                try {
                    String str2 = new String(io.sentry.vendor.a.a(qb.d.K(j10, file.getPath())), "US-ASCII");
                    if (str2.isEmpty()) {
                        throw new SentryEnvelopeException("Profiling trace file is empty");
                    }
                    t3Var.L = str2;
                    try {
                        t3Var.f17883v = (List) t3Var.f17873l.call();
                        break;
                    } catch (Throwable unused) {
                    }
                    try {
                        try {
                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                            try {
                                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream2, h5.f17319d));
                                try {
                                    i1Var.a(bufferedWriter2, t3Var);
                                    byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                                    bufferedWriter2.close();
                                    byteArrayOutputStream2.close();
                                    return byteArray2;
                                } finally {
                                    try {
                                        break;
                                    } catch (Throwable th4) {
                                    }
                                }
                            } catch (Throwable th5) {
                                try {
                                    byteArrayOutputStream2.close();
                                    break;
                                } catch (Throwable th6) {
                                    th5.addSuppressed(th6);
                                }
                                throw th5;
                            }
                        } finally {
                            file.delete();
                        }
                    } catch (IOException e5) {
                        throw new SentryEnvelopeException("Failed to serialize profiling trace data\n" + e5.getMessage());
                    }
                } catch (UnsupportedEncodingException e6) {
                    throw new AssertionError(e6);
                }
        }
    }

    public /* synthetic */ e5(File file, long j10, t3 t3Var, i1 i1Var) {
        this.f17234d = file;
        this.f17232b = j10;
        this.f17235e = t3Var;
        this.f17233c = i1Var;
    }
}

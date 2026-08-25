package io.sentry;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c5 implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17132a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ i1 f17133b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f17134c;

    public /* synthetic */ c5(i1 i1Var, Object obj, int i10) {
        this.f17132a = i10;
        this.f17133b = i1Var;
        this.f17134c = obj;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() throws IOException {
        BufferedWriter bufferedWriter;
        ByteArrayOutputStream byteArrayOutputStream;
        switch (this.f17132a) {
            case 0:
                i1 i1Var = this.f17133b;
                d6 d6Var = (d6) this.f17134c;
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream2, h5.f17319d));
                    try {
                        i1Var.a(bufferedWriter2, d6Var);
                        byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        bufferedWriter2.close();
                        byteArrayOutputStream2.close();
                        return byteArray;
                    } finally {
                        try {
                            bufferedWriter2.close();
                            break;
                        } catch (Throwable th) {
                            th.addSuppressed(th);
                        }
                    }
                } finally {
                    try {
                        byteArrayOutputStream2.close();
                        break;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            case 1:
                i1 i1Var2 = this.f17133b;
                v4 v4Var = (v4) this.f17134c;
                ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                try {
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream3, h5.f17319d));
                    try {
                        i1Var2.a(bufferedWriter, v4Var);
                        byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
                        bufferedWriter.close();
                        byteArrayOutputStream3.close();
                        return byteArray2;
                    } finally {
                        try {
                            bufferedWriter.close();
                            break;
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                } finally {
                    try {
                        byteArrayOutputStream3.close();
                        break;
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                }
            case 2:
                i1 i1Var3 = this.f17133b;
                io.sentry.clientreport.b bVar = (io.sentry.clientreport.b) this.f17134c;
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    BufferedWriter bufferedWriter3 = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, h5.f17319d));
                    try {
                        i1Var3.a(bufferedWriter3, bVar);
                        byte[] byteArray3 = byteArrayOutputStream.toByteArray();
                        bufferedWriter3.close();
                        byteArrayOutputStream.close();
                        return byteArray3;
                    } finally {
                        try {
                            bufferedWriter3.close();
                            break;
                        } catch (Throwable th5) {
                            th.addSuppressed(th5);
                        }
                    }
                } finally {
                    try {
                        byteArrayOutputStream.close();
                        break;
                    } catch (Throwable th6) {
                        th.addSuppressed(th6);
                    }
                }
            case 3:
                i1 i1Var4 = this.f17133b;
                j7 j7Var = (j7) this.f17134c;
                ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
                try {
                    BufferedWriter bufferedWriter4 = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream4, h5.f17319d));
                    try {
                        i1Var4.a(bufferedWriter4, j7Var);
                        byte[] byteArray4 = byteArrayOutputStream4.toByteArray();
                        bufferedWriter4.close();
                        byteArrayOutputStream4.close();
                        return byteArray4;
                    } finally {
                        try {
                            bufferedWriter4.close();
                            break;
                        } catch (Throwable th7) {
                            th.addSuppressed(th7);
                        }
                    }
                } finally {
                    try {
                        break;
                    } catch (Throwable th8) {
                    }
                }
            default:
                i1 i1Var5 = this.f17133b;
                z5 z5Var = (z5) this.f17134c;
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, h5.f17319d));
                    try {
                        i1Var5.a(bufferedWriter, z5Var);
                        byte[] byteArray5 = byteArrayOutputStream.toByteArray();
                        bufferedWriter.close();
                        byteArrayOutputStream.close();
                        return byteArray5;
                    } finally {
                        try {
                            break;
                        } catch (Throwable th32) {
                        }
                    }
                } finally {
                    try {
                        break;
                    } catch (Throwable th62) {
                    }
                }
        }
    }
}

package io.sentry.android.core.anr;

import io.sentry.util.s;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final StackTraceElement[] f16435i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f16436l;

    public f(long j10, StackTraceElement[] stackTraceElementArr) {
        this.f16436l = j10;
        this.f16435i = stackTraceElementArr;
    }

    public final void a(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(1);
        dataOutputStream.writeLong(this.f16436l);
        StackTraceElement[] stackTraceElementArr = this.f16435i;
        dataOutputStream.writeInt(stackTraceElementArr.length);
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            String className = stackTraceElement.getClassName();
            Charset charset = s.f17974a;
            String str = "";
            if (className == null) {
                className = "";
            }
            dataOutputStream.writeUTF(className);
            String methodName = stackTraceElement.getMethodName();
            if (methodName == null) {
                methodName = "";
            }
            dataOutputStream.writeUTF(methodName);
            String fileName = stackTraceElement.getFileName();
            dataOutputStream.writeBoolean(fileName == null);
            if (fileName != null) {
                str = fileName;
            }
            dataOutputStream.writeUTF(str);
            dataOutputStream.writeInt(stackTraceElement.getLineNumber());
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.compare(this.f16436l, ((f) obj).f16436l);
    }
}

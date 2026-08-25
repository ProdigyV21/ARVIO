package a1;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.SparseIntArray;
import androidx.appcompat.app.g;
import androidx.appcompat.widget.f0;
import androidx.emoji2.text.i0;
import androidx.emoji2.text.j0;
import androidx.emoji2.text.n;
import androidx.lifecycle.q;
import androidx.navigation.d0;
import androidx.navigation.i;
import androidx.navigation.x0;
import bc.b;
import c2.c;
import c2.d;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.v;
import com.google.android.gms.common.api.x;
import com.google.android.gms.common.internal.h0;
import com.google.android.gms.common.internal.s;
import com.google.android.gms.common.internal.t;
import fi.iki.elonen.p;
import io.sentry.android.core.q0;
import io.sentry.cache.tape.f;
import io.sentry.h5;
import io.sentry.hints.m;
import io.sentry.util.j;
import j$.util.Objects;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class a implements z0.a, g, n, z1.a, b, v, h0, s, d.a, p, fi.iki.elonen.s, f, j, io.sentry.clientreport.f, m, io.sentry.hints.n {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static a f80i;

    public static i j(Context context, d0 d0Var, Bundle bundle, q qVar, x0 x0Var) {
        return new i(context, d0Var, bundle, qVar, x0Var, UUID.randomUUID().toString(), null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0045, code lost:
    
        if (java.lang.Character.isHighSurrogate(r5) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0082, code lost:
    
        if (java.lang.Character.isLowSurrogate(r5) != false) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006c A[EDGE_INSN: B:92:0x006c->B:46:0x006c BREAK  A[LOOP:2: B:47:0x006e->B:58:0x0085], EDGE_INSN: B:93:0x006c->B:46:0x006c BREAK  A[LOOP:2: B:47:0x006e->B:58:0x0085, LOOP_LABEL: LOOP:2: B:47:0x006e->B:58:0x0085]] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00a2 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean m(e1.b r7, android.text.Editable r8, int r9, int r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a1.a.m(e1.b, android.text.Editable, int, int, boolean):boolean");
    }

    public static final CharSequence t(Object obj) {
        Objects.requireNonNull(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    @Override // fi.iki.elonen.s
    public fi.iki.elonen.f a() {
        return new fi.iki.elonen.f(0);
    }

    @Override // c2.d.a
    public d b(c cVar) {
        return new d2.g((Context) cVar.f7382c, (String) cVar.f7383d, (f0) cVar.f7384e, cVar.f7380a, cVar.f7381b);
    }

    @Override // com.google.android.gms.common.internal.s
    public /* bridge */ /* synthetic */ Object convert(x xVar) {
        return null;
    }

    @Override // com.google.android.gms.common.api.internal.v
    public ApiException d(Status status) {
        return t.m(status);
    }

    @Override // io.sentry.cache.tape.f
    public void e(Object obj, OutputStream outputStream) throws IOException {
        io.sentry.android.core.anr.f fVar = (io.sentry.android.core.anr.f) obj;
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        try {
            fVar.a(dataOutputStream);
            dataOutputStream.flush();
            outputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.cache.tape.f
    public Object f(byte[] bArr) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        try {
            if (dataInputStream.readShort() == 1) {
                long j10 = dataInputStream.readLong();
                int i10 = dataInputStream.readInt();
                if (i10 >= 0 && i10 <= 1000) {
                    StackTraceElement[] stackTraceElementArr = new StackTraceElement[i10];
                    for (int i11 = 0; i11 < i10; i11++) {
                        String utf = dataInputStream.readUTF();
                        String utf2 = dataInputStream.readUTF();
                        boolean z = dataInputStream.readBoolean();
                        String utf3 = dataInputStream.readUTF();
                        if (z) {
                            utf3 = null;
                        }
                        stackTraceElementArr[i11] = new StackTraceElement(utf, utf2, utf3, dataInputStream.readInt());
                    }
                    return new io.sentry.android.core.anr.f(j10, stackTraceElementArr);
                }
            }
        } catch (EOFException unused) {
        }
        return null;
    }

    @Override // io.sentry.util.j
    public Object g() {
        return Boolean.valueOf(q0.i());
    }

    @Override // androidx.emoji2.text.n
    public j0 h(i0 i0Var) {
        return new j0(i0Var);
    }

    public SparseIntArray[] k() {
        return null;
    }

    public Signature[] l(PackageManager packageManager, String str) {
        return packageManager.getPackageInfo(str, 64).signatures;
    }

    public boolean n(CharSequence charSequence) {
        return charSequence instanceof androidx.core.text.g;
    }

    public SparseIntArray[] p(Activity activity) {
        return null;
    }

    public SparseIntArray[] q() {
        return null;
    }

    public SparseIntArray[] r() {
        return null;
    }

    @Override // fi.iki.elonen.p
    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public ServerSocket mo0a() {
        return new ServerSocket();
    }

    public void i(Activity activity) {
    }

    @Override // io.sentry.clientreport.f
    public fi.iki.elonen.f z(fi.iki.elonen.f fVar) {
        return fVar;
    }

    @Override // io.sentry.clientreport.f
    public void c(io.sentry.clientreport.d dVar, io.sentry.m mVar) {
    }

    @Override // io.sentry.clientreport.f
    public void s(io.sentry.clientreport.d dVar, h5 h5Var) {
    }

    @Override // io.sentry.clientreport.f
    public void v(io.sentry.clientreport.d dVar, fi.iki.elonen.f fVar) {
    }

    @Override // io.sentry.clientreport.f
    public void o(io.sentry.clientreport.d dVar, io.sentry.m mVar, long j10) {
    }
}

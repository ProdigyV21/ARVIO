package io.sentry;

import io.github.jan.supabase.gotrue.SettingsSessionManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes4.dex */
public final class m3 extends x implements s0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Charset f17468i = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c1 f17469e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r0 f17470f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i1 f17471g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ILogger f17472h;

    public m3(c1 c1Var, r0 r0Var, i1 i1Var, ILogger iLogger, long j10, int i10) {
        super(c1Var, iLogger, j10, i10);
        ac.b.V(c1Var, "Scopes are required.");
        this.f17469e = c1Var;
        ac.b.V(r0Var, "Envelope reader is required.");
        this.f17470f = r0Var;
        ac.b.V(i1Var, "Serializer is required.");
        this.f17471g = i1Var;
        ac.b.V(iLogger, "Logger is required.");
        this.f17472h = iLogger;
    }

    public static /* synthetic */ void c(m3 m3Var, File file, io.sentry.hints.l lVar) {
        ILogger iLogger = m3Var.f17472h;
        if (lVar.a()) {
            return;
        }
        try {
            if (file.delete()) {
                return;
            }
            iLogger.q(w5.ERROR, "Failed to delete: %s", file.getAbsolutePath());
        } catch (RuntimeException e5) {
            iLogger.k(w5.ERROR, e5, "Failed to delete: %s", file.getAbsolutePath());
        }
    }

    @Override // io.sentry.x
    public final boolean a(String str) {
        return (str == null || str.startsWith(SettingsSessionManager.SETTINGS_KEY) || str.startsWith("previous_session") || str.startsWith("startup_crash")) ? false : true;
    }

    @Override // io.sentry.x
    public final void b(File file, j0 j0Var) {
        boolean zA = a(file.getName());
        ILogger iLogger = this.f17472h;
        try {
            if (!zA) {
                iLogger.q(w5.DEBUG, "File '%s' should be ignored.", file.getAbsolutePath());
                return;
            }
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    fi.iki.elonen.f fVarA = this.f17470f.a(bufferedInputStream);
                    if (fVarA == null) {
                        iLogger.q(w5.ERROR, "Stream from path %s resulted in a null envelope.", file.getAbsolutePath());
                    } else {
                        e(fVarA, j0Var);
                        iLogger.q(w5.DEBUG, "File '%s' is done.", file.getAbsolutePath());
                    }
                    bufferedInputStream.close();
                    Object objB = j0Var.b("sentry:typeCheckHint");
                    if (!io.sentry.hints.l.class.isInstance(j0Var.b("sentry:typeCheckHint")) || objB == null) {
                        a.a.V(io.sentry.hints.l.class, objB, iLogger);
                    } else {
                        c(this, file, (io.sentry.hints.l) objB);
                    }
                } catch (Throwable th) {
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (IOException e5) {
                iLogger.l(w5.ERROR, "Error processing envelope.", e5);
                Object objB2 = j0Var.b("sentry:typeCheckHint");
                if (!io.sentry.hints.l.class.isInstance(j0Var.b("sentry:typeCheckHint")) || objB2 == null) {
                    a.a.V(io.sentry.hints.l.class, objB2, iLogger);
                } else {
                    c(this, file, (io.sentry.hints.l) objB2);
                }
            }
        } catch (Throwable th3) {
            Object objB3 = j0Var.b("sentry:typeCheckHint");
            if (!io.sentry.hints.l.class.isInstance(j0Var.b("sentry:typeCheckHint")) || objB3 == null) {
                a.a.V(io.sentry.hints.l.class, objB3, iLogger);
            } else {
                c(this, file, (io.sentry.hints.l) objB3);
            }
            throw th3;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final androidx.core.provider.e d(s7 s7Var) {
        String str;
        ILogger iLogger = this.f17472h;
        if (s7Var != null && (str = s7Var.f17863q) != null) {
            try {
                Double dValueOf = Double.valueOf(Double.parseDouble(str));
                if (androidx.work.impl.t.x(dValueOf, false)) {
                    String str2 = s7Var.f17864r;
                    if (str2 != null) {
                        Double dValueOf2 = Double.valueOf(Double.parseDouble(str2));
                        if (androidx.work.impl.t.x(dValueOf2, false)) {
                            return new androidx.core.provider.e(Boolean.TRUE, dValueOf, dValueOf2);
                        }
                    }
                    return androidx.work.impl.t.b(new androidx.core.provider.e(Boolean.TRUE, dValueOf));
                }
                iLogger.q(w5.ERROR, "Invalid sample rate parsed from TraceContext: %s", str);
            } catch (Exception unused) {
                iLogger.q(w5.ERROR, "Unable to parse sample rate from TraceContext: %s", str);
            }
        }
        return new androidx.core.provider.e(Boolean.TRUE, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02a0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02a7 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e(fi.iki.elonen.f r23, io.sentry.j0 r24) {
        /*
            Method dump skipped, instruction units count: 689
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.m3.e(fi.iki.elonen.f, io.sentry.j0):void");
    }

    public final boolean f(j0 j0Var) {
        Object objB = j0Var.b("sentry:typeCheckHint");
        if (objB instanceof io.sentry.hints.i) {
            return ((io.sentry.hints.i) objB).g();
        }
        a.a.V(io.sentry.hints.i.class, objB, this.f17472h);
        return true;
    }
}

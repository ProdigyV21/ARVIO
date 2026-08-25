package io.sentry;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class r2 implements q0, w3, n1, o1, ILogger {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final r2 f17772i = new r2();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final r2 f17773l = new r2();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final r2 f17774m = new r2();

    @Override // io.sentry.w3
    public v3 G() {
        return w2.f18036a;
    }

    @Override // io.sentry.o1
    public io.sentry.transport.g a(y6 y6Var, fi.iki.elonen.f fVar) {
        return new io.sentry.transport.c(y6Var, new io.sentry.transport.p(y6Var), y6Var.getTransportGate(), fVar);
    }

    @Override // io.sentry.n1
    public t3 c(h7 h7Var, List list, y6 y6Var) {
        return null;
    }

    @Override // io.sentry.n1
    public boolean isRunning() {
        return false;
    }

    @Override // io.sentry.ILogger
    public void k(w5 w5Var, Throwable th, String str, Object... objArr) {
        PrintStream printStream = System.out;
        String str2 = String.format(str, objArr);
        String string = th.toString();
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        printStream.println(w5Var + ": " + str2 + " \n " + string + "\n" + stringWriter.toString());
    }

    @Override // io.sentry.ILogger
    public void l(w5 w5Var, String str, Throwable th) {
        if (th == null) {
            q(w5Var, str, new Object[0]);
            return;
        }
        PrintStream printStream = System.out;
        String str2 = String.format(str, th.toString());
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        printStream.println(w5Var + ": " + str2 + "\n" + stringWriter.toString());
    }

    @Override // io.sentry.w3
    public io.sentry.protocol.v o() {
        return io.sentry.protocol.v.f17707l;
    }

    @Override // io.sentry.ILogger
    public void q(w5 w5Var, String str, Object... objArr) {
        System.out.println(w5Var + ": " + String.format(str, objArr));
    }

    @Override // io.sentry.ILogger
    public boolean t(w5 w5Var) {
        return true;
    }

    @Override // io.sentry.n1
    public void close() {
    }

    @Override // io.sentry.w3
    public void d() {
    }

    @Override // io.sentry.w3
    public void pause() {
    }

    @Override // io.sentry.n1
    public void start() {
    }

    @Override // io.sentry.w3
    public void stop() {
    }

    @Override // io.sentry.w3
    public void y() {
    }

    @Override // io.sentry.n1
    public void b(m1 m1Var) {
    }

    @Override // io.sentry.w3
    public void j(io.sentry.android.replay.d dVar) {
    }

    @Override // io.sentry.w3
    public void x(Boolean bool) {
    }
}

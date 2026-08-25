package io.sentry;

import java.util.List;
import java.util.Map;
import java.util.Queue;

/* JADX INFO: loaded from: classes4.dex */
public interface a1 {
    io.sentry.protocol.i0 A();

    String B();

    void C();

    io.sentry.featureflags.b D();

    j7 E();

    androidx.appcompat.app.i1 F();

    void G(String str);

    void H(androidx.appcompat.app.i1 i1Var);

    f1 I();

    List J();

    void K(j5 j5Var);

    androidx.appcompat.app.i1 M(z3 z3Var);

    void N(b4 b4Var);

    void O(io.sentry.protocol.v vVar);

    List P();

    void clear();

    a1 clone();

    void d(io.sentry.protocol.i0 i0Var);

    void e(String str, String str2);

    void g(String str, String str2);

    Map getExtras();

    w5 getLevel();

    y6 getOptions();

    io.sentry.protocol.p getRequest();

    void h(f fVar, j0 j0Var);

    m1 j();

    j7 m();

    io.sentry.protocol.h n();

    io.sentry.protocol.v o();

    void p(io.sentry.protocol.v vVar);

    fi.iki.elonen.f q();

    k1 r();

    Queue s();

    j7 t(a4 a4Var);

    Map u();

    List v();

    io.sentry.protocol.c w();

    String x();

    void y(m1 m1Var);

    List z();
}

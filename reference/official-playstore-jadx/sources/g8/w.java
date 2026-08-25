package g8;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface w extends c {

    public interface a<D extends w> {
        a a(List list);

        a b(int i10);

        w build();

        a c(v9.v0 v0Var);

        a d(e eVar);

        a e();

        a f();

        a g();

        a h();

        a i(s0 s0Var);

        a j(v9.w wVar);

        a k(int i10);

        a l();

        a m();

        a n(p pVar);

        a o(h8.h hVar);

        a p(k kVar);

        a q(f9.f fVar);

        a r();
    }

    boolean A();

    boolean B0();

    a C0();

    @Override // g8.c, g8.a, g8.k
    w a();

    w b(v9.y0 y0Var);

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    boolean isSuspend();

    w n0();

    boolean y0();
}

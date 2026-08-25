package j$.util.stream;

import java.util.concurrent.CountedCompleter;

/* JADX INFO: loaded from: classes2.dex */
public class u3 extends CountedCompleter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g2 f18904a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18905b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f18906c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f18907d;

    public u3(g2 g2Var, Object obj, int i10) {
        this.f18906c = i10;
        this.f18904a = g2Var;
        this.f18905b = 0;
        this.f18907d = obj;
    }

    public u3(u3 u3Var, g2 g2Var, int i10, byte b10) {
        super(u3Var);
        this.f18904a = g2Var;
        this.f18905b = i10;
    }

    @Override // java.util.concurrent.CountedCompleter
    public final void compute() {
        u3 u3VarA = this;
        while (u3VarA.f18904a.o() != 0) {
            u3VarA.setPendingCount(u3VarA.f18904a.o() - 1);
            int i10 = 0;
            int iCount = 0;
            while (i10 < u3VarA.f18904a.o() - 1) {
                u3 u3VarA2 = u3VarA.a(i10, u3VarA.f18905b + iCount);
                iCount = (int) (u3VarA2.f18904a.count() + ((long) iCount));
                u3VarA2.fork();
                i10++;
            }
            u3VarA = u3VarA.a(i10, u3VarA.f18905b + iCount);
        }
        switch (u3VarA.f18906c) {
            case 0:
                ((f2) u3VarA.f18904a).e(u3VarA.f18907d, u3VarA.f18905b);
                break;
            default:
                u3VarA.f18904a.k((Object[]) u3VarA.f18907d, u3VarA.f18905b);
                break;
        }
        u3VarA.propagateCompletion();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public u3(u3 u3Var, g2 g2Var, int i10) {
        this(u3Var, g2Var, i10, (byte) 0);
        this.f18906c = 1;
        this.f18907d = (Object[]) u3Var.f18907d;
    }

    public final u3 a(int i10, int i11) {
        switch (this.f18906c) {
            case 0:
                return new u3(this, ((f2) this.f18904a).a(i10), i11);
            default:
                return new u3(this, this.f18904a.a(i10), i11);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public u3(u3 u3Var, f2 f2Var, int i10) {
        this(u3Var, f2Var, i10, (byte) 0);
        this.f18906c = 0;
        this.f18907d = u3Var.f18907d;
    }
}

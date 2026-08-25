package j$.util.stream;

import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class r extends c5 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f18839s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f18840t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(a aVar, int i10, Object obj, int i11) {
        super(aVar, i10);
        this.f18839s = i11;
        this.f18840t = obj;
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        switch (this.f18839s) {
            case 0:
                return new q(this, l5Var, 0);
            case 1:
                return new t0(this, l5Var, 0);
            case 2:
                return new c1(this, l5Var, 0);
            case 3:
                return new m(this, l5Var, 1);
            case 4:
                return new m(this, l5Var, 2);
            case 5:
                return new m(this, l5Var, 3);
            default:
                return new l(this, l5Var);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(d5 d5Var, Consumer consumer) {
        super(d5Var, 0);
        this.f18839s = 3;
        this.f18840t = consumer;
    }
}

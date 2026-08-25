package j$.util.stream;

import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class u0 extends z0 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f18901s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f18902t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u0(a aVar, int i10, Object obj, int i11) {
        super(aVar, i10);
        this.f18901s = i11;
        this.f18902t = obj;
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        switch (this.f18901s) {
            case 0:
                return new t0(this, l5Var, 1);
            case 1:
                return new t0(this, l5Var, 2);
            case 2:
                return new w0(this, l5Var);
            case 3:
                return new m(this, l5Var, 4);
            default:
                return new y4(this, l5Var);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u0(a1 a1Var, IntConsumer intConsumer) {
        super(a1Var, 0);
        this.f18901s = 0;
        this.f18902t = intConsumer;
    }
}

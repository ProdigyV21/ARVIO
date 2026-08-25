package j$.util.stream;

import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class s extends z {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f18859s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f18860t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ s(a aVar, int i10, Object obj, int i11) {
        super(aVar, i10);
        this.f18859s = i11;
        this.f18860t = obj;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(a0 a0Var, DoubleConsumer doubleConsumer) {
        super(a0Var, 0);
        this.f18859s = 2;
        this.f18860t = doubleConsumer;
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        switch (this.f18859s) {
            case 0:
                return new q(this, l5Var, 1);
            case 1:
                return new v(this, l5Var);
            case 2:
                return new q(this, l5Var, 5);
            case 3:
                return new m(this, l5Var, 6);
            default:
                return new y4(this, l5Var);
        }
    }
}

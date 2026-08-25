package j$.util.stream;

import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public final class c4 extends v3 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f18652h;

    public /* synthetic */ c4(int i10) {
        this.f18652h = i10;
    }

    @Override // j$.util.stream.v3
    public final q4 D0() {
        switch (this.f18652h) {
            case 0:
                return new u4();
            case 1:
                return new s4();
            case 2:
                return new v4();
            default:
                return new t4();
        }
    }

    @Override // j$.util.stream.v3, j$.util.stream.e8
    public final Object e(a aVar, Spliterator spliterator) {
        switch (this.f18652h) {
            case 0:
                if (!y6.SIZED.r(aVar.f18607m)) {
                }
                break;
            case 1:
                if (!y6.SIZED.r(aVar.f18607m)) {
                }
                break;
            case 2:
                if (!y6.SIZED.r(aVar.f18607m)) {
                }
                break;
            default:
                if (!y6.SIZED.r(aVar.f18607m)) {
                }
                break;
        }
        return (Long) super.e(aVar, spliterator);
    }

    @Override // j$.util.stream.v3, j$.util.stream.e8
    public final Object h(v3 v3Var, Spliterator spliterator) {
        switch (this.f18652h) {
            case 0:
                if (!y6.SIZED.r(((a) v3Var).f18607m)) {
                }
                break;
            case 1:
                if (!y6.SIZED.r(((a) v3Var).f18607m)) {
                }
                break;
            case 2:
                if (!y6.SIZED.r(((a) v3Var).f18607m)) {
                }
                break;
            default:
                if (!y6.SIZED.r(((a) v3Var).f18607m)) {
                }
                break;
        }
        return (Long) super.h(v3Var, spliterator);
    }

    @Override // j$.util.stream.v3, j$.util.stream.e8
    public final int t() {
        switch (this.f18652h) {
        }
        return y6.f18974r;
    }
}

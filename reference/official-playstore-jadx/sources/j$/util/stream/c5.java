package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c5 extends d5 {
    @Override // j$.util.stream.a
    public final boolean O0() {
        return false;
    }

    @Override // j$.util.stream.g
    public final g unordered() {
        return !y6.ORDERED.r(this.f18607m) ? this : new z4(this, y6.f18974r);
    }
}

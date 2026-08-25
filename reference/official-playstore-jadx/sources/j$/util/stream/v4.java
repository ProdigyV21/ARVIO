package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class v4 extends w4 {
    @Override // j$.util.stream.r4, java.util.function.Supplier
    public final Object get() {
        return Long.valueOf(this.f18941b);
    }

    @Override // j$.util.stream.q4
    public final void j(q4 q4Var) {
        this.f18941b += ((w4) q4Var).f18941b;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f18941b++;
    }
}

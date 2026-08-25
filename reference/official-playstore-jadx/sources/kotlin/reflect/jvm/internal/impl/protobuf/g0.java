package kotlin.reflect.jvm.internal.impl.protobuf;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 implements e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.cache.tape.d f19773i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public a0 f19774l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19775m;

    public g0(h0 h0Var) {
        io.sentry.cache.tape.d dVar = new io.sentry.cache.tape.d(h0Var);
        this.f19773i = dVar;
        this.f19774l = new a0(dVar.a());
        this.f19775m = h0Var.f19777l;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f19775m > 0;
    }

    @Override // java.util.Iterator
    public final Byte next() {
        if (!this.f19774l.hasNext()) {
            this.f19774l = new a0(this.f19773i.a());
        }
        this.f19775m--;
        return Byte.valueOf(this.f19774l.a());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 implements e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f19761i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f19762l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ b0 f19763m;

    public a0(b0 b0Var) {
        this.f19763m = b0Var;
        this.f19762l = b0Var.f19764l.length;
    }

    public final byte a() {
        try {
            byte[] bArr = this.f19763m.f19764l;
            int i10 = this.f19761i;
            this.f19761i = i10 + 1;
            return bArr[i10];
        } catch (ArrayIndexOutOfBoundsException e5) {
            throw new NoSuchElementException(e5.getMessage());
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f19761i < this.f19762l;
    }

    @Override // java.util.Iterator
    public final Byte next() {
        return Byte.valueOf(a());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public final class n3 extends o3 {
    @Override // androidx.datastore.preferences.protobuf.o3
    public final boolean c(long j10, Object obj) {
        return this.f2486a.getBoolean(obj, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final byte d(long j10, Object obj) {
        return this.f2486a.getByte(obj, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final double e(long j10, Object obj) {
        return this.f2486a.getDouble(obj, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final float f(long j10, Object obj) {
        return this.f2486a.getFloat(obj, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final void k(Object obj, long j10, boolean z) {
        this.f2486a.putBoolean(obj, j10, z);
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final void l(Object obj, long j10, byte b10) {
        this.f2486a.putByte(obj, j10, b10);
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final void m(Object obj, long j10, double d4) {
        this.f2486a.putDouble(obj, j10, d4);
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final void n(Object obj, long j10, float f10) {
        this.f2486a.putFloat(obj, j10, f10);
    }
}

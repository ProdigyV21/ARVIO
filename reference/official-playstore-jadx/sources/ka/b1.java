package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class b1 implements p1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f19554i;

    public b1(boolean z) {
        this.f19554i = z;
    }

    @Override // ka.p1
    public final i2 getList() {
        return null;
    }

    @Override // ka.p1
    public final boolean isActive() {
        return this.f19554i;
    }

    public final String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("Empty{"), this.f19554i ? "Active" : "New", '}');
    }
}

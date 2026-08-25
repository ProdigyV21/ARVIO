package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i0 f22502a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f22503b;

    public z(i0 i0Var, b bVar) {
        this.f22502a = i0Var;
        this.f22503b = bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        return this.f22502a.equals(zVar.f22502a) && this.f22503b.equals(zVar.f22503b);
    }

    public final int hashCode() {
        return this.f22503b.hashCode() + ((this.f22502a.hashCode() + (l.SESSION_START.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "SessionEvent(eventType=" + l.SESSION_START + ", sessionData=" + this.f22502a + ", applicationInfo=" + this.f22503b + ')';
    }
}

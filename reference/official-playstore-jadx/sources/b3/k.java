package b3;

/* JADX INFO: loaded from: classes4.dex */
public final class k extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f7175a;

    public k(i iVar) {
        this.f7175a = iVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        Object obj2 = q.f7192i;
        if (obj2.equals(obj2)) {
            return this.f7175a.equals(((k) rVar).f7175a);
        }
        return false;
    }

    public final int hashCode() {
        return ((q.f7192i.hashCode() ^ 1000003) * 1000003) ^ this.f7175a.hashCode();
    }

    public final String toString() {
        return "ClientInfo{clientType=" + q.f7192i + ", androidClientInfo=" + this.f7175a + "}";
    }
}

package b3;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class j extends p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f7174a;

    public j(ArrayList arrayList) {
        this.f7174a = arrayList;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        return this.f7174a.equals(((j) ((p) obj)).f7174a);
    }

    public final int hashCode() {
        return this.f7174a.hashCode() ^ 1000003;
    }

    public final String toString() {
        return "BatchedLogRequest{logRequests=" + this.f7174a + "}";
    }
}

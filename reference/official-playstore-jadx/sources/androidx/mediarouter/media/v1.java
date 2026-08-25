package androidx.mediarouter.media;

import j$.util.Objects;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class v1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4491a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f4492b;

    public v1(x xVar) {
        this.f4491a = xVar.f4506b;
        this.f4492b = xVar.f4507c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v1)) {
            return false;
        }
        v1 v1Var = (v1) obj;
        return this.f4491a.equals(v1Var.f4491a) && this.f4492b == v1Var.f4492b;
    }

    public final int hashCode() {
        return Objects.hash(this.f4491a, Boolean.valueOf(this.f4492b), null);
    }
}

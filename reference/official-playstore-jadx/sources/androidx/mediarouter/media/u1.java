package androidx.mediarouter.media;

import android.text.TextUtils;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class u1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4487b;

    public u1(q1 q1Var) {
        this.f4486a = q1Var.f4474a;
        this.f4487b = q1Var.f4475b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u1)) {
            return false;
        }
        u1 u1Var = (u1) obj;
        return this.f4486a.equals(u1Var.f4486a) && this.f4487b == u1Var.f4487b && TextUtils.equals(null, null);
    }

    public final int hashCode() {
        return Objects.hash(this.f4486a, Integer.valueOf(this.f4487b), 0, 0, null);
    }
}

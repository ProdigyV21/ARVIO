package androidx.media;

import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public h f3355a;

    public d(String str, int i10, int i11) {
        if (str == null) {
            throw new NullPointerException("package shouldn't be null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packageName should be nonempty");
        }
        if (Build.VERSION.SDK_INT < 28) {
            this.f3355a = new h(str, i10, i11);
            return;
        }
        g gVar = new g(str, i10, i11);
        f.a(i10, i11, str);
        this.f3355a = gVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            return this.f3355a.equals(((d) obj).f3355a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f3355a.hashCode();
    }
}

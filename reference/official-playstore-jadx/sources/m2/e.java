package m2;

import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f20185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f20186b;

    public e(Uri uri, boolean z) {
        this.f20185a = uri;
        this.f20186b = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!e.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        e eVar = (e) obj;
        return kotlin.jvm.internal.p.a(this.f20185a, eVar.f20185a) && this.f20186b == eVar.f20186b;
    }

    public final int hashCode() {
        return (this.f20185a.hashCode() * 31) + (this.f20186b ? 1231 : 1237);
    }
}

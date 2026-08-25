package androidx.media;

import android.text.TextUtils;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class h implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3356a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3357b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3358c;

    public h(String str, int i10, int i11) {
        this.f3356a = str;
        this.f3357b = i10;
        this.f3358c = i11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        int i10 = hVar.f3358c;
        String str = hVar.f3356a;
        int i11 = hVar.f3357b;
        int i12 = this.f3358c;
        String str2 = this.f3356a;
        int i13 = this.f3357b;
        return (i13 < 0 || i11 < 0) ? TextUtils.equals(str2, str) && i12 == i10 : TextUtils.equals(str2, str) && i13 == i11 && i12 == i10;
    }

    public final int hashCode() {
        return Objects.hash(this.f3356a, Integer.valueOf(this.f3358c));
    }
}

package kotlin.reflect;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import h.f0;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class t {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final t f19910c = new t(0, null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f19911a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f19912b;

    public t(int i10, q qVar) {
        String string;
        this.f19911a = i10;
        this.f19912b = qVar;
        if ((i10 == 0) == (qVar == null)) {
            return;
        }
        if (i10 != 0) {
            StringBuilder sb2 = new StringBuilder("The projection variance ");
            sb2.append(i10 != 1 ? i10 != 2 ? i10 != 3 ? "null" : HlsMediaPlaylist.Interstitial.SNAP_TYPE_OUT : HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN : "INVARIANT");
            sb2.append(" requires type to be specified.");
            string = sb2.toString();
        } else {
            string = "Star projection must have no type specified.";
        }
        throw new IllegalArgumentException(string.toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        return this.f19911a == tVar.f19911a && kotlin.jvm.internal.p.a(this.f19912b, tVar.f19912b);
    }

    public final int hashCode() {
        int i10 = this.f19911a;
        int iC = (i10 == 0 ? 0 : f0.c(i10)) * 31;
        q qVar = this.f19912b;
        return iC + (qVar != null ? qVar.hashCode() : 0);
    }

    public final String toString() {
        int i10 = this.f19911a;
        int i11 = i10 == 0 ? -1 : s.f19909a[f0.c(i10)];
        if (i11 == -1) {
            return "*";
        }
        q qVar = this.f19912b;
        if (i11 == 1) {
            return String.valueOf(qVar);
        }
        if (i11 == 2) {
            return "in " + qVar;
        }
        if (i11 != 3) {
            throw new NoWhenBranchMatchedException();
        }
        return "out " + qVar;
    }
}

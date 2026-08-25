package androidx.media3.exoplayer.dash.manifest;

import a0.c;
import android.net.Uri;
import androidx.media3.common.util.UriUtil;

/* JADX INFO: loaded from: classes3.dex */
public final class RangedUri {
    private int hashCode;
    public final long length;
    private final String referenceUri;
    public final long start;

    public RangedUri(String str, long j10, long j11) {
        this.referenceUri = str == null ? "" : str;
        this.start = j10;
        this.length = j11;
    }

    public RangedUri attemptMerge(RangedUri rangedUri, String str) {
        String strResolveUriString = resolveUriString(str);
        RangedUri rangedUri2 = null;
        if (rangedUri != null && strResolveUriString.equals(rangedUri.resolveUriString(str))) {
            long j10 = this.length;
            if (j10 != -1) {
                long j11 = this.start;
                if (j11 + j10 == rangedUri.start) {
                    long j12 = rangedUri.length;
                    return new RangedUri(strResolveUriString, j11, j12 != -1 ? j10 + j12 : -1L);
                }
            }
            long j13 = rangedUri.length;
            if (j13 != -1) {
                long j14 = rangedUri.start;
                if (j14 + j13 == this.start) {
                    rangedUri2 = new RangedUri(strResolveUriString, j14, j10 != -1 ? j13 + j10 : -1L);
                }
            }
        }
        return rangedUri2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && RangedUri.class == obj.getClass()) {
            RangedUri rangedUri = (RangedUri) obj;
            if (this.start == rangedUri.start && this.length == rangedUri.length && this.referenceUri.equals(rangedUri.referenceUri)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.referenceUri.hashCode() + ((((527 + ((int) this.start)) * 31) + ((int) this.length)) * 31);
        }
        return this.hashCode;
    }

    public Uri resolveUri(String str) {
        return UriUtil.resolveToUri(str, this.referenceUri);
    }

    public String resolveUriString(String str) {
        return UriUtil.resolve(str, this.referenceUri);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("RangedUri(referenceUri=");
        sb2.append(this.referenceUri);
        sb2.append(", start=");
        sb2.append(this.start);
        sb2.append(", length=");
        return c.j(this.length, ")", sb2);
    }
}

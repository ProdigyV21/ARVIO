package androidx.media3.extractor.mp4;

import androidx.compose.material3.d;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SniffFailure;

/* JADX INFO: loaded from: classes3.dex */
public final class AtomSizeTooSmallSniffFailure implements SniffFailure {
    public final long atomSize;
    public final int atomType;
    public final int minimumHeaderSize;

    public AtomSizeTooSmallSniffFailure(int i10, long j10, int i11) {
        this.atomType = i10;
        this.atomSize = j10;
        this.minimumHeaderSize = i11;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AtomSizeTooSmall{type=");
        sb2.append(Util.toFourccString(this.atomType));
        sb2.append(", size=");
        sb2.append(this.atomSize);
        sb2.append(", minHeaderSize=");
        return d.j(this.minimumHeaderSize, "}", sb2);
    }
}

package androidx.media3.extractor.metadata;

import androidx.media3.common.Format;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.g;
import qb.d;

/* JADX INFO: loaded from: classes3.dex */
public final class ThumbnailMetadata implements Metadata.Entry {
    public final long presentationTimeUs;

    public ThumbnailMetadata(long j10) {
        this.presentationTimeUs = j10;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && ThumbnailMetadata.class == obj.getClass() && this.presentationTimeUs == ((ThumbnailMetadata) obj).presentationTimeUs;
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ byte[] getWrappedMetadataBytes() {
        return g.a(this);
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ Format getWrappedMetadataFormat() {
        return g.b(this);
    }

    public int hashCode() {
        return d.F(this.presentationTimeUs) + 527;
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ void populateMediaMetadata(MediaMetadata.Builder builder) {
        g.c(this, builder);
    }

    public String toString() {
        return "ThumbnailMetadata: presentationTimeUs=" + this.presentationTimeUs;
    }
}

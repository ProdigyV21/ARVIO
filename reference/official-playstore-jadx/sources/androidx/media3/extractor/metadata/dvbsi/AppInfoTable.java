package androidx.media3.extractor.metadata.dvbsi;

import a0.c;
import androidx.media3.common.Format;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.g;

/* JADX INFO: loaded from: classes3.dex */
public final class AppInfoTable implements Metadata.Entry {
    public static final int CONTROL_CODE_AUTOSTART = 1;
    public static final int CONTROL_CODE_PRESENT = 2;
    public final int controlCode;
    public final String url;

    public AppInfoTable(int i10, String str) {
        this.controlCode = i10;
        this.url = str;
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ byte[] getWrappedMetadataBytes() {
        return g.a(this);
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ Format getWrappedMetadataFormat() {
        return g.b(this);
    }

    @Override // androidx.media3.common.Metadata.Entry
    public final /* synthetic */ void populateMediaMetadata(MediaMetadata.Builder builder) {
        g.c(this, builder);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Ait(controlCode=");
        sb2.append(this.controlCode);
        sb2.append(",url=");
        return c.p(sb2, this.url, ")");
    }
}

package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.Format;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ Format a(Format format, Format format2) {
        return format2 != null ? format.withManifestFormatInfo(format2) : format;
    }
}

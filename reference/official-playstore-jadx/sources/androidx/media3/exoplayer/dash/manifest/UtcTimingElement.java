package androidx.media3.exoplayer.dash.manifest;

/* JADX INFO: loaded from: classes3.dex */
public final class UtcTimingElement {
    public final String schemeIdUri;
    public final String value;

    public UtcTimingElement(String str, String str2) {
        this.schemeIdUri = str;
        this.value = str2;
    }

    public String toString() {
        return this.schemeIdUri + ", " + this.value;
    }
}

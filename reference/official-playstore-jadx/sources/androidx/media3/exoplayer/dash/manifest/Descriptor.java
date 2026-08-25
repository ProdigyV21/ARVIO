package androidx.media3.exoplayer.dash.manifest;

import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class Descriptor {
    public final String id;
    public final String schemeIdUri;
    public final String value;

    public Descriptor(String str, String str2, String str3) {
        this.schemeIdUri = str;
        this.value = str2;
        this.id = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Descriptor.class == obj.getClass()) {
            Descriptor descriptor = (Descriptor) obj;
            if (Objects.equals(this.schemeIdUri, descriptor.schemeIdUri) && Objects.equals(this.value, descriptor.value) && Objects.equals(this.id, descriptor.id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.schemeIdUri.hashCode() * 31;
        String str = this.value;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.id;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }
}

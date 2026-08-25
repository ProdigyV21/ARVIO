package androidx.media3.exoplayer.dash.manifest;

import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class BaseUrl {
    public static final int DEFAULT_DVB_PRIORITY = 1;
    public static final int DEFAULT_WEIGHT = 1;
    public static final int PRIORITY_UNSET = Integer.MIN_VALUE;
    public final int priority;
    public final String serviceLocation;
    public final String url;
    public final int weight;

    public BaseUrl(String str) {
        this(str, str, Integer.MIN_VALUE, 1);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseUrl)) {
            return false;
        }
        BaseUrl baseUrl = (BaseUrl) obj;
        return this.priority == baseUrl.priority && this.weight == baseUrl.weight && Objects.equals(this.url, baseUrl.url) && Objects.equals(this.serviceLocation, baseUrl.serviceLocation);
    }

    public int hashCode() {
        return Objects.hash(this.url, this.serviceLocation, Integer.valueOf(this.priority), Integer.valueOf(this.weight));
    }

    public BaseUrl(String str, String str2, int i10, int i11) {
        this.url = str;
        this.serviceLocation = str2;
        this.priority = i10;
        this.weight = i11;
    }
}

package com.arflix.tv.data.repository;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/repository/StreamingServicesResult;", "", TtmlNode.TAG_REGION, "", "services", "", "Lcom/arflix/tv/data/repository/StreamingServiceInfo;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getRegion", "()Ljava/lang/String;", "getServices", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StreamingServicesResult {
    public static final int $stable = 0;
    private final String region;
    private final List<StreamingServiceInfo> services;

    public StreamingServicesResult(String str, List<StreamingServiceInfo> list) {
        this.region = str;
        this.services = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StreamingServicesResult copy$default(StreamingServicesResult streamingServicesResult, String str, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = streamingServicesResult.region;
        }
        if ((i10 & 2) != 0) {
            list = streamingServicesResult.services;
        }
        return streamingServicesResult.copy(str, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRegion() {
        return this.region;
    }

    public final List<StreamingServiceInfo> component2() {
        return this.services;
    }

    public final StreamingServicesResult copy(String region, List<StreamingServiceInfo> services) {
        return new StreamingServicesResult(region, services);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamingServicesResult)) {
            return false;
        }
        StreamingServicesResult streamingServicesResult = (StreamingServicesResult) other;
        return kotlin.jvm.internal.p.a(this.region, streamingServicesResult.region) && kotlin.jvm.internal.p.a(this.services, streamingServicesResult.services);
    }

    public final String getRegion() {
        return this.region;
    }

    public final List<StreamingServiceInfo> getServices() {
        return this.services;
    }

    public int hashCode() {
        return this.services.hashCode() + (this.region.hashCode() * 31);
    }

    public String toString() {
        return "StreamingServicesResult(region=" + this.region + ", services=" + this.services + ")";
    }
}

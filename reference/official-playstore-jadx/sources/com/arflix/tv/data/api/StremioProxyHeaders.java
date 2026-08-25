package com.arflix.tv.data.api;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\r\u001a\u00020\u00002\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0004HÖ\u0081\u0004R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001f\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/data/api/StremioProxyHeaders;", "", "request", "", "", "response", "<init>", "(Ljava/util/Map;Ljava/util/Map;)V", "getRequest", "()Ljava/util/Map;", "getResponse", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StremioProxyHeaders {
    public static final int $stable = 0;
    private final Map<String, String> request;
    private final Map<String, String> response;

    /* JADX WARN: Multi-variable type inference failed */
    public StremioProxyHeaders() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StremioProxyHeaders copy$default(StremioProxyHeaders stremioProxyHeaders, Map map, Map map2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            map = stremioProxyHeaders.request;
        }
        if ((i10 & 2) != 0) {
            map2 = stremioProxyHeaders.response;
        }
        return stremioProxyHeaders.copy(map, map2);
    }

    public final Map<String, String> component1() {
        return this.request;
    }

    public final Map<String, String> component2() {
        return this.response;
    }

    public final StremioProxyHeaders copy(Map<String, String> request, Map<String, String> response) {
        return new StremioProxyHeaders(request, response);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StremioProxyHeaders)) {
            return false;
        }
        StremioProxyHeaders stremioProxyHeaders = (StremioProxyHeaders) other;
        return p.a(this.request, stremioProxyHeaders.request) && p.a(this.response, stremioProxyHeaders.response);
    }

    public final Map<String, String> getRequest() {
        return this.request;
    }

    public final Map<String, String> getResponse() {
        return this.response;
    }

    public int hashCode() {
        Map<String, String> map = this.request;
        int iHashCode = (map == null ? 0 : map.hashCode()) * 31;
        Map<String, String> map2 = this.response;
        return iHashCode + (map2 != null ? map2.hashCode() : 0);
    }

    public String toString() {
        return "StremioProxyHeaders(request=" + this.request + ", response=" + this.response + ")";
    }

    public StremioProxyHeaders(Map<String, String> map, Map<String, String> map2) {
        this.request = map;
        this.response = map2;
    }

    public /* synthetic */ StremioProxyHeaders(Map map, Map map2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : map, (i10 & 2) != 0 ? null : map2);
    }
}

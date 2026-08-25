package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JQ\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\nHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/api/YouTubeClient;", "", "key", "", TtmlNode.ATTR_ID, "version", "userAgent", "context", "", "priority", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;I)V", "getKey", "()Ljava/lang/String;", "getId", "getVersion", "getUserAgent", "getContext", "()Ljava/util/Map;", "getPriority", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class YouTubeClient {
    private final Map<String, Object> context;
    private final String id;
    private final String key;
    private final int priority;
    private final String userAgent;
    private final String version;

    public YouTubeClient(String str, String str2, String str3, String str4, Map<String, ? extends Object> map, int i10) {
        this.key = str;
        this.id = str2;
        this.version = str3;
        this.userAgent = str4;
        this.context = map;
        this.priority = i10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ YouTubeClient copy$default(YouTubeClient youTubeClient, String str, String str2, String str3, String str4, Map map, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = youTubeClient.key;
        }
        if ((i11 & 2) != 0) {
            str2 = youTubeClient.id;
        }
        if ((i11 & 4) != 0) {
            str3 = youTubeClient.version;
        }
        if ((i11 & 8) != 0) {
            str4 = youTubeClient.userAgent;
        }
        if ((i11 & 16) != 0) {
            map = youTubeClient.context;
        }
        if ((i11 & 32) != 0) {
            i10 = youTubeClient.priority;
        }
        Map map2 = map;
        int i12 = i10;
        return youTubeClient.copy(str, str2, str3, str4, map2, i12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUserAgent() {
        return this.userAgent;
    }

    public final Map<String, Object> component5() {
        return this.context;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getPriority() {
        return this.priority;
    }

    public final YouTubeClient copy(String key, String id, String version, String userAgent, Map<String, ? extends Object> context, int priority) {
        return new YouTubeClient(key, id, version, userAgent, context, priority);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof YouTubeClient)) {
            return false;
        }
        YouTubeClient youTubeClient = (YouTubeClient) other;
        return p.a(this.key, youTubeClient.key) && p.a(this.id, youTubeClient.id) && p.a(this.version, youTubeClient.version) && p.a(this.userAgent, youTubeClient.userAgent) && p.a(this.context, youTubeClient.context) && this.priority == youTubeClient.priority;
    }

    public final Map<String, Object> getContext() {
        return this.context;
    }

    public final String getId() {
        return this.id;
    }

    public final String getKey() {
        return this.key;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final String getUserAgent() {
        return this.userAgent;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return a2.g(this.context, androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.key.hashCode() * 31, 31, this.id), 31, this.version), 31, this.userAgent), 31) + this.priority;
    }

    public String toString() {
        String str = this.key;
        String str2 = this.id;
        String str3 = this.version;
        String str4 = this.userAgent;
        Map<String, Object> map = this.context;
        int i10 = this.priority;
        StringBuilder sbR = a2.r("YouTubeClient(key=", str, ", id=", str2, ", version=");
        y.a.i(sbR, str3, ", userAgent=", str4, ", context=");
        sbR.append(map);
        sbR.append(", priority=");
        sbR.append(i10);
        sbR.append(")");
        return sbR.toString();
    }
}

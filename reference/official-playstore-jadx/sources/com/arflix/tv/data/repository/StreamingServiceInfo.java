package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/repository/StreamingServiceInfo;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "logoUrl", "<init>", "(ILjava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getLogoUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StreamingServiceInfo {
    public static final int $stable = 0;
    private final int id;
    private final String logoUrl;
    private final String name;

    public StreamingServiceInfo(int i10, String str, String str2) {
        this.id = i10;
        this.name = str;
        this.logoUrl = str2;
    }

    public static /* synthetic */ StreamingServiceInfo copy$default(StreamingServiceInfo streamingServiceInfo, int i10, String str, String str2, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = streamingServiceInfo.id;
        }
        if ((i11 & 2) != 0) {
            str = streamingServiceInfo.name;
        }
        if ((i11 & 4) != 0) {
            str2 = streamingServiceInfo.logoUrl;
        }
        return streamingServiceInfo.copy(i10, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    public final StreamingServiceInfo copy(int id, String name, String logoUrl) {
        return new StreamingServiceInfo(id, name, logoUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamingServiceInfo)) {
            return false;
        }
        StreamingServiceInfo streamingServiceInfo = (StreamingServiceInfo) other;
        return this.id == streamingServiceInfo.id && kotlin.jvm.internal.p.a(this.name, streamingServiceInfo.name) && kotlin.jvm.internal.p.a(this.logoUrl, streamingServiceInfo.logoUrl);
    }

    public final int getId() {
        return this.id;
    }

    public final String getLogoUrl() {
        return this.logoUrl;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.id * 31, 31, this.name);
        String str = this.logoUrl;
        return iC + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        int i10 = this.id;
        String str = this.name;
        return a0.c.p(a2.o("StreamingServiceInfo(id=", i10, ", name=", str, ", logoUrl="), this.logoUrl, ")");
    }

    public /* synthetic */ StreamingServiceInfo(int i10, String str, String str2, int i11, kotlin.jvm.internal.h hVar) {
        this(i10, str, (i11 & 4) != 0 ? null : str2);
    }
}

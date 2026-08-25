package com.arflix.tv.ui.screens.details;

import androidx.fragment.app.a2;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/ui/screens/details/StreamingServiceUi;", "", ContentDisposition.Parameters.Name, "", "logoUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getLogoUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StreamingServiceUi {
    public static final int $stable = 0;
    private final String logoUrl;
    private final String name;

    public StreamingServiceUi(String str, String str2) {
        this.name = str;
        this.logoUrl = str2;
    }

    public static /* synthetic */ StreamingServiceUi copy$default(StreamingServiceUi streamingServiceUi, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = streamingServiceUi.name;
        }
        if ((i10 & 2) != 0) {
            str2 = streamingServiceUi.logoUrl;
        }
        return streamingServiceUi.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    public final StreamingServiceUi copy(String name, String logoUrl) {
        return new StreamingServiceUi(name, logoUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamingServiceUi)) {
            return false;
        }
        StreamingServiceUi streamingServiceUi = (StreamingServiceUi) other;
        return kotlin.jvm.internal.p.a(this.name, streamingServiceUi.name) && kotlin.jvm.internal.p.a(this.logoUrl, streamingServiceUi.logoUrl);
    }

    public final String getLogoUrl() {
        return this.logoUrl;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        String str = this.logoUrl;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return a2.m("StreamingServiceUi(name=", this.name, ", logoUrl=", this.logoUrl, ")");
    }

    public /* synthetic */ StreamingServiceUi(String str, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2);
    }
}

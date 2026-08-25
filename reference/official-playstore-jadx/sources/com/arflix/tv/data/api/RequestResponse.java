package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0017\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/api/RequestResponse;", "", "ok", "", "status", "", "statusText", "", "url", TtmlNode.TAG_BODY, "<init>", "(ZILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getOk", "()Z", "getStatus", "()I", "getStatusText", "()Ljava/lang/String;", "getUrl", "getBody", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class RequestResponse {
    private final String body;
    private final boolean ok;
    private final int status;
    private final String statusText;
    private final String url;

    public RequestResponse(boolean z, int i10, String str, String str2, String str3) {
        this.ok = z;
        this.status = i10;
        this.statusText = str;
        this.url = str2;
        this.body = str3;
    }

    public static /* synthetic */ RequestResponse copy$default(RequestResponse requestResponse, boolean z, int i10, String str, String str2, String str3, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            z = requestResponse.ok;
        }
        if ((i11 & 2) != 0) {
            i10 = requestResponse.status;
        }
        if ((i11 & 4) != 0) {
            str = requestResponse.statusText;
        }
        if ((i11 & 8) != 0) {
            str2 = requestResponse.url;
        }
        if ((i11 & 16) != 0) {
            str3 = requestResponse.body;
        }
        String str4 = str3;
        String str5 = str;
        return requestResponse.copy(z, i10, str5, str2, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getOk() {
        return this.ok;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getStatusText() {
        return this.statusText;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getBody() {
        return this.body;
    }

    public final RequestResponse copy(boolean ok, int status, String statusText, String url, String body) {
        return new RequestResponse(ok, status, statusText, url, body);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RequestResponse)) {
            return false;
        }
        RequestResponse requestResponse = (RequestResponse) other;
        return this.ok == requestResponse.ok && this.status == requestResponse.status && p.a(this.statusText, requestResponse.statusText) && p.a(this.url, requestResponse.url) && p.a(this.body, requestResponse.body);
    }

    public final String getBody() {
        return this.body;
    }

    public final boolean getOk() {
        return this.ok;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getStatusText() {
        return this.statusText;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return this.body.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((((this.ok ? 1231 : 1237) * 31) + this.status) * 31, 31, this.statusText), 31, this.url);
    }

    public String toString() {
        boolean z = this.ok;
        int i10 = this.status;
        String str = this.statusText;
        String str2 = this.url;
        String str3 = this.body;
        StringBuilder sb2 = new StringBuilder("RequestResponse(ok=");
        sb2.append(z);
        sb2.append(", status=");
        sb2.append(i10);
        sb2.append(", statusText=");
        y.a.i(sb2, str, ", url=", str2, ", body=");
        return a0.c.p(sb2, str3, ")");
    }
}

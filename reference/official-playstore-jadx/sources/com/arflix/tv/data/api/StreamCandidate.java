package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\"\b\u0082\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\t\u0010\"\u001a\u00020\nHÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003Jc\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u0003HÆ\u0001J\u0014\u0010(\u001a\u00020\n2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010*\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010+\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012¨\u0006,"}, d2 = {"Lcom/arflix/tv/data/api/StreamCandidate;", "", "client", "", "priority", "", "url", "score", "", "hasN", "", "itag", "height", "fps", "ext", "<init>", "(Ljava/lang/String;ILjava/lang/String;DZLjava/lang/String;IILjava/lang/String;)V", "getClient", "()Ljava/lang/String;", "getPriority", "()I", "getUrl", "getScore", "()D", "getHasN", "()Z", "getItag", "getHeight", "getFps", "getExt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class StreamCandidate {
    private final String client;
    private final String ext;
    private final int fps;
    private final boolean hasN;
    private final int height;
    private final String itag;
    private final int priority;
    private final double score;
    private final String url;

    public StreamCandidate(String str, int i10, String str2, double d4, boolean z, String str3, int i11, int i12, String str4) {
        this.client = str;
        this.priority = i10;
        this.url = str2;
        this.score = d4;
        this.hasN = z;
        this.itag = str3;
        this.height = i11;
        this.fps = i12;
        this.ext = str4;
    }

    public static /* synthetic */ StreamCandidate copy$default(StreamCandidate streamCandidate, String str, int i10, String str2, double d4, boolean z, String str3, int i11, int i12, String str4, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            str = streamCandidate.client;
        }
        if ((i13 & 2) != 0) {
            i10 = streamCandidate.priority;
        }
        if ((i13 & 4) != 0) {
            str2 = streamCandidate.url;
        }
        if ((i13 & 8) != 0) {
            d4 = streamCandidate.score;
        }
        if ((i13 & 16) != 0) {
            z = streamCandidate.hasN;
        }
        if ((i13 & 32) != 0) {
            str3 = streamCandidate.itag;
        }
        if ((i13 & 64) != 0) {
            i11 = streamCandidate.height;
        }
        if ((i13 & 128) != 0) {
            i12 = streamCandidate.fps;
        }
        if ((i13 & 256) != 0) {
            str4 = streamCandidate.ext;
        }
        String str5 = str4;
        int i14 = i11;
        boolean z5 = z;
        double d10 = d4;
        String str6 = str2;
        return streamCandidate.copy(str, i10, str6, d10, z5, str3, i14, i12, str5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getClient() {
        return this.client;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getPriority() {
        return this.priority;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final double getScore() {
        return this.score;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getHasN() {
        return this.hasN;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getItag() {
        return this.itag;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getFps() {
        return this.fps;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getExt() {
        return this.ext;
    }

    public final StreamCandidate copy(String client, int priority, String url, double score, boolean hasN, String itag, int height, int fps, String ext) {
        return new StreamCandidate(client, priority, url, score, hasN, itag, height, fps, ext);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamCandidate)) {
            return false;
        }
        StreamCandidate streamCandidate = (StreamCandidate) other;
        return p.a(this.client, streamCandidate.client) && this.priority == streamCandidate.priority && p.a(this.url, streamCandidate.url) && Double.compare(this.score, streamCandidate.score) == 0 && this.hasN == streamCandidate.hasN && p.a(this.itag, streamCandidate.itag) && this.height == streamCandidate.height && this.fps == streamCandidate.fps && p.a(this.ext, streamCandidate.ext);
    }

    public final String getClient() {
        return this.client;
    }

    public final String getExt() {
        return this.ext;
    }

    public final int getFps() {
        return this.fps;
    }

    public final boolean getHasN() {
        return this.hasN;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getItag() {
        return this.itag;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final double getScore() {
        return this.score;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(((this.client.hashCode() * 31) + this.priority) * 31, 31, this.url);
        long jDoubleToLongBits = Double.doubleToLongBits(this.score);
        return this.ext.hashCode() + ((((androidx.compose.foundation.c.c((((iC + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)))) * 31) + (this.hasN ? 1231 : 1237)) * 31, 31, this.itag) + this.height) * 31) + this.fps) * 31);
    }

    public String toString() {
        String str = this.client;
        int i10 = this.priority;
        String str2 = this.url;
        double d4 = this.score;
        boolean z = this.hasN;
        String str3 = this.itag;
        int i11 = this.height;
        int i12 = this.fps;
        String str4 = this.ext;
        StringBuilder sbQ = a2.q("StreamCandidate(client=", str, ", priority=", i10, ", url=");
        sbQ.append(str2);
        sbQ.append(", score=");
        sbQ.append(d4);
        sbQ.append(", hasN=");
        sbQ.append(z);
        sbQ.append(", itag=");
        sbQ.append(str3);
        sbQ.append(", height=");
        sbQ.append(i11);
        sbQ.append(", fps=");
        sbQ.append(i12);
        return androidx.compose.material3.d.q(sbQ, ", ext=", str4, ")");
    }
}

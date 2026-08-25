package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/repository/IptvPlaybackTarget;", "", "url", "", "isHls", "", "<init>", "(Ljava/lang/String;Z)V", "getUrl", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IptvPlaybackTarget {
    public static final int $stable = 0;
    private final boolean isHls;
    private final String url;

    public IptvPlaybackTarget(String str, boolean z) {
        this.url = str;
        this.isHls = z;
    }

    public static /* synthetic */ IptvPlaybackTarget copy$default(IptvPlaybackTarget iptvPlaybackTarget, String str, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = iptvPlaybackTarget.url;
        }
        if ((i10 & 2) != 0) {
            z = iptvPlaybackTarget.isHls;
        }
        return iptvPlaybackTarget.copy(str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsHls() {
        return this.isHls;
    }

    public final IptvPlaybackTarget copy(String url, boolean isHls) {
        return new IptvPlaybackTarget(url, isHls);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IptvPlaybackTarget)) {
            return false;
        }
        IptvPlaybackTarget iptvPlaybackTarget = (IptvPlaybackTarget) other;
        return kotlin.jvm.internal.p.a(this.url, iptvPlaybackTarget.url) && this.isHls == iptvPlaybackTarget.isHls;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return (this.url.hashCode() * 31) + (this.isHls ? 1231 : 1237);
    }

    public final boolean isHls() {
        return this.isHls;
    }

    public String toString() {
        return "IptvPlaybackTarget(url=" + this.url + ", isHls=" + this.isHls + ")";
    }

    public /* synthetic */ IptvPlaybackTarget(String str, boolean z, int i10, kotlin.jvm.internal.h hVar) {
        this(str, (i10 & 2) != 0 ? false : z);
    }
}

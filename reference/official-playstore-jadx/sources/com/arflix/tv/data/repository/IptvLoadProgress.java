package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/repository/IptvLoadProgress;", "", "message", "", "percent", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;)V", "getMessage", "()Ljava/lang/String;", "getPercent", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Integer;)Lcom/arflix/tv/data/repository/IptvLoadProgress;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IptvLoadProgress {
    public static final int $stable = 0;
    private final String message;
    private final Integer percent;

    public IptvLoadProgress(String str, Integer num) {
        this.message = str;
        this.percent = num;
    }

    public static /* synthetic */ IptvLoadProgress copy$default(IptvLoadProgress iptvLoadProgress, String str, Integer num, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = iptvLoadProgress.message;
        }
        if ((i10 & 2) != 0) {
            num = iptvLoadProgress.percent;
        }
        return iptvLoadProgress.copy(str, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getPercent() {
        return this.percent;
    }

    public final IptvLoadProgress copy(String message, Integer percent) {
        return new IptvLoadProgress(message, percent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IptvLoadProgress)) {
            return false;
        }
        IptvLoadProgress iptvLoadProgress = (IptvLoadProgress) other;
        return kotlin.jvm.internal.p.a(this.message, iptvLoadProgress.message) && kotlin.jvm.internal.p.a(this.percent, iptvLoadProgress.percent);
    }

    public final String getMessage() {
        return this.message;
    }

    public final Integer getPercent() {
        return this.percent;
    }

    public int hashCode() {
        int iHashCode = this.message.hashCode() * 31;
        Integer num = this.percent;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "IptvLoadProgress(message=" + this.message + ", percent=" + this.percent + ")";
    }

    public /* synthetic */ IptvLoadProgress(String str, Integer num, int i10, kotlin.jvm.internal.h hVar) {
        this(str, (i10 & 2) != 0 ? null : num);
    }
}

package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/data/repository/TvDeviceAuthCompleteResult;", "", "ok", "", "message", "", "<init>", "(ZLjava/lang/String;)V", "getOk", "()Z", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TvDeviceAuthCompleteResult {
    public static final int $stable = 0;
    private final String message;
    private final boolean ok;

    public TvDeviceAuthCompleteResult(boolean z, String str) {
        this.ok = z;
        this.message = str;
    }

    public static /* synthetic */ TvDeviceAuthCompleteResult copy$default(TvDeviceAuthCompleteResult tvDeviceAuthCompleteResult, boolean z, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = tvDeviceAuthCompleteResult.ok;
        }
        if ((i10 & 2) != 0) {
            str = tvDeviceAuthCompleteResult.message;
        }
        return tvDeviceAuthCompleteResult.copy(z, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getOk() {
        return this.ok;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final TvDeviceAuthCompleteResult copy(boolean ok, String message) {
        return new TvDeviceAuthCompleteResult(ok, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TvDeviceAuthCompleteResult)) {
            return false;
        }
        TvDeviceAuthCompleteResult tvDeviceAuthCompleteResult = (TvDeviceAuthCompleteResult) other;
        return this.ok == tvDeviceAuthCompleteResult.ok && kotlin.jvm.internal.p.a(this.message, tvDeviceAuthCompleteResult.message);
    }

    public final String getMessage() {
        return this.message;
    }

    public final boolean getOk() {
        return this.ok;
    }

    public int hashCode() {
        int i10 = (this.ok ? 1231 : 1237) * 31;
        String str = this.message;
        return i10 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "TvDeviceAuthCompleteResult(ok=" + this.ok + ", message=" + this.message + ")";
    }

    public /* synthetic */ TvDeviceAuthCompleteResult(boolean z, String str, int i10, kotlin.jvm.internal.h hVar) {
        this(z, (i10 & 2) != 0 ? null : str);
    }
}

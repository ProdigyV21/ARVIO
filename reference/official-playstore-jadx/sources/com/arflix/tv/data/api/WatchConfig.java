package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/api/WatchConfig;", "", "apiKey", "", "visitorData", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getApiKey", "()Ljava/lang/String;", "getVisitorData", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class WatchConfig {
    private final String apiKey;
    private final String visitorData;

    public WatchConfig(String str, String str2) {
        this.apiKey = str;
        this.visitorData = str2;
    }

    public static /* synthetic */ WatchConfig copy$default(WatchConfig watchConfig, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = watchConfig.apiKey;
        }
        if ((i10 & 2) != 0) {
            str2 = watchConfig.visitorData;
        }
        return watchConfig.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getApiKey() {
        return this.apiKey;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getVisitorData() {
        return this.visitorData;
    }

    public final WatchConfig copy(String apiKey, String visitorData) {
        return new WatchConfig(apiKey, visitorData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchConfig)) {
            return false;
        }
        WatchConfig watchConfig = (WatchConfig) other;
        return p.a(this.apiKey, watchConfig.apiKey) && p.a(this.visitorData, watchConfig.visitorData);
    }

    public final String getApiKey() {
        return this.apiKey;
    }

    public final String getVisitorData() {
        return this.visitorData;
    }

    public int hashCode() {
        String str = this.apiKey;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.visitorData;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return a2.m("WatchConfig(apiKey=", this.apiKey, ", visitorData=", this.visitorData, ")");
    }
}

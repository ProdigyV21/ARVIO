package com.arflix.tv.domain.model;

import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/domain/model/RemotePluginInfo;", "", "url", "", "repoType", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "getRepoType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class RemotePluginInfo {
    public static final int $stable = 0;
    private final String repoType;
    private final String url;

    public RemotePluginInfo(String str, String str2) {
        this.url = str;
        this.repoType = str2;
    }

    public static /* synthetic */ RemotePluginInfo copy$default(RemotePluginInfo remotePluginInfo, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = remotePluginInfo.url;
        }
        if ((i10 & 2) != 0) {
            str2 = remotePluginInfo.repoType;
        }
        return remotePluginInfo.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRepoType() {
        return this.repoType;
    }

    public final RemotePluginInfo copy(String url, String repoType) {
        return new RemotePluginInfo(url, repoType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RemotePluginInfo)) {
            return false;
        }
        RemotePluginInfo remotePluginInfo = (RemotePluginInfo) other;
        return p.a(this.url, remotePluginInfo.url) && p.a(this.repoType, remotePluginInfo.repoType);
    }

    public final String getRepoType() {
        return this.repoType;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int iHashCode = this.url.hashCode() * 31;
        String str = this.repoType;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return a2.m("RemotePluginInfo(url=", this.url, ", repoType=", this.repoType, ")");
    }

    public /* synthetic */ RemotePluginInfo(String str, String str2, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2);
    }
}

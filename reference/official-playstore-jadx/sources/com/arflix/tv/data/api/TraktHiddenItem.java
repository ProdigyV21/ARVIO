package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/api/TraktHiddenItem;", "", "hiddenAt", "", LinkHeader.Parameters.Type, "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktShowInfo;)V", "getHiddenAt", "()Ljava/lang/String;", "getType", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktHiddenItem {
    public static final int $stable = 0;

    @SerializedName("hidden_at")
    private final String hiddenAt;
    private final TraktShowInfo show;
    private final String type;

    public TraktHiddenItem(String str, String str2, TraktShowInfo traktShowInfo) {
        this.hiddenAt = str;
        this.type = str2;
        this.show = traktShowInfo;
    }

    public static /* synthetic */ TraktHiddenItem copy$default(TraktHiddenItem traktHiddenItem, String str, String str2, TraktShowInfo traktShowInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktHiddenItem.hiddenAt;
        }
        if ((i10 & 2) != 0) {
            str2 = traktHiddenItem.type;
        }
        if ((i10 & 4) != 0) {
            traktShowInfo = traktHiddenItem.show;
        }
        return traktHiddenItem.copy(str, str2, traktShowInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getHiddenAt() {
        return this.hiddenAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final TraktHiddenItem copy(String hiddenAt, String type, TraktShowInfo show) {
        return new TraktHiddenItem(hiddenAt, type, show);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktHiddenItem)) {
            return false;
        }
        TraktHiddenItem traktHiddenItem = (TraktHiddenItem) other;
        return p.a(this.hiddenAt, traktHiddenItem.hiddenAt) && p.a(this.type, traktHiddenItem.type) && p.a(this.show, traktHiddenItem.show);
    }

    public final String getHiddenAt() {
        return this.hiddenAt;
    }

    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.hiddenAt;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.type;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        TraktShowInfo traktShowInfo = this.show;
        return iHashCode2 + (traktShowInfo != null ? traktShowInfo.hashCode() : 0);
    }

    public String toString() {
        String str = this.hiddenAt;
        String str2 = this.type;
        TraktShowInfo traktShowInfo = this.show;
        StringBuilder sbR = a2.r("TraktHiddenItem(hiddenAt=", str, ", type=", str2, ", show=");
        sbR.append(traktShowInfo);
        sbR.append(")");
        return sbR.toString();
    }
}

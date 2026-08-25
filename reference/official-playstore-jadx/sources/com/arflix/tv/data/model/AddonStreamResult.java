package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0010\b\u0002\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\u0011\u0010\u0017\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\nHÆ\u0003J?\u0010\u0018\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0010\b\u0002\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\nHÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0006HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0019\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/model/AddonStreamResult;", "Ljava/io/Serializable;", "streams", "", "Lcom/arflix/tv/data/model/StreamSource;", "addonId", "", "addonName", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V", "getStreams", "()Ljava/util/List;", "getAddonId", "()Ljava/lang/String;", "getAddonName", "getError", "()Ljava/lang/Exception;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AddonStreamResult implements Serializable {
    public static final int $stable = 0;
    private final String addonId;
    private final String addonName;
    private final Exception error;
    private final List<StreamSource> streams;

    public AddonStreamResult(List<StreamSource> list, String str, String str2, Exception exc) {
        this.streams = list;
        this.addonId = str;
        this.addonName = str2;
        this.error = exc;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AddonStreamResult copy$default(AddonStreamResult addonStreamResult, List list, String str, String str2, Exception exc, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = addonStreamResult.streams;
        }
        if ((i10 & 2) != 0) {
            str = addonStreamResult.addonId;
        }
        if ((i10 & 4) != 0) {
            str2 = addonStreamResult.addonName;
        }
        if ((i10 & 8) != 0) {
            exc = addonStreamResult.error;
        }
        return addonStreamResult.copy(list, str, str2, exc);
    }

    public final List<StreamSource> component1() {
        return this.streams;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAddonId() {
        return this.addonId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAddonName() {
        return this.addonName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Exception getError() {
        return this.error;
    }

    public final AddonStreamResult copy(List<StreamSource> streams, String addonId, String addonName, Exception error) {
        return new AddonStreamResult(streams, addonId, addonName, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonStreamResult)) {
            return false;
        }
        AddonStreamResult addonStreamResult = (AddonStreamResult) other;
        return p.a(this.streams, addonStreamResult.streams) && p.a(this.addonId, addonStreamResult.addonId) && p.a(this.addonName, addonStreamResult.addonName) && p.a(this.error, addonStreamResult.error);
    }

    public final String getAddonId() {
        return this.addonId;
    }

    public final String getAddonName() {
        return this.addonName;
    }

    public final Exception getError() {
        return this.error;
    }

    public final List<StreamSource> getStreams() {
        return this.streams;
    }

    public int hashCode() {
        int iC = c.c(c.c(this.streams.hashCode() * 31, 31, this.addonId), 31, this.addonName);
        Exception exc = this.error;
        return iC + (exc == null ? 0 : exc.hashCode());
    }

    public String toString() {
        return "AddonStreamResult(streams=" + this.streams + ", addonId=" + this.addonId + ", addonName=" + this.addonName + ", error=" + this.error + ")";
    }

    public /* synthetic */ AddonStreamResult(List list, String str, String str2, Exception exc, int i10, h hVar) {
        this(list, str, str2, (i10 & 8) != 0 ? null : exc);
    }
}

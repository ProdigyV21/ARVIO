package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/arflix/tv/data/api/DeviceCodeRequest;", "", "clientId", "", "<init>", "(Ljava/lang/String;)V", "getClientId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class DeviceCodeRequest {
    public static final int $stable = 0;

    @SerializedName("client_id")
    private final String clientId;

    public DeviceCodeRequest(String str) {
        this.clientId = str;
    }

    public static /* synthetic */ DeviceCodeRequest copy$default(DeviceCodeRequest deviceCodeRequest, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = deviceCodeRequest.clientId;
        }
        return deviceCodeRequest.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getClientId() {
        return this.clientId;
    }

    public final DeviceCodeRequest copy(String clientId) {
        return new DeviceCodeRequest(clientId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DeviceCodeRequest) && p.a(this.clientId, ((DeviceCodeRequest) other).clientId);
    }

    public final String getClientId() {
        return this.clientId;
    }

    public int hashCode() {
        return this.clientId.hashCode();
    }

    public String toString() {
        return a0.c.l("DeviceCodeRequest(clientId=", this.clientId, ")");
    }
}

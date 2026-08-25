package io.ktor.http;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lio/ktor/http/RangeUnits;", "", "unitToken", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getUnitToken", "()Ljava/lang/String;", "Bytes", "None", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public enum RangeUnits {
    Bytes("bytes"),
    None("none");

    private final String unitToken;

    RangeUnits(String str) {
        this.unitToken = str;
    }

    public final String getUnitToken() {
        return this.unitToken;
    }
}

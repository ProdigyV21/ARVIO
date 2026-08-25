package com.google.android.gms.common.api;

/* JADX INFO: loaded from: classes4.dex */
public class ApiException extends Exception {

    @Deprecated
    protected final Status mStatus;

    public ApiException(Status status) {
        int i10 = status.f12675i;
        String str = status.f12676l;
        str = str == null ? "" : str;
        StringBuilder sb2 = new StringBuilder(String.valueOf(i10).length() + 2 + String.valueOf(str).length());
        sb2.append(i10);
        sb2.append(": ");
        sb2.append(str);
        super(sb2.toString());
        this.mStatus = status;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public int getStatusCode() {
        return this.mStatus.f12675i;
    }

    @Deprecated
    public String getStatusMessage() {
        return this.mStatus.f12676l;
    }
}

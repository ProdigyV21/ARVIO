package com.google.android.gms.common.api;

/* JADX INFO: loaded from: classes4.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o3.d f12679i;

    public UnsupportedApiCallException(o3.d dVar) {
        this.f12679i = dVar;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        return "Missing ".concat(String.valueOf(this.f12679i));
    }
}

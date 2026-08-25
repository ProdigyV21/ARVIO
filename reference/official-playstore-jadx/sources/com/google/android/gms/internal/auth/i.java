package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class i implements com.google.android.gms.common.api.x {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13045i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Status f13046l;

    public /* synthetic */ i(Status status, int i10) {
        this.f13045i = i10;
        this.f13046l = status;
    }

    @Override // com.google.android.gms.common.api.x
    public final Status getStatus() {
        switch (this.f13045i) {
        }
        return this.f13046l;
    }
}

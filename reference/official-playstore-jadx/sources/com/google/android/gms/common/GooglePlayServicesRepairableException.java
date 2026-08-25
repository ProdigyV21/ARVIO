package com.google.android.gms.common;

import android.content.Intent;

/* JADX INFO: loaded from: classes4.dex */
public class GooglePlayServicesRepairableException extends UserRecoverableException {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f12664l;

    public GooglePlayServicesRepairableException(Intent intent, int i10) {
        super(intent);
        this.f12664l = i10;
    }
}

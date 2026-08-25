package com.google.android.gms.internal.cast;

import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class w2 implements CastRemoteDisplay.CastRemoteDisplaySessionResult {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Status f13661i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Display f13662l;

    public w2(Status status) {
        this.f13661i = status;
        this.f13662l = null;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionResult
    public final Display getPresentationDisplay() {
        return this.f13662l;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionResult, com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.f13661i;
    }

    public w2(Display display) {
        this.f13661i = Status.f12670o;
        this.f13662l = display;
    }
}

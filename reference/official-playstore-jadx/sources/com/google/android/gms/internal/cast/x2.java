package com.google.android.gms.internal.cast;

import android.hardware.display.VirtualDisplay;
import com.google.android.gms.cast.CastRemoteDisplayApi;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final class x2 implements CastRemoteDisplayApi {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Logger f13682d = new Logger("CastRemoteDisplayApiImpl");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.google.android.gms.common.api.i f13683a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public VirtualDisplay f13684b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final p2 f13685c = new p2(this);

    public x2(com.google.android.gms.common.api.i iVar) {
        this.f13683a = iVar;
    }

    public final /* synthetic */ void a() {
        VirtualDisplay virtualDisplay = this.f13684b;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                int displayId = virtualDisplay.getDisplay().getDisplayId();
                f13682d.d(androidx.compose.foundation.c.p(displayId, "releasing virtual display: ", new StringBuilder(String.valueOf(displayId).length() + 27)), new Object[0]);
            }
            virtualDisplay.release();
        }
        this.f13684b = null;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayApi
    public final com.google.android.gms.common.api.v startRemoteDisplay(com.google.android.gms.common.api.q qVar, String str) {
        f13682d.d("startRemoteDisplay", new Object[0]);
        return ((com.google.android.gms.common.api.internal.i0) qVar).f12756b.doWrite(new q2(this, qVar, str));
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayApi
    public final com.google.android.gms.common.api.v stopRemoteDisplay(com.google.android.gms.common.api.q qVar) {
        f13682d.d("stopRemoteDisplay", new Object[0]);
        return ((com.google.android.gms.common.api.internal.i0) qVar).f12756b.doWrite(new r2(this, qVar));
    }
}

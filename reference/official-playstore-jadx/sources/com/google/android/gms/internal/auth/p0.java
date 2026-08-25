package com.google.android.gms.internal.auth;

import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: loaded from: classes4.dex */
public final class p0 extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13088a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p0(int i10, Handler handler) {
        super(handler);
        this.f13088a = i10;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        switch (this.f13088a) {
            case 0:
                q0.f13096d.set(true);
                break;
            default:
                c1.f13006i.incrementAndGet();
                break;
        }
    }
}

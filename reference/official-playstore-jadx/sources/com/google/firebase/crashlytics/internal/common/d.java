package com.google.firebase.crashlytics.internal.common;

import com.google.android.gms.tasks.h;
import com.google.android.gms.tasks.i;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements com.google.android.gms.tasks.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14354i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ i f14355l;

    public /* synthetic */ d(int i10, i iVar) {
        this.f14354i = i10;
        this.f14355l = iVar;
    }

    @Override // com.google.android.gms.tasks.a
    public final Object then(h hVar) {
        switch (this.f14354i) {
            case 0:
                return Utils.lambda$race$0(this.f14355l, hVar);
            case 1:
                return Utils.lambda$callTask$2(this.f14355l, hVar);
            default:
                return Utils.lambda$race$1(this.f14355l, hVar);
        }
    }
}

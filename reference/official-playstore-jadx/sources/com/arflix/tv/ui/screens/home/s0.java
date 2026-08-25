package com.arflix.tv.ui.screens.home;

import androidx.compose.foundation.gestures.ScrollScope;
import com.arflix.tv.ui.screens.home.HomeScreenKt;
import com.arflix.tv.updater.AppUpdate;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class s0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9312i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9313l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9314m;

    public /* synthetic */ s0(Object obj, Object obj2, int i10) {
        this.f9312i = i10;
        this.f9313l = obj;
        this.f9314m = obj2;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9312i) {
            case 0:
                return HomeScreenKt.AnonymousClass2.invokeSuspend$lambda$0((kotlin.jvm.internal.h0) this.f9313l, (ScrollScope) this.f9314m, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            default:
                return HomeViewModel$downloadAppUpdate$1$result$1.invokeSuspend$lambda$0((HomeViewModel) this.f9313l, (AppUpdate) this.f9314m, ((Long) obj).longValue(), (Long) obj2);
        }
    }
}

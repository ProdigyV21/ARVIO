package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class q1 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11718i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f11719l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f11720m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f11721n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MutableState f11722o;

    public /* synthetic */ q1(boolean z, int i10, MutableIntState mutableIntState, MutableState mutableState) {
        this.f11718i = 0;
        this.f11719l = z;
        this.f11720m = i10;
        this.f11722o = mutableState;
        this.f11721n = mutableIntState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f11718i) {
            case 0:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$0$0(this.f11719l, this.f11720m, this.f11722o, this.f11721n);
            case 1:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$2$9$0(this.f11719l, this.f11720m, this.f11721n, this.f11722o);
            default:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$2$10$1$0(this.f11719l, this.f11720m, this.f11721n, this.f11722o);
        }
    }

    public /* synthetic */ q1(boolean z, int i10, MutableIntState mutableIntState, MutableState mutableState, int i11) {
        this.f11718i = i11;
        this.f11719l = z;
        this.f11720m = i10;
        this.f11721n = mutableIntState;
        this.f11722o = mutableState;
    }
}

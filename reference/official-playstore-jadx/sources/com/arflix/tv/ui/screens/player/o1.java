package com.arflix.tv.ui.screens.player;

import android.app.Activity;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import com.arflix.tv.util.DeviceType;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9677i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f9678l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f9679m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9680n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9681o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9682p;

    public /* synthetic */ o1(Object obj, Object obj2, Object obj3, MutableState mutableState, MutableState mutableState2, int i10) {
        this.f9677i = i10;
        this.f9680n = obj;
        this.f9681o = obj2;
        this.f9682p = obj3;
        this.f9678l = mutableState;
        this.f9679m = mutableState2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9677i) {
            case 0:
                return PlayerScreenKt$PlayerScreen$49$1$1.invokeSuspend$lambda$0((PointerInputScope) this.f9680n, (r7.l) this.f9681o, (State) this.f9682p, this.f9678l, this.f9679m, (Offset) obj);
            default:
                return PlayerScreenKt.PlayerScreen$lambda$283$0((DeviceType) this.f9680n, (androidx.lifecycle.y) this.f9681o, (Activity) this.f9682p, this.f9678l, this.f9679m, (DisposableEffectScope) obj);
        }
    }
}

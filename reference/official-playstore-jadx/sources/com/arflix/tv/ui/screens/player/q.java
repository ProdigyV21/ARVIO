package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.unit.IntSize;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class q implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9702i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f9703l;

    public /* synthetic */ q(MutableIntState mutableIntState, int i10) {
        this.f9702i = i10;
        this.f9703l = mutableIntState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9702i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$10$0$0(this.f9703l, ((Integer) obj).intValue());
            case 1:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$15$0(this.f9703l, ((Integer) obj).intValue());
            case 2:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$0$0(this.f9703l, ((Boolean) obj).booleanValue());
            case 3:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$20$0(this.f9703l, ((Boolean) obj).booleanValue());
            default:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$1$7$0(this.f9703l, (IntSize) obj);
        }
    }
}

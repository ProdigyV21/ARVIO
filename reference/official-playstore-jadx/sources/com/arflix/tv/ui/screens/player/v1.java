package com.arflix.tv.ui.screens.player;

import android.app.Activity;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusState;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.cast.CastManager;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.ui.screens.player.PlayerViewModel;
import com.arflix.tv.util.DeviceType;
import java.util.Map;
import ka.o2;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class v1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9780i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9781l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9782m;

    public /* synthetic */ v1(int i10, Object obj, boolean z, Object obj2) {
        this.f9780i = i10;
        this.f9782m = obj;
        this.f9781l = obj2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9780i) {
            case 0:
                return PlayerViewModel.C14121.invokeSuspend$lambda$1((PlayerViewModel) this.f9781l, (String) this.f9782m, (Double) obj);
            case 1:
                return PlayerScreenKt.PlayerScreen$lambda$16$0((Activity) this.f9781l, (DeviceType) this.f9782m, (DisposableEffectScope) obj);
            case 2:
                return PlayerScreenKt.PlayerScreen$lambda$17$0((CastManager) this.f9781l, (DeviceType) this.f9782m, (DisposableEffectScope) obj);
            case 3:
                return PlayerScreenKt.PlayerScreen$lambda$179$2((String) this.f9782m, (String) this.f9781l, (Map.Entry) obj);
            case 4:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$12$0((PlayerViewModel) this.f9781l, (State) this.f9782m, (StreamSource) obj);
            case 5:
                return PlayerScreenKt.PlayerIconButton_xI7PXdU$lambda$8$0((r7.l) this.f9781l, (MutableState) this.f9782m, (FocusState) obj);
            case 6:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$11$0$0((MutableIntState) this.f9781l, (MutableIntState) this.f9782m, ((Integer) obj).intValue());
            case 7:
                return PlayerScreenKt.PlayerScreen$lambda$315$0((AiSubtitleRenderersFactory) this.f9782m, (PlayerViewModel) this.f9781l, (DisposableEffectScope) obj);
            case 8:
                return PlayerScreenKt.PlayerScreen$lambda$352$0((ExoPlayer) this.f9781l, (State) this.f9782m, (DisposableEffectScope) obj);
            default:
                return PlayerViewModel.saveProgress$lambda$0((PlayerViewModel) this.f9781l, (o2) this.f9782m, (Throwable) obj);
        }
    }

    public /* synthetic */ v1(Object obj, Object obj2, int i10) {
        this.f9780i = i10;
        this.f9781l = obj;
        this.f9782m = obj2;
    }
}

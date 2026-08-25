package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.model.IptvProgram;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11337i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11338l;

    public /* synthetic */ d(Object obj, int i10) {
        this.f11337i = i10;
        this.f11338l = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11337i) {
            case 0:
                return CategorySidebarKt.CategorySidebar$lambda$38$5$0((List) this.f11338l, ((Integer) obj).intValue());
            case 1:
                return Boolean.valueOf(FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$8$1((MutableLongState) this.f11338l, (IptvProgram) obj));
            case 2:
                return LiveTvScreenKt.LiveTvScreen$lambda$84$0((ka.v1[]) this.f11338l, (DisposableEffectScope) obj);
            default:
                return SearchOverlayKt.SearchOverlay$lambda$16$1$0$0$0((FocusRequester) this.f11338l, (KeyboardActionScope) obj);
        }
    }
}

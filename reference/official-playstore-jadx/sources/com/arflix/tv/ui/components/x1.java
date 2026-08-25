package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt;
import com.arflix.tv.ui.screens.search.SearchScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class x1 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8490i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8491l;

    public /* synthetic */ x1(Object obj, int i10) {
        this.f8490i = i10;
        this.f8491l = obj;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f8490i) {
            case 0:
                return ProfileIndicatorKt.ProfileIndicator$lambda$6((Profile) this.f8491l, (BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 1:
                return SourceInfoOverlayKt.SourceInfoOverlay$lambda$0((StreamSource) this.f8491l, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 2:
                return ProfileSelectionScreenKt.CloudConnectButton$lambda$6((MutableIntState) this.f8491l, (BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            default:
                return SearchScreenKt.SearchInputBar_yZaabhs$lambda$6$0$1((String) this.f8491l, (r7.p) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    }
}

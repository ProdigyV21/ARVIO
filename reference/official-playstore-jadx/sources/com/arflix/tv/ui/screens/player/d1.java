package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.Subtitle;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d1 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9432i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f9433l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Serializable f9434m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9435n;

    public /* synthetic */ d1(Subtitle subtitle, boolean z, r7.l lVar) {
        this.f9434m = subtitle;
        this.f9433l = z;
        this.f9435n = lVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f9432i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return PlayerScreenKt.SubtitleMenu$lambda$13$2$2$0$0((Subtitle) this.f9434m, this.f9433l, (r7.l) this.f9435n, (LazyItemScope) obj, (Composer) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return SettingsScreenKt.AiModelDialog$lambda$1$0$0$0$3(this.f9433l, (String) this.f9434m, (String) this.f9435n, (BoxScope) obj, (Composer) obj2, iIntValue2);
        }
    }

    public /* synthetic */ d1(String str, String str2, boolean z) {
        this.f9433l = z;
        this.f9434m = str;
        this.f9435n = str2;
    }
}

package com.arflix.tv.data.repository;

import android.content.Context;
import android.widget.EditText;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import com.arflix.tv.data.api.StremioMetaPreview;
import com.arflix.tv.ui.components.TextInputModalKt;
import com.arflix.tv.ui.motion.ArvioBackMotion;
import com.arflix.tv.ui.motion.ArvioPredictiveBackKt;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7630i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f7631l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7632m;

    public /* synthetic */ c0(int i10, Object obj, boolean z) {
        this.f7630i = i10;
        this.f7631l = z;
        this.f7632m = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7630i) {
            case 0:
                return Boolean.valueOf(SportsRepository.loadSportItems$lambda$0(this.f7631l, (SportsRepository) this.f7632m, (StremioMetaPreview) obj));
            case 1:
                return TextInputModalKt.TextInputModal$lambda$17$1$0$0$1$0(this.f7631l, (Context) this.f7632m, (EditText) obj);
            default:
                return ArvioPredictiveBackKt.arvioBackPeek$lambda$0(this.f7631l, (ArvioBackMotion) this.f7632m, (GraphicsLayerScope) obj);
        }
    }
}

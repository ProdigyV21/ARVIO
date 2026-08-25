package com.arflix.tv.ui.components;

import androidx.compose.foundation.ScrollState;
import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.PersonDetails;
import com.arflix.tv.ui.screens.player.SubtitleAiModel;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class k1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8130i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f8131l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8132m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8133n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Serializable f8134o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8135p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8136q;

    public /* synthetic */ k1(PersonDetails personDetails, ScrollState scrollState, int i10, r7.p pVar, r7.a aVar, int i11) {
        this.f8134o = personDetails;
        this.f8135p = scrollState;
        this.f8132m = i10;
        this.f8136q = pVar;
        this.f8131l = aVar;
        this.f8133n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8130i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return PersonModalKt.MobilePersonContent$lambda$1((PersonDetails) this.f8134o, (ScrollState) this.f8135p, this.f8132m, (r7.p) this.f8136q, this.f8131l, this.f8133n, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.AiApiKeyDialog$lambda$7((String) this.f8134o, (r7.l) this.f8135p, this.f8131l, (SubtitleAiModel) this.f8136q, this.f8132m, this.f8133n, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ k1(String str, r7.l lVar, r7.a aVar, SubtitleAiModel subtitleAiModel, int i10, int i11) {
        this.f8134o = str;
        this.f8135p = lVar;
        this.f8131l = aVar;
        this.f8136q = subtitleAiModel;
        this.f8132m = i10;
        this.f8133n = i11;
    }
}

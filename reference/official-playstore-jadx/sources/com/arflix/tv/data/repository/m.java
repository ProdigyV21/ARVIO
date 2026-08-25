package com.arflix.tv.data.repository;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.screens.player.SubtitleAiModel;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7664i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f7665l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Serializable f7666m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7667n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f7668o;

    public /* synthetic */ m(MediaType mediaType, int i10, Integer num, Integer num2) {
        this.f7665l = i10;
        this.f7666m = mediaType;
        this.f7667n = num;
        this.f7668o = num2;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7664i) {
            case 0:
                return IptvRepository.fetchXtreamShortEpg$lambda$12((kotlin.jvm.internal.i0) this.f7666m, (kotlin.jvm.internal.i0) this.f7667n, this.f7665l, (r7.l) this.f7668o, ((Integer) obj).intValue(), ((Boolean) obj2).booleanValue());
            case 1:
                return TraktRepository.removeFromContinueWatchingCache$lambda$1(this.f7665l, (MediaType) this.f7666m, (Integer) this.f7667n, (Integer) this.f7668o, (String) obj, (List) obj2);
            default:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.AiModelDialog$lambda$2((SubtitleAiModel) this.f7666m, (r7.l) this.f7668o, (r7.a) this.f7667n, this.f7665l, (Composer) obj, iIntValue);
        }
    }

    public /* synthetic */ m(SubtitleAiModel subtitleAiModel, r7.l lVar, r7.a aVar, int i10) {
        this.f7666m = subtitleAiModel;
        this.f7668o = lVar;
        this.f7667n = aVar;
        this.f7665l = i10;
    }

    public /* synthetic */ m(kotlin.jvm.internal.i0 i0Var, kotlin.jvm.internal.i0 i0Var2, int i10, r7.l lVar) {
        this.f7666m = i0Var;
        this.f7667n = i0Var2;
        this.f7665l = i10;
        this.f7668o = lVar;
    }
}

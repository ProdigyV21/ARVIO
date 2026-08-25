package com.arflix.tv.ui.screens.player;

import com.arflix.tv.data.model.StreamSource;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class x1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9808i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PlayerViewModel f9809l;

    public /* synthetic */ x1(PlayerViewModel playerViewModel, int i10) {
        this.f9808i = i10;
        this.f9809l = playerViewModel;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9808i) {
            case 0:
                return PlayerViewModel.access$streamDiag(this.f9809l, (StreamSource) obj);
            case 1:
                return PlayerViewModel.filterSubsByPreferredLanguage$matchesLang$lambda$4$0(this.f9809l, (kotlin.text.i) obj);
            case 2:
                return PlayerViewModel.pendingSubtitleAddonsReporter$lambda$0(this.f9809l, (List) obj);
            case 3:
                return PlayerViewModel.runFindBestMatch$lambda$0(this.f9809l, (Double) obj);
            default:
                return PlayerViewModel.translationManager$lambda$2$0(this.f9809l, ((Boolean) obj).booleanValue());
        }
    }
}

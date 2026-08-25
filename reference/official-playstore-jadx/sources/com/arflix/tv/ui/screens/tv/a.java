package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.IptvChannel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11183i;

    public /* synthetic */ a(int i10) {
        this.f11183i = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f11183i) {
            case 0:
                return ComposableSingletons$TvScreenKt.lambda_2056658595$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 1:
                return ComposableSingletons$TvScreenKt.lambda__1162273427$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 2:
                return ComposableSingletons$TvScreenKt.lambda_821190870$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 3:
                return TvScreenKt.CategoryRail$lambda$8$0$0(((Integer) obj).intValue(), (String) obj2);
            default:
                return TvScreenKt.GuidePanel$lambda$5$1$0$0(((Integer) obj).intValue(), (IptvChannel) obj2);
        }
    }
}

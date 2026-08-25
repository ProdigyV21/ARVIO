package com.arflix.tv.ui.screens.player;

import com.arflix.tv.ui.skin.ArvioSkinKt;
import com.arflix.tv.ui.theme.ThemeKt;
import com.arflix.tv.util.AppLanguageKt;
import com.arflix.tv.util.DeviceType;
import com.arflix.tv.util.DeviceTypeKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9431i;

    public /* synthetic */ d0(int i10) {
        this.f9431i = i10;
    }

    @Override // r7.a
    public final Object invoke() {
        boolean zLocalHasTouchScreen$lambda$0;
        switch (this.f9431i) {
            case 0:
                return x6.t0.f22605a;
            case 1:
                return x6.t0.f22605a;
            case 2:
                return x6.t0.f22605a;
            case 3:
                return x6.t0.f22605a;
            case 4:
                return x6.t0.f22605a;
            case 5:
                return x6.t0.f22605a;
            case 6:
                return x6.t0.f22605a;
            case 7:
                return SubtitleAiModel.GROQ_LLAMA_70B;
            case 8:
                return x6.t0.f22605a;
            case 9:
                return x6.t0.f22605a;
            case 10:
                return x6.t0.f22605a;
            case 11:
                return x6.t0.f22605a;
            case 12:
                return x6.t0.f22605a;
            case 13:
                return x6.t0.f22605a;
            case 14:
                return x6.t0.f22605a;
            case 15:
                return x6.t0.f22605a;
            case 16:
                return x6.t0.f22605a;
            case 17:
                return x6.t0.f22605a;
            case 18:
                return ArvioSkinKt.LocalArvioSkinTokens$lambda$0();
            case 19:
                return AppLanguageKt.LocalAppLanguage$lambda$0();
            case 20:
                return DeviceType.TV;
            case 21:
                zLocalHasTouchScreen$lambda$0 = DeviceTypeKt.LocalHasTouchScreen$lambda$0();
                break;
            case 22:
                return x6.t0.f22605a;
            case 23:
                return x6.t0.f22605a;
            case 24:
                return x6.t0.f22605a;
            case 25:
                return ThemeKt.LocalArvioColors$lambda$0();
            default:
                zLocalHasTouchScreen$lambda$0 = ThemeKt.LocalOledBlackBackground$lambda$0();
                break;
        }
        return Boolean.valueOf(zLocalHasTouchScreen$lambda$0);
    }
}

package com.arflix.tv.ui.components;

import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.ui.screens.login.LoginScreenKt;
import com.arflix.tv.ui.screens.login.LoginViewModel;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsUiState;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.LiveTvEnhancementsKt;
import com.arflix.tv.ui.screens.tv.live.MiniPlayerKt;
import com.arflix.tv.ui.screens.tv.live.PlaybackDiagnostic;
import com.arflix.tv.ui.screens.tv.live.QuickZapOverlayKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import com.arflix.tv.ui.skin.ArvioSkinKt;
import com.arflix.tv.ui.skin.ArvioSkinTokens;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8263i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8264l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8265m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8266n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8267o;

    public /* synthetic */ p1(int i10, EnrichedChannel enrichedChannel, IptvNowNext iptvNowNext, int i11) {
        this.f8263i = 7;
        this.f8265m = i10;
        this.f8264l = enrichedChannel;
        this.f8267o = iptvNowNext;
        this.f8266n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8263i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return PremiumEffectsKt.AnimatedGradientBackground$lambda$2((Modifier) this.f8264l, (r7.q) this.f8267o, this.f8265m, this.f8266n, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SkeletonLoaderKt.SkeletonBox$lambda$0((Modifier) this.f8264l, (RoundedCornerShape) this.f8267o, this.f8265m, this.f8266n, (Composer) obj, iIntValue2);
            case 2:
                int iIntValue3 = ((Integer) obj2).intValue();
                return LoginScreenKt.LoginScreen$lambda$24((LoginViewModel) this.f8264l, (r7.a) this.f8267o, this.f8265m, this.f8266n, (Composer) obj, iIntValue3);
            case 3:
                int iIntValue4 = ((Integer) obj2).intValue();
                return SettingsScreenKt.TvSettingsSectionHeader$lambda$1((String) this.f8264l, (SettingsUiState) this.f8267o, this.f8265m, this.f8266n, (Composer) obj, iIntValue4);
            case 4:
                int iIntValue5 = ((Integer) obj2).intValue();
                return TelegramSettingsScreenKt.TelegramSettingsScreen$lambda$6((r7.a) this.f8264l, (TelegramSettingsViewModel) this.f8267o, this.f8265m, this.f8266n, (Composer) obj, iIntValue5);
            case 5:
                int iIntValue6 = ((Integer) obj2).intValue();
                return LiveTvEnhancementsKt.PlaybackDiagnosticBanner$lambda$1((PlaybackDiagnostic) this.f8267o, (Modifier) this.f8264l, this.f8265m, this.f8266n, (Composer) obj, iIntValue6);
            case 6:
                int iIntValue7 = ((Integer) obj2).intValue();
                return MiniPlayerKt.ChannelIdentityRow$lambda$1((EnrichedChannel) this.f8264l, this.f8265m, (r7.a) this.f8267o, this.f8266n, (Composer) obj, iIntValue7);
            case 7:
                int iIntValue8 = ((Integer) obj2).intValue();
                return QuickZapOverlayKt.NonFocusedChannelSlot$lambda$1(this.f8265m, (EnrichedChannel) this.f8264l, (IptvNowNext) this.f8267o, this.f8266n, (Composer) obj, iIntValue8);
            case 8:
                int iIntValue9 = ((Integer) obj2).intValue();
                return WatchlistScreenKt.MobileLibrarySelector$lambda$4((List) this.f8264l, this.f8265m, (r7.p) this.f8267o, this.f8266n, (Composer) obj, iIntValue9);
            default:
                int iIntValue10 = ((Integer) obj2).intValue();
                return ArvioSkinKt.ProvideArvioSkin$lambda$0((ArvioSkinTokens) this.f8264l, (r7.p) this.f8267o, this.f8265m, this.f8266n, (Composer) obj, iIntValue10);
        }
    }

    public /* synthetic */ p1(PlaybackDiagnostic playbackDiagnostic, Modifier modifier, int i10, int i11) {
        this.f8263i = 5;
        this.f8267o = playbackDiagnostic;
        this.f8264l = modifier;
        this.f8265m = i10;
        this.f8266n = i11;
    }

    public /* synthetic */ p1(Object obj, int i10, x6.o oVar, int i11, int i12) {
        this.f8263i = i12;
        this.f8264l = obj;
        this.f8265m = i10;
        this.f8267o = oVar;
        this.f8266n = i11;
    }

    public /* synthetic */ p1(Object obj, Object obj2, int i10, int i11, int i12) {
        this.f8263i = i12;
        this.f8264l = obj;
        this.f8267o = obj2;
        this.f8265m = i10;
        this.f8266n = i11;
    }
}

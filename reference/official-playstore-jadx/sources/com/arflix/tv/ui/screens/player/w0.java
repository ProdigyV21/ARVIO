package com.arflix.tv.ui.screens.player;

import androidx.compose.ui.focus.FocusProperties;
import androidx.media3.common.MediaItem;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.model.Subtitle;
import com.arflix.tv.ui.screens.plugin.PluginScreenKt;
import com.arflix.tv.ui.screens.profile.ProfileDialogsKt;
import com.arflix.tv.ui.skin.ArvioFocusKt;
import com.arflix.tv.util.AppLoggerKt;
import com.arflix.tv.util.ContinueWatchingSelector;
import com.arflix.tv.util.CrashReportFilter;
import com.arflix.tv.util.EpisodeProgressSnapshot;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9787i;

    public /* synthetic */ w0(int i10) {
        this.f9787i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9787i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$35$0(((Boolean) obj).booleanValue());
            case 1:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$40$0(((Boolean) obj).booleanValue());
            case 2:
                return PlayerScreenKt.buildExternalSubtitleConfigurations$lambda$1((Subtitle) obj);
            case 3:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$5$0(((Boolean) obj).booleanValue());
            case 4:
                return PlayerScreenKt.buildExternalSubtitleConfigurations$lambda$2((MediaItem.SubtitleConfiguration) obj);
            case 5:
                return PlayerScreenKt.PlayerScreen$lambda$179$3((Map.Entry) obj);
            case 6:
                return ((kotlin.text.i) obj).getValue();
            case 7:
                return PlayerViewModel.prewarmTopStreams$lambda$0((StreamSource) obj);
            case 8:
                return Boolean.valueOf(PlayerViewModel.filterSubsByPreferredLanguage$matchesLang$lambda$4$1((String) obj));
            case 9:
                return ((kotlin.text.i) obj).getValue();
            case 10:
                return SubtitleTranslationService.extractJsonArray$lambda$0((kotlin.text.i) obj);
            case 11:
                return PluginScreenKt.FocusableSettingsRow$lambda$0$0((FocusProperties) obj);
            case 12:
                return PluginScreenKt.PluginScreen$lambda$0$0(((Integer) obj).intValue());
            case 13:
                return PluginScreenKt.PluginScreen$lambda$20$6$0((FocusProperties) obj);
            case 14:
                return PluginScreenKt.PluginScreen$lambda$1$0(((Integer) obj).intValue());
            case 15:
                return PluginScreenKt.PluginScreen$lambda$3$0(((Boolean) obj).booleanValue());
            case 16:
                return PluginScreenKt.PluginScreen$lambda$20$1$0((FocusProperties) obj);
            case 17:
                return PluginScreenKt.FocusableSettingsToggleRow$lambda$0$0((FocusProperties) obj);
            case 18:
                return ProfileDialogsKt.EditProfileDialog$lambda$0$0(((Integer) obj).intValue());
            case 19:
                return ProfileDialogsKt.EditProfileDialog$lambda$1$0((String) obj);
            case 20:
                return ProfileDialogsKt.AddProfileDialog$lambda$0$0(((Integer) obj).intValue());
            case 21:
                return ProfileDialogsKt.AddProfileDialog$lambda$1$0((String) obj);
            case 22:
                return ArvioFocusKt.ArvioFocusableSurface_aewEPWQ$lambda$0$0(((Boolean) obj).booleanValue());
            case 23:
                return ArvioFocusKt.arvioFocusable_gqPShRU$lambda$0(((Boolean) obj).booleanValue());
            case 24:
                return AppLoggerKt.hash$lambda$0(((Byte) obj).byteValue());
            case 25:
                return ContinueWatchingSelector.selectNextEpisodeAfterLastWatched$lambda$1((EpisodeProgressSnapshot) obj);
            case 26:
                return ContinueWatchingSelector.selectNextEpisodeAfterLastWatched$lambda$2((EpisodeProgressSnapshot) obj);
            case 27:
                return Boolean.valueOf(CrashReportFilter.containsIgnoredMessage$lambda$0((Throwable) obj));
            case 28:
                return CrashReportFilter.handledEventFingerprint$lambda$0((Throwable) obj);
            default:
                return CrashReportFilter.handledEventFingerprint$lambda$3(((Byte) obj).byteValue());
        }
    }
}

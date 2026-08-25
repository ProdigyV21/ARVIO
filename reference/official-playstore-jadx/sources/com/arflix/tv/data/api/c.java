package com.arflix.tv.data.api;

import android.content.Context;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.ui.focus.FocusProperties;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.model.Subtitle;
import com.arflix.tv.ui.components.ContextAction;
import com.arflix.tv.ui.components.ContextMenuKt;
import com.arflix.tv.ui.components.KeepScreenOnKt;
import com.arflix.tv.ui.components.MediaContextMenuKt;
import com.arflix.tv.ui.components.NextEpisodeOverlayKt;
import com.arflix.tv.ui.components.PersonModalKt;
import com.arflix.tv.ui.components.SidebarItem;
import com.arflix.tv.ui.components.SidebarKt;
import com.arflix.tv.ui.components.StreamSelectorKt;
import com.arflix.tv.ui.components.ToastKt;
import com.arflix.tv.ui.components.TrailerPlayerKt;
import com.arflix.tv.ui.screens.player.PlayerScreenKt;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7613i;

    public /* synthetic */ c(int i10) {
        this.f7613i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7613i) {
            case 0:
                return InAppYouTubeExtractor.parseHlsManifest$lambda$0((String) obj);
            case 1:
                return Boolean.valueOf(InAppYouTubeExtractor.parseHlsManifest$lambda$1((String) obj));
            case 2:
                return Integer.valueOf(ContextMenuKt.ContextMenu$lambda$9$3$0$0(((Integer) obj).intValue()));
            case 3:
                return Integer.valueOf(ContextMenuKt.ContextMenu$lambda$9$3$1$0(((Integer) obj).intValue()));
            case 4:
                return ContextMenuKt.ContextMenu$lambda$0$0((ContextAction) obj);
            case 5:
                return KeepScreenOnKt.KeepScreenOn$lambda$0$0$1((Context) obj);
            case 6:
                return Integer.valueOf(MediaContextMenuKt.MediaContextMenu$lambda$8$3$0$0(((Integer) obj).intValue()));
            case 7:
                return Integer.valueOf(MediaContextMenuKt.MediaContextMenu$lambda$8$3$1$0(((Integer) obj).intValue()));
            case 8:
                return Integer.valueOf(NextEpisodeOverlayKt.NextEpisodeOverlay$lambda$16$0(((Integer) obj).intValue()));
            case 9:
                return Integer.valueOf(NextEpisodeOverlayKt.NextEpisodeOverlay$lambda$17$0(((Integer) obj).intValue()));
            case 10:
                return Integer.valueOf(PersonModalKt.PersonModal$lambda$9$0(((Integer) obj).intValue()));
            case 11:
                return Integer.valueOf(PersonModalKt.PersonModal$lambda$8$0(((Integer) obj).intValue()));
            case 12:
                return SidebarKt.Sidebar$lambda$1$0((SidebarItem) obj);
            case 13:
                return StreamSelectorKt.AddonRailItem$lambda$0$0((FocusProperties) obj);
            case 14:
                return StreamSelectorKt.cleanStreamDescription$lambda$2((String) obj);
            case 15:
                return StreamSelectorKt.StreamSelector$lambda$0$0((StreamSource) obj);
            case 16:
                return StreamSelectorKt.StreamSelector$lambda$1$0((StreamSource) obj);
            case 17:
                return Integer.valueOf(StreamSelectorKt.StreamSelector$lambda$42$0(((Integer) obj).intValue()));
            case 18:
                return Integer.valueOf(StreamSelectorKt.StreamSelector$lambda$43$0(((Integer) obj).intValue()));
            case 19:
                return StreamSelectorKt.OledSourceRow$lambda$0$0((FocusProperties) obj);
            case 20:
                return Integer.valueOf(ToastKt.Toast$lambda$5$0$0(((Integer) obj).intValue()));
            case 21:
                return Integer.valueOf(ToastKt.Toast$lambda$5$1$0(((Integer) obj).intValue()));
            case 22:
                return TrailerPlayerKt.TrailerPlayer$lambda$0$0(((Boolean) obj).booleanValue());
            case 23:
                return PlayerScreenKt.PlayerScreen$lambda$18$0((DisposableEffectScope) obj);
            case 24:
                return PlayerScreenKt.PulsingLogo$lambda$0$0((KeyframesSpec.KeyframesSpecConfig) obj);
            case 25:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$10$0(((Boolean) obj).booleanValue());
            case 26:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$15$0(((Boolean) obj).booleanValue());
            case 27:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$26$0(((Boolean) obj).booleanValue());
            case 28:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$31$0(((Boolean) obj).booleanValue());
            default:
                return Boolean.valueOf(PlayerScreenKt.buildExternalSubtitleConfigurations$lambda$0((Subtitle) obj));
        }
    }
}

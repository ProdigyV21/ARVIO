package com.arflix.tv.ui.screens.settings;

import com.arflix.tv.data.repository.IptvConfig;
import com.arflix.tv.data.repository.IptvPlaylistEntry;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"syncSignature", "", "Lcom/arflix/tv/data/repository/IptvConfig;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SettingsViewModelKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String syncSignature(IptvConfig iptvConfig) {
        return kotlin.collections.x.u0(t7.a.E(iptvConfig.getM3uUrl(), iptvConfig.getEpgUrl(), iptvConfig.getStalkerPortalUrl(), iptvConfig.getStalkerMacAddress(), kotlin.collections.x.u0(iptvConfig.getPlaylists(), "|", null, null, new o2(18), 30)), "||", null, null, null, 62);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence syncSignature$lambda$0(IptvPlaylistEntry iptvPlaylistEntry) {
        String id = iptvPlaylistEntry.getId();
        String name = iptvPlaylistEntry.getName();
        String m3uUrl = iptvPlaylistEntry.getM3uUrl();
        String epgUrl = iptvPlaylistEntry.getEpgUrl();
        List<String> epgUrls = iptvPlaylistEntry.getEpgUrls();
        if (epgUrls == null) {
            epgUrls = kotlin.collections.z.f19728i;
        }
        return kotlin.collections.x.u0(t7.a.E(id, name, m3uUrl, epgUrl, kotlin.collections.x.u0(epgUrls, ",", null, null, null, 62), String.valueOf(iptvPlaylistEntry.getEnabled())), "~", null, null, null, 62);
    }
}

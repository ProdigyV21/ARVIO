package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.data.repository.IptvConfig;
import com.arflix.tv.data.repository.IptvPlaylistEntry;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveTvGuideSources;", "", "<init>", "()V", "LARGE_LIST_CHANNEL_COUNT", "", "hasXmltvSource", "", "config", "Lcom/arflix/tv/data/repository/IptvConfig;", "allowsFullGuideBackfill", "channelCount", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LiveTvGuideSources {
    public static final int $stable = 0;
    public static final LiveTvGuideSources INSTANCE = new LiveTvGuideSources();
    public static final int LARGE_LIST_CHANNEL_COUNT = 10000;

    private LiveTvGuideSources() {
    }

    public final boolean allowsFullGuideBackfill(IptvConfig config, int channelCount) {
        if (channelCount < 10000) {
            return true;
        }
        return hasXmltvSource(config);
    }

    public final boolean hasXmltvSource(IptvConfig config) {
        if (!kotlin.text.o.h0(config.getEpgUrl())) {
            return true;
        }
        List<IptvPlaylistEntry> playlists = config.getPlaylists();
        if (playlists != null && playlists.isEmpty()) {
            return false;
        }
        for (IptvPlaylistEntry iptvPlaylistEntry : playlists) {
            if (iptvPlaylistEntry.getEnabled()) {
                if (!kotlin.text.o.h0(iptvPlaylistEntry.getEpgUrl())) {
                    return true;
                }
                List<String> epgUrls = iptvPlaylistEntry.getEpgUrls();
                if (epgUrls == null || !epgUrls.isEmpty()) {
                    Iterator<T> it = epgUrls.iterator();
                    while (it.hasNext()) {
                        if (!kotlin.text.o.h0((String) it.next())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

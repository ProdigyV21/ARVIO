package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.data.model.IptvChannel;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tJ\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0011J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000bJ\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cR\u000e\u0010\u0017\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveTvStartup;", "", "<init>", "()V", "resumeChannelId", "", "explicitChannelId", "lastChannelId", "availableChannelIds", "", "shouldClaimSidebarFocus", "", "isTouchDevice", "isCategoryZoneActive", "channelsLoaded", "shouldFocusSearch", "focusSearchSignal", "", "searchIsReachable", "categoryCount", "entryFocus", "Lcom/arflix/tv/ui/screens/tv/live/LiveTvStartup$EntryFocus;", "hasChannels", "INITIAL_FOCUS_ATTEMPTS", "INITIAL_FOCUS_RETRY_MS", "", "channelIds", "channels", "", "Lcom/arflix/tv/data/model/IptvChannel;", "EntryFocus", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LiveTvStartup {
    public static final int $stable = 0;
    public static final int INITIAL_FOCUS_ATTEMPTS = 25;
    public static final long INITIAL_FOCUS_RETRY_MS = 80;
    public static final LiveTvStartup INSTANCE = new LiveTvStartup();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveTvStartup$EntryFocus;", "", "<init>", "(Ljava/lang/String;I)V", "CATEGORY_LIST", "NONE", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class EntryFocus {
        private static final /* synthetic */ g7.a $ENTRIES;
        private static final /* synthetic */ EntryFocus[] $VALUES;
        public static final EntryFocus CATEGORY_LIST = new EntryFocus("CATEGORY_LIST", 0);
        public static final EntryFocus NONE = new EntryFocus("NONE", 1);

        private static final /* synthetic */ EntryFocus[] $values() {
            return new EntryFocus[]{CATEGORY_LIST, NONE};
        }

        static {
            EntryFocus[] entryFocusArr$values = $values();
            $VALUES = entryFocusArr$values;
            $ENTRIES = new g7.b(entryFocusArr$values);
        }

        private EntryFocus(String str, int i10) {
        }

        public static g7.a<EntryFocus> getEntries() {
            return $ENTRIES;
        }

        public static EntryFocus valueOf(String str) {
            return (EntryFocus) Enum.valueOf(EntryFocus.class, str);
        }

        public static EntryFocus[] values() {
            return (EntryFocus[]) $VALUES.clone();
        }
    }

    private LiveTvStartup() {
    }

    public final Set<String> channelIds(List<IptvChannel> channels) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(channels.size());
        Iterator<T> it = channels.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((IptvChannel) it.next()).getId());
        }
        return linkedHashSet;
    }

    public final EntryFocus entryFocus(boolean isTouchDevice, boolean hasChannels) {
        return isTouchDevice ? EntryFocus.NONE : !hasChannels ? EntryFocus.NONE : EntryFocus.CATEGORY_LIST;
    }

    public final String resumeChannelId(String explicitChannelId, String lastChannelId, Set<String> availableChannelIds) {
        if (explicitChannelId != null) {
            if (kotlin.text.o.h0(explicitChannelId)) {
                explicitChannelId = null;
            }
            if (explicitChannelId != null) {
                return explicitChannelId;
            }
        }
        String string = lastChannelId != null ? kotlin.text.o.L0(lastChannelId).toString() : null;
        if (string == null) {
            string = "";
        }
        if (string.length() == 0) {
            return null;
        }
        if (availableChannelIds.isEmpty() || availableChannelIds.contains(string)) {
            return string;
        }
        return null;
    }

    public final boolean searchIsReachable(int categoryCount) {
        return categoryCount > 0;
    }

    public final boolean shouldClaimSidebarFocus(boolean isTouchDevice, boolean isCategoryZoneActive, boolean channelsLoaded) {
        return !isTouchDevice && isCategoryZoneActive && channelsLoaded;
    }

    public final boolean shouldFocusSearch(int focusSearchSignal) {
        return focusSearchSignal > 0;
    }
}

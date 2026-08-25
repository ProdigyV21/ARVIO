package com.arflix.tv.ui.components;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.outlined.BookmarkKt;
import androidx.compose.material.icons.outlined.HomeKt;
import androidx.compose.material.icons.outlined.LiveTvKt;
import androidx.compose.material.icons.outlined.SearchKt;
import androidx.compose.material.icons.outlined.SettingsKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.arflix.tv.R;
import kotlin.Metadata;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/ui/components/SidebarItem;", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "labelRes", "", "<init>", "(Ljava/lang/String;ILandroidx/compose/ui/graphics/vector/ImageVector;I)V", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "getLabelRes", "()I", "SEARCH", "HOME", "WATCHLIST", "TV", "SETTINGS", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SidebarItem {
    private static final /* synthetic */ g7.a $ENTRIES;
    private static final /* synthetic */ SidebarItem[] $VALUES;
    public static final SidebarItem HOME;
    public static final SidebarItem SEARCH;
    public static final SidebarItem SETTINGS;
    public static final SidebarItem TV;
    public static final SidebarItem WATCHLIST;
    private final ImageVector icon;
    private final int labelRes;

    private static final /* synthetic */ SidebarItem[] $values() {
        return new SidebarItem[]{SEARCH, HOME, WATCHLIST, TV, SETTINGS};
    }

    static {
        Icons.Outlined outlined = Icons.Outlined.INSTANCE;
        SEARCH = new SidebarItem("SEARCH", 0, SearchKt.getSearch(outlined), R.string.search);
        HOME = new SidebarItem("HOME", 1, HomeKt.getHome(outlined), R.string.home);
        WATCHLIST = new SidebarItem("WATCHLIST", 2, BookmarkKt.getBookmark(outlined), R.string.library_default);
        TV = new SidebarItem("TV", 3, LiveTvKt.getLiveTv(outlined), R.string.tv_shows);
        SETTINGS = new SidebarItem("SETTINGS", 4, SettingsKt.getSettings(outlined), R.string.settings);
        SidebarItem[] sidebarItemArr$values = $values();
        $VALUES = sidebarItemArr$values;
        $ENTRIES = new g7.b(sidebarItemArr$values);
    }

    private SidebarItem(String str, int i10, ImageVector imageVector, int i11) {
        this.icon = imageVector;
        this.labelRes = i11;
    }

    public static g7.a<SidebarItem> getEntries() {
        return $ENTRIES;
    }

    public static SidebarItem valueOf(String str) {
        return (SidebarItem) Enum.valueOf(SidebarItem.class, str);
    }

    public static SidebarItem[] values() {
        return (SidebarItem[]) $VALUES.clone();
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final int getLabelRes() {
        return this.labelRes;
    }
}

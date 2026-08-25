package com.arflix.tv.ui.screens.tv.live;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003Je\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010(\u001a\u00020\u001d2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010*\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010+\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001e¨\u0006,"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveCategory;", "", TtmlNode.ATTR_ID, "", "label", "count", "", "iconToken", "Lcom/arflix/tv/ui/screens/tv/live/CategoryIcon;", "flagEmoji", "children", "", "playlistGroupName", "playlistId", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILcom/arflix/tv/ui/screens/tv/live/CategoryIcon;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getLabel", "getCount", "()I", "getIconToken", "()Lcom/arflix/tv/ui/screens/tv/live/CategoryIcon;", "getFlagEmoji", "getChildren", "()Ljava/util/List;", "getPlaylistGroupName", "getPlaylistId", "isGroup", "", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LiveCategory {
    public static final int $stable = 8;
    private final List<LiveCategory> children;
    private final int count;
    private final String flagEmoji;
    private final CategoryIcon iconToken;
    private final String id;
    private final String label;
    private final String playlistGroupName;
    private final String playlistId;

    public LiveCategory(String str, String str2, int i10, CategoryIcon categoryIcon, String str3, List<LiveCategory> list, String str4, String str5) {
        this.id = str;
        this.label = str2;
        this.count = i10;
        this.iconToken = categoryIcon;
        this.flagEmoji = str3;
        this.children = list;
        this.playlistGroupName = str4;
        this.playlistId = str5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LiveCategory copy$default(LiveCategory liveCategory, String str, String str2, int i10, CategoryIcon categoryIcon, String str3, List list, String str4, String str5, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = liveCategory.id;
        }
        if ((i11 & 2) != 0) {
            str2 = liveCategory.label;
        }
        if ((i11 & 4) != 0) {
            i10 = liveCategory.count;
        }
        if ((i11 & 8) != 0) {
            categoryIcon = liveCategory.iconToken;
        }
        if ((i11 & 16) != 0) {
            str3 = liveCategory.flagEmoji;
        }
        if ((i11 & 32) != 0) {
            list = liveCategory.children;
        }
        if ((i11 & 64) != 0) {
            str4 = liveCategory.playlistGroupName;
        }
        if ((i11 & 128) != 0) {
            str5 = liveCategory.playlistId;
        }
        String str6 = str4;
        String str7 = str5;
        String str8 = str3;
        List list2 = list;
        return liveCategory.copy(str, str2, i10, categoryIcon, str8, list2, str6, str7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCount() {
        return this.count;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final CategoryIcon getIconToken() {
        return this.iconToken;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getFlagEmoji() {
        return this.flagEmoji;
    }

    public final List<LiveCategory> component6() {
        return this.children;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPlaylistGroupName() {
        return this.playlistGroupName;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPlaylistId() {
        return this.playlistId;
    }

    public final LiveCategory copy(String id, String label, int count, CategoryIcon iconToken, String flagEmoji, List<LiveCategory> children, String playlistGroupName, String playlistId) {
        return new LiveCategory(id, label, count, iconToken, flagEmoji, children, playlistGroupName, playlistId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiveCategory)) {
            return false;
        }
        LiveCategory liveCategory = (LiveCategory) other;
        return kotlin.jvm.internal.p.a(this.id, liveCategory.id) && kotlin.jvm.internal.p.a(this.label, liveCategory.label) && this.count == liveCategory.count && this.iconToken == liveCategory.iconToken && kotlin.jvm.internal.p.a(this.flagEmoji, liveCategory.flagEmoji) && kotlin.jvm.internal.p.a(this.children, liveCategory.children) && kotlin.jvm.internal.p.a(this.playlistGroupName, liveCategory.playlistGroupName) && kotlin.jvm.internal.p.a(this.playlistId, liveCategory.playlistId);
    }

    public final List<LiveCategory> getChildren() {
        return this.children;
    }

    public final int getCount() {
        return this.count;
    }

    public final String getFlagEmoji() {
        return this.flagEmoji;
    }

    public final CategoryIcon getIconToken() {
        return this.iconToken;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getPlaylistGroupName() {
        return this.playlistGroupName;
    }

    public final String getPlaylistId() {
        return this.playlistId;
    }

    public int hashCode() {
        int iHashCode = (this.iconToken.hashCode() + ((androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.label) + this.count) * 31)) * 31;
        String str = this.flagEmoji;
        int iE = androidx.compose.material3.d.e(this.children, (iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31);
        String str2 = this.playlistGroupName;
        int iHashCode2 = (iE + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.playlistId;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public final boolean isGroup() {
        return !this.children.isEmpty();
    }

    public String toString() {
        String str = this.id;
        String str2 = this.label;
        int i10 = this.count;
        CategoryIcon categoryIcon = this.iconToken;
        String str3 = this.flagEmoji;
        List<LiveCategory> list = this.children;
        String str4 = this.playlistGroupName;
        String str5 = this.playlistId;
        StringBuilder sbR = androidx.fragment.app.a2.r("LiveCategory(id=", str, ", label=", str2, ", count=");
        sbR.append(i10);
        sbR.append(", iconToken=");
        sbR.append(categoryIcon);
        sbR.append(", flagEmoji=");
        sbR.append(str3);
        sbR.append(", children=");
        sbR.append(list);
        sbR.append(", playlistGroupName=");
        return androidx.fragment.app.a2.n(sbR, str4, ", playlistId=", str5, ")");
    }

    public /* synthetic */ LiveCategory(String str, String str2, int i10, CategoryIcon categoryIcon, String str3, List list, String str4, String str5, int i11, kotlin.jvm.internal.h hVar) {
        this(str, str2, i10, categoryIcon, (i11 & 16) != 0 ? null : str3, (i11 & 32) != 0 ? kotlin.collections.z.f19728i : list, (i11 & 64) != 0 ? null : str4, (i11 & 128) != 0 ? null : str5);
    }
}

package com.arflix.tv.ui.screens.tv.live;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0019\b\u0082\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003JQ\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0014\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/CategoryMenuState;", "", TtmlNode.ATTR_ID, "", "playlistId", "groupName", "canMove", "", "canHide", "canUnhide", "focusedIndex", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZI)V", "getId", "()Ljava/lang/String;", "getPlaylistId", "getGroupName", "getCanMove", "()Z", "getCanHide", "getCanUnhide", "getFocusedIndex", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class CategoryMenuState {
    private final boolean canHide;
    private final boolean canMove;
    private final boolean canUnhide;
    private final int focusedIndex;
    private final String groupName;
    private final String id;
    private final String playlistId;

    public CategoryMenuState(String str, String str2, String str3, boolean z, boolean z5, boolean z10, int i10) {
        this.id = str;
        this.playlistId = str2;
        this.groupName = str3;
        this.canMove = z;
        this.canHide = z5;
        this.canUnhide = z10;
        this.focusedIndex = i10;
    }

    public static /* synthetic */ CategoryMenuState copy$default(CategoryMenuState categoryMenuState, String str, String str2, String str3, boolean z, boolean z5, boolean z10, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = categoryMenuState.id;
        }
        if ((i11 & 2) != 0) {
            str2 = categoryMenuState.playlistId;
        }
        if ((i11 & 4) != 0) {
            str3 = categoryMenuState.groupName;
        }
        if ((i11 & 8) != 0) {
            z = categoryMenuState.canMove;
        }
        if ((i11 & 16) != 0) {
            z5 = categoryMenuState.canHide;
        }
        if ((i11 & 32) != 0) {
            z10 = categoryMenuState.canUnhide;
        }
        if ((i11 & 64) != 0) {
            i10 = categoryMenuState.focusedIndex;
        }
        boolean z11 = z10;
        int i12 = i10;
        boolean z12 = z5;
        String str4 = str3;
        return categoryMenuState.copy(str, str2, str4, z, z12, z11, i12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPlaylistId() {
        return this.playlistId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getGroupName() {
        return this.groupName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getCanMove() {
        return this.canMove;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getCanHide() {
        return this.canHide;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getCanUnhide() {
        return this.canUnhide;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getFocusedIndex() {
        return this.focusedIndex;
    }

    public final CategoryMenuState copy(String id, String playlistId, String groupName, boolean canMove, boolean canHide, boolean canUnhide, int focusedIndex) {
        return new CategoryMenuState(id, playlistId, groupName, canMove, canHide, canUnhide, focusedIndex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CategoryMenuState)) {
            return false;
        }
        CategoryMenuState categoryMenuState = (CategoryMenuState) other;
        return kotlin.jvm.internal.p.a(this.id, categoryMenuState.id) && kotlin.jvm.internal.p.a(this.playlistId, categoryMenuState.playlistId) && kotlin.jvm.internal.p.a(this.groupName, categoryMenuState.groupName) && this.canMove == categoryMenuState.canMove && this.canHide == categoryMenuState.canHide && this.canUnhide == categoryMenuState.canUnhide && this.focusedIndex == categoryMenuState.focusedIndex;
    }

    public final boolean getCanHide() {
        return this.canHide;
    }

    public final boolean getCanMove() {
        return this.canMove;
    }

    public final boolean getCanUnhide() {
        return this.canUnhide;
    }

    public final int getFocusedIndex() {
        return this.focusedIndex;
    }

    public final String getGroupName() {
        return this.groupName;
    }

    public final String getId() {
        return this.id;
    }

    public final String getPlaylistId() {
        return this.playlistId;
    }

    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        String str = this.playlistId;
        return ((((((androidx.compose.foundation.c.c((iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31, this.groupName) + (this.canMove ? 1231 : 1237)) * 31) + (this.canHide ? 1231 : 1237)) * 31) + (this.canUnhide ? 1231 : 1237)) * 31) + this.focusedIndex;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.playlistId;
        String str3 = this.groupName;
        boolean z = this.canMove;
        boolean z5 = this.canHide;
        boolean z10 = this.canUnhide;
        int i10 = this.focusedIndex;
        StringBuilder sbR = androidx.fragment.app.a2.r("CategoryMenuState(id=", str, ", playlistId=", str2, ", groupName=");
        sbR.append(str3);
        sbR.append(", canMove=");
        sbR.append(z);
        sbR.append(", canHide=");
        v.f.h(sbR, z5, ", canUnhide=", z10, ", focusedIndex=");
        return androidx.compose.material3.d.j(i10, ")", sbR);
    }

    public /* synthetic */ CategoryMenuState(String str, String str2, String str3, boolean z, boolean z5, boolean z10, int i10, int i11, kotlin.jvm.internal.h hVar) {
        this(str, str2, str3, z, z5, z10, (i11 & 64) != 0 ? 0 : i10);
    }
}

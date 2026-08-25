package com.arflix.tv.ui.screens.plugin;

import androidx.fragment.app.a2;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003JM\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010\u0019\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PendingRepoChangeInfo;", "", "changeId", "", "proposedUrls", "", "addedUrls", "removedUrls", "isApplying", "", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Z)V", "getChangeId", "()Ljava/lang/String;", "getProposedUrls", "()Ljava/util/List;", "getAddedUrls", "getRemovedUrls", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PendingRepoChangeInfo {
    public static final int $stable = 0;
    private final List<String> addedUrls;
    private final String changeId;
    private final boolean isApplying;
    private final List<String> proposedUrls;
    private final List<String> removedUrls;

    public PendingRepoChangeInfo(String str, List<String> list, List<String> list2, List<String> list3, boolean z) {
        this.changeId = str;
        this.proposedUrls = list;
        this.addedUrls = list2;
        this.removedUrls = list3;
        this.isApplying = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PendingRepoChangeInfo copy$default(PendingRepoChangeInfo pendingRepoChangeInfo, String str, List list, List list2, List list3, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = pendingRepoChangeInfo.changeId;
        }
        if ((i10 & 2) != 0) {
            list = pendingRepoChangeInfo.proposedUrls;
        }
        if ((i10 & 4) != 0) {
            list2 = pendingRepoChangeInfo.addedUrls;
        }
        if ((i10 & 8) != 0) {
            list3 = pendingRepoChangeInfo.removedUrls;
        }
        if ((i10 & 16) != 0) {
            z = pendingRepoChangeInfo.isApplying;
        }
        boolean z5 = z;
        List list4 = list2;
        return pendingRepoChangeInfo.copy(str, list, list4, list3, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getChangeId() {
        return this.changeId;
    }

    public final List<String> component2() {
        return this.proposedUrls;
    }

    public final List<String> component3() {
        return this.addedUrls;
    }

    public final List<String> component4() {
        return this.removedUrls;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsApplying() {
        return this.isApplying;
    }

    public final PendingRepoChangeInfo copy(String changeId, List<String> proposedUrls, List<String> addedUrls, List<String> removedUrls, boolean isApplying) {
        return new PendingRepoChangeInfo(changeId, proposedUrls, addedUrls, removedUrls, isApplying);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PendingRepoChangeInfo)) {
            return false;
        }
        PendingRepoChangeInfo pendingRepoChangeInfo = (PendingRepoChangeInfo) other;
        return p.a(this.changeId, pendingRepoChangeInfo.changeId) && p.a(this.proposedUrls, pendingRepoChangeInfo.proposedUrls) && p.a(this.addedUrls, pendingRepoChangeInfo.addedUrls) && p.a(this.removedUrls, pendingRepoChangeInfo.removedUrls) && this.isApplying == pendingRepoChangeInfo.isApplying;
    }

    public final List<String> getAddedUrls() {
        return this.addedUrls;
    }

    public final String getChangeId() {
        return this.changeId;
    }

    public final List<String> getProposedUrls() {
        return this.proposedUrls;
    }

    public final List<String> getRemovedUrls() {
        return this.removedUrls;
    }

    public int hashCode() {
        return androidx.compose.material3.d.e(this.removedUrls, androidx.compose.material3.d.e(this.addedUrls, androidx.compose.material3.d.e(this.proposedUrls, this.changeId.hashCode() * 31, 31), 31), 31) + (this.isApplying ? 1231 : 1237);
    }

    public final boolean isApplying() {
        return this.isApplying;
    }

    public String toString() {
        String str = this.changeId;
        List<String> list = this.proposedUrls;
        List<String> list2 = this.addedUrls;
        List<String> list3 = this.removedUrls;
        boolean z = this.isApplying;
        StringBuilder sb2 = new StringBuilder("PendingRepoChangeInfo(changeId=");
        sb2.append(str);
        sb2.append(", proposedUrls=");
        sb2.append(list);
        sb2.append(", addedUrls=");
        a2.A(sb2, list2, ", removedUrls=", list3, ", isApplying=");
        return a0.c.m(")", z, sb2);
    }

    public /* synthetic */ PendingRepoChangeInfo(String str, List list, List list2, List list3, boolean z, int i10, kotlin.jvm.internal.h hVar) {
        this(str, list, list2, list3, (i10 & 16) != 0 ? false : z);
    }
}

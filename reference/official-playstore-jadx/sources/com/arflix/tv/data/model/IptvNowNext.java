package com.arflix.tv.data.model;

import a0.c;
import androidx.compose.material3.d;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003JM\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/model/IptvNowNext;", "", "now", "Lcom/arflix/tv/data/model/IptvProgram;", LinkHeader.Rel.Next, "later", "upcoming", "", "recent", "<init>", "(Lcom/arflix/tv/data/model/IptvProgram;Lcom/arflix/tv/data/model/IptvProgram;Lcom/arflix/tv/data/model/IptvProgram;Ljava/util/List;Ljava/util/List;)V", "getNow", "()Lcom/arflix/tv/data/model/IptvProgram;", "getNext", "getLater", "getUpcoming", "()Ljava/util/List;", "getRecent", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IptvNowNext {
    public static final int $stable = 0;
    private final IptvProgram later;
    private final IptvProgram next;
    private final IptvProgram now;
    private final List<IptvProgram> recent;
    private final List<IptvProgram> upcoming;

    public IptvNowNext() {
        this(null, null, null, null, null, 31, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IptvNowNext copy$default(IptvNowNext iptvNowNext, IptvProgram iptvProgram, IptvProgram iptvProgram2, IptvProgram iptvProgram3, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            iptvProgram = iptvNowNext.now;
        }
        if ((i10 & 2) != 0) {
            iptvProgram2 = iptvNowNext.next;
        }
        if ((i10 & 4) != 0) {
            iptvProgram3 = iptvNowNext.later;
        }
        if ((i10 & 8) != 0) {
            list = iptvNowNext.upcoming;
        }
        if ((i10 & 16) != 0) {
            list2 = iptvNowNext.recent;
        }
        List list3 = list2;
        IptvProgram iptvProgram4 = iptvProgram3;
        return iptvNowNext.copy(iptvProgram, iptvProgram2, iptvProgram4, list, list3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IptvProgram getNow() {
        return this.now;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final IptvProgram getNext() {
        return this.next;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final IptvProgram getLater() {
        return this.later;
    }

    public final List<IptvProgram> component4() {
        return this.upcoming;
    }

    public final List<IptvProgram> component5() {
        return this.recent;
    }

    public final IptvNowNext copy(IptvProgram now, IptvProgram next, IptvProgram later, List<IptvProgram> upcoming, List<IptvProgram> recent) {
        return new IptvNowNext(now, next, later, upcoming, recent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IptvNowNext)) {
            return false;
        }
        IptvNowNext iptvNowNext = (IptvNowNext) other;
        return p.a(this.now, iptvNowNext.now) && p.a(this.next, iptvNowNext.next) && p.a(this.later, iptvNowNext.later) && p.a(this.upcoming, iptvNowNext.upcoming) && p.a(this.recent, iptvNowNext.recent);
    }

    public final IptvProgram getLater() {
        return this.later;
    }

    public final IptvProgram getNext() {
        return this.next;
    }

    public final IptvProgram getNow() {
        return this.now;
    }

    public final List<IptvProgram> getRecent() {
        return this.recent;
    }

    public final List<IptvProgram> getUpcoming() {
        return this.upcoming;
    }

    public int hashCode() {
        IptvProgram iptvProgram = this.now;
        int iHashCode = (iptvProgram == null ? 0 : iptvProgram.hashCode()) * 31;
        IptvProgram iptvProgram2 = this.next;
        int iHashCode2 = (iHashCode + (iptvProgram2 == null ? 0 : iptvProgram2.hashCode())) * 31;
        IptvProgram iptvProgram3 = this.later;
        return this.recent.hashCode() + d.e(this.upcoming, (iHashCode2 + (iptvProgram3 != null ? iptvProgram3.hashCode() : 0)) * 31, 31);
    }

    public String toString() {
        IptvProgram iptvProgram = this.now;
        IptvProgram iptvProgram2 = this.next;
        IptvProgram iptvProgram3 = this.later;
        List<IptvProgram> list = this.upcoming;
        List<IptvProgram> list2 = this.recent;
        StringBuilder sb2 = new StringBuilder("IptvNowNext(now=");
        sb2.append(iptvProgram);
        sb2.append(", next=");
        sb2.append(iptvProgram2);
        sb2.append(", later=");
        sb2.append(iptvProgram3);
        sb2.append(", upcoming=");
        sb2.append(list);
        sb2.append(", recent=");
        return c.q(sb2, list2, ")");
    }

    public IptvNowNext(IptvProgram iptvProgram, IptvProgram iptvProgram2, IptvProgram iptvProgram3, List<IptvProgram> list, List<IptvProgram> list2) {
        this.now = iptvProgram;
        this.next = iptvProgram2;
        this.later = iptvProgram3;
        this.upcoming = list;
        this.recent = list2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ IptvNowNext(IptvProgram iptvProgram, IptvProgram iptvProgram2, IptvProgram iptvProgram3, List list, List list2, int i10, h hVar) {
        iptvProgram = (i10 & 1) != 0 ? null : iptvProgram;
        iptvProgram2 = (i10 & 2) != 0 ? null : iptvProgram2;
        iptvProgram3 = (i10 & 4) != 0 ? null : iptvProgram3;
        int i11 = i10 & 8;
        z zVar = z.f19728i;
        this(iptvProgram, iptvProgram2, iptvProgram3, i11 != 0 ? zVar : list, (i10 & 16) != 0 ? zVar : list2);
    }
}

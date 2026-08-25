package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0006J\u000e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0013JD\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0014\u0010\u001f\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013¨\u0006$"}, d2 = {"Lcom/arflix/tv/data/model/IptvProgram;", "", LinkHeader.Parameters.Title, "", MediaTrack.ROLE_DESCRIPTION, "startUtcMillis", "", "endUtcMillis", "catchupAvailable", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;JJLjava/lang/Boolean;)V", "getTitle", "()Ljava/lang/String;", "getDescription", "getStartUtcMillis", "()J", "getEndUtcMillis", "getCatchupAvailable", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "isLive", "atUtcMillis", "startsInMinutes", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;JJLjava/lang/Boolean;)Lcom/arflix/tv/data/model/IptvProgram;", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IptvProgram {
    public static final int $stable = 0;
    private final Boolean catchupAvailable;
    private final String description;
    private final long endUtcMillis;
    private final long startUtcMillis;
    private final String title;

    public IptvProgram(String str, String str2, long j10, long j11, Boolean bool) {
        this.title = str;
        this.description = str2;
        this.startUtcMillis = j10;
        this.endUtcMillis = j11;
        this.catchupAvailable = bool;
    }

    public static /* synthetic */ IptvProgram copy$default(IptvProgram iptvProgram, String str, String str2, long j10, long j11, Boolean bool, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = iptvProgram.title;
        }
        if ((i10 & 2) != 0) {
            str2 = iptvProgram.description;
        }
        if ((i10 & 4) != 0) {
            j10 = iptvProgram.startUtcMillis;
        }
        if ((i10 & 8) != 0) {
            j11 = iptvProgram.endUtcMillis;
        }
        if ((i10 & 16) != 0) {
            bool = iptvProgram.catchupAvailable;
        }
        Boolean bool2 = bool;
        long j12 = j11;
        return iptvProgram.copy(str, str2, j10, j12, bool2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getStartUtcMillis() {
        return this.startUtcMillis;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getEndUtcMillis() {
        return this.endUtcMillis;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Boolean getCatchupAvailable() {
        return this.catchupAvailable;
    }

    public final IptvProgram copy(String title, String description, long startUtcMillis, long endUtcMillis, Boolean catchupAvailable) {
        return new IptvProgram(title, description, startUtcMillis, endUtcMillis, catchupAvailable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IptvProgram)) {
            return false;
        }
        IptvProgram iptvProgram = (IptvProgram) other;
        return p.a(this.title, iptvProgram.title) && p.a(this.description, iptvProgram.description) && this.startUtcMillis == iptvProgram.startUtcMillis && this.endUtcMillis == iptvProgram.endUtcMillis && p.a(this.catchupAvailable, iptvProgram.catchupAvailable);
    }

    public final Boolean getCatchupAvailable() {
        return this.catchupAvailable;
    }

    public final String getDescription() {
        return this.description;
    }

    public final long getEndUtcMillis() {
        return this.endUtcMillis;
    }

    public final long getStartUtcMillis() {
        return this.startUtcMillis;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int iHashCode = this.title.hashCode() * 31;
        String str = this.description;
        int iHashCode2 = str == null ? 0 : str.hashCode();
        long j10 = this.startUtcMillis;
        int i10 = (((iHashCode + iHashCode2) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        long j11 = this.endUtcMillis;
        int i11 = (i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31;
        Boolean bool = this.catchupAvailable;
        return i11 + (bool != null ? bool.hashCode() : 0);
    }

    public final boolean isLive(long atUtcMillis) {
        return atUtcMillis < this.endUtcMillis && this.startUtcMillis <= atUtcMillis;
    }

    public final long startsInMinutes(long atUtcMillis) {
        long j10 = (this.startUtcMillis - atUtcMillis) / 60000;
        if (j10 < 0) {
            return 0L;
        }
        return j10;
    }

    public String toString() {
        String str = this.title;
        String str2 = this.description;
        long j10 = this.startUtcMillis;
        long j11 = this.endUtcMillis;
        Boolean bool = this.catchupAvailable;
        StringBuilder sbR = a2.r("IptvProgram(title=", str, ", description=", str2, ", startUtcMillis=");
        sbR.append(j10);
        c.z(sbR, ", endUtcMillis=", j11, ", catchupAvailable=");
        sbR.append(bool);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ IptvProgram(String str, String str2, long j10, long j11, Boolean bool, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2, j10, j11, (i10 & 16) != 0 ? null : bool);
    }
}

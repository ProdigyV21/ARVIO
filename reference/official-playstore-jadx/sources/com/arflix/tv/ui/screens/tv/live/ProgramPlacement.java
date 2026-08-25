package com.arflix.tv.ui.screens.tv.live;

import com.arflix.tv.data.model.IptvProgram;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\bJ\u000e\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\bJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\t\u0010!\u001a\u00020\u000bHÆ\u0003JE\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0014\u0010#\u001a\u00020\u000b2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010&\u001a\u00020'HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0011¨\u0006("}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/ProgramPlacement;", "", "program", "Lcom/arflix/tv/data/model/IptvProgram;", "startMin", "", "durationMin", "startMillis", "", "endMillis", "isPlaceholder", "", "<init>", "(Lcom/arflix/tv/data/model/IptvProgram;IIJJZ)V", "getProgram", "()Lcom/arflix/tv/data/model/IptvProgram;", "getStartMin", "()I", "getDurationMin", "getStartMillis", "()J", "getEndMillis", "()Z", "endMin", "getEndMin", "isNow", "nowMs", "isPast", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ProgramPlacement {
    private final int durationMin;
    private final long endMillis;
    private final boolean isPlaceholder;
    private final IptvProgram program;
    private final long startMillis;
    private final int startMin;

    public ProgramPlacement(IptvProgram iptvProgram, int i10, int i11, long j10, long j11, boolean z) {
        this.program = iptvProgram;
        this.startMin = i10;
        this.durationMin = i11;
        this.startMillis = j10;
        this.endMillis = j11;
        this.isPlaceholder = z;
    }

    public static /* synthetic */ ProgramPlacement copy$default(ProgramPlacement programPlacement, IptvProgram iptvProgram, int i10, int i11, long j10, long j11, boolean z, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            iptvProgram = programPlacement.program;
        }
        if ((i12 & 2) != 0) {
            i10 = programPlacement.startMin;
        }
        if ((i12 & 4) != 0) {
            i11 = programPlacement.durationMin;
        }
        if ((i12 & 8) != 0) {
            j10 = programPlacement.startMillis;
        }
        if ((i12 & 16) != 0) {
            j11 = programPlacement.endMillis;
        }
        if ((i12 & 32) != 0) {
            z = programPlacement.isPlaceholder;
        }
        boolean z5 = z;
        long j12 = j11;
        int i13 = i11;
        return programPlacement.copy(iptvProgram, i10, i13, j10, j12, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IptvProgram getProgram() {
        return this.program;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getStartMin() {
        return this.startMin;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getDurationMin() {
        return this.durationMin;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getStartMillis() {
        return this.startMillis;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getEndMillis() {
        return this.endMillis;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsPlaceholder() {
        return this.isPlaceholder;
    }

    public final ProgramPlacement copy(IptvProgram program, int startMin, int durationMin, long startMillis, long endMillis, boolean isPlaceholder) {
        return new ProgramPlacement(program, startMin, durationMin, startMillis, endMillis, isPlaceholder);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProgramPlacement)) {
            return false;
        }
        ProgramPlacement programPlacement = (ProgramPlacement) other;
        return kotlin.jvm.internal.p.a(this.program, programPlacement.program) && this.startMin == programPlacement.startMin && this.durationMin == programPlacement.durationMin && this.startMillis == programPlacement.startMillis && this.endMillis == programPlacement.endMillis && this.isPlaceholder == programPlacement.isPlaceholder;
    }

    public final int getDurationMin() {
        return this.durationMin;
    }

    public final long getEndMillis() {
        return this.endMillis;
    }

    public final int getEndMin() {
        return this.startMin + this.durationMin;
    }

    public final IptvProgram getProgram() {
        return this.program;
    }

    public final long getStartMillis() {
        return this.startMillis;
    }

    public final int getStartMin() {
        return this.startMin;
    }

    public int hashCode() {
        int iHashCode = ((((this.program.hashCode() * 31) + this.startMin) * 31) + this.durationMin) * 31;
        long j10 = this.startMillis;
        int i10 = (iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        long j11 = this.endMillis;
        return ((i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31) + (this.isPlaceholder ? 1231 : 1237);
    }

    public final boolean isNow(long nowMs) {
        return nowMs < this.endMillis && this.startMillis <= nowMs;
    }

    public final boolean isPast(long nowMs) {
        return this.endMillis <= nowMs;
    }

    public final boolean isPlaceholder() {
        return this.isPlaceholder;
    }

    public String toString() {
        IptvProgram iptvProgram = this.program;
        int i10 = this.startMin;
        int i11 = this.durationMin;
        long j10 = this.startMillis;
        long j11 = this.endMillis;
        boolean z = this.isPlaceholder;
        StringBuilder sb2 = new StringBuilder("ProgramPlacement(program=");
        sb2.append(iptvProgram);
        sb2.append(", startMin=");
        sb2.append(i10);
        sb2.append(", durationMin=");
        sb2.append(i11);
        sb2.append(", startMillis=");
        sb2.append(j10);
        androidx.compose.foundation.c.z(sb2, ", endMillis=", j11, ", isPlaceholder=");
        return a0.c.m(")", z, sb2);
    }

    public /* synthetic */ ProgramPlacement(IptvProgram iptvProgram, int i10, int i11, long j10, long j11, boolean z, int i12, kotlin.jvm.internal.h hVar) {
        this(iptvProgram, i10, i11, j10, j11, (i12 & 32) != 0 ? false : z);
    }
}

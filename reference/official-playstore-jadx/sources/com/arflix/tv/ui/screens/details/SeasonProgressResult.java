package com.arflix.tv.ui.screens.details;

import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0082\b\u0018\u00002\u00020\u0001BE\u0012\u001e\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00040\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0004¢\u0006\u0004\b\t\u0010\nJ(\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00040\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u001e\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010JT\u0010\u0011\u001a\u00020\u00002 \b\u0002\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00040\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u0019\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR/\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b\u001e\u0010\u000eR%\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b \u0010\u0010¨\u0006!"}, d2 = {"Lcom/arflix/tv/ui/screens/details/SeasonProgressResult;", "", "", "", "Lx6/x;", "progress", "", "hasWatched", "nextUnwatched", "<init>", "(Ljava/util/Map;ZLx6/x;)V", "component1", "()Ljava/util/Map;", "component2", "()Z", "component3", "()Lx6/x;", "copy", "(Ljava/util/Map;ZLx6/x;)Lcom/arflix/tv/ui/screens/details/SeasonProgressResult;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/Map;", "getProgress", "Z", "getHasWatched", "Lx6/x;", "getNextUnwatched", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class SeasonProgressResult {
    private final boolean hasWatched;
    private final x6.x nextUnwatched;
    private final Map<Integer, x6.x> progress;

    public SeasonProgressResult(Map<Integer, x6.x> map, boolean z, x6.x xVar) {
        this.progress = map;
        this.hasWatched = z;
        this.nextUnwatched = xVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SeasonProgressResult copy$default(SeasonProgressResult seasonProgressResult, Map map, boolean z, x6.x xVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            map = seasonProgressResult.progress;
        }
        if ((i10 & 2) != 0) {
            z = seasonProgressResult.hasWatched;
        }
        if ((i10 & 4) != 0) {
            xVar = seasonProgressResult.nextUnwatched;
        }
        return seasonProgressResult.copy(map, z, xVar);
    }

    public final Map<Integer, x6.x> component1() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getHasWatched() {
        return this.hasWatched;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final x6.x getNextUnwatched() {
        return this.nextUnwatched;
    }

    public final SeasonProgressResult copy(Map<Integer, x6.x> progress, boolean hasWatched, x6.x nextUnwatched) {
        return new SeasonProgressResult(progress, hasWatched, nextUnwatched);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SeasonProgressResult)) {
            return false;
        }
        SeasonProgressResult seasonProgressResult = (SeasonProgressResult) other;
        return kotlin.jvm.internal.p.a(this.progress, seasonProgressResult.progress) && this.hasWatched == seasonProgressResult.hasWatched && kotlin.jvm.internal.p.a(this.nextUnwatched, seasonProgressResult.nextUnwatched);
    }

    public final boolean getHasWatched() {
        return this.hasWatched;
    }

    public final x6.x getNextUnwatched() {
        return this.nextUnwatched;
    }

    public final Map<Integer, x6.x> getProgress() {
        return this.progress;
    }

    public int hashCode() {
        int iHashCode = ((this.progress.hashCode() * 31) + (this.hasWatched ? 1231 : 1237)) * 31;
        x6.x xVar = this.nextUnwatched;
        return iHashCode + (xVar == null ? 0 : xVar.hashCode());
    }

    public String toString() {
        return "SeasonProgressResult(progress=" + this.progress + ", hasWatched=" + this.hasWatched + ", nextUnwatched=" + this.nextUnwatched + ")";
    }
}

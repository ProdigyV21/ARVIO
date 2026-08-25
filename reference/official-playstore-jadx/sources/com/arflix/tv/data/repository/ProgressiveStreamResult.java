package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.model.Subtitle;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\u000bHÆ\u0003JG\u0010\u001a\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0014¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/repository/ProgressiveStreamResult;", "", "streams", "", "Lcom/arflix/tv/data/model/StreamSource;", "subtitles", "Lcom/arflix/tv/data/model/Subtitle;", "completedAddons", "", "totalAddons", "isFinal", "", "<init>", "(Ljava/util/List;Ljava/util/List;IIZ)V", "getStreams", "()Ljava/util/List;", "getSubtitles", "getCompletedAddons", "()I", "getTotalAddons", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ProgressiveStreamResult {
    public static final int $stable = 0;
    private final int completedAddons;
    private final boolean isFinal;
    private final List<StreamSource> streams;
    private final List<Subtitle> subtitles;
    private final int totalAddons;

    public ProgressiveStreamResult(List<StreamSource> list, List<Subtitle> list2, int i10, int i11, boolean z) {
        this.streams = list;
        this.subtitles = list2;
        this.completedAddons = i10;
        this.totalAddons = i11;
        this.isFinal = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ProgressiveStreamResult copy$default(ProgressiveStreamResult progressiveStreamResult, List list, List list2, int i10, int i11, boolean z, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            list = progressiveStreamResult.streams;
        }
        if ((i12 & 2) != 0) {
            list2 = progressiveStreamResult.subtitles;
        }
        if ((i12 & 4) != 0) {
            i10 = progressiveStreamResult.completedAddons;
        }
        if ((i12 & 8) != 0) {
            i11 = progressiveStreamResult.totalAddons;
        }
        if ((i12 & 16) != 0) {
            z = progressiveStreamResult.isFinal;
        }
        boolean z5 = z;
        int i13 = i10;
        return progressiveStreamResult.copy(list, list2, i13, i11, z5);
    }

    public final List<StreamSource> component1() {
        return this.streams;
    }

    public final List<Subtitle> component2() {
        return this.subtitles;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCompletedAddons() {
        return this.completedAddons;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getTotalAddons() {
        return this.totalAddons;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsFinal() {
        return this.isFinal;
    }

    public final ProgressiveStreamResult copy(List<StreamSource> streams, List<Subtitle> subtitles, int completedAddons, int totalAddons, boolean isFinal) {
        return new ProgressiveStreamResult(streams, subtitles, completedAddons, totalAddons, isFinal);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProgressiveStreamResult)) {
            return false;
        }
        ProgressiveStreamResult progressiveStreamResult = (ProgressiveStreamResult) other;
        return kotlin.jvm.internal.p.a(this.streams, progressiveStreamResult.streams) && kotlin.jvm.internal.p.a(this.subtitles, progressiveStreamResult.subtitles) && this.completedAddons == progressiveStreamResult.completedAddons && this.totalAddons == progressiveStreamResult.totalAddons && this.isFinal == progressiveStreamResult.isFinal;
    }

    public final int getCompletedAddons() {
        return this.completedAddons;
    }

    public final List<StreamSource> getStreams() {
        return this.streams;
    }

    public final List<Subtitle> getSubtitles() {
        return this.subtitles;
    }

    public final int getTotalAddons() {
        return this.totalAddons;
    }

    public int hashCode() {
        return ((((androidx.compose.material3.d.e(this.subtitles, this.streams.hashCode() * 31, 31) + this.completedAddons) * 31) + this.totalAddons) * 31) + (this.isFinal ? 1231 : 1237);
    }

    public final boolean isFinal() {
        return this.isFinal;
    }

    public String toString() {
        List<StreamSource> list = this.streams;
        List<Subtitle> list2 = this.subtitles;
        int i10 = this.completedAddons;
        int i11 = this.totalAddons;
        boolean z = this.isFinal;
        StringBuilder sb2 = new StringBuilder("ProgressiveStreamResult(streams=");
        sb2.append(list);
        sb2.append(", subtitles=");
        sb2.append(list2);
        sb2.append(", completedAddons=");
        a2.y(sb2, i10, ", totalAddons=", i11, ", isFinal=");
        return a0.c.m(")", z, sb2);
    }

    public /* synthetic */ ProgressiveStreamResult(List list, List list2, int i10, int i11, boolean z, int i12, kotlin.jvm.internal.h hVar) {
        this(list, (i12 & 2) != 0 ? kotlin.collections.z.f19728i : list2, i10, i11, z);
    }
}

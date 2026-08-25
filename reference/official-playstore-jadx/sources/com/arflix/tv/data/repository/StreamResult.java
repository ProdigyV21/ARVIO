package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.model.Subtitle;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/repository/StreamResult;", "", "streams", "", "Lcom/arflix/tv/data/model/StreamSource;", "subtitles", "Lcom/arflix/tv/data/model/Subtitle;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getStreams", "()Ljava/util/List;", "getSubtitles", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StreamResult {
    public static final int $stable = 0;
    private final List<StreamSource> streams;
    private final List<Subtitle> subtitles;

    public StreamResult(List<StreamSource> list, List<Subtitle> list2) {
        this.streams = list;
        this.subtitles = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StreamResult copy$default(StreamResult streamResult, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = streamResult.streams;
        }
        if ((i10 & 2) != 0) {
            list2 = streamResult.subtitles;
        }
        return streamResult.copy(list, list2);
    }

    public final List<StreamSource> component1() {
        return this.streams;
    }

    public final List<Subtitle> component2() {
        return this.subtitles;
    }

    public final StreamResult copy(List<StreamSource> streams, List<Subtitle> subtitles) {
        return new StreamResult(streams, subtitles);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StreamResult)) {
            return false;
        }
        StreamResult streamResult = (StreamResult) other;
        return kotlin.jvm.internal.p.a(this.streams, streamResult.streams) && kotlin.jvm.internal.p.a(this.subtitles, streamResult.subtitles);
    }

    public final List<StreamSource> getStreams() {
        return this.streams;
    }

    public final List<Subtitle> getSubtitles() {
        return this.subtitles;
    }

    public int hashCode() {
        return this.subtitles.hashCode() + (this.streams.hashCode() * 31);
    }

    public String toString() {
        return "StreamResult(streams=" + this.streams + ", subtitles=" + this.subtitles + ")";
    }
}

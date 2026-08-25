package io.ktor.http.cio.internals;

import a0.c;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u000e"}, d2 = {"Lio/ktor/http/cio/internals/MutableRange;", "", TtmlNode.START, "", TtmlNode.END, "(II)V", "getEnd", "()I", "setEnd", "(I)V", "getStart", "setStart", "toString", "", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MutableRange {
    private int end;
    private int start;

    public MutableRange(int i10, int i11) {
        this.start = i10;
        this.end = i11;
    }

    public final int getEnd() {
        return this.end;
    }

    public final int getStart() {
        return this.start;
    }

    public final void setEnd(int i10) {
        this.end = i10;
    }

    public final void setStart(int i10) {
        this.start = i10;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("MutableRange(start=");
        sb2.append(this.start);
        sb2.append(", end=");
        return c.o(sb2, this.end, ')');
    }
}

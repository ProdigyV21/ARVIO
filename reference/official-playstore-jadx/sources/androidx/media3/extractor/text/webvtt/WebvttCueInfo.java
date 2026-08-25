package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.text.Cue;

/* JADX INFO: loaded from: classes3.dex */
public final class WebvttCueInfo {
    public final Cue cue;
    public final long endTimeUs;
    public final long startTimeUs;

    public WebvttCueInfo(Cue cue, long j10, long j11) {
        this.cue = cue;
        this.startTimeUs = j10;
        this.endTimeUs = j11;
    }
}

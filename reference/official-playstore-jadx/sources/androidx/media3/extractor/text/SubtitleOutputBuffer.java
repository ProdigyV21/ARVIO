package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import androidx.media3.decoder.DecoderOutputBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SubtitleOutputBuffer extends DecoderOutputBuffer implements Subtitle {
    private long subsampleOffsetUs;
    private Subtitle subtitle;

    @Override // androidx.media3.decoder.DecoderOutputBuffer, androidx.media3.decoder.Buffer
    public void clear() {
        super.clear();
        this.subtitle = null;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public List<Cue> getCues(long j10) {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getCues(j10 - this.subsampleOffsetUs);
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public long getEventTime(int i10) {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getEventTime(i10) + this.subsampleOffsetUs;
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getEventTimeCount() {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getEventTimeCount();
    }

    @Override // androidx.media3.extractor.text.Subtitle
    public int getNextEventTimeIndex(long j10) {
        Subtitle subtitle = this.subtitle;
        subtitle.getClass();
        return subtitle.getNextEventTimeIndex(j10 - this.subsampleOffsetUs);
    }

    public void setContent(long j10, Subtitle subtitle, long j11) {
        this.timeUs = j10;
        this.subtitle = subtitle;
        if (j11 != Long.MAX_VALUE) {
            j10 = j11;
        }
        this.subsampleOffsetUs = j10;
    }
}

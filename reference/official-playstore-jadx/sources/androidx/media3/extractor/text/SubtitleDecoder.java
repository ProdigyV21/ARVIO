package androidx.media3.extractor.text;

import androidx.media3.decoder.Decoder;

/* JADX INFO: loaded from: classes3.dex */
public interface SubtitleDecoder extends Decoder<SubtitleInputBuffer, SubtitleOutputBuffer, SubtitleDecoderException> {
    void setPositionUs(long j10);
}

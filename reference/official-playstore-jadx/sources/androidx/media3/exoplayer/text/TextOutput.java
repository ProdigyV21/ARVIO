package androidx.media3.exoplayer.text;

import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface TextOutput {
    void onCues(CueGroup cueGroup);

    @Deprecated
    void onCues(List<Cue> list);
}

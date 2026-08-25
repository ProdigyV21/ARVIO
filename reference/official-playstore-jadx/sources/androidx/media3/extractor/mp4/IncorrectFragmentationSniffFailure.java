package androidx.media3.extractor.mp4;

import a0.c;
import androidx.media3.extractor.SniffFailure;

/* JADX INFO: loaded from: classes3.dex */
public final class IncorrectFragmentationSniffFailure implements SniffFailure {
    public static final IncorrectFragmentationSniffFailure FILE_FRAGMENTED = new IncorrectFragmentationSniffFailure(true);
    public static final IncorrectFragmentationSniffFailure FILE_NOT_FRAGMENTED = new IncorrectFragmentationSniffFailure(false);
    public final boolean fileIsFragmented;

    private IncorrectFragmentationSniffFailure(boolean z) {
        this.fileIsFragmented = z;
    }

    public String toString() {
        return c.m("}", !this.fileIsFragmented, new StringBuilder("IncorrectFragmentation{expected="));
    }
}

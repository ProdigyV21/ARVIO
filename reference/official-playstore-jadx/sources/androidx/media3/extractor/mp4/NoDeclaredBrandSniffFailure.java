package androidx.media3.extractor.mp4;

import androidx.media3.extractor.SniffFailure;

/* JADX INFO: loaded from: classes3.dex */
public final class NoDeclaredBrandSniffFailure implements SniffFailure {
    public static final NoDeclaredBrandSniffFailure INSTANCE = new NoDeclaredBrandSniffFailure();

    private NoDeclaredBrandSniffFailure() {
    }

    public String toString() {
        return "NoDeclaredBrand";
    }
}

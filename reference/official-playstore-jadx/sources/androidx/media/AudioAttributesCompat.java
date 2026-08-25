package androidx.media;

import android.util.SparseIntArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class AudioAttributesCompat implements k2.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f3347b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AudioAttributesImpl f3348a;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface b {
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(5, 1);
        sparseIntArray.put(6, 2);
        sparseIntArray.put(7, 2);
        sparseIntArray.put(8, 1);
        sparseIntArray.put(9, 1);
        sparseIntArray.put(10, 1);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesCompat)) {
            return false;
        }
        AudioAttributesCompat audioAttributesCompat = (AudioAttributesCompat) obj;
        AudioAttributesImpl audioAttributesImpl = this.f3348a;
        return audioAttributesImpl == null ? audioAttributesCompat.f3348a == null : audioAttributesImpl.equals(audioAttributesCompat.f3348a);
    }

    public final int hashCode() {
        return this.f3348a.hashCode();
    }

    public final String toString() {
        return this.f3348a.toString();
    }
}

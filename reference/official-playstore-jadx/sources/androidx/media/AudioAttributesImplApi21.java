package androidx.media;

import android.media.AudioAttributes;

/* JADX INFO: loaded from: classes3.dex */
public class AudioAttributesImplApi21 implements AudioAttributesImpl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AudioAttributes f3349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3350b = -1;

    public final boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplApi21) {
            return this.f3349a.equals(((AudioAttributesImplApi21) obj).f3349a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f3349a.hashCode();
    }

    public final String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.f3349a;
    }
}

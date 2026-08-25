package androidx.media;

import android.media.AudioAttributes;

/* JADX INFO: loaded from: classes3.dex */
public class AudioAttributesImplApi26Parcelizer {
    public static AudioAttributesImplApi26 read(k2.d dVar) {
        AudioAttributesImplApi26 audioAttributesImplApi26 = new AudioAttributesImplApi26();
        audioAttributesImplApi26.f3349a = (AudioAttributes) dVar.g(audioAttributesImplApi26.f3349a, 1);
        audioAttributesImplApi26.f3350b = dVar.f(audioAttributesImplApi26.f3350b, 2);
        return audioAttributesImplApi26;
    }

    public static void write(AudioAttributesImplApi26 audioAttributesImplApi26, k2.d dVar) {
        dVar.getClass();
        dVar.k(audioAttributesImplApi26.f3349a, 1);
        dVar.j(audioAttributesImplApi26.f3350b, 2);
    }
}

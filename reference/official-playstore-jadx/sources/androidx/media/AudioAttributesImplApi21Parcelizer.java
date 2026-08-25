package androidx.media;

import android.media.AudioAttributes;

/* JADX INFO: loaded from: classes3.dex */
public class AudioAttributesImplApi21Parcelizer {
    public static AudioAttributesImplApi21 read(k2.d dVar) {
        AudioAttributesImplApi21 audioAttributesImplApi21 = new AudioAttributesImplApi21();
        audioAttributesImplApi21.f3349a = (AudioAttributes) dVar.g(audioAttributesImplApi21.f3349a, 1);
        audioAttributesImplApi21.f3350b = dVar.f(audioAttributesImplApi21.f3350b, 2);
        return audioAttributesImplApi21;
    }

    public static void write(AudioAttributesImplApi21 audioAttributesImplApi21, k2.d dVar) {
        dVar.getClass();
        dVar.k(audioAttributesImplApi21.f3349a, 1);
        dVar.j(audioAttributesImplApi21.f3350b, 2);
    }
}

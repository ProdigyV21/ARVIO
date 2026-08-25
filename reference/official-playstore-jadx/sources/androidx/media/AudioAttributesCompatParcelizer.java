package androidx.media;

/* JADX INFO: loaded from: classes3.dex */
public class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(k2.d dVar) {
        AudioAttributesCompat audioAttributesCompat = new AudioAttributesCompat();
        k2.f fVarH = audioAttributesCompat.f3348a;
        if (dVar.e(1)) {
            fVarH = dVar.h();
        }
        audioAttributesCompat.f3348a = (AudioAttributesImpl) fVarH;
        return audioAttributesCompat;
    }

    public static void write(AudioAttributesCompat audioAttributesCompat, k2.d dVar) {
        dVar.getClass();
        AudioAttributesImpl audioAttributesImpl = audioAttributesCompat.f3348a;
        dVar.i(1);
        dVar.l(audioAttributesImpl);
    }
}

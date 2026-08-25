package androidx.media;

/* JADX INFO: loaded from: classes3.dex */
public class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(k2.d dVar) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.f3351a = dVar.f(audioAttributesImplBase.f3351a, 1);
        audioAttributesImplBase.f3352b = dVar.f(audioAttributesImplBase.f3352b, 2);
        audioAttributesImplBase.f3353c = dVar.f(audioAttributesImplBase.f3353c, 3);
        audioAttributesImplBase.f3354d = dVar.f(audioAttributesImplBase.f3354d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, k2.d dVar) {
        dVar.getClass();
        dVar.j(audioAttributesImplBase.f3351a, 1);
        dVar.j(audioAttributesImplBase.f3352b, 2);
        dVar.j(audioAttributesImplBase.f3353c, 3);
        dVar.j(audioAttributesImplBase.f3354d, 4);
    }
}

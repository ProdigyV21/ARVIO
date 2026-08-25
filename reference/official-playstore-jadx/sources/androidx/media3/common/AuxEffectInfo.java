package androidx.media3.common;

/* JADX INFO: loaded from: classes3.dex */
public final class AuxEffectInfo {
    public static final int NO_AUX_EFFECT_ID = 0;
    public final int effectId;
    public final float sendLevel;

    public AuxEffectInfo(int i10, float f10) {
        this.effectId = i10;
        this.sendLevel = f10;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && AuxEffectInfo.class == obj.getClass()) {
            AuxEffectInfo auxEffectInfo = (AuxEffectInfo) obj;
            if (this.effectId == auxEffectInfo.effectId && Float.compare(auxEffectInfo.sendLevel, this.sendLevel) == 0) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.sendLevel) + ((527 + this.effectId) * 31);
    }
}

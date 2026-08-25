package androidx.compose.ui.unit;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class c {
    public static float a(FontScalingLinear fontScalingLinear, long j10) {
        if (!TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(j10), TextUnitType.INSTANCE.m5898getSpUIouoOA())) {
            throw new IllegalStateException("Only Sp can convert to Px");
        }
        return Dp.m5678constructorimpl(fontScalingLinear.getFontScale() * TextUnit.m5865getValueimpl(j10));
    }

    public static long b(FontScalingLinear fontScalingLinear, float f10) {
        return TextUnitKt.getSp(f10 / fontScalingLinear.getFontScale());
    }
}

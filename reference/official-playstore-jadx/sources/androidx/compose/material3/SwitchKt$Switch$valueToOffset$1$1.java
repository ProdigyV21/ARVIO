package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "value", "", "invoke", "(Z)Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SwitchKt$Switch$valueToOffset$1$1 extends r implements l<Boolean, Float> {
    final /* synthetic */ float $maxBound;
    final /* synthetic */ float $minBound;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwitchKt$Switch$valueToOffset$1$1(float f10, float f11) {
        super(1);
        this.$maxBound = f10;
        this.$minBound = f11;
    }

    public final Float invoke(boolean z) {
        return Float.valueOf(z ? this.$maxBound : this.$minBound);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Boolean) obj).booleanValue());
    }
}

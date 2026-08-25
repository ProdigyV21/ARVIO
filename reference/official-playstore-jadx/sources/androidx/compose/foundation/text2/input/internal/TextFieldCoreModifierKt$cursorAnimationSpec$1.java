package androidx.compose.foundation.text2.input.internal;

import androidx.compose.animation.core.KeyframesSpec;
import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;", "", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldCoreModifierKt$cursorAnimationSpec$1 extends r implements l<KeyframesSpec.KeyframesSpecConfig<Float>, t0> {
    public static final TextFieldCoreModifierKt$cursorAnimationSpec$1 INSTANCE = new TextFieldCoreModifierKt$cursorAnimationSpec$1();

    public TextFieldCoreModifierKt$cursorAnimationSpec$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((KeyframesSpec.KeyframesSpecConfig<Float>) obj);
        return t0.f22605a;
    }

    public final void invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
        keyframesSpecConfig.setDurationMillis(1000);
        Float fValueOf = Float.valueOf(1.0f);
        keyframesSpecConfig.at(fValueOf, 0);
        keyframesSpecConfig.at(fValueOf, 499);
        Float fValueOf2 = Float.valueOf(0.0f);
        keyframesSpecConfig.at(fValueOf2, 500);
        keyframesSpecConfig.at(fValueOf2, MediaError.DetailedErrorCode.GENERIC);
    }
}

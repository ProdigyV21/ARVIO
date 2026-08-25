package androidx.compose.foundation.gestures;

import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "z", "Landroidx/compose/ui/geometry/Offset;", TtmlNode.TAG_P, "r", "Lx6/t0;", "invoke-d-4ec7I", "(FJF)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TransformableStateKt$rememberTransformableState$1$1 extends r implements q<Float, Offset, Float, t0> {
    final /* synthetic */ State<q<Float, Offset, Float, t0>> $lambdaState;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TransformableStateKt$rememberTransformableState$1$1(State<? extends q<? super Float, ? super Offset, ? super Float, t0>> state) {
        super(3);
        this.$lambdaState = state;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        m396invoked4ec7I(((Number) obj).floatValue(), ((Offset) obj2).getPackedValue(), ((Number) obj3).floatValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
    public final void m396invoked4ec7I(float f10, long j10, float f11) {
        this.$lambdaState.getValue().invoke(Float.valueOf(f10), Offset.m3225boximpl(j10), Float.valueOf(f11));
    }
}

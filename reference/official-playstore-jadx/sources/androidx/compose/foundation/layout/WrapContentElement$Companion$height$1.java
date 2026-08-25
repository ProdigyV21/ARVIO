package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntOffset;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/IntSize;", "<anonymous parameter 1>", "Landroidx/compose/ui/unit/LayoutDirection;", "invoke-5SAbXVA", "(JLandroidx/compose/ui/unit/LayoutDirection;)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class WrapContentElement$Companion$height$1 extends r implements p<IntSize, LayoutDirection, IntOffset> {
    final /* synthetic */ Alignment.Vertical $align;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WrapContentElement$Companion$height$1(Alignment.Vertical vertical) {
        super(2);
        this.$align = vertical;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return IntOffset.m5793boximpl(m630invoke5SAbXVA(((IntSize) obj).getPackedValue(), (LayoutDirection) obj2));
    }

    /* JADX INFO: renamed from: invoke-5SAbXVA, reason: not valid java name */
    public final long m630invoke5SAbXVA(long j10, LayoutDirection layoutDirection) {
        return IntOffsetKt.IntOffset(0, this.$align.align(0, IntSize.m5843getHeightimpl(j10)));
    }
}

package androidx.compose.material3;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.PointMode;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SliderDefaults$Track$1$1 extends r implements l<DrawScope, t0> {
    final /* synthetic */ long $activeTickColor;
    final /* synthetic */ long $activeTrackColor;
    final /* synthetic */ long $inactiveTickColor;
    final /* synthetic */ long $inactiveTrackColor;
    final /* synthetic */ SliderPositions $sliderPositions;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SliderDefaults$Track$1$1(long j10, SliderPositions sliderPositions, long j11, long j12, long j13) {
        super(1);
        this.$inactiveTrackColor = j10;
        this.$sliderPositions = sliderPositions;
        this.$activeTrackColor = j11;
        this.$inactiveTickColor = j12;
        this.$activeTickColor = j13;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(DrawScope drawScope) {
        long j10;
        long j11;
        boolean z = drawScope.getLayoutDirection() == LayoutDirection.Rtl;
        long jOffset = OffsetKt.Offset(0.0f, Offset.m3237getYimpl(drawScope.mo3915getCenterF1C5BW0()));
        long jOffset2 = OffsetKt.Offset(Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()), Offset.m3237getYimpl(drawScope.mo3915getCenterF1C5BW0()));
        long j12 = jOffset;
        long j13 = z ? jOffset2 : j12;
        if (!z) {
            j12 = jOffset2;
        }
        float fMo285toPx0680j_4 = drawScope.mo285toPx0680j_4(SliderKt.TickSize);
        float fMo285toPx0680j_42 = drawScope.mo285toPx0680j_4(SliderKt.getTrackHeight());
        long j14 = this.$inactiveTrackColor;
        StrokeCap.Companion companion = StrokeCap.INSTANCE;
        androidx.compose.ui.graphics.drawscope.c.C(drawScope, j14, j13, j12, fMo285toPx0680j_42, companion.m3804getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
        long j15 = j13;
        long j16 = j12;
        androidx.compose.ui.graphics.drawscope.c.C(drawScope, this.$activeTrackColor, OffsetKt.Offset((((Number) this.$sliderPositions.getActiveRange().getStart()).floatValue() * (Offset.m3236getXimpl(j12) - Offset.m3236getXimpl(j13))) + Offset.m3236getXimpl(j13), Offset.m3237getYimpl(drawScope.mo3915getCenterF1C5BW0())), OffsetKt.Offset((((Number) this.$sliderPositions.getActiveRange().b()).floatValue() * (Offset.m3236getXimpl(j12) - Offset.m3236getXimpl(j13))) + Offset.m3236getXimpl(j13), Offset.m3237getYimpl(drawScope.mo3915getCenterF1C5BW0())), fMo285toPx0680j_42, companion.m3804getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
        float[] tickFractions = this.$sliderPositions.getTickFractions();
        SliderPositions sliderPositions = this.$sliderPositions;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int length = tickFractions.length;
        for (int i10 = 0; i10 < length; i10++) {
            float f10 = tickFractions[i10];
            Boolean boolValueOf = Boolean.valueOf(f10 > ((Number) sliderPositions.getActiveRange().b()).floatValue() || f10 < ((Number) sliderPositions.getActiveRange().getStart()).floatValue());
            Object arrayList = linkedHashMap.get(boolValueOf);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(boolValueOf, arrayList);
            }
            ((List) arrayList).add(Float.valueOf(f10));
        }
        long j17 = this.$inactiveTickColor;
        long j18 = this.$activeTickColor;
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            boolean zBooleanValue = ((Boolean) entry.getKey()).booleanValue();
            List list = (List) entry.getValue();
            long j19 = j18;
            ArrayList arrayList2 = new ArrayList(list.size());
            int size = list.size();
            for (int i11 = 0; i11 < size; i11++) {
                arrayList2.add(Offset.m3225boximpl(OffsetKt.Offset(Offset.m3236getXimpl(OffsetKt.m3259lerpWko1d7g(j15, j16, ((Number) list.get(i11)).floatValue())), Offset.m3237getYimpl(drawScope.mo3915getCenterF1C5BW0()))));
            }
            long j20 = j15;
            long j21 = j16;
            int iM3756getPointsr_lszbg = PointMode.INSTANCE.m3756getPointsr_lszbg();
            if (zBooleanValue) {
                j11 = j19;
                j10 = j17;
            } else {
                j10 = j19;
                j11 = j10;
            }
            androidx.compose.ui.graphics.drawscope.c.H(drawScope, arrayList2, iM3756getPointsr_lszbg, j10, fMo285toPx0680j_4, StrokeCap.INSTANCE.m3804getRoundKaPHkGw(), null, 0.0f, null, 0, 480, null);
            j16 = j21;
            j18 = j11;
            j15 = j20;
        }
    }
}

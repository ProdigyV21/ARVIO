package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.i;
import java.util.ArrayList;
import java.util.List;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J2\u0010\u000f\u001a\u00020\f*\u00020\u00062\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R0\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/material3/SegmentedButtonContentMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "Lka/k0;", "scope", "<init>", "(Lka/k0;)V", "Landroidx/compose/ui/layout/MeasureScope;", "", "Landroidx/compose/ui/layout/Measurable;", "measurables", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Lka/k0;", "getScope", "()Lka/k0;", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "animatable", "Landroidx/compose/animation/core/Animatable;", "getAnimatable", "()Landroidx/compose/animation/core/Animatable;", "setAnimatable", "(Landroidx/compose/animation/core/Animatable;)V", "initialOffset", "Ljava/lang/Integer;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SegmentedButtonContentMeasurePolicy implements MultiContentMeasurePolicy {
    public static final int $stable = 0;
    private Animatable<Integer, AnimationVector1D> animatable;
    private Integer initialOffset;
    private final k0 scope;

    public SegmentedButtonContentMeasurePolicy(k0 k0Var) {
        this.scope = k0Var;
    }

    public final Animatable<Integer, AnimationVector1D> getAnimatable() {
        return this.animatable;
    }

    public final k0 getScope() {
        return this.scope;
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        return i.a(this, intrinsicMeasureScope, list, i10);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        return i.b(this, intrinsicMeasureScope, list, i10);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo1658measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j10) {
        Object obj;
        Object obj2;
        Object obj3;
        List<? extends Measurable> list2 = list.get(0);
        int i10 = 1;
        List<? extends Measurable> list3 = list.get(1);
        ArrayList arrayList = new ArrayList(list2.size());
        int size = list2.size();
        for (int i11 = 0; i11 < size; i11++) {
            arrayList.add(list2.get(i11).mo4631measureBRTryo0(j10));
        }
        if (arrayList.isEmpty()) {
            obj = null;
        } else {
            obj = arrayList.get(0);
            int width = ((Placeable) obj).getWidth();
            int iW = t7.a.w(arrayList);
            if (1 <= iW) {
                int i12 = 1;
                while (true) {
                    Object obj4 = arrayList.get(i12);
                    int width2 = ((Placeable) obj4).getWidth();
                    if (width < width2) {
                        obj = obj4;
                        width = width2;
                    }
                    if (i12 == iW) {
                        break;
                    }
                    i12++;
                }
            }
        }
        Placeable placeable = (Placeable) obj;
        int width3 = placeable != null ? placeable.getWidth() : 0;
        ArrayList arrayList2 = new ArrayList(list3.size());
        int size2 = list3.size();
        for (int i13 = 0; i13 < size2; i13++) {
            arrayList2.add(list3.get(i13).mo4631measureBRTryo0(j10));
        }
        if (arrayList2.isEmpty()) {
            obj2 = null;
        } else {
            obj2 = arrayList2.get(0);
            int width4 = ((Placeable) obj2).getWidth();
            int iW2 = t7.a.w(arrayList2);
            if (1 <= iW2) {
                int i14 = 1;
                while (true) {
                    Object obj5 = arrayList2.get(i14);
                    int width5 = ((Placeable) obj5).getWidth();
                    if (width4 < width5) {
                        obj2 = obj5;
                        width4 = width5;
                    }
                    if (i14 == iW2) {
                        break;
                    }
                    i14++;
                }
            }
        }
        Placeable placeable2 = (Placeable) obj2;
        Integer numValueOf = placeable2 != null ? Integer.valueOf(placeable2.getWidth()) : null;
        if (arrayList2.isEmpty()) {
            obj3 = null;
        } else {
            obj3 = arrayList2.get(0);
            int height = ((Placeable) obj3).getHeight();
            int iW3 = t7.a.w(arrayList2);
            if (1 <= iW3) {
                while (true) {
                    Object obj6 = arrayList2.get(i10);
                    int height2 = ((Placeable) obj6).getHeight();
                    if (height < height2) {
                        obj3 = obj6;
                        height = height2;
                    }
                    if (i10 == iW3) {
                        break;
                    }
                    i10++;
                }
            }
        }
        Placeable placeable3 = (Placeable) obj3;
        int height3 = placeable3 != null ? placeable3.getHeight() : 0;
        SegmentedButtonDefaults segmentedButtonDefaults = SegmentedButtonDefaults.INSTANCE;
        int iMo279roundToPx0680j_4 = measureScope.mo279roundToPx0680j_4(SegmentedButtonKt.IconSpacing) + Math.max(measureScope.mo279roundToPx0680j_4(segmentedButtonDefaults.m1844getIconSizeD9Ej5fM()), width3) + (numValueOf != null ? numValueOf.intValue() : 0);
        int i15 = width3 == 0 ? (-(measureScope.mo279roundToPx0680j_4(SegmentedButtonKt.IconSpacing) + measureScope.mo279roundToPx0680j_4(segmentedButtonDefaults.m1844getIconSizeD9Ej5fM()))) / 2 : 0;
        Integer num = this.initialOffset;
        if (num == null) {
            this.initialOffset = Integer.valueOf(i15);
        } else {
            Animatable<Integer, AnimationVector1D> animatable = this.animatable;
            if (animatable == null) {
                Animatable<Integer, AnimationVector1D> animatable2 = new Animatable<>(num, VectorConvertersKt.getVectorConverter(n.f19749a), null, null, 12, null);
                this.animatable = animatable2;
                animatable = animatable2;
            }
            if (animatable.getTargetValue().intValue() != i15) {
                m0.p(this.scope, null, 0, new SegmentedButtonContentMeasurePolicy$measure$1(animatable, i15, null), 3);
            }
        }
        int i16 = height3;
        return MeasureScope.CC.q(measureScope, iMo279roundToPx0680j_4, i16, null, new SegmentedButtonContentMeasurePolicy$measure$2(arrayList, measureScope, this, i15, arrayList2, i16), 4, null);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        return i.c(this, intrinsicMeasureScope, list, i10);
    }

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        return i.d(this, intrinsicMeasureScope, list, i10);
    }

    public final void setAnimatable(Animatable<Integer, AnimationVector1D> animatable) {
        this.animatable = animatable;
    }
}

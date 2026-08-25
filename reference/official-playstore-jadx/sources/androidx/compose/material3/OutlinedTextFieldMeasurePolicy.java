package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B3\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJC\u0010\u0016\u001a\u00020\u0012*\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0013\u001a\u00020\u00122\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u0014H\u0002¢\u0006\u0004\b\u0016\u0010\u0017JC\u0010\u0019\u001a\u00020\u0012*\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0018\u001a\u00020\u00122\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u0014H\u0002¢\u0006\u0004\b\u0019\u0010\u0017J,\u0010!\u001a\u00020\u001e*\u00020\u001a2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000f2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J)\u0010\"\u001a\u00020\u0012*\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0018\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\"\u0010#J)\u0010$\u001a\u00020\u0012*\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0018\u001a\u00020\u0012H\u0016¢\u0006\u0004\b$\u0010#J)\u0010%\u001a\u00020\u0012*\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b%\u0010#J)\u0010&\u001a\u00020\u0012*\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b&\u0010#R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010'R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010(R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010)R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010*\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Landroidx/compose/material3/OutlinedTextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Size;", "Lx6/t0;", "onLabelMeasured", "", "singleLine", "", "animationProgress", "Landroidx/compose/foundation/layout/PaddingValues;", "paddingValues", "<init>", "(Lr7/l;ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "measurables", "", "height", "Lkotlin/Function2;", "intrinsicMeasurer", "intrinsicWidth", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Ljava/util/List;ILr7/p;)I", "width", "intrinsicHeight", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "maxIntrinsicHeight", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Ljava/util/List;I)I", "minIntrinsicHeight", "maxIntrinsicWidth", "minIntrinsicWidth", "Lr7/l;", "Z", "F", "Landroidx/compose/foundation/layout/PaddingValues;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class OutlinedTextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final l<Size, t0> onLabelMeasured;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$maxIntrinsicHeight$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "intrinsicMeasurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "w", "invoke", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<IntrinsicMeasurable, Integer, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2);
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
        }

        public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i10) {
            return Integer.valueOf(intrinsicMeasurable.maxIntrinsicHeight(i10));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$maxIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "intrinsicMeasurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", CmcdData.STREAMING_FORMAT_HLS, "invoke", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05321 extends r implements p<IntrinsicMeasurable, Integer, Integer> {
        public static final C05321 INSTANCE = new C05321();

        public C05321() {
            super(2);
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
        }

        public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i10) {
            return Integer.valueOf(intrinsicMeasurable.maxIntrinsicWidth(i10));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$minIntrinsicHeight$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "intrinsicMeasurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "w", "invoke", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05331 extends r implements p<IntrinsicMeasurable, Integer, Integer> {
        public static final C05331 INSTANCE = new C05331();

        public C05331() {
            super(2);
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
        }

        public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i10) {
            return Integer.valueOf(intrinsicMeasurable.minIntrinsicHeight(i10));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$minIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "intrinsicMeasurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", CmcdData.STREAMING_FORMAT_HLS, "invoke", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05341 extends r implements p<IntrinsicMeasurable, Integer, Integer> {
        public static final C05341 INSTANCE = new C05341();

        public C05341() {
            super(2);
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue());
        }

        public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i10) {
            return Integer.valueOf(intrinsicMeasurable.minIntrinsicWidth(i10));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OutlinedTextFieldMeasurePolicy(l<? super Size, t0> lVar, boolean z, float f10, PaddingValues paddingValues) {
        this.onLabelMeasured = lVar;
        this.singleLine = z;
        this.animationProgress = f10;
        this.paddingValues = paddingValues;
    }

    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10, p<? super IntrinsicMeasurable, ? super Integer, Integer> pVar) {
        IntrinsicMeasurable intrinsicMeasurable;
        int iSubstractConstraintSafely;
        int iIntValue;
        IntrinsicMeasurable intrinsicMeasurable2;
        int iIntValue2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        int iIntValue3;
        IntrinsicMeasurable intrinsicMeasurable5;
        int i11;
        IntrinsicMeasurable intrinsicMeasurable6;
        IntrinsicMeasurable intrinsicMeasurable7;
        int size = list.size();
        int i12 = 0;
        while (true) {
            if (i12 >= size) {
                intrinsicMeasurable = null;
                break;
            }
            intrinsicMeasurable = list.get(i12);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable), TextFieldImplKt.LeadingId)) {
                break;
            }
            i12++;
        }
        IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable;
        if (intrinsicMeasurable8 != null) {
            iSubstractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(i10, intrinsicMeasurable8.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue = ((Number) pVar.invoke(intrinsicMeasurable8, Integer.valueOf(i10))).intValue();
        } else {
            iSubstractConstraintSafely = i10;
            iIntValue = 0;
        }
        int size2 = list.size();
        int i13 = 0;
        while (true) {
            if (i13 >= size2) {
                intrinsicMeasurable2 = null;
                break;
            }
            intrinsicMeasurable2 = list.get(i13);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i13++;
        }
        IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable2;
        if (intrinsicMeasurable9 != null) {
            iSubstractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable9.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue2 = ((Number) pVar.invoke(intrinsicMeasurable9, Integer.valueOf(i10))).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list.size();
        int i14 = 0;
        while (true) {
            if (i14 >= size3) {
                intrinsicMeasurable3 = null;
                break;
            }
            intrinsicMeasurable3 = list.get(i14);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), TextFieldImplKt.LabelId)) {
                break;
            }
            i14++;
        }
        IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable3;
        int iIntValue4 = intrinsicMeasurable10 != null ? ((Number) pVar.invoke(intrinsicMeasurable10, Integer.valueOf(MathHelpersKt.lerp(iSubstractConstraintSafely, i10, this.animationProgress)))).intValue() : 0;
        int size4 = list.size();
        int i15 = 0;
        while (true) {
            if (i15 >= size4) {
                intrinsicMeasurable4 = null;
                break;
            }
            intrinsicMeasurable4 = list.get(i15);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                break;
            }
            i15++;
        }
        IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable4;
        if (intrinsicMeasurable11 != null) {
            iIntValue3 = ((Number) pVar.invoke(intrinsicMeasurable11, Integer.valueOf(iSubstractConstraintSafely))).intValue();
            iSubstractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable11.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            iIntValue3 = 0;
        }
        int size5 = list.size();
        int i16 = 0;
        while (true) {
            if (i16 >= size5) {
                intrinsicMeasurable5 = null;
                break;
            }
            intrinsicMeasurable5 = list.get(i16);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                break;
            }
            i16++;
        }
        IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable5;
        if (intrinsicMeasurable12 != null) {
            int iIntValue5 = ((Number) pVar.invoke(intrinsicMeasurable12, Integer.valueOf(iSubstractConstraintSafely))).intValue();
            iSubstractConstraintSafely = OutlinedTextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable12.maxIntrinsicWidth(Integer.MAX_VALUE));
            i11 = iIntValue5;
        } else {
            i11 = 0;
        }
        int size6 = list.size();
        for (int i17 = 0; i17 < size6; i17++) {
            IntrinsicMeasurable intrinsicMeasurable13 = list.get(i17);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable13), TextFieldImplKt.TextFieldId)) {
                int iIntValue6 = ((Number) pVar.invoke(intrinsicMeasurable13, Integer.valueOf(iSubstractConstraintSafely))).intValue();
                int size7 = list.size();
                int i18 = 0;
                while (true) {
                    if (i18 >= size7) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = list.get(i18);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i18++;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable6;
                int iIntValue7 = intrinsicMeasurable14 != null ? ((Number) pVar.invoke(intrinsicMeasurable14, Integer.valueOf(iSubstractConstraintSafely))).intValue() : 0;
                int size8 = list.size();
                int i19 = 0;
                while (true) {
                    if (i19 >= size8) {
                        intrinsicMeasurable7 = null;
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable15 = list.get(i19);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable15), TextFieldImplKt.SupportingId)) {
                        intrinsicMeasurable7 = intrinsicMeasurable15;
                        break;
                    }
                    i19++;
                }
                IntrinsicMeasurable intrinsicMeasurable16 = intrinsicMeasurable7;
                return OutlinedTextFieldKt.m1753calculateHeightmKXJcVc(iIntValue, iIntValue2, iIntValue3, i11, iIntValue6, iIntValue4, iIntValue7, intrinsicMeasurable16 != null ? ((Number) pVar.invoke(intrinsicMeasurable16, Integer.valueOf(i10))).intValue() : 0, this.animationProgress, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10, p<? super IntrinsicMeasurable, ? super Integer, Integer> pVar) {
        IntrinsicMeasurable intrinsicMeasurable;
        IntrinsicMeasurable intrinsicMeasurable2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        IntrinsicMeasurable intrinsicMeasurable5;
        IntrinsicMeasurable intrinsicMeasurable6;
        int size = list.size();
        for (int i11 = 0; i11 < size; i11++) {
            IntrinsicMeasurable intrinsicMeasurable7 = list.get(i11);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable7), TextFieldImplKt.TextFieldId)) {
                int iIntValue = ((Number) pVar.invoke(intrinsicMeasurable7, Integer.valueOf(i10))).intValue();
                int size2 = list.size();
                int i12 = 0;
                while (true) {
                    intrinsicMeasurable = null;
                    if (i12 >= size2) {
                        intrinsicMeasurable2 = null;
                        break;
                    }
                    intrinsicMeasurable2 = list.get(i12);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), TextFieldImplKt.LabelId)) {
                        break;
                    }
                    i12++;
                }
                IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable2;
                int iIntValue2 = intrinsicMeasurable8 != null ? ((Number) pVar.invoke(intrinsicMeasurable8, Integer.valueOf(i10))).intValue() : 0;
                int size3 = list.size();
                int i13 = 0;
                while (true) {
                    if (i13 >= size3) {
                        intrinsicMeasurable3 = null;
                        break;
                    }
                    intrinsicMeasurable3 = list.get(i13);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), TextFieldImplKt.TrailingId)) {
                        break;
                    }
                    i13++;
                }
                IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable3;
                int iIntValue3 = intrinsicMeasurable9 != null ? ((Number) pVar.invoke(intrinsicMeasurable9, Integer.valueOf(i10))).intValue() : 0;
                int size4 = list.size();
                int i14 = 0;
                while (true) {
                    if (i14 >= size4) {
                        intrinsicMeasurable4 = null;
                        break;
                    }
                    intrinsicMeasurable4 = list.get(i14);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.LeadingId)) {
                        break;
                    }
                    i14++;
                }
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable4;
                int iIntValue4 = intrinsicMeasurable10 != null ? ((Number) pVar.invoke(intrinsicMeasurable10, Integer.valueOf(i10))).intValue() : 0;
                int size5 = list.size();
                int i15 = 0;
                while (true) {
                    if (i15 >= size5) {
                        intrinsicMeasurable5 = null;
                        break;
                    }
                    intrinsicMeasurable5 = list.get(i15);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.PrefixId)) {
                        break;
                    }
                    i15++;
                }
                IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable5;
                int iIntValue5 = intrinsicMeasurable11 != null ? ((Number) pVar.invoke(intrinsicMeasurable11, Integer.valueOf(i10))).intValue() : 0;
                int size6 = list.size();
                int i16 = 0;
                while (true) {
                    if (i16 >= size6) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = list.get(i16);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.SuffixId)) {
                        break;
                    }
                    i16++;
                }
                IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable6;
                int iIntValue6 = intrinsicMeasurable12 != null ? ((Number) pVar.invoke(intrinsicMeasurable12, Integer.valueOf(i10))).intValue() : 0;
                int size7 = list.size();
                int i17 = 0;
                while (true) {
                    if (i17 >= size7) {
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable13 = list.get(i17);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable13), TextFieldImplKt.PlaceholderId)) {
                        intrinsicMeasurable = intrinsicMeasurable13;
                        break;
                    }
                    i17++;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable;
                return OutlinedTextFieldKt.m1754calculateWidthDHJA7U0(iIntValue4, iIntValue3, iIntValue5, iIntValue6, iIntValue, iIntValue2, intrinsicMeasurable14 != null ? ((Number) pVar.invoke(intrinsicMeasurable14, Integer.valueOf(i10))).intValue() : 0, this.animationProgress, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return intrinsicHeight(intrinsicMeasureScope, list, i10, AnonymousClass1.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return intrinsicWidth(intrinsicMeasureScope, list, i10, C05321.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
        Measurable measurable;
        Measurable measurable2;
        Measurable measurable3;
        Measurable measurable4;
        Placeable placeable;
        Measurable measurable5;
        Measurable measurable6;
        Measurable measurable7;
        OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy = this;
        MeasureScope measureScope2 = measureScope;
        int iMo279roundToPx0680j_4 = measureScope2.mo279roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.getBottom());
        long jM5637copyZbe2FdA$default = Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 10, null);
        int size = list.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                measurable = null;
                break;
            }
            measurable = list.get(i10);
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable), TextFieldImplKt.LeadingId)) {
                break;
            }
            i10++;
        }
        Measurable measurable8 = measurable;
        Placeable placeableMo4631measureBRTryo0 = measurable8 != null ? measurable8.mo4631measureBRTryo0(jM5637copyZbe2FdA$default) : null;
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo0);
        int iMax = Math.max(0, TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo0));
        int size2 = list.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size2) {
                measurable2 = null;
                break;
            }
            measurable2 = list.get(i11);
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i11++;
        }
        Measurable measurable9 = measurable2;
        Placeable placeableMo4631measureBRTryo02 = measurable9 != null ? measurable9.mo4631measureBRTryo0(ConstraintsKt.m5663offsetNN6EwU$default(jM5637copyZbe2FdA$default, -iWidthOrZero, 0, 2, null)) : null;
        int iWidthOrZero2 = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo02) + iWidthOrZero;
        int iMax2 = Math.max(iMax, TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo02));
        int size3 = list.size();
        int i12 = 0;
        while (true) {
            if (i12 >= size3) {
                measurable3 = null;
                break;
            }
            measurable3 = list.get(i12);
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable3), TextFieldImplKt.PrefixId)) {
                break;
            }
            i12++;
        }
        Measurable measurable10 = measurable3;
        Placeable placeableMo4631measureBRTryo03 = measurable10 != null ? measurable10.mo4631measureBRTryo0(ConstraintsKt.m5663offsetNN6EwU$default(jM5637copyZbe2FdA$default, -iWidthOrZero2, 0, 2, null)) : null;
        int iWidthOrZero3 = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo03) + iWidthOrZero2;
        int iMax3 = Math.max(iMax2, TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo03));
        int size4 = list.size();
        int i13 = 0;
        while (true) {
            if (i13 >= size4) {
                measurable4 = null;
                break;
            }
            measurable4 = list.get(i13);
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable4), TextFieldImplKt.SuffixId)) {
                break;
            }
            i13++;
        }
        Measurable measurable11 = measurable4;
        Placeable placeableMo4631measureBRTryo04 = measurable11 != null ? measurable11.mo4631measureBRTryo0(ConstraintsKt.m5663offsetNN6EwU$default(jM5637copyZbe2FdA$default, -iWidthOrZero3, 0, 2, null)) : null;
        int iWidthOrZero4 = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo04) + iWidthOrZero3;
        int iMax4 = Math.max(iMax3, TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo04));
        int iMo279roundToPx0680j_42 = measureScope2.mo279roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.mo481calculateRightPaddingu2uoSUM(measureScope2.getLayoutDirection())) + measureScope2.mo279roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.mo480calculateLeftPaddingu2uoSUM(measureScope2.getLayoutDirection()));
        int i14 = -iWidthOrZero4;
        int iLerp = MathHelpersKt.lerp(i14 - iMo279roundToPx0680j_42, -iMo279roundToPx0680j_42, outlinedTextFieldMeasurePolicy.animationProgress);
        int i15 = -iMo279roundToPx0680j_4;
        long jM5662offsetNN6EwU = ConstraintsKt.m5662offsetNN6EwU(jM5637copyZbe2FdA$default, iLerp, i15);
        int size5 = list.size();
        int i16 = 0;
        while (true) {
            if (i16 >= size5) {
                placeable = placeableMo4631measureBRTryo0;
                measurable5 = null;
                break;
            }
            measurable5 = list.get(i16);
            int i17 = i16;
            placeable = placeableMo4631measureBRTryo0;
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable5), TextFieldImplKt.LabelId)) {
                break;
            }
            i16 = i17 + 1;
            placeableMo4631measureBRTryo0 = placeable;
        }
        Measurable measurable12 = measurable5;
        Placeable placeableMo4631measureBRTryo05 = measurable12 != null ? measurable12.mo4631measureBRTryo0(jM5662offsetNN6EwU) : null;
        if (placeableMo4631measureBRTryo05 != null) {
            outlinedTextFieldMeasurePolicy.onLabelMeasured.invoke(Size.m3293boximpl(SizeKt.Size(placeableMo4631measureBRTryo05.getWidth(), placeableMo4631measureBRTryo05.getHeight())));
        }
        int size6 = list.size();
        int i18 = 0;
        while (true) {
            if (i18 >= size6) {
                measurable6 = null;
                break;
            }
            measurable6 = list.get(i18);
            int i19 = size6;
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable6), TextFieldImplKt.SupportingId)) {
                break;
            }
            i18++;
            size6 = i19;
        }
        Measurable measurable13 = measurable6;
        int iMinIntrinsicHeight = measurable13 != null ? measurable13.minIntrinsicHeight(Constraints.m5648getMinWidthimpl(j10)) : 0;
        int iMax5 = Math.max(TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo05) / 2, measureScope2.mo279roundToPx0680j_4(outlinedTextFieldMeasurePolicy.paddingValues.getTop()));
        long jM5637copyZbe2FdA$default2 = Constraints.m5637copyZbe2FdA$default(ConstraintsKt.m5662offsetNN6EwU(j10, i14, (i15 - iMax5) - iMinIntrinsicHeight), 0, 0, 0, 0, 11, null);
        int size7 = list.size();
        Placeable placeable2 = placeableMo4631measureBRTryo04;
        int i20 = 0;
        while (i20 < size7) {
            Placeable placeable3 = placeableMo4631measureBRTryo03;
            Measurable measurable14 = list.get(i20);
            int i21 = size7;
            Placeable placeable4 = placeableMo4631measureBRTryo02;
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable14), TextFieldImplKt.TextFieldId)) {
                Placeable placeableMo4631measureBRTryo06 = measurable14.mo4631measureBRTryo0(jM5637copyZbe2FdA$default2);
                long jM5637copyZbe2FdA$default3 = Constraints.m5637copyZbe2FdA$default(jM5637copyZbe2FdA$default2, 0, 0, 0, 0, 14, null);
                int size8 = list.size();
                int i22 = 0;
                while (true) {
                    if (i22 >= size8) {
                        measurable7 = null;
                        break;
                    }
                    measurable7 = list.get(i22);
                    int i23 = size8;
                    int i24 = i22;
                    if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable7), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i22 = i24 + 1;
                    size8 = i23;
                }
                Measurable measurable15 = measurable7;
                Placeable placeableMo4631measureBRTryo07 = measurable15 != null ? measurable15.mo4631measureBRTryo0(jM5637copyZbe2FdA$default3) : null;
                int iMax6 = Math.max(iMax4, Math.max(TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo06), TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo07)) + iMax5 + iMo279roundToPx0680j_4);
                int iM1754calculateWidthDHJA7U0 = OutlinedTextFieldKt.m1754calculateWidthDHJA7U0(TextFieldImplKt.widthOrZero(placeable), TextFieldImplKt.widthOrZero(placeable4), TextFieldImplKt.widthOrZero(placeable3), TextFieldImplKt.widthOrZero(placeable2), placeableMo4631measureBRTryo06.getWidth(), TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo05), TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo07), outlinedTextFieldMeasurePolicy.animationProgress, j10, measureScope2.getDensity(), outlinedTextFieldMeasurePolicy.paddingValues);
                Placeable placeableMo4631measureBRTryo08 = measurable13 != null ? measurable13.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(ConstraintsKt.m5663offsetNN6EwU$default(jM5637copyZbe2FdA$default, 0, -iMax6, 1, null), 0, iM1754calculateWidthDHJA7U0, 0, 0, 9, null)) : null;
                int iHeightOrZero = TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo08);
                int iM1753calculateHeightmKXJcVc = OutlinedTextFieldKt.m1753calculateHeightmKXJcVc(TextFieldImplKt.heightOrZero(placeable), TextFieldImplKt.heightOrZero(placeable4), TextFieldImplKt.heightOrZero(placeable3), TextFieldImplKt.heightOrZero(placeable2), placeableMo4631measureBRTryo06.getHeight(), TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo05), TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo07), TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo08), outlinedTextFieldMeasurePolicy.animationProgress, j10, measureScope2.getDensity(), outlinedTextFieldMeasurePolicy.paddingValues);
                int i25 = iM1753calculateHeightmKXJcVc - iHeightOrZero;
                int size9 = list.size();
                int i26 = 0;
                while (i26 < size9) {
                    Measurable measurable16 = list.get(i26);
                    if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable16), TextFieldImplKt.ContainerId)) {
                        return MeasureScope.CC.q(measureScope, iM1754calculateWidthDHJA7U0, iM1753calculateHeightmKXJcVc, null, new OutlinedTextFieldMeasurePolicy$measure$2(iM1753calculateHeightmKXJcVc, iM1754calculateWidthDHJA7U0, placeable, placeable4, placeable3, placeable2, placeableMo4631measureBRTryo06, placeableMo4631measureBRTryo05, placeableMo4631measureBRTryo07, measurable16.mo4631measureBRTryo0(ConstraintsKt.Constraints(iM1754calculateWidthDHJA7U0 != Integer.MAX_VALUE ? iM1754calculateWidthDHJA7U0 : 0, iM1754calculateWidthDHJA7U0, i25 != Integer.MAX_VALUE ? i25 : 0, i25)), placeableMo4631measureBRTryo08, outlinedTextFieldMeasurePolicy, measureScope2), 4, null);
                    }
                    i26++;
                    outlinedTextFieldMeasurePolicy = this;
                    measureScope2 = measureScope;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i20++;
            outlinedTextFieldMeasurePolicy = this;
            measureScope2 = measureScope;
            size7 = i21;
            placeableMo4631measureBRTryo03 = placeable3;
            placeableMo4631measureBRTryo02 = placeable4;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return intrinsicHeight(intrinsicMeasureScope, list, i10, C05331.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return intrinsicWidth(intrinsicMeasureScope, list, i10, C05341.INSTANCE);
    }
}

package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
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
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ?\u0010\u0011\u001a\u00020\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000e\u001a\u00020\r2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u000fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012JC\u0010\u0015\u001a\u00020\r*\u00020\u00132\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0014\u001a\u00020\r2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u000fH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J,\u0010\u001e\u001a\u00020\u001b*\u00020\u00172\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00180\n2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ)\u0010\u001f\u001a\u00020\r*\u00020\u00132\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0014\u001a\u00020\rH\u0016¢\u0006\u0004\b\u001f\u0010 J)\u0010!\u001a\u00020\r*\u00020\u00132\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0014\u001a\u00020\rH\u0016¢\u0006\u0004\b!\u0010 J)\u0010\"\u001a\u00020\r*\u00020\u00132\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\"\u0010 J)\u0010#\u001a\u00020\r*\u00020\u00132\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b#\u0010 R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010$R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010%R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010&\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"Landroidx/compose/material3/TextFieldMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "", "singleLine", "", "animationProgress", "Landroidx/compose/foundation/layout/PaddingValues;", "paddingValues", "<init>", "(ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "measurables", "", "height", "Lkotlin/Function2;", "intrinsicMeasurer", "intrinsicWidth", "(Ljava/util/List;ILr7/p;)I", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "width", "intrinsicHeight", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Ljava/util/List;ILr7/p;)I", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "maxIntrinsicHeight", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Ljava/util/List;I)I", "minIntrinsicHeight", "maxIntrinsicWidth", "minIntrinsicWidth", "Z", "F", "Landroidx/compose/foundation/layout/PaddingValues;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class TextFieldMeasurePolicy implements MeasurePolicy {
    private final float animationProgress;
    private final PaddingValues paddingValues;
    private final boolean singleLine;

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldMeasurePolicy$maxIntrinsicHeight$1, reason: invalid class name */
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

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldMeasurePolicy$maxIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "intrinsicMeasurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", CmcdData.STREAMING_FORMAT_HLS, "invoke", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05671 extends r implements p<IntrinsicMeasurable, Integer, Integer> {
        public static final C05671 INSTANCE = new C05671();

        public C05671() {
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

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldMeasurePolicy$minIntrinsicHeight$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "intrinsicMeasurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "w", "invoke", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05681 extends r implements p<IntrinsicMeasurable, Integer, Integer> {
        public static final C05681 INSTANCE = new C05681();

        public C05681() {
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

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldMeasurePolicy$minIntrinsicWidth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "intrinsicMeasurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", CmcdData.STREAMING_FORMAT_HLS, "invoke", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05691 extends r implements p<IntrinsicMeasurable, Integer, Integer> {
        public static final C05691 INSTANCE = new C05691();

        public C05691() {
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

    public TextFieldMeasurePolicy(boolean z, float f10, PaddingValues paddingValues) {
        this.singleLine = z;
        this.animationProgress = f10;
        this.paddingValues = paddingValues;
    }

    private final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10, p<? super IntrinsicMeasurable, ? super Integer, Integer> pVar) {
        IntrinsicMeasurable intrinsicMeasurable;
        int i11;
        int iSubstractConstraintSafely;
        int iIntValue;
        IntrinsicMeasurable intrinsicMeasurable2;
        int iIntValue2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        int iIntValue3;
        IntrinsicMeasurable intrinsicMeasurable5;
        int i12;
        IntrinsicMeasurable intrinsicMeasurable6;
        IntrinsicMeasurable intrinsicMeasurable7;
        int size = list.size();
        int i13 = 0;
        while (true) {
            if (i13 >= size) {
                intrinsicMeasurable = null;
                break;
            }
            intrinsicMeasurable = list.get(i13);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable), TextFieldImplKt.LeadingId)) {
                break;
            }
            i13++;
        }
        IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable;
        if (intrinsicMeasurable8 != null) {
            i11 = i10;
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(i11, intrinsicMeasurable8.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue = ((Number) pVar.invoke(intrinsicMeasurable8, Integer.valueOf(i11))).intValue();
        } else {
            i11 = i10;
            iSubstractConstraintSafely = i11;
            iIntValue = 0;
        }
        int size2 = list.size();
        int i14 = 0;
        while (true) {
            if (i14 >= size2) {
                intrinsicMeasurable2 = null;
                break;
            }
            intrinsicMeasurable2 = list.get(i14);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i14++;
        }
        IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable2;
        if (intrinsicMeasurable9 != null) {
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable9.maxIntrinsicWidth(Integer.MAX_VALUE));
            iIntValue2 = ((Number) pVar.invoke(intrinsicMeasurable9, Integer.valueOf(i11))).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list.size();
        int i15 = 0;
        while (true) {
            if (i15 >= size3) {
                intrinsicMeasurable3 = null;
                break;
            }
            intrinsicMeasurable3 = list.get(i15);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), TextFieldImplKt.LabelId)) {
                break;
            }
            i15++;
        }
        IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable3;
        int iIntValue4 = intrinsicMeasurable10 != null ? ((Number) pVar.invoke(intrinsicMeasurable10, Integer.valueOf(iSubstractConstraintSafely))).intValue() : 0;
        int size4 = list.size();
        int i16 = 0;
        while (true) {
            if (i16 >= size4) {
                intrinsicMeasurable4 = null;
                break;
            }
            intrinsicMeasurable4 = list.get(i16);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                break;
            }
            i16++;
        }
        IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable4;
        if (intrinsicMeasurable11 != null) {
            iIntValue3 = ((Number) pVar.invoke(intrinsicMeasurable11, Integer.valueOf(iSubstractConstraintSafely))).intValue();
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable11.maxIntrinsicWidth(Integer.MAX_VALUE));
        } else {
            iIntValue3 = 0;
        }
        int size5 = list.size();
        int i17 = 0;
        while (true) {
            if (i17 >= size5) {
                intrinsicMeasurable5 = null;
                break;
            }
            intrinsicMeasurable5 = list.get(i17);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                break;
            }
            i17++;
        }
        IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable5;
        if (intrinsicMeasurable12 != null) {
            int iIntValue5 = ((Number) pVar.invoke(intrinsicMeasurable12, Integer.valueOf(iSubstractConstraintSafely))).intValue();
            iSubstractConstraintSafely = TextFieldKt.substractConstraintSafely(iSubstractConstraintSafely, intrinsicMeasurable12.maxIntrinsicWidth(Integer.MAX_VALUE));
            i12 = iIntValue5;
        } else {
            i12 = 0;
        }
        int size6 = list.size();
        for (int i18 = 0; i18 < size6; i18++) {
            IntrinsicMeasurable intrinsicMeasurable13 = list.get(i18);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable13), TextFieldImplKt.TextFieldId)) {
                int iIntValue6 = ((Number) pVar.invoke(intrinsicMeasurable13, Integer.valueOf(iSubstractConstraintSafely))).intValue();
                int size7 = list.size();
                int i19 = 0;
                while (true) {
                    if (i19 >= size7) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = list.get(i19);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i19++;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable6;
                int iIntValue7 = intrinsicMeasurable14 != null ? ((Number) pVar.invoke(intrinsicMeasurable14, Integer.valueOf(iSubstractConstraintSafely))).intValue() : 0;
                int size8 = list.size();
                int i20 = 0;
                while (true) {
                    if (i20 >= size8) {
                        intrinsicMeasurable7 = null;
                        break;
                    }
                    intrinsicMeasurable7 = list.get(i20);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable7), TextFieldImplKt.SupportingId)) {
                        break;
                    }
                    i20++;
                }
                IntrinsicMeasurable intrinsicMeasurable15 = intrinsicMeasurable7;
                return TextFieldKt.m2149calculateHeightmKXJcVc(iIntValue6, iIntValue4, iIntValue, iIntValue2, iIntValue3, i12, iIntValue7, intrinsicMeasurable15 != null ? ((Number) pVar.invoke(intrinsicMeasurable15, Integer.valueOf(i11))).intValue() : 0, this.animationProgress, TextFieldImplKt.getZeroConstraints(), intrinsicMeasureScope.getDensity(), this.paddingValues);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final int intrinsicWidth(List<? extends IntrinsicMeasurable> measurables, int height, p<? super IntrinsicMeasurable, ? super Integer, Integer> intrinsicMeasurer) {
        IntrinsicMeasurable intrinsicMeasurable;
        IntrinsicMeasurable intrinsicMeasurable2;
        IntrinsicMeasurable intrinsicMeasurable3;
        IntrinsicMeasurable intrinsicMeasurable4;
        IntrinsicMeasurable intrinsicMeasurable5;
        IntrinsicMeasurable intrinsicMeasurable6;
        int size = measurables.size();
        for (int i10 = 0; i10 < size; i10++) {
            IntrinsicMeasurable intrinsicMeasurable7 = measurables.get(i10);
            if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable7), TextFieldImplKt.TextFieldId)) {
                int iIntValue = ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable7, Integer.valueOf(height))).intValue();
                int size2 = measurables.size();
                int i11 = 0;
                while (true) {
                    intrinsicMeasurable = null;
                    if (i11 >= size2) {
                        intrinsicMeasurable2 = null;
                        break;
                    }
                    intrinsicMeasurable2 = measurables.get(i11);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable2), TextFieldImplKt.LabelId)) {
                        break;
                    }
                    i11++;
                }
                IntrinsicMeasurable intrinsicMeasurable8 = intrinsicMeasurable2;
                int iIntValue2 = intrinsicMeasurable8 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable8, Integer.valueOf(height))).intValue() : 0;
                int size3 = measurables.size();
                int i12 = 0;
                while (true) {
                    if (i12 >= size3) {
                        intrinsicMeasurable3 = null;
                        break;
                    }
                    intrinsicMeasurable3 = measurables.get(i12);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable3), TextFieldImplKt.TrailingId)) {
                        break;
                    }
                    i12++;
                }
                IntrinsicMeasurable intrinsicMeasurable9 = intrinsicMeasurable3;
                int iIntValue3 = intrinsicMeasurable9 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable9, Integer.valueOf(height))).intValue() : 0;
                int size4 = measurables.size();
                int i13 = 0;
                while (true) {
                    if (i13 >= size4) {
                        intrinsicMeasurable4 = null;
                        break;
                    }
                    intrinsicMeasurable4 = measurables.get(i13);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable4), TextFieldImplKt.PrefixId)) {
                        break;
                    }
                    i13++;
                }
                IntrinsicMeasurable intrinsicMeasurable10 = intrinsicMeasurable4;
                int iIntValue4 = intrinsicMeasurable10 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable10, Integer.valueOf(height))).intValue() : 0;
                int size5 = measurables.size();
                int i14 = 0;
                while (true) {
                    if (i14 >= size5) {
                        intrinsicMeasurable5 = null;
                        break;
                    }
                    intrinsicMeasurable5 = measurables.get(i14);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable5), TextFieldImplKt.SuffixId)) {
                        break;
                    }
                    i14++;
                }
                IntrinsicMeasurable intrinsicMeasurable11 = intrinsicMeasurable5;
                int iIntValue5 = intrinsicMeasurable11 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable11, Integer.valueOf(height))).intValue() : 0;
                int size6 = measurables.size();
                int i15 = 0;
                while (true) {
                    if (i15 >= size6) {
                        intrinsicMeasurable6 = null;
                        break;
                    }
                    intrinsicMeasurable6 = measurables.get(i15);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable6), TextFieldImplKt.LeadingId)) {
                        break;
                    }
                    i15++;
                }
                IntrinsicMeasurable intrinsicMeasurable12 = intrinsicMeasurable6;
                int iIntValue6 = intrinsicMeasurable12 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable12, Integer.valueOf(height))).intValue() : 0;
                int size7 = measurables.size();
                int i16 = 0;
                while (true) {
                    if (i16 >= size7) {
                        break;
                    }
                    IntrinsicMeasurable intrinsicMeasurable13 = measurables.get(i16);
                    if (kotlin.jvm.internal.p.a(TextFieldImplKt.getLayoutId(intrinsicMeasurable13), TextFieldImplKt.PlaceholderId)) {
                        intrinsicMeasurable = intrinsicMeasurable13;
                        break;
                    }
                    i16++;
                }
                IntrinsicMeasurable intrinsicMeasurable14 = intrinsicMeasurable;
                return TextFieldKt.m2150calculateWidthyeHjK3Y(iIntValue6, iIntValue3, iIntValue4, iIntValue5, iIntValue, iIntValue2, intrinsicMeasurable14 != null ? ((Number) intrinsicMeasurer.invoke(intrinsicMeasurable14, Integer.valueOf(height))).intValue() : 0, TextFieldImplKt.getZeroConstraints());
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
        return intrinsicWidth(list, i10, C05671.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
        Measurable measurable;
        Measurable measurable2;
        long j11;
        Placeable placeableMo4631measureBRTryo0;
        Measurable measurable3;
        Measurable measurable4;
        Placeable placeable;
        int i10;
        Placeable placeable2;
        long j12;
        Placeable placeableMo4631measureBRTryo02;
        Placeable placeable3;
        Measurable measurable5;
        Placeable placeable4;
        Measurable measurable6;
        Placeable placeable5;
        Measurable measurable7;
        TextFieldMeasurePolicy textFieldMeasurePolicy = this;
        MeasureScope measureScope2 = measureScope;
        List<? extends Measurable> list2 = list;
        int iMo279roundToPx0680j_4 = measureScope2.mo279roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getTop());
        int iMo279roundToPx0680j_42 = measureScope2.mo279roundToPx0680j_4(textFieldMeasurePolicy.paddingValues.getBottom());
        long jM5637copyZbe2FdA$default = Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 10, null);
        int size = list2.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size) {
                measurable = null;
                break;
            }
            measurable = list2.get(i11);
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable), TextFieldImplKt.LeadingId)) {
                break;
            }
            i11++;
        }
        Measurable measurable8 = measurable;
        Placeable placeableMo4631measureBRTryo03 = measurable8 != null ? measurable8.mo4631measureBRTryo0(jM5637copyZbe2FdA$default) : null;
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo03);
        int iMax = Math.max(0, TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo03));
        int size2 = list2.size();
        int i12 = 0;
        while (true) {
            if (i12 >= size2) {
                measurable2 = null;
                break;
            }
            measurable2 = list2.get(i12);
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable2), TextFieldImplKt.TrailingId)) {
                break;
            }
            i12++;
        }
        Measurable measurable9 = measurable2;
        if (measurable9 != null) {
            j11 = jM5637copyZbe2FdA$default;
            placeableMo4631measureBRTryo0 = measurable9.mo4631measureBRTryo0(ConstraintsKt.m5663offsetNN6EwU$default(j11, -iWidthOrZero, 0, 2, null));
        } else {
            j11 = jM5637copyZbe2FdA$default;
            placeableMo4631measureBRTryo0 = null;
        }
        int iWidthOrZero2 = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo0) + iWidthOrZero;
        int iMax2 = Math.max(iMax, TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo0));
        int size3 = list2.size();
        int i13 = 0;
        while (true) {
            if (i13 >= size3) {
                measurable3 = null;
                break;
            }
            measurable3 = list2.get(i13);
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable3), TextFieldImplKt.PrefixId)) {
                break;
            }
            i13++;
        }
        Measurable measurable10 = measurable3;
        Placeable placeableMo4631measureBRTryo04 = measurable10 != null ? measurable10.mo4631measureBRTryo0(ConstraintsKt.m5663offsetNN6EwU$default(j11, -iWidthOrZero2, 0, 2, null)) : null;
        int iWidthOrZero3 = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo04) + iWidthOrZero2;
        int iMax3 = Math.max(iMax2, TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo04));
        int size4 = list2.size();
        int i14 = 0;
        while (true) {
            if (i14 >= size4) {
                measurable4 = null;
                break;
            }
            measurable4 = list2.get(i14);
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable4), TextFieldImplKt.SuffixId)) {
                break;
            }
            i14++;
        }
        Measurable measurable11 = measurable4;
        if (measurable11 != null) {
            placeable = placeableMo4631measureBRTryo0;
            long jM5663offsetNN6EwU$default = ConstraintsKt.m5663offsetNN6EwU$default(j11, -iWidthOrZero3, 0, 2, null);
            i10 = iWidthOrZero3;
            placeable2 = placeableMo4631measureBRTryo03;
            j12 = j11;
            placeableMo4631measureBRTryo02 = measurable11.mo4631measureBRTryo0(jM5663offsetNN6EwU$default);
        } else {
            placeable = placeableMo4631measureBRTryo0;
            i10 = iWidthOrZero3;
            placeable2 = placeableMo4631measureBRTryo03;
            j12 = j11;
            placeableMo4631measureBRTryo02 = null;
        }
        int iWidthOrZero4 = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo02) + i10;
        int iMax4 = Math.max(iMax3, TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo02));
        int i15 = -iWidthOrZero4;
        long jM5662offsetNN6EwU = ConstraintsKt.m5662offsetNN6EwU(j12, i15, -iMo279roundToPx0680j_42);
        int size5 = list2.size();
        int i16 = 0;
        while (true) {
            if (i16 >= size5) {
                placeable3 = placeableMo4631measureBRTryo02;
                measurable5 = null;
                break;
            }
            measurable5 = list2.get(i16);
            int i17 = i16;
            placeable3 = placeableMo4631measureBRTryo02;
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable5), TextFieldImplKt.LabelId)) {
                break;
            }
            i16 = i17 + 1;
            placeableMo4631measureBRTryo02 = placeable3;
        }
        Measurable measurable12 = measurable5;
        Placeable placeableMo4631measureBRTryo05 = measurable12 != null ? measurable12.mo4631measureBRTryo0(jM5662offsetNN6EwU) : null;
        int size6 = list2.size();
        int i18 = 0;
        while (true) {
            if (i18 >= size6) {
                placeable4 = placeableMo4631measureBRTryo05;
                measurable6 = null;
                break;
            }
            measurable6 = list2.get(i18);
            placeable4 = placeableMo4631measureBRTryo05;
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable6), TextFieldImplKt.SupportingId)) {
                break;
            }
            i18++;
            placeableMo4631measureBRTryo05 = placeable4;
        }
        Measurable measurable13 = measurable6;
        int iMinIntrinsicHeight = measurable13 != null ? measurable13.minIntrinsicHeight(Constraints.m5648getMinWidthimpl(j10)) : 0;
        int iHeightOrZero = TextFieldImplKt.heightOrZero(placeable4) + iMo279roundToPx0680j_4;
        long j13 = j12;
        long jM5662offsetNN6EwU2 = ConstraintsKt.m5662offsetNN6EwU(Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 11, null), i15, ((-iHeightOrZero) - iMo279roundToPx0680j_42) - iMinIntrinsicHeight);
        int size7 = list2.size();
        int i19 = 0;
        while (i19 < size7) {
            Measurable measurable14 = list2.get(i19);
            int i20 = size7;
            int i21 = iHeightOrZero;
            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable14), TextFieldImplKt.TextFieldId)) {
                Placeable placeableMo4631measureBRTryo06 = measurable14.mo4631measureBRTryo0(jM5662offsetNN6EwU2);
                long jM5637copyZbe2FdA$default2 = Constraints.m5637copyZbe2FdA$default(jM5662offsetNN6EwU2, 0, 0, 0, 0, 14, null);
                int size8 = list2.size();
                int i22 = 0;
                while (true) {
                    if (i22 >= size8) {
                        placeable5 = placeableMo4631measureBRTryo06;
                        measurable7 = null;
                        break;
                    }
                    measurable7 = list2.get(i22);
                    placeable5 = placeableMo4631measureBRTryo06;
                    int i23 = size8;
                    if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable7), TextFieldImplKt.PlaceholderId)) {
                        break;
                    }
                    i22++;
                    size8 = i23;
                    placeableMo4631measureBRTryo06 = placeable5;
                }
                Measurable measurable15 = measurable7;
                Placeable placeableMo4631measureBRTryo07 = measurable15 != null ? measurable15.mo4631measureBRTryo0(jM5637copyZbe2FdA$default2) : null;
                int iMax5 = Math.max(iMax4, Math.max(TextFieldImplKt.heightOrZero(placeable5), TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo07)) + i21 + iMo279roundToPx0680j_42);
                int iM2150calculateWidthyeHjK3Y = TextFieldKt.m2150calculateWidthyeHjK3Y(TextFieldImplKt.widthOrZero(placeable2), TextFieldImplKt.widthOrZero(placeable), TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo04), TextFieldImplKt.widthOrZero(placeable3), placeable5.getWidth(), TextFieldImplKt.widthOrZero(placeable4), TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo07), j10);
                int i24 = iM2150calculateWidthyeHjK3Y;
                Placeable placeableMo4631measureBRTryo08 = measurable13 != null ? measurable13.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(ConstraintsKt.m5663offsetNN6EwU$default(j13, 0, -iMax5, 1, null), 0, iM2150calculateWidthyeHjK3Y, 0, 0, 9, null)) : null;
                int iHeightOrZero2 = TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo08);
                int iM2149calculateHeightmKXJcVc = TextFieldKt.m2149calculateHeightmKXJcVc(placeable5.getHeight(), TextFieldImplKt.heightOrZero(placeable4), TextFieldImplKt.heightOrZero(placeable2), TextFieldImplKt.heightOrZero(placeable), TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo04), TextFieldImplKt.heightOrZero(placeable3), TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo07), TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo08), textFieldMeasurePolicy.animationProgress, j10, measureScope2.getDensity(), textFieldMeasurePolicy.paddingValues);
                int i25 = iM2149calculateHeightmKXJcVc - iHeightOrZero2;
                int size9 = list2.size();
                int i26 = 0;
                while (i26 < size9) {
                    Measurable measurable16 = list2.get(i26);
                    int i27 = iM2149calculateHeightmKXJcVc;
                    if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable16), TextFieldImplKt.ContainerId)) {
                        int i28 = i24;
                        return MeasureScope.CC.q(measureScope, i28, i27, null, new TextFieldMeasurePolicy$measure$1(placeable4, i28, i27, placeable5, placeableMo4631measureBRTryo07, placeable2, placeable, placeableMo4631measureBRTryo04, placeable3, measurable16.mo4631measureBRTryo0(ConstraintsKt.Constraints(i24 != Integer.MAX_VALUE ? i24 : 0, i24, i25 != Integer.MAX_VALUE ? i25 : 0, i25)), placeableMo4631measureBRTryo08, textFieldMeasurePolicy, iMo279roundToPx0680j_4, measureScope2), 4, null);
                    }
                    i26++;
                    i24 = i24;
                    iM2149calculateHeightmKXJcVc = i27;
                    textFieldMeasurePolicy = this;
                    measureScope2 = measureScope;
                    list2 = list;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i19++;
            textFieldMeasurePolicy = this;
            measureScope2 = measureScope;
            size7 = i20;
            iHeightOrZero = i21;
            list2 = list;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return intrinsicHeight(intrinsicMeasureScope, list, i10, C05681.INSTANCE);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return intrinsicWidth(list, i10, C05691.INSTANCE);
    }
}

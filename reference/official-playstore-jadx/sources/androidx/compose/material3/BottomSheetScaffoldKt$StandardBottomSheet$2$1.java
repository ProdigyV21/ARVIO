package androidx.compose.material3;

import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/unit/IntSize;", "layoutSize", "Lx6/t0;", "invoke-ozmzZPI", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BottomSheetScaffoldKt$StandardBottomSheet$2$1 extends r implements l<IntSize, t0> {
    final /* synthetic */ l<IntSize, DraggableAnchors<SheetValue>> $calculateAnchors;
    final /* synthetic */ SheetState $state;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SheetValue.values().length];
            try {
                iArr[SheetValue.Hidden.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SheetValue.Expanded.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BottomSheetScaffoldKt$StandardBottomSheet$2$1(l<? super IntSize, ? extends DraggableAnchors<SheetValue>> lVar, SheetState sheetState) {
        super(1);
        this.$calculateAnchors = lVar;
        this.$state = sheetState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m1257invokeozmzZPI(((IntSize) obj).getPackedValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
    public final void m1257invokeozmzZPI(long j10) {
        SheetValue sheetValue;
        DraggableAnchors<SheetValue> draggableAnchors = (DraggableAnchors) this.$calculateAnchors.invoke(IntSize.m5836boximpl(j10));
        int i10 = WhenMappings.$EnumSwitchMapping$0[this.$state.getAnchoredDraggableState$material3_release().getTargetValue().ordinal()];
        if (i10 == 1 || i10 == 2) {
            sheetValue = SheetValue.PartiallyExpanded;
        } else {
            if (i10 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            sheetValue = SheetValue.Expanded;
            if (!draggableAnchors.hasAnchorFor(sheetValue)) {
                sheetValue = SheetValue.PartiallyExpanded;
            }
        }
        this.$state.getAnchoredDraggableState$material3_release().updateAnchors(draggableAnchors, sheetValue);
    }
}

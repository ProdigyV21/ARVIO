package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¨\u0006\u0007"}, d2 = {"windowInsetsBottomHeight", "Landroidx/compose/ui/Modifier;", "insets", "Landroidx/compose/foundation/layout/WindowInsets;", "windowInsetsEndWidth", "windowInsetsStartWidth", "windowInsetsTopHeight", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WindowInsetsSizeKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsBottomHeight$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/layout/WindowInsets;", "it", "Landroidx/compose/ui/unit/Density;", "invoke", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/unit/Density;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<WindowInsets, Density, Integer> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(2);
        }

        @Override // r7.p
        public final Integer invoke(WindowInsets windowInsets, Density density) {
            return Integer.valueOf(windowInsets.getBottom(density));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsEndWidth$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/layout/WindowInsets;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "invoke", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03402 extends r implements q<WindowInsets, LayoutDirection, Density, Integer> {
        public static final C03402 INSTANCE = new C03402();

        public C03402() {
            super(3);
        }

        @Override // r7.q
        public final Integer invoke(WindowInsets windowInsets, LayoutDirection layoutDirection, Density density) {
            return Integer.valueOf(layoutDirection == LayoutDirection.Rtl ? windowInsets.getLeft(density, layoutDirection) : windowInsets.getRight(density, layoutDirection));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsStartWidth$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/layout/WindowInsets;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "invoke", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03412 extends r implements q<WindowInsets, LayoutDirection, Density, Integer> {
        public static final C03412 INSTANCE = new C03412();

        public C03412() {
            super(3);
        }

        @Override // r7.q
        public final Integer invoke(WindowInsets windowInsets, LayoutDirection layoutDirection, Density density) {
            return Integer.valueOf(layoutDirection == LayoutDirection.Ltr ? windowInsets.getLeft(density, layoutDirection) : windowInsets.getRight(density, layoutDirection));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsTopHeight$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/layout/WindowInsets;", "it", "Landroidx/compose/ui/unit/Density;", "invoke", "(Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/unit/Density;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C03422 extends r implements p<WindowInsets, Density, Integer> {
        public static final C03422 INSTANCE = new C03422();

        public C03422() {
            super(2);
        }

        @Override // r7.p
        public final Integer invoke(WindowInsets windowInsets, Density density) {
            return Integer.valueOf(windowInsets.getTop(density));
        }
    }

    public static final Modifier windowInsetsBottomHeight(Modifier modifier, WindowInsets windowInsets) {
        return modifier.then(new DerivedHeightModifier(windowInsets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new WindowInsetsSizeKt$windowInsetsBottomHeight$$inlined$debugInspectorInfo$1(windowInsets) : InspectableValueKt.getNoInspectorInfo(), AnonymousClass2.INSTANCE));
    }

    public static final Modifier windowInsetsEndWidth(Modifier modifier, WindowInsets windowInsets) {
        return modifier.then(new DerivedWidthModifier(windowInsets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new WindowInsetsSizeKt$windowInsetsEndWidth$$inlined$debugInspectorInfo$1(windowInsets) : InspectableValueKt.getNoInspectorInfo(), C03402.INSTANCE));
    }

    public static final Modifier windowInsetsStartWidth(Modifier modifier, WindowInsets windowInsets) {
        return modifier.then(new DerivedWidthModifier(windowInsets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new WindowInsetsSizeKt$windowInsetsStartWidth$$inlined$debugInspectorInfo$1(windowInsets) : InspectableValueKt.getNoInspectorInfo(), C03412.INSTANCE));
    }

    public static final Modifier windowInsetsTopHeight(Modifier modifier, WindowInsets windowInsets) {
        return modifier.then(new DerivedHeightModifier(windowInsets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new WindowInsetsSizeKt$windowInsetsTopHeight$$inlined$debugInspectorInfo$1(windowInsets) : InspectableValueKt.getNoInspectorInfo(), C03422.INSTANCE));
    }
}

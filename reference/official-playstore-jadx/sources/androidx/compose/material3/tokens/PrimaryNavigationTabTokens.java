package androidx.compose.material3.tokens;

import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0019\u0010\u0011\u001a\u00020\u0012ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0006R\u0011\u0010\u001c\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0006R\u0011\u0010\u001e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0006R\u0011\u0010 \u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0006R\u0019\u0010\"\u001a\u00020\u0012ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b#\u0010\u0014R\u0019\u0010$\u001a\u00020\u0012ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b%\u0010\u0014R\u0011\u0010&\u001a\u00020'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0019\u0010*\u001a\u00020\u0012ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b+\u0010\u0014R\u0019\u0010,\u001a\u00020\u0012ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b-\u0010\u0014R\u0011\u0010.\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0006R\u0011\u00100\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0006R\u0011\u00102\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0006R\u0011\u00104\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0006R\u0011\u00106\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0006R\u0011\u00108\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0006R\u0011\u0010:\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0006R\u0011\u0010<\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0006R\u0011\u0010>\u001a\u00020?¢\u0006\b\n\u0000\u001a\u0004\b@\u0010A\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006B"}, d2 = {"Landroidx/compose/material3/tokens/PrimaryNavigationTabTokens;", "", "()V", "ActiveFocusIconColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getActiveFocusIconColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ActiveFocusLabelTextColor", "getActiveFocusLabelTextColor", "ActiveHoverIconColor", "getActiveHoverIconColor", "ActiveHoverLabelTextColor", "getActiveHoverLabelTextColor", "ActiveIconColor", "getActiveIconColor", "ActiveIndicatorColor", "getActiveIndicatorColor", "ActiveIndicatorHeight", "Landroidx/compose/ui/unit/Dp;", "getActiveIndicatorHeight-D9Ej5fM", "()F", "F", "ActiveIndicatorShape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "getActiveIndicatorShape", "()Landroidx/compose/foundation/shape/RoundedCornerShape;", "ActiveLabelTextColor", "getActiveLabelTextColor", "ActivePressedIconColor", "getActivePressedIconColor", "ActivePressedLabelTextColor", "getActivePressedLabelTextColor", "ContainerColor", "getContainerColor", "ContainerElevation", "getContainerElevation-D9Ej5fM", "ContainerHeight", "getContainerHeight-D9Ej5fM", "ContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "IconAndLabelTextContainerHeight", "getIconAndLabelTextContainerHeight-D9Ej5fM", "IconSize", "getIconSize-D9Ej5fM", "InactiveFocusIconColor", "getInactiveFocusIconColor", "InactiveFocusLabelTextColor", "getInactiveFocusLabelTextColor", "InactiveHoverIconColor", "getInactiveHoverIconColor", "InactiveHoverLabelTextColor", "getInactiveHoverLabelTextColor", "InactiveIconColor", "getInactiveIconColor", "InactiveLabelTextColor", "getInactiveLabelTextColor", "InactivePressedIconColor", "getInactivePressedIconColor", "InactivePressedLabelTextColor", "getInactivePressedLabelTextColor", "LabelTextFont", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "getLabelTextFont", "()Landroidx/compose/material3/tokens/TypographyKeyTokens;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PrimaryNavigationTabTokens {
    public static final int $stable = 0;
    private static final ColorSchemeKeyTokens ActiveFocusIconColor;
    private static final ColorSchemeKeyTokens ActiveFocusLabelTextColor;
    private static final ColorSchemeKeyTokens ActiveHoverIconColor;
    private static final ColorSchemeKeyTokens ActiveHoverLabelTextColor;
    private static final ColorSchemeKeyTokens ActiveIconColor;
    private static final ColorSchemeKeyTokens ActiveIndicatorColor;
    private static final float ActiveIndicatorHeight;
    private static final RoundedCornerShape ActiveIndicatorShape;
    private static final ColorSchemeKeyTokens ActiveLabelTextColor;
    private static final ColorSchemeKeyTokens ActivePressedIconColor;
    private static final ColorSchemeKeyTokens ActivePressedLabelTextColor;
    private static final ColorSchemeKeyTokens ContainerColor;
    private static final float ContainerElevation;
    private static final float ContainerHeight;
    private static final ShapeKeyTokens ContainerShape;
    public static final PrimaryNavigationTabTokens INSTANCE = new PrimaryNavigationTabTokens();
    private static final float IconAndLabelTextContainerHeight;
    private static final float IconSize;
    private static final ColorSchemeKeyTokens InactiveFocusIconColor;
    private static final ColorSchemeKeyTokens InactiveFocusLabelTextColor;
    private static final ColorSchemeKeyTokens InactiveHoverIconColor;
    private static final ColorSchemeKeyTokens InactiveHoverLabelTextColor;
    private static final ColorSchemeKeyTokens InactiveIconColor;
    private static final ColorSchemeKeyTokens InactiveLabelTextColor;
    private static final ColorSchemeKeyTokens InactivePressedIconColor;
    private static final ColorSchemeKeyTokens InactivePressedLabelTextColor;
    private static final TypographyKeyTokens LabelTextFont;

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.Primary;
        ActiveIndicatorColor = colorSchemeKeyTokens;
        float f10 = (float) 3.0d;
        ActiveIndicatorHeight = Dp.m5678constructorimpl(f10);
        ActiveIndicatorShape = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10));
        ContainerColor = ColorSchemeKeyTokens.Surface;
        ContainerElevation = ElevationTokens.INSTANCE.m2506getLevel0D9Ej5fM();
        ContainerHeight = Dp.m5678constructorimpl((float) 48.0d);
        ContainerShape = ShapeKeyTokens.CornerNone;
        ActiveFocusIconColor = colorSchemeKeyTokens;
        ActiveHoverIconColor = colorSchemeKeyTokens;
        ActiveIconColor = colorSchemeKeyTokens;
        ActivePressedIconColor = colorSchemeKeyTokens;
        IconAndLabelTextContainerHeight = Dp.m5678constructorimpl((float) 64.0d);
        IconSize = Dp.m5678constructorimpl((float) 24.0d);
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurface;
        InactiveFocusIconColor = colorSchemeKeyTokens2;
        InactiveHoverIconColor = colorSchemeKeyTokens2;
        ColorSchemeKeyTokens colorSchemeKeyTokens3 = ColorSchemeKeyTokens.OnSurfaceVariant;
        InactiveIconColor = colorSchemeKeyTokens3;
        InactivePressedIconColor = colorSchemeKeyTokens2;
        ActiveFocusLabelTextColor = colorSchemeKeyTokens;
        ActiveHoverLabelTextColor = colorSchemeKeyTokens;
        ActiveLabelTextColor = colorSchemeKeyTokens;
        ActivePressedLabelTextColor = colorSchemeKeyTokens;
        InactiveFocusLabelTextColor = colorSchemeKeyTokens2;
        InactiveHoverLabelTextColor = colorSchemeKeyTokens2;
        InactiveLabelTextColor = colorSchemeKeyTokens3;
        InactivePressedLabelTextColor = colorSchemeKeyTokens2;
        LabelTextFont = TypographyKeyTokens.TitleSmall;
    }

    private PrimaryNavigationTabTokens() {
    }

    public final ColorSchemeKeyTokens getActiveFocusIconColor() {
        return ActiveFocusIconColor;
    }

    public final ColorSchemeKeyTokens getActiveFocusLabelTextColor() {
        return ActiveFocusLabelTextColor;
    }

    public final ColorSchemeKeyTokens getActiveHoverIconColor() {
        return ActiveHoverIconColor;
    }

    public final ColorSchemeKeyTokens getActiveHoverLabelTextColor() {
        return ActiveHoverLabelTextColor;
    }

    public final ColorSchemeKeyTokens getActiveIconColor() {
        return ActiveIconColor;
    }

    public final ColorSchemeKeyTokens getActiveIndicatorColor() {
        return ActiveIndicatorColor;
    }

    /* JADX INFO: renamed from: getActiveIndicatorHeight-D9Ej5fM, reason: not valid java name */
    public final float m2797getActiveIndicatorHeightD9Ej5fM() {
        return ActiveIndicatorHeight;
    }

    public final RoundedCornerShape getActiveIndicatorShape() {
        return ActiveIndicatorShape;
    }

    public final ColorSchemeKeyTokens getActiveLabelTextColor() {
        return ActiveLabelTextColor;
    }

    public final ColorSchemeKeyTokens getActivePressedIconColor() {
        return ActivePressedIconColor;
    }

    public final ColorSchemeKeyTokens getActivePressedLabelTextColor() {
        return ActivePressedLabelTextColor;
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m2798getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    /* JADX INFO: renamed from: getContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m2799getContainerHeightD9Ej5fM() {
        return ContainerHeight;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    /* JADX INFO: renamed from: getIconAndLabelTextContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m2800getIconAndLabelTextContainerHeightD9Ej5fM() {
        return IconAndLabelTextContainerHeight;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m2801getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final ColorSchemeKeyTokens getInactiveFocusIconColor() {
        return InactiveFocusIconColor;
    }

    public final ColorSchemeKeyTokens getInactiveFocusLabelTextColor() {
        return InactiveFocusLabelTextColor;
    }

    public final ColorSchemeKeyTokens getInactiveHoverIconColor() {
        return InactiveHoverIconColor;
    }

    public final ColorSchemeKeyTokens getInactiveHoverLabelTextColor() {
        return InactiveHoverLabelTextColor;
    }

    public final ColorSchemeKeyTokens getInactiveIconColor() {
        return InactiveIconColor;
    }

    public final ColorSchemeKeyTokens getInactiveLabelTextColor() {
        return InactiveLabelTextColor;
    }

    public final ColorSchemeKeyTokens getInactivePressedIconColor() {
        return InactivePressedIconColor;
    }

    public final ColorSchemeKeyTokens getInactivePressedLabelTextColor() {
        return InactivePressedLabelTextColor;
    }

    public final TypographyKeyTokens getLabelTextFont() {
        return LabelTextFont;
    }
}

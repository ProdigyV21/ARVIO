package androidx.compose.material3.tokens;

import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b1\n\u0002\u0018\u0002\n\u0002\b(\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0019\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u000e\u0010\u0016\u001a\u00020\u0017X\u0086T¢\u0006\u0002\n\u0000R\u0019\u0010\u0018\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0019\u0010\u0006R\u0011\u0010\u001a\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\u001c\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u001e\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0011\u0010 \u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0013R\u0019\u0010\"\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b#\u0010\u0006R\u0011\u0010$\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0013R\u0011\u0010&\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0013R\u0019\u0010(\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b)\u0010\u0006R\u0011\u0010*\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0013R\u0011\u0010,\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0013R\u0011\u0010.\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0013R\u0019\u00100\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b1\u0010\u0006R\u0011\u00102\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0013R\u0011\u00104\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0013R\u0019\u00106\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b7\u0010\u0006R\u0011\u00108\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0013R\u0011\u0010:\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0013R\u0019\u0010<\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b=\u0010\u0006R\u0011\u0010>\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0013R\u0019\u0010@\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\bA\u0010\u0006R\u0011\u0010B\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\u0013R\u0011\u0010D\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\u0013R\u0019\u0010F\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\bG\u0010\u0006R\u0011\u0010H\u001a\u00020I¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR\u0019\u0010L\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\bM\u0010\u0006R\u000e\u0010N\u001a\u00020\u0017X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010O\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0013R\u0019\u0010Q\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\bR\u0010\u0006R\u0011\u0010S\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bT\u0010\u0013R\u0019\u0010U\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\bV\u0010\u0006R\u0011\u0010W\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\u0013R\u0019\u0010Y\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\bZ\u0010\u0006R\u0011\u0010[\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\u0013R\u0011\u0010]\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0013R\u0019\u0010_\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b`\u0010\u0006R\u0011\u0010a\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0013R\u0019\u0010c\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\bd\u0010\u0006R\u0011\u0010e\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0013R\u0019\u0010g\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\bh\u0010\u0006R\u0011\u0010i\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0013R\u0019\u0010k\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\bl\u0010\u0006R\u0011\u0010m\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0013R\u0019\u0010o\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\bp\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006q"}, d2 = {"Landroidx/compose/material3/tokens/CheckboxTokens;", "", "()V", "ContainerHeight", "Landroidx/compose/ui/unit/Dp;", "getContainerHeight-D9Ej5fM", "()F", "F", "ContainerShape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "getContainerShape", "()Landroidx/compose/foundation/shape/RoundedCornerShape;", "ContainerWidth", "getContainerWidth-D9Ej5fM", "IconSize", "getIconSize-D9Ej5fM", "SelectedContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getSelectedContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "SelectedDisabledContainerColor", "getSelectedDisabledContainerColor", "SelectedDisabledContainerOpacity", "", "SelectedDisabledContainerOutlineWidth", "getSelectedDisabledContainerOutlineWidth-D9Ej5fM", "SelectedDisabledIconColor", "getSelectedDisabledIconColor", "SelectedErrorContainerColor", "getSelectedErrorContainerColor", "SelectedErrorFocusContainerColor", "getSelectedErrorFocusContainerColor", "SelectedErrorFocusIconColor", "getSelectedErrorFocusIconColor", "SelectedErrorFocusOutlineWidth", "getSelectedErrorFocusOutlineWidth-D9Ej5fM", "SelectedErrorHoverContainerColor", "getSelectedErrorHoverContainerColor", "SelectedErrorHoverIconColor", "getSelectedErrorHoverIconColor", "SelectedErrorHoverOutlineWidth", "getSelectedErrorHoverOutlineWidth-D9Ej5fM", "SelectedErrorIconColor", "getSelectedErrorIconColor", "SelectedErrorPressedContainerColor", "getSelectedErrorPressedContainerColor", "SelectedErrorPressedIconColor", "getSelectedErrorPressedIconColor", "SelectedErrorPressedOutlineWidth", "getSelectedErrorPressedOutlineWidth-D9Ej5fM", "SelectedFocusContainerColor", "getSelectedFocusContainerColor", "SelectedFocusIconColor", "getSelectedFocusIconColor", "SelectedFocusOutlineWidth", "getSelectedFocusOutlineWidth-D9Ej5fM", "SelectedHoverContainerColor", "getSelectedHoverContainerColor", "SelectedHoverIconColor", "getSelectedHoverIconColor", "SelectedHoverOutlineWidth", "getSelectedHoverOutlineWidth-D9Ej5fM", "SelectedIconColor", "getSelectedIconColor", "SelectedOutlineWidth", "getSelectedOutlineWidth-D9Ej5fM", "SelectedPressedContainerColor", "getSelectedPressedContainerColor", "SelectedPressedIconColor", "getSelectedPressedIconColor", "SelectedPressedOutlineWidth", "getSelectedPressedOutlineWidth-D9Ej5fM", "StateLayerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getStateLayerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "StateLayerSize", "getStateLayerSize-D9Ej5fM", "UnselectedDisabledContainerOpacity", "UnselectedDisabledOutlineColor", "getUnselectedDisabledOutlineColor", "UnselectedDisabledOutlineWidth", "getUnselectedDisabledOutlineWidth-D9Ej5fM", "UnselectedErrorFocusOutlineColor", "getUnselectedErrorFocusOutlineColor", "UnselectedErrorFocusOutlineWidth", "getUnselectedErrorFocusOutlineWidth-D9Ej5fM", "UnselectedErrorHoverOutlineColor", "getUnselectedErrorHoverOutlineColor", "UnselectedErrorHoverOutlineWidth", "getUnselectedErrorHoverOutlineWidth-D9Ej5fM", "UnselectedErrorOutlineColor", "getUnselectedErrorOutlineColor", "UnselectedErrorPressedOutlineColor", "getUnselectedErrorPressedOutlineColor", "UnselectedErrorPressedOutlineWidth", "getUnselectedErrorPressedOutlineWidth-D9Ej5fM", "UnselectedFocusOutlineColor", "getUnselectedFocusOutlineColor", "UnselectedFocusOutlineWidth", "getUnselectedFocusOutlineWidth-D9Ej5fM", "UnselectedHoverOutlineColor", "getUnselectedHoverOutlineColor", "UnselectedHoverOutlineWidth", "getUnselectedHoverOutlineWidth-D9Ej5fM", "UnselectedOutlineColor", "getUnselectedOutlineColor", "UnselectedOutlineWidth", "getUnselectedOutlineWidth-D9Ej5fM", "UnselectedPressedOutlineColor", "getUnselectedPressedOutlineColor", "UnselectedPressedOutlineWidth", "getUnselectedPressedOutlineWidth-D9Ej5fM", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CheckboxTokens {
    public static final int $stable = 0;
    private static final float ContainerHeight;
    private static final RoundedCornerShape ContainerShape;
    private static final float ContainerWidth;
    public static final CheckboxTokens INSTANCE = new CheckboxTokens();
    private static final float IconSize;
    private static final ColorSchemeKeyTokens SelectedContainerColor;
    private static final ColorSchemeKeyTokens SelectedDisabledContainerColor;
    public static final float SelectedDisabledContainerOpacity = 0.38f;
    private static final float SelectedDisabledContainerOutlineWidth;
    private static final ColorSchemeKeyTokens SelectedDisabledIconColor;
    private static final ColorSchemeKeyTokens SelectedErrorContainerColor;
    private static final ColorSchemeKeyTokens SelectedErrorFocusContainerColor;
    private static final ColorSchemeKeyTokens SelectedErrorFocusIconColor;
    private static final float SelectedErrorFocusOutlineWidth;
    private static final ColorSchemeKeyTokens SelectedErrorHoverContainerColor;
    private static final ColorSchemeKeyTokens SelectedErrorHoverIconColor;
    private static final float SelectedErrorHoverOutlineWidth;
    private static final ColorSchemeKeyTokens SelectedErrorIconColor;
    private static final ColorSchemeKeyTokens SelectedErrorPressedContainerColor;
    private static final ColorSchemeKeyTokens SelectedErrorPressedIconColor;
    private static final float SelectedErrorPressedOutlineWidth;
    private static final ColorSchemeKeyTokens SelectedFocusContainerColor;
    private static final ColorSchemeKeyTokens SelectedFocusIconColor;
    private static final float SelectedFocusOutlineWidth;
    private static final ColorSchemeKeyTokens SelectedHoverContainerColor;
    private static final ColorSchemeKeyTokens SelectedHoverIconColor;
    private static final float SelectedHoverOutlineWidth;
    private static final ColorSchemeKeyTokens SelectedIconColor;
    private static final float SelectedOutlineWidth;
    private static final ColorSchemeKeyTokens SelectedPressedContainerColor;
    private static final ColorSchemeKeyTokens SelectedPressedIconColor;
    private static final float SelectedPressedOutlineWidth;
    private static final ShapeKeyTokens StateLayerShape;
    private static final float StateLayerSize;
    public static final float UnselectedDisabledContainerOpacity = 0.38f;
    private static final ColorSchemeKeyTokens UnselectedDisabledOutlineColor;
    private static final float UnselectedDisabledOutlineWidth;
    private static final ColorSchemeKeyTokens UnselectedErrorFocusOutlineColor;
    private static final float UnselectedErrorFocusOutlineWidth;
    private static final ColorSchemeKeyTokens UnselectedErrorHoverOutlineColor;
    private static final float UnselectedErrorHoverOutlineWidth;
    private static final ColorSchemeKeyTokens UnselectedErrorOutlineColor;
    private static final ColorSchemeKeyTokens UnselectedErrorPressedOutlineColor;
    private static final float UnselectedErrorPressedOutlineWidth;
    private static final ColorSchemeKeyTokens UnselectedFocusOutlineColor;
    private static final float UnselectedFocusOutlineWidth;
    private static final ColorSchemeKeyTokens UnselectedHoverOutlineColor;
    private static final float UnselectedHoverOutlineWidth;
    private static final ColorSchemeKeyTokens UnselectedOutlineColor;
    private static final float UnselectedOutlineWidth;
    private static final ColorSchemeKeyTokens UnselectedPressedOutlineColor;
    private static final float UnselectedPressedOutlineWidth;

    static {
        float f10 = (float) 18.0d;
        ContainerHeight = Dp.m5678constructorimpl(f10);
        float f11 = (float) 2.0d;
        ContainerShape = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f11));
        ContainerWidth = Dp.m5678constructorimpl(f10);
        IconSize = Dp.m5678constructorimpl(f10);
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.Primary;
        SelectedContainerColor = colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurface;
        SelectedDisabledContainerColor = colorSchemeKeyTokens2;
        float f12 = (float) 0.0d;
        SelectedDisabledContainerOutlineWidth = Dp.m5678constructorimpl(f12);
        SelectedDisabledIconColor = ColorSchemeKeyTokens.Surface;
        ColorSchemeKeyTokens colorSchemeKeyTokens3 = ColorSchemeKeyTokens.Error;
        SelectedErrorContainerColor = colorSchemeKeyTokens3;
        SelectedErrorFocusContainerColor = colorSchemeKeyTokens3;
        ColorSchemeKeyTokens colorSchemeKeyTokens4 = ColorSchemeKeyTokens.OnError;
        SelectedErrorFocusIconColor = colorSchemeKeyTokens4;
        SelectedErrorFocusOutlineWidth = Dp.m5678constructorimpl(f12);
        SelectedErrorHoverContainerColor = colorSchemeKeyTokens3;
        SelectedErrorHoverIconColor = colorSchemeKeyTokens4;
        SelectedErrorHoverOutlineWidth = Dp.m5678constructorimpl(f12);
        SelectedErrorIconColor = colorSchemeKeyTokens4;
        SelectedErrorPressedContainerColor = colorSchemeKeyTokens3;
        SelectedErrorPressedIconColor = colorSchemeKeyTokens4;
        SelectedErrorPressedOutlineWidth = Dp.m5678constructorimpl(f12);
        SelectedFocusContainerColor = colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens5 = ColorSchemeKeyTokens.OnPrimary;
        SelectedFocusIconColor = colorSchemeKeyTokens5;
        SelectedFocusOutlineWidth = Dp.m5678constructorimpl(f12);
        SelectedHoverContainerColor = colorSchemeKeyTokens;
        SelectedHoverIconColor = colorSchemeKeyTokens5;
        SelectedHoverOutlineWidth = Dp.m5678constructorimpl(f12);
        SelectedIconColor = colorSchemeKeyTokens5;
        SelectedOutlineWidth = Dp.m5678constructorimpl(f12);
        SelectedPressedContainerColor = colorSchemeKeyTokens;
        SelectedPressedIconColor = colorSchemeKeyTokens5;
        SelectedPressedOutlineWidth = Dp.m5678constructorimpl(f12);
        StateLayerShape = ShapeKeyTokens.CornerFull;
        StateLayerSize = Dp.m5678constructorimpl((float) 40.0d);
        UnselectedDisabledOutlineColor = colorSchemeKeyTokens2;
        UnselectedDisabledOutlineWidth = Dp.m5678constructorimpl(f11);
        UnselectedErrorFocusOutlineColor = colorSchemeKeyTokens3;
        UnselectedErrorFocusOutlineWidth = Dp.m5678constructorimpl(f11);
        UnselectedErrorHoverOutlineColor = colorSchemeKeyTokens3;
        UnselectedErrorHoverOutlineWidth = Dp.m5678constructorimpl(f11);
        UnselectedErrorOutlineColor = colorSchemeKeyTokens3;
        UnselectedErrorPressedOutlineColor = colorSchemeKeyTokens3;
        UnselectedErrorPressedOutlineWidth = Dp.m5678constructorimpl(f11);
        UnselectedFocusOutlineColor = colorSchemeKeyTokens2;
        UnselectedFocusOutlineWidth = Dp.m5678constructorimpl(f11);
        UnselectedHoverOutlineColor = colorSchemeKeyTokens2;
        UnselectedHoverOutlineWidth = Dp.m5678constructorimpl(f11);
        UnselectedOutlineColor = ColorSchemeKeyTokens.OnSurfaceVariant;
        UnselectedOutlineWidth = Dp.m5678constructorimpl(f11);
        UnselectedPressedOutlineColor = colorSchemeKeyTokens2;
        UnselectedPressedOutlineWidth = Dp.m5678constructorimpl(f11);
    }

    private CheckboxTokens() {
    }

    /* JADX INFO: renamed from: getContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m2349getContainerHeightD9Ej5fM() {
        return ContainerHeight;
    }

    public final RoundedCornerShape getContainerShape() {
        return ContainerShape;
    }

    /* JADX INFO: renamed from: getContainerWidth-D9Ej5fM, reason: not valid java name */
    public final float m2350getContainerWidthD9Ej5fM() {
        return ContainerWidth;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m2351getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final ColorSchemeKeyTokens getSelectedContainerColor() {
        return SelectedContainerColor;
    }

    public final ColorSchemeKeyTokens getSelectedDisabledContainerColor() {
        return SelectedDisabledContainerColor;
    }

    /* JADX INFO: renamed from: getSelectedDisabledContainerOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2352getSelectedDisabledContainerOutlineWidthD9Ej5fM() {
        return SelectedDisabledContainerOutlineWidth;
    }

    public final ColorSchemeKeyTokens getSelectedDisabledIconColor() {
        return SelectedDisabledIconColor;
    }

    public final ColorSchemeKeyTokens getSelectedErrorContainerColor() {
        return SelectedErrorContainerColor;
    }

    public final ColorSchemeKeyTokens getSelectedErrorFocusContainerColor() {
        return SelectedErrorFocusContainerColor;
    }

    public final ColorSchemeKeyTokens getSelectedErrorFocusIconColor() {
        return SelectedErrorFocusIconColor;
    }

    /* JADX INFO: renamed from: getSelectedErrorFocusOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2353getSelectedErrorFocusOutlineWidthD9Ej5fM() {
        return SelectedErrorFocusOutlineWidth;
    }

    public final ColorSchemeKeyTokens getSelectedErrorHoverContainerColor() {
        return SelectedErrorHoverContainerColor;
    }

    public final ColorSchemeKeyTokens getSelectedErrorHoverIconColor() {
        return SelectedErrorHoverIconColor;
    }

    /* JADX INFO: renamed from: getSelectedErrorHoverOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2354getSelectedErrorHoverOutlineWidthD9Ej5fM() {
        return SelectedErrorHoverOutlineWidth;
    }

    public final ColorSchemeKeyTokens getSelectedErrorIconColor() {
        return SelectedErrorIconColor;
    }

    public final ColorSchemeKeyTokens getSelectedErrorPressedContainerColor() {
        return SelectedErrorPressedContainerColor;
    }

    public final ColorSchemeKeyTokens getSelectedErrorPressedIconColor() {
        return SelectedErrorPressedIconColor;
    }

    /* JADX INFO: renamed from: getSelectedErrorPressedOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2355getSelectedErrorPressedOutlineWidthD9Ej5fM() {
        return SelectedErrorPressedOutlineWidth;
    }

    public final ColorSchemeKeyTokens getSelectedFocusContainerColor() {
        return SelectedFocusContainerColor;
    }

    public final ColorSchemeKeyTokens getSelectedFocusIconColor() {
        return SelectedFocusIconColor;
    }

    /* JADX INFO: renamed from: getSelectedFocusOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2356getSelectedFocusOutlineWidthD9Ej5fM() {
        return SelectedFocusOutlineWidth;
    }

    public final ColorSchemeKeyTokens getSelectedHoverContainerColor() {
        return SelectedHoverContainerColor;
    }

    public final ColorSchemeKeyTokens getSelectedHoverIconColor() {
        return SelectedHoverIconColor;
    }

    /* JADX INFO: renamed from: getSelectedHoverOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2357getSelectedHoverOutlineWidthD9Ej5fM() {
        return SelectedHoverOutlineWidth;
    }

    public final ColorSchemeKeyTokens getSelectedIconColor() {
        return SelectedIconColor;
    }

    /* JADX INFO: renamed from: getSelectedOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2358getSelectedOutlineWidthD9Ej5fM() {
        return SelectedOutlineWidth;
    }

    public final ColorSchemeKeyTokens getSelectedPressedContainerColor() {
        return SelectedPressedContainerColor;
    }

    public final ColorSchemeKeyTokens getSelectedPressedIconColor() {
        return SelectedPressedIconColor;
    }

    /* JADX INFO: renamed from: getSelectedPressedOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2359getSelectedPressedOutlineWidthD9Ej5fM() {
        return SelectedPressedOutlineWidth;
    }

    public final ShapeKeyTokens getStateLayerShape() {
        return StateLayerShape;
    }

    /* JADX INFO: renamed from: getStateLayerSize-D9Ej5fM, reason: not valid java name */
    public final float m2360getStateLayerSizeD9Ej5fM() {
        return StateLayerSize;
    }

    public final ColorSchemeKeyTokens getUnselectedDisabledOutlineColor() {
        return UnselectedDisabledOutlineColor;
    }

    /* JADX INFO: renamed from: getUnselectedDisabledOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2361getUnselectedDisabledOutlineWidthD9Ej5fM() {
        return UnselectedDisabledOutlineWidth;
    }

    public final ColorSchemeKeyTokens getUnselectedErrorFocusOutlineColor() {
        return UnselectedErrorFocusOutlineColor;
    }

    /* JADX INFO: renamed from: getUnselectedErrorFocusOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2362getUnselectedErrorFocusOutlineWidthD9Ej5fM() {
        return UnselectedErrorFocusOutlineWidth;
    }

    public final ColorSchemeKeyTokens getUnselectedErrorHoverOutlineColor() {
        return UnselectedErrorHoverOutlineColor;
    }

    /* JADX INFO: renamed from: getUnselectedErrorHoverOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2363getUnselectedErrorHoverOutlineWidthD9Ej5fM() {
        return UnselectedErrorHoverOutlineWidth;
    }

    public final ColorSchemeKeyTokens getUnselectedErrorOutlineColor() {
        return UnselectedErrorOutlineColor;
    }

    public final ColorSchemeKeyTokens getUnselectedErrorPressedOutlineColor() {
        return UnselectedErrorPressedOutlineColor;
    }

    /* JADX INFO: renamed from: getUnselectedErrorPressedOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2364getUnselectedErrorPressedOutlineWidthD9Ej5fM() {
        return UnselectedErrorPressedOutlineWidth;
    }

    public final ColorSchemeKeyTokens getUnselectedFocusOutlineColor() {
        return UnselectedFocusOutlineColor;
    }

    /* JADX INFO: renamed from: getUnselectedFocusOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2365getUnselectedFocusOutlineWidthD9Ej5fM() {
        return UnselectedFocusOutlineWidth;
    }

    public final ColorSchemeKeyTokens getUnselectedHoverOutlineColor() {
        return UnselectedHoverOutlineColor;
    }

    /* JADX INFO: renamed from: getUnselectedHoverOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2366getUnselectedHoverOutlineWidthD9Ej5fM() {
        return UnselectedHoverOutlineWidth;
    }

    public final ColorSchemeKeyTokens getUnselectedOutlineColor() {
        return UnselectedOutlineColor;
    }

    /* JADX INFO: renamed from: getUnselectedOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2367getUnselectedOutlineWidthD9Ej5fM() {
        return UnselectedOutlineWidth;
    }

    public final ColorSchemeKeyTokens getUnselectedPressedOutlineColor() {
        return UnselectedPressedOutlineColor;
    }

    /* JADX INFO: renamed from: getUnselectedPressedOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m2368getUnselectedPressedOutlineWidthD9Ej5fM() {
        return UnselectedPressedOutlineWidth;
    }
}

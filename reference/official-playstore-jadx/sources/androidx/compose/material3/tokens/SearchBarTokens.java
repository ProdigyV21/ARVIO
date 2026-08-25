package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\r\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0010\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0011\u0010\nR\u0019\u0010\u0012\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0013\u0010\nR\u0011\u0010\u0014\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0006R\u0011\u0010\u0016\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0018\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000fR\u0011\u0010\u001a\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000fR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u000fR\u0011\u0010\"\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u000fR\u0011\u0010$\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u000fR\u0011\u0010&\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0011\u0010(\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u000f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006*"}, d2 = {"Landroidx/compose/material3/tokens/SearchBarTokens;", "", "()V", "AvatarShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getAvatarShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "AvatarSize", "Landroidx/compose/ui/unit/Dp;", "getAvatarSize-D9Ej5fM", "()F", "F", "ContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ContainerElevation", "getContainerElevation-D9Ej5fM", "ContainerHeight", "getContainerHeight-D9Ej5fM", "ContainerShape", "getContainerShape", "ContainerSurfaceTintLayerColor", "getContainerSurfaceTintLayerColor", "HoverSupportingTextColor", "getHoverSupportingTextColor", "InputTextColor", "getInputTextColor", "InputTextFont", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "getInputTextFont", "()Landroidx/compose/material3/tokens/TypographyKeyTokens;", "LeadingIconColor", "getLeadingIconColor", "PressedSupportingTextColor", "getPressedSupportingTextColor", "SupportingTextColor", "getSupportingTextColor", "SupportingTextFont", "getSupportingTextFont", "TrailingIconColor", "getTrailingIconColor", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SearchBarTokens {
    public static final int $stable = 0;
    private static final ShapeKeyTokens AvatarShape;
    private static final float AvatarSize;
    private static final ColorSchemeKeyTokens ContainerColor;
    private static final float ContainerElevation;
    private static final float ContainerHeight;
    private static final ShapeKeyTokens ContainerShape;
    private static final ColorSchemeKeyTokens ContainerSurfaceTintLayerColor;
    private static final ColorSchemeKeyTokens HoverSupportingTextColor;
    public static final SearchBarTokens INSTANCE = new SearchBarTokens();
    private static final ColorSchemeKeyTokens InputTextColor;
    private static final TypographyKeyTokens InputTextFont;
    private static final ColorSchemeKeyTokens LeadingIconColor;
    private static final ColorSchemeKeyTokens PressedSupportingTextColor;
    private static final ColorSchemeKeyTokens SupportingTextColor;
    private static final TypographyKeyTokens SupportingTextFont;
    private static final ColorSchemeKeyTokens TrailingIconColor;

    static {
        ShapeKeyTokens shapeKeyTokens = ShapeKeyTokens.CornerFull;
        AvatarShape = shapeKeyTokens;
        AvatarSize = Dp.m5678constructorimpl((float) 30.0d);
        ContainerColor = ColorSchemeKeyTokens.Surface;
        ContainerElevation = ElevationTokens.INSTANCE.m2509getLevel3D9Ej5fM();
        ContainerHeight = Dp.m5678constructorimpl((float) 56.0d);
        ContainerShape = shapeKeyTokens;
        ContainerSurfaceTintLayerColor = ColorSchemeKeyTokens.SurfaceTint;
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurfaceVariant;
        HoverSupportingTextColor = colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurface;
        InputTextColor = colorSchemeKeyTokens2;
        TypographyKeyTokens typographyKeyTokens = TypographyKeyTokens.BodyLarge;
        InputTextFont = typographyKeyTokens;
        LeadingIconColor = colorSchemeKeyTokens2;
        PressedSupportingTextColor = colorSchemeKeyTokens;
        SupportingTextColor = colorSchemeKeyTokens;
        SupportingTextFont = typographyKeyTokens;
        TrailingIconColor = colorSchemeKeyTokens;
    }

    private SearchBarTokens() {
    }

    public final ShapeKeyTokens getAvatarShape() {
        return AvatarShape;
    }

    /* JADX INFO: renamed from: getAvatarSize-D9Ej5fM, reason: not valid java name */
    public final float m2805getAvatarSizeD9Ej5fM() {
        return AvatarSize;
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m2806getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    /* JADX INFO: renamed from: getContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m2807getContainerHeightD9Ej5fM() {
        return ContainerHeight;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    public final ColorSchemeKeyTokens getContainerSurfaceTintLayerColor() {
        return ContainerSurfaceTintLayerColor;
    }

    public final ColorSchemeKeyTokens getHoverSupportingTextColor() {
        return HoverSupportingTextColor;
    }

    public final ColorSchemeKeyTokens getInputTextColor() {
        return InputTextColor;
    }

    public final TypographyKeyTokens getInputTextFont() {
        return InputTextFont;
    }

    public final ColorSchemeKeyTokens getLeadingIconColor() {
        return LeadingIconColor;
    }

    public final ColorSchemeKeyTokens getPressedSupportingTextColor() {
        return PressedSupportingTextColor;
    }

    public final ColorSchemeKeyTokens getSupportingTextColor() {
        return SupportingTextColor;
    }

    public final TypographyKeyTokens getSupportingTextFont() {
        return SupportingTextFont;
    }

    public final ColorSchemeKeyTokens getTrailingIconColor() {
        return TrailingIconColor;
    }
}

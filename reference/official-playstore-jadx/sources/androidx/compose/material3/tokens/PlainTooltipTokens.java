package androidx.compose.material3.tokens;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/material3/tokens/PlainTooltipTokens;", "", "()V", "ContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "SupportingTextColor", "getSupportingTextColor", "SupportingTextFont", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "getSupportingTextFont", "()Landroidx/compose/material3/tokens/TypographyKeyTokens;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PlainTooltipTokens {
    public static final int $stable = 0;
    public static final PlainTooltipTokens INSTANCE = new PlainTooltipTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.InverseSurface;
    private static final ShapeKeyTokens ContainerShape = ShapeKeyTokens.CornerExtraSmall;
    private static final ColorSchemeKeyTokens SupportingTextColor = ColorSchemeKeyTokens.InverseOnSurface;
    private static final TypographyKeyTokens SupportingTextFont = TypographyKeyTokens.BodySmall;

    private PlainTooltipTokens() {
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    public final ShapeKeyTokens getContainerShape() {
        return ContainerShape;
    }

    public final ColorSchemeKeyTokens getSupportingTextColor() {
        return SupportingTextColor;
    }

    public final TypographyKeyTokens getSupportingTextFont() {
        return SupportingTextFont;
    }
}

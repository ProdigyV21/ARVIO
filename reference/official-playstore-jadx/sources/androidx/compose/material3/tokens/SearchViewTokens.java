package androidx.compose.material3.tokens;

import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0006R\u0011\u0010\u000e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0006R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0015\u0010\nR\u0011\u0010\u0016\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0019\u0010\u0018\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0019\u0010\nR\u0011\u0010\u001a\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0006R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0006R\u0011\u0010\"\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0006R\u0011\u0010$\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0011\u0010&\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006("}, d2 = {"Landroidx/compose/material3/tokens/SearchViewTokens;", "", "()V", "ContainerColor", "Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "getContainerColor", "()Landroidx/compose/material3/tokens/ColorSchemeKeyTokens;", "ContainerElevation", "Landroidx/compose/ui/unit/Dp;", "getContainerElevation-D9Ej5fM", "()F", "F", "ContainerSurfaceTintLayerColor", "getContainerSurfaceTintLayerColor", "DividerColor", "getDividerColor", "DockedContainerShape", "Landroidx/compose/material3/tokens/ShapeKeyTokens;", "getDockedContainerShape", "()Landroidx/compose/material3/tokens/ShapeKeyTokens;", "DockedHeaderContainerHeight", "getDockedHeaderContainerHeight-D9Ej5fM", "FullScreenContainerShape", "getFullScreenContainerShape", "FullScreenHeaderContainerHeight", "getFullScreenHeaderContainerHeight-D9Ej5fM", "HeaderInputTextColor", "getHeaderInputTextColor", "HeaderInputTextFont", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "getHeaderInputTextFont", "()Landroidx/compose/material3/tokens/TypographyKeyTokens;", "HeaderLeadingIconColor", "getHeaderLeadingIconColor", "HeaderSupportingTextColor", "getHeaderSupportingTextColor", "HeaderSupportingTextFont", "getHeaderSupportingTextFont", "HeaderTrailingIconColor", "getHeaderTrailingIconColor", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SearchViewTokens {
    public static final int $stable = 0;
    private static final ColorSchemeKeyTokens HeaderInputTextColor;
    private static final TypographyKeyTokens HeaderInputTextFont;
    private static final ColorSchemeKeyTokens HeaderLeadingIconColor;
    private static final ColorSchemeKeyTokens HeaderSupportingTextColor;
    private static final TypographyKeyTokens HeaderSupportingTextFont;
    private static final ColorSchemeKeyTokens HeaderTrailingIconColor;
    public static final SearchViewTokens INSTANCE = new SearchViewTokens();
    private static final ColorSchemeKeyTokens ContainerColor = ColorSchemeKeyTokens.Surface;
    private static final float ContainerElevation = ElevationTokens.INSTANCE.m2509getLevel3D9Ej5fM();
    private static final ColorSchemeKeyTokens ContainerSurfaceTintLayerColor = ColorSchemeKeyTokens.SurfaceTint;
    private static final ColorSchemeKeyTokens DividerColor = ColorSchemeKeyTokens.Outline;
    private static final ShapeKeyTokens DockedContainerShape = ShapeKeyTokens.CornerExtraLarge;
    private static final float DockedHeaderContainerHeight = Dp.m5678constructorimpl((float) 56.0d);
    private static final ShapeKeyTokens FullScreenContainerShape = ShapeKeyTokens.CornerNone;
    private static final float FullScreenHeaderContainerHeight = Dp.m5678constructorimpl((float) 72.0d);

    static {
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurface;
        HeaderInputTextColor = colorSchemeKeyTokens;
        TypographyKeyTokens typographyKeyTokens = TypographyKeyTokens.BodyLarge;
        HeaderInputTextFont = typographyKeyTokens;
        HeaderLeadingIconColor = colorSchemeKeyTokens;
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurfaceVariant;
        HeaderSupportingTextColor = colorSchemeKeyTokens2;
        HeaderSupportingTextFont = typographyKeyTokens;
        HeaderTrailingIconColor = colorSchemeKeyTokens2;
    }

    private SearchViewTokens() {
    }

    public final ColorSchemeKeyTokens getContainerColor() {
        return ContainerColor;
    }

    /* JADX INFO: renamed from: getContainerElevation-D9Ej5fM, reason: not valid java name */
    public final float m2808getContainerElevationD9Ej5fM() {
        return ContainerElevation;
    }

    public final ColorSchemeKeyTokens getContainerSurfaceTintLayerColor() {
        return ContainerSurfaceTintLayerColor;
    }

    public final ColorSchemeKeyTokens getDividerColor() {
        return DividerColor;
    }

    public final ShapeKeyTokens getDockedContainerShape() {
        return DockedContainerShape;
    }

    /* JADX INFO: renamed from: getDockedHeaderContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m2809getDockedHeaderContainerHeightD9Ej5fM() {
        return DockedHeaderContainerHeight;
    }

    public final ShapeKeyTokens getFullScreenContainerShape() {
        return FullScreenContainerShape;
    }

    /* JADX INFO: renamed from: getFullScreenHeaderContainerHeight-D9Ej5fM, reason: not valid java name */
    public final float m2810getFullScreenHeaderContainerHeightD9Ej5fM() {
        return FullScreenHeaderContainerHeight;
    }

    public final ColorSchemeKeyTokens getHeaderInputTextColor() {
        return HeaderInputTextColor;
    }

    public final TypographyKeyTokens getHeaderInputTextFont() {
        return HeaderInputTextFont;
    }

    public final ColorSchemeKeyTokens getHeaderLeadingIconColor() {
        return HeaderLeadingIconColor;
    }

    public final ColorSchemeKeyTokens getHeaderSupportingTextColor() {
        return HeaderSupportingTextColor;
    }

    public final TypographyKeyTokens getHeaderSupportingTextFont() {
        return HeaderSupportingTextFont;
    }

    public final ColorSchemeKeyTokens getHeaderTrailingIconColor() {
        return HeaderTrailingIconColor;
    }
}

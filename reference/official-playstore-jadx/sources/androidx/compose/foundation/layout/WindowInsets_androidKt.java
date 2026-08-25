package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.platform.ComposeView;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\bB\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\b\u0010\t\"(\u0010\u0011\u001a\u00020\u000b*\u00020\n2\u0006\u0010\f\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\".\u0010\u0011\u001a\u00020\u000b*\u00020\u00122\u0006\u0010\f\u001a\u00020\u000b8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\r\u0010\u0013\"\u0004\b\u000f\u0010\u0014\"\u0015\u0010\u001b\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\"\u0015\u0010\u001d\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001a\"\u0015\u0010\u001f\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001a\"\u0015\u0010!\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b \u0010\u001a\"\u0015\u0010#\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b\"\u0010\u001a\"\u0015\u0010%\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b$\u0010\u001a\"\u0015\u0010'\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b&\u0010\u001a\"\u0015\u0010)\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b(\u0010\u001a\"\u0015\u0010+\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b*\u0010\u001a\"\u0015\u0010-\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b,\u0010\u001a\"\u0015\u0010/\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b.\u0010\u001a\"\u0015\u00101\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b0\u0010\u001a\"\u0015\u00103\u001a\u00020\u0018*\u00020\u00178G¢\u0006\u0006\u001a\u0004\b2\u0010\u001a\"\u001e\u00107\u001a\u00020\u0018*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\b5\u00106\u001a\u0004\b4\u0010\u001a\"\u001e\u0010:\u001a\u00020\u0018*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\b9\u00106\u001a\u0004\b8\u0010\u001a\"\u001e\u0010=\u001a\u00020\u0018*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\b<\u00106\u001a\u0004\b;\u0010\u001a\"\u001e\u0010@\u001a\u00020\u0018*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\b?\u00106\u001a\u0004\b>\u0010\u001a\"\u001e\u0010C\u001a\u00020\u0018*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\bB\u00106\u001a\u0004\bA\u0010\u001a\"\u001e\u0010D\u001a\u00020\u000b*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\bF\u00106\u001a\u0004\bD\u0010E\"\u001e\u0010G\u001a\u00020\u000b*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\bH\u00106\u001a\u0004\bG\u0010E\"\u001e\u0010K\u001a\u00020\u000b*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\bJ\u00106\u001a\u0004\bI\u0010E\"\u001e\u0010N\u001a\u00020\u000b*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\bM\u00106\u001a\u0004\bL\u0010E\"\u001e\u0010Q\u001a\u00020\u000b*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\bP\u00106\u001a\u0004\bO\u0010E\"\u001e\u0010R\u001a\u00020\u000b*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\bS\u00106\u001a\u0004\bR\u0010E\"\u001e\u0010V\u001a\u00020\u0018*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\bU\u00106\u001a\u0004\bT\u0010\u001a\"\u001e\u0010Y\u001a\u00020\u0018*\u00020\u00178GX\u0087\u0004¢\u0006\f\u0012\u0004\bX\u00106\u001a\u0004\bW\u0010\u001a¨\u0006Z"}, d2 = {"Landroidx/core/graphics/c;", "Landroidx/compose/foundation/layout/InsetsValues;", "toInsetsValues", "(Landroidx/core/graphics/c;)Landroidx/compose/foundation/layout/InsetsValues;", "insets", "", ContentDisposition.Parameters.Name, "Landroidx/compose/foundation/layout/ValueInsets;", "ValueInsets", "(Landroidx/core/graphics/c;Ljava/lang/String;)Landroidx/compose/foundation/layout/ValueInsets;", "Landroidx/compose/ui/platform/AbstractComposeView;", "", "value", "getConsumeWindowInsets", "(Landroidx/compose/ui/platform/AbstractComposeView;)Z", "setConsumeWindowInsets", "(Landroidx/compose/ui/platform/AbstractComposeView;Z)V", "consumeWindowInsets", "Landroidx/compose/ui/platform/ComposeView;", "(Landroidx/compose/ui/platform/ComposeView;)Z", "(Landroidx/compose/ui/platform/ComposeView;Z)V", "getConsumeWindowInsets$annotations", "(Landroidx/compose/ui/platform/ComposeView;)V", "Landroidx/compose/foundation/layout/WindowInsets$Companion;", "Landroidx/compose/foundation/layout/WindowInsets;", "getCaptionBar", "(Landroidx/compose/foundation/layout/WindowInsets$Companion;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "captionBar", "getDisplayCutout", "displayCutout", "getIme", "ime", "getMandatorySystemGestures", "mandatorySystemGestures", "getNavigationBars", "navigationBars", "getStatusBars", "statusBars", "getSystemBars", "systemBars", "getSystemGestures", "systemGestures", "getTappableElement", "tappableElement", "getWaterfall", "waterfall", "getSafeDrawing", "safeDrawing", "getSafeGestures", "safeGestures", "getSafeContent", "safeContent", "getCaptionBarIgnoringVisibility", "getCaptionBarIgnoringVisibility$annotations", "(Landroidx/compose/foundation/layout/WindowInsets$Companion;)V", "captionBarIgnoringVisibility", "getNavigationBarsIgnoringVisibility", "getNavigationBarsIgnoringVisibility$annotations", "navigationBarsIgnoringVisibility", "getStatusBarsIgnoringVisibility", "getStatusBarsIgnoringVisibility$annotations", "statusBarsIgnoringVisibility", "getSystemBarsIgnoringVisibility", "getSystemBarsIgnoringVisibility$annotations", "systemBarsIgnoringVisibility", "getTappableElementIgnoringVisibility", "getTappableElementIgnoringVisibility$annotations", "tappableElementIgnoringVisibility", "isCaptionBarVisible", "(Landroidx/compose/foundation/layout/WindowInsets$Companion;Landroidx/compose/runtime/Composer;I)Z", "isCaptionBarVisible$annotations", "isImeVisible", "isImeVisible$annotations", "getAreStatusBarsVisible", "getAreStatusBarsVisible$annotations", "areStatusBarsVisible", "getAreNavigationBarsVisible", "getAreNavigationBarsVisible$annotations", "areNavigationBarsVisible", "getAreSystemBarsVisible", "getAreSystemBarsVisible$annotations", "areSystemBarsVisible", "isTappableElementVisible", "isTappableElementVisible$annotations", "getImeAnimationSource", "getImeAnimationSource$annotations", "imeAnimationSource", "getImeAnimationTarget", "getImeAnimationTarget$annotations", "imeAnimationTarget", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WindowInsets_androidKt {
    public static final ValueInsets ValueInsets(androidx.core.graphics.c cVar, String str) {
        return new ValueInsets(toInsetsValues(cVar), str);
    }

    public static final boolean getAreNavigationBarsVisible(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(710310464);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(710310464, i10, -1, "androidx.compose.foundation.layout.<get-areNavigationBarsVisible> (WindowInsets.android.kt:366)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 8).getNavigationBars().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return zIsVisible;
    }

    public static /* synthetic */ void getAreNavigationBarsVisible$annotations(WindowInsets.Companion companion) {
    }

    public static final boolean getAreStatusBarsVisible(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(1613283456);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1613283456, i10, -1, "androidx.compose.foundation.layout.<get-areStatusBarsVisible> (WindowInsets.android.kt:354)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 8).getStatusBars().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return zIsVisible;
    }

    public static /* synthetic */ void getAreStatusBarsVisible$annotations(WindowInsets.Companion companion) {
    }

    public static final boolean getAreSystemBarsVisible(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(1985490720);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1985490720, i10, -1, "androidx.compose.foundation.layout.<get-areSystemBarsVisible> (WindowInsets.android.kt:378)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 8).getSystemBars().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return zIsVisible;
    }

    public static /* synthetic */ void getAreSystemBarsVisible$annotations(WindowInsets.Companion companion) {
    }

    public static final WindowInsets getCaptionBar(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-1832025528);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1832025528, i10, -1, "androidx.compose.foundation.layout.<get-captionBar> (WindowInsets.android.kt:142)");
        }
        AndroidWindowInsets captionBar = WindowInsetsHolder.INSTANCE.current(composer, 8).getCaptionBar();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return captionBar;
    }

    public static final WindowInsets getCaptionBarIgnoringVisibility(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-1731251574);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1731251574, i10, -1, "androidx.compose.foundation.layout.<get-captionBarIgnoringVisibility> (WindowInsets.android.kt:266)");
        }
        ValueInsets captionBarIgnoringVisibility = WindowInsetsHolder.INSTANCE.current(composer, 8).getCaptionBarIgnoringVisibility();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return captionBarIgnoringVisibility;
    }

    public static /* synthetic */ void getCaptionBarIgnoringVisibility$annotations(WindowInsets.Companion companion) {
    }

    public static final boolean getConsumeWindowInsets(AbstractComposeView abstractComposeView) {
        Object tag = abstractComposeView.getTag(androidx.compose.ui.R.id.consume_window_insets_tag);
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }

    @x6.e
    public static /* synthetic */ void getConsumeWindowInsets$annotations(ComposeView composeView) {
    }

    public static final WindowInsets getDisplayCutout(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(1324817724);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1324817724, i10, -1, "androidx.compose.foundation.layout.<get-displayCutout> (WindowInsets.android.kt:151)");
        }
        AndroidWindowInsets displayCutout = WindowInsetsHolder.INSTANCE.current(composer, 8).getDisplayCutout();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return displayCutout;
    }

    public static final WindowInsets getIme(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-1466917860);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1466917860, i10, -1, "androidx.compose.foundation.layout.<get-ime> (WindowInsets.android.kt:165)");
        }
        AndroidWindowInsets ime = WindowInsetsHolder.INSTANCE.current(composer, 8).getIme();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return ime;
    }

    public static final WindowInsets getImeAnimationSource(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-1126064918);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1126064918, i10, -1, "androidx.compose.foundation.layout.<get-imeAnimationSource> (WindowInsets.android.kt:404)");
        }
        ValueInsets imeAnimationSource = WindowInsetsHolder.INSTANCE.current(composer, 8).getImeAnimationSource();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return imeAnimationSource;
    }

    public static /* synthetic */ void getImeAnimationSource$annotations(WindowInsets.Companion companion) {
    }

    public static final WindowInsets getImeAnimationTarget(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-466319786);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-466319786, i10, -1, "androidx.compose.foundation.layout.<get-imeAnimationTarget> (WindowInsets.android.kt:419)");
        }
        ValueInsets imeAnimationTarget = WindowInsetsHolder.INSTANCE.current(composer, 8).getImeAnimationTarget();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return imeAnimationTarget;
    }

    public static /* synthetic */ void getImeAnimationTarget$annotations(WindowInsets.Companion companion) {
    }

    public static final WindowInsets getMandatorySystemGestures(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(1369492988);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1369492988, i10, -1, "androidx.compose.foundation.layout.<get-mandatorySystemGestures> (WindowInsets.android.kt:174)");
        }
        AndroidWindowInsets mandatorySystemGestures = WindowInsetsHolder.INSTANCE.current(composer, 8).getMandatorySystemGestures();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return mandatorySystemGestures;
    }

    public static final WindowInsets getNavigationBars(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(1596175702);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1596175702, i10, -1, "androidx.compose.foundation.layout.<get-navigationBars> (WindowInsets.android.kt:184)");
        }
        AndroidWindowInsets navigationBars = WindowInsetsHolder.INSTANCE.current(composer, 8).getNavigationBars();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return navigationBars;
    }

    public static final WindowInsets getNavigationBarsIgnoringVisibility(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-1990981160);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1990981160, i10, -1, "androidx.compose.foundation.layout.<get-navigationBarsIgnoringVisibility> (WindowInsets.android.kt:280)");
        }
        ValueInsets navigationBarsIgnoringVisibility = WindowInsetsHolder.INSTANCE.current(composer, 8).getNavigationBarsIgnoringVisibility();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return navigationBarsIgnoringVisibility;
    }

    public static /* synthetic */ void getNavigationBarsIgnoringVisibility$annotations(WindowInsets.Companion companion) {
    }

    public static final WindowInsets getSafeContent(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-2026663876);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2026663876, i10, -1, "androidx.compose.foundation.layout.<get-safeContent> (WindowInsets.android.kt:254)");
        }
        WindowInsets safeContent = WindowInsetsHolder.INSTANCE.current(composer, 8).getSafeContent();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return safeContent;
    }

    public static final WindowInsets getSafeDrawing(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-49441252);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-49441252, i10, -1, "androidx.compose.foundation.layout.<get-safeDrawing> (WindowInsets.android.kt:234)");
        }
        WindowInsets safeDrawing = WindowInsetsHolder.INSTANCE.current(composer, 8).getSafeDrawing();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return safeDrawing;
    }

    public static final WindowInsets getSafeGestures(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-1594247780);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1594247780, i10, -1, "androidx.compose.foundation.layout.<get-safeGestures> (WindowInsets.android.kt:245)");
        }
        WindowInsets safeGestures = WindowInsetsHolder.INSTANCE.current(composer, 8).getSafeGestures();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return safeGestures;
    }

    public static final WindowInsets getStatusBars(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-675090670);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-675090670, i10, -1, "androidx.compose.foundation.layout.<get-statusBars> (WindowInsets.android.kt:192)");
        }
        AndroidWindowInsets statusBars = WindowInsetsHolder.INSTANCE.current(composer, 8).getStatusBars();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return statusBars;
    }

    public static final WindowInsets getStatusBarsIgnoringVisibility(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(594020756);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(594020756, i10, -1, "androidx.compose.foundation.layout.<get-statusBarsIgnoringVisibility> (WindowInsets.android.kt:292)");
        }
        ValueInsets statusBarsIgnoringVisibility = WindowInsetsHolder.INSTANCE.current(composer, 8).getStatusBarsIgnoringVisibility();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return statusBarsIgnoringVisibility;
    }

    public static /* synthetic */ void getStatusBarsIgnoringVisibility$annotations(WindowInsets.Companion companion) {
    }

    public static final WindowInsets getSystemBars(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-282936756);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-282936756, i10, -1, "androidx.compose.foundation.layout.<get-systemBars> (WindowInsets.android.kt:200)");
        }
        AndroidWindowInsets systemBars = WindowInsetsHolder.INSTANCE.current(composer, 8).getSystemBars();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return systemBars;
    }

    public static final WindowInsets getSystemBarsIgnoringVisibility(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(1564566798);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1564566798, i10, -1, "androidx.compose.foundation.layout.<get-systemBarsIgnoringVisibility> (WindowInsets.android.kt:305)");
        }
        ValueInsets systemBarsIgnoringVisibility = WindowInsetsHolder.INSTANCE.current(composer, 8).getSystemBarsIgnoringVisibility();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return systemBarsIgnoringVisibility;
    }

    public static /* synthetic */ void getSystemBarsIgnoringVisibility$annotations(WindowInsets.Companion companion) {
    }

    public static final WindowInsets getSystemGestures(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(989216224);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(989216224, i10, -1, "androidx.compose.foundation.layout.<get-systemGestures> (WindowInsets.android.kt:208)");
        }
        AndroidWindowInsets systemGestures = WindowInsetsHolder.INSTANCE.current(composer, 8).getSystemGestures();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return systemGestures;
    }

    public static final WindowInsets getTappableElement(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-1994205284);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1994205284, i10, -1, "androidx.compose.foundation.layout.<get-tappableElement> (WindowInsets.android.kt:216)");
        }
        AndroidWindowInsets tappableElement = WindowInsetsHolder.INSTANCE.current(composer, 8).getTappableElement();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return tappableElement;
    }

    public static final WindowInsets getTappableElementIgnoringVisibility(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-1488788292);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1488788292, i10, -1, "androidx.compose.foundation.layout.<get-tappableElementIgnoringVisibility> (WindowInsets.android.kt:318)");
        }
        ValueInsets tappableElementIgnoringVisibility = WindowInsetsHolder.INSTANCE.current(composer, 8).getTappableElementIgnoringVisibility();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return tappableElementIgnoringVisibility;
    }

    public static /* synthetic */ void getTappableElementIgnoringVisibility$annotations(WindowInsets.Companion companion) {
    }

    public static final WindowInsets getWaterfall(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(1943241020);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1943241020, i10, -1, "androidx.compose.foundation.layout.<get-waterfall> (WindowInsets.android.kt:224)");
        }
        ValueInsets waterfall = WindowInsetsHolder.INSTANCE.current(composer, 8).getWaterfall();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return waterfall;
    }

    public static final boolean isCaptionBarVisible(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-501076620);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-501076620, i10, -1, "androidx.compose.foundation.layout.<get-isCaptionBarVisible> (WindowInsets.android.kt:330)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 8).getCaptionBar().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return zIsVisible;
    }

    public static /* synthetic */ void isCaptionBarVisible$annotations(WindowInsets.Companion companion) {
    }

    public static final boolean isImeVisible(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-1873571424);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1873571424, i10, -1, "androidx.compose.foundation.layout.<get-isImeVisible> (WindowInsets.android.kt:342)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 8).getIme().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return zIsVisible;
    }

    public static /* synthetic */ void isImeVisible$annotations(WindowInsets.Companion companion) {
    }

    public static final boolean isTappableElementVisible(WindowInsets.Companion companion, Composer composer, int i10) {
        composer.startReplaceableGroup(-1737201120);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1737201120, i10, -1, "androidx.compose.foundation.layout.<get-isTappableElementVisible> (WindowInsets.android.kt:389)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 8).getTappableElement().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return zIsVisible;
    }

    public static /* synthetic */ void isTappableElementVisible$annotations(WindowInsets.Companion companion) {
    }

    public static final void setConsumeWindowInsets(AbstractComposeView abstractComposeView, boolean z) {
        abstractComposeView.setTag(androidx.compose.ui.R.id.consume_window_insets_tag, Boolean.valueOf(z));
    }

    public static final InsetsValues toInsetsValues(androidx.core.graphics.c cVar) {
        return new InsetsValues(cVar.f2086a, cVar.f2087b, cVar.f2088c, cVar.f2089d);
    }

    public static final /* synthetic */ boolean getConsumeWindowInsets(ComposeView composeView) {
        Object tag = composeView.getTag(androidx.compose.ui.R.id.consume_window_insets_tag);
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return true;
    }
}

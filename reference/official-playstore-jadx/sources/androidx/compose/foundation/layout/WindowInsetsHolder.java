package androidx.compose.foundation.layout;

import android.os.Build;
import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.view.b2;
import androidx.core.view.k1;
import androidx.core.view.n3;
import androidx.core.view.q3;
import androidx.core.view.u;
import androidx.core.view.v;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 O2\u00020\u0001:\u0001OB\u001b\b\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\nJ\u001f\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R\u0017\u0010\u001b\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018R\u0017\u0010\u001d\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001e\u0010\u0018R\u0017\u0010\u001f\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010\u0018R\u0017\u0010!\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b!\u0010\u0016\u001a\u0004\b\"\u0010\u0018R\u0017\u0010#\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b#\u0010\u0016\u001a\u0004\b$\u0010\u0018R\u0017\u0010%\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b%\u0010\u0016\u001a\u0004\b&\u0010\u0018R\u0017\u0010'\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b'\u0010\u0016\u001a\u0004\b(\u0010\u0018R\u0017\u0010*\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0017\u0010/\u001a\u00020.8\u0006¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0017\u00103\u001a\u00020.8\u0006¢\u0006\f\n\u0004\b3\u00100\u001a\u0004\b4\u00102R\u0017\u00105\u001a\u00020.8\u0006¢\u0006\f\n\u0004\b5\u00100\u001a\u0004\b6\u00102R\u0017\u00107\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b7\u0010+\u001a\u0004\b8\u0010-R\u0017\u00109\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b9\u0010+\u001a\u0004\b:\u0010-R\u0017\u0010;\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b;\u0010+\u001a\u0004\b<\u0010-R\u0017\u0010=\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b=\u0010+\u001a\u0004\b>\u0010-R\u0017\u0010?\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b?\u0010+\u001a\u0004\b@\u0010-R\u0017\u0010A\u001a\u00020)8\u0006¢\u0006\f\n\u0004\bA\u0010+\u001a\u0004\bB\u0010-R\u0017\u0010C\u001a\u00020)8\u0006¢\u0006\f\n\u0004\bC\u0010+\u001a\u0004\bD\u0010-R\u0017\u0010F\u001a\u00020E8\u0006¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bH\u0010IR\u0016\u0010J\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010M\u001a\u00020L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010N¨\u0006P"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsHolder;", "", "Landroidx/core/view/q3;", "insets", "Landroid/view/View;", "view", "<init>", "(Landroidx/core/view/q3;Landroid/view/View;)V", "Lx6/t0;", "incrementAccessors", "(Landroid/view/View;)V", "decrementAccessors", "windowInsets", "", "types", "update", "(Landroidx/core/view/q3;I)V", "updateImeAnimationSource", "(Landroidx/core/view/q3;)V", "updateImeAnimationTarget", "Landroidx/compose/foundation/layout/AndroidWindowInsets;", "captionBar", "Landroidx/compose/foundation/layout/AndroidWindowInsets;", "getCaptionBar", "()Landroidx/compose/foundation/layout/AndroidWindowInsets;", "displayCutout", "getDisplayCutout", "ime", "getIme", "mandatorySystemGestures", "getMandatorySystemGestures", "navigationBars", "getNavigationBars", "statusBars", "getStatusBars", "systemBars", "getSystemBars", "systemGestures", "getSystemGestures", "tappableElement", "getTappableElement", "Landroidx/compose/foundation/layout/ValueInsets;", "waterfall", "Landroidx/compose/foundation/layout/ValueInsets;", "getWaterfall", "()Landroidx/compose/foundation/layout/ValueInsets;", "Landroidx/compose/foundation/layout/WindowInsets;", "safeDrawing", "Landroidx/compose/foundation/layout/WindowInsets;", "getSafeDrawing", "()Landroidx/compose/foundation/layout/WindowInsets;", "safeGestures", "getSafeGestures", "safeContent", "getSafeContent", "captionBarIgnoringVisibility", "getCaptionBarIgnoringVisibility", "navigationBarsIgnoringVisibility", "getNavigationBarsIgnoringVisibility", "statusBarsIgnoringVisibility", "getStatusBarsIgnoringVisibility", "systemBarsIgnoringVisibility", "getSystemBarsIgnoringVisibility", "tappableElementIgnoringVisibility", "getTappableElementIgnoringVisibility", "imeAnimationTarget", "getImeAnimationTarget", "imeAnimationSource", "getImeAnimationSource", "", "consumes", "Z", "getConsumes", "()Z", "accessCount", "I", "Landroidx/compose/foundation/layout/InsetsListener;", "insetsListener", "Landroidx/compose/foundation/layout/InsetsListener;", "Companion", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WindowInsetsHolder {
    private static boolean testInsets;
    private int accessCount;
    private final AndroidWindowInsets captionBar;
    private final ValueInsets captionBarIgnoringVisibility;
    private final boolean consumes;
    private final AndroidWindowInsets displayCutout;
    private final AndroidWindowInsets ime;
    private final ValueInsets imeAnimationSource;
    private final ValueInsets imeAnimationTarget;
    private final InsetsListener insetsListener;
    private final AndroidWindowInsets mandatorySystemGestures;
    private final AndroidWindowInsets navigationBars;
    private final ValueInsets navigationBarsIgnoringVisibility;
    private final WindowInsets safeContent;
    private final WindowInsets safeDrawing;
    private final WindowInsets safeGestures;
    private final AndroidWindowInsets statusBars;
    private final ValueInsets statusBarsIgnoringVisibility;
    private final AndroidWindowInsets systemBars;
    private final ValueInsets systemBarsIgnoringVisibility;
    private final AndroidWindowInsets systemGestures;
    private final AndroidWindowInsets tappableElement;
    private final ValueInsets tappableElementIgnoringVisibility;
    private final ValueInsets waterfall;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final WeakHashMap<View, WindowInsetsHolder> viewMap = new WeakHashMap<>();

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ)\u0010\u0010\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J)\u0010\u0013\u001a\u00020\u00122\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u001cR \u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsHolder$Companion;", "", "<init>", "()V", "Landroid/view/View;", "view", "Landroidx/compose/foundation/layout/WindowInsetsHolder;", "getOrCreateFor", "(Landroid/view/View;)Landroidx/compose/foundation/layout/WindowInsetsHolder;", "Landroidx/core/view/q3;", "windowInsets", "", LinkHeader.Parameters.Type, "", ContentDisposition.Parameters.Name, "Landroidx/compose/foundation/layout/AndroidWindowInsets;", "systemInsets", "(Landroidx/core/view/q3;ILjava/lang/String;)Landroidx/compose/foundation/layout/AndroidWindowInsets;", "Landroidx/compose/foundation/layout/ValueInsets;", "valueInsetsIgnoringVisibility", "(Landroidx/core/view/q3;ILjava/lang/String;)Landroidx/compose/foundation/layout/ValueInsets;", "", "testInsets", "Lx6/t0;", "setUseTestInsets", "(Z)V", "current", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsetsHolder;", "Z", "Ljava/util/WeakHashMap;", "viewMap", "Ljava/util/WeakHashMap;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private final WindowInsetsHolder getOrCreateFor(View view) {
            WindowInsetsHolder windowInsetsHolder;
            synchronized (WindowInsetsHolder.viewMap) {
                try {
                    WeakHashMap weakHashMap = WindowInsetsHolder.viewMap;
                    Object obj = weakHashMap.get(view);
                    Object obj2 = obj;
                    if (obj == null) {
                        WindowInsetsHolder windowInsetsHolder2 = new WindowInsetsHolder(null, view, false ? 1 : 0);
                        weakHashMap.put(view, windowInsetsHolder2);
                        obj2 = windowInsetsHolder2;
                    }
                    windowInsetsHolder = (WindowInsetsHolder) obj2;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return windowInsetsHolder;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final AndroidWindowInsets systemInsets(q3 windowInsets, int type, String name) {
            AndroidWindowInsets androidWindowInsets = new AndroidWindowInsets(type, name);
            if (windowInsets != null) {
                androidWindowInsets.update$foundation_layout_release(windowInsets, type);
            }
            return androidWindowInsets;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ValueInsets valueInsetsIgnoringVisibility(q3 windowInsets, int type, String name) {
            androidx.core.graphics.c cVarG;
            if (windowInsets == null || (cVarG = windowInsets.f2300a.g(type)) == null) {
                cVarG = androidx.core.graphics.c.f2085e;
            }
            return WindowInsets_androidKt.ValueInsets(cVarG, name);
        }

        public final WindowInsetsHolder current(Composer composer, int i10) {
            composer.startReplaceableGroup(-1366542614);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1366542614, i10, -1, "androidx.compose.foundation.layout.WindowInsetsHolder.Companion.current (WindowInsets.android.kt:626)");
            }
            View view = (View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView());
            WindowInsetsHolder orCreateFor = getOrCreateFor(view);
            EffectsKt.DisposableEffect(orCreateFor, new WindowInsetsHolder$Companion$current$1(orCreateFor, view), composer, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return orCreateFor;
        }

        public final void setUseTestInsets(boolean testInsets) {
            WindowInsetsHolder.testInsets = testInsets;
        }

        private Companion() {
        }
    }

    public /* synthetic */ WindowInsetsHolder(q3 q3Var, View view, h hVar) {
        this(q3Var, view);
    }

    public static /* synthetic */ void update$default(WindowInsetsHolder windowInsetsHolder, q3 q3Var, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        windowInsetsHolder.update(q3Var, i10);
    }

    public final void decrementAccessors(View view) {
        int i10 = this.accessCount - 1;
        this.accessCount = i10;
        if (i10 == 0) {
            WeakHashMap weakHashMap = b2.f2200a;
            k1.u(view, null);
            b2.j(view, null);
            view.removeOnAttachStateChangeListener(this.insetsListener);
        }
    }

    public final AndroidWindowInsets getCaptionBar() {
        return this.captionBar;
    }

    public final ValueInsets getCaptionBarIgnoringVisibility() {
        return this.captionBarIgnoringVisibility;
    }

    public final boolean getConsumes() {
        return this.consumes;
    }

    public final AndroidWindowInsets getDisplayCutout() {
        return this.displayCutout;
    }

    public final AndroidWindowInsets getIme() {
        return this.ime;
    }

    public final ValueInsets getImeAnimationSource() {
        return this.imeAnimationSource;
    }

    public final ValueInsets getImeAnimationTarget() {
        return this.imeAnimationTarget;
    }

    public final AndroidWindowInsets getMandatorySystemGestures() {
        return this.mandatorySystemGestures;
    }

    public final AndroidWindowInsets getNavigationBars() {
        return this.navigationBars;
    }

    public final ValueInsets getNavigationBarsIgnoringVisibility() {
        return this.navigationBarsIgnoringVisibility;
    }

    public final WindowInsets getSafeContent() {
        return this.safeContent;
    }

    public final WindowInsets getSafeDrawing() {
        return this.safeDrawing;
    }

    public final WindowInsets getSafeGestures() {
        return this.safeGestures;
    }

    public final AndroidWindowInsets getStatusBars() {
        return this.statusBars;
    }

    public final ValueInsets getStatusBarsIgnoringVisibility() {
        return this.statusBarsIgnoringVisibility;
    }

    public final AndroidWindowInsets getSystemBars() {
        return this.systemBars;
    }

    public final ValueInsets getSystemBarsIgnoringVisibility() {
        return this.systemBarsIgnoringVisibility;
    }

    public final AndroidWindowInsets getSystemGestures() {
        return this.systemGestures;
    }

    public final AndroidWindowInsets getTappableElement() {
        return this.tappableElement;
    }

    public final ValueInsets getTappableElementIgnoringVisibility() {
        return this.tappableElementIgnoringVisibility;
    }

    public final ValueInsets getWaterfall() {
        return this.waterfall;
    }

    public final void incrementAccessors(View view) {
        if (this.accessCount == 0) {
            InsetsListener insetsListener = this.insetsListener;
            WeakHashMap weakHashMap = b2.f2200a;
            k1.u(view, insetsListener);
            if (view.isAttachedToWindow()) {
                view.requestApplyInsets();
            }
            view.addOnAttachStateChangeListener(this.insetsListener);
            b2.j(view, this.insetsListener);
        }
        this.accessCount++;
    }

    public final void update(q3 windowInsets, int types) {
        if (testInsets) {
            windowInsets = q3.c(null, windowInsets.b());
        }
        this.captionBar.update$foundation_layout_release(windowInsets, types);
        this.ime.update$foundation_layout_release(windowInsets, types);
        this.displayCutout.update$foundation_layout_release(windowInsets, types);
        this.navigationBars.update$foundation_layout_release(windowInsets, types);
        this.statusBars.update$foundation_layout_release(windowInsets, types);
        this.systemBars.update$foundation_layout_release(windowInsets, types);
        this.systemGestures.update$foundation_layout_release(windowInsets, types);
        this.tappableElement.update$foundation_layout_release(windowInsets, types);
        this.mandatorySystemGestures.update$foundation_layout_release(windowInsets, types);
        if (types == 0) {
            ValueInsets valueInsets = this.captionBarIgnoringVisibility;
            n3 n3Var = windowInsets.f2300a;
            valueInsets.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(n3Var.g(4)));
            this.navigationBarsIgnoringVisibility.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(n3Var.g(2)));
            this.statusBarsIgnoringVisibility.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(n3Var.g(1)));
            this.systemBarsIgnoringVisibility.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(n3Var.g(7)));
            this.tappableElementIgnoringVisibility.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(n3Var.g(64)));
            v vVarE = n3Var.e();
            if (vVarE != null) {
                this.waterfall.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(Build.VERSION.SDK_INT >= 30 ? androidx.core.graphics.c.c(u.b(vVarE.f2323a)) : androidx.core.graphics.c.f2085e));
            }
        }
        Snapshot.INSTANCE.sendApplyNotifications();
    }

    public final void updateImeAnimationSource(q3 windowInsets) {
        this.imeAnimationSource.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(windowInsets.f2300a.f(8)));
    }

    public final void updateImeAnimationTarget(q3 windowInsets) {
        this.imeAnimationTarget.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(windowInsets.f2300a.f(8)));
    }

    private WindowInsetsHolder(q3 q3Var, View view) {
        v vVarE;
        Companion companion = INSTANCE;
        this.captionBar = companion.systemInsets(q3Var, 4, "captionBar");
        AndroidWindowInsets androidWindowInsetsSystemInsets = companion.systemInsets(q3Var, 128, "displayCutout");
        this.displayCutout = androidWindowInsetsSystemInsets;
        AndroidWindowInsets androidWindowInsetsSystemInsets2 = companion.systemInsets(q3Var, 8, "ime");
        this.ime = androidWindowInsetsSystemInsets2;
        AndroidWindowInsets androidWindowInsetsSystemInsets3 = companion.systemInsets(q3Var, 32, "mandatorySystemGestures");
        this.mandatorySystemGestures = androidWindowInsetsSystemInsets3;
        this.navigationBars = companion.systemInsets(q3Var, 2, "navigationBars");
        this.statusBars = companion.systemInsets(q3Var, 1, "statusBars");
        AndroidWindowInsets androidWindowInsetsSystemInsets4 = companion.systemInsets(q3Var, 7, "systemBars");
        this.systemBars = androidWindowInsetsSystemInsets4;
        AndroidWindowInsets androidWindowInsetsSystemInsets5 = companion.systemInsets(q3Var, 16, "systemGestures");
        this.systemGestures = androidWindowInsetsSystemInsets5;
        AndroidWindowInsets androidWindowInsetsSystemInsets6 = companion.systemInsets(q3Var, 64, "tappableElement");
        this.tappableElement = androidWindowInsetsSystemInsets6;
        androidx.core.graphics.c cVarC = androidx.core.graphics.c.f2085e;
        if (q3Var != null && (vVarE = q3Var.f2300a.e()) != null && Build.VERSION.SDK_INT >= 30) {
            cVarC = androidx.core.graphics.c.c(u.b(vVarE.f2323a));
        }
        ValueInsets ValueInsets = WindowInsets_androidKt.ValueInsets(cVarC, "waterfall");
        this.waterfall = ValueInsets;
        WindowInsets windowInsetsUnion = WindowInsetsKt.union(WindowInsetsKt.union(androidWindowInsetsSystemInsets4, androidWindowInsetsSystemInsets2), androidWindowInsetsSystemInsets);
        this.safeDrawing = windowInsetsUnion;
        WindowInsets windowInsetsUnion2 = WindowInsetsKt.union(WindowInsetsKt.union(WindowInsetsKt.union(androidWindowInsetsSystemInsets6, androidWindowInsetsSystemInsets3), androidWindowInsetsSystemInsets5), ValueInsets);
        this.safeGestures = windowInsetsUnion2;
        this.safeContent = WindowInsetsKt.union(windowInsetsUnion, windowInsetsUnion2);
        this.captionBarIgnoringVisibility = companion.valueInsetsIgnoringVisibility(q3Var, 4, "captionBarIgnoringVisibility");
        this.navigationBarsIgnoringVisibility = companion.valueInsetsIgnoringVisibility(q3Var, 2, "navigationBarsIgnoringVisibility");
        this.statusBarsIgnoringVisibility = companion.valueInsetsIgnoringVisibility(q3Var, 1, "statusBarsIgnoringVisibility");
        this.systemBarsIgnoringVisibility = companion.valueInsetsIgnoringVisibility(q3Var, 7, "systemBarsIgnoringVisibility");
        this.tappableElementIgnoringVisibility = companion.valueInsetsIgnoringVisibility(q3Var, 64, "tappableElementIgnoringVisibility");
        this.imeAnimationTarget = companion.valueInsetsIgnoringVisibility(q3Var, 8, "imeAnimationTarget");
        this.imeAnimationSource = companion.valueInsetsIgnoringVisibility(q3Var, 8, "imeAnimationSource");
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        Object tag = view2 != null ? view2.getTag(androidx.compose.ui.R.id.consume_window_insets_tag) : null;
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        this.consumes = bool != null ? bool.booleanValue() : true;
        this.insetsListener = new InsetsListener(this);
    }
}

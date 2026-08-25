package androidx.compose.ui.platform;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.view.ViewParent;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MonotonicFrameClock;
import androidx.compose.runtime.PausableMonotonicFrameClock;
import androidx.compose.runtime.Recomposer;
import androidx.compose.ui.MotionDurationScale;
import androidx.compose.ui.R;
import androidx.lifecycle.w;
import androidx.lifecycle.y;
import androidx.lifecycle.z0;
import java.util.LinkedHashMap;
import java.util.Map;
import ka.l0;
import ka.m0;
import ka.p2;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import m2.f0;
import na.g1;
import na.h1;
import na.t0;
import na.y0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0010\u001a\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\t\u001a)\u0010\u000f\u001a\u00020\u000e*\u00020\u00002\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\"&\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013\",\u0010\u0018\u001a\u0004\u0018\u00010\u0001*\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0003\"\u0004\b\u0016\u0010\u0017\"\u0018\u0010\u001b\u001a\u00020\u0000*\u00020\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\"\u001e\u0010 \u001a\u00020\u000e*\u00020\u00008@X\u0080\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Landroid/view/View;", "Landroidx/compose/runtime/CompositionContext;", "findViewTreeCompositionContext", "(Landroid/view/View;)Landroidx/compose/runtime/CompositionContext;", "Landroid/content/Context;", "applicationContext", "Lna/h1;", "", "getAnimationScaleFlowFor", "(Landroid/content/Context;)Lna/h1;", "Ld7/j;", "coroutineContext", "Landroidx/lifecycle/r;", "lifecycle", "Landroidx/compose/runtime/Recomposer;", "createLifecycleAwareWindowRecomposer", "(Landroid/view/View;Ld7/j;Landroidx/lifecycle/r;)Landroidx/compose/runtime/Recomposer;", "", "animationScale", "Ljava/util/Map;", "value", "getCompositionContext", "setCompositionContext", "(Landroid/view/View;Landroidx/compose/runtime/CompositionContext;)V", "compositionContext", "getContentChild", "(Landroid/view/View;)Landroid/view/View;", "contentChild", "getWindowRecomposer", "(Landroid/view/View;)Landroidx/compose/runtime/Recomposer;", "getWindowRecomposer$annotations", "(Landroid/view/View;)V", "windowRecomposer", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WindowRecomposer_androidKt {
    private static final Map<Context, h1<Float>> animationScale = new LinkedHashMap();

    public static final Recomposer createLifecycleAwareWindowRecomposer(final View view, d7.j jVar, androidx.lifecycle.r rVar) {
        final PausableMonotonicFrameClock pausableMonotonicFrameClock;
        if (jVar.get(d7.f.f14687i) == null || jVar.get(MonotonicFrameClock.INSTANCE) == null) {
            jVar = AndroidUiDispatcher.INSTANCE.getCurrentThread().plus(jVar);
        }
        MonotonicFrameClock monotonicFrameClock = (MonotonicFrameClock) jVar.get(MonotonicFrameClock.INSTANCE);
        if (monotonicFrameClock != null) {
            PausableMonotonicFrameClock pausableMonotonicFrameClock2 = new PausableMonotonicFrameClock(monotonicFrameClock);
            pausableMonotonicFrameClock2.pause();
            pausableMonotonicFrameClock = pausableMonotonicFrameClock2;
        } else {
            pausableMonotonicFrameClock = null;
        }
        final k0 k0Var = new k0();
        d7.j motionDurationScaleImpl = (MotionDurationScale) jVar.get(MotionDurationScale.INSTANCE);
        if (motionDurationScaleImpl == null) {
            motionDurationScaleImpl = new MotionDurationScaleImpl();
            k0Var.f19746i = motionDurationScaleImpl;
        }
        d7.j jVarPlus = jVar.plus(pausableMonotonicFrameClock != null ? pausableMonotonicFrameClock : d7.k.f14688i).plus(motionDurationScaleImpl);
        final Recomposer recomposer = new Recomposer(jVarPlus);
        recomposer.pauseCompositionFrameClock();
        final pa.e eVarA = l0.a(jVarPlus);
        if (rVar == null) {
            y yVarE = z0.e(view);
            rVar = yVarE != null ? yVarE.getLifecycle() : null;
        }
        if (rVar != null) {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.WindowRecomposer_androidKt.createLifecycleAwareWindowRecomposer.1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View v2) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View v2) {
                    view.removeOnAttachStateChangeListener(this);
                    recomposer.cancel();
                }
            });
            rVar.addObserver(new w() { // from class: androidx.compose.ui.platform.WindowRecomposer_androidKt.createLifecycleAwareWindowRecomposer.2

                /* JADX INFO: renamed from: androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$WhenMappings */
                @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[androidx.lifecycle.p.values().length];
                        try {
                            iArr[androidx.lifecycle.p.ON_CREATE.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[androidx.lifecycle.p.ON_START.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[androidx.lifecycle.p.ON_STOP.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        try {
                            iArr[androidx.lifecycle.p.ON_DESTROY.ordinal()] = 4;
                        } catch (NoSuchFieldError unused4) {
                        }
                        try {
                            iArr[androidx.lifecycle.p.ON_PAUSE.ordinal()] = 5;
                        } catch (NoSuchFieldError unused5) {
                        }
                        try {
                            iArr[androidx.lifecycle.p.ON_RESUME.ordinal()] = 6;
                        } catch (NoSuchFieldError unused6) {
                        }
                        try {
                            iArr[androidx.lifecycle.p.ON_ANY.ordinal()] = 7;
                        } catch (NoSuchFieldError unused7) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                @Override // androidx.lifecycle.w
                public void onStateChanged(y source, androidx.lifecycle.p event) {
                    int i10 = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
                    if (i10 == 1) {
                        m0.p(eVarA, null, 4, new WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1(k0Var, recomposer, source, this, view, null), 1);
                        return;
                    }
                    if (i10 == 2) {
                        PausableMonotonicFrameClock pausableMonotonicFrameClock3 = pausableMonotonicFrameClock;
                        if (pausableMonotonicFrameClock3 != null) {
                            pausableMonotonicFrameClock3.resume();
                        }
                        recomposer.resumeCompositionFrameClock();
                        return;
                    }
                    if (i10 == 3) {
                        recomposer.pauseCompositionFrameClock();
                    } else {
                        if (i10 != 4) {
                            return;
                        }
                        recomposer.cancel();
                    }
                }
            });
            return recomposer;
        }
        throw new IllegalStateException(("ViewTreeLifecycleOwner not found from " + view).toString());
    }

    public static /* synthetic */ Recomposer createLifecycleAwareWindowRecomposer$default(View view, d7.j jVar, androidx.lifecycle.r rVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            jVar = d7.k.f14688i;
        }
        if ((i10 & 2) != 0) {
            rVar = null;
        }
        return createLifecycleAwareWindowRecomposer(view, jVar, rVar);
    }

    public static final CompositionContext findViewTreeCompositionContext(View view) {
        CompositionContext compositionContext = getCompositionContext(view);
        if (compositionContext != null) {
            return compositionContext;
        }
        for (ViewParent parent = view.getParent(); compositionContext == null && (parent instanceof View); parent = parent.getParent()) {
            compositionContext = getCompositionContext((View) parent);
        }
        return compositionContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r5v1, types: [androidx.compose.ui.platform.WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$contentObserver$1] */
    public static final h1<Float> getAnimationScaleFlowFor(Context context) {
        h1<Float> h1Var;
        Map<Context, h1<Float>> map = animationScale;
        synchronized (map) {
            try {
                h1<Float> h1VarO = map.get(context);
                if (h1VarO == null) {
                    ContentResolver contentResolver = context.getContentResolver();
                    Uri uriFor = Settings.Global.getUriFor("animator_duration_scale");
                    final ma.f fVarA = f0.a(-1, 0, 6);
                    final Handler handlerI = a.a.I(Looper.getMainLooper());
                    t0 t0Var = new t0(new WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1(contentResolver, uriFor, new ContentObserver(handlerI) { // from class: androidx.compose.ui.platform.WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$contentObserver$1
                        @Override // android.database.ContentObserver
                        public void onChange(boolean selfChange, Uri uri) {
                            fVarA.mo6685trySendJP2dKIU(x6.t0.f22605a);
                        }
                    }, fVarA, context, null));
                    p2 p2VarD = m0.d();
                    x0 x0Var = x0.f19652a;
                    h1VarO = y0.o(t0Var, new pa.e(d7.h.a(p2VarD, pa.q.f21294a)), new g1(), Float.valueOf(Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f)));
                    map.put(context, h1VarO);
                }
                h1Var = h1VarO;
            } catch (Throwable th) {
                throw th;
            }
        }
        return h1Var;
    }

    public static final CompositionContext getCompositionContext(View view) {
        Object tag = view.getTag(R.id.androidx_compose_ui_view_composition_context);
        if (tag instanceof CompositionContext) {
            return (CompositionContext) tag;
        }
        return null;
    }

    private static final View getContentChild(View view) {
        Object parent = view.getParent();
        while (parent instanceof View) {
            View view2 = (View) parent;
            if (view2.getId() == 16908290) {
                break;
            }
            parent = view2.getParent();
            view = view2;
        }
        return view;
    }

    public static final Recomposer getWindowRecomposer(View view) {
        if (!view.isAttachedToWindow()) {
            throw new IllegalStateException(("Cannot locate windowRecomposer; View " + view + " is not attached to a window").toString());
        }
        View contentChild = getContentChild(view);
        CompositionContext compositionContext = getCompositionContext(contentChild);
        if (compositionContext == null) {
            return WindowRecomposerPolicy.INSTANCE.createAndInstallWindowRecomposer$ui_release(contentChild);
        }
        if (compositionContext instanceof Recomposer) {
            return (Recomposer) compositionContext;
        }
        throw new IllegalStateException("root viewTreeParentCompositionContext is not a Recomposer");
    }

    public static /* synthetic */ void getWindowRecomposer$annotations(View view) {
    }

    public static final void setCompositionContext(View view, CompositionContext compositionContext) {
        view.setTag(R.id.androidx_compose_ui_view_composition_context, compositionContext);
    }
}

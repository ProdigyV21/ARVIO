package androidx.compose.runtime;

import a8.x1;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\u001a+\u0010\u0004\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0013\b\u0004\u0010\u0003\u001a\r\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0002\b\u0002H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a5\u0010\u0004\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0013\b\u0004\u0010\u0003\u001a\r\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0002\b\u0002H\u0087\b¢\u0006\u0004\b\u0004\u0010\b\u001a?\u0010\u0004\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00062\u0013\b\u0004\u0010\u0003\u001a\r\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0002\b\u0002H\u0087\b¢\u0006\u0004\b\u0004\u0010\n\u001aI\u0010\u0004\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0013\b\u0004\u0010\u0003\u001a\r\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0002\b\u0002H\u0087\b¢\u0006\u0004\b\u0004\u0010\f\u001aC\u0010\u0004\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\r\"\u0004\u0018\u00010\u00062\u0013\b\u0004\u0010\u0003\u001a\r\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0002\b\u0002H\u0087\b¢\u0006\u0004\b\u0004\u0010\u000f\u001aA\u0010\u0012\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\r\"\u0004\u0018\u00010\u00062\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a-\u0010\u0016\u001a\u00020\u00142\b\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00140\u0001¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0004\b\u0016\u0010\u0017\u001a-\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\u0013\b\u0004\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00140\u0001¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0004\b\u001a\u0010\u001b\u001a^\u0010#\u001a\u00020\u0014\"\b\b\u0000\u0010\u0000*\u00020\u0006\"\u000e\b\u0001\u0010\u001d\u0018\u0001*\u0006\u0012\u0002\b\u00030\u001c2\u000e\b\b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\"\u0010\"\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000 \u0012\u0004\u0012\u00020\u00140\u001f¢\u0006\u0002\b\u0002¢\u0006\u0002\b!H\u0087\b¢\u0006\u0004\b#\u0010$\u001a^\u0010%\u001a\u00020\u0014\"\b\b\u0000\u0010\u0000*\u00020\u0006\"\u000e\b\u0001\u0010\u001d\u0018\u0001*\u0006\u0012\u0002\b\u00030\u001c2\u000e\b\b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\"\u0010\"\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000 \u0012\u0004\u0012\u00020\u00140\u001f¢\u0006\u0002\b\u0002¢\u0006\u0002\b!H\u0087\b¢\u0006\u0004\b%\u0010$\u001am\u0010#\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u0000\"\u000e\b\u0001\u0010\u001d\u0018\u0001*\u0006\u0012\u0002\b\u00030\u001c2\u000e\b\b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\"\u0010\"\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000 \u0012\u0004\u0012\u00020\u00140\u001f¢\u0006\u0002\b\u0002¢\u0006\u0002\b!2\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00140\u0001¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0004\b#\u0010&\u001am\u0010%\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u0000\"\u000e\b\u0001\u0010\u001d\u0018\u0001*\u0006\u0012\u0002\b\u00030\u001c2\u000e\b\b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\"\u0010\"\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000 \u0012\u0004\u0012\u00020\u00140\u001f¢\u0006\u0002\b\u0002¢\u0006\u0002\b!2\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00140\u0001¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0004\b%\u0010&\u001a\u0093\u0001\u0010#\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u0000\"\u000e\b\u0001\u0010\u001d\u0018\u0001*\u0006\u0012\u0002\b\u00030\u001c2\u000e\b\b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\"\u0010\"\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000 \u0012\u0004\u0012\u00020\u00140\u001f¢\u0006\u0002\b\u0002¢\u0006\u0002\b!2$\b\b\u0010(\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000'\u0012\u0004\u0012\u00020\u00140\u001f¢\u0006\u0002\b\u0010¢\u0006\u0002\b!2\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00140\u0001¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0004\b#\u0010)\u001a\u0093\u0001\u0010%\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u0000\"\u000e\b\u0001\u0010\u001d\u0018\u0001*\u0006\u0012\u0002\b\u00030\u001c2\u000e\b\b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\"\u0010\"\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000 \u0012\u0004\u0012\u00020\u00140\u001f¢\u0006\u0002\b\u0002¢\u0006\u0002\b!2$\b\b\u0010(\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000'\u0012\u0004\u0012\u00020\u00140\u001f¢\u0006\u0002\b\u0010¢\u0006\u0002\b!2\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00140\u0001¢\u0006\u0002\b\u0010H\u0087\b¢\u0006\u0004\b%\u0010)\u001a\u000f\u0010*\u001a\u00020\u0014H\u0001¢\u0006\u0004\b*\u0010+\u001a\u000f\u0010-\u001a\u00020,H\u0007¢\u0006\u0004\b-\u0010.\"\u0011\u00102\u001a\u00020/8G¢\u0006\u0006\u001a\u0004\b0\u00101\"\u0011\u00106\u001a\u0002038G¢\u0006\u0006\u001a\u0004\b4\u00105\"\u0017\u0010;\u001a\u0002078G¢\u0006\f\u0012\u0004\b:\u0010+\u001a\u0004\b8\u00109\"\u0011\u0010?\u001a\u00020<8G¢\u0006\u0006\u001a\u0004\b=\u0010>¨\u0006@"}, d2 = {"T", "Lkotlin/Function0;", "Landroidx/compose/runtime/DisallowComposableCalls;", "calculation", "remember", "(Lr7/a;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "", "key1", "(Ljava/lang/Object;Lr7/a;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "key2", "(Ljava/lang/Object;Ljava/lang/Object;Lr7/a;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "key3", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lr7/a;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "", UserMetadata.KEYDATA_FILENAME, "([Ljava/lang/Object;Lr7/a;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "Landroidx/compose/runtime/Composable;", "block", "key", "([Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)Ljava/lang/Object;", "Lx6/t0;", "content", "ReusableContent", "(Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "", "active", "ReusableContentHost", "(ZLr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/Applier;", "E", "factory", "Lkotlin/Function1;", "Landroidx/compose/runtime/Updater;", "Lx6/n;", "update", "ComposeNode", "(Lr7/a;Lr7/l;Landroidx/compose/runtime/Composer;I)V", "ReusableComposeNode", "(Lr7/a;Lr7/l;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/SkippableUpdater;", "skippableUpdate", "(Lr7/a;Lr7/l;Lr7/q;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "invalidApplier", "()V", "Landroidx/compose/runtime/CompositionContext;", "rememberCompositionContext", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/CompositionContext;", "Landroidx/compose/runtime/Composer;", "getCurrentComposer", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/Composer;", "currentComposer", "Landroidx/compose/runtime/RecomposeScope;", "getCurrentRecomposeScope", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/RecomposeScope;", "currentRecomposeScope", "Landroidx/compose/runtime/CompositionLocalContext;", "getCurrentCompositionLocalContext", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/CompositionLocalContext;", "getCurrentCompositionLocalContext$annotations", "currentCompositionLocalContext", "", "getCurrentCompositeKeyHash", "(Landroidx/compose/runtime/Composer;I)I", "currentCompositeKeyHash", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ComposablesKt {
    public static final <T, E extends Applier<?>> void ComposeNode(r7.a<? extends T> aVar, r7.l<? super Updater<T>, t0> lVar, Composer composer, int i10) {
        composer.getApplier();
        p.h();
        throw null;
    }

    public static final <T, E extends Applier<?>> void ReusableComposeNode(r7.a<? extends T> aVar, r7.l<? super Updater<T>, t0> lVar, Composer composer, int i10) {
        composer.getApplier();
        p.h();
        throw null;
    }

    public static final void ReusableContent(Object obj, r7.p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        composer.startReusableGroup(ComposerKt.reuseKey, obj);
        pVar.invoke(composer, Integer.valueOf((i10 >> 3) & 14));
        composer.endReusableGroup();
    }

    public static final void ReusableContentHost(boolean z, r7.p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        composer.startReusableGroup(ComposerKt.reuseKey, Boolean.valueOf(z));
        boolean zChanged = composer.changed(z);
        composer.startReplaceGroup(-869707859);
        if (z) {
            pVar.invoke(composer, Integer.valueOf((i10 >> 3) & 14));
        } else {
            composer.deactivateToEndGroup(zChanged);
        }
        composer.endReplaceGroup();
        composer.endReusableGroup();
    }

    public static final Composer getCurrentComposer(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-554250212, i10, -1, "androidx.compose.runtime.<get-currentComposer> (Composables.kt:187)");
        }
        throw new x1("Implemented as an intrinsic");
    }

    public static final int getCurrentCompositeKeyHash(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(524444915, i10, -1, "androidx.compose.runtime.<get-currentCompositeKeyHash> (Composables.kt:228)");
        }
        int compoundKeyHash = composer.getCompoundKeyHash();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return compoundKeyHash;
    }

    public static final CompositionLocalContext getCurrentCompositionLocalContext(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-43352356, i10, -1, "androidx.compose.runtime.<get-currentCompositionLocalContext> (Composables.kt:211)");
        }
        CompositionLocalContext compositionLocalContext = new CompositionLocalContext(composer.buildContext().getCompositionLocalScope$runtime_release());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return compositionLocalContext;
    }

    public static /* synthetic */ void getCurrentCompositionLocalContext$annotations() {
    }

    public static final RecomposeScope getCurrentRecomposeScope(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(394957799, i10, -1, "androidx.compose.runtime.<get-currentRecomposeScope> (Composables.kt:196)");
        }
        RecomposeScope recomposeScope = composer.getRecomposeScope();
        if (recomposeScope == null) {
            throw new IllegalStateException("no recompose scope found");
        }
        composer.recordUsed(recomposeScope);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return recomposeScope;
    }

    public static final void invalidApplier() {
        throw new IllegalStateException("Invalid applier");
    }

    public static final <T> T key(Object[] objArr, r7.p<? super Composer, ? super Integer, ? extends T> pVar, Composer composer, int i10) {
        return (T) pVar.invoke(composer, Integer.valueOf((i10 >> 3) & 14));
    }

    public static final <T> T remember(Object obj, r7.a<? extends T> aVar, Composer composer, int i10) {
        boolean zChanged = composer.changed(obj);
        T t2 = (T) composer.rememberedValue();
        if (!zChanged && t2 != Composer.INSTANCE.getEmpty()) {
            return t2;
        }
        T t10 = (T) aVar.invoke();
        composer.updateRememberedValue(t10);
        return t10;
    }

    public static final CompositionContext rememberCompositionContext(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1165786124, i10, -1, "androidx.compose.runtime.rememberCompositionContext (Composables.kt:484)");
        }
        CompositionContext compositionContextBuildContext = composer.buildContext();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return compositionContextBuildContext;
    }

    public static final <T, E extends Applier<?>> void ComposeNode(r7.a<? extends T> aVar, r7.l<? super Updater<T>, t0> lVar, r7.p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        composer.getApplier();
        p.h();
        throw null;
    }

    public static final <T, E extends Applier<?>> void ReusableComposeNode(r7.a<? extends T> aVar, r7.l<? super Updater<T>, t0> lVar, r7.p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        composer.getApplier();
        p.h();
        throw null;
    }

    public static final <T, E extends Applier<?>> void ComposeNode(r7.a<? extends T> aVar, r7.l<? super Updater<T>, t0> lVar, q<? super SkippableUpdater<T>, ? super Composer, ? super Integer, t0> qVar, r7.p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        composer.getApplier();
        p.h();
        throw null;
    }

    public static final <T, E extends Applier<?>> void ReusableComposeNode(r7.a<? extends T> aVar, r7.l<? super Updater<T>, t0> lVar, q<? super SkippableUpdater<T>, ? super Composer, ? super Integer, t0> qVar, r7.p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        composer.getApplier();
        p.h();
        throw null;
    }

    public static final <T> T remember(Object obj, Object obj2, r7.a<? extends T> aVar, Composer composer, int i10) {
        boolean zChanged = composer.changed(obj) | composer.changed(obj2);
        T t2 = (T) composer.rememberedValue();
        if (!zChanged && t2 != Composer.INSTANCE.getEmpty()) {
            return t2;
        }
        T t10 = (T) aVar.invoke();
        composer.updateRememberedValue(t10);
        return t10;
    }

    public static final <T> T remember(Object obj, Object obj2, Object obj3, r7.a<? extends T> aVar, Composer composer, int i10) {
        boolean zChanged = composer.changed(obj) | composer.changed(obj2) | composer.changed(obj3);
        T t2 = (T) composer.rememberedValue();
        if (!zChanged && t2 != Composer.INSTANCE.getEmpty()) {
            return t2;
        }
        T t10 = (T) aVar.invoke();
        composer.updateRememberedValue(t10);
        return t10;
    }

    public static final <T> T remember(Object[] objArr, r7.a<? extends T> aVar, Composer composer, int i10) {
        boolean zChanged = false;
        for (Object obj : objArr) {
            zChanged |= composer.changed(obj);
        }
        T t2 = (T) composer.rememberedValue();
        if (!zChanged && t2 != Composer.INSTANCE.getEmpty()) {
            return t2;
        }
        T t10 = (T) aVar.invoke();
        composer.updateRememberedValue(t10);
        return t10;
    }

    public static final <T> T remember(r7.a<? extends T> aVar, Composer composer, int i10) {
        T t2 = (T) composer.rememberedValue();
        if (t2 != Composer.INSTANCE.getEmpty()) {
            return t2;
        }
        T t10 = (T) aVar.invoke();
        composer.updateRememberedValue(t10);
        return t10;
    }
}

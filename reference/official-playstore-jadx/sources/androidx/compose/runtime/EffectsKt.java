package androidx.compose.runtime;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.Arrays;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.u1;
import ka.v1;
import ka.x1;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u001d\u0010\u0003\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a(\u0010\t\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\t\u0010\n\u001a2\u0010\t\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\t\u0010\r\u001a<\u0010\t\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\t\u0010\u000f\u001aF\u0010\t\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\t\u0010\u0011\u001a@\u0010\t\u001a\u00020\u00012\u0016\u0010\u0013\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\u0012\"\u0004\u0018\u00010\u000b2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\t\u0010\u0014\u001a8\u0010\u0019\u001a\u00020\u00012'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0015¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001aB\u0010\u0019\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u000b2'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0015¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\u0019\u0010\u001b\u001aL\u0010\u0019\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0015¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\u0019\u0010\u001c\u001aV\u0010\u0019\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000b2'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0015¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\u0019\u0010\u001d\u001aP\u0010\u0019\u001a\u00020\u00012\u0016\u0010\u0013\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\u0012\"\u0004\u0018\u00010\u000b2'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0015¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\u0019\u0010\u001e\u001a\u001f\u0010#\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020!H\u0001¢\u0006\u0004\b#\u0010$\u001a%\u0010'\u001a\u00020\u00162\u0013\b\u0006\u0010&\u001a\r\u0012\u0004\u0012\u00020\u001f0\u0000¢\u0006\u0002\b%H\u0087\b¢\u0006\u0004\b'\u0010(\"\u0014\u0010)\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*\"\u0014\u0010,\u001a\u00020+8\u0002X\u0082T¢\u0006\u0006\n\u0004\b,\u0010-\"\u0014\u0010.\u001a\u00020+8\u0002X\u0082T¢\u0006\u0006\n\u0004\b.\u0010-¨\u0006/"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "effect", "SideEffect", "(Lr7/a;Landroidx/compose/runtime/Composer;I)V", "Lkotlin/Function1;", "Landroidx/compose/runtime/DisposableEffectScope;", "Landroidx/compose/runtime/DisposableEffectResult;", "Lx6/n;", "DisposableEffect", "(Lr7/l;Landroidx/compose/runtime/Composer;I)V", "", "key1", "(Ljava/lang/Object;Lr7/l;Landroidx/compose/runtime/Composer;I)V", "key2", "(Ljava/lang/Object;Ljava/lang/Object;Lr7/l;Landroidx/compose/runtime/Composer;I)V", "key3", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lr7/l;Landroidx/compose/runtime/Composer;I)V", "", UserMetadata.KEYDATA_FILENAME, "([Ljava/lang/Object;Lr7/l;Landroidx/compose/runtime/Composer;I)V", "Lkotlin/Function2;", "Lka/k0;", "Ld7/d;", "block", "LaunchedEffect", "(Lr7/p;Landroidx/compose/runtime/Composer;I)V", "(Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "(Ljava/lang/Object;Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "([Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Ld7/j;", "coroutineContext", "Landroidx/compose/runtime/Composer;", "composer", "createCompositionCoroutineScope", "(Ld7/j;Landroidx/compose/runtime/Composer;)Lka/k0;", "Landroidx/compose/runtime/DisallowComposableCalls;", "getContext", "rememberCoroutineScope", "(Lr7/a;Landroidx/compose/runtime/Composer;II)Lka/k0;", "InternalDisposableEffectScope", "Landroidx/compose/runtime/DisposableEffectScope;", "", "DisposableEffectNoParamError", "Ljava/lang/String;", "LaunchedEffectNoParamError", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EffectsKt {
    private static final String DisposableEffectNoParamError = "DisposableEffect must provide one or more 'key' parameters that define the identity of the DisposableEffect and determine when its previous effect should be disposed and a new effect started for the new key.";
    private static final DisposableEffectScope InternalDisposableEffectScope = new DisposableEffectScope();
    private static final String LaunchedEffectNoParamError = "LaunchedEffect must provide one or more 'key' parameters that define the identity of the LaunchedEffect and determine when its previous effect coroutine should be cancelled and a new effect launched for the new key.";

    /* JADX INFO: renamed from: androidx.compose.runtime.EffectsKt$LaunchedEffect$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<k0, d7.d<? super t0>, Object> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super k0, ? super d7.d<? super t0>, ? extends Object> pVar, int i10) {
            super(2);
            this.$block = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            EffectsKt.LaunchedEffect(this.$block, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.EffectsKt$rememberCoroutineScope$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C05891 extends r implements r7.a<d7.k> {
        public static final C05891 INSTANCE = new C05891();

        public C05891() {
            super(0);
        }

        @Override // r7.a
        public final d7.k invoke() {
            return d7.k.f14688i;
        }
    }

    @x6.e
    public static final void DisposableEffect(r7.l<? super DisposableEffectScope, ? extends DisposableEffectResult> lVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-904483903, i10, -1, "androidx.compose.runtime.DisposableEffect (Effects.kt:119)");
        }
        throw new IllegalStateException(DisposableEffectNoParamError);
    }

    @x6.e
    public static final void LaunchedEffect(p<? super k0, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-805415771);
        if ((i10 & 1) != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-805415771, i10, -1, "androidx.compose.runtime.LaunchedEffect (Effects.kt:318)");
            }
            throw new IllegalStateException(LaunchedEffectNoParamError);
        }
        composerStartRestartGroup.skipToGroupEnd();
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass1(pVar, i10));
        }
    }

    public static final void SideEffect(r7.a<t0> aVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1288466761, i10, -1, "androidx.compose.runtime.SideEffect (Effects.kt:48)");
        }
        composer.recordSideEffect(aVar);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static final k0 createCompositionCoroutineScope(d7.j jVar, Composer composer) {
        u1 u1Var = u1.f19642i;
        if (jVar.get(u1Var) == null) {
            d7.j applyCoroutineContext = composer.getApplyCoroutineContext();
            return l0.a(applyCoroutineContext.plus(new x1((v1) applyCoroutineContext.get(u1Var))).plus(jVar));
        }
        x1 x1VarC = m0.c();
        x1VarC.i(new IllegalArgumentException("CoroutineContext supplied to rememberCoroutineScope may not include a parent job"));
        return l0.a(x1VarC);
    }

    public static final k0 rememberCoroutineScope(r7.a<? extends d7.j> aVar, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            aVar = C05891.INSTANCE;
        }
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new CompositionScopedCoroutineScopeCanceller(createCompositionCoroutineScope((d7.j) aVar.invoke(), composer));
            composer.updateRememberedValue(objRememberedValue);
        }
        return ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
    }

    public static final void DisposableEffect(Object obj, r7.l<? super DisposableEffectScope, ? extends DisposableEffectResult> lVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1371986847, i10, -1, "androidx.compose.runtime.DisposableEffect (Effects.kt:155)");
        }
        boolean zChanged = composer.changed(obj);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new DisposableEffectImpl(lVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static final void LaunchedEffect(Object obj, p<? super k0, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1179185413, i10, -1, "androidx.compose.runtime.LaunchedEffect (Effects.kt:337)");
        }
        d7.j applyCoroutineContext = composer.getApplyCoroutineContext();
        boolean zChanged = composer.changed(obj);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new LaunchedEffectImpl(applyCoroutineContext, pVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static final void DisposableEffect(Object obj, Object obj2, r7.l<? super DisposableEffectScope, ? extends DisposableEffectResult> lVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1429097729, i10, -1, "androidx.compose.runtime.DisposableEffect (Effects.kt:195)");
        }
        boolean zChanged = composer.changed(obj) | composer.changed(obj2);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new DisposableEffectImpl(lVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static final void LaunchedEffect(Object obj, Object obj2, p<? super k0, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(590241125, i10, -1, "androidx.compose.runtime.LaunchedEffect (Effects.kt:360)");
        }
        d7.j applyCoroutineContext = composer.getApplyCoroutineContext();
        boolean zChanged = composer.changed(obj) | composer.changed(obj2);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new LaunchedEffectImpl(applyCoroutineContext, pVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static final void DisposableEffect(Object obj, Object obj2, Object obj3, r7.l<? super DisposableEffectScope, ? extends DisposableEffectResult> lVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1239538271, i10, -1, "androidx.compose.runtime.DisposableEffect (Effects.kt:236)");
        }
        boolean zChanged = composer.changed(obj) | composer.changed(obj2) | composer.changed(obj3);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new DisposableEffectImpl(lVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static final void LaunchedEffect(Object obj, Object obj2, Object obj3, p<? super k0, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-54093371, i10, -1, "androidx.compose.runtime.LaunchedEffect (Effects.kt:384)");
        }
        d7.j applyCoroutineContext = composer.getApplyCoroutineContext();
        boolean zChanged = composer.changed(obj) | composer.changed(obj2) | composer.changed(obj3);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new LaunchedEffectImpl(applyCoroutineContext, pVar);
            composer.updateRememberedValue(objRememberedValue);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static final void DisposableEffect(Object[] objArr, r7.l<? super DisposableEffectScope, ? extends DisposableEffectResult> lVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1307627122, i10, -1, "androidx.compose.runtime.DisposableEffect (Effects.kt:276)");
        }
        boolean zChanged = false;
        for (Object obj : Arrays.copyOf(objArr, objArr.length)) {
            zChanged |= composer.changed(obj);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            composer.updateRememberedValue(new DisposableEffectImpl(lVar));
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static final void LaunchedEffect(Object[] objArr, p<? super k0, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-139560008, i10, -1, "androidx.compose.runtime.LaunchedEffect (Effects.kt:417)");
        }
        d7.j applyCoroutineContext = composer.getApplyCoroutineContext();
        boolean zChanged = false;
        for (Object obj : Arrays.copyOf(objArr, objArr.length)) {
            zChanged |= composer.changed(obj);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            composer.updateRememberedValue(new LaunchedEffectImpl(applyCoroutineContext, pVar));
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}

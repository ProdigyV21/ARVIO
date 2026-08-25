package androidx.compose.ui.viewinterop;

import a0.c;
import android.content.Context;
import android.view.View;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.lifecycle.y;
import androidx.media3.exoplayer.RendererCapabilities;
import b2.g;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aM\u0010\t\u001a\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0002H\u0007¢\u0006\u0004\b\t\u0010\n\u001a{\u0010\t\u001a\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00022\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u00022\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0002H\u0007¢\u0006\u0004\b\t\u0010\r\u001a3\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\u0002H\u0003¢\u0006\u0004\b\u0010\u0010\u0011\u001a^\u0010!\u001a\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00020\u000f0\u00122\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a#\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\"\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u000fH\u0002¢\u0006\u0004\b#\u0010$\"(\u0010&\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0002¢\u0006\u0002\b%8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006*"}, d2 = {"Landroid/view/View;", "T", "Lkotlin/Function1;", "Landroid/content/Context;", "factory", "Landroidx/compose/ui/Modifier;", "modifier", "Lx6/t0;", "update", "AndroidView", "(Lr7/l;Landroidx/compose/ui/Modifier;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "onReset", "onRelease", "(Lr7/l;Landroidx/compose/ui/Modifier;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "Lkotlin/Function0;", "Landroidx/compose/ui/node/LayoutNode;", "createAndroidViewNodeFactory", "(Lr7/l;Landroidx/compose/runtime/Composer;I)Lr7/a;", "Landroidx/compose/runtime/Updater;", "", "compositeKeyHash", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/lifecycle/y;", "lifecycleOwner", "Lb2/g;", "savedStateRegistryOwner", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/runtime/CompositionLocalMap;", "compositionLocalMap", "updateViewHolderParams-6NefGtU", "(Landroidx/compose/runtime/Composer;Landroidx/compose/ui/Modifier;ILandroidx/compose/ui/unit/Density;Landroidx/lifecycle/y;Lb2/g;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/runtime/CompositionLocalMap;)V", "updateViewHolderParams", "Landroidx/compose/ui/viewinterop/ViewFactoryHolder;", "requireViewFactoryHolder", "(Landroidx/compose/ui/node/LayoutNode;)Landroidx/compose/ui/viewinterop/ViewFactoryHolder;", "Lx6/n;", "NoOpUpdate", "Lr7/l;", "getNoOpUpdate", "()Lr7/l;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidView_androidKt {
    private static final l<View, t0> NoOpUpdate = AndroidView_androidKt$NoOpUpdate$1.INSTANCE;

    /* JADX INFO: renamed from: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ l<Context, T> $factory;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<T, t0> $update;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super Context, ? extends T> lVar, Modifier modifier, l<? super T, t0> lVar2, int i10, int i11) {
            super(2);
            this.$factory = lVar;
            this.$modifier = modifier;
            this.$update = lVar2;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidView_androidKt.AndroidView(this.$factory, this.$modifier, this.$update, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ l<Context, T> $factory;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<T, t0> $onRelease;
        final /* synthetic */ l<T, t0> $onReset;
        final /* synthetic */ l<T, t0> $update;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(l<? super Context, ? extends T> lVar, Modifier modifier, l<? super T, t0> lVar2, l<? super T, t0> lVar3, l<? super T, t0> lVar4, int i10, int i11) {
            super(2);
            this.$factory = lVar;
            this.$modifier = modifier;
            this.$onReset = lVar2;
            this.$onRelease = lVar3;
            this.$update = lVar4;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidView_androidKt.AndroidView(this.$factory, this.$modifier, this.$onReset, this.$onRelease, this.$update, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.viewinterop.AndroidView_androidKt$createAndroidViewNodeFactory$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/node/LayoutNode;", "T", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06271 extends r implements r7.a<LayoutNode> {
        final /* synthetic */ int $compositeKeyHash;
        final /* synthetic */ Context $context;
        final /* synthetic */ l<Context, T> $factory;
        final /* synthetic */ View $ownerView;
        final /* synthetic */ CompositionContext $parentReference;
        final /* synthetic */ SaveableStateRegistry $stateRegistry;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C06271(Context context, l<? super Context, ? extends T> lVar, CompositionContext compositionContext, SaveableStateRegistry saveableStateRegistry, int i10, View view) {
            super(0);
            this.$context = context;
            this.$factory = lVar;
            this.$parentReference = compositionContext;
            this.$stateRegistry = saveableStateRegistry;
            this.$compositeKeyHash = i10;
            this.$ownerView = view;
        }

        @Override // r7.a
        public final LayoutNode invoke() {
            return new ViewFactoryHolder(this.$context, this.$factory, this.$parentReference, this.$stateRegistry, this.$compositeKeyHash, (Owner) this.$ownerView).getLayoutNode();
        }
    }

    public static final <T extends View> void AndroidView(l<? super Context, ? extends T> lVar, Modifier modifier, l<? super T, t0> lVar2, Composer composer, int i10, int i11) {
        int i12;
        Modifier modifier2;
        l<? super T, t0> lVar3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1783766393);
        if ((i11 & 1) != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 14) == 0) {
            i12 = (composerStartRestartGroup.changedInstance(lVar) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i13 = i11 & 2;
        if (i13 != 0) {
            i12 |= 48;
        } else if ((i10 & 112) == 0) {
            i12 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        int i14 = i11 & 4;
        if (i14 != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & 896) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(lVar2) ? 256 : 128;
        }
        if ((i12 & 731) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
            lVar3 = lVar2;
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            Modifier modifier3 = modifier;
            if (i14 != 0) {
                lVar2 = NoOpUpdate;
            }
            l<? super T, t0> lVar4 = lVar2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1783766393, i12, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:106)");
            }
            AndroidView(lVar, modifier3, null, NoOpUpdate, lVar4, composerStartRestartGroup, (i12 & 14) | 3072 | (i12 & 112) | (57344 & (i12 << 6)), 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            lVar3 = lVar4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass1(lVar, modifier2, lVar3, i10, i11));
        }
    }

    private static final <T extends View> r7.a<LayoutNode> createAndroidViewNodeFactory(l<? super Context, ? extends T> lVar, Composer composer, int i10) {
        composer.startReplaceableGroup(2030558801);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2030558801, i10, -1, "androidx.compose.ui.viewinterop.createAndroidViewNodeFactory (AndroidView.android.kt:265)");
        }
        C06271 c06271 = new C06271((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext()), lVar, ComposablesKt.rememberCompositionContext(composer, 0), (SaveableStateRegistry) composer.consume(SaveableStateRegistryKt.getLocalSaveableStateRegistry()), ComposablesKt.getCurrentCompositeKeyHash(composer, 0), (View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return c06271;
    }

    public static final l<View, t0> getNoOpUpdate() {
        return NoOpUpdate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends View> ViewFactoryHolder<T> requireViewFactoryHolder(LayoutNode layoutNode) {
        AndroidViewHolder interopViewFactoryHolder = layoutNode.getInteropViewFactoryHolder();
        if (interopViewFactoryHolder != null) {
            return (ViewFactoryHolder) interopViewFactoryHolder;
        }
        throw new IllegalStateException("Required value was null.");
    }

    /* JADX INFO: renamed from: updateViewHolderParams-6NefGtU, reason: not valid java name */
    private static final <T extends View> void m5926updateViewHolderParams6NefGtU(Composer composer, Modifier modifier, int i10, Density density, y yVar, g gVar, LayoutDirection layoutDirection, CompositionLocalMap compositionLocalMap) {
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        Updater.m2998setimpl(composer, compositionLocalMap, companion.getSetResolvedCompositionLocals());
        Updater.m2998setimpl(composer, modifier, AndroidView_androidKt$updateViewHolderParams$1.INSTANCE);
        Updater.m2998setimpl(composer, density, AndroidView_androidKt$updateViewHolderParams$2.INSTANCE);
        Updater.m2998setimpl(composer, yVar, AndroidView_androidKt$updateViewHolderParams$3.INSTANCE);
        Updater.m2998setimpl(composer, gVar, AndroidView_androidKt$updateViewHolderParams$4.INSTANCE);
        Updater.m2998setimpl(composer, layoutDirection, AndroidView_androidKt$updateViewHolderParams$5.INSTANCE);
        p<ComposeUiNode, Integer, t0> setCompositeKeyHash = companion.getSetCompositeKeyHash();
        if (composer.getInserting() || !kotlin.jvm.internal.p.a(composer.rememberedValue(), Integer.valueOf(i10))) {
            c.w(i10, composer, i10, setCompositeKeyHash);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T extends android.view.View> void AndroidView(r7.l<? super android.content.Context, ? extends T> r21, androidx.compose.ui.Modifier r22, r7.l<? super T, x6.t0> r23, r7.l<? super T, x6.t0> r24, r7.l<? super T, x6.t0> r25, androidx.compose.runtime.Composer r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.viewinterop.AndroidView_androidKt.AndroidView(r7.l, androidx.compose.ui.Modifier, r7.l, r7.l, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }
}

package androidx.compose.ui.window;

import a0.c;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.g;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.LayoutDirection;
import d7.d;
import f7.e;
import f7.j;
import java.util.ArrayList;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aU\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001aF\u0010\r\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u000e2\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\tH\u0007¢\u0006\u0004\b\r\u0010\u0010\u001a*\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00112\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\tH\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u001a-\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00152\u0013\b\b\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\tH\u0083\b¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0013\u0010\u001b\u001a\u00020\u001a*\u00020\u0019H\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0013\u0010\u001f\u001a\u00020\u001e*\u00020\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 \u001a#\u0010#\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u00192\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0004\b#\u0010$\" \u0010&\u001a\b\u0012\u0004\u0012\u00020\u00110%8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006+²\u0006\u0017\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\t8\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/Alignment;", "alignment", "Landroidx/compose/ui/unit/IntOffset;", "offset", "Lkotlin/Function0;", "Lx6/t0;", "onDismissRequest", "Landroidx/compose/ui/window/PopupProperties;", "properties", "Landroidx/compose/runtime/Composable;", "content", "Popup-K5zGePQ", "(Landroidx/compose/ui/Alignment;JLr7/a;Landroidx/compose/ui/window/PopupProperties;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Popup", "Landroidx/compose/ui/window/PopupPositionProvider;", "popupPositionProvider", "(Landroidx/compose/ui/window/PopupPositionProvider;Lr7/a;Landroidx/compose/ui/window/PopupProperties;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "", "tag", "PopupTestTag", "(Ljava/lang/String;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/Modifier;", "modifier", "SimpleStack", "(Landroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroid/view/View;", "", "isFlagSecureEnabled", "(Landroid/view/View;)Z", "Landroid/graphics/Rect;", "Landroidx/compose/ui/unit/IntRect;", "toIntBounds", "(Landroid/graphics/Rect;)Landroidx/compose/ui/unit/IntRect;", "view", "testTag", "isPopupLayout", "(Landroid/view/View;Ljava/lang/String;)Z", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalPopupTestTag", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalPopupTestTag", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "currentContent", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidPopup_androidKt {
    private static final ProvidableCompositionLocal<String> LocalPopupTestTag = CompositionLocalKt.compositionLocalOf$default(null, AndroidPopup_androidKt$LocalPopupTestTag$1.INSTANCE, 1, null);

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Alignment $alignment;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ long $offset;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ PopupProperties $properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Alignment alignment, long j10, r7.a<t0> aVar, PopupProperties popupProperties, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$alignment = alignment;
            this.$offset = j10;
            this.$onDismissRequest = aVar;
            this.$properties = popupProperties;
            this.$content = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidPopup_androidKt.m5932PopupK5zGePQ(this.$alignment, this.$offset, this.$onDismissRequest, this.$properties, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ LayoutDirection $layoutDirection;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ PopupLayout $popupLayout;
        final /* synthetic */ PopupProperties $properties;
        final /* synthetic */ String $testTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(PopupLayout popupLayout, r7.a<t0> aVar, PopupProperties popupProperties, String str, LayoutDirection layoutDirection) {
            super(1);
            this.$popupLayout = popupLayout;
            this.$onDismissRequest = aVar;
            this.$properties = popupProperties;
            this.$testTag = str;
            this.$layoutDirection = layoutDirection;
        }

        @Override // r7.l
        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
            this.$popupLayout.show();
            this.$popupLayout.updateParameters(this.$onDismissRequest, this.$properties, this.$testTag, this.$layoutDirection);
            final PopupLayout popupLayout = this.$popupLayout;
            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$2$invoke$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    popupLayout.disposeComposition();
                    popupLayout.dismiss();
                }
            };
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements r7.a<t0> {
        final /* synthetic */ LayoutDirection $layoutDirection;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ PopupLayout $popupLayout;
        final /* synthetic */ PopupProperties $properties;
        final /* synthetic */ String $testTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(PopupLayout popupLayout, r7.a<t0> aVar, PopupProperties popupProperties, String str, LayoutDirection layoutDirection) {
            super(0);
            this.$popupLayout = popupLayout;
            this.$onDismissRequest = aVar;
            this.$properties = popupProperties;
            this.$testTag = str;
            this.$layoutDirection = layoutDirection;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m5933invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m5933invoke() {
            this.$popupLayout.updateParameters(this.$onDismissRequest, this.$properties, this.$testTag, this.$layoutDirection);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ PopupLayout $popupLayout;
        final /* synthetic */ PopupPositionProvider $popupPositionProvider;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(PopupLayout popupLayout, PopupPositionProvider popupPositionProvider) {
            super(1);
            this.$popupLayout = popupLayout;
            this.$popupPositionProvider = popupPositionProvider;
        }

        @Override // r7.l
        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
            this.$popupLayout.setPositionProvider(this.$popupPositionProvider);
            this.$popupLayout.updatePosition();
            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$4$invoke$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                }
            };
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.ui.window.AndroidPopup_androidKt$Popup$5", f = "AndroidPopup.android.kt", l = {303}, m = "invokeSuspend")
    public static final class AnonymousClass5 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ PopupLayout $popupLayout;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$5$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<Long, t0> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
                super(1);
            }

            public final void invoke(long j10) {
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke(((Number) obj).longValue());
                return t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(PopupLayout popupLayout, d<? super AnonymousClass5> dVar) {
            super(2, dVar);
            this.$popupLayout = popupLayout;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.$popupLayout, dVar);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0031 -> B:14:0x0034). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r4) {
            /*
                r3 = this;
                int r0 = r3.label
                r1 = 1
                if (r0 == 0) goto L17
                if (r0 != r1) goto Lf
                java.lang.Object r0 = r3.L$0
                ka.k0 r0 = (ka.k0) r0
                k2.c.G(r4)
                goto L34
            Lf:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r0)
                throw r4
            L17:
                k2.c.G(r4)
                java.lang.Object r4 = r3.L$0
                ka.k0 r4 = (ka.k0) r4
                r0 = r4
            L1f:
                boolean r4 = ka.l0.d(r0)
                if (r4 == 0) goto L3a
                androidx.compose.ui.window.AndroidPopup_androidKt$Popup$5$1 r4 = androidx.compose.ui.window.AndroidPopup_androidKt.AnonymousClass5.AnonymousClass1.INSTANCE
                r3.L$0 = r0
                r3.label = r1
                java.lang.Object r4 = androidx.compose.ui.platform.InfiniteAnimationPolicyKt.withInfiniteAnimationFrameNanos(r4, r3)
                e7.a r2 = e7.a.f15033i
                if (r4 != r2) goto L34
                return r2
            L34:
                androidx.compose.ui.window.PopupLayout r4 = r3.$popupLayout
                r4.pollForLocationOnScreenChange()
                goto L1f
            L3a:
                x6.t0 r4 = x6.t0.f22605a
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.window.AndroidPopup_androidKt.AnonymousClass5.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass5) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$7, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/layout/LayoutCoordinates;", "childCoordinates", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass7 extends r implements l<LayoutCoordinates, t0> {
        final /* synthetic */ PopupLayout $popupLayout;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(PopupLayout popupLayout) {
            super(1);
            this.$popupLayout = popupLayout;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((LayoutCoordinates) obj);
            return t0.f22605a;
        }

        public final void invoke(LayoutCoordinates layoutCoordinates) {
            this.$popupLayout.updateParentLayoutCoordinates(layoutCoordinates.getParentLayoutCoordinates());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$9, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass9 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ PopupPositionProvider $popupPositionProvider;
        final /* synthetic */ PopupProperties $properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass9(PopupPositionProvider popupPositionProvider, r7.a<t0> aVar, PopupProperties popupProperties, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$popupPositionProvider = popupPositionProvider;
            this.$onDismissRequest = aVar;
            this.$properties = popupProperties;
            this.$content = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidPopup_androidKt.Popup(this.$popupPositionProvider, this.$onDismissRequest, this.$properties, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$PopupTestTag$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06301 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ String $tag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C06301(String str, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$tag = str;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidPopup_androidKt.PopupTestTag(this.$tag, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$SimpleStack$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06311 implements MeasurePolicy {
        public static final C06311 INSTANCE = new C06311();

        /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$SimpleStack$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C01241 extends r implements l<Placeable.PlacementScope, t0> {
            public static final C01241 INSTANCE = new C01241();

            public C01241() {
                super(1);
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return t0.f22605a;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$SimpleStack$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass2 extends r implements l<Placeable.PlacementScope, t0> {
            final /* synthetic */ Placeable $p;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(Placeable placeable) {
                super(1);
                this.$p = placeable;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return t0.f22605a;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                Placeable.PlacementScope.placeRelative$default(placementScope, this.$p, 0, 0, 0.0f, 4, null);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidPopup_androidKt$SimpleStack$1$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass3 extends r implements l<Placeable.PlacementScope, t0> {
            final /* synthetic */ List<Placeable> $placeables;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass3(List<? extends Placeable> list) {
                super(1);
                this.$placeables = list;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return t0.f22605a;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                int iW = t7.a.w(this.$placeables);
                if (iW < 0) {
                    return;
                }
                int i10 = 0;
                while (true) {
                    Placeable.PlacementScope placementScope2 = placementScope;
                    Placeable.PlacementScope.placeRelative$default(placementScope2, this.$placeables.get(i10), 0, 0, 0.0f, 4, null);
                    if (i10 == iW) {
                        return;
                    }
                    i10++;
                    placementScope = placementScope2;
                }
            }
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return g.a(this, intrinsicMeasureScope, list, i10);
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return g.b(this, intrinsicMeasureScope, list, i10);
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        /* JADX INFO: renamed from: measure-3p2s80s */
        public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
            int i10;
            int i11;
            int size = list.size();
            if (size == 0) {
                return MeasureScope.CC.q(measureScope, 0, 0, null, C01241.INSTANCE, 4, null);
            }
            int i12 = 0;
            if (size == 1) {
                Placeable placeableMo4631measureBRTryo0 = list.get(0).mo4631measureBRTryo0(j10);
                return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new AnonymousClass2(placeableMo4631measureBRTryo0), 4, null);
            }
            ArrayList arrayList = new ArrayList(list.size());
            int size2 = list.size();
            for (int i13 = 0; i13 < size2; i13++) {
                arrayList.add(list.get(i13).mo4631measureBRTryo0(j10));
            }
            int iW = t7.a.w(arrayList);
            if (iW >= 0) {
                int iMax = 0;
                int iMax2 = 0;
                while (true) {
                    Placeable placeable = (Placeable) arrayList.get(i12);
                    iMax = Math.max(iMax, placeable.getWidth());
                    iMax2 = Math.max(iMax2, placeable.getHeight());
                    if (i12 == iW) {
                        break;
                    }
                    i12++;
                }
                i10 = iMax;
                i11 = iMax2;
            } else {
                i10 = 0;
                i11 = 0;
            }
            return MeasureScope.CC.q(measureScope, i10, i11, null, new AnonymousClass3(arrayList), 4, null);
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return g.c(this, intrinsicMeasureScope, list, i10);
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return g.d(this, intrinsicMeasureScope, list, i10);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Popup(androidx.compose.ui.window.PopupPositionProvider r27, r7.a<x6.t0> r28, androidx.compose.ui.window.PopupProperties r29, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 551
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.window.AndroidPopup_androidKt.Popup(androidx.compose.ui.window.PopupPositionProvider, r7.a, androidx.compose.ui.window.PopupProperties, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p<Composer, Integer, t0> Popup$lambda$1(State<? extends p<? super Composer, ? super Integer, t0>> state) {
        return (p) state.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:92:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: Popup-K5zGePQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m5932PopupK5zGePQ(androidx.compose.ui.Alignment r23, long r24, r7.a<x6.t0> r26, androidx.compose.ui.window.PopupProperties r27, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, androidx.compose.runtime.Composer r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.window.AndroidPopup_androidKt.m5932PopupK5zGePQ(androidx.compose.ui.Alignment, long, r7.a, androidx.compose.ui.window.PopupProperties, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void PopupTestTag(String str, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-498879600);
        if ((i10 & 14) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 112) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i11 & 91) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-498879600, i11, -1, "androidx.compose.ui.window.PopupTestTag (AndroidPopup.android.kt:333)");
            }
            CompositionLocalKt.CompositionLocalProvider(LocalPopupTestTag.provides(str), pVar, composerStartRestartGroup, (i11 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C06301(str, pVar, i10));
        }
    }

    private static final void SimpleStack(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        composer.startReplaceableGroup(1406149896);
        C06311 c06311 = C06311.INSTANCE;
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier);
        int i11 = (((((i10 << 3) & 112) | ((i10 >> 3) & 14)) << 9) & 7168) | 6;
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
        Updater.m2998setimpl(composerM2991constructorimpl, c06311, companion.getSetMeasurePolicy());
        Updater.m2998setimpl(composerM2991constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
        p<ComposeUiNode, Integer, t0> setCompositeKeyHash = companion.getSetCompositeKeyHash();
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, setCompositeKeyHash);
        }
        c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        pVar.invoke(composer, Integer.valueOf((i11 >> 9) & 14));
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
    }

    public static final ProvidableCompositionLocal<String> getLocalPopupTestTag() {
        return LocalPopupTestTag;
    }

    public static final boolean isFlagSecureEnabled(View view) {
        ViewGroup.LayoutParams layoutParams = view.getRootView().getLayoutParams();
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (layoutParams2 == null || (layoutParams2.flags & 8192) == 0) ? false : true;
    }

    public static final boolean isPopupLayout(View view, String str) {
        if (view instanceof PopupLayout) {
            return str == null || str.equals(((PopupLayout) view).getTestTag());
        }
        return false;
    }

    public static /* synthetic */ boolean isPopupLayout$default(View view, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        return isPopupLayout(view, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRect toIntBounds(Rect rect) {
        return new IntRect(rect.left, rect.top, rect.right, rect.bottom);
    }
}

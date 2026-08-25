package androidx.compose.material3;

import android.view.View;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.media3.exoplayer.RendererCapabilities;
import f7.j;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aS\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\b¢\u0006\u0002\b\tH\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a-\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011H\u0003¢\u0006\u0004\b\u0013\u0010\u0014\u001a9\u0010\u0018\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00112\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a)\u0010\u001f\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 \u001a\u0013\u0010!\u001a\u00020\u001a*\u00020\rH\u0002¢\u0006\u0004\b!\u0010\"\u001a\u0015\u0010$\u001a\u00020\u001a*\u0004\u0018\u00010#H\u0002¢\u0006\u0004\b$\u0010%\"\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(¨\u0006,²\u0006\u0010\u0010)\u001a\u0004\u0018\u00010#8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010*\u001a\u00020\u001d8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010+\u001a\u00020\u001d8\n@\nX\u008a\u008e\u0002"}, d2 = {"", "expanded", "Lkotlin/Function1;", "Lx6/t0;", "onExpandedChange", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/material3/ExposedDropdownMenuBoxScope;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "ExposedDropdownMenuBox", "(ZLr7/l;Landroidx/compose/ui/Modifier;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "Landroid/view/View;", "view", "Landroidx/compose/ui/unit/Density;", "density", "Lkotlin/Function0;", "onKeyboardVisibilityChange", "SoftKeyboardListener", "(Landroid/view/View;Landroidx/compose/ui/unit/Density;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "", "expandedDescription", "collapsedDescription", "expandable", "(Landroidx/compose/ui/Modifier;ZLr7/a;Ljava/lang/String;Ljava/lang/String;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/geometry/Rect;", "windowBounds", "anchorBounds", "", "verticalMargin", "calculateMaxHeight", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;I)I", "getWindowBounds", "(Landroid/view/View;)Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getAnchorBounds", "(Landroidx/compose/ui/layout/LayoutCoordinates;)Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/Dp;", "ExposedDropdownMenuItemHorizontalPadding", "F", "anchorCoordinates", "anchorWidth", "menuMaxHeight", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExposedDropdownMenu_androidKt {
    private static final float ExposedDropdownMenuItemHorizontalPadding = Dp.m5678constructorimpl(16);

    /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenu_androidKt$ExposedDropdownMenuBox$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<ExposedDropdownMenuBoxScope, Composer, Integer, t0> $content;
        final /* synthetic */ boolean $expanded;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<Boolean, t0> $onExpandedChange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(boolean z, l<? super Boolean, t0> lVar, Modifier modifier, q<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
            super(2);
            this.$expanded = z;
            this.$onExpandedChange = lVar;
            this.$modifier = modifier;
            this.$content = qVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox(this.$expanded, this.$onExpandedChange, this.$modifier, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenu_androidKt$SoftKeyboardListener$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ Density $density;
        final /* synthetic */ r7.a<t0> $onKeyboardVisibilityChange;
        final /* synthetic */ View $view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(View view, Density density, r7.a<t0> aVar, int i10) {
            super(2);
            this.$view = view;
            this.$density = density;
            this.$onKeyboardVisibilityChange = aVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ExposedDropdownMenu_androidKt.SoftKeyboardListener(this.$view, this.$density, this.$onKeyboardVisibilityChange, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenu_androidKt$expandable$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.ExposedDropdownMenu_androidKt$expandable$1", f = "ExposedDropdownMenu.android.kt", l = {1041}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<PointerInputScope, d7.d<? super t0>, Object> {
        final /* synthetic */ r7.a<t0> $onExpandedChange;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenu_androidKt$expandable$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.ExposedDropdownMenu_androidKt$expandable$1$1", f = "ExposedDropdownMenu.android.kt", l = {1044, 1045}, m = "invokeSuspend")
        public static final class C01021 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
            final /* synthetic */ r7.a<t0> $onExpandedChange;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01021(r7.a<t0> aVar, d7.d<? super C01021> dVar) {
                super(2, dVar);
                this.$onExpandedChange = aVar;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                C01021 c01021 = new C01021(this.$onExpandedChange, dVar);
                c01021.L$0 = obj;
                return c01021;
            }

            @Override // r7.p
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
                return ((C01021) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
            }

            /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
            
                if (r11 == r3) goto L16;
             */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r11) {
                /*
                    r10 = this;
                    int r0 = r10.label
                    r1 = 2
                    r2 = 1
                    e7.a r3 = e7.a.f15033i
                    if (r0 == 0) goto L22
                    if (r0 == r2) goto L19
                    if (r0 != r1) goto L11
                    k2.c.G(r11)
                    r7 = r10
                    goto L4a
                L11:
                    java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r11.<init>(r0)
                    throw r11
                L19:
                    java.lang.Object r0 = r10.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                    k2.c.G(r11)
                    r7 = r10
                    goto L3c
                L22:
                    k2.c.G(r11)
                    java.lang.Object r11 = r10.L$0
                    r4 = r11
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                    androidx.compose.ui.input.pointer.PointerEventPass r6 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r10.L$0 = r4
                    r10.label = r2
                    r5 = 0
                    r8 = 1
                    r9 = 0
                    r7 = r10
                    java.lang.Object r11 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r4, r5, r6, r7, r8, r9)
                    if (r11 != r3) goto L3b
                    goto L49
                L3b:
                    r0 = r4
                L3c:
                    androidx.compose.ui.input.pointer.PointerEventPass r11 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r2 = 0
                    r7.L$0 = r2
                    r7.label = r1
                    java.lang.Object r11 = androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation(r0, r11, r10)
                    if (r11 != r3) goto L4a
                L49:
                    return r3
                L4a:
                    androidx.compose.ui.input.pointer.PointerInputChange r11 = (androidx.compose.ui.input.pointer.PointerInputChange) r11
                    if (r11 == 0) goto L53
                    r7.a<x6.t0> r11 = r7.$onExpandedChange
                    r11.invoke()
                L53:
                    x6.t0 r11 = x6.t0.f22605a
                    return r11
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ExposedDropdownMenu_androidKt.AnonymousClass1.C01021.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(r7.a<t0> aVar, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$onExpandedChange = aVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$onExpandedChange, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                C01021 c01021 = new C01021(this.$onExpandedChange, null);
                this.label = 1;
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, c01021, this);
                e7.a aVar = e7.a.f15033i;
                if (objAwaitEachGesture == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenu_androidKt$expandable$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05052 extends r implements l<SemanticsPropertyReceiver, t0> {
        final /* synthetic */ String $collapsedDescription;
        final /* synthetic */ boolean $expanded;
        final /* synthetic */ String $expandedDescription;
        final /* synthetic */ r7.a<t0> $onExpandedChange;

        /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenu_androidKt$expandable$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class AnonymousClass1 extends r implements r7.a<Boolean> {
            final /* synthetic */ r7.a<t0> $onExpandedChange;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(r7.a<t0> aVar) {
                super(0);
                this.$onExpandedChange = aVar;
            }

            @Override // r7.a
            public final Boolean invoke() {
                this.$onExpandedChange.invoke();
                return Boolean.TRUE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05052(boolean z, String str, String str2, r7.a<t0> aVar) {
            super(1);
            this.$expanded = z;
            this.$expandedDescription = str;
            this.$collapsedDescription = str2;
            this.$onExpandedChange = aVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, this.$expanded ? this.$expandedDescription : this.$collapsedDescription);
            SemanticsPropertiesKt.m5039setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5025getDropdownListo7Vup1c());
            SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new AnonymousClass1(this.$onExpandedChange), 1, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:122:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x022d  */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ExposedDropdownMenuBox(boolean r24, r7.l<? super java.lang.Boolean, x6.t0> r25, androidx.compose.ui.Modifier r26, r7.q<? super androidx.compose.material3.ExposedDropdownMenuBoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r27, androidx.compose.runtime.Composer r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 682
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox(boolean, r7.l, androidx.compose.ui.Modifier, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LayoutCoordinates ExposedDropdownMenuBox$lambda$2(MutableState<LayoutCoordinates> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SoftKeyboardListener(View view, Density density, r7.a<t0> aVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1319522472);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(view) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(density) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1319522472, i11, -1, "androidx.compose.material3.SoftKeyboardListener (ExposedDropdownMenu.android.kt:190)");
            }
            composerStartRestartGroup.startReplaceableGroup(-491766155);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(view) | ((i11 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new ExposedDropdownMenu_androidKt$SoftKeyboardListener$1$1(view, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.DisposableEffect(view, density, (l) objRememberedValue, composerStartRestartGroup, i11 & 126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(view, density, aVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int calculateMaxHeight(Rect rect, Rect rect2, int i10) {
        if (rect2 == null) {
            return 0;
        }
        float f10 = i10;
        float top = rect.getTop() + f10;
        float bottom = rect.getBottom() - f10;
        return Math.max((rect2.getTop() > rect.getBottom() || rect2.getBottom() < rect.getTop()) ? t7.a.M(bottom - top) : t7.a.M(Math.max(rect2.getTop() - top, bottom - rect2.getBottom())), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier expandable(Modifier modifier, boolean z, r7.a<t0> aVar, String str, String str2) {
        return SemanticsModifierKt.semantics$default(SuspendingPointerInputFilterKt.pointerInput(modifier, aVar, new AnonymousClass1(aVar, null)), false, new C05052(z, str, str2, aVar), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect getAnchorBounds(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates == null ? Rect.INSTANCE.getZero() : RectKt.m3276Recttz77jQw(LayoutCoordinatesKt.positionInWindow(layoutCoordinates), IntSizeKt.m5854toSizeozmzZPI(layoutCoordinates.mo4638getSizeYbymL2g()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect getWindowBounds(View view) {
        android.graphics.Rect rect = new android.graphics.Rect();
        view.getWindowVisibleDisplayFrame(rect);
        return RectHelper_androidKt.toComposeRect(rect);
    }
}

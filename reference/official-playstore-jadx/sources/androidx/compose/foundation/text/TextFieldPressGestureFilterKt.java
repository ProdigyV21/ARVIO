package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.media3.container.MdtaMetadataEntry;
import com.arflix.tv.data.repository.g;
import d7.d;
import d7.k;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a;\u0010\t\u001a\u00020\u0000*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0000¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "", "enabled", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/t0;", "onTap", "tapPressTextFieldModifier", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLr7/l;)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldPressGestureFilterKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ l<Offset, t0> $onTap;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2", f = "TextFieldPressGestureFilter.kt", l = {54}, m = "invokeSuspend")
        public static final class AnonymousClass2 extends j implements p<PointerInputScope, d<? super t0>, Object> {
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ State<l<Offset, t0>> $onTapState;
            final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
            final /* synthetic */ k0 $scope;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/PressGestureScope;Landroidx/compose/ui/geometry/Offset;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1", f = "TextFieldPressGestureFilter.kt", l = {MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "invokeSuspend")
            public static final class C00481 extends j implements q<PressGestureScope, Offset, d<? super t0>, Object> {
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
                final /* synthetic */ k0 $scope;
                /* synthetic */ long J$0;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                @e(c = "androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$1", f = "TextFieldPressGestureFilter.kt", l = {60, 64}, m = "invokeSuspend")
                public static final class C00491 extends j implements p<k0, d<? super t0>, Object> {
                    final /* synthetic */ MutableInteractionSource $interactionSource;
                    final /* synthetic */ long $it;
                    final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
                    Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00491(MutableState<PressInteraction.Press> mutableState, long j10, MutableInteractionSource mutableInteractionSource, d<? super C00491> dVar) {
                        super(2, dVar);
                        this.$pressedInteraction = mutableState;
                        this.$it = j10;
                        this.$interactionSource = mutableInteractionSource;
                    }

                    @Override // f7.a
                    public final d<t0> create(Object obj, d<?> dVar) {
                        return new C00491(this.$pressedInteraction, this.$it, this.$interactionSource, dVar);
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:23:0x0060, code lost:
                    
                        if (r8.emit(r0, r7) == r4) goto L24;
                     */
                    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
                    @Override // f7.a
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                        /*
                            r7 = this;
                            int r0 = r7.label
                            r1 = 0
                            r2 = 2
                            r3 = 1
                            e7.a r4 = e7.a.f15033i
                            if (r0 == 0) goto L25
                            if (r0 == r3) goto L1d
                            if (r0 != r2) goto L15
                            java.lang.Object r0 = r7.L$0
                            androidx.compose.foundation.interaction.PressInteraction$Press r0 = (androidx.compose.foundation.interaction.PressInteraction.Press) r0
                            k2.c.G(r8)
                            goto L63
                        L15:
                            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r8.<init>(r0)
                            throw r8
                        L1d:
                            java.lang.Object r0 = r7.L$0
                            androidx.compose.runtime.MutableState r0 = (androidx.compose.runtime.MutableState) r0
                            k2.c.G(r8)
                            goto L49
                        L25:
                            k2.c.G(r8)
                            androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.PressInteraction$Press> r8 = r7.$pressedInteraction
                            java.lang.Object r8 = r8.getValue()
                            androidx.compose.foundation.interaction.PressInteraction$Press r8 = (androidx.compose.foundation.interaction.PressInteraction.Press) r8
                            if (r8 == 0) goto L4d
                            androidx.compose.foundation.interaction.MutableInteractionSource r0 = r7.$interactionSource
                            androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.PressInteraction$Press> r5 = r7.$pressedInteraction
                            androidx.compose.foundation.interaction.PressInteraction$Cancel r6 = new androidx.compose.foundation.interaction.PressInteraction$Cancel
                            r6.<init>(r8)
                            if (r0 == 0) goto L4a
                            r7.L$0 = r5
                            r7.label = r3
                            java.lang.Object r8 = r0.emit(r6, r7)
                            if (r8 != r4) goto L48
                            goto L62
                        L48:
                            r0 = r5
                        L49:
                            r5 = r0
                        L4a:
                            r5.setValue(r1)
                        L4d:
                            androidx.compose.foundation.interaction.PressInteraction$Press r0 = new androidx.compose.foundation.interaction.PressInteraction$Press
                            long r5 = r7.$it
                            r0.<init>(r5, r1)
                            androidx.compose.foundation.interaction.MutableInteractionSource r8 = r7.$interactionSource
                            if (r8 == 0) goto L63
                            r7.L$0 = r0
                            r7.label = r2
                            java.lang.Object r8 = r8.emit(r0, r7)
                            if (r8 != r4) goto L63
                        L62:
                            return r4
                        L63:
                            androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.PressInteraction$Press> r8 = r7.$pressedInteraction
                            r8.setValue(r0)
                            x6.t0 r8 = x6.t0.f22605a
                            return r8
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextFieldPressGestureFilterKt.AnonymousClass1.AnonymousClass2.C00481.C00491.invokeSuspend(java.lang.Object):java.lang.Object");
                    }

                    @Override // r7.p
                    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                        return ((C00491) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$2, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                @e(c = "androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$1$2", f = "TextFieldPressGestureFilter.kt", l = {76}, m = "invokeSuspend")
                public static final class C00502 extends j implements p<k0, d<? super t0>, Object> {
                    final /* synthetic */ MutableInteractionSource $interactionSource;
                    final /* synthetic */ MutableState<PressInteraction.Press> $pressedInteraction;
                    final /* synthetic */ boolean $success;
                    Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00502(MutableState<PressInteraction.Press> mutableState, boolean z, MutableInteractionSource mutableInteractionSource, d<? super C00502> dVar) {
                        super(2, dVar);
                        this.$pressedInteraction = mutableState;
                        this.$success = z;
                        this.$interactionSource = mutableInteractionSource;
                    }

                    @Override // f7.a
                    public final d<t0> create(Object obj, d<?> dVar) {
                        return new C00502(this.$pressedInteraction, this.$success, this.$interactionSource, dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        MutableState<PressInteraction.Press> mutableState;
                        MutableState<PressInteraction.Press> mutableState2;
                        int i10 = this.label;
                        if (i10 == 0) {
                            c.G(obj);
                            PressInteraction.Press value = this.$pressedInteraction.getValue();
                            if (value != null) {
                                boolean z = this.$success;
                                MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                                mutableState = this.$pressedInteraction;
                                Interaction release = z ? new PressInteraction.Release(value) : new PressInteraction.Cancel(value);
                                if (mutableInteractionSource != null) {
                                    this.L$0 = mutableState;
                                    this.label = 1;
                                    Object objEmit = mutableInteractionSource.emit(release, this);
                                    a aVar = a.f15033i;
                                    if (objEmit == aVar) {
                                        return aVar;
                                    }
                                    mutableState2 = mutableState;
                                }
                                mutableState.setValue(null);
                            }
                            return t0.f22605a;
                        }
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        mutableState2 = (MutableState) this.L$0;
                        c.G(obj);
                        mutableState = mutableState2;
                        mutableState.setValue(null);
                        return t0.f22605a;
                    }

                    @Override // r7.p
                    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                        return ((C00502) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00481(k0 k0Var, MutableState<PressInteraction.Press> mutableState, MutableInteractionSource mutableInteractionSource, d<? super C00481> dVar) {
                    super(3, dVar);
                    this.$scope = k0Var;
                    this.$pressedInteraction = mutableState;
                    this.$interactionSource = mutableInteractionSource;
                }

                @Override // r7.q
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return m894invoked4ec7I((PressGestureScope) obj, ((Offset) obj2).getPackedValue(), (d) obj3);
                }

                /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
                public final Object m894invoked4ec7I(PressGestureScope pressGestureScope, long j10, d<? super t0> dVar) {
                    C00481 c00481 = new C00481(this.$scope, this.$pressedInteraction, this.$interactionSource, dVar);
                    c00481.L$0 = pressGestureScope;
                    c00481.J$0 = j10;
                    return c00481.invokeSuspend(t0.f22605a);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        c.G(obj);
                        PressGestureScope pressGestureScope = (PressGestureScope) this.L$0;
                        m0.p(this.$scope, null, 0, new C00491(this.$pressedInteraction, this.J$0, this.$interactionSource, null), 3);
                        this.label = 1;
                        obj = pressGestureScope.tryAwaitRelease(this);
                        a aVar = a.f15033i;
                        if (obj == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        c.G(obj);
                    }
                    m0.p(this.$scope, null, 0, new C00502(this.$pressedInteraction, ((Boolean) obj).booleanValue(), this.$interactionSource, null), 3);
                    return t0.f22605a;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$2$2, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C00512 extends r implements l<Offset, t0> {
                final /* synthetic */ State<l<Offset, t0>> $onTapState;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00512(State<? extends l<? super Offset, t0>> state) {
                    super(1);
                    this.$onTapState = state;
                }

                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m895invokek4lQ0M(((Offset) obj).getPackedValue());
                    return t0.f22605a;
                }

                /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
                public final void m895invokek4lQ0M(long j10) {
                    this.$onTapState.getValue().invoke(Offset.m3225boximpl(j10));
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass2(k0 k0Var, MutableState<PressInteraction.Press> mutableState, MutableInteractionSource mutableInteractionSource, State<? extends l<? super Offset, t0>> state, d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.$scope = k0Var;
                this.$pressedInteraction = mutableState;
                this.$interactionSource = mutableInteractionSource;
                this.$onTapState = state;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$scope, this.$pressedInteraction, this.$interactionSource, this.$onTapState, dVar);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // r7.p
            public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
                return ((AnonymousClass2) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                    C00481 c00481 = new C00481(this.$scope, this.$pressedInteraction, this.$interactionSource, null);
                    C00512 c00512 = new C00512(this.$onTapState);
                    this.label = 1;
                    Object objDetectTapAndPress = TapGestureDetectorKt.detectTapAndPress(pointerInputScope, c00481, c00512, this);
                    a aVar = a.f15033i;
                    if (objDetectTapAndPress == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super Offset, t0> lVar, MutableInteractionSource mutableInteractionSource) {
            super(3);
            this.$onTap = lVar;
            this.$interactionSource = mutableInteractionSource;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(-102778667);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-102778667, i10, -1, "androidx.compose.foundation.text.tapPressTextFieldModifier.<anonymous> (TextFieldPressGestureFilter.kt:40)");
            }
            Object objG = a0.c.g(773894976, composer, -492369756);
            Composer.Companion companion = Composer.INSTANCE;
            if (objG == companion.getEmpty()) {
                objG = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
            }
            composer.endReplaceableGroup();
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
            composer.endReplaceableGroup();
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            MutableState mutableState = (MutableState) objRememberedValue;
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(this.$onTap, composer, 0);
            MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            composer.startReplaceableGroup(-585649902);
            boolean zChanged = composer.changed(mutableState) | composer.changed(this.$interactionSource);
            MutableInteractionSource mutableInteractionSource2 = this.$interactionSource;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new TextFieldPressGestureFilterKt$tapPressTextFieldModifier$1$1$1(mutableState, mutableInteractionSource2);
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            EffectsKt.DisposableEffect(mutableInteractionSource, (l<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composer, 0);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MutableInteractionSource mutableInteractionSource3 = this.$interactionSource;
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion2, mutableInteractionSource3, new AnonymousClass2(coroutineScope, mutableState, mutableInteractionSource3, stateRememberUpdatedState, null));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifierPointerInput;
        }
    }

    public static final Modifier tapPressTextFieldModifier(Modifier modifier, MutableInteractionSource mutableInteractionSource, boolean z, l<? super Offset, t0> lVar) {
        return z ? ComposedModifierKt.composed$default(modifier, null, new AnonymousClass1(lVar, mutableInteractionSource), 1, null) : modifier;
    }

    public static /* synthetic */ Modifier tapPressTextFieldModifier$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        return tapPressTextFieldModifier(modifier, mutableInteractionSource, z, lVar);
    }
}

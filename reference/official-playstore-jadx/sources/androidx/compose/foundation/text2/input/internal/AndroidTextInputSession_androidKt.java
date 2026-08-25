package androidx.compose.foundation.text2.input.internal;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.foundation.text2.input.InputTransformation;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.TextFieldState;
import androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt;
import androidx.compose.foundation.text2.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.ui.platform.PlatformTextInputMethodRequest;
import androidx.compose.ui.platform.PlatformTextInputSession;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.c;
import f7.e;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a:\u0010\n\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005H\u0080@¢\u0006\u0004\b\n\u0010\u000b\u001a#\u0010\u000f\u001a\u00020\u0007*\u00020\f2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0015\u0010\u0016\u001a'\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\"\u001a\u0010\u001d\u001a\u00020\u00148\u0000X\u0081T¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u0012\u0004\b\u001f\u0010 \"\u0014\u0010!\u001a\u00020\u00178\u0002X\u0082T¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Landroidx/compose/ui/platform/PlatformTextInputSession;", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "state", "Landroidx/compose/ui/text/input/ImeOptions;", "imeOptions", "Lkotlin/Function1;", "Landroidx/compose/ui/text/input/ImeAction;", "Lx6/t0;", "onImeAction", "", "platformSpecificTextInputSession", "(Landroidx/compose/ui/platform/PlatformTextInputSession;Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/ui/text/input/ImeOptions;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Landroid/view/inputmethod/EditorInfo;", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "textFieldValue", "update", "(Landroid/view/inputmethod/EditorInfo;Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/ui/text/input/ImeOptions;)V", "", "bits", "flag", "", "hasFlag", "(II)Z", "", "tag", "Lkotlin/Function0;", "content", "logDebug", "(Ljava/lang/String;Lr7/a;)V", "TIA_DEBUG", "Z", "getTIA_DEBUG$annotations", "()V", "TAG", "Ljava/lang/String;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidTextInputSession_androidKt {
    private static final String TAG = "AndroidTextInputSession";
    public static final boolean TIA_DEBUG = false;

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt", f = "AndroidTextInputSession.android.kt", l = {50}, m = "platformSpecificTextInputSession")
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidTextInputSession_androidKt.platformSpecificTextInputSession(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/Void;"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2", f = "AndroidTextInputSession.android.kt", l = {73}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<?>, Object> {
        final /* synthetic */ ComposeInputMethodManager $composeImm;
        final /* synthetic */ ImeOptions $imeOptions;
        final /* synthetic */ l<ImeAction, t0> $onImeAction;
        final /* synthetic */ TransformedTextFieldState $state;
        final /* synthetic */ PlatformTextInputSession $this_platformSpecificTextInputSession;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2$1", f = "AndroidTextInputSession.android.kt", l = {52}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ ComposeInputMethodManager $composeImm;
            final /* synthetic */ ImeOptions $imeOptions;
            final /* synthetic */ TransformedTextFieldState $state;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TransformedTextFieldState transformedTextFieldState, ComposeInputMethodManager composeInputMethodManager, ImeOptions imeOptions, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$state = transformedTextFieldState;
                this.$composeImm = composeInputMethodManager;
                this.$imeOptions = imeOptions;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invokeSuspend$lambda$0(ComposeInputMethodManager composeInputMethodManager, ImeOptions imeOptions, TextFieldCharSequence textFieldCharSequence, TextFieldCharSequence textFieldCharSequence2) {
                if (!TextRange.m5185equalsimpl0(textFieldCharSequence.getSelectionInChars(), textFieldCharSequence2.getSelectionInChars()) || !kotlin.jvm.internal.p.a(textFieldCharSequence.getCompositionInChars(), textFieldCharSequence2.getCompositionInChars())) {
                    int iM5190getMinimpl = TextRange.m5190getMinimpl(textFieldCharSequence2.getSelectionInChars());
                    int iM5189getMaximpl = TextRange.m5189getMaximpl(textFieldCharSequence2.getSelectionInChars());
                    TextRange compositionInChars = textFieldCharSequence2.getCompositionInChars();
                    int iM5190getMinimpl2 = compositionInChars != null ? TextRange.m5190getMinimpl(compositionInChars.getPackedValue()) : -1;
                    TextRange compositionInChars2 = textFieldCharSequence2.getCompositionInChars();
                    composeInputMethodManager.updateSelection(iM5190getMinimpl, iM5189getMaximpl, iM5190getMinimpl2, compositionInChars2 != null ? TextRange.m5189getMaximpl(compositionInChars2.getPackedValue()) : -1);
                }
                if (textFieldCharSequence.contentEquals(textFieldCharSequence2) || KeyboardType.m5402equalsimpl0(imeOptions.getKeyboardType(), KeyboardType.INSTANCE.m5420getPasswordPjHm6EE())) {
                    return;
                }
                composeInputMethodManager.restartInput();
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass1(this.$state, this.$composeImm, this.$imeOptions, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    TransformedTextFieldState transformedTextFieldState = this.$state;
                    final ComposeInputMethodManager composeInputMethodManager = this.$composeImm;
                    final ImeOptions imeOptions = this.$imeOptions;
                    TextFieldState.NotifyImeListener notifyImeListener = new TextFieldState.NotifyImeListener() { // from class: androidx.compose.foundation.text2.input.internal.b
                        @Override // androidx.compose.foundation.text2.input.TextFieldState.NotifyImeListener
                        public final void onChange(TextFieldCharSequence textFieldCharSequence, TextFieldCharSequence textFieldCharSequence2) {
                            AndroidTextInputSession_androidKt.AnonymousClass2.AnonymousClass1.invokeSuspend$lambda$0(composeInputMethodManager, imeOptions, textFieldCharSequence, textFieldCharSequence2);
                        }
                    };
                    this.label = 1;
                    Object objCollectImeNotifications = transformedTextFieldState.collectImeNotifications(notifyImeListener, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objCollectImeNotifications == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                throw new KotlinNothingValueException();
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(PlatformTextInputSession platformTextInputSession, TransformedTextFieldState transformedTextFieldState, ComposeInputMethodManager composeInputMethodManager, ImeOptions imeOptions, l<? super ImeAction, t0> lVar, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$this_platformSpecificTextInputSession = platformTextInputSession;
            this.$state = transformedTextFieldState;
            this.$composeImm = composeInputMethodManager;
            this.$imeOptions = imeOptions;
            this.$onImeAction = lVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final InputConnection invokeSuspend$lambda$0(final TransformedTextFieldState transformedTextFieldState, ImeOptions imeOptions, final ComposeInputMethodManager composeInputMethodManager, final l lVar, EditorInfo editorInfo) {
            AndroidTextInputSession_androidKt.logDebug$default(null, new AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2$2$1(transformedTextFieldState), 1, null);
            TextInputSession textInputSession = new TextInputSession() { // from class: androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2$2$textInputSession$1
                @Override // androidx.compose.foundation.text2.input.internal.TextInputSession
                public TextFieldCharSequence getText() {
                    return transformedTextFieldState.getText();
                }

                @Override // androidx.compose.foundation.text2.input.internal.TextInputSession
                /* JADX INFO: renamed from: onImeAction-KlQnJC8, reason: not valid java name */
                public void mo1082onImeActionKlQnJC8(int imeAction) {
                    l<ImeAction, t0> lVar2 = lVar;
                    if (lVar2 != null) {
                        lVar2.invoke(ImeAction.m5352boximpl(imeAction));
                    }
                }

                @Override // androidx.compose.foundation.text2.input.internal.TextInputSession
                public void requestEdit(l<? super EditingBuffer, t0> block) {
                    TransformedTextFieldState transformedTextFieldState2 = transformedTextFieldState;
                    TextFieldState textFieldState = transformedTextFieldState2.textFieldState;
                    InputTransformation inputTransformation = transformedTextFieldState2.inputTransformation;
                    TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
                    TextFieldCharSequence text = textFieldState.getText();
                    textFieldState.getMainBuffer().getChangeTracker().clearChanges();
                    block.invoke(textFieldState.getMainBuffer());
                    if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && kotlin.jvm.internal.p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
                        return;
                    }
                    textFieldState.commitEditAsUser(text, inputTransformation, false, textFieldEditUndoBehavior);
                }

                @Override // androidx.compose.foundation.text2.input.internal.TextInputSession
                public void sendKeyEvent(KeyEvent keyEvent) {
                    composeInputMethodManager.sendKeyEvent(keyEvent);
                }
            };
            AndroidTextInputSession_androidKt.update(editorInfo, transformedTextFieldState.getText(), imeOptions);
            return new StatelessInputConnection(textInputSession);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_platformSpecificTextInputSession, this.$state, this.$composeImm, this.$imeOptions, this.$onImeAction, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                m0.p((k0) this.L$0, null, 4, new AnonymousClass1(this.$state, this.$composeImm, this.$imeOptions, null), 1);
                PlatformTextInputSession platformTextInputSession = this.$this_platformSpecificTextInputSession;
                final TransformedTextFieldState transformedTextFieldState = this.$state;
                final ImeOptions imeOptions = this.$imeOptions;
                final ComposeInputMethodManager composeInputMethodManager = this.$composeImm;
                final l<ImeAction, t0> lVar = this.$onImeAction;
                PlatformTextInputMethodRequest platformTextInputMethodRequest = new PlatformTextInputMethodRequest() { // from class: androidx.compose.foundation.text2.input.internal.a
                    @Override // androidx.compose.ui.platform.PlatformTextInputMethodRequest
                    public final InputConnection createInputConnection(EditorInfo editorInfo) {
                        return AndroidTextInputSession_androidKt.AnonymousClass2.invokeSuspend$lambda$0(transformedTextFieldState, imeOptions, composeInputMethodManager, lVar, editorInfo);
                    }
                };
                this.label = 1;
                Object objStartInputMethod = platformTextInputSession.startInputMethod(platformTextInputMethodRequest, this);
                e7.a aVar = e7.a.f15033i;
                if (objStartInputMethod == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            throw new KotlinNothingValueException();
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<?> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public static /* synthetic */ void getTIA_DEBUG$annotations() {
    }

    private static final boolean hasFlag(int i10, int i11) {
        return (i10 & i11) == i11;
    }

    public static /* synthetic */ void logDebug$default(String str, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = TAG;
        }
        logDebug(str, aVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object platformSpecificTextInputSession(androidx.compose.ui.platform.PlatformTextInputSession r10, androidx.compose.foundation.text2.input.internal.TransformedTextFieldState r11, androidx.compose.ui.text.input.ImeOptions r12, r7.l<? super androidx.compose.ui.text.input.ImeAction, x6.t0> r13, d7.d<?> r14) {
        /*
            boolean r0 = r14 instanceof androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r14
            androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$1 r0 = (androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$1 r0 = new androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$1
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 == r2) goto L29
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L29:
            k2.c.G(r14)
            goto L4d
        L2d:
            k2.c.G(r14)
            android.view.View r14 = r10.getView()
            androidx.compose.foundation.text2.input.internal.ComposeInputMethodManager r6 = androidx.compose.foundation.text2.input.internal.ComposeInputMethodManager_androidKt.ComposeInputMethodManager(r14)
            androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2 r3 = new androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt$platformSpecificTextInputSession$2
            r9 = 0
            r4 = r10
            r5 = r11
            r7 = r12
            r8 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r0.label = r2
            java.lang.Object r10 = ka.l0.c(r3, r0)
            e7.a r11 = e7.a.f15033i
            if (r10 != r11) goto L4d
            return r11
        L4d:
            kotlin.KotlinNothingValueException r10 = new kotlin.KotlinNothingValueException
            r10.<init>()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.AndroidTextInputSession_androidKt.platformSpecificTextInputSession(androidx.compose.ui.platform.PlatformTextInputSession, androidx.compose.foundation.text2.input.internal.TransformedTextFieldState, androidx.compose.ui.text.input.ImeOptions, r7.l, d7.d):java.lang.Object");
    }

    public static final void update(EditorInfo editorInfo, TextFieldCharSequence textFieldCharSequence, ImeOptions imeOptions) {
        int imeAction = imeOptions.getImeAction();
        ImeAction.Companion companion = ImeAction.INSTANCE;
        int i10 = 3;
        int i11 = 6;
        if (ImeAction.m5355equalsimpl0(imeAction, companion.m5367getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i11 = 0;
            }
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5371getNoneeUduSuo())) {
            i11 = 1;
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5369getGoeUduSuo())) {
            i11 = 2;
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5370getNexteUduSuo())) {
            i11 = 5;
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5372getPreviouseUduSuo())) {
            i11 = 7;
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5373getSearcheUduSuo())) {
            i11 = 3;
        } else if (ImeAction.m5355equalsimpl0(imeAction, companion.m5374getSendeUduSuo())) {
            i11 = 4;
        } else if (!ImeAction.m5355equalsimpl0(imeAction, companion.m5368getDoneeUduSuo())) {
            throw new IllegalStateException("invalid ImeAction");
        }
        editorInfo.imeOptions = i11;
        int keyboardType = imeOptions.getKeyboardType();
        KeyboardType.Companion companion2 = KeyboardType.INSTANCE;
        if (KeyboardType.m5402equalsimpl0(keyboardType, companion2.m5422getTextPjHm6EE())) {
            i10 = 1;
        } else if (KeyboardType.m5402equalsimpl0(keyboardType, companion2.m5415getAsciiPjHm6EE())) {
            editorInfo.imeOptions |= Integer.MIN_VALUE;
            i10 = 1;
        } else if (KeyboardType.m5402equalsimpl0(keyboardType, companion2.m5418getNumberPjHm6EE())) {
            i10 = 2;
        } else if (!KeyboardType.m5402equalsimpl0(keyboardType, companion2.m5421getPhonePjHm6EE())) {
            if (KeyboardType.m5402equalsimpl0(keyboardType, companion2.m5423getUriPjHm6EE())) {
                i10 = 17;
            } else if (KeyboardType.m5402equalsimpl0(keyboardType, companion2.m5417getEmailPjHm6EE())) {
                i10 = 33;
            } else if (KeyboardType.m5402equalsimpl0(keyboardType, companion2.m5420getPasswordPjHm6EE())) {
                i10 = TsExtractor.TS_STREAM_TYPE_AC3;
            } else if (KeyboardType.m5402equalsimpl0(keyboardType, companion2.m5419getNumberPasswordPjHm6EE())) {
                i10 = 18;
            } else {
                if (!KeyboardType.m5402equalsimpl0(keyboardType, companion2.m5416getDecimalPjHm6EE())) {
                    throw new IllegalStateException("Invalid Keyboard Type");
                }
                i10 = 8194;
            }
        }
        editorInfo.inputType = i10;
        if (!imeOptions.getSingleLine() && hasFlag(editorInfo.inputType, 1)) {
            editorInfo.inputType |= 131072;
            if (ImeAction.m5355equalsimpl0(imeOptions.getImeAction(), companion.m5367getDefaulteUduSuo())) {
                editorInfo.imeOptions |= 1073741824;
            }
        }
        if (hasFlag(editorInfo.inputType, 1)) {
            int capitalization = imeOptions.getCapitalization();
            KeyboardCapitalization.Companion companion3 = KeyboardCapitalization.INSTANCE;
            if (KeyboardCapitalization.m5387equalsimpl0(capitalization, companion3.m5395getCharactersIUNYP9k())) {
                editorInfo.inputType |= 4096;
            } else if (KeyboardCapitalization.m5387equalsimpl0(capitalization, companion3.m5398getWordsIUNYP9k())) {
                editorInfo.inputType |= 8192;
            } else if (KeyboardCapitalization.m5387equalsimpl0(capitalization, companion3.m5397getSentencesIUNYP9k())) {
                editorInfo.inputType |= 16384;
            }
            if (imeOptions.getAutoCorrect()) {
                editorInfo.inputType |= 32768;
            }
        }
        editorInfo.initialSelStart = TextRange.m5192getStartimpl(textFieldCharSequence.getSelectionInChars());
        editorInfo.initialSelEnd = TextRange.m5187getEndimpl(textFieldCharSequence.getSelectionInChars());
        androidx.core.view.inputmethod.b.c(editorInfo, textFieldCharSequence);
        editorInfo.imeOptions |= 33554432;
    }

    private static final void logDebug(String str, r7.a<String> aVar) {
    }
}

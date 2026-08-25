package androidx.compose.foundation.text2;

import a3.b;
import androidx.compose.foundation.text2.input.CodepointTransformation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.media3.exoplayer.RendererCapabilities;
import d7.d;
import e7.a;
import f7.e;
import k2.c;
import ka.k0;
import ka.m0;
import ka.s0;
import kotlin.Metadata;
import m2.f0;
import ma.j;
import ma.n;
import na.y0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/text2/SecureTextFieldController;", "", "Lka/k0;", "coroutineScope", "<init>", "(Lka/k0;)V", "Lx6/t0;", "scheduleHide", "()V", "Landroidx/compose/foundation/text2/PasswordRevealFilter;", "passwordRevealFilter", "Landroidx/compose/foundation/text2/PasswordRevealFilter;", "getPasswordRevealFilter", "()Landroidx/compose/foundation/text2/PasswordRevealFilter;", "Landroidx/compose/foundation/text2/input/CodepointTransformation;", "codepointTransformation", "Landroidx/compose/foundation/text2/input/CodepointTransformation;", "getCodepointTransformation", "()Landroidx/compose/foundation/text2/input/CodepointTransformation;", "Landroidx/compose/ui/Modifier;", "focusChangeModifier", "Landroidx/compose/ui/Modifier;", "getFocusChangeModifier", "()Landroidx/compose/ui/Modifier;", "Lma/j;", "resetTimerSignal", "Lma/j;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SecureTextFieldController {
    public static final int $stable = 8;
    private final PasswordRevealFilter passwordRevealFilter = new PasswordRevealFilter(new SecureTextFieldController$passwordRevealFilter$1(this));
    private final CodepointTransformation codepointTransformation = new b(this, 1);
    private final Modifier focusChangeModifier = FocusChangedModifierKt.onFocusChanged(Modifier.INSTANCE, new SecureTextFieldController$focusChangeModifier$1(this));
    private final j<t0> resetTimerSignal = f0.a(Integer.MAX_VALUE, 0, 6);

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.SecureTextFieldController$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.text2.SecureTextFieldController$1", f = "BasicSecureTextField.kt", l = {RendererCapabilities.DECODER_SUPPORT_MASK}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends f7.j implements p<k0, d<? super t0>, Object> {
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.text2.SecureTextFieldController$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lx6/t0;", "it", "<anonymous>", "(V)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text2.SecureTextFieldController$1$1", f = "BasicSecureTextField.kt", l = {385}, m = "invokeSuspend")
        public static final class C00651 extends f7.j implements p<t0, d<? super t0>, Object> {
            int label;
            final /* synthetic */ SecureTextFieldController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00651(SecureTextFieldController secureTextFieldController, d<? super C00651> dVar) {
                super(2, dVar);
                this.this$0 = secureTextFieldController;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C00651(this.this$0, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    this.label = 1;
                    Object objA = s0.a(1500L, this);
                    a aVar = a.f15033i;
                    if (objA == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                this.this$0.getPasswordRevealFilter().hide();
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(t0 t0Var, d<? super t0> dVar) {
                return ((C00651) create(t0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return SecureTextFieldController.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            boolean z = true;
            if (i10 == 0) {
                c.G(obj);
                na.e eVar = new na.e(SecureTextFieldController.this.resetTimerSignal, z);
                C00651 c00651 = new C00651(SecureTextFieldController.this, null);
                this.label = 1;
                Object objG = y0.g(eVar, c00651, this);
                a aVar = a.f15033i;
                if (objG == aVar) {
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

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public SecureTextFieldController(k0 k0Var) {
        m0.p(k0Var, null, 0, new AnonymousClass1(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int codepointTransformation$lambda$0(SecureTextFieldController secureTextFieldController, int i10, int i11) {
        if (i10 == secureTextFieldController.passwordRevealFilter.getRevealCodepointIndex$foundation_release()) {
            return i11;
        }
        return 8226;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleHide() {
        if (this.resetTimerSignal.mo6685trySendJP2dKIU(t0.f22605a) instanceof n) {
            this.passwordRevealFilter.hide();
        }
    }

    public final CodepointTransformation getCodepointTransformation() {
        return this.codepointTransformation;
    }

    public final Modifier getFocusChangeModifier() {
        return this.focusChangeModifier;
    }

    public final PasswordRevealFilter getPasswordRevealFilter() {
        return this.passwordRevealFilter;
    }
}

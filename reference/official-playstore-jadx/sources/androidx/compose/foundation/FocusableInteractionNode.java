package androidx.compose.foundation;

import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\u000b\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0005R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/FocusableInteractionNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "<init>", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;)V", "Lx6/t0;", "disposeInteractionSource", "()V", "Landroidx/compose/foundation/interaction/Interaction;", "interaction", "emitWithFallback", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/Interaction;)V", "", "isFocused", "setFocus", "(Z)V", "update", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/foundation/interaction/FocusInteraction$Focus;", "focusedInteraction", "Landroidx/compose/foundation/interaction/FocusInteraction$Focus;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class FocusableInteractionNode extends Modifier.Node {
    private FocusInteraction.Focus focusedInteraction;
    private MutableInteractionSource interactionSource;

    /* JADX INFO: renamed from: androidx.compose.foundation.FocusableInteractionNode$emitWithFallback$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.FocusableInteractionNode$emitWithFallback$1", f = "Focusable.kt", l = {MediaError.DetailedErrorCode.HLS_NETWORK_MASTER_PLAYLIST}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ Interaction $interaction;
        final /* synthetic */ MutableInteractionSource $this_emitWithFallback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MutableInteractionSource mutableInteractionSource, Interaction interaction, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$this_emitWithFallback = mutableInteractionSource;
            this.$interaction = interaction;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$this_emitWithFallback, this.$interaction, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                MutableInteractionSource mutableInteractionSource = this.$this_emitWithFallback;
                Interaction interaction = this.$interaction;
                this.label = 1;
                Object objEmit = mutableInteractionSource.emit(interaction, this);
                e7.a aVar = e7.a.f15033i;
                if (objEmit == aVar) {
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

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public FocusableInteractionNode(MutableInteractionSource mutableInteractionSource) {
        this.interactionSource = mutableInteractionSource;
    }

    private final void disposeInteractionSource() {
        FocusInteraction.Focus focus;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null && (focus = this.focusedInteraction) != null) {
            mutableInteractionSource.tryEmit(new FocusInteraction.Unfocus(focus));
        }
        this.focusedInteraction = null;
    }

    private final void emitWithFallback(MutableInteractionSource mutableInteractionSource, Interaction interaction) {
        if (getIsAttached()) {
            m0.p(getCoroutineScope(), null, 0, new AnonymousClass1(mutableInteractionSource, interaction, null), 3);
        } else {
            mutableInteractionSource.tryEmit(interaction);
        }
    }

    public final void setFocus(boolean isFocused) {
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        if (mutableInteractionSource != null) {
            if (!isFocused) {
                FocusInteraction.Focus focus = this.focusedInteraction;
                if (focus != null) {
                    emitWithFallback(mutableInteractionSource, new FocusInteraction.Unfocus(focus));
                    this.focusedInteraction = null;
                    return;
                }
                return;
            }
            FocusInteraction.Focus focus2 = this.focusedInteraction;
            if (focus2 != null) {
                emitWithFallback(mutableInteractionSource, new FocusInteraction.Unfocus(focus2));
                this.focusedInteraction = null;
            }
            FocusInteraction.Focus focus3 = new FocusInteraction.Focus();
            emitWithFallback(mutableInteractionSource, focus3);
            this.focusedInteraction = focus3;
        }
    }

    public final void update(MutableInteractionSource interactionSource) {
        if (kotlin.jvm.internal.p.a(this.interactionSource, interactionSource)) {
            return;
        }
        disposeInteractionSource();
        this.interactionSource = interactionSource;
    }
}

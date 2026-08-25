package androidx.compose.foundation;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.media3.exoplayer.RendererCapabilities;
import d7.d;
import f7.e;
import f7.j;
import java.util.Map;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "invoke-ZmokQxo", "(Landroid/view/KeyEvent;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1 extends r implements l<KeyEvent, Boolean> {
    final /* synthetic */ Map<Key, PressInteraction.Press> $currentKeyPressInteractions;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ k0 $indicationScope;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ State<Offset> $keyClickOffset;
    final /* synthetic */ r7.a<t0> $onClick;

    /* JADX INFO: renamed from: androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$1", f = "Clickable.kt", l = {RendererCapabilities.DECODER_SUPPORT_MASK}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ PressInteraction.Press $press;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MutableInteractionSource mutableInteractionSource, PressInteraction.Press press, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$interactionSource = mutableInteractionSource;
            this.$press = press;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass1(this.$interactionSource, this.$press, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                PressInteraction.Press press = this.$press;
                this.label = 1;
                Object objEmit = mutableInteractionSource.emit(press, this);
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1(boolean z, Map<Key, PressInteraction.Press> map, State<Offset> state, k0 k0Var, r7.a<t0> aVar, MutableInteractionSource mutableInteractionSource) {
        super(1);
        this.$enabled = z;
        this.$currentKeyPressInteractions = map;
        this.$keyClickOffset = state;
        this.$indicationScope = k0Var;
        this.$onClick = aVar;
        this.$interactionSource = mutableInteractionSource;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return m222invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
    }

    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
    public final Boolean m222invokeZmokQxo(android.view.KeyEvent keyEvent) {
        boolean z = false;
        if (this.$enabled && Clickable_androidKt.m229isPressZmokQxo(keyEvent)) {
            if (!this.$currentKeyPressInteractions.containsKey(Key.m4090boximpl(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent)))) {
                PressInteraction.Press press = new PressInteraction.Press(this.$keyClickOffset.getValue().getPackedValue(), null);
                this.$currentKeyPressInteractions.put(Key.m4090boximpl(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent)), press);
                m0.p(this.$indicationScope, null, 0, new AnonymousClass1(this.$interactionSource, press, null), 3);
                z = true;
            }
        } else if (this.$enabled && Clickable_androidKt.m227isClickZmokQxo(keyEvent)) {
            PressInteraction.Press pressRemove = this.$currentKeyPressInteractions.remove(Key.m4090boximpl(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent)));
            if (pressRemove != null) {
                m0.p(this.$indicationScope, null, 0, new ClickableKt$genericClickableWithoutGesture$detectPressAndClickFromKey$1$2$1(this.$interactionSource, pressRemove, null), 3);
            }
            this.$onClick.invoke();
            z = true;
        }
        return Boolean.valueOf(z);
    }
}

package androidx.compose.foundation;

import androidx.compose.foundation.AbstractClickableNode;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.ClickableKt$handlePressInteraction$2$delayJob$1", f = "Clickable.kt", l = {MediaError.DetailedErrorCode.SEGMENT_NETWORK, 304}, m = "invokeSuspend")
public final class ClickableKt$handlePressInteraction$2$delayJob$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ r7.a<Boolean> $delayPressInteraction;
    final /* synthetic */ AbstractClickableNode.InteractionData $interactionData;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ long $pressPoint;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClickableKt$handlePressInteraction$2$delayJob$1(r7.a<Boolean> aVar, long j10, MutableInteractionSource mutableInteractionSource, AbstractClickableNode.InteractionData interactionData, d<? super ClickableKt$handlePressInteraction$2$delayJob$1> dVar) {
        super(2, dVar);
        this.$delayPressInteraction = aVar;
        this.$pressPoint = j10;
        this.$interactionSource = mutableInteractionSource;
        this.$interactionData = interactionData;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new ClickableKt$handlePressInteraction$2$delayJob$1(this.$delayPressInteraction, this.$pressPoint, this.$interactionSource, this.$interactionData, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0050, code lost:
    
        if (r7.emit(r0, r6) == r3) goto L17;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            int r0 = r6.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L20
            if (r0 == r2) goto L1c
            if (r0 != r1) goto L14
            java.lang.Object r0 = r6.L$0
            androidx.compose.foundation.interaction.PressInteraction$Press r0 = (androidx.compose.foundation.interaction.PressInteraction.Press) r0
            k2.c.G(r7)
            goto L53
        L14:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1c:
            k2.c.G(r7)
            goto L3e
        L20:
            k2.c.G(r7)
            r7.a<java.lang.Boolean> r7 = r6.$delayPressInteraction
            java.lang.Object r7 = r7.invoke()
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L3e
            long r4 = androidx.compose.foundation.Clickable_androidKt.getTapIndicationDelay()
            r6.label = r2
            java.lang.Object r7 = ka.s0.a(r4, r6)
            if (r7 != r3) goto L3e
            goto L52
        L3e:
            androidx.compose.foundation.interaction.PressInteraction$Press r0 = new androidx.compose.foundation.interaction.PressInteraction$Press
            long r4 = r6.$pressPoint
            r7 = 0
            r0.<init>(r4, r7)
            androidx.compose.foundation.interaction.MutableInteractionSource r7 = r6.$interactionSource
            r6.L$0 = r0
            r6.label = r1
            java.lang.Object r7 = r7.emit(r0, r6)
            if (r7 != r3) goto L53
        L52:
            return r3
        L53:
            androidx.compose.foundation.AbstractClickableNode$InteractionData r7 = r6.$interactionData
            r7.setPressInteraction(r0)
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.ClickableKt$handlePressInteraction$2$delayJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((ClickableKt$handlePressInteraction$2$delayJob$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}

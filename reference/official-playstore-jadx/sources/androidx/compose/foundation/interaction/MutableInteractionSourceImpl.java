package androidx.compose.foundation.interaction;

import d7.d;
import e7.a;
import kotlin.Metadata;
import na.p0;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0096@¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\n\u0010\u000bR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/interaction/MutableInteractionSourceImpl;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "<init>", "()V", "Landroidx/compose/foundation/interaction/Interaction;", "interaction", "Lx6/t0;", "emit", "(Landroidx/compose/foundation/interaction/Interaction;Ld7/d;)Ljava/lang/Object;", "", "tryEmit", "(Landroidx/compose/foundation/interaction/Interaction;)Z", "Lna/p0;", "interactions", "Lna/p0;", "getInteractions", "()Lna/p0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class MutableInteractionSourceImpl implements MutableInteractionSource {
    private final p0<Interaction> interactions = y0.a(16, 1);

    @Override // androidx.compose.foundation.interaction.MutableInteractionSource
    public Object emit(Interaction interaction, d<? super t0> dVar) {
        Object objEmit = getInteractions().emit(interaction, dVar);
        return objEmit == a.f15033i ? objEmit : t0.f22605a;
    }

    @Override // androidx.compose.foundation.interaction.MutableInteractionSource
    public boolean tryEmit(Interaction interaction) {
        return getInteractions().b(interaction);
    }

    @Override // androidx.compose.foundation.interaction.InteractionSource
    public p0<Interaction> getInteractions() {
        return this.interactions;
    }
}

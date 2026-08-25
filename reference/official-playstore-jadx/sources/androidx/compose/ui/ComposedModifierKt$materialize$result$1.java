package androidx.compose.ui;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.q0;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "acc", "element", "Landroidx/compose/ui/Modifier$Element;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ComposedModifierKt$materialize$result$1 extends r implements p<Modifier, Modifier.Element, Modifier> {
    final /* synthetic */ Composer $this_materialize;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposedModifierKt$materialize$result$1(Composer composer) {
        super(2);
        this.$this_materialize = composer;
    }

    @Override // r7.p
    public final Modifier invoke(Modifier modifier, Modifier.Element element) {
        boolean z = element instanceof ComposedModifier;
        Modifier modifierMaterializeModifier = element;
        if (z) {
            q<Modifier, Composer, Integer, Modifier> factory = ((ComposedModifier) element).getFactory();
            q0.e(3, factory);
            modifierMaterializeModifier = ComposedModifierKt.materializeModifier(this.$this_materialize, (Modifier) factory.invoke(Modifier.INSTANCE, this.$this_materialize, 0));
        }
        return modifier.then(modifierMaterializeModifier);
    }
}

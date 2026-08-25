package androidx.compose.ui.modifier;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0006\u001a\u00020\u0000*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function1;", "Landroidx/compose/ui/modifier/ModifierLocalReadScope;", "Lx6/t0;", "Lx6/n;", "consumer", "modifierLocalConsumer", "(Landroidx/compose/ui/Modifier;Lr7/l;)Landroidx/compose/ui/Modifier;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModifierLocalConsumerKt {
    public static final Modifier modifierLocalConsumer(Modifier modifier, l<? super ModifierLocalReadScope, t0> lVar) {
        return modifier.then(new ModifierLocalConsumerImpl(lVar, InspectableValueKt.isDebugInspectorInfoEnabled() ? new ModifierLocalConsumerKt$modifierLocalConsumer$$inlined$debugInspectorInfo$1(lVar) : InspectableValueKt.getNoInspectorInfo()));
    }
}

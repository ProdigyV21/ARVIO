package androidx.compose.ui.modifier;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"T", "Lkotlin/Function0;", "defaultFactory", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "modifierLocalOf", "(Lr7/a;)Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModifierLocalKt {
    public static final <T> ProvidableModifierLocal<T> modifierLocalOf(r7.a<? extends T> aVar) {
        return new ProvidableModifierLocal<>(aVar);
    }
}

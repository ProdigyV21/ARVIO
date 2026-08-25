package androidx.compose.ui.modifier;

import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.InspectorValueInfo;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a5\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"T", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "key", "Lkotlin/Function0;", "value", "modifierLocalProvider", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/modifier/ProvidableModifierLocal;Lr7/a;)Landroidx/compose/ui/Modifier;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModifierLocalProviderKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.ui.modifier.ModifierLocalProviderKt$modifierLocalProvider$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u00012\u00020\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00028\u00008VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"androidx/compose/ui/modifier/ModifierLocalProviderKt$modifierLocalProvider$1", "Landroidx/compose/ui/modifier/ModifierLocalProvider;", "Landroidx/compose/ui/platform/InspectorValueInfo;", "key", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "getKey", "()Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "value", "getValue", "()Ljava/lang/Object;", "value$delegate", "Landroidx/compose/runtime/State;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1<T> extends InspectorValueInfo implements ModifierLocalProvider<T> {
        private final ProvidableModifierLocal<T> key;

        /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
        private final State value;

        public AnonymousClass1(ProvidableModifierLocal<T> providableModifierLocal, r7.a<? extends T> aVar, l<? super InspectorInfo, t0> lVar) {
            super(lVar);
            this.key = providableModifierLocal;
            this.value = SnapshotStateKt.derivedStateOf(aVar);
        }

        @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
        public final /* synthetic */ boolean all(l lVar) {
            return androidx.compose.ui.b.a(this, lVar);
        }

        @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
        public final /* synthetic */ boolean any(l lVar) {
            return androidx.compose.ui.b.b(this, lVar);
        }

        @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
        public final /* synthetic */ Object foldIn(Object obj, p pVar) {
            return androidx.compose.ui.b.c(this, obj, pVar);
        }

        @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
        public final /* synthetic */ Object foldOut(Object obj, p pVar) {
            return androidx.compose.ui.b.d(this, obj, pVar);
        }

        @Override // androidx.compose.ui.modifier.ModifierLocalProvider
        public ProvidableModifierLocal<T> getKey() {
            return this.key;
        }

        @Override // androidx.compose.ui.modifier.ModifierLocalProvider
        public T getValue() {
            return (T) this.value.getValue();
        }

        @Override // androidx.compose.ui.Modifier
        public final /* synthetic */ Modifier then(Modifier modifier) {
            return androidx.compose.ui.a.a(this, modifier);
        }
    }

    public static final <T> Modifier modifierLocalProvider(Modifier modifier, ProvidableModifierLocal<T> providableModifierLocal, r7.a<? extends T> aVar) {
        return modifier.then(new AnonymousClass1(providableModifierLocal, aVar, InspectableValueKt.isDebugInspectorInfoEnabled() ? new ModifierLocalProviderKt$modifierLocalProvider$$inlined$debugInspectorInfo$1(providableModifierLocal, aVar) : InspectableValueKt.getNoInspectorInfo()));
    }
}

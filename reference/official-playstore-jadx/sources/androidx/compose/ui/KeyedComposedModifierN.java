package androidx.compose.ui;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.platform.InspectorInfo;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0003\u0018\u00002\u00020\u0001BX\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n\u0012\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\u0002\b\r¢\u0006\u0002\b\n¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0096\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR!\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/KeyedComposedModifierN;", "Landroidx/compose/ui/ComposedModifier;", "", "fqName", "", "", UserMetadata.KEYDATA_FILENAME, "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "Lx6/n;", "inspectorInfo", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/runtime/Composable;", "factory", "<init>", "(Ljava/lang/String;[Ljava/lang/Object;Lr7/l;Lr7/q;)V", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Ljava/lang/String;", "getFqName", "()Ljava/lang/String;", "[Ljava/lang/Object;", "getKeys", "()[Ljava/lang/Object;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class KeyedComposedModifierN extends ComposedModifier {
    private final String fqName;
    private final Object[] keys;

    public KeyedComposedModifierN(String str, Object[] objArr, l<? super InspectorInfo, t0> lVar, q<? super Modifier, ? super Composer, ? super Integer, ? extends Modifier> qVar) {
        super(lVar, qVar);
        this.fqName = str;
        this.keys = objArr;
    }

    public boolean equals(Object other) {
        if (!(other instanceof KeyedComposedModifierN)) {
            return false;
        }
        KeyedComposedModifierN keyedComposedModifierN = (KeyedComposedModifierN) other;
        return p.a(this.fqName, keyedComposedModifierN.fqName) && Arrays.equals(this.keys, keyedComposedModifierN.keys);
    }

    public final String getFqName() {
        return this.fqName;
    }

    public final Object[] getKeys() {
        return this.keys;
    }

    public int hashCode() {
        return Arrays.hashCode(this.keys) + (this.fqName.hashCode() * 31);
    }
}

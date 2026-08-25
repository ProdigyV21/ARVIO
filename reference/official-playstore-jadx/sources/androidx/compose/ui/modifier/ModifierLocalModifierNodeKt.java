package androidx.compose.ui.modifier;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\r\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a!\u0010\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0001\u0010\u0006\u001a-\u0010\u0001\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00032\u0018\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\u0001\u0010\t\u001a)\u0010\u0001\u001a\u00020\u00002\u001a\u0010\u000b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00040\n\"\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0001\u0010\f\u001aA\u0010\u0001\u001a\u00020\u000022\u0010\u000e\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\r0\u00070\n\"\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\r0\u0007¢\u0006\u0004\b\u0001\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/modifier/ModifierLocalMap;", "modifierLocalMapOf", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "T", "Landroidx/compose/ui/modifier/ModifierLocal;", "key", "(Landroidx/compose/ui/modifier/ModifierLocal;)Landroidx/compose/ui/modifier/ModifierLocalMap;", "Lx6/x;", "entry", "(Lx6/x;)Landroidx/compose/ui/modifier/ModifierLocalMap;", "", UserMetadata.KEYDATA_FILENAME, "([Landroidx/compose/ui/modifier/ModifierLocal;)Landroidx/compose/ui/modifier/ModifierLocalMap;", "", "entries", "([Lx6/x;)Landroidx/compose/ui/modifier/ModifierLocalMap;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModifierLocalModifierNodeKt {
    public static final ModifierLocalMap modifierLocalMapOf() {
        return EmptyMap.INSTANCE;
    }

    public static final <T> ModifierLocalMap modifierLocalMapOf(ModifierLocal<T> modifierLocal) {
        return new SingleLocalMap(modifierLocal);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> ModifierLocalMap modifierLocalMapOf(x xVar) {
        SingleLocalMap singleLocalMap = new SingleLocalMap((ModifierLocal) xVar.f22608i);
        singleLocalMap.mo4706set$ui_release((ModifierLocal) xVar.f22608i, xVar.f22609l);
        return singleLocalMap;
    }

    public static final ModifierLocalMap modifierLocalMapOf(x... xVarArr) {
        return new MultiLocalMap((x[]) Arrays.copyOf(xVarArr, xVarArr.length));
    }

    public static final ModifierLocalMap modifierLocalMapOf(ModifierLocal<?>... modifierLocalArr) {
        ArrayList arrayList = new ArrayList(modifierLocalArr.length);
        for (ModifierLocal<?> modifierLocal : modifierLocalArr) {
            arrayList.add(new x(modifierLocal, null));
        }
        x[] xVarArr = (x[]) arrayList.toArray(new x[0]);
        return new MultiLocalMap((x[]) Arrays.copyOf(xVarArr, xVarArr.length));
    }
}

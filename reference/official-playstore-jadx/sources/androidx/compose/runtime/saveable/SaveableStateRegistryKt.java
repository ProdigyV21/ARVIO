package androidx.compose.runtime.saveable;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import com.google.common.util.concurrent.r0;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a?\u0010\t\u001a\u00020\b2\u001c\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002\u0018\u00010\u00002\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\t\u0010\n\u001a\u0013\u0010\f\u001a\u00020\u0006*\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\r\"\u001f\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"", "", "", "", "restoredValues", "Lkotlin/Function1;", "", "canBeSaved", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "SaveableStateRegistry", "(Ljava/util/Map;Lr7/l;)Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "", "fastIsBlank", "(Ljava/lang/CharSequence;)Z", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalSaveableStateRegistry", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalSaveableStateRegistry", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "runtime-saveable_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SaveableStateRegistryKt {
    private static final ProvidableCompositionLocal<SaveableStateRegistry> LocalSaveableStateRegistry = CompositionLocalKt.staticCompositionLocalOf(SaveableStateRegistryKt$LocalSaveableStateRegistry$1.INSTANCE);

    public static final SaveableStateRegistry SaveableStateRegistry(Map<String, ? extends List<? extends Object>> map, l<Object, Boolean> lVar) {
        return new SaveableStateRegistryImpl(map, lVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean fastIsBlank(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i10 = 0; i10 < length; i10++) {
            if (!r0.u(charSequence.charAt(i10))) {
                return false;
            }
        }
        return true;
    }

    public static final ProvidableCompositionLocal<SaveableStateRegistry> getLocalSaveableStateRegistry() {
        return LocalSaveableStateRegistry;
    }
}

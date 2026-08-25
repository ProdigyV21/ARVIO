package androidx.compose.ui;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.common.util.concurrent.r0;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\n\u001a\u00020\t*\u00020\u00062\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "", "areObjectsOfSameType", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "Landroidx/compose/ui/platform/InspectorInfo;", "Landroidx/compose/ui/node/ModifierNodeElement;", "element", "Lx6/t0;", "tryPopulateReflectively", "(Landroidx/compose/ui/platform/InspectorInfo;Landroidx/compose/ui/node/ModifierNodeElement;)V", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Actual_jvmKt {
    public static final boolean areObjectsOfSameType(Object obj, Object obj2) {
        return obj.getClass() == obj2.getClass();
    }

    public static final void tryPopulateReflectively(InspectorInfo inspectorInfo, ModifierNodeElement<?> modifierNodeElement) {
        List listK0 = r.k0(modifierNodeElement.getClass().getDeclaredFields(), new Comparator() { // from class: androidx.compose.ui.Actual_jvmKt$tryPopulateReflectively$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(((Field) t2).getName(), ((Field) t10).getName());
            }
        });
        int size = listK0.size();
        for (int i10 = 0; i10 < size; i10++) {
            Field field = (Field) listK0.get(i10);
            if (!field.getDeclaringClass().isAssignableFrom(ModifierNodeElement.class)) {
                try {
                    field.setAccessible(true);
                    inspectorInfo.getProperties().set(field.getName(), field.get(modifierNodeElement));
                } catch (IllegalAccessException | SecurityException unused) {
                }
            }
        }
    }
}

package androidx.compose.runtime.saveable;

import androidx.compose.runtime.saveable.SaveableStateRegistry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import r7.a;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B9\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0018\u00010\u0002\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\t\u0010\rJ\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00032\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J#\u0010\u0016\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00040\u0002H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R \u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0018R(\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00040\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001aR.\u0010\u001c\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00110\u001b0\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001a¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/saveable/SaveableStateRegistryImpl;", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "", "", "", "", "restored", "Lkotlin/Function1;", "", "canBeSaved", "<init>", "(Ljava/util/Map;Lr7/l;)V", "value", "(Ljava/lang/Object;)Z", "key", "consumeRestored", "(Ljava/lang/String;)Ljava/lang/Object;", "Lkotlin/Function0;", "valueProvider", "Landroidx/compose/runtime/saveable/SaveableStateRegistry$Entry;", "registerProvider", "(Ljava/lang/String;Lr7/a;)Landroidx/compose/runtime/saveable/SaveableStateRegistry$Entry;", "performSave", "()Ljava/util/Map;", "Lr7/l;", "", "Ljava/util/Map;", "", "valueProviders", "runtime-saveable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SaveableStateRegistryImpl implements SaveableStateRegistry {
    private final l<Object, Boolean> canBeSaved;
    private final Map<String, List<Object>> restored;
    private final Map<String, List<a<Object>>> valueProviders;

    public SaveableStateRegistryImpl(Map<String, ? extends List<? extends Object>> map, l<Object, Boolean> lVar) {
        this.canBeSaved = lVar;
        this.restored = map != null ? new LinkedHashMap(map) : new LinkedHashMap();
        this.valueProviders = new LinkedHashMap();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public boolean canBeSaved(Object value) {
        return ((Boolean) this.canBeSaved.invoke(value)).booleanValue();
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Object consumeRestored(String key) {
        List<Object> listRemove = this.restored.remove(key);
        if (listRemove == null || listRemove.isEmpty()) {
            return null;
        }
        if (listRemove.size() > 1) {
            this.restored.put(key, listRemove.subList(1, listRemove.size()));
        }
        return listRemove.get(0);
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public Map<String, List<Object>> performSave() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.restored);
        for (Map.Entry<String, List<a<Object>>> entry : this.valueProviders.entrySet()) {
            String key = entry.getKey();
            List<a<Object>> value = entry.getValue();
            if (value.size() == 1) {
                Object objInvoke = value.get(0).invoke();
                if (objInvoke == null) {
                    continue;
                } else {
                    if (!canBeSaved(objInvoke)) {
                        throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(objInvoke).toString());
                    }
                    linkedHashMap.put(key, t7.a.a(objInvoke));
                }
            } else {
                int size = value.size();
                ArrayList arrayList = new ArrayList(size);
                for (int i10 = 0; i10 < size; i10++) {
                    Object objInvoke2 = value.get(i10).invoke();
                    if (objInvoke2 != null && !canBeSaved(objInvoke2)) {
                        throw new IllegalStateException(RememberSaveableKt.generateCannotBeSavedErrorMessage(objInvoke2).toString());
                    }
                    arrayList.add(objInvoke2);
                }
                linkedHashMap.put(key, arrayList);
            }
        }
        return linkedHashMap;
    }

    @Override // androidx.compose.runtime.saveable.SaveableStateRegistry
    public SaveableStateRegistry.Entry registerProvider(final String key, final a<? extends Object> valueProvider) {
        if (SaveableStateRegistryKt.fastIsBlank(key)) {
            throw new IllegalArgumentException("Registered key is empty or blank");
        }
        Map<String, List<a<Object>>> map = this.valueProviders;
        List<a<Object>> arrayList = map.get(key);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            map.put(key, arrayList);
        }
        arrayList.add(valueProvider);
        return new SaveableStateRegistry.Entry() { // from class: androidx.compose.runtime.saveable.SaveableStateRegistryImpl.registerProvider.3
            @Override // androidx.compose.runtime.saveable.SaveableStateRegistry.Entry
            public void unregister() {
                List list = (List) SaveableStateRegistryImpl.this.valueProviders.remove(key);
                if (list != null) {
                    list.remove(valueProvider);
                }
                if (list == null || list.isEmpty()) {
                    return;
                }
                SaveableStateRegistryImpl.this.valueProviders.put(key, list);
            }
        };
    }
}

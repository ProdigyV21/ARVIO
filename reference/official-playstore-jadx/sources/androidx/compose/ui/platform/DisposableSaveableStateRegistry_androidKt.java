package androidx.compose.ui.platform;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import android.view.View;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import androidx.compose.ui.R;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\n\u001a\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a)\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0012\u0018\u00010\u0011*\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a'\u0010\u0015\u001a\u00020\u0010*\u0016\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00120\u0011H\u0002¢\u0006\u0004\b\u0015\u0010\u0016\"\"\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u00180\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroid/view/View;", "view", "Lb2/g;", "owner", "Landroidx/compose/ui/platform/DisposableSaveableStateRegistry;", "DisposableSaveableStateRegistry", "(Landroid/view/View;Lb2/g;)Landroidx/compose/ui/platform/DisposableSaveableStateRegistry;", "", TtmlNode.ATTR_ID, "savedStateRegistryOwner", "(Ljava/lang/String;Lb2/g;)Landroidx/compose/ui/platform/DisposableSaveableStateRegistry;", "", "value", "", "canBeSavedToBundle", "(Ljava/lang/Object;)Z", "Landroid/os/Bundle;", "", "", "toMap", "(Landroid/os/Bundle;)Ljava/util/Map;", "toBundle", "(Ljava/util/Map;)Landroid/os/Bundle;", "", "Ljava/lang/Class;", "AcceptableClasses", "[Ljava/lang/Class;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DisposableSaveableStateRegistry_androidKt {
    private static final Class<? extends Object>[] AcceptableClasses = {Serializable.class, Parcelable.class, String.class, SparseArray.class, Binder.class, Size.class, SizeF.class};

    /* JADX INFO: renamed from: androidx.compose.ui.platform.DisposableSaveableStateRegistry_androidKt$DisposableSaveableStateRegistry$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.a<t0> {
        final /* synthetic */ b2.e $androidxRegistry;
        final /* synthetic */ String $key;
        final /* synthetic */ boolean $registered;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z, b2.e eVar, String str) {
            super(0);
            this.$registered = z;
            this.$androidxRegistry = eVar;
            this.$key = str;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m4961invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4961invoke() {
            if (this.$registered) {
                b2.e eVar = this.$androidxRegistry;
                eVar.f7114a.b(this.$key);
            }
        }
    }

    public static final DisposableSaveableStateRegistry DisposableSaveableStateRegistry(View view, b2.g gVar) {
        View view2 = (View) view.getParent();
        Object tag = view2.getTag(R.id.compose_view_saveable_id_tag);
        String strValueOf = tag instanceof String ? (String) tag : null;
        if (strValueOf == null) {
            strValueOf = String.valueOf(view2.getId());
        }
        return DisposableSaveableStateRegistry(strValueOf, gVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle DisposableSaveableStateRegistry$lambda$0(SaveableStateRegistry saveableStateRegistry) {
        return toBundle(saveableStateRegistry.performSave());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean canBeSavedToBundle(Object obj) {
        if (obj instanceof SnapshotMutableState) {
            SnapshotMutableState snapshotMutableState = (SnapshotMutableState) obj;
            if (snapshotMutableState.getPolicy() != SnapshotStateKt.neverEqualPolicy() && snapshotMutableState.getPolicy() != SnapshotStateKt.structuralEqualityPolicy() && snapshotMutableState.getPolicy() != SnapshotStateKt.referentialEqualityPolicy()) {
                return false;
            }
            T value = snapshotMutableState.getValue();
            if (value == 0) {
                return true;
            }
            return canBeSavedToBundle(value);
        }
        if ((obj instanceof x6.o) && (obj instanceof Serializable)) {
            return false;
        }
        for (Class<? extends Object> cls : AcceptableClasses) {
            if (cls.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    private static final Bundle toBundle(Map<String, ? extends List<? extends Object>> map) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, ? extends List<? extends Object>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<? extends Object> value = entry.getValue();
            bundle.putParcelableArrayList(key, value instanceof ArrayList ? (ArrayList) value : new ArrayList<>(value));
        }
        return bundle;
    }

    private static final Map<String, List<Object>> toMap(Bundle bundle) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : bundle.keySet()) {
            linkedHashMap.put(str, bundle.getParcelableArrayList(str));
        }
        return linkedHashMap;
    }

    public static final DisposableSaveableStateRegistry DisposableSaveableStateRegistry(String str, b2.g gVar) {
        boolean z;
        String strC = androidx.compose.material3.d.C("SaveableStateRegistry:", str);
        b2.e savedStateRegistry = gVar.getSavedStateRegistry();
        Bundle bundleA = savedStateRegistry.a(strC);
        final SaveableStateRegistry SaveableStateRegistry = SaveableStateRegistryKt.SaveableStateRegistry(bundleA != null ? toMap(bundleA) : null, DisposableSaveableStateRegistry_androidKt$DisposableSaveableStateRegistry$saveableStateRegistry$1.INSTANCE);
        try {
            savedStateRegistry.c(strC, new b2.d() { // from class: androidx.compose.ui.platform.k
                @Override // b2.d
                public final Bundle a() {
                    return DisposableSaveableStateRegistry_androidKt.DisposableSaveableStateRegistry$lambda$0(SaveableStateRegistry);
                }
            });
            z = true;
        } catch (IllegalArgumentException unused) {
            z = false;
        }
        return new DisposableSaveableStateRegistry(SaveableStateRegistry, new AnonymousClass1(z, savedStateRegistry, strC));
    }
}

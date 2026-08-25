package androidx.navigation.compose;

import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.lifecycle.d1;
import androidx.lifecycle.w0;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.UUID;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R(\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/navigation/compose/BackStackEntryIdViewModel;", "Landroidx/lifecycle/d1;", "Landroidx/lifecycle/w0;", "handle", "<init>", "(Landroidx/lifecycle/w0;)V", "Lx6/t0;", "onCleared", "()V", "", "IdKey", "Ljava/lang/String;", "Ljava/util/UUID;", TtmlNode.ATTR_ID, "Ljava/util/UUID;", "getId", "()Ljava/util/UUID;", "Ljava/lang/ref/WeakReference;", "Landroidx/compose/runtime/saveable/SaveableStateHolder;", "saveableStateHolderRef", "Ljava/lang/ref/WeakReference;", "getSaveableStateHolderRef", "()Ljava/lang/ref/WeakReference;", "setSaveableStateHolderRef", "(Ljava/lang/ref/WeakReference;)V", "navigation-compose_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BackStackEntryIdViewModel extends d1 {
    private final String IdKey = "SaveableStateHolder_BackStackEntryKey";
    private final UUID id;
    public WeakReference<SaveableStateHolder> saveableStateHolderRef;

    public BackStackEntryIdViewModel(w0 w0Var) {
        Object obj;
        LinkedHashMap linkedHashMap = w0Var.f3304a;
        try {
            obj = linkedHashMap.get("SaveableStateHolder_BackStackEntryKey");
        } catch (ClassCastException unused) {
            linkedHashMap.remove("SaveableStateHolder_BackStackEntryKey");
            if (w0Var.f3306c.remove("SaveableStateHolder_BackStackEntryKey") != null) {
                throw new ClassCastException();
            }
            w0Var.f3307d.remove("SaveableStateHolder_BackStackEntryKey");
            obj = null;
        }
        UUID uuidRandomUUID = (UUID) obj;
        if (uuidRandomUUID == null) {
            uuidRandomUUID = UUID.randomUUID();
            w0Var.b(this.IdKey, uuidRandomUUID);
        }
        this.id = uuidRandomUUID;
    }

    public final UUID getId() {
        return this.id;
    }

    public final WeakReference<SaveableStateHolder> getSaveableStateHolderRef() {
        WeakReference<SaveableStateHolder> weakReference = this.saveableStateHolderRef;
        if (weakReference != null) {
            return weakReference;
        }
        kotlin.jvm.internal.p.i("saveableStateHolderRef");
        throw null;
    }

    @Override // androidx.lifecycle.d1
    public void onCleared() {
        super.onCleared();
        SaveableStateHolder saveableStateHolder = getSaveableStateHolderRef().get();
        if (saveableStateHolder != null) {
            saveableStateHolder.removeState(this.id);
        }
        getSaveableStateHolderRef().clear();
    }

    public final void setSaveableStateHolderRef(WeakReference<SaveableStateHolder> weakReference) {
        this.saveableStateHolderRef = weakReference;
    }
}

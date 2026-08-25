package androidx.compose.ui.node;

import android.view.View;
import android.view.ViewGroup;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\t\u001a\u00028\u0000\"\b\b\u0000\u0010\u0004*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0086\b¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u001f\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0096D¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/node/MergedViewAdapter;", "Landroidx/compose/ui/node/ViewAdapter;", "<init>", "()V", "T", "", TtmlNode.ATTR_ID, "Lkotlin/Function0;", "factory", "get", "(ILr7/a;)Landroidx/compose/ui/node/ViewAdapter;", "Landroid/view/View;", "view", "Landroid/view/ViewGroup;", "parent", "Lx6/t0;", "willInsert", "(Landroid/view/View;Landroid/view/ViewGroup;)V", "didInsert", "didUpdate", "I", "getId", "()I", "", "adapters", "Ljava/util/List;", "getAdapters", "()Ljava/util/List;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MergedViewAdapter implements ViewAdapter {
    public static final int $stable = 8;
    private final List<ViewAdapter> adapters = new ArrayList();
    private final int id;

    @Override // androidx.compose.ui.node.ViewAdapter
    public void didInsert(View view, ViewGroup parent) {
        List<ViewAdapter> list = this.adapters;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            list.get(i10).didInsert(view, parent);
        }
    }

    @Override // androidx.compose.ui.node.ViewAdapter
    public void didUpdate(View view, ViewGroup parent) {
        List<ViewAdapter> list = this.adapters;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            list.get(i10).didUpdate(view, parent);
        }
    }

    public final <T extends ViewAdapter> T get(int id, r7.a<? extends T> factory) {
        ViewAdapter viewAdapter;
        List<ViewAdapter> adapters = getAdapters();
        int size = adapters.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                viewAdapter = null;
                break;
            }
            viewAdapter = adapters.get(i10);
            if (viewAdapter.getId() == id) {
                break;
            }
            i10++;
        }
        T t2 = viewAdapter instanceof ViewAdapter ? (T) viewAdapter : null;
        if (t2 != null) {
            return t2;
        }
        T t10 = (T) factory.invoke();
        getAdapters().add(t10);
        return t10;
    }

    public final List<ViewAdapter> getAdapters() {
        return this.adapters;
    }

    @Override // androidx.compose.ui.node.ViewAdapter
    public int getId() {
        return this.id;
    }

    @Override // androidx.compose.ui.node.ViewAdapter
    public void willInsert(View view, ViewGroup parent) {
        List<ViewAdapter> list = this.adapters;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            list.get(i10).willInsert(view, parent);
        }
    }
}

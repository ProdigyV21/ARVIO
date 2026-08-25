package androidx.core.view;

import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.arvio.tv.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class a2 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ArrayList f2193d = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakHashMap f2194a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SparseArray f2195b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public WeakReference f2196c;

    public static boolean b(View view, KeyEvent keyEvent) {
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
        if (arrayList == null) {
            return false;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((x1) arrayList.get(size)).a()) {
                return true;
            }
        }
        return false;
    }

    public final View a(View view, KeyEvent keyEvent) {
        WeakHashMap weakHashMap = this.f2194a;
        if (weakHashMap == null || !weakHashMap.containsKey(view)) {
            return null;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View viewA = a(viewGroup.getChildAt(childCount), keyEvent);
                if (viewA != null) {
                    return viewA;
                }
            }
        }
        if (b(view, keyEvent)) {
            return view;
        }
        return null;
    }
}

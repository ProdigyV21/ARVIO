package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
final class OverlayListView extends ListView {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f4272i;

    public OverlayListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4272i = new ArrayList();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ArrayList arrayList = this.f4272i;
        if (arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            if (it.hasNext()) {
                it.next().getClass();
                throw new ClassCastException();
            }
        }
    }
}

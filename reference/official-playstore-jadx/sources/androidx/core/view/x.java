package androidx.core.view;

import android.content.Context;
import android.view.GestureDetector;

/* JADX INFO: loaded from: classes3.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final GestureDetector f2332a;

    public x(Context context, GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
        this.f2332a = new GestureDetector(context, simpleOnGestureListener, null);
    }
}

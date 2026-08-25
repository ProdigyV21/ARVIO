package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public class AlertController$RecycleListView extends ListView {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1028i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f1029l;

    public AlertController$RecycleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.a.f19035t);
        this.f1029l = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, -1);
        this.f1028i = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, -1);
    }
}

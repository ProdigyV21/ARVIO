package androidx.appcompat.widget;

import android.text.StaticLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class t1 extends s1 {
    @Override // androidx.appcompat.widget.s1, androidx.appcompat.widget.u1
    public void a(StaticLayout.Builder builder, TextView textView) {
        builder.setTextDirection(textView.getTextDirectionHeuristic());
    }

    @Override // androidx.appcompat.widget.u1
    public boolean b(TextView textView) {
        return textView.isHorizontallyScrollable();
    }
}

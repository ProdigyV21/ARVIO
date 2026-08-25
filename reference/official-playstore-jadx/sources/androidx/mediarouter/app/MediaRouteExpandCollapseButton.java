package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.e0;
import com.arvio.tv.R;
import com.google.common.util.concurrent.p0;

/* JADX INFO: loaded from: classes3.dex */
class MediaRouteExpandCollapseButton extends e0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AnimationDrawable f4264n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final AnimationDrawable f4265o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f4266p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f4267q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f4268r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public View.OnClickListener f4269s;

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        AnimationDrawable animationDrawable = (AnimationDrawable) androidx.core.content.a.b(context, R.drawable.mr_group_expand);
        this.f4264n = animationDrawable;
        AnimationDrawable animationDrawable2 = (AnimationDrawable) androidx.core.content.a.b(context, R.drawable.mr_group_collapse);
        this.f4265o = animationDrawable2;
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(p0.m(context), PorterDuff.Mode.SRC_IN);
        animationDrawable.setColorFilter(porterDuffColorFilter);
        animationDrawable2.setColorFilter(porterDuffColorFilter);
        String string = context.getString(R.string.mr_controller_expand_group);
        this.f4266p = string;
        this.f4267q = context.getString(R.string.mr_controller_collapse_group);
        setImageDrawable(animationDrawable.getFrame(0));
        setContentDescription(string);
        super.setOnClickListener(new i(this));
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.f4269s = onClickListener;
    }
}

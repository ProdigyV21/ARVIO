package androidx.leanback.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public class TitleView extends FrameLayout implements o1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ImageView f3052i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final TextView f3053l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SearchOrbView f3054m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f3055n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f3056o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final n1 f3057p;

    public TitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.browseTitleViewStyle);
        this.f3055n = 6;
        this.f3056o = false;
        this.f3057p = new n1();
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.lb_title_view, this);
        this.f3052i = (ImageView) viewInflate.findViewById(R.id.title_badge);
        this.f3053l = (TextView) viewInflate.findViewById(R.id.title_text);
        this.f3054m = (SearchOrbView) viewInflate.findViewById(R.id.title_orb);
        setClipToPadding(false);
        setClipChildren(false);
    }

    public Drawable getBadgeDrawable() {
        return this.f3052i.getDrawable();
    }

    public f1 getSearchAffordanceColors() {
        return this.f3054m.getOrbColors();
    }

    public View getSearchAffordanceView() {
        return this.f3054m;
    }

    public CharSequence getTitle() {
        return this.f3053l.getText();
    }

    public p1 getTitleViewAdapter() {
        return this.f3057p;
    }

    public void setBadgeDrawable(Drawable drawable) {
        ImageView imageView = this.f3052i;
        imageView.setImageDrawable(drawable);
        Drawable drawable2 = imageView.getDrawable();
        TextView textView = this.f3053l;
        if (drawable2 != null) {
            imageView.setVisibility(0);
            textView.setVisibility(8);
        } else {
            imageView.setVisibility(8);
            textView.setVisibility(0);
        }
    }

    public void setOnSearchClickedListener(View.OnClickListener onClickListener) {
        this.f3056o = onClickListener != null;
        SearchOrbView searchOrbView = this.f3054m;
        searchOrbView.setOnOrbClickedListener(onClickListener);
        searchOrbView.setVisibility((this.f3056o && (this.f3055n & 4) == 4) ? 0 : 4);
    }

    public void setSearchAffordanceColors(f1 f1Var) {
        this.f3054m.setOrbColors(f1Var);
    }

    public void setTitle(CharSequence charSequence) {
        TextView textView = this.f3053l;
        textView.setText(charSequence);
        ImageView imageView = this.f3052i;
        if (imageView.getDrawable() != null) {
            imageView.setVisibility(0);
            textView.setVisibility(8);
        } else {
            imageView.setVisibility(8);
            textView.setVisibility(0);
        }
    }
}

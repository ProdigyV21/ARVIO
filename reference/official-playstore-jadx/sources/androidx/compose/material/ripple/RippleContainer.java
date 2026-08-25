package androidx.compose.material.ripple;

import android.content.Context;
import android.view.ViewGroup;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import t7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J7\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0014¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0014¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0016\u001a\u00020\u0015*\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u00020\r*\u00020\u0014¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00150\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00150\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010\u001b¨\u0006$"}, d2 = {"Landroidx/compose/material/ripple/RippleContainer;", "Landroid/view/ViewGroup;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "changed", "", CmcdData.STREAM_TYPE_LIVE, "t", "r", "b", "Lx6/t0;", "onLayout", "(ZIIII)V", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "(II)V", "Landroidx/compose/material/ripple/AndroidRippleIndicationInstance;", "Landroidx/compose/material/ripple/RippleHostView;", "getRippleHostView", "(Landroidx/compose/material/ripple/AndroidRippleIndicationInstance;)Landroidx/compose/material/ripple/RippleHostView;", "disposeRippleIfNeeded", "(Landroidx/compose/material/ripple/AndroidRippleIndicationInstance;)V", "MaxRippleHosts", "I", "", "rippleHosts", "Ljava/util/List;", "unusedRippleHosts", "Landroidx/compose/material/ripple/RippleHostMap;", "rippleHostMap", "Landroidx/compose/material/ripple/RippleHostMap;", "nextHostIndex", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RippleContainer extends ViewGroup {
    public static final int $stable = 8;
    private final int MaxRippleHosts;
    private int nextHostIndex;
    private final RippleHostMap rippleHostMap;
    private final List<RippleHostView> rippleHosts;
    private final List<RippleHostView> unusedRippleHosts;

    public RippleContainer(Context context) {
        super(context);
        this.MaxRippleHosts = 5;
        ArrayList arrayList = new ArrayList();
        this.rippleHosts = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.unusedRippleHosts = arrayList2;
        this.rippleHostMap = new RippleHostMap();
        setClipChildren(false);
        RippleHostView rippleHostView = new RippleHostView(context);
        addView(rippleHostView);
        arrayList.add(rippleHostView);
        arrayList2.add(rippleHostView);
        this.nextHostIndex = 1;
        setTag(androidx.compose.ui.R.id.hide_in_inspector_tag, Boolean.TRUE);
    }

    public final void disposeRippleIfNeeded(AndroidRippleIndicationInstance androidRippleIndicationInstance) {
        androidRippleIndicationInstance.resetHostView();
        RippleHostView rippleHostView = this.rippleHostMap.get(androidRippleIndicationInstance);
        if (rippleHostView != null) {
            rippleHostView.disposeRipple();
            this.rippleHostMap.remove(androidRippleIndicationInstance);
            this.unusedRippleHosts.add(rippleHostView);
        }
    }

    public final RippleHostView getRippleHostView(AndroidRippleIndicationInstance androidRippleIndicationInstance) {
        RippleHostView rippleHostView = this.rippleHostMap.get(androidRippleIndicationInstance);
        if (rippleHostView != null) {
            return rippleHostView;
        }
        List<RippleHostView> list = this.unusedRippleHosts;
        RippleHostView rippleHostViewRemove = list.isEmpty() ? null : list.remove(0);
        if (rippleHostViewRemove == null) {
            if (this.nextHostIndex > a.w(this.rippleHosts)) {
                rippleHostViewRemove = new RippleHostView(getContext());
                addView(rippleHostViewRemove);
                this.rippleHosts.add(rippleHostViewRemove);
            } else {
                rippleHostViewRemove = this.rippleHosts.get(this.nextHostIndex);
                AndroidRippleIndicationInstance androidRippleIndicationInstance2 = this.rippleHostMap.get(rippleHostViewRemove);
                if (androidRippleIndicationInstance2 != null) {
                    androidRippleIndicationInstance2.resetHostView();
                    this.rippleHostMap.remove(androidRippleIndicationInstance2);
                    rippleHostViewRemove.disposeRipple();
                }
            }
            int i10 = this.nextHostIndex;
            if (i10 < this.MaxRippleHosts - 1) {
                this.nextHostIndex = i10 + 1;
            } else {
                this.nextHostIndex = 0;
            }
        }
        this.rippleHostMap.set(androidRippleIndicationInstance, rippleHostViewRemove);
        return rippleHostViewRemove;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int l10, int t2, int r4, int b10) {
    }

    @Override // android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(0, 0);
    }
}

package androidx.mediarouter.app;

import android.content.Context;
import android.os.SystemClock;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.m;
import androidx.appcompat.app.n0;
import androidx.appcompat.app.u0;
import androidx.mediarouter.media.i0;
import androidx.mediarouter.media.k0;
import androidx.mediarouter.media.z;
import com.arvio.tv.R;
import com.google.common.util.concurrent.p0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class g extends u0 {
    public final n0 A;
    public boolean B;
    public long C;
    public final m D;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k0 f4283m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d f4284n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public z f4285o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f4286p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public TextView f4287q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public TextView f4288r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public RelativeLayout f4289s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public TextView f4290t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public TextView f4291u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public LinearLayout f4292v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Button f4293w;
    public ProgressBar x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ListView f4294y;
    public e z;

    public g(Context context) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, p0.q(context, R.attr.dialogTheme));
        contextThemeWrapper = p0.q(contextThemeWrapper, R.attr.mediaRouteTheme) != 0 ? new ContextThemeWrapper(contextThemeWrapper, p0.p(contextThemeWrapper)) : contextThemeWrapper;
        int iQ = p0.q(contextThemeWrapper, R.attr.mediaRouteTheme);
        super(contextThemeWrapper, iQ == 0 ? p0.p(contextThemeWrapper) : iQ);
        this.f4285o = z.f4516c;
        this.D = new m(this);
        this.f4283m = k0.d(getContext());
        this.f4284n = new d(this, 0);
        this.A = new n0(this, 1);
    }

    public final void d(List list) {
        this.C = SystemClock.uptimeMillis();
        this.f4286p.clear();
        this.f4286p.addAll(list);
        this.z.notifyDataSetChanged();
        m mVar = this.D;
        mVar.removeMessages(3);
        mVar.removeMessages(2);
        if (!list.isEmpty()) {
            f(1);
        } else {
            f(0);
            mVar.sendMessageDelayed(mVar.obtainMessage(2), 5000L);
        }
    }

    @Override // androidx.appcompat.app.u0, android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        try {
            getContext().unregisterReceiver(this.A);
        } catch (IllegalArgumentException unused) {
        }
        super.dismiss();
    }

    public final void e() {
        if (this.B) {
            this.f4283m.getClass();
            k0.b();
            ArrayList arrayList = new ArrayList(k0.c().f4335j);
            int size = arrayList.size();
            while (true) {
                int i10 = size - 1;
                if (size <= 0) {
                    break;
                }
                i0 i0Var = (i0) arrayList.get(i10);
                if (i0Var.c() || !i0Var.f4392g || !i0Var.e(this.f4285o)) {
                    arrayList.remove(i10);
                }
                size = i10;
            }
            Collections.sort(arrayList, f.f4282i);
            if (SystemClock.uptimeMillis() - this.C >= 300) {
                d(arrayList);
                return;
            }
            m mVar = this.D;
            mVar.removeMessages(1);
            mVar.sendMessageAtTime(mVar.obtainMessage(1, arrayList), this.C + 300);
        }
    }

    public final void f(int i10) {
        if (i10 == 0) {
            setTitle(R.string.mr_chooser_title);
            this.f4294y.setVisibility(8);
            this.f4288r.setVisibility(0);
            this.x.setVisibility(0);
            this.f4292v.setVisibility(8);
            this.f4293w.setVisibility(8);
            this.f4291u.setVisibility(8);
            this.f4289s.setVisibility(8);
            return;
        }
        if (i10 == 1) {
            setTitle(R.string.mr_chooser_title);
            this.f4294y.setVisibility(0);
            this.f4288r.setVisibility(8);
            this.x.setVisibility(8);
            this.f4292v.setVisibility(8);
            this.f4293w.setVisibility(8);
            this.f4291u.setVisibility(8);
            this.f4289s.setVisibility(8);
            return;
        }
        if (i10 == 2) {
            setTitle(R.string.mr_chooser_title);
            this.f4294y.setVisibility(8);
            this.f4288r.setVisibility(8);
            this.x.setVisibility(0);
            this.f4292v.setVisibility(8);
            this.f4293w.setVisibility(8);
            this.f4291u.setVisibility(4);
            this.f4289s.setVisibility(0);
            return;
        }
        if (i10 != 3) {
            return;
        }
        setTitle(R.string.mr_chooser_zero_routes_found_title);
        this.f4294y.setVisibility(8);
        this.f4288r.setVisibility(8);
        this.x.setVisibility(8);
        this.f4292v.setVisibility(0);
        this.f4293w.setVisibility(0);
        this.f4291u.setVisibility(0);
        this.f4289s.setVisibility(0);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.B = true;
        this.f4283m.a(this.f4285o, this.f4284n, 1);
        e();
        m mVar = this.D;
        mVar.removeMessages(2);
        mVar.removeMessages(3);
        mVar.removeMessages(1);
        mVar.sendMessageDelayed(mVar.obtainMessage(2), 5000L);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x014b  */
    @Override // androidx.appcompat.app.u0, androidx.activity.u, android.app.Dialog
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCreate(android.os.Bundle r7) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.g.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onDetachedFromWindow() {
        this.B = false;
        this.f4283m.e(this.f4284n);
        m mVar = this.D;
        mVar.removeMessages(1);
        mVar.removeMessages(2);
        mVar.removeMessages(3);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.u0, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        this.f4287q.setText(charSequence);
    }

    @Override // androidx.appcompat.app.u0, android.app.Dialog
    public final void setTitle(int i10) {
        this.f4287q.setText(i10);
    }
}

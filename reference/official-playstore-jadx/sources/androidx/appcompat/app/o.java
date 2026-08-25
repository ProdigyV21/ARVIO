package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import com.arvio.tv.R;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    public final int A;
    public final int B;
    public final boolean C;
    public final m D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1122a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f1123b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Window f1124c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f1125d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AlertController$RecycleListView f1126e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f1127f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Button f1129h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f1130i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Message f1131j;
    public Button k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public CharSequence f1132l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Message f1133m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Button f1134n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CharSequence f1135o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Message f1136p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public NestedScrollView f1137q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Drawable f1138r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ImageView f1139s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public TextView f1140t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public TextView f1141u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public View f1142v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ListAdapter f1143w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f1144y;
    public final int z;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1128g = false;
    public int x = -1;
    public final i E = new i(this);

    public o(Context context, q qVar, Window window) {
        this.f1122a = context;
        this.f1123b = qVar;
        this.f1124c = window;
        m mVar = new m();
        mVar.f1116b = new WeakReference(qVar);
        this.D = mVar;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, j.a.f19021e, R.attr.alertDialogStyle, 0);
        this.f1144y = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.getResourceId(2, 0);
        this.z = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        typedArrayObtainStyledAttributes.getResourceId(5, 0);
        this.A = typedArrayObtainStyledAttributes.getResourceId(7, 0);
        this.B = typedArrayObtainStyledAttributes.getResourceId(3, 0);
        this.C = typedArrayObtainStyledAttributes.getBoolean(6, true);
        typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        qVar.b().i(1);
    }

    public static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public static ViewGroup b(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    public final void c(int i10, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        Message messageObtainMessage = onClickListener != null ? this.D.obtainMessage(i10, onClickListener) : null;
        if (i10 == -3) {
            this.f1135o = charSequence;
            this.f1136p = messageObtainMessage;
        } else if (i10 == -2) {
            this.f1132l = charSequence;
            this.f1133m = messageObtainMessage;
        } else {
            if (i10 != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.f1130i = charSequence;
            this.f1131j = messageObtainMessage;
        }
    }
}

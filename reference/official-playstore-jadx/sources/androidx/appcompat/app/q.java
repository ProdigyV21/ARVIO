package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.m2;
import androidx.core.view.b2;
import androidx.core.widget.NestedScrollView;
import com.arvio.tv.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class q extends u0 implements DialogInterface {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final o f1150m;

    public q(ContextThemeWrapper contextThemeWrapper, int i10) {
        super(contextThemeWrapper, d(contextThemeWrapper, i10));
        this.f1150m = new o(getContext(), this, getWindow());
    }

    public static int d(Context context, int i10) {
        if (((i10 >>> 24) & 255) >= 1) {
            return i10;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // androidx.appcompat.app.u0, androidx.activity.u, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        int i10;
        ListAdapter listAdapter;
        View viewFindViewById;
        super.onCreate(bundle);
        o oVar = this.f1150m;
        oVar.f1123b.setContentView(oVar.f1144y);
        Context context = oVar.f1122a;
        Window window = oVar.f1124c;
        View viewFindViewById2 = window.findViewById(R.id.parentPanel);
        View viewFindViewById3 = viewFindViewById2.findViewById(R.id.topPanel);
        View viewFindViewById4 = viewFindViewById2.findViewById(R.id.contentPanel);
        View viewFindViewById5 = viewFindViewById2.findViewById(R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById2.findViewById(R.id.customPanel);
        View view = oVar.f1127f;
        if (view == null) {
            view = null;
        }
        boolean z = view != null;
        if (!z || !o.a(view)) {
            window.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) window.findViewById(R.id.custom);
            frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
            if (oVar.f1128g) {
                frameLayout.setPadding(0, 0, 0, 0);
            }
            if (oVar.f1126e != null) {
                ((LinearLayout.LayoutParams) ((m2) viewGroup.getLayoutParams())).weight = 0.0f;
            }
        } else {
            viewGroup.setVisibility(8);
        }
        View viewFindViewById6 = viewGroup.findViewById(R.id.topPanel);
        View viewFindViewById7 = viewGroup.findViewById(R.id.contentPanel);
        View viewFindViewById8 = viewGroup.findViewById(R.id.buttonPanel);
        ViewGroup viewGroupB = o.b(viewFindViewById6, viewFindViewById3);
        ViewGroup viewGroupB2 = o.b(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupB3 = o.b(viewFindViewById8, viewFindViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) window.findViewById(R.id.scrollView);
        oVar.f1137q = nestedScrollView;
        nestedScrollView.setFocusable(false);
        oVar.f1137q.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroupB2.findViewById(android.R.id.message);
        oVar.f1141u = textView;
        if (textView != null) {
            textView.setVisibility(8);
            oVar.f1137q.removeView(oVar.f1141u);
            if (oVar.f1126e != null) {
                ViewGroup viewGroup2 = (ViewGroup) oVar.f1137q.getParent();
                int iIndexOfChild = viewGroup2.indexOfChild(oVar.f1137q);
                viewGroup2.removeViewAt(iIndexOfChild);
                viewGroup2.addView(oVar.f1126e, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
            } else {
                viewGroupB2.setVisibility(8);
            }
        }
        Button button = (Button) viewGroupB3.findViewById(android.R.id.button1);
        oVar.f1129h = button;
        i iVar = oVar.E;
        button.setOnClickListener(iVar);
        if (TextUtils.isEmpty(oVar.f1130i)) {
            oVar.f1129h.setVisibility(8);
            i10 = 0;
        } else {
            oVar.f1129h.setText(oVar.f1130i);
            oVar.f1129h.setVisibility(0);
            i10 = 1;
        }
        Button button2 = (Button) viewGroupB3.findViewById(android.R.id.button2);
        oVar.k = button2;
        button2.setOnClickListener(iVar);
        if (TextUtils.isEmpty(oVar.f1132l)) {
            oVar.k.setVisibility(8);
        } else {
            oVar.k.setText(oVar.f1132l);
            oVar.k.setVisibility(0);
            i10 |= 2;
        }
        Button button3 = (Button) viewGroupB3.findViewById(android.R.id.button3);
        oVar.f1134n = button3;
        button3.setOnClickListener(iVar);
        if (TextUtils.isEmpty(oVar.f1135o)) {
            oVar.f1134n.setVisibility(8);
        } else {
            oVar.f1134n.setText(oVar.f1135o);
            oVar.f1134n.setVisibility(0);
            i10 |= 4;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i10 == 1) {
                Button button4 = oVar.f1129h;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button4.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.weight = 0.5f;
                button4.setLayoutParams(layoutParams);
            } else if (i10 == 2) {
                Button button5 = oVar.k;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button5.getLayoutParams();
                layoutParams2.gravity = 1;
                layoutParams2.weight = 0.5f;
                button5.setLayoutParams(layoutParams2);
            } else if (i10 == 4) {
                Button button6 = oVar.f1134n;
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button6.getLayoutParams();
                layoutParams3.gravity = 1;
                layoutParams3.weight = 0.5f;
                button6.setLayoutParams(layoutParams3);
            }
        }
        if (i10 == 0) {
            viewGroupB3.setVisibility(8);
        }
        if (oVar.f1142v != null) {
            viewGroupB.addView(oVar.f1142v, 0, new ViewGroup.LayoutParams(-1, -2));
            window.findViewById(R.id.title_template).setVisibility(8);
        } else {
            oVar.f1139s = (ImageView) window.findViewById(android.R.id.icon);
            if (TextUtils.isEmpty(oVar.f1125d) || !oVar.C) {
                window.findViewById(R.id.title_template).setVisibility(8);
                oVar.f1139s.setVisibility(8);
                viewGroupB.setVisibility(8);
            } else {
                TextView textView2 = (TextView) window.findViewById(R.id.alertTitle);
                oVar.f1140t = textView2;
                textView2.setText(oVar.f1125d);
                Drawable drawable = oVar.f1138r;
                if (drawable != null) {
                    oVar.f1139s.setImageDrawable(drawable);
                } else {
                    oVar.f1140t.setPadding(oVar.f1139s.getPaddingLeft(), oVar.f1139s.getPaddingTop(), oVar.f1139s.getPaddingRight(), oVar.f1139s.getPaddingBottom());
                    oVar.f1139s.setVisibility(8);
                }
            }
        }
        boolean z5 = viewGroup.getVisibility() != 8;
        int i11 = (viewGroupB == null || viewGroupB.getVisibility() == 8) ? 0 : 1;
        boolean z10 = viewGroupB3.getVisibility() != 8;
        if (!z10 && (viewFindViewById = viewGroupB2.findViewById(R.id.textSpacerNoButtons)) != null) {
            viewFindViewById.setVisibility(0);
        }
        if (i11 != 0) {
            NestedScrollView nestedScrollView2 = oVar.f1137q;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            View viewFindViewById9 = oVar.f1126e != null ? viewGroupB.findViewById(R.id.titleDividerNoCustom) : null;
            if (viewFindViewById9 != null) {
                viewFindViewById9.setVisibility(0);
            }
        } else {
            View viewFindViewById10 = viewGroupB2.findViewById(R.id.textSpacerNoTitle);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        }
        AlertController$RecycleListView alertController$RecycleListView = oVar.f1126e;
        if (alertController$RecycleListView != null) {
            alertController$RecycleListView.getClass();
            if (!z10 || i11 == 0) {
                alertController$RecycleListView.setPadding(alertController$RecycleListView.getPaddingLeft(), i11 != 0 ? alertController$RecycleListView.getPaddingTop() : alertController$RecycleListView.f1028i, alertController$RecycleListView.getPaddingRight(), z10 ? alertController$RecycleListView.getPaddingBottom() : alertController$RecycleListView.f1029l);
            }
        }
        if (!z5) {
            View view2 = oVar.f1126e;
            if (view2 == null) {
                view2 = oVar.f1137q;
            }
            if (view2 != null) {
                int i12 = z10 ? 2 : 0;
                View viewFindViewById11 = window.findViewById(R.id.scrollIndicatorUp);
                View viewFindViewById12 = window.findViewById(R.id.scrollIndicatorDown);
                WeakHashMap weakHashMap = b2.f2200a;
                androidx.core.view.l1.d(view2, i11 | i12, 3);
                if (viewFindViewById11 != null) {
                    viewGroupB2.removeView(viewFindViewById11);
                }
                if (viewFindViewById12 != null) {
                    viewGroupB2.removeView(viewFindViewById12);
                }
            }
        }
        AlertController$RecycleListView alertController$RecycleListView2 = oVar.f1126e;
        if (alertController$RecycleListView2 == null || (listAdapter = oVar.f1143w) == null) {
            return;
        }
        alertController$RecycleListView2.setAdapter(listAdapter);
        int i13 = oVar.x;
        if (i13 > -1) {
            alertController$RecycleListView2.setItemChecked(i13, true);
            alertController$RecycleListView2.setSelection(i13);
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i10, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f1150m.f1137q;
        if (nestedScrollView == null || !nestedScrollView.d(keyEvent)) {
            return super.onKeyDown(i10, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i10, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f1150m.f1137q;
        if (nestedScrollView == null || !nestedScrollView.d(keyEvent)) {
            return super.onKeyUp(i10, keyEvent);
        }
        return true;
    }

    @Override // androidx.appcompat.app.u0, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        o oVar = this.f1150m;
        oVar.f1125d = charSequence;
        TextView textView = oVar.f1140t;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}

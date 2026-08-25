package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f1147a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1148b;

    public p(Context context) {
        this(context, q.d(context, 0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.widget.ListAdapter] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    public q create() {
        l lVar = this.f1147a;
        q qVar = new q(lVar.f1072a, this.f1148b);
        View view = lVar.f1076e;
        o oVar = qVar.f1150m;
        if (view != null) {
            oVar.f1142v = view;
        } else {
            CharSequence charSequence = lVar.f1075d;
            if (charSequence != null) {
                oVar.f1125d = charSequence;
                TextView textView = oVar.f1140t;
                if (textView != null) {
                    textView.setText(charSequence);
                }
            }
            Drawable drawable = lVar.f1074c;
            if (drawable != null) {
                oVar.f1138r = drawable;
                ImageView imageView = oVar.f1139s;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    oVar.f1139s.setImageDrawable(drawable);
                }
            }
        }
        CharSequence charSequence2 = lVar.f1077f;
        if (charSequence2 != null) {
            oVar.c(-1, charSequence2, lVar.f1078g);
        }
        CharSequence charSequence3 = lVar.f1079h;
        if (charSequence3 != null) {
            oVar.c(-2, charSequence3, lVar.f1080i);
        }
        if (lVar.k != null) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) lVar.f1073b.inflate(oVar.z, (ViewGroup) null);
            int i10 = lVar.f1084n ? oVar.A : oVar.B;
            Object obj = lVar.k;
            ?? nVar = obj;
            if (obj == null) {
                nVar = new n(lVar.f1072a, i10, R.id.text1, null);
            }
            oVar.f1143w = nVar;
            oVar.x = lVar.f1085o;
            if (lVar.f1082l != null) {
                alertController$RecycleListView.setOnItemClickListener(new j(lVar, oVar));
            }
            if (lVar.f1084n) {
                alertController$RecycleListView.setChoiceMode(1);
            }
            oVar.f1126e = alertController$RecycleListView;
        }
        View view2 = lVar.f1083m;
        if (view2 != null) {
            oVar.f1127f = view2;
            oVar.f1128g = false;
        }
        qVar.setCancelable(true);
        qVar.setCanceledOnTouchOutside(true);
        qVar.setOnCancelListener(null);
        qVar.setOnDismissListener(null);
        androidx.appcompat.view.menu.r rVar = lVar.f1081j;
        if (rVar != null) {
            qVar.setOnKeyListener(rVar);
        }
        return qVar;
    }

    public Context getContext() {
        return this.f1147a.f1072a;
    }

    public p setNegativeButton(int i10, DialogInterface.OnClickListener onClickListener) {
        l lVar = this.f1147a;
        lVar.f1079h = lVar.f1072a.getText(i10);
        lVar.f1080i = onClickListener;
        return this;
    }

    public p setPositiveButton(int i10, DialogInterface.OnClickListener onClickListener) {
        l lVar = this.f1147a;
        lVar.f1077f = lVar.f1072a.getText(i10);
        lVar.f1078g = onClickListener;
        return this;
    }

    public p setTitle(CharSequence charSequence) {
        this.f1147a.f1075d = charSequence;
        return this;
    }

    public p setView(View view) {
        this.f1147a.f1083m = view;
        return this;
    }

    public p(Context context, int i10) {
        this.f1147a = new l(new ContextThemeWrapper(context, q.d(context, i10)));
        this.f1148b = i10;
    }
}

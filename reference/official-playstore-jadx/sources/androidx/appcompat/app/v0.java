package androidx.appcompat.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class v0 implements View.OnClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final View f1198i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f1199l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Method f1200m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Context f1201n;

    public v0(View view, String str) {
        this.f1198i = view;
        this.f1199l = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        Method method;
        if (this.f1200m == null) {
            View view2 = this.f1198i;
            Context context = view2.getContext();
            while (true) {
                String str2 = this.f1199l;
                if (context == null) {
                    int id = view2.getId();
                    if (id == -1) {
                        str = "";
                    } else {
                        str = " with id '" + view2.getContext().getResources().getResourceEntryName(id) + "'";
                    }
                    StringBuilder sbT = a0.c.t("Could not find method ", str2, "(View) in a parent or ancestor Context for android:onClick attribute defined on view ");
                    sbT.append(view2.getClass());
                    sbT.append(str);
                    throw new IllegalStateException(sbT.toString());
                }
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(str2, View.class)) != null) {
                        this.f1200m = method;
                        this.f1201n = context;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
        }
        try {
            this.f1200m.invoke(this.f1201n, view);
        } catch (IllegalAccessException e5) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", e5);
        } catch (InvocationTargetException e6) {
            throw new IllegalStateException("Could not execute method for android:onClick", e6);
        }
    }
}

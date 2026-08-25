package io.sentry.android.replay.util;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.o0;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.work.impl.t;
import io.sentry.ILogger;
import io.sentry.w5;
import java.util.ArrayList;
import kotlin.text.o;
import x6.x;

/* JADX INFO: loaded from: classes5.dex */
public abstract class l {
    public static final x a(View view) {
        if (!view.isAttachedToWindow()) {
            return new x(Boolean.FALSE, null);
        }
        if (view.getWindowVisibility() != 0) {
            return new x(Boolean.FALSE, null);
        }
        Object parent = view;
        while (parent instanceof View) {
            float transitionAlpha = Build.VERSION.SDK_INT >= 29 ? ((View) parent).getTransitionAlpha() : 1.0f;
            View view2 = (View) parent;
            if (view2.getAlpha() <= 0.0f || transitionAlpha <= 0.0f || view2.getVisibility() != 0) {
                return new x(Boolean.FALSE, null);
            }
            parent = view2.getParent();
        }
        Rect rect = new Rect();
        return new x(Boolean.valueOf(view.getGlobalVisibleRect(rect, new Point())), rect);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void b(View view, io.sentry.android.replay.viewhierarchy.g gVar, o0 o0Var, ILogger iLogger) {
        LayoutNode root;
        if (view instanceof ViewGroup) {
            Object obj = io.sentry.android.replay.viewhierarchy.b.f17069a;
            if (o.T(view.getClass().getName(), "AndroidComposeView", false)) {
                try {
                    Owner owner = view instanceof Owner ? (Owner) view : null;
                    if (owner != null && (root = owner.getRoot()) != null) {
                        io.sentry.android.replay.viewhierarchy.b.c(root, gVar, true, o0Var, iLogger);
                        return;
                    }
                } catch (Throwable th) {
                    iLogger.k(w5.ERROR, th, "Error traversing Compose tree. Most likely you're using an unsupported version of\nandroidx.compose.ui:ui. The minimum supported version is 1.5.0. If it's a newer\nversion, please open a github issue with the version you're using, so we can add\nsupport for it.", new Object[0]);
                }
            }
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 0) {
                return;
            }
            ArrayList arrayList = new ArrayList(viewGroup.getChildCount());
            int childCount = viewGroup.getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = viewGroup.getChildAt(i10);
                if (childAt != null) {
                    viewGroup.indexOfChild(childAt);
                    io.sentry.android.replay.viewhierarchy.g gVarQ = t.q(childAt, gVar, o0Var);
                    arrayList.add(gVarQ);
                    b(childAt, gVarQ, o0Var, iLogger);
                }
            }
            gVar.f17083f = arrayList;
        }
    }
}

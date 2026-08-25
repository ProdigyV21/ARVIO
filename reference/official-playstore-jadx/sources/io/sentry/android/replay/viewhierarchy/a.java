package io.sentry.android.replay.viewhierarchy;

import androidx.compose.ui.node.LayoutNode;
import java.lang.reflect.Method;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a f17068i = new a(0);

    @Override // r7.a
    public final Object invoke() {
        try {
            Method declaredMethod = LayoutNode.class.getDeclaredMethod("getCollapsedSemantics$ui_release", null);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }
}

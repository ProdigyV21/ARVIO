package io.sentry.android.replay.viewhierarchy;

import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f17069a = f4.f.o(3, a.f17068i);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f17070b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static WeakReference f17071c;

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, x6.s] */
    public static final SemanticsConfiguration a(LayoutNode layoutNode) {
        try {
            return layoutNode.getSemanticsConfiguration();
        } catch (Throwable th) {
            ?? r1 = f17069a;
            if (((Method) r1.getValue()) != null) {
                return (SemanticsConfiguration) ((Method) r1.getValue()).invoke(layoutNode, null);
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(androidx.compose.ui.semantics.SemanticsConfiguration r3, boolean r4, androidx.appcompat.app.o0 r5) {
        /*
            if (r3 == 0) goto Lb
            androidx.compose.ui.semantics.SemanticsPropertyKey r0 = io.sentry.android.replay.a0.f16873a
            java.lang.Object r0 = androidx.compose.ui.semantics.SemanticsConfigurationKt.getOrNull(r3, r0)
            java.lang.String r0 = (java.lang.String) r0
            goto Lc
        Lb:
            r0 = 0
        Lc:
            java.lang.String r1 = "unmask"
            boolean r1 = kotlin.jvm.internal.p.a(r0, r1)
            r2 = 0
            if (r1 == 0) goto L19
            r5.l()
            return r2
        L19:
            java.lang.String r1 = "mask"
            boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
            if (r0 == 0) goto L26
            r5.l()
            r3 = 1
            return r3
        L26:
            if (r4 == 0) goto L2b
            java.lang.String r3 = "android.widget.ImageView"
            goto L54
        L2b:
            if (r3 == 0) goto L52
            androidx.compose.ui.semantics.SemanticsProperties r4 = androidx.compose.ui.semantics.SemanticsProperties.INSTANCE
            androidx.compose.ui.semantics.SemanticsPropertyKey r0 = r4.getText()
            boolean r0 = r3.contains(r0)
            if (r0 != 0) goto L4f
            androidx.compose.ui.semantics.SemanticsActions r0 = androidx.compose.ui.semantics.SemanticsActions.INSTANCE
            androidx.compose.ui.semantics.SemanticsPropertyKey r0 = r0.getSetText()
            boolean r0 = r3.contains(r0)
            if (r0 != 0) goto L4f
            androidx.compose.ui.semantics.SemanticsPropertyKey r4 = r4.getEditableText()
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto L52
        L4f:
            java.lang.String r3 = "android.widget.TextView"
            goto L54
        L52:
            java.lang.String r3 = "android.view.View"
        L54:
            java.lang.Object r4 = r5.f1146l
            java.util.concurrent.CopyOnWriteArraySet r4 = (java.util.concurrent.CopyOnWriteArraySet) r4
            boolean r4 = r4.contains(r3)
            if (r4 == 0) goto L5f
            return r2
        L5f:
            java.lang.Object r4 = r5.f1145i
            java.util.concurrent.CopyOnWriteArraySet r4 = (java.util.concurrent.CopyOnWriteArraySet) r4
            boolean r3 = r4.contains(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.viewhierarchy.b.b(androidx.compose.ui.semantics.SemanticsConfiguration, boolean, androidx.appcompat.app.o0):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x036b A[EDGE_INSN: B:202:0x036b->B:147:0x036b BREAK  A[LOOP:2: B:140:0x0335->B:148:0x036d], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01f1 A[PHI: r5
      0x01f1: PHI (r5v8 boolean) = (r5v46 boolean), (r5v47 boolean) binds: [B:72:0x01ef, B:67:0x01df] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(androidx.compose.ui.node.LayoutNode r29, io.sentry.android.replay.viewhierarchy.g r30, boolean r31, androidx.appcompat.app.o0 r32, io.sentry.ILogger r33) {
        /*
            Method dump skipped, instruction units count: 1060
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.viewhierarchy.b.c(androidx.compose.ui.node.LayoutNode, io.sentry.android.replay.viewhierarchy.g, boolean, androidx.appcompat.app.o0, io.sentry.ILogger):void");
    }
}

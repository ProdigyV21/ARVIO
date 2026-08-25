package androidx.compose.animation.core;

import androidx.compose.animation.core.Transition;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class d {
    public static boolean a(Transition.Segment segment, Object obj, Object obj2) {
        return p.a(obj, segment.getInitialState()) && p.a(obj2, segment.getTargetState());
    }
}

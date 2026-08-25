package androidx.compose.ui.text.platform;

import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a,\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u00020\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0081\b¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/text/platform/SynchronizedObject;", "createSynchronizedObject", "()Landroidx/compose/ui/text/platform/SynchronizedObject;", "R", "lock", "Lkotlin/Function0;", "block", "synchronized", "(Landroidx/compose/ui/text/platform/SynchronizedObject;Lr7/a;)Ljava/lang/Object;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Synchronization_jvmKt {
    public static final SynchronizedObject createSynchronizedObject() {
        return new SynchronizedObject();
    }

    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final <R> R m5448synchronized(SynchronizedObject synchronizedObject, a<? extends R> aVar) {
        R r4;
        synchronized (synchronizedObject) {
            r4 = (R) aVar.invoke();
        }
        return r4;
    }
}

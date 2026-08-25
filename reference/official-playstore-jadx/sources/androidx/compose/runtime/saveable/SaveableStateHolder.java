package androidx.compose.runtime.saveable;

import androidx.compose.runtime.Composer;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J*\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00012\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H'¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0001H&¢\u0006\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/saveable/SaveableStateHolder;", "", "key", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "SaveableStateProvider", "(Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "removeState", "(Ljava/lang/Object;)V", "runtime-saveable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface SaveableStateHolder {
    void SaveableStateProvider(Object obj, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10);

    void removeState(Object key);
}

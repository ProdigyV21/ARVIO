package androidx.compose.runtime;

import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@ComposeCompilerApi
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J)\u0010\u0007\u001a\u00020\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0002H&¢\u0006\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/ScopeUpdateScope;", "", "Lkotlin/Function2;", "Landroidx/compose/runtime/Composer;", "", "Lx6/t0;", "block", "updateScope", "(Lr7/p;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ScopeUpdateScope {
    void updateScope(p<? super Composer, ? super Integer, t0> block);
}

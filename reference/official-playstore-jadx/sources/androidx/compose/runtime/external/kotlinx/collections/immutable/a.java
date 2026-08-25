package androidx.compose.runtime.external.kotlinx.collections.immutable;

import androidx.compose.runtime.external.kotlinx.collections.immutable.ImmutableList;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static ImmutableList a(ImmutableList immutableList, int i10, int i11) {
        return new ImmutableList.SubList(immutableList, i10, i11);
    }
}

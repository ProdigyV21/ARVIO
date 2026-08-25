package com.google.android.datatransport.runtime.scheduling.persistence;

import java.io.Closeable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface d extends Closeable {
    long J(com.google.android.datatransport.runtime.i iVar);

    void W(Iterable iterable);

    b a0(com.google.android.datatransport.runtime.i iVar, com.google.android.datatransport.runtime.h hVar);

    int e();

    Iterable j0(com.google.android.datatransport.runtime.i iVar);

    void n(Iterable iterable);

    List w();

    boolean w0(com.google.android.datatransport.runtime.i iVar);

    void z(long j10, com.google.android.datatransport.runtime.i iVar);
}

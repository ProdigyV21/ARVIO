package l7;

import ga.m;
import java.io.File;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f20056a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f20057b = 2;

    public h(File file) {
        this.f20056a = file;
    }

    @Override // ga.m
    public final Iterator iterator() {
        return new f(this);
    }
}

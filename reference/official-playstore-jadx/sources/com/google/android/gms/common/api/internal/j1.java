package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class j1 extends Fragment implements k {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final WeakHashMap f12761l = new WeakHashMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.appcompat.widget.f0 f12762i = new androidx.appcompat.widget.f0();

    @Override // com.google.android.gms.common.api.internal.k
    public final a0 a() {
        return (a0) a0.class.cast(((Map) this.f12762i.f1567m).get("ConnectionlessLifecycleHelper"));
    }

    @Override // com.google.android.gms.common.api.internal.k
    public final void b(a0 a0Var) {
        this.f12762i.i(a0Var);
    }

    @Override // com.google.android.gms.common.api.internal.k
    public final Activity c() {
        return getActivity();
    }

    @Override // android.app.Fragment
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        Iterator it = ((Map) this.f12762i.f1567m).values().iterator();
        while (it.hasNext()) {
            ((a0) it.next()).getClass();
        }
    }

    @Override // android.app.Fragment
    public final void onActivityResult(int i10, int i11, Intent intent) {
        super.onActivityResult(i10, i11, intent);
        this.f12762i.k(i10, i11, intent);
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f12762i.j(bundle);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        androidx.appcompat.widget.f0 f0Var = this.f12762i;
        f0Var.f1566l = 5;
        Iterator it = ((Map) f0Var.f1567m).values().iterator();
        while (it.hasNext()) {
            ((a0) it.next()).getClass();
        }
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        androidx.appcompat.widget.f0 f0Var = this.f12762i;
        f0Var.f1566l = 3;
        Iterator it = ((Map) f0Var.f1567m).values().iterator();
        while (it.hasNext()) {
            ((a0) it.next()).d();
        }
    }

    @Override // android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.f12762i.l(bundle);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        androidx.appcompat.widget.f0 f0Var = this.f12762i;
        f0Var.f1566l = 2;
        for (a0 a0Var : ((Map) f0Var.f1567m).values()) {
            a0Var.f12694l = true;
            a0Var.d();
        }
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        androidx.appcompat.widget.f0 f0Var = this.f12762i;
        f0Var.f1566l = 4;
        Iterator it = ((Map) f0Var.f1567m).values().iterator();
        while (it.hasNext()) {
            ((a0) it.next()).c();
        }
    }
}

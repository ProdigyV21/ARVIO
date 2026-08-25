package com.google.firebase.concurrent;

import android.os.Build;
import android.os.StrictMode;
import androidx.work.impl.t;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.s;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes4.dex */
public class ExecutorsRegistrar implements ComponentRegistrar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.google.firebase.components.o f14305a = new com.google.firebase.components.o(new com.google.firebase.components.h(2));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.components.o f14306b = new com.google.firebase.components.o(new com.google.firebase.components.h(3));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.components.o f14307c = new com.google.firebase.components.o(new com.google.firebase.components.h(4));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.components.o f14308d = new com.google.firebase.components.o(new com.google.firebase.components.h(5));

    public static f a() {
        StrictMode.ThreadPolicy.Builder builderDetectNetwork = new StrictMode.ThreadPolicy.Builder().detectNetwork();
        int i10 = Build.VERSION.SDK_INT;
        builderDetectNetwork.detectResourceMismatches();
        if (i10 >= 26) {
            builderDetectNetwork.detectUnbufferedIo();
        }
        return new f(Executors.newFixedThreadPool(4, new a("Firebase Background", 10, builderDetectNetwork.penaltyLog().build())), (ScheduledExecutorService) f14308d.get());
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public final List getComponents() {
        s sVar = new s(c5.a.class, ScheduledExecutorService.class);
        s[] sVarArr = {new s(c5.a.class, ExecutorService.class), new s(c5.a.class, Executor.class)};
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        hashSet.add(sVar);
        for (s sVar2 : sVarArr) {
            t.f(sVar2, "Null interface");
        }
        Collections.addAll(hashSet, sVarArr);
        com.google.firebase.components.b bVar = new com.google.firebase.components.b(null, new HashSet(hashSet), new HashSet(hashSet2), 0, 0, new androidx.media3.extractor.mp4.b(25), hashSet3);
        s sVar3 = new s(c5.b.class, ScheduledExecutorService.class);
        s[] sVarArr2 = {new s(c5.b.class, ExecutorService.class), new s(c5.b.class, Executor.class)};
        HashSet hashSet4 = new HashSet();
        HashSet hashSet5 = new HashSet();
        HashSet hashSet6 = new HashSet();
        hashSet4.add(sVar3);
        for (s sVar4 : sVarArr2) {
            t.f(sVar4, "Null interface");
        }
        Collections.addAll(hashSet4, sVarArr2);
        com.google.firebase.components.b bVar2 = new com.google.firebase.components.b(null, new HashSet(hashSet4), new HashSet(hashSet5), 0, 0, new androidx.media3.extractor.mp4.b(26), hashSet6);
        s sVar5 = new s(c5.c.class, ScheduledExecutorService.class);
        s[] sVarArr3 = {new s(c5.c.class, ExecutorService.class), new s(c5.c.class, Executor.class)};
        HashSet hashSet7 = new HashSet();
        HashSet hashSet8 = new HashSet();
        HashSet hashSet9 = new HashSet();
        hashSet7.add(sVar5);
        for (s sVar6 : sVarArr3) {
            t.f(sVar6, "Null interface");
        }
        Collections.addAll(hashSet7, sVarArr3);
        com.google.firebase.components.b bVar3 = new com.google.firebase.components.b(null, new HashSet(hashSet7), new HashSet(hashSet8), 0, 0, new androidx.media3.extractor.mp4.b(27), hashSet9);
        com.google.firebase.components.a aVarA = com.google.firebase.components.b.a(new s(c5.d.class, Executor.class));
        aVarA.f14253f = new androidx.media3.extractor.mp4.b(28);
        return Arrays.asList(bVar, bVar2, bVar3, aVarA.b());
    }
}

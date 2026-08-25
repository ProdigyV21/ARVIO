package androidx.work.impl;

import android.content.Context;
import androidx.work.impl.background.systemjob.SystemJobService;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class s extends kotlin.jvm.internal.m implements r7.t {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final s f6974i = new s(6, t.class, "createSchedulers", "createSchedulers(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/constraints/trackers/Trackers;Landroidx/work/impl/Processor;)Ljava/util/List;", 1);

    @Override // r7.t
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Context context = (Context) obj;
        m2.c cVar = (m2.c) obj2;
        androidx.work.impl.utils.taskexecutor.b bVar = (androidx.work.impl.utils.taskexecutor.b) obj3;
        g gVar = (g) obj6;
        String str = k.f6895a;
        androidx.work.impl.background.systemjob.b bVar2 = new androidx.work.impl.background.systemjob.b(context, (WorkDatabase) obj4, cVar);
        androidx.work.impl.utils.j.a(context, SystemJobService.class, true);
        m2.t.d().a(k.f6895a, "Created SystemJobScheduler and enabled SystemJobService");
        return t7.a.E(bVar2, new n2.c(context, cVar, (androidx.work.impl.constraints.trackers.m) obj5, gVar, new io.sentry.internal.debugmeta.c(6, gVar, false, bVar), bVar));
    }
}

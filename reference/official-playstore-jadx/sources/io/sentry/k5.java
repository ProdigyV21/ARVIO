package io.sentry;

import io.sentry.exception.ExceptionMechanismException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public final class k5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f17418a;

    public static io.sentry.protocol.u c(Throwable th, io.sentry.protocol.m mVar, Long l10, List list, boolean z) {
        Package r02 = th.getClass().getPackage();
        String name = th.getClass().getName();
        io.sentry.protocol.u uVar = new io.sentry.protocol.u();
        String message = th.getMessage();
        if (r02 != null) {
            name = name.replace(r02.getName() + ".", "");
        }
        String name2 = r02 != null ? r02.getName() : null;
        if (list != null && !list.isEmpty()) {
            io.sentry.protocol.b0 b0Var = new io.sentry.protocol.b0(list);
            if (z) {
                b0Var.f17547m = Boolean.TRUE;
            }
            uVar.f17704o = b0Var;
        }
        uVar.f17703n = l10;
        uVar.f17700i = name;
        uVar.f17705p = mVar;
        uVar.f17702m = name2;
        uVar.f17701l = message;
        return uVar;
    }

    public void a(Throwable th, AtomicInteger atomicInteger, HashSet hashSet, ArrayDeque arrayDeque, String str) {
        Thread threadCurrentThread;
        io.sentry.protocol.m mVar;
        boolean z;
        int iIncrementAndGet = atomicInteger.get();
        String str2 = str;
        while (th != null && hashSet.add(th)) {
            if (str2 == null) {
                str2 = "chained";
            }
            if (th instanceof ExceptionMechanismException) {
                ExceptionMechanismException exceptionMechanismException = (ExceptionMechanismException) th;
                io.sentry.protocol.m mVar2 = exceptionMechanismException.f17239i;
                Throwable th2 = exceptionMechanismException.f17240l;
                threadCurrentThread = exceptionMechanismException.f17241m;
                z = exceptionMechanismException.f17242n;
                th = th2;
                mVar = mVar2;
            } else {
                io.sentry.protocol.m mVar3 = new io.sentry.protocol.m();
                threadCurrentThread = Thread.currentThread();
                mVar = mVar3;
                z = false;
            }
            io.sentry.protocol.u uVarC = c(th, mVar, threadCurrentThread != null ? Long.valueOf(threadCurrentThread.getId()) : null, this.f17418a.b(th.getStackTrace(), Boolean.FALSE.equals(mVar.f17641n)), z);
            ArrayDeque arrayDeque2 = arrayDeque;
            arrayDeque2.addFirst(uVarC);
            if (mVar.f17638i == null) {
                mVar.f17638i = str2;
            }
            if (atomicInteger.get() >= 0) {
                mVar.f17646s = Integer.valueOf(iIncrementAndGet);
            }
            iIncrementAndGet = atomicInteger.incrementAndGet();
            mVar.f17645r = Integer.valueOf(iIncrementAndGet);
            Throwable[] suppressed = th.getSuppressed();
            if (suppressed != null && suppressed.length > 0) {
                int length = suppressed.length;
                int i10 = 0;
                while (i10 < length) {
                    a(suppressed[i10], atomicInteger, hashSet, arrayDeque2, "suppressed");
                    i10++;
                    arrayDeque2 = arrayDeque;
                }
            }
            th = th.getCause();
            str2 = null;
        }
    }

    public ArrayList b(Map map, ArrayList arrayList, boolean z, boolean z5) {
        ArrayList arrayListB;
        Thread threadCurrentThread = Thread.currentThread();
        if (map.isEmpty()) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        if (!map.containsKey(threadCurrentThread)) {
            map.put(threadCurrentThread, threadCurrentThread.getStackTrace());
        }
        for (Map.Entry entry : map.entrySet()) {
            Thread thread = (Thread) entry.getKey();
            boolean z10 = (thread == threadCurrentThread && !z) || !(arrayList == null || !arrayList.contains(Long.valueOf(thread.getId())) || z);
            StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) entry.getValue();
            Thread thread2 = (Thread) entry.getKey();
            io.sentry.protocol.d0 d0Var = new io.sentry.protocol.d0();
            d0Var.f17558m = thread2.getName();
            d0Var.f17557l = Integer.valueOf(thread2.getPriority());
            d0Var.f17556i = Long.valueOf(thread2.getId());
            d0Var.f17562q = Boolean.valueOf(thread2.isDaemon());
            d0Var.f17559n = thread2.getState().name();
            d0Var.f17560o = Boolean.valueOf(z10);
            if (z5 && (arrayListB = this.f17418a.b(stackTraceElementArr, false)) != null && !arrayListB.isEmpty()) {
                io.sentry.protocol.b0 b0Var = new io.sentry.protocol.b0(arrayListB);
                b0Var.f17547m = Boolean.TRUE;
                d0Var.f17564s = b0Var;
            }
            arrayList2.add(d0Var);
        }
        return arrayList2;
    }
}

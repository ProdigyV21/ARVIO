package com.arflix.tv.core.plugin;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ \u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u0019\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/core/plugin/TestDiagnostics;", "", "", "", "steps", "<init>", "(Ljava/util/List;)V", "step", "Lx6/t0;", "addStep", "(Ljava/lang/String;)V", "component1", "()Ljava/util/List;", "copy", "(Ljava/util/List;)Lcom/arflix/tv/core/plugin/TestDiagnostics;", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getSteps", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TestDiagnostics {
    public static final int $stable = 0;
    private final List<String> steps;

    /* JADX WARN: Multi-variable type inference failed */
    public TestDiagnostics() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TestDiagnostics copy$default(TestDiagnostics testDiagnostics, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = testDiagnostics.steps;
        }
        return testDiagnostics.copy(list);
    }

    public final void addStep(String step) {
        this.steps.add(step);
        Log.d("TestDiagnostics", step);
    }

    public final List<String> component1() {
        return this.steps;
    }

    public final TestDiagnostics copy(List<String> steps) {
        return new TestDiagnostics(steps);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TestDiagnostics) && p.a(this.steps, ((TestDiagnostics) other).steps);
    }

    public final List<String> getSteps() {
        return this.steps;
    }

    public int hashCode() {
        return this.steps.hashCode();
    }

    public String toString() {
        return "TestDiagnostics(steps=" + this.steps + ")";
    }

    public TestDiagnostics(List<String> list) {
        this.steps = list;
    }

    public /* synthetic */ TestDiagnostics(List list, int i10, h hVar) {
        this((i10 & 1) != 0 ? new ArrayList() : list);
    }
}

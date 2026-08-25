package io.ktor.util.pipeline;

import d7.d;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.q0;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u0000 4*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001:\u00014Ba\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012H\u0010\u000e\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\r0\b¢\u0006\u0004\b\u000f\u0010\u0010B\u0019\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000f\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013JQ\u0010\u0015\u001a\u00020\f2B\u0010\u0014\u001a>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\r¢\u0006\u0004\b\u0015\u0010\u0016JW\u0010\u0018\u001a\u00020\f2H\u0010\u0017\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\r0\b¢\u0006\u0004\b\u0018\u0010\u0019J!\u0010\u0018\u001a\u00020\f2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u0004\b\u0018\u0010\u001aJO\u0010\u001b\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\r0\b¢\u0006\u0004\b\u001b\u0010\u001cJO\u0010\u001d\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\r0\b¢\u0006\u0004\b\u001d\u0010\u001cJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b%\u0010&RX\u0010\u000e\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\n\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\r0\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010'R\"\u0010)\u001a\u00020(8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0011\u0010/\u001a\u00020(8F¢\u0006\u0006\u001a\u0004\b/\u0010,R\u0011\u00103\u001a\u0002008F¢\u0006\u0006\u001a\u0004\b1\u00102¨\u00065"}, d2 = {"Lio/ktor/util/pipeline/PhaseContent;", "", "TSubject", "Call", "Lio/ktor/util/pipeline/PipelinePhase;", "phase", "Lio/ktor/util/pipeline/PipelinePhaseRelation;", "relation", "", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Ld7/d;", "Lx6/t0;", "Lio/ktor/util/pipeline/PipelineInterceptorFunction;", "interceptors", "<init>", "(Lio/ktor/util/pipeline/PipelinePhase;Lio/ktor/util/pipeline/PipelinePhaseRelation;Ljava/util/List;)V", "(Lio/ktor/util/pipeline/PipelinePhase;Lio/ktor/util/pipeline/PipelinePhaseRelation;)V", "copyInterceptors", "()V", "interceptor", "addInterceptor", "(Lr7/q;)V", "destination", "addTo", "(Ljava/util/List;)V", "(Lio/ktor/util/pipeline/PhaseContent;)V", "sharedInterceptors", "()Ljava/util/List;", "copiedInterceptors", "", "toString", "()Ljava/lang/String;", "Lio/ktor/util/pipeline/PipelinePhase;", "getPhase", "()Lio/ktor/util/pipeline/PipelinePhase;", "Lio/ktor/util/pipeline/PipelinePhaseRelation;", "getRelation", "()Lio/ktor/util/pipeline/PipelinePhaseRelation;", "Ljava/util/List;", "", "shared", "Z", "getShared", "()Z", "setShared", "(Z)V", "isEmpty", "", "getSize", "()I", ContentDisposition.Parameters.Size, "Companion", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PhaseContent<TSubject, Call> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<Object> SharedArrayList = new ArrayList();
    private List<q<PipelineContext<TSubject, Call>, TSubject, d<? super t0>, Object>> interceptors;
    private final PipelinePhase phase;
    private final PipelinePhaseRelation relation;
    private boolean shared;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001f\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/util/pipeline/PhaseContent$Companion;", "", "()V", "SharedArrayList", "", "getSharedArrayList$annotations", "getSharedArrayList", "()Ljava/util/List;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public static /* synthetic */ void getSharedArrayList$annotations() {
        }

        public final List<Object> getSharedArrayList() {
            return PhaseContent.SharedArrayList;
        }

        private Companion() {
        }
    }

    public PhaseContent(PipelinePhase pipelinePhase, PipelinePhaseRelation pipelinePhaseRelation, List<q<PipelineContext<TSubject, Call>, TSubject, d<? super t0>, Object>> list) {
        this.phase = pipelinePhase;
        this.relation = pipelinePhaseRelation;
        this.interceptors = list;
        this.shared = true;
    }

    private final void copyInterceptors() {
        this.interceptors = copiedInterceptors();
        this.shared = false;
    }

    public final void addInterceptor(q<? super PipelineContext<TSubject, Call>, ? super TSubject, ? super d<? super t0>, ? extends Object> interceptor) {
        if (this.shared) {
            copyInterceptors();
        }
        this.interceptors.add(interceptor);
    }

    public final void addTo(List<q<PipelineContext<TSubject, Call>, TSubject, d<? super t0>, Object>> destination) {
        List<q<PipelineContext<TSubject, Call>, TSubject, d<? super t0>, Object>> list = this.interceptors;
        if (destination instanceof ArrayList) {
            ((ArrayList) destination).ensureCapacity(list.size() + destination.size());
        }
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            destination.add(list.get(i10));
        }
    }

    public final List<q<PipelineContext<TSubject, Call>, TSubject, d<? super t0>, Object>> copiedInterceptors() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.interceptors);
        return arrayList;
    }

    public final PipelinePhase getPhase() {
        return this.phase;
    }

    public final PipelinePhaseRelation getRelation() {
        return this.relation;
    }

    public final boolean getShared() {
        return this.shared;
    }

    public final int getSize() {
        return this.interceptors.size();
    }

    public final boolean isEmpty() {
        return this.interceptors.isEmpty();
    }

    public final void setShared(boolean z) {
        this.shared = z;
    }

    public final List<q<PipelineContext<TSubject, Call>, TSubject, d<? super t0>, Object>> sharedInterceptors() {
        this.shared = true;
        return this.interceptors;
    }

    public String toString() {
        return "Phase `" + this.phase.getName() + "`, " + getSize() + " handlers";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PhaseContent(PipelinePhase pipelinePhase, PipelinePhaseRelation pipelinePhaseRelation) {
        List<Object> list = SharedArrayList;
        this(pipelinePhase, pipelinePhaseRelation, q0.b(list));
        if (!list.isEmpty()) {
            throw new IllegalStateException("The shared empty array list has been modified");
        }
    }

    public final void addTo(PhaseContent<TSubject, Call> destination) {
        if (isEmpty()) {
            return;
        }
        if (destination.isEmpty()) {
            destination.interceptors = sharedInterceptors();
            destination.shared = true;
        } else {
            if (destination.shared) {
                destination.copyInterceptors();
            }
            addTo(destination.interceptors);
        }
    }
}

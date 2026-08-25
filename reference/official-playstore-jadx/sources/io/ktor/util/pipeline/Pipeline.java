package io.ktor.util.pipeline;

import d7.d;
import d7.j;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.pipeline.PipelinePhaseRelation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.q0;
import r7.q;
import t7.a;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u000e\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001B\u001b\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\"\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bBU\b\u0016\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012?\u0010\u0010\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b¢\u0006\u0002\b\u000f0\nø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u0011J#\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\u001b\u0010\u001aJS\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000529\u0010\u001c\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b¢\u0006\u0002\b\u000fø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001f\u0010 JQ\u0010!\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b¢\u0006\u0002\b\u000f0\n2\u0006\u0010\t\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J!\u0010$\u001a\u00020\u000e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u0004\b$\u0010%J!\u0010&\u001a\u00020\u000e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u0004\b&\u0010%J!\u0010'\u001a\u00020\u000e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\u0004\b'\u0010%JY\u0010*\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`(0\n2\u0006\u0010\t\u001a\u00020\u0005H\u0000¢\u0006\u0004\b)\u0010\"JQ\u0010-\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`(0\nH\u0000¢\u0006\u0004\b+\u0010,J#\u0010.\u001a\u00020\u000e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002¢\u0006\u0004\b.\u0010%J3\u00101\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f2\u0006\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u00100\u001a\u00020/H\u0002¢\u0006\u0004\b1\u00102J%\u00104\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u0001032\u0006\u0010\t\u001a\u00020\u0005H\u0002¢\u0006\u0004\b4\u00105J\u0017\u00107\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0005H\u0002¢\u0006\u0004\b7\u00108J\u0017\u0010:\u001a\u0002092\u0006\u0010\t\u001a\u00020\u0005H\u0002¢\u0006\u0004\b:\u0010;JQ\u0010<\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`(0\nH\u0002¢\u0006\u0004\b<\u0010,J#\u0010=\u001a\u0002092\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002¢\u0006\u0004\b=\u0010>JQ\u0010?\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`(0\nH\u0002¢\u0006\u0004\b?\u0010,J\u000f\u0010@\u001a\u00020\u000eH\u0002¢\u0006\u0004\b@\u0010 JY\u0010B\u001a\u00020\u000e2H\u0010A\u001aD\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`(0\nH\u0002¢\u0006\u0004\bB\u0010CJ#\u0010E\u001a\u00020\u000e2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000103H\u0002¢\u0006\u0004\bE\u0010FJ#\u0010H\u001a\u00020\u000e2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002¢\u0006\u0004\bH\u0010%J[\u0010I\u001a\u0002092\u0006\u0010\t\u001a\u00020\u00052B\u0010\u001c\u001a>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`(H\u0002¢\u0006\u0004\bI\u0010JJ\u001f\u0010M\u001a\u0002092\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u0005H\u0002¢\u0006\u0004\bM\u0010NR\u0017\u0010P\u001a\u00020O8\u0006¢\u0006\f\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010SR\u001a\u0010T\u001a\u0002098\u0016X\u0096D¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u0010WR\u001a\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00010X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010ZR\u0016\u0010[\u001a\u0002068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010\\R\u0016\u0010]\u001a\u0002098\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b]\u0010UR\u0018\u0010^\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0017\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00050\n8F¢\u0006\u0006\u001a\u0004\b`\u0010,R\u0011\u0010b\u001a\u0002098F¢\u0006\u0006\u001a\u0004\bb\u0010WR¬\u0001\u0010\u0010\u001aF\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`(\u0018\u00010\n2J\u0010c\u001aF\u0012@\u0012>\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`(\u0018\u00010\n8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bd\u0010,\"\u0004\be\u0010C\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006f"}, d2 = {"Lio/ktor/util/pipeline/Pipeline;", "", "TSubject", "TContext", "", "Lio/ktor/util/pipeline/PipelinePhase;", "phases", "<init>", "([Lio/ktor/util/pipeline/PipelinePhase;)V", "phase", "", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Ld7/d;", "Lx6/t0;", "Lx6/n;", "interceptors", "(Lio/ktor/util/pipeline/PipelinePhase;Ljava/util/List;)V", "context", "subject", "execute", "(Ljava/lang/Object;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "addPhase", "(Lio/ktor/util/pipeline/PipelinePhase;)V", "reference", "insertPhaseAfter", "(Lio/ktor/util/pipeline/PipelinePhase;Lio/ktor/util/pipeline/PipelinePhase;)V", "insertPhaseBefore", "block", "intercept", "(Lio/ktor/util/pipeline/PipelinePhase;Lr7/q;)V", "afterIntercepted", "()V", "interceptorsForPhase", "(Lio/ktor/util/pipeline/PipelinePhase;)Ljava/util/List;", "from", "mergePhases", "(Lio/ktor/util/pipeline/Pipeline;)V", "merge", "resetFrom", "Lio/ktor/util/pipeline/PipelineInterceptorFunction;", "phaseInterceptors$ktor_utils", "phaseInterceptors", "interceptorsForTests$ktor_utils", "()Ljava/util/List;", "interceptorsForTests", "mergeInterceptors", "Ld7/j;", "coroutineContext", "createContext", "(Ljava/lang/Object;Ljava/lang/Object;Ld7/j;)Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/util/pipeline/PhaseContent;", "findPhase", "(Lio/ktor/util/pipeline/PipelinePhase;)Lio/ktor/util/pipeline/PhaseContent;", "", "findPhaseIndex", "(Lio/ktor/util/pipeline/PipelinePhase;)I", "", "hasPhase", "(Lio/ktor/util/pipeline/PipelinePhase;)Z", "cacheInterceptors", "fastPathMerge", "(Lio/ktor/util/pipeline/Pipeline;)Z", "sharedInterceptorsList", "resetInterceptorsList", "list", "notSharedInterceptorsList", "(Ljava/util/List;)V", "phaseContent", "setInterceptorsListFromPhase", "(Lio/ktor/util/pipeline/PhaseContent;)V", "pipeline", "setInterceptorsListFromAnotherPipeline", "tryAddToPhaseFastPath", "(Lio/ktor/util/pipeline/PipelinePhase;Lr7/q;)Z", "fromPhaseOrContent", "fromPhase", "insertRelativePhase", "(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelinePhase;)Z", "Lio/ktor/util/Attributes;", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "developmentMode", "Z", "getDevelopmentMode", "()Z", "", "phasesRaw", "Ljava/util/List;", "interceptorsQuantity", "I", "interceptorsListShared", "interceptorsListSharedPhase", "Lio/ktor/util/pipeline/PipelinePhase;", "getItems", "items", "isEmpty", "value", "getInterceptors", "setInterceptors", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class Pipeline<TSubject, TContext> {
    private volatile /* synthetic */ Object _interceptors;
    private final Attributes attributes;
    private final boolean developmentMode;
    private boolean interceptorsListShared;
    private PipelinePhase interceptorsListSharedPhase;
    private int interceptorsQuantity;
    private final List<Object> phasesRaw;

    public Pipeline(PipelinePhase... pipelinePhaseArr) {
        this.attributes = AttributesJvmKt.Attributes(true);
        this.phasesRaw = a.G(Arrays.copyOf(pipelinePhaseArr, pipelinePhaseArr.length));
        this._interceptors = null;
    }

    private final List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> cacheInterceptors() {
        int iW;
        int i10 = this.interceptorsQuantity;
        if (i10 == 0) {
            z zVar = z.f19728i;
            notSharedInterceptorsList(zVar);
            return zVar;
        }
        List<Object> list = this.phasesRaw;
        int i11 = 0;
        if (i10 == 1 && (iW = a.w(list)) >= 0) {
            int i12 = 0;
            while (true) {
                Object obj = list.get(i12);
                PhaseContent<TSubject, TContext> phaseContent = obj instanceof PhaseContent ? (PhaseContent) obj : null;
                if (phaseContent != null && !phaseContent.isEmpty()) {
                    List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> listSharedInterceptors = phaseContent.sharedInterceptors();
                    setInterceptorsListFromPhase(phaseContent);
                    return listSharedInterceptors;
                }
                if (i12 == iW) {
                    break;
                }
                i12++;
            }
        }
        ArrayList arrayList = new ArrayList();
        int iW2 = a.w(list);
        if (iW2 >= 0) {
            while (true) {
                Object obj2 = list.get(i11);
                PhaseContent phaseContent2 = obj2 instanceof PhaseContent ? (PhaseContent) obj2 : null;
                if (phaseContent2 != null) {
                    phaseContent2.addTo(arrayList);
                }
                if (i11 == iW2) {
                    break;
                }
                i11++;
            }
        }
        notSharedInterceptorsList(arrayList);
        return arrayList;
    }

    private final PipelineContext<TSubject, TContext> createContext(TContext context, TSubject subject, j coroutineContext) {
        return PipelineContextKt.pipelineContextFor(context, sharedInterceptorsList(), subject, coroutineContext, getDevelopmentMode());
    }

    private final boolean fastPathMerge(Pipeline<TSubject, TContext> from) {
        if (from.phasesRaw.isEmpty()) {
            return true;
        }
        int i10 = 0;
        if (!this.phasesRaw.isEmpty()) {
            return false;
        }
        List<Object> list = from.phasesRaw;
        int iW = a.w(list);
        if (iW >= 0) {
            while (true) {
                Object obj = list.get(i10);
                if (obj instanceof PipelinePhase) {
                    this.phasesRaw.add(obj);
                } else if (obj instanceof PhaseContent) {
                    PhaseContent phaseContent = (PhaseContent) obj;
                    this.phasesRaw.add(new PhaseContent(phaseContent.getPhase(), phaseContent.getRelation(), phaseContent.sharedInterceptors()));
                }
                if (i10 == iW) {
                    break;
                }
                i10++;
            }
        }
        this.interceptorsQuantity += from.interceptorsQuantity;
        setInterceptorsListFromAnotherPipeline(from);
        return true;
    }

    private final PhaseContent<TSubject, TContext> findPhase(PipelinePhase phase) {
        List<Object> list = this.phasesRaw;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object obj = list.get(i10);
            if (obj == phase) {
                PhaseContent<TSubject, TContext> phaseContent = new PhaseContent<>(phase, PipelinePhaseRelation.Last.INSTANCE);
                list.set(i10, phaseContent);
                return phaseContent;
            }
            if (obj instanceof PhaseContent) {
                PhaseContent<TSubject, TContext> phaseContent2 = (PhaseContent) obj;
                if (phaseContent2.getPhase() == phase) {
                    return phaseContent2;
                }
            }
        }
        return null;
    }

    private final int findPhaseIndex(PipelinePhase phase) {
        List<Object> list = this.phasesRaw;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object obj = list.get(i10);
            if (obj == phase || ((obj instanceof PhaseContent) && ((PhaseContent) obj).getPhase() == phase)) {
                return i10;
            }
        }
        return -1;
    }

    private final List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> getInterceptors() {
        return (List) this._interceptors;
    }

    private final boolean hasPhase(PipelinePhase phase) {
        List<Object> list = this.phasesRaw;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Object obj = list.get(i10);
            if (obj == phase) {
                return true;
            }
            if ((obj instanceof PhaseContent) && ((PhaseContent) obj).getPhase() == phase) {
                return true;
            }
        }
        return false;
    }

    private final boolean insertRelativePhase(Object fromPhaseOrContent, PipelinePhase fromPhase) throws InvalidPhaseException {
        PipelinePhaseRelation relation = fromPhaseOrContent == fromPhase ? PipelinePhaseRelation.Last.INSTANCE : ((PhaseContent) fromPhaseOrContent).getRelation();
        if (relation instanceof PipelinePhaseRelation.Last) {
            addPhase(fromPhase);
            return true;
        }
        if (relation instanceof PipelinePhaseRelation.Before) {
            PipelinePhaseRelation.Before before = (PipelinePhaseRelation.Before) relation;
            if (hasPhase(before.getRelativeTo())) {
                insertPhaseBefore(before.getRelativeTo(), fromPhase);
                return true;
            }
        }
        if (!(relation instanceof PipelinePhaseRelation.After)) {
            return false;
        }
        insertPhaseAfter(((PipelinePhaseRelation.After) relation).getRelativeTo(), fromPhase);
        return true;
    }

    private final void mergeInterceptors(Pipeline<TSubject, TContext> from) {
        if (this.interceptorsQuantity == 0) {
            setInterceptorsListFromAnotherPipeline(from);
        } else {
            resetInterceptorsList();
        }
        for (Object obj : from.phasesRaw) {
            PipelinePhase phase = obj instanceof PipelinePhase ? (PipelinePhase) obj : null;
            if (phase == null) {
                phase = ((PhaseContent) obj).getPhase();
            }
            if (obj instanceof PhaseContent) {
                PhaseContent phaseContent = (PhaseContent) obj;
                if (!phaseContent.isEmpty()) {
                    phaseContent.addTo(findPhase(phase));
                    this.interceptorsQuantity = phaseContent.getSize() + this.interceptorsQuantity;
                }
            }
        }
    }

    private final void notSharedInterceptorsList(List<? extends q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object>> list) {
        setInterceptors(list);
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = null;
    }

    private final void resetInterceptorsList() {
        setInterceptors(null);
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = null;
    }

    private final void setInterceptors(List<? extends q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object>> list) {
        this._interceptors = list;
    }

    private final void setInterceptorsListFromAnotherPipeline(Pipeline<TSubject, TContext> pipeline) {
        setInterceptors(pipeline.sharedInterceptorsList());
        this.interceptorsListShared = true;
        this.interceptorsListSharedPhase = null;
    }

    private final void setInterceptorsListFromPhase(PhaseContent<TSubject, TContext> phaseContent) {
        setInterceptors(phaseContent.sharedInterceptors());
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = phaseContent.getPhase();
    }

    private final List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> sharedInterceptorsList() {
        if (getInterceptors() == null) {
            cacheInterceptors();
        }
        this.interceptorsListShared = true;
        return getInterceptors();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean tryAddToPhaseFastPath(PipelinePhase phase, q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object> block) {
        List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> interceptors = getInterceptors();
        if (this.phasesRaw.isEmpty() || interceptors == null || this.interceptorsListShared || !q0.g(interceptors)) {
            return false;
        }
        if (p.a(this.interceptorsListSharedPhase, phase)) {
            interceptors.add(block);
            return true;
        }
        if (!p.a(phase, x.w0(this.phasesRaw)) && findPhaseIndex(phase) != a.w(this.phasesRaw)) {
            return false;
        }
        findPhase(phase).addInterceptor(block);
        interceptors.add(block);
        return true;
    }

    public final void addPhase(PipelinePhase phase) {
        if (hasPhase(phase)) {
            return;
        }
        this.phasesRaw.add(phase);
    }

    public void afterIntercepted() {
    }

    public final Object execute(TContext tcontext, TSubject tsubject, d<? super TSubject> dVar) {
        return createContext(tcontext, tsubject, dVar.getContext()).execute$ktor_utils(tsubject, dVar);
    }

    public final Attributes getAttributes() {
        return this.attributes;
    }

    public boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public final List<PipelinePhase> getItems() {
        List<Object> list = this.phasesRaw;
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        for (Object obj : list) {
            PipelinePhase phase = obj instanceof PipelinePhase ? (PipelinePhase) obj : null;
            if (phase == null) {
                PhaseContent phaseContent = obj instanceof PhaseContent ? (PhaseContent) obj : null;
                phase = phaseContent != null ? phaseContent.getPhase() : null;
            }
            arrayList.add(phase);
        }
        return arrayList;
    }

    public final void insertPhaseAfter(PipelinePhase reference, PipelinePhase phase) throws InvalidPhaseException {
        PipelinePhaseRelation relation;
        PipelinePhase relativeTo;
        if (hasPhase(phase)) {
            return;
        }
        int iFindPhaseIndex = findPhaseIndex(reference);
        if (iFindPhaseIndex == -1) {
            throw new InvalidPhaseException("Phase " + reference + " was not registered for this pipeline");
        }
        int i10 = iFindPhaseIndex + 1;
        int iW = a.w(this.phasesRaw);
        if (i10 <= iW) {
            while (true) {
                Object obj = this.phasesRaw.get(i10);
                PhaseContent phaseContent = obj instanceof PhaseContent ? (PhaseContent) obj : null;
                if (phaseContent != null && (relation = phaseContent.getRelation()) != null) {
                    PipelinePhaseRelation.After after = relation instanceof PipelinePhaseRelation.After ? (PipelinePhaseRelation.After) relation : null;
                    if (after != null && (relativeTo = after.getRelativeTo()) != null && relativeTo.equals(reference)) {
                        iFindPhaseIndex = i10;
                    }
                    if (i10 == iW) {
                        break;
                    } else {
                        i10++;
                    }
                } else {
                    break;
                }
            }
        }
        this.phasesRaw.add(iFindPhaseIndex + 1, new PhaseContent(phase, new PipelinePhaseRelation.After(reference)));
    }

    public final void insertPhaseBefore(PipelinePhase reference, PipelinePhase phase) throws InvalidPhaseException {
        if (hasPhase(phase)) {
            return;
        }
        int iFindPhaseIndex = findPhaseIndex(reference);
        if (iFindPhaseIndex != -1) {
            this.phasesRaw.add(iFindPhaseIndex, new PhaseContent(phase, new PipelinePhaseRelation.Before(reference)));
            return;
        }
        throw new InvalidPhaseException("Phase " + reference + " was not registered for this pipeline");
    }

    public final void intercept(PipelinePhase phase, q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object> block) {
        PhaseContent<TSubject, TContext> phaseContentFindPhase = findPhase(phase);
        if (phaseContentFindPhase == null) {
            throw new InvalidPhaseException("Phase " + phase + " was not registered for this pipeline");
        }
        q0.e(3, block);
        if (tryAddToPhaseFastPath(phase, block)) {
            this.interceptorsQuantity++;
            return;
        }
        phaseContentFindPhase.addInterceptor(block);
        this.interceptorsQuantity++;
        resetInterceptorsList();
        afterIntercepted();
    }

    public final List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> interceptorsForPhase(PipelinePhase phase) {
        Object next;
        List<Object> list = this.phasesRaw;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof PhaseContent) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (p.a(((PhaseContent) next).getPhase(), phase)) {
                break;
            }
        }
        PhaseContent phaseContent = (PhaseContent) next;
        List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> listSharedInterceptors = phaseContent != null ? phaseContent.sharedInterceptors() : null;
        return listSharedInterceptors == null ? z.f19728i : listSharedInterceptors;
    }

    public final List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> interceptorsForTests$ktor_utils() {
        List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> interceptors = getInterceptors();
        return interceptors == null ? cacheInterceptors() : interceptors;
    }

    public final boolean isEmpty() {
        return this.interceptorsQuantity == 0;
    }

    public final void merge(Pipeline<TSubject, TContext> from) {
        if (fastPathMerge(from)) {
            return;
        }
        mergePhases(from);
        mergeInterceptors(from);
    }

    public final void mergePhases(Pipeline<TSubject, TContext> from) {
        ArrayList arrayList = new ArrayList(from.phasesRaw);
        while (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                PipelinePhase phase = next instanceof PipelinePhase ? (PipelinePhase) next : null;
                if (phase == null) {
                    phase = ((PhaseContent) next).getPhase();
                }
                if (hasPhase(phase)) {
                    it.remove();
                } else if (insertRelativePhase(next, phase)) {
                    it.remove();
                }
            }
        }
    }

    public final List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> phaseInterceptors$ktor_utils(PipelinePhase phase) {
        List<q<PipelineContext<TSubject, TContext>, TSubject, d<? super t0>, Object>> listSharedInterceptors;
        PhaseContent<TSubject, TContext> phaseContentFindPhase = findPhase(phase);
        return (phaseContentFindPhase == null || (listSharedInterceptors = phaseContentFindPhase.sharedInterceptors()) == null) ? z.f19728i : listSharedInterceptors;
    }

    public final void resetFrom(Pipeline<TSubject, TContext> from) {
        this.phasesRaw.clear();
        if (this.interceptorsQuantity != 0) {
            throw new IllegalStateException("Check failed.");
        }
        fastPathMerge(from);
    }

    public Pipeline(PipelinePhase pipelinePhase, List<? extends q<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super d<? super t0>, ? extends Object>> list) {
        this(pipelinePhase);
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            intercept(pipelinePhase, (q) it.next());
        }
    }
}

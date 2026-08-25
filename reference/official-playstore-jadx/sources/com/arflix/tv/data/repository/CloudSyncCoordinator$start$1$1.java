package com.arflix.tv.data.repository;

import androidx.media3.container.MdtaMetadataEntry;
import kotlin.Metadata;
import na.u0;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.CloudSyncCoordinator$start$1$1", f = "CloudSyncCoordinator.kt", l = {55}, m = "invokeSuspend", v = 2)
public final class CloudSyncCoordinator$start$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    int label;
    final /* synthetic */ CloudSyncCoordinator this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CloudSyncCoordinator$start$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/arflix/tv/data/repository/CloudSyncInvalidation;", "invalidation", "Lx6/t0;", "<anonymous>", "(Lcom/arflix/tv/data/repository/CloudSyncInvalidation;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CloudSyncCoordinator$start$1$1$1", f = "CloudSyncCoordinator.kt", l = {56, 58, MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<CloudSyncInvalidation, d7.d<? super t0>, Object> {
        /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ CloudSyncCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(CloudSyncCoordinator cloudSyncCoordinator, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = cloudSyncCoordinator;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(CloudSyncInvalidation cloudSyncInvalidation, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(cloudSyncInvalidation, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x0092, code lost:
        
            if (r11.markLocalStateDirtyNow(r10) == r7) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x00ab, code lost:
        
            if (r11.markLocalStateDirtyNow(r10) == r7) goto L30;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instruction units count: 212
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CloudSyncCoordinator$start$1$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloudSyncCoordinator$start$1$1(CloudSyncCoordinator cloudSyncCoordinator, d7.d<? super CloudSyncCoordinator$start$1$1> dVar) {
        super(2, dVar);
        this.this$0 = cloudSyncCoordinator;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new CloudSyncCoordinator$start$1$1(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            u0<CloudSyncInvalidation> events = this.this$0.invalidationBus.getEvents();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
            this.label = 1;
            Object objG = y0.g(events, anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            if (objG == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((CloudSyncCoordinator$start$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}

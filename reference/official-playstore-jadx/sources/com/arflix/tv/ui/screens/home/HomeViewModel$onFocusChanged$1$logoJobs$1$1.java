package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.MediaRepository;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/x;", "", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$onFocusChanged$1$logoJobs$1$1", f = "HomeViewModel.kt", l = {4255}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$onFocusChanged$1$logoJobs$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.x>, Object> {
    final /* synthetic */ MediaItem $item;
    Object L$0;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$onFocusChanged$1$logoJobs$1$1(MediaItem mediaItem, HomeViewModel homeViewModel, d7.d<? super HomeViewModel$onFocusChanged$1$logoJobs$1$1> dVar) {
        super(2, dVar);
        this.$item = mediaItem;
        this.this$0 = homeViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeViewModel$onFocusChanged$1$logoJobs$1$1(this.$item, this.this$0, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [int] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object] */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object obj3 = this.label;
        x6.x xVar = null;
        try {
            try {
                if (obj3 == 0) {
                    k2.c.G(obj);
                    String str = this.$item.getMediaType() + "_" + this.$item.getId();
                    MediaRepository mediaRepository = this.this$0.mediaRepository;
                    MediaType mediaType = this.$item.getMediaType();
                    int id = this.$item.getId();
                    this.L$0 = str;
                    this.label = 1;
                    obj = mediaRepository.getLogoUrl(mediaType, id, this);
                    e7.a aVar = e7.a.f15033i;
                    obj3 = str;
                    if (obj == aVar) {
                        return aVar;
                    }
                } else {
                    if (obj3 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    String str2 = (String) this.L$0;
                    k2.c.G(obj);
                    obj3 = str2;
                }
                String str3 = (String) obj;
                obj2 = obj3;
                if (str3 != null) {
                    xVar = new x6.x(obj3, str3);
                    obj2 = obj3;
                }
            } catch (Exception e5) {
                if (e5 instanceof CancellationException) {
                    throw e5;
                }
                obj2 = obj3;
            }
            return xVar;
        } finally {
            this.this$0.logoFetchInFlight.remove(obj3);
        }
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.x> dVar) {
        return ((HomeViewModel$onFocusChanged$1$logoJobs$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}

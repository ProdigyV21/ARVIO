package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.api.TraktScrobbleBody;
import com.arflix.tv.data.api.TraktScrobbleResponse;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TraktScrobbleResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository$scrobbleStop$response$1", f = "TraktRepository.kt", l = {868, 869}, m = "invokeSuspend", v = 2)
public final class TraktRepository$scrobbleStop$response$1 extends f7.j implements r7.l<d7.d<? super TraktScrobbleResponse>, Object> {
    final /* synthetic */ TraktScrobbleBody $body;
    Object L$0;
    int label;
    final /* synthetic */ TraktRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktRepository$scrobbleStop$response$1(TraktRepository traktRepository, TraktScrobbleBody traktScrobbleBody, d7.d<? super TraktRepository$scrobbleStop$response$1> dVar) {
        super(1, dVar);
        this.this$0 = traktRepository;
        this.$body = traktScrobbleBody;
    }

    @Override // f7.a
    public final d7.d<t0> create(d7.d<?> dVar) {
        return new TraktRepository$scrobbleStop$response$1(this.this$0, this.$body, dVar);
    }

    @Override // r7.l
    public final Object invoke(d7.d<? super TraktScrobbleResponse> dVar) {
        return ((TraktRepository$scrobbleStop$response$1) create(dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        e7.a aVar = e7.a.f15033i;
        if (i10 == 0) {
            k2.c.G(obj);
            TraktRepository traktRepository = this.this$0;
            this.label = 1;
            obj = traktRepository.getAuthHeader(this);
            if (obj != aVar) {
            }
        }
        if (i10 != 1) {
            if (i10 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        String str = (String) obj;
        if (str == null) {
            throw new IllegalStateException("Missing auth");
        }
        TraktApi traktApi = this.this$0.traktApi;
        String str2 = this.this$0.clientId;
        TraktScrobbleBody traktScrobbleBody = this.$body;
        this.L$0 = null;
        this.label = 2;
        Object objScrobbleStop = traktApi.scrobbleStop(str, str2, "2", traktScrobbleBody, this);
        return objScrobbleStop == aVar ? aVar : objScrobbleStop;
    }
}

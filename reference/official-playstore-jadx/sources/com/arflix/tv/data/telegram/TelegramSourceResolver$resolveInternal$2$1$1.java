package com.arflix.tv.data.telegram;

import android.util.Log;
import f7.j;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.z;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/telegram/TelegramVideoMessage;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.telegram.TelegramSourceResolver$resolveInternal$2$1$1", f = "TelegramSourceResolver.kt", l = {115}, m = "invokeSuspend", v = 2)
public final class TelegramSourceResolver$resolveInternal$2$1$1 extends j implements p<k0, d7.d<? super List<? extends TelegramVideoMessage>>, Object> {
    final /* synthetic */ Set<Long> $excludedIds;
    final /* synthetic */ String $query;
    int label;
    final /* synthetic */ TelegramSourceResolver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TelegramSourceResolver$resolveInternal$2$1$1(TelegramSourceResolver telegramSourceResolver, String str, Set<Long> set, d7.d<? super TelegramSourceResolver$resolveInternal$2$1$1> dVar) {
        super(2, dVar);
        this.this$0 = telegramSourceResolver;
        this.$query = str;
        this.$excludedIds = set;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TelegramSourceResolver$resolveInternal$2$1$1(this.this$0, this.$query, this.$excludedIds, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Exception {
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                TelegramRepository telegramRepository = this.this$0.repository;
                String str = this.$query;
                this.label = 1;
                obj = telegramRepository.searchVideoMessages(str, 100, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            Set<Long> set = this.$excludedIds;
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : (Iterable) obj) {
                if (!set.contains(new Long(((TelegramVideoMessage) obj2).getChatId()))) {
                    arrayList.add(obj2);
                }
            }
            return arrayList;
        } catch (TelegramApiException e5) {
            throw e5;
        } catch (CancellationException e6) {
            throw e6;
        } catch (Exception e10) {
            if (e10 instanceof CancellationException) {
                throw e10;
            }
            Log.e("TelegramResolver", "Search failed for '" + this.$query + "'", e10);
            return z.f19728i;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super List<TelegramVideoMessage>> dVar) {
        return ((TelegramSourceResolver$resolveInternal$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}

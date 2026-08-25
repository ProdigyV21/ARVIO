package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import retrofit2.r0;
import x6.t0;
import zc.i;
import zc.k;
import zc.o;
import zc.s;
import zc.t;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¤\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\u0003\u001a\u00020\u0007H§@¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\f\u001a\u00020\b2\b\b\u0001\u0010\u0003\u001a\u00020\u000bH§@¢\u0006\u0004\b\f\u0010\rJ.\u0010\u0013\u001a\u00020\u00122\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000eH§@¢\u0006\u0004\b\u0013\u0010\u0014JX\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u00152\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u000eH§@¢\u0006\u0004\b\u001b\u0010\u001cJX\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u00152\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u000eH§@¢\u0006\u0004\b\u001e\u0010\u001cJX\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u000e2\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u0015H§@¢\u0006\u0004\b!\u0010\"J8\u0010&\u001a\u00020%2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010$\u001a\u00020#H§@¢\u0006\u0004\b&\u0010'J8\u0010+\u001a\u00020*2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020(H§@¢\u0006\u0004\b+\u0010,J8\u0010-\u001a\u00020*2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020(H§@¢\u0006\u0004\b-\u0010,J8\u00100\u001a\u00020/2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020.H§@¢\u0006\u0004\b0\u00101J8\u00102\u001a\u00020/2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020.H§@¢\u0006\u0004\b2\u00101J8\u00103\u001a\u00020/2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020.H§@¢\u0006\u0004\b3\u00101J4\u00106\u001a\b\u0012\u0004\u0012\u0002050\u00192\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0001\u00104\u001a\u00020\u00152\b\b\u0001\u0010\u001f\u001a\u00020\u000eH§@¢\u0006\u0004\b6\u00107JR\u0010:\u001a\b\u0012\u0004\u0012\u0002090\u00192\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u00108\u001a\u00020\u000e2\b\b\u0003\u0010\u0016\u001a\u00020\u00152\b\b\u0003\u0010\u0017\u001a\u00020\u00152\b\b\u0003\u0010\u0018\u001a\u00020\u000eH§@¢\u0006\u0004\b:\u0010;J>\u0010=\u001a\b\u0012\u0004\u0012\u00020<0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0003\u0010\u0018\u001a\u00020\u000eH§@¢\u0006\u0004\b=\u0010>J>\u0010@\u001a\b\u0012\u0004\u0012\u00020?0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0003\u0010\u0018\u001a\u00020\u000eH§@¢\u0006\u0004\b@\u0010>J8\u0010B\u001a\u00020*2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020AH§@¢\u0006\u0004\bB\u0010CJ8\u0010D\u001a\u00020*2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020AH§@¢\u0006\u0004\bD\u0010CJ4\u0010F\u001a\b\u0012\u0004\u0012\u00020E0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000eH§@¢\u0006\u0004\bF\u0010\u0014J4\u0010G\u001a\b\u0012\u0004\u0012\u00020E0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000eH§@¢\u0006\u0004\bG\u0010\u0014J4\u0010H\u001a\b\u0012\u0004\u0012\u00020E0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000eH§@¢\u0006\u0004\bH\u0010\u0014J8\u0010J\u001a\u00020*2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020IH§@¢\u0006\u0004\bJ\u0010KJ8\u0010L\u001a\u00020*2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020IH§@¢\u0006\u0004\bL\u0010KJR\u0010P\u001a\b\u0012\u0004\u0012\u00020O0\u00192\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010M\u001a\u00020\u000e2\b\b\u0003\u0010N\u001a\u00020\u000e2\b\b\u0003\u0010\u0016\u001a\u00020\u00152\b\b\u0003\u0010\u0017\u001a\u00020\u0015H§@¢\u0006\u0004\bP\u0010QJR\u0010S\u001a\b\u0012\u0004\u0012\u00020O0\u00192\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010R\u001a\u00020\u000e2\b\b\u0003\u0010N\u001a\u00020\u000e2\b\b\u0003\u0010\u0016\u001a\u00020\u00152\b\b\u0003\u0010\u0017\u001a\u00020\u0015H§@¢\u0006\u0004\bS\u0010QJ\\\u0010U\u001a\b\u0012\u0004\u0012\u00020O0\u00192\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010R\u001a\u00020\u000e2\b\b\u0001\u0010T\u001a\u00020\u00152\b\b\u0003\u0010N\u001a\u00020\u000e2\b\b\u0003\u0010\u0016\u001a\u00020\u00152\b\b\u0003\u0010\u0017\u001a\u00020\u0015H§@¢\u0006\u0004\bU\u0010VJf\u0010X\u001a\b\u0012\u0004\u0012\u00020O0\u00192\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010R\u001a\u00020\u000e2\b\b\u0001\u0010T\u001a\u00020\u00152\b\b\u0001\u0010W\u001a\u00020\u00152\b\b\u0003\u0010N\u001a\u00020\u000e2\b\b\u0003\u0010\u0016\u001a\u00020\u00152\b\b\u0003\u0010\u0017\u001a\u00020\u0015H§@¢\u0006\u0004\bX\u0010YJT\u0010\\\u001a\b\u0012\u0004\u0012\u00020[0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0003\u0010\u0016\u001a\u00020\u00152\b\b\u0003\u0010\u0017\u001a\u00020\u00152\n\b\u0003\u0010Z\u001a\u0004\u0018\u00010\u000eH§@¢\u0006\u0004\b\\\u0010;JT\u0010]\u001a\b\u0012\u0004\u0012\u00020[0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0003\u0010\u0016\u001a\u00020\u00152\b\b\u0003\u0010\u0017\u001a\u00020\u00152\n\b\u0003\u0010Z\u001a\u0004\u0018\u00010\u000eH§@¢\u0006\u0004\b]\u0010;J8\u0010_\u001a\u00020*2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020^H§@¢\u0006\u0004\b_\u0010`JJ\u0010b\u001a\b\u0012\u0004\u0012\u00020a0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u000e2\b\b\u0003\u0010\u0018\u001a\u00020\u000eH§@¢\u0006\u0004\bb\u0010cJd\u0010e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020a0\u00190d2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u000e2\b\b\u0003\u0010\u0018\u001a\u00020\u000e2\b\b\u0001\u0010\u0016\u001a\u00020\u00152\b\b\u0001\u0010\u0017\u001a\u00020\u0015H§@¢\u0006\u0004\be\u0010fJb\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020a0\u00190d2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010\u001f\u001a\u00020\u000e2\b\b\u0003\u0010\u0018\u001a\u00020\u000e2\b\b\u0001\u0010\u0016\u001a\u00020\u00152\b\b\u0001\u0010\u0017\u001a\u00020\u0015H§@¢\u0006\u0004\bg\u0010fJ8\u0010i\u001a\u00020*2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020hH§@¢\u0006\u0004\bi\u0010jJ8\u0010k\u001a\u00020*2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010)\u001a\u00020hH§@¢\u0006\u0004\bk\u0010jJV\u0010p\u001a\u00020o2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010R\u001a\u00020\u000e2\b\b\u0003\u0010l\u001a\u00020\u000e2\b\b\u0003\u0010m\u001a\u00020\u000e2\b\b\u0003\u0010n\u001a\u00020\u000eH§@¢\u0006\u0004\bp\u0010qJT\u0010s\u001a\b\u0012\u0004\u0012\u00020r0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0003\u0010\u001f\u001a\u00020\u000e2\b\b\u0003\u0010\u0017\u001a\u00020\u00152\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0015H§@¢\u0006\u0004\bs\u0010tJT\u0010u\u001a\b\u0012\u0004\u0012\u00020r0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0003\u0010\u001f\u001a\u00020\u000e2\b\b\u0003\u0010\u0017\u001a\u00020\u00152\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0015H§@¢\u0006\u0004\bu\u0010tJ*\u0010w\u001a\b\u0012\u0004\u0012\u00020v0\u00192\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000eH§@¢\u0006\u0004\bw\u0010xJ4\u0010z\u001a\b\u0012\u0004\u0012\u00020y0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000eH§@¢\u0006\u0004\bz\u0010\u0014Jf\u0010}\u001a\b\u0012\u0004\u0012\u00020|0\u00192\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010{\u001a\u00020\u000e2\b\b\u0001\u0010\u001f\u001a\u00020\u000e2\b\b\u0003\u0010\u0018\u001a\u00020\u000e2\b\b\u0003\u0010\u0016\u001a\u00020\u00152\b\b\u0003\u0010\u0017\u001a\u00020\u0015H§@¢\u0006\u0004\b}\u0010~J;\u0010\u0081\u0001\u001a\u00030\u0080\u00012\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010\u007f\u001a\u00020\u000e2\b\b\u0001\u0010{\u001a\u00020\u000eH§@¢\u0006\u0005\b\u0081\u0001\u0010>Jh\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020|0\u00192\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010\u007f\u001a\u00020\u000e2\b\b\u0001\u0010{\u001a\u00020\u000e2\b\b\u0001\u0010\u001f\u001a\u00020\u000e2\b\b\u0003\u0010\u0018\u001a\u00020\u000e2\b\b\u0003\u0010\u0016\u001a\u00020\u00152\b\b\u0003\u0010\u0017\u001a\u00020\u0015H§@¢\u0006\u0005\b\u0082\u0001\u0010~J1\u0010\u0083\u0001\u001a\u00030\u0080\u00012\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010{\u001a\u00020\u000eH§@¢\u0006\u0005\b\u0083\u0001\u0010\u0014J^\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020|0\u00192\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010{\u001a\u00020\u000e2\b\b\u0001\u0010\u001f\u001a\u00020\u000e2\b\b\u0003\u0010\u0018\u001a\u00020\u000e2\b\b\u0003\u0010\u0016\u001a\u00020\u00152\b\b\u0003\u0010\u0017\u001a\u00020\u0015H§@¢\u0006\u0005\b\u0084\u0001\u0010fJ1\u0010\u0086\u0001\u001a\u00030\u0085\u00012\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0003\u0010\u0011\u001a\u00020\u000eH§@¢\u0006\u0005\b\u0086\u0001\u0010\u0014¨\u0006\u0087\u0001À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/api/TraktApi;", "", "Lcom/arflix/tv/data/api/DeviceCodeRequest;", "request", "Lcom/arflix/tv/data/api/TraktDeviceCode;", "getDeviceCode", "(Lcom/arflix/tv/data/api/DeviceCodeRequest;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TokenPollRequest;", "Lcom/arflix/tv/data/api/TraktToken;", "pollToken", "(Lcom/arflix/tv/data/api/TokenPollRequest;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/RefreshTokenRequest;", "refreshToken", "(Lcom/arflix/tv/data/api/RefreshTokenRequest;Ld7/d;)Ljava/lang/Object;", "", "auth", "clientId", "version", "Lcom/arflix/tv/data/api/TraktLastActivities;", "getLastActivities", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "", "page", "limit", "extended", "", "Lcom/arflix/tv/data/api/TraktWatchedMovie;", "getWatchedMovies", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TraktWatchedShow;", "getWatchedShows", LinkHeader.Parameters.Type, "Lcom/arflix/tv/data/api/TraktPlaybackItem;", "getPlaybackProgress", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "", TtmlNode.ATTR_ID, "Lx6/t0;", "removePlaybackItem", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLd7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TraktHistoryBody;", TtmlNode.TAG_BODY, "Lcom/arflix/tv/data/api/TraktSyncResponse;", "addToHistory", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktHistoryBody;Ld7/d;)Ljava/lang/Object;", "removeFromHistory", "Lcom/arflix/tv/data/api/TraktScrobbleBody;", "Lcom/arflix/tv/data/api/TraktScrobbleResponse;", "scrobbleStart", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktScrobbleBody;Ld7/d;)Ljava/lang/Object;", "scrobblePause", "scrobbleStop", "tmdbId", "Lcom/arflix/tv/data/api/TraktSearchResult;", "searchByTmdb", "(Ljava/lang/String;ILjava/lang/String;Ld7/d;)Ljava/lang/Object;", "query", "Lcom/arflix/tv/data/api/TraktListSearchResult;", "searchLists", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TraktCollectionMovie;", "getCollectionMovies", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TraktCollectionShow;", "getCollectionShows", "Lcom/arflix/tv/data/api/TraktCollectionBody;", "addToCollection", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktCollectionBody;Ld7/d;)Ljava/lang/Object;", "removeFromCollection", "Lcom/arflix/tv/data/api/TraktRatingItem;", "getRatingsMovies", "getRatingsShows", "getRatingsEpisodes", "Lcom/arflix/tv/data/api/TraktRatingBody;", "addRating", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktRatingBody;Ld7/d;)Ljava/lang/Object;", "removeRating", "movieId", "sort", "Lcom/arflix/tv/data/api/TraktComment;", "getMovieComments", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILd7/d;)Ljava/lang/Object;", "showId", "getShowComments", "season", "getSeasonComments", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;IILd7/d;)Ljava/lang/Object;", "episode", "getEpisodeComments", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;IILd7/d;)Ljava/lang/Object;", "startAt", "Lcom/arflix/tv/data/api/TraktHistoryItem;", "getHistoryMovies", "getHistoryEpisodes", "Lcom/arflix/tv/data/api/TraktHistoryRemoveBody;", "removeFromHistoryByIds", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktHistoryRemoveBody;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TraktWatchlistItem;", "getWatchlist", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lretrofit2/r0;", "getWatchlistPage", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILd7/d;)Ljava/lang/Object;", "getWatchlistAddedPage", "Lcom/arflix/tv/data/api/TraktWatchlistBody;", "addToWatchlist", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktWatchlistBody;Ld7/d;)Ljava/lang/Object;", "removeFromWatchlist", "hidden", "specials", "countSpecials", "Lcom/arflix/tv/data/api/TraktShowProgress;", "getShowProgress", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TraktHiddenItem;", "getHiddenProgressShows", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "getHiddenProgressResetShows", "Lcom/arflix/tv/data/api/TraktListItem;", "getTrendingAnime", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TraktSearchList;", "getMyLists", "listId", "Lcom/arflix/tv/data/api/TraktPublicListItem;", "getMyListItems", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILd7/d;)Ljava/lang/Object;", "username", "Lcom/arflix/tv/data/api/TraktPublicListSummary;", "getUserListSummary", "getUserListItems", "getListSummary", "getListItems", "Lcom/arflix/tv/data/api/TraktUserProfile;", "getMe", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface TraktApi {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    @k({"Content-Type: application/json"})
    @o("sync/ratings")
    Object addRating(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktRatingBody traktRatingBody, d7.d<? super TraktSyncResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("sync/collection")
    Object addToCollection(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktCollectionBody traktCollectionBody, d7.d<? super TraktSyncResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("sync/history")
    Object addToHistory(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktHistoryBody traktHistoryBody, d7.d<? super TraktSyncResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("sync/watchlist")
    Object addToWatchlist(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktWatchlistBody traktWatchlistBody, d7.d<? super TraktSyncResponse> dVar);

    @zc.f("sync/collection/movies")
    Object getCollectionMovies(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t("extended") String str4, d7.d<? super List<TraktCollectionMovie>> dVar);

    @zc.f("sync/collection/shows")
    Object getCollectionShows(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t("extended") String str4, d7.d<? super List<TraktCollectionShow>> dVar);

    @k({"Content-Type: application/json"})
    @o("oauth/device/code")
    Object getDeviceCode(@zc.a DeviceCodeRequest deviceCodeRequest, d7.d<? super TraktDeviceCode> dVar);

    @zc.f("shows/{id}/seasons/{season}/episodes/{episode}/comments/{sort}")
    Object getEpisodeComments(@i("trakt-api-key") String str, @i("trakt-api-version") String str2, @s(TtmlNode.ATTR_ID) String str3, @s("season") int i10, @s("episode") int i11, @s("sort") String str4, @t("page") int i12, @t("limit") int i13, d7.d<? super List<TraktComment>> dVar);

    @zc.f("users/hidden/progress_watched_reset")
    Object getHiddenProgressResetShows(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t(LinkHeader.Parameters.Type) String str4, @t("limit") int i10, @t("page") Integer num, d7.d<? super List<TraktHiddenItem>> dVar);

    @zc.f("users/hidden/progress_watched")
    Object getHiddenProgressShows(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t(LinkHeader.Parameters.Type) String str4, @t("limit") int i10, @t("page") Integer num, d7.d<? super List<TraktHiddenItem>> dVar);

    @zc.f("users/me/history/episodes")
    @k({"Content-Type: application/json"})
    Object getHistoryEpisodes(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t("page") int i10, @t("limit") int i11, @t("start_at") String str4, d7.d<? super List<TraktHistoryItem>> dVar);

    @zc.f("users/me/history/movies")
    @k({"Content-Type: application/json"})
    Object getHistoryMovies(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t("page") int i10, @t("limit") int i11, @t("start_at") String str4, d7.d<? super List<TraktHistoryItem>> dVar);

    @zc.f("sync/last_activities")
    @k({"Content-Type: application/json"})
    Object getLastActivities(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, d7.d<? super TraktLastActivities> dVar);

    @zc.f("lists/{listId}/items/{type}")
    Object getListItems(@i("trakt-api-key") String str, @i("trakt-api-version") String str2, @s("listId") String str3, @s(LinkHeader.Parameters.Type) String str4, @t("extended") String str5, @t("page") int i10, @t("limit") int i11, d7.d<? super List<TraktPublicListItem>> dVar);

    @zc.f("lists/{listId}")
    Object getListSummary(@i("trakt-api-key") String str, @i("trakt-api-version") String str2, @s("listId") String str3, d7.d<? super TraktPublicListSummary> dVar);

    @zc.f("users/me")
    @k({"Content-Type: application/json"})
    Object getMe(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, d7.d<? super TraktUserProfile> dVar);

    @zc.f("movies/{id}/comments/{sort}")
    Object getMovieComments(@i("trakt-api-key") String str, @i("trakt-api-version") String str2, @s(TtmlNode.ATTR_ID) String str3, @s("sort") String str4, @t("page") int i10, @t("limit") int i11, d7.d<? super List<TraktComment>> dVar);

    @zc.f("users/me/lists/{listId}/items/{type}")
    Object getMyListItems(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @s("listId") String str4, @s(LinkHeader.Parameters.Type) String str5, @t("extended") String str6, @t("page") int i10, @t("limit") int i11, d7.d<? super List<TraktPublicListItem>> dVar);

    @zc.f("users/me/lists")
    Object getMyLists(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, d7.d<? super List<TraktSearchList>> dVar);

    @zc.f("sync/playback")
    @k({"Content-Type: application/json"})
    Object getPlaybackProgress(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t(LinkHeader.Parameters.Type) String str4, @t("page") Integer num, @t("limit") Integer num2, d7.d<? super List<TraktPlaybackItem>> dVar);

    @zc.f("sync/ratings/episodes")
    Object getRatingsEpisodes(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, d7.d<? super List<TraktRatingItem>> dVar);

    @zc.f("sync/ratings/movies")
    Object getRatingsMovies(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, d7.d<? super List<TraktRatingItem>> dVar);

    @zc.f("sync/ratings/shows")
    Object getRatingsShows(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, d7.d<? super List<TraktRatingItem>> dVar);

    @zc.f("shows/{id}/seasons/{season}/comments/{sort}")
    Object getSeasonComments(@i("trakt-api-key") String str, @i("trakt-api-version") String str2, @s(TtmlNode.ATTR_ID) String str3, @s("season") int i10, @s("sort") String str4, @t("page") int i11, @t("limit") int i12, d7.d<? super List<TraktComment>> dVar);

    @zc.f("shows/{id}/comments/{sort}")
    Object getShowComments(@i("trakt-api-key") String str, @i("trakt-api-version") String str2, @s(TtmlNode.ATTR_ID) String str3, @s("sort") String str4, @t("page") int i10, @t("limit") int i11, d7.d<? super List<TraktComment>> dVar);

    @zc.f("shows/{id}/progress/watched")
    Object getShowProgress(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @s(TtmlNode.ATTR_ID) String str4, @t("hidden") String str5, @t("specials") String str6, @t("count_specials") String str7, d7.d<? super TraktShowProgress> dVar);

    @zc.f("lists/anime-streaming/anime-trending/items")
    Object getTrendingAnime(@i("trakt-api-key") String str, @i("trakt-api-version") String str2, d7.d<? super List<TraktListItem>> dVar);

    @zc.f("users/{username}/lists/{listId}/items/{type}")
    Object getUserListItems(@i("trakt-api-key") String str, @i("trakt-api-version") String str2, @s("username") String str3, @s("listId") String str4, @s(LinkHeader.Parameters.Type) String str5, @t("extended") String str6, @t("page") int i10, @t("limit") int i11, d7.d<? super List<TraktPublicListItem>> dVar);

    @zc.f("users/{username}/lists/{listId}")
    Object getUserListSummary(@i("trakt-api-key") String str, @i("trakt-api-version") String str2, @s("username") String str3, @s("listId") String str4, d7.d<? super TraktPublicListSummary> dVar);

    @zc.f("sync/watched/movies")
    @k({"Content-Type: application/json"})
    Object getWatchedMovies(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t("page") Integer num, @t("limit") Integer num2, @t("extended") String str4, d7.d<? super List<TraktWatchedMovie>> dVar);

    @zc.f("sync/watched/shows")
    @k({"Content-Type: application/json"})
    Object getWatchedShows(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t("page") Integer num, @t("limit") Integer num2, @t("extended") String str4, d7.d<? super List<TraktWatchedShow>> dVar);

    @zc.f("users/me/watchlist")
    Object getWatchlist(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t(LinkHeader.Parameters.Type) String str4, @t("extended") String str5, d7.d<? super List<TraktWatchlistItem>> dVar);

    @zc.f("users/me/watchlist/{type}/added")
    Object getWatchlistAddedPage(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @s(LinkHeader.Parameters.Type) String str4, @t("extended") String str5, @t("page") int i10, @t("limit") int i11, d7.d<? super r0> dVar);

    @zc.f("users/me/watchlist")
    Object getWatchlistPage(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @t(LinkHeader.Parameters.Type) String str4, @t("extended") String str5, @t("page") int i10, @t("limit") int i11, d7.d<? super r0> dVar);

    @k({"Content-Type: application/json"})
    @o("oauth/device/token")
    Object pollToken(@zc.a TokenPollRequest tokenPollRequest, d7.d<? super TraktToken> dVar);

    @k({"Content-Type: application/json"})
    @o("oauth/token")
    Object refreshToken(@zc.a RefreshTokenRequest refreshTokenRequest, d7.d<? super TraktToken> dVar);

    @k({"Content-Type: application/json"})
    @o("sync/collection/remove")
    Object removeFromCollection(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktCollectionBody traktCollectionBody, d7.d<? super TraktSyncResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("sync/history/remove")
    Object removeFromHistory(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktHistoryBody traktHistoryBody, d7.d<? super TraktSyncResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("sync/history/remove")
    Object removeFromHistoryByIds(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktHistoryRemoveBody traktHistoryRemoveBody, d7.d<? super TraktSyncResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("sync/watchlist/remove")
    Object removeFromWatchlist(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktWatchlistBody traktWatchlistBody, d7.d<? super TraktSyncResponse> dVar);

    @zc.b("sync/playback/{id}")
    Object removePlaybackItem(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @s(TtmlNode.ATTR_ID) long j10, d7.d<? super t0> dVar);

    @k({"Content-Type: application/json"})
    @o("sync/ratings/remove")
    Object removeRating(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktRatingBody traktRatingBody, d7.d<? super TraktSyncResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("scrobble/pause")
    Object scrobblePause(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktScrobbleBody traktScrobbleBody, d7.d<? super TraktScrobbleResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("scrobble/start")
    Object scrobbleStart(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktScrobbleBody traktScrobbleBody, d7.d<? super TraktScrobbleResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("scrobble/stop")
    Object scrobbleStop(@i("Authorization") String str, @i("trakt-api-key") String str2, @i("trakt-api-version") String str3, @zc.a TraktScrobbleBody traktScrobbleBody, d7.d<? super TraktScrobbleResponse> dVar);

    @zc.f("search/tmdb/{id}")
    Object searchByTmdb(@i("trakt-api-key") String str, @s(TtmlNode.ATTR_ID) int i10, @t(LinkHeader.Parameters.Type) String str2, d7.d<? super List<TraktSearchResult>> dVar);

    @zc.f("search/list")
    Object searchLists(@i("trakt-api-key") String str, @i("trakt-api-version") String str2, @t("query") String str3, @t("page") int i10, @t("limit") int i11, @t("extended") String str4, d7.d<? super List<TraktListSearchResult>> dVar);
}

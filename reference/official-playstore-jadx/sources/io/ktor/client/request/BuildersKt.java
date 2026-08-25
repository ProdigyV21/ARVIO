package io.ktor.client.request;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import g8.b;
import io.ktor.client.HttpClient;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HttpMethod;
import io.ktor.http.Url;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\u001a!\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a!\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u0005\u001a0\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\f\u001a0\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\f\u001a:\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u000f\u001a:\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u000f\u001a:\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00102\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0012\u001a:\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00102\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u0012\u001a\u001f\u0010\u0013\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0005\u001a\u001f\u0010\u0014\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0005\u001a\u001f\u0010\u0015\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0005\u001a\u001f\u0010\u0016\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0005\u001a\u001f\u0010\u0017\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0005\u001a\u001f\u0010\u0018\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0005\u001a\u001f\u0010\u0019\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0005\u001a\u001f\u0010\u001a\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005\u001a\u001f\u0010\u001b\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0005\u001a\u001f\u0010\u001c\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0005\u001a\u001f\u0010\u001d\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0005\u001a\u001f\u0010\u001e\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0005\u001a\u001f\u0010\u001f\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0005\u001a\u001f\u0010 \u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b \u0010\u0005\u001a0\u0010\u0013\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\f\u001a0\u0010\u0014\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\f\u001a0\u0010\u0015\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\f\u001a0\u0010\u0016\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\f\u001a0\u0010\u0017\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\f\u001a0\u0010\u0018\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\f\u001a0\u0010\u0019\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\f\u001a0\u0010\u001a\u001a\u00020\u0006*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\f\u001a0\u0010\u001b\u001a\u00020\u0006*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\f\u001a0\u0010\u001c\u001a\u00020\u0006*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\f\u001a0\u0010\u001d\u001a\u00020\u0006*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\f\u001a0\u0010\u001e\u001a\u00020\u0006*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\f\u001a0\u0010\u001f\u001a\u00020\u0006*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\f\u001a0\u0010 \u001a\u00020\u0006*\u00020\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b \u0010\f\u001a&\u0010\u0004\u001a\u00020\u00012\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n¢\u0006\u0004\b\u0004\u0010!\u001a:\u0010\u0013\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u000f\u001a:\u0010\u0014\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u000f\u001a:\u0010\u0015\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u000f\u001a:\u0010\u0016\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u000f\u001a:\u0010\u0017\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u000f\u001a:\u0010\u0018\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u000f\u001a:\u0010\u0019\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u000f\u001a:\u0010\u001a\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000f\u001a:\u0010\u001b\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000f\u001a:\u0010\u001c\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000f\u001a:\u0010\u001d\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u000f\u001a:\u0010\u001e\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u000f\u001a:\u0010\u001f\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u000f\u001a:\u0010 \u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\r2\u0019\b\u0002\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0086Hø\u0001\u0000¢\u0006\u0004\b \u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lio/ktor/client/HttpClient;", "Lio/ktor/client/request/HttpRequestBuilder;", "builder", "Lio/ktor/client/statement/HttpResponse;", "request", "(Lio/ktor/client/HttpClient;Lio/ktor/client/request/HttpRequestBuilder;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/statement/HttpStatement;", "prepareRequest", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "(Lio/ktor/client/HttpClient;Lr7/l;Ld7/d;)Ljava/lang/Object;", "", "urlString", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/Url;", "url", "(Lio/ktor/client/HttpClient;Lio/ktor/http/Url;Lr7/l;Ld7/d;)Ljava/lang/Object;", "get", "post", "put", "delete", "options", "patch", TtmlNode.TAG_HEAD, "prepareGet", "preparePost", "preparePut", "prepareDelete", "prepareOptions", "preparePatch", "prepareHead", "(Lr7/l;)Lio/ktor/client/request/HttpRequestBuilder;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BuildersKt {

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$delete$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements l<HttpRequestBuilder, t0> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$get$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17724 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17724 INSTANCE = new C17724();

        public C17724() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$head$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17734 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17734 INSTANCE = new C17734();

        public C17734() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$options$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17744 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17744 INSTANCE = new C17744();

        public C17744() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$patch$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17754 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17754 INSTANCE = new C17754();

        public C17754() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$post$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17764 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17764 INSTANCE = new C17764();

        public C17764() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$prepareDelete$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17774 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17774 INSTANCE = new C17774();

        public C17774() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$prepareGet$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17784 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17784 INSTANCE = new C17784();

        public C17784() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$prepareHead$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17794 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17794 INSTANCE = new C17794();

        public C17794() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$prepareOptions$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17804 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17804 INSTANCE = new C17804();

        public C17804() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$preparePatch$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17814 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17814 INSTANCE = new C17814();

        public C17814() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$preparePost$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17824 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17824 INSTANCE = new C17824();

        public C17824() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$preparePut$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17834 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17834 INSTANCE = new C17834();

        public C17834() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$prepareRequest$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17844 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17844 INSTANCE = new C17844();

        public C17844() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$prepareRequest$7, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass7 extends r implements l<HttpRequestBuilder, t0> {
        public static final AnonymousClass7 INSTANCE = new AnonymousClass7();

        public AnonymousClass7() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$put$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17854 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17854 INSTANCE = new C17854();

        public C17854() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$request$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17864 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17864 INSTANCE = new C17864();

        public C17864() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.request.BuildersKt$request$7, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/client/request/HttpRequestBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17877 extends r implements l<HttpRequestBuilder, t0> {
        public static final C17877 INSTANCE = new C17877();

        public C17877() {
            super(1);
        }

        public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpRequestBuilder) obj);
            return t0.f22605a;
        }
    }

    public static final Object delete(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    private static final Object delete$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object delete$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass4.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static final Object get(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    private static final Object get$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object get$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17724.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static final Object head(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    private static final Object head$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object head$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17734.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static final Object options(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    private static final Object options$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object options$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17744.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static final Object patch(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    private static final Object patch$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object patch$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17754.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static final Object post(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    private static final Object post$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object post$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17764.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static final Object prepareDelete(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    private static final Object prepareDelete$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static /* synthetic */ Object prepareDelete$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17774.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static final Object prepareGet(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    private static final Object prepareGet$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static /* synthetic */ Object prepareGet$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17784.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static final Object prepareHead(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    private static final Object prepareHead$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static /* synthetic */ Object prepareHead$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17794.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static final Object prepareOptions(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    private static final Object prepareOptions$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static /* synthetic */ Object prepareOptions$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17804.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static final Object preparePatch(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    private static final Object preparePatch$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static /* synthetic */ Object preparePatch$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17814.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static final Object preparePost(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    private static final Object preparePost$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static /* synthetic */ Object preparePost$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17824.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static final Object preparePut(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    private static final Object preparePut$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static /* synthetic */ Object preparePut$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17834.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilderF, httpClient);
    }

    public static final Object prepareRequest(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        return new HttpStatement(b.f(str, lVar), httpClient);
    }

    private static final Object prepareRequest$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        return new HttpStatement(b.f(str, lVar), httpClient);
    }

    public static /* synthetic */ Object prepareRequest$default(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            httpRequestBuilder = new HttpRequestBuilder();
        }
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object put(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    private static final Object put$$forInline(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object put$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17854.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilderF = b.f(str, lVar);
        httpRequestBuilderF.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilderF, httpClient).execute(dVar);
    }

    public static final HttpRequestBuilder request(l<? super HttpRequestBuilder, t0> lVar) {
        return b.g(lVar);
    }

    public static /* synthetic */ Object request$default(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            httpRequestBuilder = new HttpRequestBuilder();
        }
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object prepareRequest$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17844.INSTANCE;
        }
        return new HttpStatement(b.f(str, lVar), httpClient);
    }

    public static /* synthetic */ Object request$default(HttpClient httpClient, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17864.INSTANCE;
        }
        return new HttpStatement(b.f(str, lVar), httpClient).execute(dVar);
    }

    public static final Object request(HttpClient httpClient, String str, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return new HttpStatement(b.f(str, lVar), httpClient).execute(dVar);
    }

    public static final Object prepareRequest(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        return new HttpStatement(b.g(lVar), httpClient);
    }

    private static final Object prepareRequest$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        return new HttpStatement(b.g(lVar), httpClient);
    }

    public static final Object delete(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    private static final Object delete$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    public static final Object get(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    private static final Object get$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    public static final Object head(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    private static final Object head$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    public static final Object options(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    private static final Object options$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    public static final Object patch(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    private static final Object patch$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    public static final Object post(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    private static final Object post$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    public static final Object prepareDelete(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    private static final Object prepareDelete$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    public static final Object prepareGet(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    private static final Object prepareGet$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    public static final Object prepareHead(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    private static final Object prepareHead$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    public static final Object prepareOptions(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    private static final Object prepareOptions$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    public static final Object preparePatch(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    private static final Object preparePatch$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    public static final Object preparePost(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    private static final Object preparePost$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    public static final Object preparePut(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    private static final Object preparePut$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilderG, httpClient);
    }

    public static final Object put(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    private static final Object put$$forInline(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilderG = b.g(lVar);
        httpRequestBuilderG.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilderG, httpClient).execute(dVar);
    }

    public static /* synthetic */ Object prepareRequest$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass7.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        BuildersWithUrlKt.url(httpRequestBuilder, url);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static /* synthetic */ Object request$default(HttpClient httpClient, Url url, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C17877.INSTANCE;
        }
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        BuildersWithUrlKt.url(httpRequestBuilder, url);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object request(HttpClient httpClient, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        return new HttpStatement(b.g(lVar), httpClient).execute(dVar);
    }

    public static final Object prepareRequest(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareRequest$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object prepareRequest(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        BuildersWithUrlKt.url(httpRequestBuilder, url);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareRequest$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpStatement> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        BuildersWithUrlKt.url(httpRequestBuilder, url);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object request$$forInline(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        BuildersWithUrlKt.url(httpRequestBuilder, url);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object delete(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object delete$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object get(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object get$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object head(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object head$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object options(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object options$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object patch(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object patch$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object post(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object post$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object prepareDelete(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareDelete$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getDelete());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object prepareGet(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareGet$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object prepareHead(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareHead$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getHead());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object prepareOptions(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object prepareOptions$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getOptions());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object preparePatch(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object preparePatch$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPatch());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object preparePost(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object preparePost$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPost());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object preparePut(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    private static final Object preparePut$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpStatement> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    public static final Object put(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    private static final Object put$$forInline(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        httpRequestBuilder.setMethod(HttpMethod.INSTANCE.getPut());
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object request(HttpClient httpClient, HttpRequestBuilder httpRequestBuilder, d<? super HttpResponse> dVar) {
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }

    public static final Object request(HttpClient httpClient, Url url, l<? super HttpRequestBuilder, t0> lVar, d<? super HttpResponse> dVar) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        BuildersWithUrlKt.url(httpRequestBuilder, url);
        lVar.invoke(httpRequestBuilder);
        return new HttpStatement(httpRequestBuilder, httpClient).execute(dVar);
    }
}

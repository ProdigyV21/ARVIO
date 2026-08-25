package io.ktor.server.routing;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMethod;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import java.util.Arrays;
import java.util.List;
import k2.c;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import kotlin.text.u;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0013\u001a4\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a<\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\u000b\u001a4\u0010\n\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\n\u001a\u00020\t2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\n\u0010\r\u001a<\u0010\u0010\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a4\u0010\u0010\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0010\u0010\b\u001a4\u0010\u0012\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0012\u0010\b\u001a<\u0010\u0013\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0013\u0010\u0011\u001a4\u0010\u0016\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00142\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001a@\u0010\u0016\u001a\u00020\u0000*\u00020\u00002\u0012\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0018\"\u00020\u00142\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0016\u0010\u001a\u001a4\u0010\u0015\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00142\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0015\u0010\u0017\u001aY\u0010 \u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001aQ\u0010 \u001a\u00020\u0000*\u00020\u000029\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010\"\u001aY\u0010#\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010!\u001a`\u0010#\u001a\u00020\u0000\"\n\b\u0000\u0010$\u0018\u0001*\u00020\u001f*\u00020\u00002;\b\u0004\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010\"\u001ah\u0010#\u001a\u00020\u0000\"\n\b\u0000\u0010$\u0018\u0001*\u00020\u001f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012;\b\u0004\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010!\u001aQ\u0010#\u001a\u00020\u0000*\u00020\u000029\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010\"\u001aY\u0010'\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010!\u001aQ\u0010'\u001a\u00020\u0000*\u00020\u000029\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010\"\u001aY\u0010(\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010!\u001aQ\u0010(\u001a\u00020\u0000*\u00020\u000029\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010\"\u001a`\u0010(\u001a\u00020\u0000\"\n\b\u0000\u0010$\u0018\u0001*\u00020\u001f*\u00020\u00002;\b\u0004\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\"\u001ah\u0010(\u001a\u00020\u0000\"\n\b\u0000\u0010$\u0018\u0001*\u00020\u001f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012;\b\u0004\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010!\u001aY\u0010+\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010!\u001aQ\u0010+\u001a\u00020\u0000*\u00020\u000029\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010\"\u001a`\u0010+\u001a\u00020\u0000\"\n\b\u0000\u0010$\u0018\u0001*\u00020\u001f*\u00020\u00002;\b\u0004\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b,\u0010\"\u001ah\u0010+\u001a\u00020\u0000\"\n\b\u0000\u0010$\u0018\u0001*\u00020\u001f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012;\b\u0004\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b-\u0010!\u001aY\u0010.\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b.\u0010!\u001aQ\u0010.\u001a\u00020\u0000*\u00020\u000029\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b.\u0010\"\u001aY\u0010/\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u0010!\u001aQ\u0010/\u001a\u00020\u0000*\u00020\u000029\u0010\f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u0010\"\u001a\u0019\u00100\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b0\u00101\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"}, d2 = {"Lio/ktor/server/routing/Route;", "", "path", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "build", "route", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Lr7/l;)Lio/ktor/server/routing/Route;", "Lio/ktor/http/HttpMethod;", "method", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Lio/ktor/http/HttpMethod;Lr7/l;)Lio/ktor/server/routing/Route;", TtmlNode.TAG_BODY, "(Lio/ktor/server/routing/Route;Lio/ktor/http/HttpMethod;Lr7/l;)Lio/ktor/server/routing/Route;", ContentDisposition.Parameters.Name, "value", "param", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Ljava/lang/String;Lr7/l;)Lio/ktor/server/routing/Route;", "optionalParam", "header", "Lio/ktor/http/ContentType;", "contentType", "accept", "(Lio/ktor/server/routing/Route;Lio/ktor/http/ContentType;Lr7/l;)Lio/ktor/server/routing/Route;", "", "contentTypes", "(Lio/ktor/server/routing/Route;[Lio/ktor/http/ContentType;Lr7/l;)Lio/ktor/server/routing/Route;", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "Ld7/d;", "", "get", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Lr7/q;)Lio/ktor/server/routing/Route;", "(Lio/ktor/server/routing/Route;Lr7/q;)Lio/ktor/server/routing/Route;", "post", "R", "postTyped", "postTypedPath", TtmlNode.TAG_HEAD, "put", "putTyped", "putTypedPath", "patch", "patchTyped", "patchTypedPath", "delete", "options", "createRouteFromPath", "(Lio/ktor/server/routing/Route;Ljava/lang/String;)Lio/ktor/server/routing/Route;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RoutingBuilderKt {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RoutingPathSegmentKind.values().length];
            try {
                iArr[RoutingPathSegmentKind.Parameter.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RoutingPathSegmentKind.Constant.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$accept$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Route, t0> {
        final /* synthetic */ l<Route, t0> $build;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super Route, t0> lVar) {
            super(1);
            this.$build = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            this.$build.invoke(route);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$delete$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19221 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19221(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$delete$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$get$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19231 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19231(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$get$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19242 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19242(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$head$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19251 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19251(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$head$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19262 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19262(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$options$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19271 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19271(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$options$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19282 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19282(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$patch$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19291 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19291(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$patch$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19302 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19302(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$patch$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "R", "Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.routing.RoutingBuilderKt$patch$3", f = "RoutingBuilder.kt", l = {409, 262}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, R, d<? super t0>, Object> $body;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar, d<? super AnonymousClass3> dVar) {
            super(3, dVar);
            this.$body = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$body, dVar);
            anonymousClass3.L$0 = pipelineContext;
            return anonymousClass3.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                p.h();
                throw null;
            }
            if (i10 == 1) {
                PipelineContext pipelineContext = (PipelineContext) this.L$1;
                q qVar = (q) this.L$0;
                c.G(obj);
                if (obj == null) {
                    p.h();
                    throw null;
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 2;
                Object objInvoke = qVar.invoke(pipelineContext, obj, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            p.h();
            throw null;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$patch$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "R", "Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.routing.RoutingBuilderKt$patch$4", f = "RoutingBuilder.kt", l = {409, 275}, m = "invokeSuspend")
    public static final class AnonymousClass4 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, R, d<? super t0>, Object> $body;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar, d<? super AnonymousClass4> dVar) {
            super(3, dVar);
            this.$body = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$body, dVar);
            anonymousClass4.L$0 = pipelineContext;
            return anonymousClass4.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                p.h();
                throw null;
            }
            if (i10 == 1) {
                PipelineContext pipelineContext = (PipelineContext) this.L$1;
                q qVar = (q) this.L$0;
                c.G(obj);
                if (obj == null) {
                    p.h();
                    throw null;
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 2;
                Object objInvoke = qVar.invoke(pipelineContext, obj, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            p.h();
            throw null;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$post$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19311 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19311(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$post$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "R", "Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.routing.RoutingBuilderKt$post$2", f = "RoutingBuilder.kt", l = {409, 149}, m = "invokeSuspend")
    public static final class C19322 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, R, d<? super t0>, Object> $body;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19322(q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar, d<? super C19322> dVar) {
            super(3, dVar);
            this.$body = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C19322 c19322 = new C19322(this.$body, dVar);
            c19322.L$0 = pipelineContext;
            return c19322.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                p.h();
                throw null;
            }
            if (i10 == 1) {
                PipelineContext pipelineContext = (PipelineContext) this.L$1;
                q qVar = (q) this.L$0;
                c.G(obj);
                if (obj == null) {
                    p.h();
                    throw null;
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 2;
                Object objInvoke = qVar.invoke(pipelineContext, obj, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            p.h();
            throw null;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$post$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "R", "Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.routing.RoutingBuilderKt$post$3", f = "RoutingBuilder.kt", l = {409, 162}, m = "invokeSuspend")
    public static final class C19333 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, R, d<? super t0>, Object> $body;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19333(q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar, d<? super C19333> dVar) {
            super(3, dVar);
            this.$body = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C19333 c19333 = new C19333(this.$body, dVar);
            c19333.L$0 = pipelineContext;
            return c19333.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                p.h();
                throw null;
            }
            if (i10 == 1) {
                PipelineContext pipelineContext = (PipelineContext) this.L$1;
                q qVar = (q) this.L$0;
                c.G(obj);
                if (obj == null) {
                    p.h();
                    throw null;
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 2;
                Object objInvoke = qVar.invoke(pipelineContext, obj, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            p.h();
            throw null;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$post$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19344 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19344(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$put$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19351 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19351(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$put$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19362 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19362(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$put$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "R", "Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.routing.RoutingBuilderKt$put$3", f = "RoutingBuilder.kt", l = {409, 219}, m = "invokeSuspend")
    public static final class C19373 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, R, d<? super t0>, Object> $body;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19373(q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar, d<? super C19373> dVar) {
            super(3, dVar);
            this.$body = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C19373 c19373 = new C19373(this.$body, dVar);
            c19373.L$0 = pipelineContext;
            return c19373.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                p.h();
                throw null;
            }
            if (i10 == 1) {
                PipelineContext pipelineContext = (PipelineContext) this.L$1;
                q qVar = (q) this.L$0;
                c.G(obj);
                if (obj == null) {
                    p.h();
                    throw null;
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 2;
                Object objInvoke = qVar.invoke(pipelineContext, obj, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            p.h();
            throw null;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingBuilderKt$put$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "R", "Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.routing.RoutingBuilderKt$put$4", f = "RoutingBuilder.kt", l = {409, 232}, m = "invokeSuspend")
    public static final class C19384 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, R, d<? super t0>, Object> $body;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19384(q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar, d<? super C19384> dVar) {
            super(3, dVar);
            this.$body = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C19384 c19384 = new C19384(this.$body, dVar);
            c19384.L$0 = pipelineContext;
            return c19384.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                p.h();
                throw null;
            }
            if (i10 == 1) {
                PipelineContext pipelineContext = (PipelineContext) this.L$1;
                q qVar = (q) this.L$0;
                c.G(obj);
                if (obj == null) {
                    p.h();
                    throw null;
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 2;
                Object objInvoke = qVar.invoke(pipelineContext, obj, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            p.h();
            throw null;
        }
    }

    @KtorDsl
    public static final Route contentType(Route route, ContentType contentType, l<? super Route, t0> lVar) {
        Route routeCreateChild = route.createChild(new ContentTypeHeaderRouteSelector(contentType));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }

    public static final Route createRouteFromPath(Route route, String str) {
        RouteSelector parameter;
        List<RoutingPathSegment> parts = RoutingPath.INSTANCE.parse(str).getParts();
        int size = parts.size();
        for (int i10 = 0; i10 < size; i10++) {
            RoutingPathSegment routingPathSegment = parts.get(i10);
            String value = routingPathSegment.getValue();
            int i11 = WhenMappings.$EnumSwitchMapping$0[routingPathSegment.getKind().ordinal()];
            if (i11 == 1) {
                parameter = PathSegmentSelectorBuilder.INSTANCE.parseParameter(value);
            } else {
                if (i11 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                parameter = PathSegmentSelectorBuilder.INSTANCE.parseConstant(value);
            }
            route = route.createChild(parameter);
        }
        return u.K(str, DomExceptionUtils.SEPARATOR, false) ? route.createChild(TrailingSlashRouteSelector.INSTANCE) : route;
    }

    @KtorDsl
    public static final Route delete(Route route, String str, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, str, HttpMethod.INSTANCE.getDelete(), new C19221(qVar));
    }

    @KtorDsl
    public static final Route get(Route route, String str, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, str, HttpMethod.INSTANCE.getGet(), new C19231(qVar));
    }

    @KtorDsl
    public static final Route head(Route route, String str, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, str, HttpMethod.INSTANCE.getHead(), new C19251(qVar));
    }

    @KtorDsl
    public static final Route header(Route route, String str, String str2, l<? super Route, t0> lVar) {
        Route routeCreateChild = route.createChild(new HttpHeaderRouteSelector(str, str2));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }

    @KtorDsl
    public static final Route method(Route route, HttpMethod httpMethod, l<? super Route, t0> lVar) {
        Route routeCreateChild = route.createChild(new HttpMethodRouteSelector(httpMethod));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }

    @KtorDsl
    public static final Route optionalParam(Route route, String str, l<? super Route, t0> lVar) {
        Route routeCreateChild = route.createChild(new OptionalParameterRouteSelector(str));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }

    @KtorDsl
    public static final Route options(Route route, String str, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, str, HttpMethod.INSTANCE.getOptions(), new C19271(qVar));
    }

    @KtorDsl
    public static final Route param(Route route, String str, String str2, l<? super Route, t0> lVar) {
        Route routeCreateChild = route.createChild(new ConstantParameterRouteSelector(str, str2));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }

    @KtorDsl
    public static final Route patch(Route route, String str, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, str, HttpMethod.INSTANCE.getPatch(), new C19291(qVar));
    }

    @KtorDsl
    public static final <R> Route patchTyped(Route route, q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar) {
        p.h();
        throw null;
    }

    @KtorDsl
    public static final <R> Route patchTypedPath(Route route, String str, q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar) {
        p.h();
        throw null;
    }

    @KtorDsl
    public static final Route post(Route route, String str, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, str, HttpMethod.INSTANCE.getPost(), new C19311(qVar));
    }

    @KtorDsl
    public static final <R> Route postTyped(Route route, q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar) {
        p.h();
        throw null;
    }

    @KtorDsl
    public static final <R> Route postTypedPath(Route route, String str, q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar) {
        p.h();
        throw null;
    }

    @KtorDsl
    public static final Route put(Route route, String str, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, str, HttpMethod.INSTANCE.getPut(), new C19351(qVar));
    }

    @KtorDsl
    public static final <R> Route putTyped(Route route, q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar) {
        p.h();
        throw null;
    }

    @KtorDsl
    public static final <R> Route putTypedPath(Route route, String str, q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar) {
        p.h();
        throw null;
    }

    @KtorDsl
    public static final Route route(Route route, String str, l<? super Route, t0> lVar) {
        Route routeCreateRouteFromPath = createRouteFromPath(route, str);
        lVar.invoke(routeCreateRouteFromPath);
        return routeCreateRouteFromPath;
    }

    @KtorDsl
    public static final Route accept(Route route, ContentType[] contentTypeArr, l<? super Route, t0> lVar) {
        Route routeCreateChild = route.createChild(new HttpMultiAcceptRouteSelector(t7.a.E(Arrays.copyOf(contentTypeArr, contentTypeArr.length))));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }

    @KtorDsl
    public static final Route delete(Route route, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return method(route, HttpMethod.INSTANCE.getDelete(), new AnonymousClass2(qVar));
    }

    @KtorDsl
    public static final Route get(Route route, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return method(route, HttpMethod.INSTANCE.getGet(), new C19242(qVar));
    }

    @KtorDsl
    public static final Route head(Route route, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return method(route, HttpMethod.INSTANCE.getHead(), new C19262(qVar));
    }

    @KtorDsl
    public static final Route options(Route route, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return method(route, HttpMethod.INSTANCE.getOptions(), new C19282(qVar));
    }

    @KtorDsl
    public static final Route patch(Route route, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return method(route, HttpMethod.INSTANCE.getPatch(), new C19302(qVar));
    }

    @KtorDsl
    public static final Route post(Route route, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return method(route, HttpMethod.INSTANCE.getPost(), new C19344(qVar));
    }

    @KtorDsl
    public static final Route put(Route route, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return method(route, HttpMethod.INSTANCE.getPut(), new C19362(qVar));
    }

    @KtorDsl
    public static final Route route(Route route, String str, HttpMethod httpMethod, l<? super Route, t0> lVar) {
        Route routeCreateChild = createRouteFromPath(route, str).createChild(new HttpMethodRouteSelector(httpMethod));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }

    @KtorDsl
    public static final Route param(Route route, String str, l<? super Route, t0> lVar) {
        Route routeCreateChild = route.createChild(new ParameterRouteSelector(str));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }
}

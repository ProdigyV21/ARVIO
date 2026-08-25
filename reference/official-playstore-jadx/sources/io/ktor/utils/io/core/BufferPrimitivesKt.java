package io.ktor.utils.io.core;

import a0.c;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.bits.PrimitiveArraysJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0018\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a+\u0010\u0005\u001a\u00020\u0003*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0014\u0010\b\u001a\u00020\u0007*\u00020\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\b\u001a\u00020\u0007*\u00020\nH\u0086\bø\u0001\u0001¢\u0006\u0004\b\b\u0010\u000b\u001a\u001f\u0010\u000f\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\f\u001a\u00020\u0007ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000e\u001a\u001f\u0010\u000f\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u0007ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u0010\u001a\u0011\u0010\u0012\u001a\u00020\u0011*\u00020\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0014\u0010\u0012\u001a\u00020\u0011*\u00020\nH\u0086\b¢\u0006\u0004\b\u0012\u0010\u0014\u001a\u0014\u0010\u0016\u001a\u00020\u0015*\u00020\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0013\u001a\u0017\u0010\u0016\u001a\u00020\u0015*\u00020\nH\u0086\bø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0014\u001a\u0011\u0010\u0018\u001a\u00020\u0017*\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0014\u0010\u0018\u001a\u00020\u0017*\u00020\nH\u0086\b¢\u0006\u0004\b\u0018\u0010\u001a\u001a\u0014\u0010\u001c\u001a\u00020\u001b*\u00020\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u0019\u001a\u0017\u0010\u001c\u001a\u00020\u001b*\u00020\nH\u0086\bø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001a\u001a\u0011\u0010\u001e\u001a\u00020\u001d*\u00020\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0014\u0010\u001e\u001a\u00020\u001d*\u00020\nH\u0086\b¢\u0006\u0004\b\u001e\u0010 \u001a\u0014\u0010\"\u001a\u00020!*\u00020\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010\u001f\u001a\u0017\u0010\"\u001a\u00020!*\u00020\nH\u0086\bø\u0001\u0001¢\u0006\u0004\b\"\u0010 \u001a\u0011\u0010$\u001a\u00020#*\u00020\u0000¢\u0006\u0004\b$\u0010%\u001a\u0014\u0010$\u001a\u00020#*\u00020\nH\u0086\b¢\u0006\u0004\b$\u0010&\u001a\u0011\u0010(\u001a\u00020'*\u00020\u0000¢\u0006\u0004\b(\u0010)\u001a\u0014\u0010(\u001a\u00020'*\u00020\nH\u0086\b¢\u0006\u0004\b(\u0010*\u001a\u0019\u0010+\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\f\u001a\u00020\u0011¢\u0006\u0004\b+\u0010,\u001a\u001c\u0010+\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u0011H\u0086\b¢\u0006\u0004\b+\u0010-\u001a\u001f\u0010/\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\f\u001a\u00020\u0015ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b.\u0010,\u001a\"\u0010/\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u0015H\u0086\bø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b.\u0010-\u001a\u0019\u00100\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\f\u001a\u00020\u0017¢\u0006\u0004\b0\u00101\u001a\u001c\u00100\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u0017H\u0086\b¢\u0006\u0004\b0\u00102\u001a\u001f\u00104\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\f\u001a\u00020\u001bø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b3\u00101\u001a\"\u00104\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u001bH\u0086\bø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b3\u00102\u001a\u0019\u00105\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\f\u001a\u00020\u001d¢\u0006\u0004\b5\u00106\u001a\u001c\u00105\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020\u001dH\u0086\b¢\u0006\u0004\b5\u00107\u001a\u001f\u00109\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\f\u001a\u00020!ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b8\u00106\u001a\"\u00109\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020!H\u0086\bø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b8\u00107\u001a\u0019\u0010:\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\f\u001a\u00020#¢\u0006\u0004\b:\u0010;\u001a\u001c\u0010:\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020#H\u0086\b¢\u0006\u0004\b:\u0010<\u001a\u0019\u0010=\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\f\u001a\u00020'¢\u0006\u0004\b=\u0010>\u001a\u001c\u0010=\u001a\u00020\u0003*\u00020\n2\u0006\u0010\f\u001a\u00020'H\u0086\b¢\u0006\u0004\b=\u0010?\u001a-\u0010D\u001a\u00020\u0003*\u00020\u00002\u0006\u0010A\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bD\u0010E\u001a0\u0010D\u001a\u00020\u0003*\u00020\n2\u0006\u0010A\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017H\u0086\b¢\u0006\u0004\bD\u0010F\u001a3\u0010D\u001a\u00020\u0003*\u00020\u00002\u0006\u0010A\u001a\u00020G2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bH\u0010E\u001a-\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010A\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bI\u0010J\u001a0\u0010I\u001a\u00020\u0017*\u00020\n2\u0006\u0010A\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017H\u0086\b¢\u0006\u0004\bI\u0010K\u001a3\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010A\u001a\u00020G2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bL\u0010J\u001a-\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010M\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bN\u0010E\u001a0\u0010N\u001a\u00020\u0003*\u00020\n2\u0006\u0010M\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017H\u0086\b¢\u0006\u0004\bN\u0010F\u001a3\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010M\u001a\u00020G2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bO\u0010E\u001a-\u0010D\u001a\u00020\u0003*\u00020\u00002\u0006\u0010A\u001a\u00020P2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bD\u0010Q\u001a3\u0010D\u001a\u00020\u0003*\u00020\u00002\u0006\u0010A\u001a\u00020R2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bS\u0010Q\u001a-\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010A\u001a\u00020P2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bI\u0010T\u001a3\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010A\u001a\u00020R2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bU\u0010T\u001a-\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010M\u001a\u00020P2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bN\u0010Q\u001a3\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010M\u001a\u00020R2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bV\u0010Q\u001a-\u0010D\u001a\u00020\u0003*\u00020\u00002\u0006\u0010A\u001a\u00020W2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bD\u0010X\u001a3\u0010D\u001a\u00020\u0003*\u00020\u00002\u0006\u0010A\u001a\u00020Y2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bZ\u0010X\u001a-\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010A\u001a\u00020W2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bI\u0010[\u001a3\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010A\u001a\u00020Y2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\\\u0010[\u001a-\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010M\u001a\u00020W2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bN\u0010X\u001a3\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010M\u001a\u00020Y2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b]\u0010X\u001a-\u0010D\u001a\u00020\u0003*\u00020\u00002\u0006\u0010A\u001a\u00020^2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bD\u0010_\u001a3\u0010D\u001a\u00020\u0003*\u00020\u00002\u0006\u0010A\u001a\u00020`2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\ba\u0010_\u001a-\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010A\u001a\u00020^2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bI\u0010b\u001a3\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010A\u001a\u00020`2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bc\u0010b\u001a-\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010M\u001a\u00020^2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bN\u0010_\u001a3\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010M\u001a\u00020`2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\bd\u0010_\u001a-\u0010D\u001a\u00020\u0003*\u00020\u00002\u0006\u0010A\u001a\u00020e2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bD\u0010f\u001a-\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010A\u001a\u00020e2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bI\u0010g\u001a-\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010M\u001a\u00020e2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bN\u0010f\u001a-\u0010D\u001a\u00020\u0003*\u00020\u00002\u0006\u0010A\u001a\u00020h2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bD\u0010i\u001a-\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010A\u001a\u00020h2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bI\u0010j\u001a-\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010M\u001a\u00020h2\b\b\u0002\u0010B\u001a\u00020\u00172\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bN\u0010i\u001a#\u0010D\u001a\u00020\u0017*\u00020\u00002\u0006\u0010k\u001a\u00020\u00002\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bD\u0010l\u001a#\u0010I\u001a\u00020\u0017*\u00020\u00002\u0006\u0010k\u001a\u00020\u00002\b\b\u0002\u0010C\u001a\u00020\u0017¢\u0006\u0004\bI\u0010l\u001a\u0019\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010m\u001a\u00020\u0000¢\u0006\u0004\bN\u0010n\u001a!\u0010N\u001a\u00020\u0003*\u00020\u00002\u0006\u0010m\u001a\u00020\u00002\u0006\u0010C\u001a\u00020\u0017¢\u0006\u0004\bN\u0010o\u001au\u0010x\u001a\u00028\u0000\"\u0004\b\u0000\u0010p*\u00020\u00002\u0006\u0010q\u001a\u00020\u00172\u0006\u0010s\u001a\u00020r26\u0010\u0004\u001a2\u0012\u0013\u0012\u00110u¢\u0006\f\bv\u0012\b\bs\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\u0017¢\u0006\f\bv\u0012\b\bs\u0012\u0004\b\b(B\u0012\u0004\u0012\u00028\u00000tH\u0081\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0004\bx\u0010y\u001ao\u0010z\u001a\u00020\u0003*\u00020\u00002\u0006\u0010q\u001a\u00020\u00172\u0006\u0010s\u001a\u00020r26\u0010\u0004\u001a2\u0012\u0013\u0012\u00110u¢\u0006\f\bv\u0012\b\bs\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\u0017¢\u0006\f\bv\u0012\b\bs\u0012\u0004\b\b(B\u0012\u0004\u0012\u00020\u00030tH\u0081\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0004\bz\u0010{\u0082\u0002\u0012\n\u0005\b\u009920\u0001\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006|"}, d2 = {"Lio/ktor/utils/io/core/Buffer;", "Lkotlin/Function1;", "", "Lx6/t0;", "block", "forEach", "(Lio/ktor/utils/io/core/Buffer;Lr7/l;)V", "Lx6/k0;", "readUByte", "(Lio/ktor/utils/io/core/Buffer;)B", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)B", "value", "writeUByte-EK-6454", "(Lio/ktor/utils/io/core/Buffer;B)V", "writeUByte", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;B)V", "", "readShort", "(Lio/ktor/utils/io/core/Buffer;)S", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)S", "Lx6/r0;", "readUShort", "", "readInt", "(Lio/ktor/utils/io/core/Buffer;)I", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)I", "Lx6/m0;", "readUInt", "", "readLong", "(Lio/ktor/utils/io/core/Buffer;)J", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)J", "Lx6/o0;", "readULong", "", "readFloat", "(Lio/ktor/utils/io/core/Buffer;)F", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)F", "", "readDouble", "(Lio/ktor/utils/io/core/Buffer;)D", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)D", "writeShort", "(Lio/ktor/utils/io/core/Buffer;S)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;S)V", "writeUShort-i8woANY", "writeUShort", "writeInt", "(Lio/ktor/utils/io/core/Buffer;I)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;I)V", "writeUInt-Qn1smSk", "writeUInt", "writeLong", "(Lio/ktor/utils/io/core/Buffer;J)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;J)V", "writeULong-2TYgG_w", "writeULong", "writeFloat", "(Lio/ktor/utils/io/core/Buffer;F)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;F)V", "writeDouble", "(Lio/ktor/utils/io/core/Buffer;D)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;D)V", "", "destination", "offset", "length", "readFully", "(Lio/ktor/utils/io/core/Buffer;[BII)V", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;[BII)V", "Lx6/l0;", "readFully-o1GoV1E", "readAvailable", "(Lio/ktor/utils/io/core/Buffer;[BII)I", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;[BII)I", "readAvailable-o1GoV1E", "source", "writeFully", "writeFully-o1GoV1E", "", "(Lio/ktor/utils/io/core/Buffer;[SII)V", "Lx6/s0;", "readFully-Wt3Bwxc", "(Lio/ktor/utils/io/core/Buffer;[SII)I", "readAvailable-Wt3Bwxc", "writeFully-Wt3Bwxc", "", "(Lio/ktor/utils/io/core/Buffer;[III)V", "Lx6/n0;", "readFully-o2ZM2JE", "(Lio/ktor/utils/io/core/Buffer;[III)I", "readAvailable-o2ZM2JE", "writeFully-o2ZM2JE", "", "(Lio/ktor/utils/io/core/Buffer;[JII)V", "Lx6/p0;", "readFully-pqYNikA", "(Lio/ktor/utils/io/core/Buffer;[JII)I", "readAvailable-pqYNikA", "writeFully-pqYNikA", "", "(Lio/ktor/utils/io/core/Buffer;[FII)V", "(Lio/ktor/utils/io/core/Buffer;[FII)I", "", "(Lio/ktor/utils/io/core/Buffer;[DII)V", "(Lio/ktor/utils/io/core/Buffer;[DII)I", "dst", "(Lio/ktor/utils/io/core/Buffer;Lio/ktor/utils/io/core/Buffer;I)I", "src", "(Lio/ktor/utils/io/core/Buffer;Lio/ktor/utils/io/core/Buffer;)V", "(Lio/ktor/utils/io/core/Buffer;Lio/ktor/utils/io/core/Buffer;I)V", "R", ContentDisposition.Parameters.Size, "", ContentDisposition.Parameters.Name, "Lkotlin/Function2;", "Lio/ktor/utils/io/bits/Memory;", "Lx6/y;", "memory", "readExact", "(Lio/ktor/utils/io/core/Buffer;ILjava/lang/String;Lr7/p;)Ljava/lang/Object;", "writeExact", "(Lio/ktor/utils/io/core/Buffer;ILjava/lang/String;Lr7/p;)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BufferPrimitivesKt {
    public static final void forEach(Buffer buffer, l<? super Byte, t0> lVar) {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        for (int i10 = readPosition; i10 < writePosition; i10++) {
            lVar.invoke(Byte.valueOf(memory.get(i10)));
        }
        buffer.discardExact(writePosition - readPosition);
    }

    public static final int readAvailable(Buffer buffer, byte[] bArr, int i10, int i11) {
        if (i10 < 0) {
            throw new IllegalArgumentException(c.i(i10, "offset shouldn't be negative: ").toString());
        }
        if (i11 < 0) {
            throw new IllegalArgumentException(c.i(i11, "length shouldn't be negative: ").toString());
        }
        if (i10 + i11 > bArr.length) {
            StringBuilder sbV = androidx.compose.foundation.c.v("offset + length should be less than the destination size: ", i10, " + ", i11, " > ");
            sbV.append(bArr.length);
            throw new IllegalArgumentException(sbV.toString().toString());
        }
        if (buffer.getWritePosition() <= buffer.getReadPosition()) {
            return -1;
        }
        int iMin = Math.min(i11, buffer.getWritePosition() - buffer.getReadPosition());
        readFully(buffer, bArr, i10, iMin);
        return iMin;
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        return readAvailable(buffer, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailable-Wt3Bwxc, reason: not valid java name */
    public static final int m6892readAvailableWt3Bwxc(Buffer buffer, short[] sArr, int i10, int i11) {
        return readAvailable(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailable-Wt3Bwxc$default, reason: not valid java name */
    public static int m6893readAvailableWt3Bwxc$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        return m6892readAvailableWt3Bwxc(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailable-o1GoV1E, reason: not valid java name */
    public static final int m6894readAvailableo1GoV1E(Buffer buffer, byte[] bArr, int i10, int i11) {
        return readAvailable(buffer, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailable-o1GoV1E$default, reason: not valid java name */
    public static int m6895readAvailableo1GoV1E$default(Buffer buffer, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        return m6894readAvailableo1GoV1E(buffer, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailable-o2ZM2JE, reason: not valid java name */
    public static final int m6896readAvailableo2ZM2JE(Buffer buffer, int[] iArr, int i10, int i11) {
        return readAvailable(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailable-o2ZM2JE$default, reason: not valid java name */
    public static int m6897readAvailableo2ZM2JE$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        return m6896readAvailableo2ZM2JE(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailable-pqYNikA, reason: not valid java name */
    public static final int m6898readAvailablepqYNikA(Buffer buffer, long[] jArr, int i10, int i11) {
        return readAvailable(buffer, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailable-pqYNikA$default, reason: not valid java name */
    public static int m6899readAvailablepqYNikA$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        return m6898readAvailablepqYNikA(buffer, jArr, i10, i11);
    }

    public static final double readDouble(ChunkBuffer chunkBuffer) {
        return readDouble((Buffer) chunkBuffer);
    }

    public static final <R> R readExact(Buffer buffer, int i10, String str, p<? super Memory, ? super Integer, ? extends R> pVar) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= i10) {
            R r4 = (R) pVar.invoke(Memory.m6745boximpl(memory), Integer.valueOf(readPosition));
            buffer.discardExact(i10);
            return r4;
        }
        throw new EOFException("Not enough bytes to read a " + str + " of size " + i10 + '.');
    }

    public static final float readFloat(ChunkBuffer chunkBuffer) {
        return readFloat((Buffer) chunkBuffer);
    }

    public static final void readFully(ChunkBuffer chunkBuffer, byte[] bArr, int i10, int i11) {
        readFully((Buffer) chunkBuffer, bArr, i10, i11);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        readFully(buffer, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-Wt3Bwxc, reason: not valid java name */
    public static final void m6900readFullyWt3Bwxc(Buffer buffer, short[] sArr, int i10, int i11) throws EOFException {
        readFully(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-Wt3Bwxc$default, reason: not valid java name */
    public static void m6901readFullyWt3Bwxc$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        m6900readFullyWt3Bwxc(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-o1GoV1E, reason: not valid java name */
    public static final void m6902readFullyo1GoV1E(Buffer buffer, byte[] bArr, int i10, int i11) {
        readFully(buffer, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-o1GoV1E$default, reason: not valid java name */
    public static void m6903readFullyo1GoV1E$default(Buffer buffer, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        m6902readFullyo1GoV1E(buffer, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-o2ZM2JE, reason: not valid java name */
    public static final void m6904readFullyo2ZM2JE(Buffer buffer, int[] iArr, int i10, int i11) throws EOFException {
        readFully(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-o2ZM2JE$default, reason: not valid java name */
    public static void m6905readFullyo2ZM2JE$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        m6904readFullyo2ZM2JE(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-pqYNikA, reason: not valid java name */
    public static final void m6906readFullypqYNikA(Buffer buffer, long[] jArr, int i10, int i11) throws EOFException {
        readFully(buffer, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-pqYNikA$default, reason: not valid java name */
    public static void m6907readFullypqYNikA$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        m6906readFullypqYNikA(buffer, jArr, i10, i11);
    }

    public static final int readInt(ChunkBuffer chunkBuffer) {
        return readInt((Buffer) chunkBuffer);
    }

    public static final long readLong(ChunkBuffer chunkBuffer) {
        return readLong((Buffer) chunkBuffer);
    }

    public static final short readShort(ChunkBuffer chunkBuffer) {
        return readShort((Buffer) chunkBuffer);
    }

    public static final byte readUByte(Buffer buffer) {
        return buffer.readByte();
    }

    public static final int readUInt(ChunkBuffer chunkBuffer) {
        return readUInt((Buffer) chunkBuffer);
    }

    public static final long readULong(ChunkBuffer chunkBuffer) {
        return readULong((Buffer) chunkBuffer);
    }

    public static final short readUShort(ChunkBuffer chunkBuffer) {
        return readUShort((Buffer) chunkBuffer);
    }

    public static final void writeDouble(ChunkBuffer chunkBuffer, double d4) throws InsufficientSpaceException {
        writeDouble((Buffer) chunkBuffer, d4);
    }

    public static final void writeExact(Buffer buffer, int i10, String str, p<? super Memory, ? super Integer, t0> pVar) throws InsufficientSpaceException {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < i10) {
            throw new InsufficientSpaceException(str, i10, limit);
        }
        pVar.invoke(Memory.m6745boximpl(memory), Integer.valueOf(writePosition));
        buffer.commitWritten(i10);
    }

    public static final void writeFloat(ChunkBuffer chunkBuffer, float f10) throws InsufficientSpaceException {
        writeFloat((Buffer) chunkBuffer, f10);
    }

    public static final void writeFully(ChunkBuffer chunkBuffer, byte[] bArr, int i10, int i11) throws InsufficientSpaceException {
        writeFully((Buffer) chunkBuffer, bArr, i10, i11);
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, byte[] bArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        writeFully(buffer, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-Wt3Bwxc, reason: not valid java name */
    public static final void m6908writeFullyWt3Bwxc(Buffer buffer, short[] sArr, int i10, int i11) throws InsufficientSpaceException {
        writeFully(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-Wt3Bwxc$default, reason: not valid java name */
    public static void m6909writeFullyWt3Bwxc$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        m6908writeFullyWt3Bwxc(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-o1GoV1E, reason: not valid java name */
    public static final void m6910writeFullyo1GoV1E(Buffer buffer, byte[] bArr, int i10, int i11) throws InsufficientSpaceException {
        writeFully(buffer, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-o1GoV1E$default, reason: not valid java name */
    public static void m6911writeFullyo1GoV1E$default(Buffer buffer, byte[] bArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        m6910writeFullyo1GoV1E(buffer, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-o2ZM2JE, reason: not valid java name */
    public static final void m6912writeFullyo2ZM2JE(Buffer buffer, int[] iArr, int i10, int i11) throws InsufficientSpaceException {
        writeFully(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-o2ZM2JE$default, reason: not valid java name */
    public static void m6913writeFullyo2ZM2JE$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        m6912writeFullyo2ZM2JE(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-pqYNikA, reason: not valid java name */
    public static final void m6914writeFullypqYNikA(Buffer buffer, long[] jArr, int i10, int i11) throws InsufficientSpaceException {
        writeFully(buffer, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-pqYNikA$default, reason: not valid java name */
    public static void m6915writeFullypqYNikA$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        m6914writeFullypqYNikA(buffer, jArr, i10, i11);
    }

    public static final void writeInt(ChunkBuffer chunkBuffer, int i10) {
        writeInt((Buffer) chunkBuffer, i10);
    }

    public static final void writeLong(ChunkBuffer chunkBuffer, long j10) {
        writeLong((Buffer) chunkBuffer, j10);
    }

    public static final void writeShort(ChunkBuffer chunkBuffer, short s10) {
        writeShort((Buffer) chunkBuffer, s10);
    }

    /* JADX INFO: renamed from: writeUByte-EK-6454, reason: not valid java name */
    public static final void m6916writeUByteEK6454(Buffer buffer, byte b10) throws InsufficientSpaceException {
        buffer.writeByte(b10);
    }

    /* JADX INFO: renamed from: writeUInt-Qn1smSk, reason: not valid java name */
    public static final void m6919writeUIntQn1smSk(ChunkBuffer chunkBuffer, int i10) throws InsufficientSpaceException {
        m6918writeUIntQn1smSk((Buffer) chunkBuffer, i10);
    }

    /* JADX INFO: renamed from: writeULong-2TYgG_w, reason: not valid java name */
    public static final void m6921writeULong2TYgG_w(ChunkBuffer chunkBuffer, long j10) throws InsufficientSpaceException {
        m6920writeULong2TYgG_w((Buffer) chunkBuffer, j10);
    }

    /* JADX INFO: renamed from: writeUShort-i8woANY, reason: not valid java name */
    public static final void m6923writeUShorti8woANY(ChunkBuffer chunkBuffer, short s10) throws InsufficientSpaceException {
        m6922writeUShorti8woANY((Buffer) chunkBuffer, s10);
    }

    public static /* synthetic */ int readAvailable$default(ChunkBuffer chunkBuffer, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        return readAvailable((Buffer) chunkBuffer, bArr, i10, i11);
    }

    public static final double readDouble(Buffer buffer) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < 8) {
            throw new EOFException("Not enough bytes to read a long floating point number of size 8.");
        }
        Double dValueOf = Double.valueOf(memory.getDouble(readPosition));
        buffer.discardExact(8);
        return dValueOf.doubleValue();
    }

    public static final float readFloat(Buffer buffer) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < 4) {
            throw new EOFException("Not enough bytes to read a floating point number of size 4.");
        }
        Float fValueOf = Float.valueOf(memory.getFloat(readPosition));
        buffer.discardExact(4);
        return fValueOf.floatValue();
    }

    public static final int readFully(Buffer buffer, Buffer buffer2, int i10) throws EOFException {
        if (i10 < 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (i10 > buffer2.getLimit() - buffer2.getWritePosition()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < i10) {
            throw new EOFException(c.k("Not enough bytes to read a buffer content of size ", i10, '.'));
        }
        Memory.m6747copyToJT6ljtQ(memory, buffer2.getMemory(), readPosition, i10, buffer2.getWritePosition());
        buffer2.commitWritten(i10);
        buffer.discardExact(i10);
        return i10;
    }

    public static /* synthetic */ void readFully$default(ChunkBuffer chunkBuffer, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        readFully((Buffer) chunkBuffer, bArr, i10, i11);
    }

    public static final int readInt(Buffer buffer) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < 4) {
            throw new EOFException("Not enough bytes to read a regular integer of size 4.");
        }
        Integer numValueOf = Integer.valueOf(memory.getInt(readPosition));
        buffer.discardExact(4);
        return numValueOf.intValue();
    }

    public static final long readLong(Buffer buffer) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < 8) {
            throw new EOFException("Not enough bytes to read a long integer of size 8.");
        }
        Long lValueOf = Long.valueOf(memory.getLong(readPosition));
        buffer.discardExact(8);
        return lValueOf.longValue();
    }

    public static final short readShort(Buffer buffer) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < 2) {
            throw new EOFException("Not enough bytes to read a short integer of size 2.");
        }
        Short shValueOf = Short.valueOf(memory.getShort(readPosition));
        buffer.discardExact(2);
        return shValueOf.shortValue();
    }

    public static final byte readUByte(ChunkBuffer chunkBuffer) {
        return readUByte((Buffer) chunkBuffer);
    }

    public static final int readUInt(Buffer buffer) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < 4) {
            throw new EOFException("Not enough bytes to read a regular unsigned integer of size 4.");
        }
        int i10 = memory.getInt(readPosition);
        buffer.discardExact(4);
        return i10;
    }

    public static final long readULong(Buffer buffer) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < 8) {
            throw new EOFException("Not enough bytes to read a long unsigned integer of size 8.");
        }
        long j10 = memory.getLong(readPosition);
        buffer.discardExact(8);
        return j10;
    }

    public static final short readUShort(Buffer buffer) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < 2) {
            throw new EOFException("Not enough bytes to read a short unsigned integer of size 2.");
        }
        short s10 = memory.getShort(readPosition);
        buffer.discardExact(2);
        return s10;
    }

    public static final void writeDouble(Buffer buffer, double d4) throws InsufficientSpaceException {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < 8) {
            throw new InsufficientSpaceException("long floating point number", 8, limit);
        }
        memory.putDouble(writePosition, d4);
        buffer.commitWritten(8);
    }

    public static final void writeFloat(Buffer buffer, float f10) throws InsufficientSpaceException {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < 4) {
            throw new InsufficientSpaceException("floating point number", 4, limit);
        }
        memory.putFloat(writePosition, f10);
        buffer.commitWritten(4);
    }

    public static final void writeFully(Buffer buffer, byte[] bArr, int i10, int i11) throws InsufficientSpaceException {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < i11) {
            throw new InsufficientSpaceException("byte array", i11, limit);
        }
        Memory.m6747copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i10, i11).slice().order(java.nio.ByteOrder.BIG_ENDIAN)), memory, 0, i11, writePosition);
        buffer.commitWritten(i11);
    }

    public static /* synthetic */ void writeFully$default(ChunkBuffer chunkBuffer, byte[] bArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        writeFully((Buffer) chunkBuffer, bArr, i10, i11);
    }

    public static final void writeInt(Buffer buffer, int i10) {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < 4) {
            throw new InsufficientSpaceException("regular integer", 4, limit);
        }
        memory.putInt(writePosition, i10);
        buffer.commitWritten(4);
    }

    public static final void writeLong(Buffer buffer, long j10) {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < 8) {
            throw new InsufficientSpaceException("long integer", 8, limit);
        }
        memory.putLong(writePosition, j10);
        buffer.commitWritten(8);
    }

    public static final void writeShort(Buffer buffer, short s10) {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < 2) {
            throw new InsufficientSpaceException("short integer", 2, limit);
        }
        memory.putShort(writePosition, s10);
        buffer.commitWritten(2);
    }

    /* JADX INFO: renamed from: writeUByte-EK-6454, reason: not valid java name */
    public static final void m6917writeUByteEK6454(ChunkBuffer chunkBuffer, byte b10) throws InsufficientSpaceException {
        m6916writeUByteEK6454((Buffer) chunkBuffer, b10);
    }

    /* JADX INFO: renamed from: writeUInt-Qn1smSk, reason: not valid java name */
    public static final void m6918writeUIntQn1smSk(Buffer buffer, int i10) throws InsufficientSpaceException {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < 4) {
            throw new InsufficientSpaceException("regular unsigned integer", 4, limit);
        }
        memory.putInt(writePosition, i10);
        buffer.commitWritten(4);
    }

    /* JADX INFO: renamed from: writeULong-2TYgG_w, reason: not valid java name */
    public static final void m6920writeULong2TYgG_w(Buffer buffer, long j10) throws InsufficientSpaceException {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < 8) {
            throw new InsufficientSpaceException("long unsigned integer", 8, limit);
        }
        memory.putLong(writePosition, j10);
        buffer.commitWritten(8);
    }

    /* JADX INFO: renamed from: writeUShort-i8woANY, reason: not valid java name */
    public static final void m6922writeUShorti8woANY(Buffer buffer, short s10) throws InsufficientSpaceException {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < 2) {
            throw new InsufficientSpaceException("short unsigned integer", 2, limit);
        }
        memory.putShort(writePosition, s10);
        buffer.commitWritten(2);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        return readAvailable(buffer, sArr, i10, i11);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        readFully(buffer, sArr, i10, i11);
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        writeFully(buffer, sArr, i10, i11);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        readFully(buffer, iArr, i10, i11);
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        writeFully(buffer, iArr, i10, i11);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        return readAvailable(buffer, iArr, i10, i11);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        readFully(buffer, jArr, i10, i11);
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        writeFully(buffer, jArr, i10, i11);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        return readAvailable(buffer, jArr, i10, i11);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, float[] fArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        readFully(buffer, fArr, i10, i11);
    }

    public static final void writeFully(Buffer buffer, short[] sArr, int i10, int i11) throws InsufficientSpaceException {
        int i12 = i11 * 2;
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit >= i12) {
            PrimitiveArraysJvmKt.m6886storeShortArray9zorpBc(memory, writePosition, sArr, i10, i11);
            buffer.commitWritten(i12);
            return;
        }
        throw new InsufficientSpaceException("short integers array", i12, limit);
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, float[] fArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        writeFully(buffer, fArr, i10, i11);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, float[] fArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        return readAvailable(buffer, fArr, i10, i11);
    }

    public static /* synthetic */ void readFully$default(Buffer buffer, double[] dArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        readFully(buffer, dArr, i10, i11);
    }

    public static /* synthetic */ void writeFully$default(Buffer buffer, double[] dArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        writeFully(buffer, dArr, i10, i11);
    }

    public static /* synthetic */ int readFully$default(Buffer buffer, Buffer buffer2, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = buffer2.getLimit() - buffer2.getWritePosition();
        }
        return readFully(buffer, buffer2, i10);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, double[] dArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        return readAvailable(buffer, dArr, i10, i11);
    }

    public static final void writeFully(Buffer buffer, int[] iArr, int i10, int i11) throws InsufficientSpaceException {
        int i12 = i11 * 4;
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit >= i12) {
            PrimitiveArraysJvmKt.m6878storeIntArray9zorpBc(memory, writePosition, iArr, i10, i11);
            buffer.commitWritten(i12);
            return;
        }
        throw new InsufficientSpaceException("integers array", i12, limit);
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, Buffer buffer2, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = buffer2.getLimit() - buffer2.getWritePosition();
        }
        return readAvailable(buffer, buffer2, i10);
    }

    public static final void writeFully(Buffer buffer, long[] jArr, int i10, int i11) throws InsufficientSpaceException {
        int i12 = i11 * 8;
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit >= i12) {
            PrimitiveArraysJvmKt.m6882storeLongArray9zorpBc(memory, writePosition, jArr, i10, i11);
            buffer.commitWritten(i12);
            return;
        }
        throw new InsufficientSpaceException("long integers array", i12, limit);
    }

    public static final void readFully(Buffer buffer, byte[] bArr, int i10, int i11) {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= i11) {
            MemoryJvmKt.m6765copyTo9zorpBc(memory, bArr, readPosition, i11, i10);
            buffer.discardExact(i11);
            return;
        }
        throw new EOFException(c.k("Not enough bytes to read a byte array of size ", i11, '.'));
    }

    public static final void writeFully(Buffer buffer, float[] fArr, int i10, int i11) throws InsufficientSpaceException {
        int i12 = i11 * 4;
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit >= i12) {
            PrimitiveArraysJvmKt.m6874storeFloatArray9zorpBc(memory, writePosition, fArr, i10, i11);
            buffer.commitWritten(i12);
            return;
        }
        throw new InsufficientSpaceException("floating point numbers array", i12, limit);
    }

    public static final void writeFully(Buffer buffer, double[] dArr, int i10, int i11) throws InsufficientSpaceException {
        int i12 = i11 * 8;
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit >= i12) {
            PrimitiveArraysJvmKt.m6870storeDoubleArray9zorpBc(memory, writePosition, dArr, i10, i11);
            buffer.commitWritten(i12);
            return;
        }
        throw new InsufficientSpaceException("floating point numbers array", i12, limit);
    }

    public static final void writeFully(Buffer buffer, Buffer buffer2) throws InsufficientSpaceException {
        int writePosition = buffer2.getWritePosition() - buffer2.getReadPosition();
        ByteBuffer memory = buffer.getMemory();
        int writePosition2 = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition2;
        if (limit >= writePosition) {
            Memory.m6747copyToJT6ljtQ(buffer2.getMemory(), memory, buffer2.getReadPosition(), writePosition, writePosition2);
            buffer2.discardExact(writePosition);
            buffer.commitWritten(writePosition);
            return;
        }
        throw new InsufficientSpaceException("buffer readable content", writePosition, limit);
    }

    public static final void readFully(Buffer buffer, short[] sArr, int i10, int i11) throws EOFException {
        int i12 = i11 * 2;
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= i12) {
            PrimitiveArraysJvmKt.m6866loadShortArray9zorpBc(memory, readPosition, sArr, i10, i11);
            buffer.discardExact(i12);
            return;
        }
        throw new EOFException(c.k("Not enough bytes to read a short integers array of size ", i12, '.'));
    }

    public static final int readAvailable(ChunkBuffer chunkBuffer, byte[] bArr, int i10, int i11) {
        return readAvailable((Buffer) chunkBuffer, bArr, i10, i11);
    }

    public static final int readAvailable(Buffer buffer, short[] sArr, int i10, int i11) throws EOFException {
        if (i10 < 0) {
            throw new IllegalArgumentException(c.i(i10, "offset shouldn't be negative: ").toString());
        }
        if (i11 >= 0) {
            if (i10 + i11 <= sArr.length) {
                if (buffer.getWritePosition() <= buffer.getReadPosition()) {
                    return -1;
                }
                int iMin = Math.min(i11 / 2, buffer.getWritePosition() - buffer.getReadPosition());
                readFully(buffer, sArr, i10, iMin);
                return iMin;
            }
            StringBuilder sbV = androidx.compose.foundation.c.v("offset + length should be less than the destination size: ", i10, " + ", i11, " > ");
            sbV.append(sArr.length);
            throw new IllegalArgumentException(sbV.toString().toString());
        }
        throw new IllegalArgumentException(c.i(i11, "length shouldn't be negative: ").toString());
    }

    public static final void writeFully(Buffer buffer, Buffer buffer2, int i10) {
        if (i10 >= 0) {
            if (i10 <= buffer2.getWritePosition() - buffer2.getReadPosition()) {
                if (i10 <= buffer.getLimit() - buffer.getWritePosition()) {
                    ByteBuffer memory = buffer.getMemory();
                    int writePosition = buffer.getWritePosition();
                    int limit = buffer.getLimit() - writePosition;
                    if (limit >= i10) {
                        Memory.m6747copyToJT6ljtQ(buffer2.getMemory(), memory, buffer2.getReadPosition(), i10, writePosition);
                        buffer2.discardExact(i10);
                        buffer.commitWritten(i10);
                        return;
                    }
                    throw new InsufficientSpaceException("buffer readable content", i10, limit);
                }
                StringBuilder sbS = c.s(i10, "length shouldn't be greater than the destination write remaining space: ", " > ");
                sbS.append(buffer.getLimit() - buffer.getWritePosition());
                throw new IllegalArgumentException(sbS.toString().toString());
            }
            StringBuilder sbS2 = c.s(i10, "length shouldn't be greater than the source read remaining: ", " > ");
            sbS2.append(buffer2.getWritePosition() - buffer2.getReadPosition());
            throw new IllegalArgumentException(sbS2.toString().toString());
        }
        throw new IllegalArgumentException(c.i(i10, "length shouldn't be negative: ").toString());
    }

    public static final void readFully(Buffer buffer, int[] iArr, int i10, int i11) throws EOFException {
        int i12 = i11 * 4;
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= i12) {
            PrimitiveArraysJvmKt.m6858loadIntArray9zorpBc(memory, readPosition, iArr, i10, i11);
            buffer.discardExact(i12);
            return;
        }
        throw new EOFException(c.k("Not enough bytes to read a integers array of size ", i12, '.'));
    }

    public static final void readFully(Buffer buffer, long[] jArr, int i10, int i11) throws EOFException {
        int i12 = i11 * 8;
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= i12) {
            PrimitiveArraysJvmKt.m6862loadLongArray9zorpBc(memory, readPosition, jArr, i10, i11);
            buffer.discardExact(i12);
            return;
        }
        throw new EOFException(c.k("Not enough bytes to read a long integers array of size ", i12, '.'));
    }

    public static final void readFully(Buffer buffer, float[] fArr, int i10, int i11) throws EOFException {
        int i12 = i11 * 4;
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= i12) {
            PrimitiveArraysJvmKt.m6854loadFloatArray9zorpBc(memory, readPosition, fArr, i10, i11);
            buffer.discardExact(i12);
            return;
        }
        throw new EOFException(c.k("Not enough bytes to read a floating point numbers array of size ", i12, '.'));
    }

    public static final int readAvailable(Buffer buffer, int[] iArr, int i10, int i11) throws EOFException {
        if (i10 < 0) {
            throw new IllegalArgumentException(c.i(i10, "offset shouldn't be negative: ").toString());
        }
        if (i11 >= 0) {
            if (i10 + i11 <= iArr.length) {
                if (buffer.getWritePosition() <= buffer.getReadPosition()) {
                    return -1;
                }
                int iMin = Math.min(i11 / 4, buffer.getWritePosition() - buffer.getReadPosition());
                readFully(buffer, iArr, i10, iMin);
                return iMin;
            }
            StringBuilder sbV = androidx.compose.foundation.c.v("offset + length should be less than the destination size: ", i10, " + ", i11, " > ");
            sbV.append(iArr.length);
            throw new IllegalArgumentException(sbV.toString().toString());
        }
        throw new IllegalArgumentException(c.i(i11, "length shouldn't be negative: ").toString());
    }

    public static final void readFully(Buffer buffer, double[] dArr, int i10, int i11) throws EOFException {
        int i12 = i11 * 8;
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= i12) {
            PrimitiveArraysJvmKt.m6850loadDoubleArray9zorpBc(memory, readPosition, dArr, i10, i11);
            buffer.discardExact(i12);
            return;
        }
        throw new EOFException(c.k("Not enough bytes to read a floating point numbers array of size ", i12, '.'));
    }

    public static final int readAvailable(Buffer buffer, long[] jArr, int i10, int i11) throws EOFException {
        if (i10 < 0) {
            throw new IllegalArgumentException(c.i(i10, "offset shouldn't be negative: ").toString());
        }
        if (i11 >= 0) {
            if (i10 + i11 <= jArr.length) {
                if (buffer.getWritePosition() <= buffer.getReadPosition()) {
                    return -1;
                }
                int iMin = Math.min(i11 / 8, buffer.getWritePosition() - buffer.getReadPosition());
                readFully(buffer, jArr, i10, iMin);
                return iMin;
            }
            StringBuilder sbV = androidx.compose.foundation.c.v("offset + length should be less than the destination size: ", i10, " + ", i11, " > ");
            sbV.append(jArr.length);
            throw new IllegalArgumentException(sbV.toString().toString());
        }
        throw new IllegalArgumentException(c.i(i11, "length shouldn't be negative: ").toString());
    }

    public static final int readAvailable(Buffer buffer, float[] fArr, int i10, int i11) throws EOFException {
        if (i10 < 0) {
            throw new IllegalArgumentException(c.i(i10, "offset shouldn't be negative: ").toString());
        }
        if (i11 >= 0) {
            if (i10 + i11 <= fArr.length) {
                if (buffer.getWritePosition() <= buffer.getReadPosition()) {
                    return -1;
                }
                int iMin = Math.min(i11 / 4, buffer.getWritePosition() - buffer.getReadPosition());
                readFully(buffer, fArr, i10, iMin);
                return iMin;
            }
            StringBuilder sbV = androidx.compose.foundation.c.v("offset + length should be less than the destination size: ", i10, " + ", i11, " > ");
            sbV.append(fArr.length);
            throw new IllegalArgumentException(sbV.toString().toString());
        }
        throw new IllegalArgumentException(c.i(i11, "length shouldn't be negative: ").toString());
    }

    public static final int readAvailable(Buffer buffer, double[] dArr, int i10, int i11) throws EOFException {
        if (i10 < 0) {
            throw new IllegalArgumentException(c.i(i10, "offset shouldn't be negative: ").toString());
        }
        if (i11 >= 0) {
            if (i10 + i11 <= dArr.length) {
                if (buffer.getWritePosition() <= buffer.getReadPosition()) {
                    return -1;
                }
                int iMin = Math.min(i11 / 8, buffer.getWritePosition() - buffer.getReadPosition());
                readFully(buffer, dArr, i10, iMin);
                return iMin;
            }
            StringBuilder sbV = androidx.compose.foundation.c.v("offset + length should be less than the destination size: ", i10, " + ", i11, " > ");
            sbV.append(dArr.length);
            throw new IllegalArgumentException(sbV.toString().toString());
        }
        throw new IllegalArgumentException(c.i(i11, "length shouldn't be negative: ").toString());
    }

    public static final int readAvailable(Buffer buffer, Buffer buffer2, int i10) throws EOFException {
        if (buffer.getWritePosition() <= buffer.getReadPosition()) {
            return -1;
        }
        int iMin = Math.min(buffer2.getLimit() - buffer2.getWritePosition(), Math.min(buffer.getWritePosition() - buffer.getReadPosition(), i10));
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition >= iMin) {
            Memory.m6747copyToJT6ljtQ(memory, buffer2.getMemory(), readPosition, iMin, buffer2.getWritePosition());
            buffer2.commitWritten(iMin);
            buffer.discardExact(iMin);
            return iMin;
        }
        throw new EOFException(c.k("Not enough bytes to read a buffer content of size ", iMin, '.'));
    }
}

package io.github.jan.supabase.gotrue.user;

import ab.g;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import cb.b1;
import cb.d;
import cb.l1;
import cb.r1;
import db.b0;
import db.e0;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import q7.n;
import wa.c;
import x6.e;
import xa.a;
import ya.h;
import ya.q;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b8\b\u0087\b\u0018\u0000 \u0085\u00012\u00020\u0001:\u0004\u0086\u0001\u0085\u0001B\u009d\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\f\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u001f\u0010 B¹\u0002\b\u0011\u0012\u0006\u0010\"\u001a\u00020!\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\u0010\b\u0001\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\f\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u001d\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u001e\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010$\u001a\u0004\u0018\u00010#¢\u0006\u0004\b\u001f\u0010%J\u0012\u0010&\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b(\u0010)J\u0012\u0010*\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b*\u0010+J\u0012\u0010,\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b,\u0010+J\u0012\u0010-\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b-\u0010+J\u0012\u0010.\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b.\u0010)J\u0012\u0010/\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b/\u0010+J\u0016\u00100\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0003¢\u0006\u0004\b0\u00101J\u0010\u00102\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b2\u0010)J\u0018\u00103\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b3\u00101J\u0012\u00104\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b4\u0010+J\u0012\u00105\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b5\u0010)J\u0012\u00106\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b6\u0010)J\u0012\u00107\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b7\u0010+J\u0012\u00108\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b8\u0010'J\u0012\u00109\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b9\u0010+J\u0012\u0010:\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b:\u0010)J\u0012\u0010;\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b;\u0010+J\u0012\u0010<\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b<\u0010)J\u0012\u0010=\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b=\u0010+J\u0012\u0010>\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b>\u0010+J\u0012\u0010?\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b?\u0010+J\u0012\u0010@\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b@\u0010)Jª\u0002\u0010A\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\bA\u0010BJ\u0010\u0010C\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\bC\u0010)J\u0010\u0010D\u001a\u00020!HÖ\u0001¢\u0006\u0004\bD\u0010EJ\u001a\u0010H\u001a\u00020G2\b\u0010F\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\bH\u0010IJ(\u0010R\u001a\u00020O2\u0006\u0010J\u001a\u00020\u00002\u0006\u0010L\u001a\u00020K2\u0006\u0010N\u001a\u00020MHÁ\u0001¢\u0006\u0004\bP\u0010QR\"\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0003\u0010S\u0012\u0004\bU\u0010V\u001a\u0004\bT\u0010'R \u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010W\u0012\u0004\bY\u0010V\u001a\u0004\bX\u0010)R\"\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010Z\u0012\u0004\b\\\u0010V\u001a\u0004\b[\u0010+R\"\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\b\u0010Z\u0012\u0004\b^\u0010V\u001a\u0004\b]\u0010+R\"\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\t\u0010Z\u0012\u0004\b`\u0010V\u001a\u0004\b_\u0010+R\"\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\n\u0010W\u0012\u0004\bb\u0010V\u001a\u0004\ba\u0010)R\"\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u000b\u0010Z\u0012\u0004\bd\u0010V\u001a\u0004\bc\u0010+R\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010e\u001a\u0004\bf\u00101R \u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u000f\u0010W\u0012\u0004\bh\u0010V\u001a\u0004\bg\u0010)R(\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0011\u0010e\u0012\u0004\bj\u0010V\u001a\u0004\bi\u00101R\"\u0010\u0012\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0012\u0010Z\u0012\u0004\bl\u0010V\u001a\u0004\bk\u0010+R\"\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0013\u0010W\u0012\u0004\bn\u0010V\u001a\u0004\bm\u0010)R\"\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0014\u0010W\u0012\u0004\bp\u0010V\u001a\u0004\bo\u0010)R\"\u0010\u0015\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0015\u0010Z\u0012\u0004\br\u0010V\u001a\u0004\bq\u0010+R\"\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0016\u0010S\u0012\u0004\bt\u0010V\u001a\u0004\bs\u0010'R\"\u0010\u0017\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0017\u0010Z\u0012\u0004\bv\u0010V\u001a\u0004\bu\u0010+R\"\u0010\u0018\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0018\u0010W\u0012\u0004\bx\u0010V\u001a\u0004\bw\u0010)R\"\u0010\u0019\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0019\u0010Z\u0012\u0004\bz\u0010V\u001a\u0004\by\u0010+R\"\u0010\u001a\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u001a\u0010W\u0012\u0004\b|\u0010V\u001a\u0004\b{\u0010)R\"\u0010\u001b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u001b\u0010Z\u0012\u0004\b~\u0010V\u001a\u0004\b}\u0010+R#\u0010\u001c\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0013\n\u0004\b\u001c\u0010Z\u0012\u0005\b\u0080\u0001\u0010V\u001a\u0004\b\u007f\u0010+R$\u0010\u001d\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0014\n\u0004\b\u001d\u0010Z\u0012\u0005\b\u0082\u0001\u0010V\u001a\u0005\b\u0081\u0001\u0010+R$\u0010\u001e\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0014\n\u0004\b\u001e\u0010W\u0012\u0005\b\u0084\u0001\u0010V\u001a\u0005\b\u0083\u0001\u0010)¨\u0006\u0087\u0001"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserInfo;", "", "Ldb/b0;", "appMetadata", "", "aud", "Lwa/c;", "confirmationSentAt", "confirmedAt", "createdAt", "email", "emailConfirmedAt", "", "Lio/github/jan/supabase/gotrue/user/UserMfaFactor;", "factors", TtmlNode.ATTR_ID, "Lio/github/jan/supabase/gotrue/user/Identity;", "identities", "lastSignInAt", "phone", "role", "updatedAt", "userMetadata", "phoneChangeSentAt", "newPhone", "emailChangeSentAt", "newEmail", "invitedAt", "recoverySentAt", "phoneConfirmedAt", "actionLink", "<init>", "(Ldb/b0;Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;Ljava/lang/String;Lwa/c;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Lwa/c;Ljava/lang/String;Ljava/lang/String;Lwa/c;Ldb/b0;Lwa/c;Ljava/lang/String;Lwa/c;Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;Ljava/lang/String;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILdb/b0;Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;Ljava/lang/String;Lwa/c;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Lwa/c;Ljava/lang/String;Ljava/lang/String;Lwa/c;Ldb/b0;Lwa/c;Ljava/lang/String;Lwa/c;Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;Ljava/lang/String;Lcb/l1;)V", "component1", "()Ldb/b0;", "component2", "()Ljava/lang/String;", "component3", "()Lwa/c;", "component4", "component5", "component6", "component7", "component8", "()Ljava/util/List;", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "copy", "(Ldb/b0;Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;Ljava/lang/String;Lwa/c;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Lwa/c;Ljava/lang/String;Ljava/lang/String;Lwa/c;Ldb/b0;Lwa/c;Ljava/lang/String;Lwa/c;Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;Ljava/lang/String;)Lio/github/jan/supabase/gotrue/user/UserInfo;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/user/UserInfo;Lbb/c;Lab/g;)V", "write$Self", "Ldb/b0;", "getAppMetadata", "getAppMetadata$annotations", "()V", "Ljava/lang/String;", "getAud", "getAud$annotations", "Lwa/c;", "getConfirmationSentAt", "getConfirmationSentAt$annotations", "getConfirmedAt", "getConfirmedAt$annotations", "getCreatedAt", "getCreatedAt$annotations", "getEmail", "getEmail$annotations", "getEmailConfirmedAt", "getEmailConfirmedAt$annotations", "Ljava/util/List;", "getFactors", "getId", "getId$annotations", "getIdentities", "getIdentities$annotations", "getLastSignInAt", "getLastSignInAt$annotations", "getPhone", "getPhone$annotations", "getRole", "getRole$annotations", "getUpdatedAt", "getUpdatedAt$annotations", "getUserMetadata", "getUserMetadata$annotations", "getPhoneChangeSentAt", "getPhoneChangeSentAt$annotations", "getNewPhone", "getNewPhone$annotations", "getEmailChangeSentAt", "getEmailChangeSentAt$annotations", "getNewEmail", "getNewEmail$annotations", "getInvitedAt", "getInvitedAt$annotations", "getRecoverySentAt", "getRecoverySentAt$annotations", "getPhoneConfirmedAt", "getPhoneConfirmedAt$annotations", "getActionLink", "getActionLink$annotations", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r
public final /* data */ class UserInfo {
    private final String actionLink;
    private final b0 appMetadata;
    private final String aud;
    private final c confirmationSentAt;
    private final c confirmedAt;
    private final c createdAt;
    private final String email;
    private final c emailChangeSentAt;
    private final c emailConfirmedAt;
    private final List<UserMfaFactor> factors;
    private final String id;
    private final List<Identity> identities;
    private final c invitedAt;
    private final c lastSignInAt;
    private final String newEmail;
    private final String newPhone;
    private final String phone;
    private final c phoneChangeSentAt;
    private final c phoneConfirmedAt;
    private final c recoverySentAt;
    private final String role;
    private final c updatedAt;
    private final b0 userMetadata;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final h<Object>[] $childSerializers = {null, null, null, null, null, null, null, new d(UserMfaFactor$$serializer.INSTANCE, 0), null, new d(Identity$$serializer.INSTANCE, 0), null, null, null, null, null, null, null, null, null, null, null, null, null};

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/user/UserInfo$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/user/UserInfo;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final h<UserInfo> serializer() {
            return UserInfo$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    @e
    public /* synthetic */ UserInfo(int i10, @q("app_metadata") b0 b0Var, @q("aud") String str, @q("confirmation_sent_at") c cVar, @q("confirmed_at") c cVar2, @q("created_at") c cVar3, @q("email") String str2, @q("email_confirmed_at") c cVar4, List list, @q(TtmlNode.ATTR_ID) String str3, @q("identities") List list2, @q("last_sign_in_at") c cVar5, @q("phone") String str4, @q("role") String str5, @q("updated_at") c cVar6, @q("user_metadata") b0 b0Var2, @q("phone_change_sent_at") c cVar7, @q("new_phone") String str6, @q("email_change_sent_at") c cVar8, @q("new_email") String str7, @q("invited_at") c cVar9, @q("recovery_sent_at") c cVar10, @q("phone_confirmed_at") c cVar11, @q("action_link") String str8, l1 l1Var) {
        if (258 != (i10 & 258)) {
            b1.h(i10, 258, UserInfo$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        if ((i10 & 1) == 0) {
            this.appMetadata = null;
        } else {
            this.appMetadata = b0Var;
        }
        this.aud = str;
        if ((i10 & 4) == 0) {
            this.confirmationSentAt = null;
        } else {
            this.confirmationSentAt = cVar;
        }
        if ((i10 & 8) == 0) {
            this.confirmedAt = null;
        } else {
            this.confirmedAt = cVar2;
        }
        if ((i10 & 16) == 0) {
            this.createdAt = null;
        } else {
            this.createdAt = cVar3;
        }
        if ((i10 & 32) == 0) {
            this.email = null;
        } else {
            this.email = str2;
        }
        if ((i10 & 64) == 0) {
            this.emailConfirmedAt = null;
        } else {
            this.emailConfirmedAt = cVar4;
        }
        if ((i10 & 128) == 0) {
            this.factors = z.f19728i;
        } else {
            this.factors = list;
        }
        this.id = str3;
        if ((i10 & 512) == 0) {
            this.identities = null;
        } else {
            this.identities = list2;
        }
        if ((i10 & 1024) == 0) {
            this.lastSignInAt = null;
        } else {
            this.lastSignInAt = cVar5;
        }
        if ((i10 & 2048) == 0) {
            this.phone = null;
        } else {
            this.phone = str4;
        }
        if ((i10 & 4096) == 0) {
            this.role = null;
        } else {
            this.role = str5;
        }
        if ((i10 & 8192) == 0) {
            this.updatedAt = null;
        } else {
            this.updatedAt = cVar6;
        }
        if ((i10 & 16384) == 0) {
            this.userMetadata = null;
        } else {
            this.userMetadata = b0Var2;
        }
        if ((32768 & i10) == 0) {
            this.phoneChangeSentAt = null;
        } else {
            this.phoneChangeSentAt = cVar7;
        }
        if ((65536 & i10) == 0) {
            this.newPhone = null;
        } else {
            this.newPhone = str6;
        }
        if ((131072 & i10) == 0) {
            this.emailChangeSentAt = null;
        } else {
            this.emailChangeSentAt = cVar8;
        }
        if ((262144 & i10) == 0) {
            this.newEmail = null;
        } else {
            this.newEmail = str7;
        }
        if ((524288 & i10) == 0) {
            this.invitedAt = null;
        } else {
            this.invitedAt = cVar9;
        }
        if ((1048576 & i10) == 0) {
            this.recoverySentAt = null;
        } else {
            this.recoverySentAt = cVar10;
        }
        if ((2097152 & i10) == 0) {
            this.phoneConfirmedAt = null;
        } else {
            this.phoneConfirmedAt = cVar11;
        }
        if ((i10 & 4194304) == 0) {
            this.actionLink = null;
        } else {
            this.actionLink = str8;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UserInfo copy$default(UserInfo userInfo, b0 b0Var, String str, c cVar, c cVar2, c cVar3, String str2, c cVar4, List list, String str3, List list2, c cVar5, String str4, String str5, c cVar6, b0 b0Var2, c cVar7, String str6, c cVar8, String str7, c cVar9, c cVar10, c cVar11, String str8, int i10, Object obj) {
        String str9;
        c cVar12;
        b0 b0Var3 = (i10 & 1) != 0 ? userInfo.appMetadata : b0Var;
        String str10 = (i10 & 2) != 0 ? userInfo.aud : str;
        c cVar13 = (i10 & 4) != 0 ? userInfo.confirmationSentAt : cVar;
        c cVar14 = (i10 & 8) != 0 ? userInfo.confirmedAt : cVar2;
        c cVar15 = (i10 & 16) != 0 ? userInfo.createdAt : cVar3;
        String str11 = (i10 & 32) != 0 ? userInfo.email : str2;
        c cVar16 = (i10 & 64) != 0 ? userInfo.emailConfirmedAt : cVar4;
        List list3 = (i10 & 128) != 0 ? userInfo.factors : list;
        String str12 = (i10 & 256) != 0 ? userInfo.id : str3;
        List list4 = (i10 & 512) != 0 ? userInfo.identities : list2;
        c cVar17 = (i10 & 1024) != 0 ? userInfo.lastSignInAt : cVar5;
        String str13 = (i10 & 2048) != 0 ? userInfo.phone : str4;
        String str14 = (i10 & 4096) != 0 ? userInfo.role : str5;
        c cVar18 = (i10 & 8192) != 0 ? userInfo.updatedAt : cVar6;
        b0 b0Var4 = b0Var3;
        b0 b0Var5 = (i10 & 16384) != 0 ? userInfo.userMetadata : b0Var2;
        c cVar19 = (i10 & 32768) != 0 ? userInfo.phoneChangeSentAt : cVar7;
        String str15 = (i10 & 65536) != 0 ? userInfo.newPhone : str6;
        c cVar20 = (i10 & 131072) != 0 ? userInfo.emailChangeSentAt : cVar8;
        String str16 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? userInfo.newEmail : str7;
        c cVar21 = (i10 & 524288) != 0 ? userInfo.invitedAt : cVar9;
        c cVar22 = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? userInfo.recoverySentAt : cVar10;
        c cVar23 = (i10 & 2097152) != 0 ? userInfo.phoneConfirmedAt : cVar11;
        if ((i10 & 4194304) != 0) {
            cVar12 = cVar23;
            str9 = userInfo.actionLink;
        } else {
            str9 = str8;
            cVar12 = cVar23;
        }
        return userInfo.copy(b0Var4, str10, cVar13, cVar14, cVar15, str11, cVar16, list3, str12, list4, cVar17, str13, str14, cVar18, b0Var5, cVar19, str15, cVar20, str16, cVar21, cVar22, cVar12, str9);
    }

    @q("action_link")
    public static /* synthetic */ void getActionLink$annotations() {
    }

    @q("app_metadata")
    public static /* synthetic */ void getAppMetadata$annotations() {
    }

    @q("aud")
    public static /* synthetic */ void getAud$annotations() {
    }

    @q("confirmation_sent_at")
    public static /* synthetic */ void getConfirmationSentAt$annotations() {
    }

    @q("confirmed_at")
    public static /* synthetic */ void getConfirmedAt$annotations() {
    }

    @q("created_at")
    public static /* synthetic */ void getCreatedAt$annotations() {
    }

    @q("email")
    public static /* synthetic */ void getEmail$annotations() {
    }

    @q("email_change_sent_at")
    public static /* synthetic */ void getEmailChangeSentAt$annotations() {
    }

    @q("email_confirmed_at")
    public static /* synthetic */ void getEmailConfirmedAt$annotations() {
    }

    @q(TtmlNode.ATTR_ID)
    public static /* synthetic */ void getId$annotations() {
    }

    @q("identities")
    public static /* synthetic */ void getIdentities$annotations() {
    }

    @q("invited_at")
    public static /* synthetic */ void getInvitedAt$annotations() {
    }

    @q("last_sign_in_at")
    public static /* synthetic */ void getLastSignInAt$annotations() {
    }

    @q("new_email")
    public static /* synthetic */ void getNewEmail$annotations() {
    }

    @q("new_phone")
    public static /* synthetic */ void getNewPhone$annotations() {
    }

    @q("phone")
    public static /* synthetic */ void getPhone$annotations() {
    }

    @q("phone_change_sent_at")
    public static /* synthetic */ void getPhoneChangeSentAt$annotations() {
    }

    @q("phone_confirmed_at")
    public static /* synthetic */ void getPhoneConfirmedAt$annotations() {
    }

    @q("recovery_sent_at")
    public static /* synthetic */ void getRecoverySentAt$annotations() {
    }

    @q("role")
    public static /* synthetic */ void getRole$annotations() {
    }

    @q("updated_at")
    public static /* synthetic */ void getUpdatedAt$annotations() {
    }

    @q("user_metadata")
    public static /* synthetic */ void getUserMetadata$annotations() {
    }

    @n
    public static final /* synthetic */ void write$Self$gotrue_kt_release(UserInfo self, bb.c output, g serialDesc) {
        h<Object>[] hVarArr = $childSerializers;
        if (output.C() || self.appMetadata != null) {
            output.m(serialDesc, 0, e0.f14890a, self.appMetadata);
        }
        output.x(serialDesc, 1, self.aud);
        if (output.C() || self.confirmationSentAt != null) {
            output.m(serialDesc, 2, a.f22712a, self.confirmationSentAt);
        }
        if (output.C() || self.confirmedAt != null) {
            output.m(serialDesc, 3, a.f22712a, self.confirmedAt);
        }
        if (output.C() || self.createdAt != null) {
            output.m(serialDesc, 4, a.f22712a, self.createdAt);
        }
        if (output.C() || self.email != null) {
            output.m(serialDesc, 5, r1.f7553a, self.email);
        }
        if (output.C() || self.emailConfirmedAt != null) {
            output.m(serialDesc, 6, a.f22712a, self.emailConfirmedAt);
        }
        if (output.C() || !p.a(self.factors, z.f19728i)) {
            output.j(serialDesc, 7, hVarArr[7], self.factors);
        }
        output.x(serialDesc, 8, self.id);
        if (output.C() || self.identities != null) {
            output.m(serialDesc, 9, hVarArr[9], self.identities);
        }
        if (output.C() || self.lastSignInAt != null) {
            output.m(serialDesc, 10, a.f22712a, self.lastSignInAt);
        }
        if (output.C() || self.phone != null) {
            output.m(serialDesc, 11, r1.f7553a, self.phone);
        }
        if (output.C() || self.role != null) {
            output.m(serialDesc, 12, r1.f7553a, self.role);
        }
        if (output.C() || self.updatedAt != null) {
            output.m(serialDesc, 13, a.f22712a, self.updatedAt);
        }
        if (output.C() || self.userMetadata != null) {
            output.m(serialDesc, 14, e0.f14890a, self.userMetadata);
        }
        if (output.C() || self.phoneChangeSentAt != null) {
            output.m(serialDesc, 15, a.f22712a, self.phoneChangeSentAt);
        }
        if (output.C() || self.newPhone != null) {
            output.m(serialDesc, 16, r1.f7553a, self.newPhone);
        }
        if (output.C() || self.emailChangeSentAt != null) {
            output.m(serialDesc, 17, a.f22712a, self.emailChangeSentAt);
        }
        if (output.C() || self.newEmail != null) {
            output.m(serialDesc, 18, r1.f7553a, self.newEmail);
        }
        if (output.C() || self.invitedAt != null) {
            output.m(serialDesc, 19, a.f22712a, self.invitedAt);
        }
        if (output.C() || self.recoverySentAt != null) {
            output.m(serialDesc, 20, a.f22712a, self.recoverySentAt);
        }
        if (output.C() || self.phoneConfirmedAt != null) {
            output.m(serialDesc, 21, a.f22712a, self.phoneConfirmedAt);
        }
        if (!output.C() && self.actionLink == null) {
            return;
        }
        output.m(serialDesc, 22, r1.f7553a, self.actionLink);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final b0 getAppMetadata() {
        return this.appMetadata;
    }

    public final List<Identity> component10() {
        return this.identities;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final c getLastSignInAt() {
        return this.lastSignInAt;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final c getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final b0 getUserMetadata() {
        return this.userMetadata;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final c getPhoneChangeSentAt() {
        return this.phoneChangeSentAt;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getNewPhone() {
        return this.newPhone;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final c getEmailChangeSentAt() {
        return this.emailChangeSentAt;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getNewEmail() {
        return this.newEmail;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAud() {
        return this.aud;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final c getInvitedAt() {
        return this.invitedAt;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final c getRecoverySentAt() {
        return this.recoverySentAt;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final c getPhoneConfirmedAt() {
        return this.phoneConfirmedAt;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getActionLink() {
        return this.actionLink;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final c getConfirmationSentAt() {
        return this.confirmationSentAt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final c getConfirmedAt() {
        return this.confirmedAt;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final c getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final c getEmailConfirmedAt() {
        return this.emailConfirmedAt;
    }

    public final List<UserMfaFactor> component8() {
        return this.factors;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final UserInfo copy(b0 appMetadata, String aud, c confirmationSentAt, c confirmedAt, c createdAt, String email, c emailConfirmedAt, List<UserMfaFactor> factors, String id, List<Identity> identities, c lastSignInAt, String phone, String role, c updatedAt, b0 userMetadata, c phoneChangeSentAt, String newPhone, c emailChangeSentAt, String newEmail, c invitedAt, c recoverySentAt, c phoneConfirmedAt, String actionLink) {
        return new UserInfo(appMetadata, aud, confirmationSentAt, confirmedAt, createdAt, email, emailConfirmedAt, factors, id, identities, lastSignInAt, phone, role, updatedAt, userMetadata, phoneChangeSentAt, newPhone, emailChangeSentAt, newEmail, invitedAt, recoverySentAt, phoneConfirmedAt, actionLink);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserInfo)) {
            return false;
        }
        UserInfo userInfo = (UserInfo) other;
        return p.a(this.appMetadata, userInfo.appMetadata) && p.a(this.aud, userInfo.aud) && p.a(this.confirmationSentAt, userInfo.confirmationSentAt) && p.a(this.confirmedAt, userInfo.confirmedAt) && p.a(this.createdAt, userInfo.createdAt) && p.a(this.email, userInfo.email) && p.a(this.emailConfirmedAt, userInfo.emailConfirmedAt) && p.a(this.factors, userInfo.factors) && p.a(this.id, userInfo.id) && p.a(this.identities, userInfo.identities) && p.a(this.lastSignInAt, userInfo.lastSignInAt) && p.a(this.phone, userInfo.phone) && p.a(this.role, userInfo.role) && p.a(this.updatedAt, userInfo.updatedAt) && p.a(this.userMetadata, userInfo.userMetadata) && p.a(this.phoneChangeSentAt, userInfo.phoneChangeSentAt) && p.a(this.newPhone, userInfo.newPhone) && p.a(this.emailChangeSentAt, userInfo.emailChangeSentAt) && p.a(this.newEmail, userInfo.newEmail) && p.a(this.invitedAt, userInfo.invitedAt) && p.a(this.recoverySentAt, userInfo.recoverySentAt) && p.a(this.phoneConfirmedAt, userInfo.phoneConfirmedAt) && p.a(this.actionLink, userInfo.actionLink);
    }

    public final String getActionLink() {
        return this.actionLink;
    }

    public final b0 getAppMetadata() {
        return this.appMetadata;
    }

    public final String getAud() {
        return this.aud;
    }

    public final c getConfirmationSentAt() {
        return this.confirmationSentAt;
    }

    public final c getConfirmedAt() {
        return this.confirmedAt;
    }

    public final c getCreatedAt() {
        return this.createdAt;
    }

    public final String getEmail() {
        return this.email;
    }

    public final c getEmailChangeSentAt() {
        return this.emailChangeSentAt;
    }

    public final c getEmailConfirmedAt() {
        return this.emailConfirmedAt;
    }

    public final List<UserMfaFactor> getFactors() {
        return this.factors;
    }

    public final String getId() {
        return this.id;
    }

    public final List<Identity> getIdentities() {
        return this.identities;
    }

    public final c getInvitedAt() {
        return this.invitedAt;
    }

    public final c getLastSignInAt() {
        return this.lastSignInAt;
    }

    public final String getNewEmail() {
        return this.newEmail;
    }

    public final String getNewPhone() {
        return this.newPhone;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final c getPhoneChangeSentAt() {
        return this.phoneChangeSentAt;
    }

    public final c getPhoneConfirmedAt() {
        return this.phoneConfirmedAt;
    }

    public final c getRecoverySentAt() {
        return this.recoverySentAt;
    }

    public final String getRole() {
        return this.role;
    }

    public final c getUpdatedAt() {
        return this.updatedAt;
    }

    public final b0 getUserMetadata() {
        return this.userMetadata;
    }

    public int hashCode() {
        b0 b0Var = this.appMetadata;
        int iC = androidx.compose.foundation.c.c((b0Var == null ? 0 : b0Var.f14881i.hashCode()) * 31, 31, this.aud);
        c cVar = this.confirmationSentAt;
        int iHashCode = (iC + (cVar == null ? 0 : cVar.f22539i.hashCode())) * 31;
        c cVar2 = this.confirmedAt;
        int iHashCode2 = (iHashCode + (cVar2 == null ? 0 : cVar2.f22539i.hashCode())) * 31;
        c cVar3 = this.createdAt;
        int iHashCode3 = (iHashCode2 + (cVar3 == null ? 0 : cVar3.f22539i.hashCode())) * 31;
        String str = this.email;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        c cVar4 = this.emailConfirmedAt;
        int iC2 = androidx.compose.foundation.c.c(androidx.compose.material3.d.e(this.factors, (iHashCode4 + (cVar4 == null ? 0 : cVar4.f22539i.hashCode())) * 31, 31), 31, this.id);
        List<Identity> list = this.identities;
        int iHashCode5 = (iC2 + (list == null ? 0 : list.hashCode())) * 31;
        c cVar5 = this.lastSignInAt;
        int iHashCode6 = (iHashCode5 + (cVar5 == null ? 0 : cVar5.f22539i.hashCode())) * 31;
        String str2 = this.phone;
        int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.role;
        int iHashCode8 = (iHashCode7 + (str3 == null ? 0 : str3.hashCode())) * 31;
        c cVar6 = this.updatedAt;
        int iHashCode9 = (iHashCode8 + (cVar6 == null ? 0 : cVar6.f22539i.hashCode())) * 31;
        b0 b0Var2 = this.userMetadata;
        int iHashCode10 = (iHashCode9 + (b0Var2 == null ? 0 : b0Var2.f14881i.hashCode())) * 31;
        c cVar7 = this.phoneChangeSentAt;
        int iHashCode11 = (iHashCode10 + (cVar7 == null ? 0 : cVar7.f22539i.hashCode())) * 31;
        String str4 = this.newPhone;
        int iHashCode12 = (iHashCode11 + (str4 == null ? 0 : str4.hashCode())) * 31;
        c cVar8 = this.emailChangeSentAt;
        int iHashCode13 = (iHashCode12 + (cVar8 == null ? 0 : cVar8.f22539i.hashCode())) * 31;
        String str5 = this.newEmail;
        int iHashCode14 = (iHashCode13 + (str5 == null ? 0 : str5.hashCode())) * 31;
        c cVar9 = this.invitedAt;
        int iHashCode15 = (iHashCode14 + (cVar9 == null ? 0 : cVar9.f22539i.hashCode())) * 31;
        c cVar10 = this.recoverySentAt;
        int iHashCode16 = (iHashCode15 + (cVar10 == null ? 0 : cVar10.f22539i.hashCode())) * 31;
        c cVar11 = this.phoneConfirmedAt;
        int iHashCode17 = (iHashCode16 + (cVar11 == null ? 0 : cVar11.f22539i.hashCode())) * 31;
        String str6 = this.actionLink;
        return iHashCode17 + (str6 != null ? str6.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("UserInfo(appMetadata=");
        sb2.append(this.appMetadata);
        sb2.append(", aud=");
        sb2.append(this.aud);
        sb2.append(", confirmationSentAt=");
        sb2.append(this.confirmationSentAt);
        sb2.append(", confirmedAt=");
        sb2.append(this.confirmedAt);
        sb2.append(", createdAt=");
        sb2.append(this.createdAt);
        sb2.append(", email=");
        sb2.append(this.email);
        sb2.append(", emailConfirmedAt=");
        sb2.append(this.emailConfirmedAt);
        sb2.append(", factors=");
        sb2.append(this.factors);
        sb2.append(", id=");
        sb2.append(this.id);
        sb2.append(", identities=");
        sb2.append(this.identities);
        sb2.append(", lastSignInAt=");
        sb2.append(this.lastSignInAt);
        sb2.append(", phone=");
        sb2.append(this.phone);
        sb2.append(", role=");
        sb2.append(this.role);
        sb2.append(", updatedAt=");
        sb2.append(this.updatedAt);
        sb2.append(", userMetadata=");
        sb2.append(this.userMetadata);
        sb2.append(", phoneChangeSentAt=");
        sb2.append(this.phoneChangeSentAt);
        sb2.append(", newPhone=");
        sb2.append(this.newPhone);
        sb2.append(", emailChangeSentAt=");
        sb2.append(this.emailChangeSentAt);
        sb2.append(", newEmail=");
        sb2.append(this.newEmail);
        sb2.append(", invitedAt=");
        sb2.append(this.invitedAt);
        sb2.append(", recoverySentAt=");
        sb2.append(this.recoverySentAt);
        sb2.append(", phoneConfirmedAt=");
        sb2.append(this.phoneConfirmedAt);
        sb2.append(", actionLink=");
        return androidx.compose.foundation.c.u(sb2, this.actionLink, ')');
    }

    public UserInfo(b0 b0Var, String str, c cVar, c cVar2, c cVar3, String str2, c cVar4, List<UserMfaFactor> list, String str3, List<Identity> list2, c cVar5, String str4, String str5, c cVar6, b0 b0Var2, c cVar7, String str6, c cVar8, String str7, c cVar9, c cVar10, c cVar11, String str8) {
        this.appMetadata = b0Var;
        this.aud = str;
        this.confirmationSentAt = cVar;
        this.confirmedAt = cVar2;
        this.createdAt = cVar3;
        this.email = str2;
        this.emailConfirmedAt = cVar4;
        this.factors = list;
        this.id = str3;
        this.identities = list2;
        this.lastSignInAt = cVar5;
        this.phone = str4;
        this.role = str5;
        this.updatedAt = cVar6;
        this.userMetadata = b0Var2;
        this.phoneChangeSentAt = cVar7;
        this.newPhone = str6;
        this.emailChangeSentAt = cVar8;
        this.newEmail = str7;
        this.invitedAt = cVar9;
        this.recoverySentAt = cVar10;
        this.phoneConfirmedAt = cVar11;
        this.actionLink = str8;
    }

    public /* synthetic */ UserInfo(b0 b0Var, String str, c cVar, c cVar2, c cVar3, String str2, c cVar4, List list, String str3, List list2, c cVar5, String str4, String str5, c cVar6, b0 b0Var2, c cVar7, String str6, c cVar8, String str7, c cVar9, c cVar10, c cVar11, String str8, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : b0Var, str, (i10 & 4) != 0 ? null : cVar, (i10 & 8) != 0 ? null : cVar2, (i10 & 16) != 0 ? null : cVar3, (i10 & 32) != 0 ? null : str2, (i10 & 64) != 0 ? null : cVar4, (i10 & 128) != 0 ? z.f19728i : list, str3, (i10 & 512) != 0 ? null : list2, (i10 & 1024) != 0 ? null : cVar5, (i10 & 2048) != 0 ? null : str4, (i10 & 4096) != 0 ? null : str5, (i10 & 8192) != 0 ? null : cVar6, (i10 & 16384) != 0 ? null : b0Var2, (32768 & i10) != 0 ? null : cVar7, (65536 & i10) != 0 ? null : str6, (131072 & i10) != 0 ? null : cVar8, (262144 & i10) != 0 ? null : str7, (524288 & i10) != 0 ? null : cVar9, (1048576 & i10) != 0 ? null : cVar10, (2097152 & i10) != 0 ? null : cVar11, (i10 & 4194304) != 0 ? null : str8);
    }
}

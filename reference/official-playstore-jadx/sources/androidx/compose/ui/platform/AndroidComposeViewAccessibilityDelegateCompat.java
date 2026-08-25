package androidx.compose.ui.platform;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.SpannableString;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.autofill.AutofillId;
import android.view.translation.TranslationResponseValue;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.R;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.platform.AccessibilityIterators;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt;
import androidx.compose.ui.platform.accessibility.CollectionInfo_androidKt;
import androidx.compose.ui.platform.coreshims.AutofillIdCompat;
import androidx.compose.ui.platform.coreshims.ContentCaptureSessionCompat;
import androidx.compose.ui.platform.coreshims.ViewCompatShims;
import androidx.compose.ui.platform.coreshims.ViewStructureCompat;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesAndroid;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidAccessibilitySpannableString_androidKt;
import androidx.compose.ui.text.platform.URLSpanCache;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.core.view.b2;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.y;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.w;
import kotlin.collections.z;
import m2.f0;
import t.m0;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Î\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010!\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0000\u0018\u0000 ô\u00022\u00020\u00012\u00020\u0002:\u0016õ\u0002ö\u0002ô\u0002÷\u0002ø\u0002ù\u0002ú\u0002û\u0002ü\u0002ý\u0002þ\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\f\u0010\u000bJ*\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0016H\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010 \u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b$\u0010%J\u000f\u0010(\u001a\u00020\tH\u0000¢\u0006\u0004\b&\u0010'J\u0010\u0010+\u001a\u00020\tH\u0080@¢\u0006\u0004\b)\u0010*J\u0017\u00100\u001a\u00020\t2\u0006\u0010-\u001a\u00020,H\u0000¢\u0006\u0004\b.\u0010/J\u000f\u00102\u001a\u00020\tH\u0000¢\u0006\u0004\b1\u0010'J\u000f\u00104\u001a\u00020\tH\u0000¢\u0006\u0004\b3\u0010'J\u000f\u00106\u001a\u00020\tH\u0000¢\u0006\u0004\b5\u0010'J/\u0010@\u001a\u00020\t2\u0006\u00108\u001a\u0002072\u0006\u0010:\u001a\u0002092\u000e\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010<0;H\u0001¢\u0006\u0004\b>\u0010?J\u001f\u0010F\u001a\u00020\t2\u000e\u0010C\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010B0AH\u0001¢\u0006\u0004\bD\u0010EJ8\u0010\u0015\u001a\u00020\r2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020H0G2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002ø\u0001\u0000¢\u0006\u0004\bJ\u0010KJ\u0019\u0010N\u001a\u0004\u0018\u00010M2\u0006\u0010L\u001a\u00020\u000fH\u0002¢\u0006\u0004\bN\u0010OJ\u0017\u0010R\u001a\u00020Q2\u0006\u0010P\u001a\u00020HH\u0002¢\u0006\u0004\bR\u0010SJ(\u0010X\u001a\u0012\u0012\u0004\u0012\u00020V0Uj\b\u0012\u0004\u0012\u00020V`W2\u0006\u0010T\u001a\u00020\rH\u0082\b¢\u0006\u0004\bX\u0010YJQ\u0010`\u001a\b\u0012\u0004\u0012\u00020V0^2\u0006\u0010T\u001a\u00020\r2\u0016\u0010\\\u001a\u0012\u0012\u0004\u0012\u00020V0Zj\b\u0012\u0004\u0012\u00020V`[2\u001a\b\u0002\u0010_\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020V0^0]H\u0002¢\u0006\u0004\b`\u0010aJI\u0010e\u001a\u00020\t2\u0006\u0010b\u001a\u00020V2\u0016\u0010c\u001a\u0012\u0012\u0004\u0012\u00020V0Zj\b\u0012\u0004\u0012\u00020V`[2\u0018\u0010d\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020V0^0]H\u0002¢\u0006\u0004\be\u0010fJ+\u0010h\u001a\b\u0012\u0004\u0012\u00020V0^2\u0006\u0010T\u001a\u00020\r2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020V0^H\u0002¢\u0006\u0004\bh\u0010iJ\u000f\u0010j\u001a\u00020\tH\u0002¢\u0006\u0004\bj\u0010'J\u0017\u0010k\u001a\u00020\r2\u0006\u0010P\u001a\u00020VH\u0002¢\u0006\u0004\bk\u0010lJ'\u0010p\u001a\u00020\t2\u0006\u0010L\u001a\u00020\u000f2\u0006\u0010n\u001a\u00020m2\u0006\u0010o\u001a\u00020VH\u0002¢\u0006\u0004\bp\u0010qJ\u001f\u0010r\u001a\u00020\t2\u0006\u0010P\u001a\u00020V2\u0006\u0010n\u001a\u00020mH\u0002¢\u0006\u0004\br\u0010sJ\u0019\u0010u\u001a\u0004\u0018\u00010t2\u0006\u0010P\u001a\u00020VH\u0002¢\u0006\u0004\bu\u0010vJ\u001f\u0010w\u001a\u00020\t2\u0006\u0010P\u001a\u00020V2\u0006\u0010n\u001a\u00020mH\u0002¢\u0006\u0004\bw\u0010sJ\u0017\u0010x\u001a\u00020\r2\u0006\u0010P\u001a\u00020VH\u0002¢\u0006\u0004\bx\u0010lJ\u001f\u0010y\u001a\u00020\t2\u0006\u0010P\u001a\u00020V2\u0006\u0010n\u001a\u00020mH\u0002¢\u0006\u0004\by\u0010sJ\u0019\u0010{\u001a\u0004\u0018\u00010z2\u0006\u0010P\u001a\u00020VH\u0002¢\u0006\u0004\b{\u0010|J\u001f\u0010}\u001a\u00020\t2\u0006\u0010P\u001a\u00020V2\u0006\u0010n\u001a\u00020mH\u0002¢\u0006\u0004\b}\u0010sJ\u0017\u0010~\u001a\u00020\r2\u0006\u0010L\u001a\u00020\u000fH\u0002¢\u0006\u0004\b~\u0010\u007fJ\u0019\u0010\u0080\u0001\u001a\u00020\r2\u0006\u0010L\u001a\u00020\u000fH\u0002¢\u0006\u0005\b\u0080\u0001\u0010\u007fJD\u0010\u0085\u0001\u001a\u00020\r2\u0006\u0010L\u001a\u00020\u000f2\u0007\u0010\u0081\u0001\u001a\u00020\u000f2\u000b\b\u0002\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u000f2\u0012\b\u0002\u0010\u0084\u0001\u001a\u000b\u0012\u0004\u0012\u00020t\u0018\u00010\u0083\u0001H\u0002¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J\u001b\u0010\u0088\u0001\u001a\u00020\r2\u0007\u0010\u0017\u001a\u00030\u0087\u0001H\u0002¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001J$\u0010\u008a\u0001\u001a\u00030\u0087\u00012\u0006\u0010L\u001a\u00020\u000f2\u0007\u0010\u0081\u0001\u001a\u00020\u000fH\u0003¢\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001JH\u0010\u0091\u0001\u001a\u00030\u0087\u00012\u0006\u0010L\u001a\u00020\u000f2\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u000f2\t\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u000f2\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u000f2\n\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u008f\u0001H\u0002¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J\u0019\u0010\u0093\u0001\u001a\u00020\r2\u0006\u0010L\u001a\u00020\u000fH\u0002¢\u0006\u0005\b\u0093\u0001\u0010\u007fJ/\u0010\u0097\u0001\u001a\u00020\r2\u0006\u0010L\u001a\u00020\u000f2\u0007\u0010\u0094\u0001\u001a\u00020\u000f2\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0095\u0001H\u0002¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001J7\u0010\u009a\u0001\u001a\u00020\t2\u0006\u0010L\u001a\u00020\u000f2\u0006\u0010n\u001a\u00020M2\u0007\u0010\u0099\u0001\u001a\u00020t2\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0095\u0001H\u0002¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J*\u0010 \u0001\u001a\u0005\u0018\u00010\u009f\u00012\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010V2\b\u0010\u009e\u0001\u001a\u00030\u009d\u0001H\u0002¢\u0006\u0006\b \u0001\u0010¡\u0001J\u001a\u0010¢\u0001\u001a\u00020\t2\u0006\u0010L\u001a\u00020\u000fH\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001J6\u0010¦\u0001\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010¤\u0001*\u00030\u008f\u00012\t\u0010\u0090\u0001\u001a\u0004\u0018\u00018\u00002\t\b\u0001\u0010¥\u0001\u001a\u00020\u000fH\u0002¢\u0006\u0006\b¦\u0001\u0010§\u0001J\u0019\u0010¨\u0001\u001a\u00020\t2\u0006\u0010-\u001a\u00020,H\u0002¢\u0006\u0005\b¨\u0001\u0010/J\u0019\u0010©\u0001\u001a\u00020\t2\u0006\u0010-\u001a\u00020,H\u0002¢\u0006\u0005\b©\u0001\u0010/J*\u0010¬\u0001\u001a\u00020\t2\u0006\u0010-\u001a\u00020,2\u000e\u0010«\u0001\u001a\t\u0012\u0004\u0012\u00020\u000f0ª\u0001H\u0002¢\u0006\u0006\b¬\u0001\u0010\u00ad\u0001J\u0011\u0010®\u0001\u001a\u00020\tH\u0002¢\u0006\u0005\b®\u0001\u0010'J\u0011\u0010¯\u0001\u001a\u00020\tH\u0002¢\u0006\u0005\b¯\u0001\u0010'J(\u0010²\u0001\u001a\u00020\t2\u0014\u0010±\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020H0°\u0001H\u0002¢\u0006\u0006\b²\u0001\u0010³\u0001J$\u0010¶\u0001\u001a\u00020\t2\u0007\u0010´\u0001\u001a\u00020\u000f2\u0007\u0010µ\u0001\u001a\u00020tH\u0002¢\u0006\u0006\b¶\u0001\u0010·\u0001J,\u0010º\u0001\u001a\u00020\r2\u0007\u0010´\u0001\u001a\u00020\u000f2\u000f\u0010¹\u0001\u001a\n\u0012\u0005\u0012\u00030¸\u00010\u0083\u0001H\u0002¢\u0006\u0006\bº\u0001\u0010»\u0001J\u001c\u0010½\u0001\u001a\u00020\t2\b\u0010¼\u0001\u001a\u00030¸\u0001H\u0002¢\u0006\u0006\b½\u0001\u0010¾\u0001J/\u0010Á\u0001\u001a\u00020\t2\u0007\u0010¿\u0001\u001a\u00020\u000f2\u0007\u0010\u0082\u0001\u001a\u00020\u000f2\t\u0010À\u0001\u001a\u0004\u0018\u00010tH\u0002¢\u0006\u0006\bÁ\u0001\u0010Â\u0001J\u0019\u0010Ä\u0001\u001a\u0005\u0018\u00010Ã\u0001*\u00020!H\u0002¢\u0006\u0006\bÄ\u0001\u0010Å\u0001J\u001f\u0010É\u0001\u001a\u0005\u0018\u00010È\u00012\b\u0010Ç\u0001\u001a\u00030Æ\u0001H\u0002¢\u0006\u0006\bÉ\u0001\u0010Ê\u0001J\u0019\u0010Ì\u0001\u001a\u0005\u0018\u00010Ë\u0001*\u00020VH\u0002¢\u0006\u0006\bÌ\u0001\u0010Í\u0001J'\u0010Ð\u0001\u001a\u00020\t2\u0007\u0010Î\u0001\u001a\u00020\u000f2\n\u0010Ï\u0001\u001a\u0005\u0018\u00010Ë\u0001H\u0002¢\u0006\u0006\bÐ\u0001\u0010Ñ\u0001J\u001b\u0010Ò\u0001\u001a\u00020\t2\u0007\u0010Î\u0001\u001a\u00020\u000fH\u0002¢\u0006\u0006\bÒ\u0001\u0010£\u0001J\u0011\u0010Ó\u0001\u001a\u00020\tH\u0002¢\u0006\u0005\bÓ\u0001\u0010'J\u001a\u0010Ô\u0001\u001a\u00020\t2\u0006\u0010P\u001a\u00020VH\u0002¢\u0006\u0006\bÔ\u0001\u0010Õ\u0001J\u001a\u0010Ö\u0001\u001a\u00020\t2\u0006\u0010P\u001a\u00020VH\u0002¢\u0006\u0006\bÖ\u0001\u0010Õ\u0001J\u001a\u0010×\u0001\u001a\u00020\t2\u0006\u0010P\u001a\u00020VH\u0002¢\u0006\u0006\b×\u0001\u0010Õ\u0001J\u0011\u0010Ø\u0001\u001a\u00020\tH\u0002¢\u0006\u0005\bØ\u0001\u0010'J\u0011\u0010Ù\u0001\u001a\u00020\tH\u0002¢\u0006\u0005\bÙ\u0001\u0010'J\u0011\u0010Ú\u0001\u001a\u00020\tH\u0002¢\u0006\u0005\bÚ\u0001\u0010'J%\u0010Þ\u0001\u001a\u00020\t2\u0007\u0010Û\u0001\u001a\u00020V2\b\u0010Ý\u0001\u001a\u00030Ü\u0001H\u0002¢\u0006\u0006\bÞ\u0001\u0010ß\u0001J\u001a\u0010à\u0001\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH\u0002¢\u0006\u0006\bà\u0001\u0010á\u0001J%\u0010â\u0001\u001a\u00020\t2\u0007\u0010Û\u0001\u001a\u00020V2\b\u0010Ý\u0001\u001a\u00030Ü\u0001H\u0002¢\u0006\u0006\bâ\u0001\u0010ß\u0001J\u001b\u0010ã\u0001\u001a\u00020\u000f2\u0007\u0010´\u0001\u001a\u00020\u000fH\u0002¢\u0006\u0006\bã\u0001\u0010ä\u0001J5\u0010è\u0001\u001a\u00020\r2\u0006\u0010P\u001a\u00020V2\u0007\u0010å\u0001\u001a\u00020\u000f2\u0007\u0010æ\u0001\u001a\u00020\r2\u0007\u0010ç\u0001\u001a\u00020\rH\u0002¢\u0006\u0006\bè\u0001\u0010é\u0001J\u001b\u0010ê\u0001\u001a\u00020\t2\u0007\u0010¿\u0001\u001a\u00020\u000fH\u0002¢\u0006\u0006\bê\u0001\u0010£\u0001J5\u0010î\u0001\u001a\u00020\r2\u0006\u0010P\u001a\u00020V2\u0007\u0010ë\u0001\u001a\u00020\u000f2\u0007\u0010ì\u0001\u001a\u00020\u000f2\u0007\u0010í\u0001\u001a\u00020\rH\u0002¢\u0006\u0006\bî\u0001\u0010ï\u0001J\u001a\u0010ð\u0001\u001a\u00020\u000f2\u0006\u0010P\u001a\u00020VH\u0002¢\u0006\u0006\bð\u0001\u0010ñ\u0001J\u001a\u0010ò\u0001\u001a\u00020\u000f2\u0006\u0010P\u001a\u00020VH\u0002¢\u0006\u0006\bò\u0001\u0010ñ\u0001J\u0019\u0010ó\u0001\u001a\u00020\r2\u0006\u0010P\u001a\u00020VH\u0002¢\u0006\u0005\bó\u0001\u0010lJ(\u0010õ\u0001\u001a\u0005\u0018\u00010ô\u00012\b\u0010P\u001a\u0004\u0018\u00010V2\u0007\u0010å\u0001\u001a\u00020\u000fH\u0002¢\u0006\u0006\bõ\u0001\u0010ö\u0001J\u001d\u0010÷\u0001\u001a\u0004\u0018\u00010t2\b\u0010P\u001a\u0004\u0018\u00010VH\u0002¢\u0006\u0005\b÷\u0001\u0010vJ\u001a\u0010ù\u0001\u001a\u0005\u0018\u00010ø\u0001*\u00030Æ\u0001H\u0002¢\u0006\u0006\bù\u0001\u0010ú\u0001R\u001a\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\u000f\n\u0005\b\u0004\u0010û\u0001\u001a\u0006\bü\u0001\u0010ý\u0001R0\u0010þ\u0001\u001a\u00020\u000f8\u0000@\u0000X\u0081\u000e¢\u0006\u001f\n\u0006\bþ\u0001\u0010ÿ\u0001\u0012\u0005\b\u0083\u0002\u0010'\u001a\u0006\b\u0080\u0002\u0010\u0081\u0002\"\u0006\b\u0082\u0002\u0010£\u0001R>\u0010\u0085\u0002\u001a\u0010\u0012\u0005\u0012\u00030\u0087\u0001\u0012\u0004\u0012\u00020\r0\u0084\u00028\u0000@\u0000X\u0081\u000e¢\u0006\u001f\n\u0006\b\u0085\u0002\u0010\u0086\u0002\u0012\u0005\b\u008b\u0002\u0010'\u001a\u0006\b\u0087\u0002\u0010\u0088\u0002\"\u0006\b\u0089\u0002\u0010\u008a\u0002R\u0018\u0010\u008d\u0002\u001a\u00030\u008c\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008d\u0002\u0010\u008e\u0002R2\u0010\u0090\u0002\u001a\u00020\r2\u0007\u0010\u008f\u0002\u001a\u00020\r8\u0000@@X\u0080\u000e¢\u0006\u0018\n\u0006\b\u0090\u0002\u0010\u0091\u0002\u001a\u0006\b\u0092\u0002\u0010\u0093\u0002\"\u0006\b\u0094\u0002\u0010á\u0001R\u0018\u0010\u0096\u0002\u001a\u00030\u0095\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0096\u0002\u0010\u0097\u0002R\u0018\u0010\u0099\u0002\u001a\u00030\u0098\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0099\u0002\u0010\u009a\u0002RE\u0010\u009d\u0002\u001a.\u0012\u000f\u0012\r \u009c\u0002*\u0005\u0018\u00010\u009b\u00020\u009b\u0002 \u009c\u0002*\u0016\u0012\u000f\u0012\r \u009c\u0002*\u0005\u0018\u00010\u009b\u00020\u009b\u0002\u0018\u00010\u0083\u00010^8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009d\u0002\u0010\u009e\u0002R\u001a\u0010 \u0002\u001a\u00030\u009f\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b \u0002\u0010¡\u0002R\u0018\u0010£\u0002\u001a\u00030¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b£\u0002\u0010¤\u0002R\u0019\u0010¥\u0002\u001a\u00020#8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¥\u0002\u0010¦\u0002R\u0019\u0010§\u0002\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b§\u0002\u0010ÿ\u0001R\u001b\u0010¨\u0002\u001a\u0004\u0018\u00010M8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¨\u0002\u0010©\u0002R\u0019\u0010ª\u0002\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bª\u0002\u0010\u0091\u0002R7\u0010®\u0002\u001a\"\u0012\u0004\u0012\u00020\u000f\u0012\u0005\u0012\u00030¬\u00020«\u0002j\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0005\u0012\u00030¬\u0002`\u00ad\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b®\u0002\u0010¯\u0002R7\u0010°\u0002\u001a\"\u0012\u0004\u0012\u00020\u000f\u0012\u0005\u0012\u00030¬\u00020«\u0002j\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0005\u0012\u00030¬\u0002`\u00ad\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b°\u0002\u0010¯\u0002R(\u0010²\u0002\u001a\u0011\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u008f\u00010±\u00020±\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b²\u0002\u0010³\u0002R.\u0010´\u0002\u001a\u0017\u0012\u0012\u0012\u0010\u0012\u0005\u0012\u00030\u008f\u0001\u0012\u0004\u0012\u00020\u000f0°\u00010±\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b´\u0002\u0010³\u0002R\u0019\u0010µ\u0002\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bµ\u0002\u0010ÿ\u0001R\u001b\u0010¶\u0002\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¶\u0002\u0010·\u0002R\u001e\u0010¸\u0002\u001a\t\u0012\u0004\u0012\u00020,0ª\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¸\u0002\u0010¹\u0002R\u001e\u0010»\u0002\u001a\t\u0012\u0004\u0012\u00020\t0º\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b»\u0002\u0010¼\u0002R\u0019\u0010½\u0002\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b½\u0002\u0010\u0091\u0002R0\u0010¾\u0002\u001a\u00020\r8\u0000@\u0000X\u0081\u000e¢\u0006\u001f\n\u0006\b¾\u0002\u0010\u0091\u0002\u0012\u0005\bÁ\u0002\u0010'\u001a\u0006\b¿\u0002\u0010\u0093\u0002\"\u0006\bÀ\u0002\u0010á\u0001R3\u0010Â\u0002\u001a\u0005\u0018\u00010Ã\u00018\u0000@\u0000X\u0081\u000e¢\u0006\u001f\n\u0006\bÂ\u0002\u0010Ã\u0002\u0012\u0005\bÈ\u0002\u0010'\u001a\u0006\bÄ\u0002\u0010Å\u0002\"\u0006\bÆ\u0002\u0010Ç\u0002R%\u0010Ê\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0005\u0012\u00030Ë\u00010É\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÊ\u0002\u0010Ë\u0002R\u001e\u0010Ì\u0002\u001a\t\u0012\u0004\u0012\u00020\u000f0ª\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÌ\u0002\u0010¹\u0002R\u001c\u0010Î\u0002\u001a\u0005\u0018\u00010Í\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÎ\u0002\u0010Ï\u0002R,\u0010I\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020H0°\u00018B@\u0002X\u0082\u000e¢\u0006\u000f\n\u0005\bI\u0010Ð\u0002\u001a\u0006\bÑ\u0002\u0010Ò\u0002R \u0010Ó\u0002\u001a\t\u0012\u0004\u0012\u00020\u000f0ª\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÓ\u0002\u0010¹\u0002RG\u0010Ô\u0002\u001a \u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0«\u0002j\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f`\u00ad\u00028\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\bÔ\u0002\u0010¯\u0002\u001a\u0006\bÕ\u0002\u0010Ö\u0002\"\u0006\b×\u0002\u0010Ø\u0002RG\u0010Ù\u0002\u001a \u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0«\u0002j\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f`\u00ad\u00028\u0000@\u0000X\u0080\u000e¢\u0006\u0018\n\u0006\bÙ\u0002\u0010¯\u0002\u001a\u0006\bÚ\u0002\u0010Ö\u0002\"\u0006\bÛ\u0002\u0010Ø\u0002R\u001f\u0010Ü\u0002\u001a\u00020t8\u0000X\u0080D¢\u0006\u0010\n\u0006\bÜ\u0002\u0010Ý\u0002\u001a\u0006\bÞ\u0002\u0010ß\u0002R\u001f\u0010à\u0002\u001a\u00020t8\u0000X\u0080D¢\u0006\u0010\n\u0006\bà\u0002\u0010Ý\u0002\u001a\u0006\bá\u0002\u0010ß\u0002R\u0018\u0010ã\u0002\u001a\u00030â\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bã\u0002\u0010ä\u0002R&\u0010å\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0005\u0012\u00030Ü\u00010]8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bå\u0002\u0010Ð\u0002R\u001a\u0010æ\u0002\u001a\u00030Ü\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bæ\u0002\u0010ç\u0002R\u0019\u0010è\u0002\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bè\u0002\u0010\u0091\u0002R\u0018\u0010ê\u0002\u001a\u00030é\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bê\u0002\u0010ë\u0002R\u001e\u0010ì\u0002\u001a\t\u0012\u0005\u0012\u00030¸\u00010^8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bì\u0002\u0010\u009e\u0002R%\u0010í\u0002\u001a\u0010\u0012\u0005\u0012\u00030¸\u0001\u0012\u0004\u0012\u00020\t0\u0084\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bí\u0002\u0010\u0086\u0002R\u0017\u0010ï\u0002\u001a\u00020\r8@X\u0080\u0004¢\u0006\b\u001a\u0006\bî\u0002\u0010\u0093\u0002R\u0017\u0010ð\u0002\u001a\u00020\r8BX\u0082\u0004¢\u0006\b\u001a\u0006\bð\u0002\u0010\u0093\u0002R\u001e\u0010ñ\u0002\u001a\u00020\r8BX\u0082\u0004¢\u0006\u000f\u0012\u0005\bò\u0002\u0010'\u001a\u0006\bñ\u0002\u0010\u0093\u0002R\u0017\u0010ó\u0002\u001a\u00020\r8BX\u0082\u0004¢\u0006\b\u001a\u0006\bó\u0002\u0010\u0093\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006ÿ\u0002"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "Landroidx/core/view/b;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroidx/compose/ui/platform/AndroidComposeView;", "view", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;)V", "Landroidx/lifecycle/y;", "owner", "Lx6/t0;", "onStart", "(Landroidx/lifecycle/y;)V", "onStop", "", "vertical", "", "direction", "Landroidx/compose/ui/geometry/Offset;", "position", "canScroll-0AR0LA0$ui_release", "(ZIJ)Z", "canScroll", "Landroid/view/MotionEvent;", "event", "dispatchHoverEvent$ui_release", "(Landroid/view/MotionEvent;)Z", "dispatchHoverEvent", "", "x", "y", "hitTestSemanticsAt$ui_release", "(FF)I", "hitTestSemanticsAt", "Landroid/view/View;", "host", "Lq0/l;", "getAccessibilityNodeProvider", "(Landroid/view/View;)Lq0/l;", "onSemanticsChange$ui_release", "()V", "onSemanticsChange", "boundsUpdatesEventLoop$ui_release", "(Ld7/d;)Ljava/lang/Object;", "boundsUpdatesEventLoop", "Landroidx/compose/ui/node/LayoutNode;", "layoutNode", "onLayoutChange$ui_release", "(Landroidx/compose/ui/node/LayoutNode;)V", "onLayoutChange", "onShowTranslation$ui_release", "onShowTranslation", "onHideTranslation$ui_release", "onHideTranslation", "onClearTranslation$ui_release", "onClearTranslation", "", "virtualIds", "", "supportedFormats", "Ljava/util/function/Consumer;", "Landroid/view/translation/ViewTranslationRequest;", "requestsCollector", "onCreateVirtualViewTranslationRequests$ui_release", "([J[ILjava/util/function/Consumer;)V", "onCreateVirtualViewTranslationRequests", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "response", "onVirtualViewTranslationResponses$ui_release", "(Landroid/util/LongSparseArray;)V", "onVirtualViewTranslationResponses", "", "Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;", "currentSemanticsNodes", "canScroll-moWRBKg", "(Ljava/util/Collection;ZIJ)Z", "virtualViewId", "Landroid/view/accessibility/AccessibilityNodeInfo;", "createNodeInfo", "(I)Landroid/view/accessibility/AccessibilityNodeInfo;", "node", "Landroid/graphics/Rect;", "boundsInScreen", "(Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;)Landroid/graphics/Rect;", "layoutIsRtl", "Ljava/util/Comparator;", "Landroidx/compose/ui/semantics/SemanticsNode;", "Lkotlin/Comparator;", "semanticComparator", "(Z)Ljava/util/Comparator;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "parentListToSort", "", "", "containerChildrenMapping", "sortByGeometryGroupings", "(ZLjava/util/ArrayList;Ljava/util/Map;)Ljava/util/List;", "currNode", "geometryList", "containerMapToChildren", "geometryDepthFirstSearch", "(Landroidx/compose/ui/semantics/SemanticsNode;Ljava/util/ArrayList;Ljava/util/Map;)V", "listToSort", "subtreeSortedByGeometryGrouping", "(ZLjava/util/List;)Ljava/util/List;", "setTraversalValues", "isScreenReaderFocusable", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "Lq0/k;", "info", "semanticsNode", "populateAccessibilityNodeInfoProperties", "(ILq0/k;Landroidx/compose/ui/semantics/SemanticsNode;)V", "setContentInvalid", "(Landroidx/compose/ui/semantics/SemanticsNode;Lq0/k;)V", "", "getInfoStateDescriptionOrNull", "(Landroidx/compose/ui/semantics/SemanticsNode;)Ljava/lang/String;", "setStateDescription", "getInfoIsCheckable", "setIsCheckable", "Landroid/text/SpannableString;", "getInfoText", "(Landroidx/compose/ui/semantics/SemanticsNode;)Landroid/text/SpannableString;", "setText", "isAccessibilityFocused", "(I)Z", "requestAccessibilityFocus", "eventType", "contentChangeType", "", "contentDescription", "sendEventForVirtualView", "(IILjava/lang/Integer;Ljava/util/List;)Z", "Landroid/view/accessibility/AccessibilityEvent;", "sendEvent", "(Landroid/view/accessibility/AccessibilityEvent;)Z", "createEvent", "(II)Landroid/view/accessibility/AccessibilityEvent;", "fromIndex", "toIndex", "itemCount", "", "text", "createTextSelectionChangedEvent", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/CharSequence;)Landroid/view/accessibility/AccessibilityEvent;", "clearAccessibilityFocus", "action", "Landroid/os/Bundle;", "arguments", "performActionHelper", "(IILandroid/os/Bundle;)Z", "extraDataKey", "addExtraDataToAccessibilityNodeInfoHelper", "(ILandroid/view/accessibility/AccessibilityNodeInfo;Ljava/lang/String;Landroid/os/Bundle;)V", "textNode", "Landroidx/compose/ui/geometry/Rect;", "bounds", "Landroid/graphics/RectF;", "toScreenCoords", "(Landroidx/compose/ui/semantics/SemanticsNode;Landroidx/compose/ui/geometry/Rect;)Landroid/graphics/RectF;", "updateHoveredVirtualView", "(I)V", "T", ContentDisposition.Parameters.Size, "trimToSize", "(Ljava/lang/CharSequence;I)Ljava/lang/CharSequence;", "notifySubtreeAccessibilityStateChangedIfNeeded", "sendTypeViewScrolledAccessibilityEvent", "Lt/f;", "subtreeChangedSemanticsNodesIds", "sendSubtreeChangeAccessibilityEvents", "(Landroidx/compose/ui/node/LayoutNode;Lt/f;)V", "checkForSemanticsChanges", "updateSemanticsNodesCopyAndPanes", "", "newSemanticsNodes", "sendSemanticsPropertyChangeEvents", "(Ljava/util/Map;)V", TtmlNode.ATTR_ID, "newText", "sendContentCaptureTextUpdateEvent", "(ILjava/lang/String;)V", "Landroidx/compose/ui/platform/ScrollObservationScope;", "oldScrollObservationScopes", "registerScrollingId", "(ILjava/util/List;)Z", "scrollObservationScope", "scheduleScrollEventIfNeeded", "(Landroidx/compose/ui/platform/ScrollObservationScope;)V", "semanticsNodeId", LinkHeader.Parameters.Title, "sendPaneChangeEvents", "(IILjava/lang/String;)V", "Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", "getContentCaptureSessionCompat", "(Landroid/view/View;)Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "configuration", "Landroidx/compose/ui/text/TextLayoutResult;", "getTextLayoutResult", "(Landroidx/compose/ui/semantics/SemanticsConfiguration;)Landroidx/compose/ui/text/TextLayoutResult;", "Landroidx/compose/ui/platform/coreshims/ViewStructureCompat;", "toViewStructure", "(Landroidx/compose/ui/semantics/SemanticsNode;)Landroidx/compose/ui/platform/coreshims/ViewStructureCompat;", "virtualId", "viewStructure", "bufferContentCaptureViewAppeared", "(ILandroidx/compose/ui/platform/coreshims/ViewStructureCompat;)V", "bufferContentCaptureViewDisappeared", "notifyContentCaptureChanges", "updateContentCaptureBuffersOnAppeared", "(Landroidx/compose/ui/semantics/SemanticsNode;)V", "updateContentCaptureBuffersOnDisappeared", "updateTranslationOnAppeared", "showTranslatedText", "hideTranslatedText", "clearTranslatedText", "newNode", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy;", "oldNode", "sendAccessibilitySemanticsStructureChangeEvents", "(Landroidx/compose/ui/semantics/SemanticsNode;Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy;)V", "initContentCapture", "(Z)V", "sendContentCaptureSemanticsStructureChangeEvents", "semanticsNodeIdToAccessibilityVirtualNodeId", "(I)I", "granularity", "forward", "extendSelection", "traverseAtGranularity", "(Landroidx/compose/ui/semantics/SemanticsNode;IZZ)Z", "sendPendingTextTraversedAtGranularityEvent", TtmlNode.START, TtmlNode.END, "traversalMode", "setAccessibilitySelection", "(Landroidx/compose/ui/semantics/SemanticsNode;IIZ)Z", "getAccessibilitySelectionStart", "(Landroidx/compose/ui/semantics/SemanticsNode;)I", "getAccessibilitySelectionEnd", "isAccessibilitySelectionExtendable", "Landroidx/compose/ui/platform/AccessibilityIterators$TextSegmentIterator;", "getIteratorForGranularity", "(Landroidx/compose/ui/semantics/SemanticsNode;I)Landroidx/compose/ui/platform/AccessibilityIterators$TextSegmentIterator;", "getIterableTextForAccessibility", "Landroidx/compose/ui/text/AnnotatedString;", "getTextForTextField", "(Landroidx/compose/ui/semantics/SemanticsConfiguration;)Landroidx/compose/ui/text/AnnotatedString;", "Landroidx/compose/ui/platform/AndroidComposeView;", "getView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "hoveredVirtualViewId", "I", "getHoveredVirtualViewId$ui_release", "()I", "setHoveredVirtualViewId$ui_release", "getHoveredVirtualViewId$ui_release$annotations", "Lkotlin/Function1;", "onSendAccessibilityEvent", "Lr7/l;", "getOnSendAccessibilityEvent$ui_release", "()Lr7/l;", "setOnSendAccessibilityEvent$ui_release", "(Lr7/l;)V", "getOnSendAccessibilityEvent$ui_release$annotations", "Landroid/view/accessibility/AccessibilityManager;", "accessibilityManager", "Landroid/view/accessibility/AccessibilityManager;", "value", "accessibilityForceEnabledForTesting", "Z", "getAccessibilityForceEnabledForTesting$ui_release", "()Z", "setAccessibilityForceEnabledForTesting$ui_release", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "enabledStateListener", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "Landroid/view/accessibility/AccessibilityManager$TouchExplorationStateChangeListener;", "touchExplorationStateListener", "Landroid/view/accessibility/AccessibilityManager$TouchExplorationStateChangeListener;", "Landroid/accessibilityservice/AccessibilityServiceInfo;", "kotlin.jvm.PlatformType", "enabledServices", "Ljava/util/List;", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$TranslateStatus;", "translateStatus", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$TranslateStatus;", "Landroid/os/Handler;", "handler", "Landroid/os/Handler;", "nodeProvider", "Lq0/l;", "focusedVirtualViewId", "currentlyFocusedANI", "Landroid/view/accessibility/AccessibilityNodeInfo;", "sendingFocusAffectingEvent", "Ljava/util/HashMap;", "Landroidx/compose/ui/semantics/ScrollAxisRange;", "Lkotlin/collections/HashMap;", "pendingHorizontalScrollEvents", "Ljava/util/HashMap;", "pendingVerticalScrollEvents", "Lt/m0;", "actionIdToLabel", "Lt/m0;", "labelToActionId", "accessibilityCursorPosition", "previousTraversedNode", "Ljava/lang/Integer;", "subtreeChangedLayoutNodes", "Lt/f;", "Lma/j;", "boundsUpdateChannel", "Lma/j;", "currentSemanticsNodesInvalidated", "contentCaptureForceEnabledForTesting", "getContentCaptureForceEnabledForTesting$ui_release", "setContentCaptureForceEnabledForTesting$ui_release", "getContentCaptureForceEnabledForTesting$ui_release$annotations", "contentCaptureSession", "Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", "getContentCaptureSession$ui_release", "()Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", "setContentCaptureSession$ui_release", "(Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;)V", "getContentCaptureSession$ui_release$annotations", "Lt/e;", "bufferedContentCaptureAppearedNodes", "Lt/e;", "bufferedContentCaptureDisappearedNodes", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "pendingTextTraversedEvent", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "Ljava/util/Map;", "getCurrentSemanticsNodes", "()Ljava/util/Map;", "paneDisplayed", "idToBeforeMap", "getIdToBeforeMap$ui_release", "()Ljava/util/HashMap;", "setIdToBeforeMap$ui_release", "(Ljava/util/HashMap;)V", "idToAfterMap", "getIdToAfterMap$ui_release", "setIdToAfterMap$ui_release", "ExtraDataTestTraversalBeforeVal", "Ljava/lang/String;", "getExtraDataTestTraversalBeforeVal$ui_release", "()Ljava/lang/String;", "ExtraDataTestTraversalAfterVal", "getExtraDataTestTraversalAfterVal$ui_release", "Landroidx/compose/ui/text/platform/URLSpanCache;", "urlSpanCache", "Landroidx/compose/ui/text/platform/URLSpanCache;", "previousSemanticsNodes", "previousSemanticsRoot", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy;", "checkingForSemanticsChanges", "Ljava/lang/Runnable;", "semanticsChangeChecker", "Ljava/lang/Runnable;", "scrollObservationScopes", "scheduleScrollEventIfNeededLambda", "isEnabledForAccessibility$ui_release", "isEnabledForAccessibility", "isEnabled", "isEnabledForContentCapture", "isEnabledForContentCapture$annotations", "isTouchExplorationEnabled", "Companion", "Api24Impl", "Api29Impl", "ComposeAccessibilityNodeProvider", "LtrBoundsComparator", "PendingTextTraversedEvent", "RtlBoundsComparator", "SemanticsNodeCopy", "TopBottomBoundsComparator", "TranslateStatus", "ViewTranslationHelperMethodsS", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidComposeViewAccessibilityDelegateCompat extends androidx.core.view.b implements DefaultLifecycleObserver {
    public static final int AccessibilityCursorPositionUndefined = -1;
    public static final int AccessibilitySliderStepsCount = 20;
    public static final String ClassName = "android.view.View";
    public static final String ExtraDataIdKey = "androidx.compose.ui.semantics.id";
    public static final String ExtraDataTestTagKey = "androidx.compose.ui.semantics.testTag";
    public static final int InvalidId = Integer.MIN_VALUE;
    public static final String LogTag = "AccessibilityDelegate";
    public static final int ParcelSafeTextLength = 100000;
    public static final long SendRecurringAccessibilityEventsIntervalMillis = 100;
    public static final String TextClassName = "android.widget.TextView";
    public static final String TextFieldClassName = "android.widget.EditText";
    public static final long TextTraversedEventTimeoutMillis = 1000;
    private final String ExtraDataTestTraversalAfterVal;
    private final String ExtraDataTestTraversalBeforeVal;
    private int accessibilityCursorPosition;
    private boolean accessibilityForceEnabledForTesting;
    private final android.view.accessibility.AccessibilityManager accessibilityManager;
    private m0 actionIdToLabel;
    private final ma.j<t0> boundsUpdateChannel;
    private final t.e bufferedContentCaptureAppearedNodes;
    private final t.f bufferedContentCaptureDisappearedNodes;
    private boolean checkingForSemanticsChanges;
    private boolean contentCaptureForceEnabledForTesting;
    private ContentCaptureSessionCompat contentCaptureSession;
    private Map<Integer, SemanticsNodeWithAdjustedBounds> currentSemanticsNodes;
    private boolean currentSemanticsNodesInvalidated;
    private AccessibilityNodeInfo currentlyFocusedANI;
    private List<AccessibilityServiceInfo> enabledServices;
    private final AccessibilityManager.AccessibilityStateChangeListener enabledStateListener;
    private int focusedVirtualViewId;
    private final Handler handler;
    private HashMap<Integer, Integer> idToAfterMap;
    private HashMap<Integer, Integer> idToBeforeMap;
    private m0 labelToActionId;
    private q0.l nodeProvider;
    private t.f paneDisplayed;
    private final HashMap<Integer, ScrollAxisRange> pendingHorizontalScrollEvents;
    private PendingTextTraversedEvent pendingTextTraversedEvent;
    private final HashMap<Integer, ScrollAxisRange> pendingVerticalScrollEvents;
    private Map<Integer, SemanticsNodeCopy> previousSemanticsNodes;
    private SemanticsNodeCopy previousSemanticsRoot;
    private Integer previousTraversedNode;
    private final r7.l<ScrollObservationScope, t0> scheduleScrollEventIfNeededLambda;
    private final List<ScrollObservationScope> scrollObservationScopes;
    private final Runnable semanticsChangeChecker;
    private boolean sendingFocusAffectingEvent;
    private final t.f subtreeChangedLayoutNodes;
    private final AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateListener;
    private TranslateStatus translateStatus;
    private final URLSpanCache urlSpanCache;
    private final AndroidComposeView view;
    public static final int $stable = 8;
    private static final int[] AccessibilityActionsResourceIds = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
    private int hoveredVirtualViewId = Integer.MIN_VALUE;
    private r7.l<? super AccessibilityEvent, Boolean> onSendAccessibilityEvent = new AndroidComposeViewAccessibilityDelegateCompat$onSendAccessibilityEvent$1(this);

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api24Impl;", "", "<init>", "()V", "Lq0/k;", "info", "Landroidx/compose/ui/semantics/SemanticsNode;", "semanticsNode", "Lx6/t0;", "addSetProgressAction", "(Lq0/k;Landroidx/compose/ui/semantics/SemanticsNode;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api24Impl {
        public static final Api24Impl INSTANCE = new Api24Impl();

        private Api24Impl() {
        }

        @q7.n
        public static final void addSetProgressAction(q0.k info, SemanticsNode semanticsNode) {
            AccessibilityAction accessibilityAction;
            if (!AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode) || (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress())) == null) {
                return;
            }
            info.b(new q0.e(null, android.R.id.accessibilityActionSetProgress, accessibilityAction.getLabel(), null));
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api29Impl;", "", "<init>", "()V", "Lq0/k;", "info", "Landroidx/compose/ui/semantics/SemanticsNode;", "semanticsNode", "Lx6/t0;", "addPageActions", "(Lq0/k;Landroidx/compose/ui/semantics/SemanticsNode;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api29Impl {
        public static final Api29Impl INSTANCE = new Api29Impl();

        private Api29Impl() {
        }

        @q7.n
        public static final void addPageActions(q0.k info, SemanticsNode semanticsNode) {
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                SemanticsConfiguration unmergedConfig$ui_release = semanticsNode.getUnmergedConfig();
                SemanticsActions semanticsActions = SemanticsActions.INSTANCE;
                AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, semanticsActions.getPageUp());
                if (accessibilityAction != null) {
                    info.b(new q0.e(null, android.R.id.accessibilityActionPageUp, accessibilityAction.getLabel(), null));
                }
                AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getPageDown());
                if (accessibilityAction2 != null) {
                    info.b(new q0.e(null, android.R.id.accessibilityActionPageDown, accessibilityAction2.getLabel(), null));
                }
                AccessibilityAction accessibilityAction3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getPageLeft());
                if (accessibilityAction3 != null) {
                    info.b(new q0.e(null, android.R.id.accessibilityActionPageLeft, accessibilityAction3.getLabel(), null));
                }
                AccessibilityAction accessibilityAction4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getPageRight());
                if (accessibilityAction4 != null) {
                    info.b(new q0.e(null, android.R.id.accessibilityActionPageRight, accessibilityAction4.getLabel(), null));
                }
            }
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ)\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ1\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0015\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0016\u0010\b¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$ComposeAccessibilityNodeProvider;", "Landroid/view/accessibility/AccessibilityNodeProvider;", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;)V", "", "virtualViewId", "Landroid/view/accessibility/AccessibilityNodeInfo;", "createAccessibilityNodeInfo", "(I)Landroid/view/accessibility/AccessibilityNodeInfo;", "action", "Landroid/os/Bundle;", "arguments", "", "performAction", "(IILandroid/os/Bundle;)Z", "info", "", "extraDataKey", "Lx6/t0;", "addExtraDataToAccessibilityNodeInfo", "(ILandroid/view/accessibility/AccessibilityNodeInfo;Ljava/lang/String;Landroid/os/Bundle;)V", "focus", "findFocus", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class ComposeAccessibilityNodeProvider extends AccessibilityNodeProvider {
        public ComposeAccessibilityNodeProvider() {
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey, Bundle arguments) {
            AndroidComposeViewAccessibilityDelegateCompat.this.addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, info, extraDataKey, arguments);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
            AccessibilityNodeInfo accessibilityNodeInfoCreateNodeInfo = AndroidComposeViewAccessibilityDelegateCompat.this.createNodeInfo(virtualViewId);
            if (AndroidComposeViewAccessibilityDelegateCompat.this.sendingFocusAffectingEvent && virtualViewId == AndroidComposeViewAccessibilityDelegateCompat.this.focusedVirtualViewId) {
                AndroidComposeViewAccessibilityDelegateCompat.this.currentlyFocusedANI = accessibilityNodeInfoCreateNodeInfo;
            }
            return accessibilityNodeInfoCreateNodeInfo;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int focus) {
            return createAccessibilityNodeInfo(AndroidComposeViewAccessibilityDelegateCompat.this.focusedVirtualViewId);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return AndroidComposeViewAccessibilityDelegateCompat.this.performActionHelper(virtualViewId, action, arguments);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$LtrBoundsComparator;", "Ljava/util/Comparator;", "Landroidx/compose/ui/semantics/SemanticsNode;", "Lkotlin/Comparator;", "()V", "compare", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class LtrBoundsComparator implements Comparator<SemanticsNode> {
        public static final LtrBoundsComparator INSTANCE = new LtrBoundsComparator();

        private LtrBoundsComparator() {
        }

        @Override // java.util.Comparator
        public int compare(SemanticsNode a10, SemanticsNode b10) {
            Rect boundsInWindow = a10.getBoundsInWindow();
            Rect boundsInWindow2 = b10.getBoundsInWindow();
            int iCompare = Float.compare(boundsInWindow.getLeft(), boundsInWindow2.getLeft());
            if (iCompare != 0) {
                return iCompare;
            }
            int iCompare2 = Float.compare(boundsInWindow.getTop(), boundsInWindow2.getTop());
            if (iCompare2 != 0) {
                return iCompare2;
            }
            int iCompare3 = Float.compare(boundsInWindow.getBottom(), boundsInWindow2.getBottom());
            return iCompare3 != 0 ? iCompare3 : Float.compare(boundsInWindow.getRight(), boundsInWindow2.getRight());
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "", "node", "Landroidx/compose/ui/semantics/SemanticsNode;", "action", "", "granularity", "fromIndex", "toIndex", "traverseTime", "", "(Landroidx/compose/ui/semantics/SemanticsNode;IIIIJ)V", "getAction", "()I", "getFromIndex", "getGranularity", "getNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "getToIndex", "getTraverseTime", "()J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class PendingTextTraversedEvent {
        private final int action;
        private final int fromIndex;
        private final int granularity;
        private final SemanticsNode node;
        private final int toIndex;
        private final long traverseTime;

        public PendingTextTraversedEvent(SemanticsNode semanticsNode, int i10, int i11, int i12, int i13, long j10) {
            this.node = semanticsNode;
            this.action = i10;
            this.granularity = i11;
            this.fromIndex = i12;
            this.toIndex = i13;
            this.traverseTime = j10;
        }

        public final int getAction() {
            return this.action;
        }

        public final int getFromIndex() {
            return this.fromIndex;
        }

        public final int getGranularity() {
            return this.granularity;
        }

        public final SemanticsNode getNode() {
            return this.node;
        }

        public final int getToIndex() {
            return this.toIndex;
        }

        public final long getTraverseTime() {
            return this.traverseTime;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$RtlBoundsComparator;", "Ljava/util/Comparator;", "Landroidx/compose/ui/semantics/SemanticsNode;", "Lkotlin/Comparator;", "()V", "compare", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class RtlBoundsComparator implements Comparator<SemanticsNode> {
        public static final RtlBoundsComparator INSTANCE = new RtlBoundsComparator();

        private RtlBoundsComparator() {
        }

        @Override // java.util.Comparator
        public int compare(SemanticsNode a10, SemanticsNode b10) {
            Rect boundsInWindow = a10.getBoundsInWindow();
            Rect boundsInWindow2 = b10.getBoundsInWindow();
            int iCompare = Float.compare(boundsInWindow2.getRight(), boundsInWindow.getRight());
            if (iCompare != 0) {
                return iCompare;
            }
            int iCompare2 = Float.compare(boundsInWindow.getTop(), boundsInWindow2.getTop());
            if (iCompare2 != 0) {
                return iCompare2;
            }
            int iCompare3 = Float.compare(boundsInWindow.getBottom(), boundsInWindow2.getBottom());
            return iCompare3 != 0 ? iCompare3 : Float.compare(boundsInWindow2.getLeft(), boundsInWindow.getLeft());
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0006\u0010\u0013\u001a\u00020\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy;", "", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "currentSemanticsNodes", "", "", "Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;", "(Landroidx/compose/ui/semantics/SemanticsNode;Ljava/util/Map;)V", "children", "", "getChildren", "()Ljava/util/Set;", "getSemanticsNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "unmergedConfig", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "getUnmergedConfig", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", "hasPaneTitle", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SemanticsNodeCopy {
        private final Set<Integer> children = new LinkedHashSet();
        private final SemanticsNode semanticsNode;
        private final SemanticsConfiguration unmergedConfig;

        public SemanticsNodeCopy(SemanticsNode semanticsNode, Map<Integer, SemanticsNodeWithAdjustedBounds> map) {
            this.semanticsNode = semanticsNode;
            this.unmergedConfig = semanticsNode.getUnmergedConfig();
            List<SemanticsNode> replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            for (int i10 = 0; i10 < size; i10++) {
                SemanticsNode semanticsNode2 = replacedChildren$ui_release.get(i10);
                if (map.containsKey(Integer.valueOf(semanticsNode2.getId()))) {
                    this.children.add(Integer.valueOf(semanticsNode2.getId()));
                }
            }
        }

        public final Set<Integer> getChildren() {
            return this.children;
        }

        public final SemanticsNode getSemanticsNode() {
            return this.semanticsNode;
        }

        public final SemanticsConfiguration getUnmergedConfig() {
            return this.unmergedConfig;
        }

        public final boolean hasPaneTitle() {
            return this.unmergedConfig.contains(SemanticsProperties.INSTANCE.getPaneTitle());
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u000026\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00020\u0001j\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002`\u0006B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJC\u0010\f\u001a\u00020\u000b2\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00022\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002H\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$TopBottomBoundsComparator;", "Ljava/util/Comparator;", "Lx6/x;", "Landroidx/compose/ui/geometry/Rect;", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "Lkotlin/Comparator;", "<init>", "()V", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "", "compare", "(Lx6/x;Lx6/x;)I", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TopBottomBoundsComparator implements Comparator<x> {
        public static final TopBottomBoundsComparator INSTANCE = new TopBottomBoundsComparator();

        private TopBottomBoundsComparator() {
        }

        @Override // java.util.Comparator
        public int compare(x a10, x b10) {
            int iCompare = Float.compare(((Rect) a10.f22608i).getTop(), ((Rect) b10.f22608i).getTop());
            return iCompare != 0 ? iCompare : Float.compare(((Rect) a10.f22608i).getBottom(), ((Rect) b10.f22608i).getBottom());
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$TranslateStatus;", "", "(Ljava/lang/String;I)V", "SHOW_ORIGINAL", "SHOW_TRANSLATED", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum TranslateStatus {
        SHOW_ORIGINAL,
        SHOW_TRANSLATED
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ7\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010H\u0007¢\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0007¢\u0006\u0004\b\u0015\u0010\u000b¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$ViewTranslationHelperMethodsS;", "", "<init>", "()V", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "accessibilityDelegateCompat", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "response", "Lx6/t0;", "doTranslation", "(Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;Landroid/util/LongSparseArray;)V", "", "virtualIds", "", "supportedFormats", "Ljava/util/function/Consumer;", "Landroid/view/translation/ViewTranslationRequest;", "requestsCollector", "onCreateVirtualViewTranslationRequests", "(Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;[J[ILjava/util/function/Consumer;)V", "onVirtualViewTranslationResponses", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ViewTranslationHelperMethodsS {
        public static final ViewTranslationHelperMethodsS INSTANCE = new ViewTranslationHelperMethodsS();

        private ViewTranslationHelperMethodsS() {
        }

        private final void doTranslation(AndroidComposeViewAccessibilityDelegateCompat accessibilityDelegateCompat, LongSparseArray<ViewTranslationResponse> response) {
            TranslationResponseValue value;
            CharSequence text;
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds;
            SemanticsNode semanticsNode;
            AccessibilityAction accessibilityAction;
            r7.l lVar;
            int i10 = 0;
            while (true) {
                if (!(i10 < response.size())) {
                    return;
                }
                int i11 = i10 + 1;
                long jKeyAt = response.keyAt(i10);
                ViewTranslationResponse viewTranslationResponse = response.get(jKeyAt);
                if (viewTranslationResponse != null && (value = viewTranslationResponse.getValue("android:text")) != null && (text = value.getText()) != null && (semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) accessibilityDelegateCompat.getCurrentSemanticsNodes().get(Integer.valueOf((int) jKeyAt))) != null && (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetTextSubstitution())) != null && (lVar = (r7.l) accessibilityAction.getAction()) != null) {
                }
                i10 = i11;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onVirtualViewTranslationResponses$lambda$1(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, LongSparseArray longSparseArray) {
            INSTANCE.doTranslation(androidComposeViewAccessibilityDelegateCompat, longSparseArray);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0039  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onCreateVirtualViewTranslationRequests(androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r15, long[] r16, int[] r17, java.util.function.Consumer<android.view.translation.ViewTranslationRequest> r18) {
            /*
                r14 = this;
                r0 = r16
                int r1 = r0.length
                r2 = 0
            L4:
                if (r2 >= r1) goto L5a
                r3 = r0[r2]
                java.util.Map r5 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$getCurrentSemanticsNodes(r15)
                int r3 = (int) r3
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                java.lang.Object r3 = r5.get(r3)
                androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r3 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r3
                if (r3 == 0) goto L39
                androidx.compose.ui.semantics.SemanticsNode r3 = r3.getSemanticsNode()
                if (r3 == 0) goto L39
                android.view.translation.ViewTranslationRequest$Builder r4 = new android.view.translation.ViewTranslationRequest$Builder
                androidx.compose.ui.platform.AndroidComposeView r4 = r15.getView()
                android.view.autofill.AutofillId r4 = r4.getAutofillId()
                int r5 = r3.getId()
                long r5 = (long) r5
                android.view.translation.ViewTranslationRequest$Builder r7 = new android.view.translation.ViewTranslationRequest$Builder
                r7.<init>(r4, r5)
                java.lang.String r9 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$getTextForTranslation(r3)
                if (r9 != 0) goto L3c
            L39:
                r4 = r18
                goto L57
            L3c:
                androidx.compose.ui.text.AnnotatedString r8 = new androidx.compose.ui.text.AnnotatedString
                r10 = 0
                r11 = 0
                r12 = 6
                r13 = 0
                r8.<init>(r9, r10, r11, r12, r13)
                java.lang.String r3 = "android:text"
                android.view.translation.TranslationRequestValue r4 = android.view.translation.TranslationRequestValue.forText(r8)
                r7.setValue(r3, r4)
                android.view.translation.ViewTranslationRequest r3 = r7.build()
                r4 = r18
                r4.n(r3)
            L57:
                int r2 = r2 + 1
                goto L4
            L5a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.ViewTranslationHelperMethodsS.onCreateVirtualViewTranslationRequests(androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat, long[], int[], java.util.function.Consumer):void");
        }

        public final void onVirtualViewTranslationResponses(final AndroidComposeViewAccessibilityDelegateCompat accessibilityDelegateCompat, final LongSparseArray<ViewTranslationResponse> response) {
            if (Build.VERSION.SDK_INT < 31) {
                return;
            }
            if (kotlin.jvm.internal.p.a(Looper.getMainLooper().getThread(), Thread.currentThread())) {
                doTranslation(accessibilityDelegateCompat, response);
            } else {
                accessibilityDelegateCompat.getView().post(new Runnable() { // from class: androidx.compose.ui.platform.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        AndroidComposeViewAccessibilityDelegateCompat.ViewTranslationHelperMethodsS.onVirtualViewTranslationResponses$lambda$1(accessibilityDelegateCompat, response);
                    }
                });
            }
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ToggleableState.Indeterminate.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeeded$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06201 extends kotlin.jvm.internal.r implements r7.a<t0> {
        final /* synthetic */ ScrollObservationScope $scrollObservationScope;
        final /* synthetic */ AndroidComposeViewAccessibilityDelegateCompat this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06201(ScrollObservationScope scrollObservationScope, AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat) {
            super(0);
            this.$scrollObservationScope = scrollObservationScope;
            this.this$0 = androidComposeViewAccessibilityDelegateCompat;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m4933invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m4933invoke() {
            SemanticsNode semanticsNode;
            LayoutNode layoutNode$ui_release;
            ScrollAxisRange horizontalScrollAxisRange = this.$scrollObservationScope.getHorizontalScrollAxisRange();
            ScrollAxisRange verticalScrollAxisRange = this.$scrollObservationScope.getVerticalScrollAxisRange();
            Float oldXValue = this.$scrollObservationScope.getOldXValue();
            Float oldYValue = this.$scrollObservationScope.getOldYValue();
            float fFloatValue = (horizontalScrollAxisRange == null || oldXValue == null) ? 0.0f : ((Number) horizontalScrollAxisRange.getValue().invoke()).floatValue() - oldXValue.floatValue();
            float fFloatValue2 = (verticalScrollAxisRange == null || oldYValue == null) ? 0.0f : ((Number) verticalScrollAxisRange.getValue().invoke()).floatValue() - oldYValue.floatValue();
            if (fFloatValue != 0.0f || fFloatValue2 != 0.0f) {
                int iSemanticsNodeIdToAccessibilityVirtualNodeId = this.this$0.semanticsNodeIdToAccessibilityVirtualNodeId(this.$scrollObservationScope.getSemanticsNodeId());
                SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) this.this$0.getCurrentSemanticsNodes().get(Integer.valueOf(this.this$0.focusedVirtualViewId));
                if (semanticsNodeWithAdjustedBounds != null) {
                    AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = this.this$0;
                    try {
                        AccessibilityNodeInfo accessibilityNodeInfo = androidComposeViewAccessibilityDelegateCompat.currentlyFocusedANI;
                        if (accessibilityNodeInfo != null) {
                            accessibilityNodeInfo.setBoundsInScreen(androidComposeViewAccessibilityDelegateCompat.boundsInScreen(semanticsNodeWithAdjustedBounds));
                        }
                    } catch (IllegalStateException unused) {
                    }
                }
                this.this$0.getView().invalidate();
                SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds2 = (SemanticsNodeWithAdjustedBounds) this.this$0.getCurrentSemanticsNodes().get(Integer.valueOf(iSemanticsNodeIdToAccessibilityVirtualNodeId));
                if (semanticsNodeWithAdjustedBounds2 != null && (semanticsNode = semanticsNodeWithAdjustedBounds2.getSemanticsNode()) != null && (layoutNode$ui_release = semanticsNode.getLayoutNode()) != null) {
                    AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat2 = this.this$0;
                    if (horizontalScrollAxisRange != null) {
                        androidComposeViewAccessibilityDelegateCompat2.pendingHorizontalScrollEvents.put(Integer.valueOf(iSemanticsNodeIdToAccessibilityVirtualNodeId), horizontalScrollAxisRange);
                    }
                    if (verticalScrollAxisRange != null) {
                        androidComposeViewAccessibilityDelegateCompat2.pendingVerticalScrollEvents.put(Integer.valueOf(iSemanticsNodeIdToAccessibilityVirtualNodeId), verticalScrollAxisRange);
                    }
                    androidComposeViewAccessibilityDelegateCompat2.notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode$ui_release);
                }
            }
            if (horizontalScrollAxisRange != null) {
                this.$scrollObservationScope.setOldXValue((Float) horizontalScrollAxisRange.getValue().invoke());
            }
            if (verticalScrollAxisRange != null) {
                this.$scrollObservationScope.setOldYValue((Float) verticalScrollAxisRange.getValue().invoke());
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/node/LayoutNode;", "invoke", "(Landroidx/compose/ui/node/LayoutNode;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06211 extends kotlin.jvm.internal.r implements r7.l<LayoutNode, Boolean> {
        public static final C06211 INSTANCE = new C06211();

        public C06211() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(LayoutNode layoutNode) {
            SemanticsConfiguration collapsedSemantics$ui_release = layoutNode.getCollapsedSemantics$ui_release();
            boolean z = false;
            if (collapsedSemantics$ui_release != null && collapsedSemantics$ui_release.getIsMergingSemanticsOfDescendants()) {
                z = true;
            }
            return Boolean.valueOf(z);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sortByGeometryGroupings$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "Landroidx/compose/ui/semantics/SemanticsNode;", "kotlin.jvm.PlatformType", "b", "invoke", "(Landroidx/compose/ui/semantics/SemanticsNode;Landroidx/compose/ui/semantics/SemanticsNode;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements r7.p<SemanticsNode, SemanticsNode, Integer> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(2);
        }

        @Override // r7.p
        public final Integer invoke(SemanticsNode semanticsNode, SemanticsNode semanticsNode2) {
            SemanticsConfiguration config = semanticsNode.getConfig();
            SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
            SemanticsPropertyKey<Float> traversalIndex = semanticsProperties.getTraversalIndex();
            AndroidComposeViewAccessibilityDelegateCompat_androidKt$traversalIndex$1 androidComposeViewAccessibilityDelegateCompat_androidKt$traversalIndex$1 = AndroidComposeViewAccessibilityDelegateCompat_androidKt$traversalIndex$1.INSTANCE;
            return Integer.valueOf(Float.compare(((Number) config.getOrElse(traversalIndex, androidComposeViewAccessibilityDelegateCompat_androidKt$traversalIndex$1)).floatValue(), ((Number) semanticsNode2.getConfig().getOrElse(semanticsProperties.getTraversalIndex(), androidComposeViewAccessibilityDelegateCompat_androidKt$traversalIndex$1)).floatValue()));
        }
    }

    public AndroidComposeViewAccessibilityDelegateCompat(AndroidComposeView androidComposeView) {
        this.view = androidComposeView;
        android.view.accessibility.AccessibilityManager accessibilityManager = (android.view.accessibility.AccessibilityManager) androidComposeView.getContext().getSystemService("accessibility");
        this.accessibilityManager = accessibilityManager;
        this.enabledStateListener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: androidx.compose.ui.platform.f
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public final void onAccessibilityStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat.enabledStateListener$lambda$0(this.f1934i, z);
            }
        };
        this.touchExplorationStateListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: androidx.compose.ui.platform.g
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat.touchExplorationStateListener$lambda$1(this.f1935i, z);
            }
        };
        this.enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        this.handler = new Handler(Looper.getMainLooper());
        this.nodeProvider = new q0.l(new ComposeAccessibilityNodeProvider());
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        this.pendingHorizontalScrollEvents = new HashMap<>();
        this.pendingVerticalScrollEvents = new HashMap<>();
        this.actionIdToLabel = new m0(0);
        this.labelToActionId = new m0(0);
        this.accessibilityCursorPosition = -1;
        this.subtreeChangedLayoutNodes = new t.f(0);
        this.boundsUpdateChannel = f0.a(1, 0, 6);
        this.currentSemanticsNodesInvalidated = true;
        this.bufferedContentCaptureAppearedNodes = new t.e(0);
        this.bufferedContentCaptureDisappearedNodes = new t.f(0);
        a0 a0Var = a0.f19683i;
        this.currentSemanticsNodes = a0Var;
        this.paneDisplayed = new t.f(0);
        this.idToBeforeMap = new HashMap<>();
        this.idToAfterMap = new HashMap<>();
        this.ExtraDataTestTraversalBeforeVal = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL";
        this.ExtraDataTestTraversalAfterVal = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL";
        this.urlSpanCache = new URLSpanCache();
        this.previousSemanticsNodes = new LinkedHashMap();
        this.previousSemanticsRoot = new SemanticsNodeCopy(androidComposeView.getSemanticsOwner().getUnmergedRootSemanticsNode(), a0Var);
        androidComposeView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                android.view.accessibility.AccessibilityManager accessibilityManager2 = AndroidComposeViewAccessibilityDelegateCompat.this.accessibilityManager;
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                accessibilityManager2.addAccessibilityStateChangeListener(androidComposeViewAccessibilityDelegateCompat.enabledStateListener);
                accessibilityManager2.addTouchExplorationStateChangeListener(androidComposeViewAccessibilityDelegateCompat.touchExplorationStateListener);
                if (AndroidComposeViewAccessibilityDelegateCompat.this.getContentCaptureForceEnabledForTesting()) {
                    return;
                }
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat2 = AndroidComposeViewAccessibilityDelegateCompat.this;
                androidComposeViewAccessibilityDelegateCompat2.setContentCaptureSession$ui_release(androidComposeViewAccessibilityDelegateCompat2.getContentCaptureSessionCompat(view));
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                AndroidComposeViewAccessibilityDelegateCompat.this.handler.removeCallbacks(AndroidComposeViewAccessibilityDelegateCompat.this.semanticsChangeChecker);
                android.view.accessibility.AccessibilityManager accessibilityManager2 = AndroidComposeViewAccessibilityDelegateCompat.this.accessibilityManager;
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                accessibilityManager2.removeAccessibilityStateChangeListener(androidComposeViewAccessibilityDelegateCompat.enabledStateListener);
                accessibilityManager2.removeTouchExplorationStateChangeListener(androidComposeViewAccessibilityDelegateCompat.touchExplorationStateListener);
                AndroidComposeViewAccessibilityDelegateCompat.this.setContentCaptureSession$ui_release(null);
            }
        });
        this.semanticsChangeChecker = new e(this, 2);
        this.scrollObservationScopes = new ArrayList();
        this.scheduleScrollEventIfNeededLambda = new AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeededLambda$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addExtraDataToAccessibilityNodeInfoHelper(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey, Bundle arguments) {
        SemanticsNode semanticsNode;
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(Integer.valueOf(virtualViewId));
        if (semanticsNodeWithAdjustedBounds == null || (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) == null) {
            return;
        }
        String iterableTextForAccessibility = getIterableTextForAccessibility(semanticsNode);
        if (kotlin.jvm.internal.p.a(extraDataKey, this.ExtraDataTestTraversalBeforeVal)) {
            Integer num = this.idToBeforeMap.get(Integer.valueOf(virtualViewId));
            if (num != null) {
                info.getExtras().putInt(extraDataKey, num.intValue());
                return;
            }
            return;
        }
        if (kotlin.jvm.internal.p.a(extraDataKey, this.ExtraDataTestTraversalAfterVal)) {
            Integer num2 = this.idToAfterMap.get(Integer.valueOf(virtualViewId));
            if (num2 != null) {
                info.getExtras().putInt(extraDataKey, num2.intValue());
                return;
            }
            return;
        }
        if (!semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult()) || arguments == null || !kotlin.jvm.internal.p.a(extraDataKey, "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
            SemanticsConfiguration unmergedConfig$ui_release = semanticsNode.getUnmergedConfig();
            SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
            if (!unmergedConfig$ui_release.contains(semanticsProperties.getTestTag()) || arguments == null || !kotlin.jvm.internal.p.a(extraDataKey, ExtraDataTestTagKey)) {
                if (kotlin.jvm.internal.p.a(extraDataKey, ExtraDataIdKey)) {
                    info.getExtras().putInt(extraDataKey, semanticsNode.getId());
                    return;
                }
                return;
            } else {
                String str = (String) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsProperties.getTestTag());
                if (str != null) {
                    info.getExtras().putCharSequence(extraDataKey, str);
                    return;
                }
                return;
            }
        }
        int i10 = arguments.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
        int i11 = arguments.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
        if (i11 > 0 && i10 >= 0) {
            if (i10 < (iterableTextForAccessibility != null ? iterableTextForAccessibility.length() : Integer.MAX_VALUE)) {
                TextLayoutResult textLayoutResult = getTextLayoutResult(semanticsNode.getUnmergedConfig());
                if (textLayoutResult == null) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (int i12 = 0; i12 < i11; i12++) {
                    int i13 = i10 + i12;
                    if (i13 >= textLayoutResult.getLayoutInput().getText().length()) {
                        arrayList.add(null);
                    } else {
                        arrayList.add(toScreenCoords(semanticsNode, textLayoutResult.getBoundingBox(i13)));
                    }
                }
                info.getExtras().putParcelableArray(extraDataKey, (Parcelable[]) arrayList.toArray(new RectF[0]));
                return;
            }
        }
        Log.e(LogTag, "Invalid arguments for accessibility character locations");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final android.graphics.Rect boundsInScreen(SemanticsNodeWithAdjustedBounds node) {
        android.graphics.Rect adjustedBounds = node.getAdjustedBounds();
        long jMo4587localToScreenMKHz9U = this.view.mo4587localToScreenMKHz9U(OffsetKt.Offset(adjustedBounds.left, adjustedBounds.top));
        long jMo4587localToScreenMKHz9U2 = this.view.mo4587localToScreenMKHz9U(OffsetKt.Offset(adjustedBounds.right, adjustedBounds.bottom));
        return new android.graphics.Rect((int) Math.floor(Offset.m3236getXimpl(jMo4587localToScreenMKHz9U)), (int) Math.floor(Offset.m3237getYimpl(jMo4587localToScreenMKHz9U)), (int) Math.ceil(Offset.m3236getXimpl(jMo4587localToScreenMKHz9U2)), (int) Math.ceil(Offset.m3237getYimpl(jMo4587localToScreenMKHz9U2)));
    }

    private final void bufferContentCaptureViewAppeared(int virtualId, ViewStructureCompat viewStructure) {
        if (viewStructure == null) {
            return;
        }
        if (this.bufferedContentCaptureDisappearedNodes.contains(Integer.valueOf(virtualId))) {
            this.bufferedContentCaptureDisappearedNodes.remove(Integer.valueOf(virtualId));
        } else {
            this.bufferedContentCaptureAppearedNodes.put(Integer.valueOf(virtualId), viewStructure);
        }
    }

    private final void bufferContentCaptureViewDisappeared(int virtualId) {
        if (this.bufferedContentCaptureAppearedNodes.containsKey(Integer.valueOf(virtualId))) {
            this.bufferedContentCaptureAppearedNodes.remove(Integer.valueOf(virtualId));
        } else {
            this.bufferedContentCaptureDisappearedNodes.add(Integer.valueOf(virtualId));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0055  */
    /* JADX INFO: renamed from: canScroll-moWRBKg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean m4931canScrollmoWRBKg(java.util.Collection<androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds> r6, boolean r7, int r8, long r9) {
        /*
            r5 = this;
            androidx.compose.ui.geometry.Offset$Companion r0 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r0 = r0.m3251getUnspecifiedF1C5BW0()
            boolean r0 = androidx.compose.ui.geometry.Offset.m3233equalsimpl0(r9, r0)
            r1 = 0
            if (r0 != 0) goto Lbc
            boolean r0 = androidx.compose.ui.geometry.Offset.m3239isValidimpl(r9)
            if (r0 != 0) goto L15
            goto Lbc
        L15:
            r0 = 1
            if (r7 != r0) goto L1f
            androidx.compose.ui.semantics.SemanticsProperties r7 = androidx.compose.ui.semantics.SemanticsProperties.INSTANCE
            androidx.compose.ui.semantics.SemanticsPropertyKey r7 = r7.getVerticalScrollAxisRange()
            goto L27
        L1f:
            if (r7 != 0) goto Lb6
            androidx.compose.ui.semantics.SemanticsProperties r7 = androidx.compose.ui.semantics.SemanticsProperties.INSTANCE
            androidx.compose.ui.semantics.SemanticsPropertyKey r7 = r7.getHorizontalScrollAxisRange()
        L27:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            boolean r2 = r6 instanceof java.util.Collection
            if (r2 == 0) goto L37
            r2 = r6
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L37
            return r1
        L37:
            java.util.Iterator r6 = r6.iterator()
        L3b:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto Lb5
            java.lang.Object r2 = r6.next()
            androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r2 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r2
            android.graphics.Rect r3 = r2.getAdjustedBounds()
            androidx.compose.ui.geometry.Rect r3 = androidx.compose.ui.graphics.RectHelper_androidKt.toComposeRect(r3)
            boolean r3 = r3.m3262containsk4lQ0M(r9)
            if (r3 != 0) goto L57
        L55:
            r2 = r1
            goto Lb2
        L57:
            androidx.compose.ui.semantics.SemanticsNode r2 = r2.getSemanticsNode()
            androidx.compose.ui.semantics.SemanticsConfiguration r2 = r2.getConfig()
            java.lang.Object r2 = androidx.compose.ui.semantics.SemanticsConfigurationKt.getOrNull(r2, r7)
            androidx.compose.ui.semantics.ScrollAxisRange r2 = (androidx.compose.ui.semantics.ScrollAxisRange) r2
            if (r2 != 0) goto L68
            goto L55
        L68:
            boolean r3 = r2.getReverseScrolling()
            if (r3 == 0) goto L70
            int r3 = -r8
            goto L71
        L70:
            r3 = r8
        L71:
            if (r8 != 0) goto L7a
            boolean r4 = r2.getReverseScrolling()
            if (r4 == 0) goto L7a
            r3 = -1
        L7a:
            if (r3 >= 0) goto L91
            r7.a r2 = r2.getValue()
            java.lang.Object r2 = r2.invoke()
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L55
        L8f:
            r2 = r0
            goto Lb2
        L91:
            r7.a r3 = r2.getValue()
            java.lang.Object r3 = r3.invoke()
            java.lang.Number r3 = (java.lang.Number) r3
            float r3 = r3.floatValue()
            r7.a r2 = r2.getMaxValue()
            java.lang.Object r2 = r2.invoke()
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 >= 0) goto L55
            goto L8f
        Lb2:
            if (r2 == 0) goto L3b
            return r0
        Lb5:
            return r1
        Lb6:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        Lbc:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.m4931canScrollmoWRBKg(java.util.Collection, boolean, int, long):boolean");
    }

    private final void checkForSemanticsChanges() {
        if (isEnabledForAccessibility$ui_release()) {
            sendAccessibilitySemanticsStructureChangeEvents(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), this.previousSemanticsRoot);
        }
        if (isEnabledForContentCapture()) {
            sendContentCaptureSemanticsStructureChangeEvents(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), this.previousSemanticsRoot);
        }
        sendSemanticsPropertyChangeEvents(getCurrentSemanticsNodes());
        updateSemanticsNodesCopyAndPanes();
    }

    private final boolean clearAccessibilityFocus(int virtualViewId) {
        if (!isAccessibilityFocused(virtualViewId)) {
            return false;
        }
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        this.currentlyFocusedANI = null;
        this.view.invalidate();
        sendEventForVirtualView$default(this, virtualViewId, 65536, null, null, 12, null);
        return true;
    }

    private final void clearTranslatedText() {
        AccessibilityAction accessibilityAction;
        r7.a aVar;
        Iterator<SemanticsNodeWithAdjustedBounds> it = getCurrentSemanticsNodes().values().iterator();
        while (it.hasNext()) {
            SemanticsConfiguration unmergedConfig$ui_release = it.next().getSemanticsNode().getUnmergedConfig();
            if (SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()) != null && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, SemanticsActions.INSTANCE.getClearTextSubstitution())) != null && (aVar = (r7.a) accessibilityAction.getAction()) != null) {
            }
        }
    }

    private final AccessibilityEvent createEvent(int virtualViewId, int eventType) {
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds;
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(eventType);
        accessibilityEventObtain.setEnabled(true);
        accessibilityEventObtain.setClassName(ClassName);
        accessibilityEventObtain.setPackageName(this.view.getContext().getPackageName());
        accessibilityEventObtain.setSource(this.view, virtualViewId);
        if (isEnabledForAccessibility$ui_release() && (semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(Integer.valueOf(virtualViewId))) != null) {
            accessibilityEventObtain.setPassword(semanticsNodeWithAdjustedBounds.getSemanticsNode().getConfig().contains(SemanticsProperties.INSTANCE.getPassword()));
        }
        return accessibilityEventObtain;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final AccessibilityNodeInfo createNodeInfo(int virtualViewId) {
        y lifecycleOwner;
        androidx.lifecycle.r lifecycle;
        AndroidComposeView.ViewTreeOwners viewTreeOwners = this.view.getViewTreeOwners();
        if (((viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null || (lifecycle = lifecycleOwner.getLifecycle()) == null) ? null : lifecycle.getCurrentState()) != androidx.lifecycle.q.f3266i) {
            AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain();
            q0.k kVar = new q0.k(accessibilityNodeInfoObtain);
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(Integer.valueOf(virtualViewId));
            if (semanticsNodeWithAdjustedBounds != null) {
                SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode();
                if (virtualViewId == -1) {
                    AndroidComposeView androidComposeView = this.view;
                    WeakHashMap weakHashMap = b2.f2200a;
                    ViewParent parentForAccessibility = androidComposeView.getParentForAccessibility();
                    View view = parentForAccessibility instanceof View ? (View) parentForAccessibility : null;
                    kVar.f21320b = -1;
                    accessibilityNodeInfoObtain.setParent(view);
                } else {
                    SemanticsNode parent = semanticsNode.getParent();
                    Integer numValueOf = parent != null ? Integer.valueOf(parent.getId()) : null;
                    if (numValueOf == null) {
                        throw new IllegalStateException(androidx.compose.foundation.c.o(virtualViewId, "semanticsNode ", " has null parent").toString());
                    }
                    int iIntValue = numValueOf.intValue();
                    int i10 = iIntValue != this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId() ? iIntValue : -1;
                    AndroidComposeView androidComposeView2 = this.view;
                    kVar.f21320b = i10;
                    accessibilityNodeInfoObtain.setParent(androidComposeView2, i10);
                }
                AndroidComposeView androidComposeView3 = this.view;
                kVar.f21321c = virtualViewId;
                accessibilityNodeInfoObtain.setSource(androidComposeView3, virtualViewId);
                accessibilityNodeInfoObtain.setBoundsInScreen(boundsInScreen(semanticsNodeWithAdjustedBounds));
                populateAccessibilityNodeInfoProperties(virtualViewId, kVar, semanticsNode);
                return accessibilityNodeInfoObtain;
            }
        }
        return null;
    }

    private final AccessibilityEvent createTextSelectionChangedEvent(int virtualViewId, Integer fromIndex, Integer toIndex, Integer itemCount, CharSequence text) {
        AccessibilityEvent accessibilityEventCreateEvent = createEvent(virtualViewId, 8192);
        if (fromIndex != null) {
            accessibilityEventCreateEvent.setFromIndex(fromIndex.intValue());
        }
        if (toIndex != null) {
            accessibilityEventCreateEvent.setToIndex(toIndex.intValue());
        }
        if (itemCount != null) {
            accessibilityEventCreateEvent.setItemCount(itemCount.intValue());
        }
        if (text != null) {
            accessibilityEventCreateEvent.getText().add(text);
        }
        return accessibilityEventCreateEvent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void enabledStateListener$lambda$0(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, boolean z) {
        androidComposeViewAccessibilityDelegateCompat.enabledServices = z ? androidComposeViewAccessibilityDelegateCompat.accessibilityManager.getEnabledAccessibilityServiceList(-1) : z.f19728i;
    }

    private final void geometryDepthFirstSearch(SemanticsNode currNode, ArrayList<SemanticsNode> geometryList, Map<Integer, List<SemanticsNode>> containerMapToChildren) {
        boolean z = currNode.getLayoutInfo().getLayoutDirection() == LayoutDirection.Rtl;
        boolean zBooleanValue = ((Boolean) currNode.getConfig().getOrElse(SemanticsProperties.INSTANCE.getIsTraversalGroup(), AndroidComposeViewAccessibilityDelegateCompat_androidKt.AnonymousClass1.INSTANCE)).booleanValue();
        if ((zBooleanValue || isScreenReaderFocusable(currNode)) && getCurrentSemanticsNodes().keySet().contains(Integer.valueOf(currNode.getId()))) {
            geometryList.add(currNode);
        }
        if (zBooleanValue) {
            containerMapToChildren.put(Integer.valueOf(currNode.getId()), subtreeSortedByGeometryGrouping(z, new ArrayList(currNode.getChildren())));
            return;
        }
        List<SemanticsNode> children = currNode.getChildren();
        int size = children.size();
        for (int i10 = 0; i10 < size; i10++) {
            geometryDepthFirstSearch(children.get(i10), geometryList, containerMapToChildren);
        }
    }

    private final int getAccessibilitySelectionEnd(SemanticsNode node) {
        SemanticsConfiguration unmergedConfig$ui_release = node.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        return (unmergedConfig$ui_release.contains(semanticsProperties.getContentDescription()) || !node.getUnmergedConfig().contains(semanticsProperties.getTextSelectionRange())) ? this.accessibilityCursorPosition : TextRange.m5187getEndimpl(((TextRange) node.getUnmergedConfig().get(semanticsProperties.getTextSelectionRange())).getPackedValue());
    }

    private final int getAccessibilitySelectionStart(SemanticsNode node) {
        SemanticsConfiguration unmergedConfig$ui_release = node.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        return (unmergedConfig$ui_release.contains(semanticsProperties.getContentDescription()) || !node.getUnmergedConfig().contains(semanticsProperties.getTextSelectionRange())) ? this.accessibilityCursorPosition : TextRange.m5192getStartimpl(((TextRange) node.getUnmergedConfig().get(semanticsProperties.getTextSelectionRange())).getPackedValue());
    }

    public static /* synthetic */ void getContentCaptureForceEnabledForTesting$ui_release$annotations() {
    }

    public static /* synthetic */ void getContentCaptureSession$ui_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContentCaptureSessionCompat getContentCaptureSessionCompat(View view) {
        ViewCompatShims.setImportantForContentCapture(view, 1);
        return ViewCompatShims.getContentCaptureSession(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<Integer, SemanticsNodeWithAdjustedBounds> getCurrentSemanticsNodes() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = AndroidComposeViewAccessibilityDelegateCompat_androidKt.getAllUncoveredSemanticsNodesToMap(this.view.getSemanticsOwner());
            if (isEnabledForAccessibility$ui_release()) {
                setTraversalValues();
            }
        }
        return this.currentSemanticsNodes;
    }

    public static /* synthetic */ void getHoveredVirtualViewId$ui_release$annotations() {
    }

    private final boolean getInfoIsCheckable(SemanticsNode node) {
        SemanticsConfiguration unmergedConfig$ui_release = node.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        ToggleableState toggleableState = (ToggleableState) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, semanticsProperties.getToggleableState());
        Role role = (Role) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), semanticsProperties.getRole());
        boolean z = toggleableState != null;
        if (((Boolean) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), semanticsProperties.getSelected())) != null) {
            if (!(role != null ? Role.m5019equalsimpl0(role.getValue(), Role.INSTANCE.m5029getTabo7Vup1c()) : false)) {
                return true;
            }
        }
        return z;
    }

    private final String getInfoStateDescriptionOrNull(SemanticsNode node) {
        int iN;
        SemanticsConfiguration unmergedConfig$ui_release = node.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        Object orNull = SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, semanticsProperties.getStateDescription());
        ToggleableState toggleableState = (ToggleableState) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), semanticsProperties.getToggleableState());
        Role role = (Role) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), semanticsProperties.getRole());
        if (toggleableState != null) {
            int i10 = WhenMappings.$EnumSwitchMapping$0[toggleableState.ordinal()];
            if (i10 == 1) {
                if ((role == null ? false : Role.m5019equalsimpl0(role.getValue(), Role.INSTANCE.m5028getSwitcho7Vup1c())) && orNull == null) {
                    orNull = this.view.getContext().getResources().getString(R.string.on);
                }
            } else if (i10 == 2) {
                if ((role == null ? false : Role.m5019equalsimpl0(role.getValue(), Role.INSTANCE.m5028getSwitcho7Vup1c())) && orNull == null) {
                    orNull = this.view.getContext().getResources().getString(R.string.off);
                }
            } else if (i10 == 3 && orNull == null) {
                orNull = this.view.getContext().getResources().getString(R.string.indeterminate);
            }
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), semanticsProperties.getSelected());
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (!(role == null ? false : Role.m5019equalsimpl0(role.getValue(), Role.INSTANCE.m5029getTabo7Vup1c())) && orNull == null) {
                orNull = zBooleanValue ? this.view.getContext().getResources().getString(R.string.selected) : this.view.getContext().getResources().getString(R.string.not_selected);
            }
        }
        ProgressBarRangeInfo progressBarRangeInfo = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), semanticsProperties.getProgressBarRangeInfo());
        if (progressBarRangeInfo != null) {
            if (progressBarRangeInfo != ProgressBarRangeInfo.INSTANCE.getIndeterminate()) {
                if (orNull == null) {
                    x7.e<Float> range = progressBarRangeInfo.getRange();
                    float fM = qb.d.m(((((Number) range.b()).floatValue() - ((Number) range.getStart()).floatValue()) > 0.0f ? 1 : ((((Number) range.b()).floatValue() - ((Number) range.getStart()).floatValue()) == 0.0f ? 0 : -1)) == 0 ? 0.0f : (progressBarRangeInfo.getCurrent() - ((Number) range.getStart()).floatValue()) / (((Number) range.b()).floatValue() - ((Number) range.getStart()).floatValue()), 0.0f, 1.0f);
                    if (fM == 0.0f) {
                        iN = 0;
                    } else {
                        iN = 100;
                        if (!(fM == 1.0f)) {
                            iN = qb.d.n(t7.a.M(fM * 100), 1, 99);
                        }
                    }
                    orNull = this.view.getContext().getResources().getString(R.string.template_percent, Integer.valueOf(iN));
                }
            } else if (orNull == null) {
                orNull = this.view.getContext().getResources().getString(R.string.in_progress);
            }
        }
        return (String) orNull;
    }

    private final SpannableString getInfoText(SemanticsNode node) {
        AnnotatedString annotatedString;
        FontFamily.Resolver fontFamilyResolver = this.view.getFontFamilyResolver();
        AnnotatedString textForTextField = getTextForTextField(node.getUnmergedConfig());
        SpannableString accessibilitySpannableString = null;
        SpannableString spannableString = (SpannableString) trimToSize(textForTextField != null ? AndroidAccessibilitySpannableString_androidKt.toAccessibilitySpannableString(textForTextField, this.view.getDensity(), fontFamilyResolver, this.urlSpanCache) : null, 100000);
        List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
        if (list != null && (annotatedString = (AnnotatedString) kotlin.collections.x.o0(list)) != null) {
            accessibilitySpannableString = AndroidAccessibilitySpannableString_androidKt.toAccessibilitySpannableString(annotatedString, this.view.getDensity(), fontFamilyResolver, this.urlSpanCache);
        }
        return spannableString == null ? (SpannableString) trimToSize(accessibilitySpannableString, 100000) : spannableString;
    }

    private final String getIterableTextForAccessibility(SemanticsNode node) {
        AnnotatedString annotatedString;
        if (node == null) {
            return null;
        }
        SemanticsConfiguration unmergedConfig$ui_release = node.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        if (unmergedConfig$ui_release.contains(semanticsProperties.getContentDescription())) {
            return ListUtilsKt.fastJoinToString$default((List) node.getUnmergedConfig().get(semanticsProperties.getContentDescription()), ",", null, null, 0, null, null, 62, null);
        }
        if (node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetText())) {
            AnnotatedString textForTextField = getTextForTextField(node.getUnmergedConfig());
            if (textForTextField != null) {
                return textForTextField.getText();
            }
            return null;
        }
        List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), semanticsProperties.getText());
        if (list == null || (annotatedString = (AnnotatedString) kotlin.collections.x.o0(list)) == null) {
            return null;
        }
        return annotatedString.getText();
    }

    private final AccessibilityIterators.TextSegmentIterator getIteratorForGranularity(SemanticsNode node, int granularity) {
        String iterableTextForAccessibility;
        TextLayoutResult textLayoutResult;
        if (node == null || (iterableTextForAccessibility = getIterableTextForAccessibility(node)) == null || iterableTextForAccessibility.length() == 0) {
            return null;
        }
        if (granularity == 1) {
            AccessibilityIterators.CharacterTextSegmentIterator companion = AccessibilityIterators.CharacterTextSegmentIterator.INSTANCE.getInstance(this.view.getContext().getResources().getConfiguration().locale);
            companion.initialize(iterableTextForAccessibility);
            return companion;
        }
        if (granularity == 2) {
            AccessibilityIterators.WordTextSegmentIterator companion2 = AccessibilityIterators.WordTextSegmentIterator.INSTANCE.getInstance(this.view.getContext().getResources().getConfiguration().locale);
            companion2.initialize(iterableTextForAccessibility);
            return companion2;
        }
        if (granularity != 4) {
            if (granularity == 8) {
                AccessibilityIterators.ParagraphTextSegmentIterator companion3 = AccessibilityIterators.ParagraphTextSegmentIterator.INSTANCE.getInstance();
                companion3.initialize(iterableTextForAccessibility);
                return companion3;
            }
            if (granularity != 16) {
                return null;
            }
        }
        if (!node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult()) || (textLayoutResult = getTextLayoutResult(node.getUnmergedConfig())) == null) {
            return null;
        }
        if (granularity == 4) {
            AccessibilityIterators.LineTextSegmentIterator companion4 = AccessibilityIterators.LineTextSegmentIterator.INSTANCE.getInstance();
            companion4.initialize(iterableTextForAccessibility, textLayoutResult);
            return companion4;
        }
        AccessibilityIterators.PageTextSegmentIterator companion5 = AccessibilityIterators.PageTextSegmentIterator.INSTANCE.getInstance();
        companion5.initialize(iterableTextForAccessibility, textLayoutResult, node);
        return companion5;
    }

    public static /* synthetic */ void getOnSendAccessibilityEvent$ui_release$annotations() {
    }

    private final AnnotatedString getTextForTextField(SemanticsConfiguration semanticsConfiguration) {
        return (AnnotatedString) SemanticsConfigurationKt.getOrNull(semanticsConfiguration, SemanticsProperties.INSTANCE.getEditableText());
    }

    private final TextLayoutResult getTextLayoutResult(SemanticsConfiguration configuration) {
        r7.l lVar;
        ArrayList arrayList = new ArrayList();
        AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(configuration, SemanticsActions.INSTANCE.getGetTextLayoutResult());
        if (accessibilityAction == null || (lVar = (r7.l) accessibilityAction.getAction()) == null || !((Boolean) lVar.invoke(arrayList)).booleanValue()) {
            return null;
        }
        return (TextLayoutResult) arrayList.get(0);
    }

    private final void hideTranslatedText() {
        AccessibilityAction accessibilityAction;
        r7.l lVar;
        Iterator<SemanticsNodeWithAdjustedBounds> it = getCurrentSemanticsNodes().values().iterator();
        while (it.hasNext()) {
            SemanticsConfiguration unmergedConfig$ui_release = it.next().getSemanticsNode().getUnmergedConfig();
            if (kotlin.jvm.internal.p.a(SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()), Boolean.TRUE) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, SemanticsActions.INSTANCE.getShowTextSubstitution())) != null && (lVar = (r7.l) accessibilityAction.getAction()) != null) {
            }
        }
    }

    private final void initContentCapture(boolean onStart) {
        if (onStart) {
            updateContentCaptureBuffersOnAppeared(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode());
        } else {
            updateContentCaptureBuffersOnDisappeared(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode());
        }
        notifyContentCaptureChanges();
    }

    private final boolean isAccessibilityFocused(int virtualViewId) {
        return this.focusedVirtualViewId == virtualViewId;
    }

    private final boolean isAccessibilitySelectionExtendable(SemanticsNode node) {
        SemanticsConfiguration unmergedConfig$ui_release = node.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        return !unmergedConfig$ui_release.contains(semanticsProperties.getContentDescription()) && node.getUnmergedConfig().contains(semanticsProperties.getEditableText());
    }

    private final boolean isEnabled() {
        return isEnabledForAccessibility$ui_release() || isEnabledForContentCapture();
    }

    private final boolean isEnabledForContentCapture() {
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.getDisableContentCapture()) {
            return false;
        }
        return this.contentCaptureSession != null || this.contentCaptureForceEnabledForTesting;
    }

    private static /* synthetic */ void isEnabledForContentCapture$annotations() {
    }

    private final boolean isScreenReaderFocusable(SemanticsNode node) {
        return node.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || (node.isUnmergedLeafNode$ui_release() && (AndroidComposeViewAccessibilityDelegateCompat_androidKt.getInfoContentDescriptionOrNull(node) != null || getInfoText(node) != null || getInfoStateDescriptionOrNull(node) != null || getInfoIsCheckable(node)));
    }

    private final boolean isTouchExplorationEnabled() {
        if (this.accessibilityForceEnabledForTesting) {
            return true;
        }
        return this.accessibilityManager.isEnabled() && this.accessibilityManager.isTouchExplorationEnabled();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void notifyContentCaptureChanges() {
        ContentCaptureSessionCompat contentCaptureSessionCompat = this.contentCaptureSession;
        if (contentCaptureSessionCompat != null && Build.VERSION.SDK_INT >= 29) {
            if (!this.bufferedContentCaptureAppearedNodes.isEmpty()) {
                List listC1 = kotlin.collections.x.c1(this.bufferedContentCaptureAppearedNodes.values());
                ArrayList arrayList = new ArrayList(listC1.size());
                int size = listC1.size();
                for (int i10 = 0; i10 < size; i10++) {
                    arrayList.add(((ViewStructureCompat) listC1.get(i10)).toViewStructure());
                }
                contentCaptureSessionCompat.notifyViewsAppeared(arrayList);
                this.bufferedContentCaptureAppearedNodes.clear();
            }
            if (this.bufferedContentCaptureDisappearedNodes.isEmpty()) {
                return;
            }
            List listC12 = kotlin.collections.x.c1(this.bufferedContentCaptureDisappearedNodes);
            ArrayList arrayList2 = new ArrayList(listC12.size());
            int size2 = listC12.size();
            for (int i11 = 0; i11 < size2; i11++) {
                arrayList2.add(Long.valueOf(((Number) listC12.get(i11)).intValue()));
            }
            contentCaptureSessionCompat.notifyViewsDisappeared(kotlin.collections.x.d1(arrayList2));
            this.bufferedContentCaptureDisappearedNodes.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySubtreeAccessibilityStateChangedIfNeeded(LayoutNode layoutNode) {
        if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
            this.boundsUpdateChannel.mo6685trySendJP2dKIU(t0.f22605a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:88:0x01b0 -> B:89:0x01b1). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:89:0x01b1
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:226)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:196)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:63)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:125)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    public final boolean performActionHelper(int r18, int r19, android.os.Bundle r20) {
        /*
            Method dump skipped, instruction units count: 1664
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.performActionHelper(int, int, android.os.Bundle):boolean");
    }

    private static final boolean performActionHelper$canScroll(ScrollAxisRange scrollAxisRange, float f10) {
        if (f10 >= 0.0f || ((Number) scrollAxisRange.getValue().invoke()).floatValue() <= 0.0f) {
            return f10 > 0.0f && ((Number) scrollAxisRange.getValue().invoke()).floatValue() < ((Number) scrollAxisRange.getMaxValue().invoke()).floatValue();
        }
        return true;
    }

    private static final float performActionHelper$scrollDelta(float f10, float f11) {
        if (Math.signum(f10) == Math.signum(f11)) {
            return Math.abs(f10) < Math.abs(f11) ? f10 : f11;
        }
        return 0.0f;
    }

    private final void populateAccessibilityNodeInfoProperties(int virtualViewId, q0.k info, SemanticsNode semanticsNode) {
        View viewSemanticsIdToView;
        Map map;
        boolean zBooleanValue;
        info.g(ClassName);
        AccessibilityNodeInfo accessibilityNodeInfo = info.f21319a;
        SemanticsConfiguration unmergedConfig$ui_release = semanticsNode.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        Role role = (Role) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, semanticsProperties.getRole());
        if (role != null) {
            role.getValue();
            if (semanticsNode.getIsFake() || semanticsNode.getReplacedChildren$ui_release().isEmpty()) {
                Role.Companion companion = Role.INSTANCE;
                if (Role.m5019equalsimpl0(role.getValue(), companion.m5029getTabo7Vup1c())) {
                    accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", this.view.getContext().getResources().getString(R.string.tab));
                } else if (Role.m5019equalsimpl0(role.getValue(), companion.m5028getSwitcho7Vup1c())) {
                    accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", this.view.getContext().getResources().getString(R.string.switch_role));
                } else {
                    String strM4935toLegacyClassNameV4PA4sw = AndroidComposeViewAccessibilityDelegateCompat_androidKt.m4935toLegacyClassNameV4PA4sw(role.getValue());
                    if (!Role.m5019equalsimpl0(role.getValue(), companion.m5026getImageo7Vup1c()) || semanticsNode.isUnmergedLeafNode$ui_release() || semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants()) {
                        info.g(strM4935toLegacyClassNameV4PA4sw);
                    }
                }
            }
        }
        if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetText())) {
            info.g(TextFieldClassName);
        }
        if (semanticsNode.getConfig().contains(semanticsProperties.getText())) {
            info.g(TextClassName);
        }
        accessibilityNodeInfo.setPackageName(this.view.getContext().getPackageName());
        info.j(AndroidComposeViewAccessibilityDelegateCompat_androidKt.isImportantForAccessibility(semanticsNode));
        List<SemanticsNode> replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size();
        for (int i10 = 0; i10 < size; i10++) {
            SemanticsNode semanticsNode2 = replacedChildren$ui_release.get(i10);
            if (getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode2.getId()))) {
                AndroidViewHolder androidViewHolder = this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(semanticsNode2.getLayoutNode());
                if (androidViewHolder != null) {
                    accessibilityNodeInfo.addChild(androidViewHolder);
                } else if (semanticsNode2.getId() != -1) {
                    accessibilityNodeInfo.addChild(this.view, semanticsNode2.getId());
                }
            }
        }
        if (virtualViewId == this.focusedVirtualViewId) {
            accessibilityNodeInfo.setAccessibilityFocused(true);
            info.b(q0.e.f21307d);
        } else {
            accessibilityNodeInfo.setAccessibilityFocused(false);
            info.b(q0.e.f21306c);
        }
        setText(semanticsNode, info);
        setContentInvalid(semanticsNode, info);
        setStateDescription(semanticsNode, info);
        setIsCheckable(semanticsNode, info);
        SemanticsConfiguration unmergedConfig$ui_release2 = semanticsNode.getUnmergedConfig();
        SemanticsProperties semanticsProperties2 = SemanticsProperties.INSTANCE;
        ToggleableState toggleableState = (ToggleableState) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release2, semanticsProperties2.getToggleableState());
        if (toggleableState != null) {
            if (toggleableState == ToggleableState.On) {
                accessibilityNodeInfo.setChecked(true);
            } else if (toggleableState == ToggleableState.Off) {
                accessibilityNodeInfo.setChecked(false);
            }
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsProperties2.getSelected());
        if (bool != null) {
            boolean zBooleanValue2 = bool.booleanValue();
            if (role == null ? false : Role.m5019equalsimpl0(role.getValue(), Role.INSTANCE.m5029getTabo7Vup1c())) {
                accessibilityNodeInfo.setSelected(zBooleanValue2);
            } else {
                accessibilityNodeInfo.setChecked(zBooleanValue2);
            }
        }
        if (!semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || semanticsNode.getReplacedChildren$ui_release().isEmpty()) {
            accessibilityNodeInfo.setContentDescription(AndroidComposeViewAccessibilityDelegateCompat_androidKt.getInfoContentDescriptionOrNull(semanticsNode));
        }
        String str = (String) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsProperties2.getTestTag());
        if (str != null) {
            SemanticsNode parent = semanticsNode;
            while (true) {
                if (parent == null) {
                    zBooleanValue = false;
                    break;
                }
                SemanticsConfiguration unmergedConfig$ui_release3 = parent.getUnmergedConfig();
                SemanticsPropertiesAndroid semanticsPropertiesAndroid = SemanticsPropertiesAndroid.INSTANCE;
                if (unmergedConfig$ui_release3.contains(semanticsPropertiesAndroid.getTestTagsAsResourceId())) {
                    zBooleanValue = ((Boolean) parent.getUnmergedConfig().get(semanticsPropertiesAndroid.getTestTagsAsResourceId())).booleanValue();
                    break;
                }
                parent = parent.getParent();
            }
            if (zBooleanValue) {
                accessibilityNodeInfo.setViewIdResourceName(str);
            }
        }
        SemanticsConfiguration unmergedConfig$ui_release4 = semanticsNode.getUnmergedConfig();
        SemanticsProperties semanticsProperties3 = SemanticsProperties.INSTANCE;
        if (((t0) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release4, semanticsProperties3.getHeading())) != null) {
            info.i(true);
        }
        accessibilityNodeInfo.setPassword(semanticsNode.getConfig().contains(semanticsProperties3.getPassword()));
        SemanticsConfiguration unmergedConfig$ui_release5 = semanticsNode.getUnmergedConfig();
        SemanticsActions semanticsActions = SemanticsActions.INSTANCE;
        accessibilityNodeInfo.setEditable(unmergedConfig$ui_release5.contains(semanticsActions.getSetText()));
        accessibilityNodeInfo.setEnabled(AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode));
        accessibilityNodeInfo.setFocusable(semanticsNode.getUnmergedConfig().contains(semanticsProperties3.getFocused()));
        if (accessibilityNodeInfo.isFocusable()) {
            accessibilityNodeInfo.setFocused(((Boolean) semanticsNode.getUnmergedConfig().get(semanticsProperties3.getFocused())).booleanValue());
            if (accessibilityNodeInfo.isFocused()) {
                info.a(2);
            } else {
                info.a(1);
            }
        }
        accessibilityNodeInfo.setVisibleToUser(AndroidComposeViewAccessibilityDelegateCompat_androidKt.isVisible(semanticsNode));
        LiveRegionMode liveRegionMode = (LiveRegionMode) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsProperties3.getLiveRegion());
        if (liveRegionMode != null) {
            int iM5013unboximpl = liveRegionMode.getValue();
            LiveRegionMode.Companion companion2 = LiveRegionMode.INSTANCE;
            accessibilityNodeInfo.setLiveRegion((LiveRegionMode.m5010equalsimpl0(iM5013unboximpl, companion2.m5015getPolite0phEisY()) || !LiveRegionMode.m5010equalsimpl0(iM5013unboximpl, companion2.m5014getAssertive0phEisY())) ? 1 : 2);
        }
        accessibilityNodeInfo.setClickable(false);
        AccessibilityAction accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getOnClick());
        if (accessibilityAction != null) {
            boolean zA = kotlin.jvm.internal.p.a(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsProperties3.getSelected()), Boolean.TRUE);
            accessibilityNodeInfo.setClickable(!zA);
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode) && !zA) {
                info.b(new q0.e(null, 16, accessibilityAction.getLabel(), null));
            }
        }
        accessibilityNodeInfo.setLongClickable(false);
        AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getOnLongClick());
        if (accessibilityAction2 != null) {
            accessibilityNodeInfo.setLongClickable(true);
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                info.b(new q0.e(null, 32, accessibilityAction2.getLabel(), null));
            }
        }
        AccessibilityAction accessibilityAction3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getCopyText());
        if (accessibilityAction3 != null) {
            info.b(new q0.e(null, 16384, accessibilityAction3.getLabel(), null));
        }
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
            AccessibilityAction accessibilityAction4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getSetText());
            if (accessibilityAction4 != null) {
                info.b(new q0.e(null, 2097152, accessibilityAction4.getLabel(), null));
            }
            AccessibilityAction accessibilityAction5 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getOnImeAction());
            if (accessibilityAction5 != null) {
                info.b(new q0.e(null, android.R.id.accessibilityActionImeEnter, accessibilityAction5.getLabel(), null));
            }
            AccessibilityAction accessibilityAction6 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getCutText());
            if (accessibilityAction6 != null) {
                info.b(new q0.e(null, 65536, accessibilityAction6.getLabel(), null));
            }
            AccessibilityAction accessibilityAction7 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getPasteText());
            if (accessibilityAction7 != null && accessibilityNodeInfo.isFocused() && this.view.getClipboardManager().hasText()) {
                info.b(new q0.e(null, 32768, accessibilityAction7.getLabel(), null));
            }
        }
        String iterableTextForAccessibility = getIterableTextForAccessibility(semanticsNode);
        if (!(iterableTextForAccessibility == null || iterableTextForAccessibility.length() == 0)) {
            accessibilityNodeInfo.setTextSelection(getAccessibilitySelectionStart(semanticsNode), getAccessibilitySelectionEnd(semanticsNode));
            AccessibilityAction accessibilityAction8 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getSetSelection());
            info.b(new q0.e(null, 131072, accessibilityAction8 != null ? accessibilityAction8.getLabel() : null, null));
            info.a(256);
            info.a(512);
            accessibilityNodeInfo.setMovementGranularities(11);
            List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsProperties3.getContentDescription());
            if ((list == null || list.isEmpty()) && semanticsNode.getUnmergedConfig().contains(semanticsActions.getGetTextLayoutResult()) && !AndroidComposeViewAccessibilityDelegateCompat_androidKt.excludeLineAndPageGranularities(semanticsNode)) {
                accessibilityNodeInfo.setMovementGranularities(accessibilityNodeInfo.getMovementGranularities() | 20);
            }
        }
        int i11 = Build.VERSION.SDK_INT;
        if (i11 >= 26) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(ExtraDataIdKey);
            CharSequence charSequenceF = info.f();
            if (!(charSequenceF == null || charSequenceF.length() == 0) && semanticsNode.getUnmergedConfig().contains(semanticsActions.getGetTextLayoutResult())) {
                arrayList.add("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY");
            }
            if (semanticsNode.getUnmergedConfig().contains(semanticsProperties3.getTestTag())) {
                arrayList.add(ExtraDataTestTagKey);
            }
            AccessibilityNodeInfoVerificationHelperMethods.INSTANCE.setAvailableExtraData(accessibilityNodeInfo, arrayList);
        }
        ProgressBarRangeInfo progressBarRangeInfo = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsProperties3.getProgressBarRangeInfo());
        if (progressBarRangeInfo != null) {
            if (semanticsNode.getUnmergedConfig().contains(semanticsActions.getSetProgress())) {
                info.g("android.widget.SeekBar");
            } else {
                info.g("android.widget.ProgressBar");
            }
            if (progressBarRangeInfo != ProgressBarRangeInfo.INSTANCE.getIndeterminate()) {
                accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(1, ((Number) progressBarRangeInfo.getRange().getStart()).floatValue(), ((Number) progressBarRangeInfo.getRange().b()).floatValue(), progressBarRangeInfo.getCurrent()));
            }
            if (semanticsNode.getUnmergedConfig().contains(semanticsActions.getSetProgress()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                float current = progressBarRangeInfo.getCurrent();
                float fFloatValue = ((Number) progressBarRangeInfo.getRange().b()).floatValue();
                float fFloatValue2 = ((Number) progressBarRangeInfo.getRange().getStart()).floatValue();
                if (fFloatValue < fFloatValue2) {
                    fFloatValue = fFloatValue2;
                }
                if (current < fFloatValue) {
                    info.b(q0.e.f21308e);
                }
                float current2 = progressBarRangeInfo.getCurrent();
                float fFloatValue3 = ((Number) progressBarRangeInfo.getRange().getStart()).floatValue();
                float fFloatValue4 = ((Number) progressBarRangeInfo.getRange().b()).floatValue();
                if (fFloatValue3 > fFloatValue4) {
                    fFloatValue3 = fFloatValue4;
                }
                if (current2 > fFloatValue3) {
                    info.b(q0.e.f21309f);
                }
            }
        }
        if (i11 >= 24) {
            Api24Impl.addSetProgressAction(info, semanticsNode);
        }
        CollectionInfo_androidKt.setCollectionInfo(semanticsNode, info);
        CollectionInfo_androidKt.setCollectionItemInfo(semanticsNode, info);
        ScrollAxisRange scrollAxisRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsProperties3.getHorizontalScrollAxisRange());
        AccessibilityAction accessibilityAction9 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getScrollBy());
        if (scrollAxisRange != null && accessibilityAction9 != null) {
            if (!CollectionInfo_androidKt.hasCollectionInfo(semanticsNode)) {
                info.g("android.widget.HorizontalScrollView");
            }
            if (((Number) scrollAxisRange.getMaxValue().invoke()).floatValue() > 0.0f) {
                info.m();
            }
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                if (populateAccessibilityNodeInfoProperties$canScrollForward(scrollAxisRange)) {
                    info.b(q0.e.f21308e);
                    info.b(!(semanticsNode.getLayoutInfo().getLayoutDirection() == LayoutDirection.Rtl) ? q0.e.f21313j : q0.e.f21311h);
                }
                if (populateAccessibilityNodeInfoProperties$canScrollBackward(scrollAxisRange)) {
                    info.b(q0.e.f21309f);
                    info.b(!(semanticsNode.getLayoutInfo().getLayoutDirection() == LayoutDirection.Rtl) ? q0.e.f21311h : q0.e.f21313j);
                }
            }
        }
        ScrollAxisRange scrollAxisRange2 = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsProperties3.getVerticalScrollAxisRange());
        if (scrollAxisRange2 != null && accessibilityAction9 != null) {
            if (!CollectionInfo_androidKt.hasCollectionInfo(semanticsNode)) {
                info.g("android.widget.ScrollView");
            }
            if (((Number) scrollAxisRange2.getMaxValue().invoke()).floatValue() > 0.0f) {
                info.m();
            }
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
                if (populateAccessibilityNodeInfoProperties$canScrollForward(scrollAxisRange2)) {
                    info.b(q0.e.f21308e);
                    info.b(q0.e.f21312i);
                }
                if (populateAccessibilityNodeInfoProperties$canScrollBackward(scrollAxisRange2)) {
                    info.b(q0.e.f21309f);
                    info.b(q0.e.f21310g);
                }
            }
        }
        if (i11 >= 29) {
            Api29Impl.addPageActions(info, semanticsNode);
        }
        info.k((CharSequence) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsProperties3.getPaneTitle()));
        if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode)) {
            AccessibilityAction accessibilityAction10 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getExpand());
            if (accessibilityAction10 != null) {
                info.b(new q0.e(null, MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES, accessibilityAction10.getLabel(), null));
            }
            AccessibilityAction accessibilityAction11 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getCollapse());
            if (accessibilityAction11 != null) {
                info.b(new q0.e(null, 524288, accessibilityAction11.getLabel(), null));
            }
            AccessibilityAction accessibilityAction12 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), semanticsActions.getDismiss());
            if (accessibilityAction12 != null) {
                info.b(new q0.e(null, ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, accessibilityAction12.getLabel(), null));
            }
            if (semanticsNode.getUnmergedConfig().contains(semanticsActions.getCustomActions())) {
                List list2 = (List) semanticsNode.getUnmergedConfig().get(semanticsActions.getCustomActions());
                int size2 = list2.size();
                int[] iArr = AccessibilityActionsResourceIds;
                if (size2 >= iArr.length) {
                    throw new IllegalStateException(androidx.compose.material3.d.j(iArr.length, " custom actions for one widget", new StringBuilder("Can't have more than ")));
                }
                m0 m0Var = new m0(0);
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                m0 m0Var2 = this.labelToActionId;
                if (m0Var2.f21851i) {
                    t.q.a(m0Var2);
                }
                if (u.a.a(m0Var2.f21854n, virtualViewId, m0Var2.f21852l) >= 0) {
                    Map map2 = (Map) this.labelToActionId.c(virtualViewId);
                    ArrayList arrayList2 = new ArrayList(iArr.length);
                    for (int i12 : iArr) {
                        arrayList2.add(Integer.valueOf(i12));
                    }
                    ArrayList arrayList3 = new ArrayList();
                    int size3 = list2.size();
                    int i13 = 0;
                    while (i13 < size3) {
                        CustomAccessibilityAction customAccessibilityAction = (CustomAccessibilityAction) list2.get(i13);
                        if (map2.containsKey(customAccessibilityAction.getLabel())) {
                            Integer num = (Integer) map2.get(customAccessibilityAction.getLabel());
                            map = map2;
                            m0Var.e(num.intValue(), customAccessibilityAction.getLabel());
                            linkedHashMap.put(customAccessibilityAction.getLabel(), num);
                            arrayList2.remove(num);
                            info.b(new q0.e(null, num.intValue(), customAccessibilityAction.getLabel(), null));
                        } else {
                            map = map2;
                            arrayList3.add(customAccessibilityAction);
                        }
                        i13++;
                        map2 = map;
                    }
                    int size4 = arrayList3.size();
                    for (int i14 = 0; i14 < size4; i14++) {
                        CustomAccessibilityAction customAccessibilityAction2 = (CustomAccessibilityAction) arrayList3.get(i14);
                        int iIntValue = ((Number) arrayList2.get(i14)).intValue();
                        m0Var.e(iIntValue, customAccessibilityAction2.getLabel());
                        linkedHashMap.put(customAccessibilityAction2.getLabel(), Integer.valueOf(iIntValue));
                        info.b(new q0.e(null, iIntValue, customAccessibilityAction2.getLabel(), null));
                    }
                } else {
                    int size5 = list2.size();
                    for (int i15 = 0; i15 < size5; i15++) {
                        CustomAccessibilityAction customAccessibilityAction3 = (CustomAccessibilityAction) list2.get(i15);
                        int i16 = AccessibilityActionsResourceIds[i15];
                        m0Var.e(i16, customAccessibilityAction3.getLabel());
                        linkedHashMap.put(customAccessibilityAction3.getLabel(), Integer.valueOf(i16));
                        info.b(new q0.e(null, i16, customAccessibilityAction3.getLabel(), null));
                    }
                }
                this.actionIdToLabel.e(virtualViewId, m0Var);
                this.labelToActionId.e(virtualViewId, linkedHashMap);
            }
        }
        info.l(isScreenReaderFocusable(semanticsNode));
        Integer num2 = this.idToBeforeMap.get(Integer.valueOf(virtualViewId));
        if (num2 != null) {
            View viewSemanticsIdToView2 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.semanticsIdToView(this.view.getAndroidViewsHandler$ui_release(), num2.intValue());
            if (viewSemanticsIdToView2 != null) {
                accessibilityNodeInfo.setTraversalBefore(viewSemanticsIdToView2);
            } else {
                accessibilityNodeInfo.setTraversalBefore(this.view, num2.intValue());
            }
            addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, accessibilityNodeInfo, this.ExtraDataTestTraversalBeforeVal, null);
        }
        Integer num3 = this.idToAfterMap.get(Integer.valueOf(virtualViewId));
        if (num3 == null || (viewSemanticsIdToView = AndroidComposeViewAccessibilityDelegateCompat_androidKt.semanticsIdToView(this.view.getAndroidViewsHandler$ui_release(), num3.intValue())) == null) {
            return;
        }
        accessibilityNodeInfo.setTraversalAfter(viewSemanticsIdToView);
        addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, accessibilityNodeInfo, this.ExtraDataTestTraversalAfterVal, null);
    }

    private static final boolean populateAccessibilityNodeInfoProperties$canScrollBackward(ScrollAxisRange scrollAxisRange) {
        if (((Number) scrollAxisRange.getValue().invoke()).floatValue() <= 0.0f || scrollAxisRange.getReverseScrolling()) {
            return ((Number) scrollAxisRange.getValue().invoke()).floatValue() < ((Number) scrollAxisRange.getMaxValue().invoke()).floatValue() && scrollAxisRange.getReverseScrolling();
        }
        return true;
    }

    private static final boolean populateAccessibilityNodeInfoProperties$canScrollForward(ScrollAxisRange scrollAxisRange) {
        if (((Number) scrollAxisRange.getValue().invoke()).floatValue() >= ((Number) scrollAxisRange.getMaxValue().invoke()).floatValue() || scrollAxisRange.getReverseScrolling()) {
            return ((Number) scrollAxisRange.getValue().invoke()).floatValue() > 0.0f && scrollAxisRange.getReverseScrolling();
        }
        return true;
    }

    private final boolean registerScrollingId(int id, List<ScrollObservationScope> oldScrollObservationScopes) {
        boolean z;
        ScrollObservationScope scrollObservationScopeFindById = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findById(oldScrollObservationScopes, id);
        if (scrollObservationScopeFindById != null) {
            z = false;
        } else {
            ScrollObservationScope scrollObservationScope = new ScrollObservationScope(id, this.scrollObservationScopes, null, null, null, null);
            z = true;
            scrollObservationScopeFindById = scrollObservationScope;
        }
        this.scrollObservationScopes.add(scrollObservationScopeFindById);
        return z;
    }

    private final boolean requestAccessibilityFocus(int virtualViewId) {
        if (!isTouchExplorationEnabled() || isAccessibilityFocused(virtualViewId)) {
            return false;
        }
        int i10 = this.focusedVirtualViewId;
        if (i10 != Integer.MIN_VALUE) {
            sendEventForVirtualView$default(this, i10, 65536, null, null, 12, null);
        }
        this.focusedVirtualViewId = virtualViewId;
        this.view.invalidate();
        sendEventForVirtualView$default(this, virtualViewId, 32768, null, null, 12, null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleScrollEventIfNeeded(ScrollObservationScope scrollObservationScope) {
        if (scrollObservationScope.isValidOwnerScope()) {
            this.view.getSnapshotObserver().observeReads$ui_release(scrollObservationScope, this.scheduleScrollEventIfNeededLambda, new C06201(scrollObservationScope, this));
        }
    }

    private final Comparator<SemanticsNode> semanticComparator(boolean layoutIsRtl) {
        return new AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$2(new AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$1(layoutIsRtl ? RtlBoundsComparator.INSTANCE : LtrBoundsComparator.INSTANCE, LayoutNode.INSTANCE.getZComparator$ui_release()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void semanticsChangeChecker$lambda$46(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat) {
        androidx.compose.ui.node.e.f(androidComposeViewAccessibilityDelegateCompat.view, false, 1, null);
        androidComposeViewAccessibilityDelegateCompat.checkForSemanticsChanges();
        androidComposeViewAccessibilityDelegateCompat.checkingForSemanticsChanges = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int semanticsNodeIdToAccessibilityVirtualNodeId(int id) {
        if (id == this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
            return -1;
        }
        return id;
    }

    private final void sendAccessibilitySemanticsStructureChangeEvents(SemanticsNode newNode, SemanticsNodeCopy oldNode) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List<SemanticsNode> replacedChildren$ui_release = newNode.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size();
        for (int i10 = 0; i10 < size; i10++) {
            SemanticsNode semanticsNode = replacedChildren$ui_release.get(i10);
            if (getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode.getId()))) {
                if (!oldNode.getChildren().contains(Integer.valueOf(semanticsNode.getId()))) {
                    notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                    return;
                }
                linkedHashSet.add(Integer.valueOf(semanticsNode.getId()));
            }
        }
        Iterator<Integer> it = oldNode.getChildren().iterator();
        while (it.hasNext()) {
            if (!linkedHashSet.contains(Integer.valueOf(it.next().intValue()))) {
                notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                return;
            }
        }
        List<SemanticsNode> replacedChildren$ui_release2 = newNode.getReplacedChildren$ui_release();
        int size2 = replacedChildren$ui_release2.size();
        for (int i11 = 0; i11 < size2; i11++) {
            SemanticsNode semanticsNode2 = replacedChildren$ui_release2.get(i11);
            if (getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode2.getId()))) {
                sendAccessibilitySemanticsStructureChangeEvents(semanticsNode2, this.previousSemanticsNodes.get(Integer.valueOf(semanticsNode2.getId())));
            }
        }
    }

    private final void sendContentCaptureSemanticsStructureChangeEvents(SemanticsNode newNode, SemanticsNodeCopy oldNode) {
        List<SemanticsNode> replacedChildren$ui_release = newNode.getReplacedChildren$ui_release();
        int size = replacedChildren$ui_release.size();
        for (int i10 = 0; i10 < size; i10++) {
            SemanticsNode semanticsNode = replacedChildren$ui_release.get(i10);
            if (getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode.getId())) && !oldNode.getChildren().contains(Integer.valueOf(semanticsNode.getId()))) {
                updateContentCaptureBuffersOnAppeared(semanticsNode);
            }
        }
        for (Map.Entry<Integer, SemanticsNodeCopy> entry : this.previousSemanticsNodes.entrySet()) {
            if (!getCurrentSemanticsNodes().containsKey(entry.getKey())) {
                bufferContentCaptureViewDisappeared(entry.getKey().intValue());
            }
        }
        List<SemanticsNode> replacedChildren$ui_release2 = newNode.getReplacedChildren$ui_release();
        int size2 = replacedChildren$ui_release2.size();
        for (int i11 = 0; i11 < size2; i11++) {
            SemanticsNode semanticsNode2 = replacedChildren$ui_release2.get(i11);
            if (getCurrentSemanticsNodes().containsKey(Integer.valueOf(semanticsNode2.getId())) && this.previousSemanticsNodes.containsKey(Integer.valueOf(semanticsNode2.getId()))) {
                sendContentCaptureSemanticsStructureChangeEvents(semanticsNode2, this.previousSemanticsNodes.get(Integer.valueOf(semanticsNode2.getId())));
            }
        }
    }

    private final void sendContentCaptureTextUpdateEvent(int id, String newText) {
        ContentCaptureSessionCompat contentCaptureSessionCompat = this.contentCaptureSession;
        if (contentCaptureSessionCompat != null && Build.VERSION.SDK_INT >= 29) {
            AutofillId autofillIdNewAutofillId = contentCaptureSessionCompat.newAutofillId(id);
            if (autofillIdNewAutofillId == null) {
                throw new IllegalStateException("Invalid content capture ID");
            }
            contentCaptureSessionCompat.notifyViewTextChanged(autofillIdNewAutofillId, newText);
        }
    }

    private final boolean sendEvent(AccessibilityEvent event) {
        if (!isEnabledForAccessibility$ui_release()) {
            return false;
        }
        if (event.getEventType() == 2048 || event.getEventType() == 32768) {
            this.sendingFocusAffectingEvent = true;
        }
        try {
            return ((Boolean) this.onSendAccessibilityEvent.invoke(event)).booleanValue();
        } finally {
            this.sendingFocusAffectingEvent = false;
        }
    }

    private final boolean sendEventForVirtualView(int virtualViewId, int eventType, Integer contentChangeType, List<String> contentDescription) {
        if (virtualViewId == Integer.MIN_VALUE || !isEnabled()) {
            return false;
        }
        AccessibilityEvent accessibilityEventCreateEvent = createEvent(virtualViewId, eventType);
        if (contentChangeType != null) {
            accessibilityEventCreateEvent.setContentChangeTypes(contentChangeType.intValue());
        }
        if (contentDescription != null) {
            accessibilityEventCreateEvent.setContentDescription(ListUtilsKt.fastJoinToString$default(contentDescription, ",", null, null, 0, null, null, 62, null));
        }
        return sendEvent(accessibilityEventCreateEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean sendEventForVirtualView$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, int i10, int i11, Integer num, List list, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            num = null;
        }
        if ((i12 & 8) != 0) {
            list = null;
        }
        return androidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView(i10, i11, num, list);
    }

    private final void sendPaneChangeEvents(int semanticsNodeId, int contentChangeType, String title) {
        AccessibilityEvent accessibilityEventCreateEvent = createEvent(semanticsNodeIdToAccessibilityVirtualNodeId(semanticsNodeId), 32);
        accessibilityEventCreateEvent.setContentChangeTypes(contentChangeType);
        if (title != null) {
            accessibilityEventCreateEvent.getText().add(title);
        }
        sendEvent(accessibilityEventCreateEvent);
    }

    private final void sendPendingTextTraversedAtGranularityEvent(int semanticsNodeId) {
        PendingTextTraversedEvent pendingTextTraversedEvent = this.pendingTextTraversedEvent;
        if (pendingTextTraversedEvent != null) {
            if (semanticsNodeId != pendingTextTraversedEvent.getNode().getId()) {
                return;
            }
            if (SystemClock.uptimeMillis() - pendingTextTraversedEvent.getTraverseTime() <= 1000) {
                AccessibilityEvent accessibilityEventCreateEvent = createEvent(semanticsNodeIdToAccessibilityVirtualNodeId(pendingTextTraversedEvent.getNode().getId()), 131072);
                accessibilityEventCreateEvent.setFromIndex(pendingTextTraversedEvent.getFromIndex());
                accessibilityEventCreateEvent.setToIndex(pendingTextTraversedEvent.getToIndex());
                accessibilityEventCreateEvent.setAction(pendingTextTraversedEvent.getAction());
                accessibilityEventCreateEvent.setMovementGranularity(pendingTextTraversedEvent.getGranularity());
                accessibilityEventCreateEvent.getText().add(getIterableTextForAccessibility(pendingTextTraversedEvent.getNode()));
                sendEvent(accessibilityEventCreateEvent);
            }
        }
        this.pendingTextTraversedEvent = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:130:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x058c  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x058f  */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v20 */
    /* JADX WARN: Type inference failed for: r17v21 */
    /* JADX WARN: Type inference failed for: r1v29, types: [java.util.Collection, java.util.LinkedHashSet, java.util.Set] */
    /* JADX WARN: Type inference failed for: r1v73 */
    /* JADX WARN: Type inference failed for: r1v82 */
    /* JADX WARN: Type inference failed for: r1v99 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.util.Collection, java.util.LinkedHashSet, java.util.Set] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void sendSemanticsPropertyChangeEvents(java.util.Map<java.lang.Integer, androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds> r31) {
        /*
            Method dump skipped, instruction units count: 1522
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendSemanticsPropertyChangeEvents(java.util.Map):void");
    }

    private final void sendSubtreeChangeAccessibilityEvents(LayoutNode layoutNode, t.f subtreeChangedSemanticsNodesIds) {
        SemanticsConfiguration collapsedSemantics$ui_release;
        LayoutNode layoutNodeFindClosestParentNode;
        if (layoutNode.isAttached() && !this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(layoutNode)) {
            int i10 = this.subtreeChangedLayoutNodes.f21811m;
            for (int i11 = 0; i11 < i10; i11++) {
                if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.isAncestorOf((LayoutNode) this.subtreeChangedLayoutNodes.f21810l[i11], layoutNode)) {
                    return;
                }
            }
            if (!layoutNode.getNodes().m4800hasH91voCI$ui_release(NodeKind.m4843constructorimpl(8))) {
                layoutNode = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(layoutNode, AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1.INSTANCE);
            }
            if (layoutNode == null || (collapsedSemantics$ui_release = layoutNode.getCollapsedSemantics$ui_release()) == null) {
                return;
            }
            if (!collapsedSemantics$ui_release.getIsMergingSemanticsOfDescendants() && (layoutNodeFindClosestParentNode = AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(layoutNode, C06211.INSTANCE)) != null) {
                layoutNode = layoutNodeFindClosestParentNode;
            }
            int semanticsId = layoutNode.getSemanticsId();
            if (subtreeChangedSemanticsNodesIds.add(Integer.valueOf(semanticsId))) {
                sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(semanticsId), 2048, 1, null, 8, null);
            }
        }
    }

    private final void sendTypeViewScrolledAccessibilityEvent(LayoutNode layoutNode) {
        if (layoutNode.isAttached() && !this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(layoutNode)) {
            int semanticsId = layoutNode.getSemanticsId();
            ScrollAxisRange scrollAxisRange = this.pendingHorizontalScrollEvents.get(Integer.valueOf(semanticsId));
            ScrollAxisRange scrollAxisRange2 = this.pendingVerticalScrollEvents.get(Integer.valueOf(semanticsId));
            if (scrollAxisRange == null && scrollAxisRange2 == null) {
                return;
            }
            AccessibilityEvent accessibilityEventCreateEvent = createEvent(semanticsId, 4096);
            if (scrollAxisRange != null) {
                accessibilityEventCreateEvent.setScrollX((int) ((Number) scrollAxisRange.getValue().invoke()).floatValue());
                accessibilityEventCreateEvent.setMaxScrollX((int) ((Number) scrollAxisRange.getMaxValue().invoke()).floatValue());
            }
            if (scrollAxisRange2 != null) {
                accessibilityEventCreateEvent.setScrollY((int) ((Number) scrollAxisRange2.getValue().invoke()).floatValue());
                accessibilityEventCreateEvent.setMaxScrollY((int) ((Number) scrollAxisRange2.getMaxValue().invoke()).floatValue());
            }
            sendEvent(accessibilityEventCreateEvent);
        }
    }

    private final boolean setAccessibilitySelection(SemanticsNode node, int start, int end, boolean traversalMode) {
        String iterableTextForAccessibility;
        SemanticsConfiguration unmergedConfig$ui_release = node.getUnmergedConfig();
        SemanticsActions semanticsActions = SemanticsActions.INSTANCE;
        if (unmergedConfig$ui_release.contains(semanticsActions.getSetSelection()) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(node)) {
            r7.q qVar = (r7.q) ((AccessibilityAction) node.getUnmergedConfig().get(semanticsActions.getSetSelection())).getAction();
            if (qVar != null) {
                return ((Boolean) qVar.invoke(Integer.valueOf(start), Integer.valueOf(end), Boolean.valueOf(traversalMode))).booleanValue();
            }
            return false;
        }
        if ((start == end && end == this.accessibilityCursorPosition) || (iterableTextForAccessibility = getIterableTextForAccessibility(node)) == null) {
            return false;
        }
        if (start < 0 || start != end || end > iterableTextForAccessibility.length()) {
            start = -1;
        }
        this.accessibilityCursorPosition = start;
        boolean z = iterableTextForAccessibility.length() > 0;
        sendEvent(createTextSelectionChangedEvent(semanticsNodeIdToAccessibilityVirtualNodeId(node.getId()), z ? Integer.valueOf(this.accessibilityCursorPosition) : null, z ? Integer.valueOf(this.accessibilityCursorPosition) : null, z ? Integer.valueOf(iterableTextForAccessibility.length()) : null, iterableTextForAccessibility));
        sendPendingTextTraversedAtGranularityEvent(node.getId());
        return true;
    }

    private final void setContentInvalid(SemanticsNode node, q0.k info) {
        SemanticsConfiguration unmergedConfig$ui_release = node.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        if (unmergedConfig$ui_release.contains(semanticsProperties.getError())) {
            info.f21319a.setContentInvalid(true);
            info.f21319a.setError((CharSequence) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), semanticsProperties.getError()));
        }
    }

    private final void setIsCheckable(SemanticsNode node, q0.k info) {
        info.f21319a.setCheckable(getInfoIsCheckable(node));
    }

    private final void setStateDescription(SemanticsNode node, q0.k info) {
        String infoStateDescriptionOrNull = getInfoStateDescriptionOrNull(node);
        AccessibilityNodeInfo accessibilityNodeInfo = info.f21319a;
        if (Build.VERSION.SDK_INT >= 30) {
            q0.f.c(accessibilityNodeInfo, infoStateDescriptionOrNull);
        } else {
            accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", infoStateDescriptionOrNull);
        }
    }

    private final void setText(SemanticsNode node, q0.k info) {
        info.f21319a.setText(getInfoText(node));
    }

    private final void setTraversalValues() {
        this.idToBeforeMap.clear();
        this.idToAfterMap.clear();
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(-1);
        SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
        int i10 = 1;
        List<SemanticsNode> listSubtreeSortedByGeometryGrouping = subtreeSortedByGeometryGrouping(semanticsNode.getLayoutInfo().getLayoutDirection() == LayoutDirection.Rtl, t7.a.G(semanticsNode));
        int iW = t7.a.w(listSubtreeSortedByGeometryGrouping);
        if (1 > iW) {
            return;
        }
        while (true) {
            int id = listSubtreeSortedByGeometryGrouping.get(i10 - 1).getId();
            int id2 = listSubtreeSortedByGeometryGrouping.get(i10).getId();
            this.idToBeforeMap.put(Integer.valueOf(id), Integer.valueOf(id2));
            this.idToAfterMap.put(Integer.valueOf(id2), Integer.valueOf(id));
            if (i10 == iW) {
                return;
            } else {
                i10++;
            }
        }
    }

    private final void showTranslatedText() {
        AccessibilityAction accessibilityAction;
        r7.l lVar;
        Iterator<SemanticsNodeWithAdjustedBounds> it = getCurrentSemanticsNodes().values().iterator();
        while (it.hasNext()) {
            SemanticsConfiguration unmergedConfig$ui_release = it.next().getSemanticsNode().getUnmergedConfig();
            if (kotlin.jvm.internal.p.a(SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()), Boolean.FALSE) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, SemanticsActions.INSTANCE.getShowTextSubstitution())) != null && (lVar = (r7.l) accessibilityAction.getAction()) != null) {
            }
        }
    }

    private final List<SemanticsNode> sortByGeometryGroupings(boolean layoutIsRtl, ArrayList<SemanticsNode> parentListToSort, Map<Integer, List<SemanticsNode>> containerChildrenMapping) {
        ArrayList arrayList = new ArrayList();
        int iW = t7.a.w(parentListToSort);
        int size = 0;
        if (iW >= 0) {
            int i10 = 0;
            while (true) {
                SemanticsNode semanticsNode = parentListToSort.get(i10);
                if (i10 == 0 || !sortByGeometryGroupings$placedEntryRowOverlaps(arrayList, semanticsNode)) {
                    arrayList.add(new x(semanticsNode.getBoundsInWindow(), t7.a.G(semanticsNode)));
                }
                if (i10 == iW) {
                    break;
                }
                i10++;
            }
        }
        w.X(TopBottomBoundsComparator.INSTANCE, arrayList);
        ArrayList arrayList2 = new ArrayList();
        int size2 = arrayList.size();
        for (int i11 = 0; i11 < size2; i11++) {
            x xVar = (x) arrayList.get(i11);
            w.X(new AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$2(new AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$1(layoutIsRtl ? RtlBoundsComparator.INSTANCE : LtrBoundsComparator.INSTANCE, LayoutNode.INSTANCE.getZComparator$ui_release())), (List) xVar.f22609l);
            arrayList2.addAll((Collection) xVar.f22609l);
        }
        final AnonymousClass2 anonymousClass2 = AnonymousClass2.INSTANCE;
        w.X(new Comparator() { // from class: androidx.compose.ui.platform.h
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return AndroidComposeViewAccessibilityDelegateCompat.sortByGeometryGroupings$lambda$7(anonymousClass2, obj, obj2);
            }
        }, arrayList2);
        while (size <= t7.a.w(arrayList2)) {
            List<SemanticsNode> list = containerChildrenMapping.get(Integer.valueOf(((SemanticsNode) arrayList2.get(size)).getId()));
            if (list != null) {
                if (isScreenReaderFocusable((SemanticsNode) arrayList2.get(size))) {
                    size++;
                } else {
                    arrayList2.remove(size);
                }
                arrayList2.addAll(size, list);
                size += list.size();
            } else {
                size++;
            }
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List sortByGeometryGroupings$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, boolean z, ArrayList arrayList, Map map, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            map = new LinkedHashMap();
        }
        return androidComposeViewAccessibilityDelegateCompat.sortByGeometryGroupings(z, arrayList, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int sortByGeometryGroupings$lambda$7(r7.p pVar, Object obj, Object obj2) {
        return ((Number) pVar.invoke(obj, obj2)).intValue();
    }

    private static final boolean sortByGeometryGroupings$placedEntryRowOverlaps(ArrayList<x> arrayList, SemanticsNode semanticsNode) {
        float top = semanticsNode.getBoundsInWindow().getTop();
        float bottom = semanticsNode.getBoundsInWindow().getBottom();
        boolean z = top >= bottom;
        int iW = t7.a.w(arrayList);
        if (iW >= 0) {
            int i10 = 0;
            while (true) {
                Rect rect = (Rect) arrayList.get(i10).f22608i;
                boolean z5 = rect.getTop() >= rect.getBottom();
                if (!z && !z5 && Math.max(top, rect.getTop()) < Math.min(bottom, rect.getBottom())) {
                    arrayList.set(i10, new x(rect.intersect(0.0f, top, Float.POSITIVE_INFINITY, bottom), arrayList.get(i10).f22609l));
                    ((List) arrayList.get(i10).f22609l).add(semanticsNode);
                    return true;
                }
                if (i10 == iW) {
                    break;
                }
                i10++;
            }
        }
        return false;
    }

    private final List<SemanticsNode> subtreeSortedByGeometryGrouping(boolean layoutIsRtl, List<SemanticsNode> listToSort) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList<SemanticsNode> arrayList = new ArrayList<>();
        int size = listToSort.size();
        for (int i10 = 0; i10 < size; i10++) {
            geometryDepthFirstSearch(listToSort.get(i10), arrayList, linkedHashMap);
        }
        return sortByGeometryGroupings(layoutIsRtl, arrayList, linkedHashMap);
    }

    private final RectF toScreenCoords(SemanticsNode textNode, Rect bounds) {
        if (textNode == null) {
            return null;
        }
        Rect rectM3273translatek4lQ0M = bounds.m3273translatek4lQ0M(textNode.m5031getPositionInRootF1C5BW0());
        Rect boundsInRoot = textNode.getBoundsInRoot();
        Rect rectIntersect = rectM3273translatek4lQ0M.overlaps(boundsInRoot) ? rectM3273translatek4lQ0M.intersect(boundsInRoot) : null;
        if (rectIntersect == null) {
            return null;
        }
        long jMo4587localToScreenMKHz9U = this.view.mo4587localToScreenMKHz9U(OffsetKt.Offset(rectIntersect.getLeft(), rectIntersect.getTop()));
        long jMo4587localToScreenMKHz9U2 = this.view.mo4587localToScreenMKHz9U(OffsetKt.Offset(rectIntersect.getRight(), rectIntersect.getBottom()));
        return new RectF(Offset.m3236getXimpl(jMo4587localToScreenMKHz9U), Offset.m3237getYimpl(jMo4587localToScreenMKHz9U), Offset.m3236getXimpl(jMo4587localToScreenMKHz9U2), Offset.m3237getYimpl(jMo4587localToScreenMKHz9U2));
    }

    private final ViewStructureCompat toViewStructure(SemanticsNode semanticsNode) {
        AutofillIdCompat autofillId;
        AutofillId autofillId2;
        String strM4935toLegacyClassNameV4PA4sw;
        ContentCaptureSessionCompat contentCaptureSessionCompat = this.contentCaptureSession;
        if (contentCaptureSessionCompat == null || Build.VERSION.SDK_INT < 29 || (autofillId = ViewCompatShims.getAutofillId(this.view)) == null) {
            return null;
        }
        if (semanticsNode.getParent() != null) {
            autofillId2 = contentCaptureSessionCompat.newAutofillId(r4.getId());
            if (autofillId2 == null) {
                return null;
            }
        } else {
            autofillId2 = autofillId.toAutofillId();
        }
        ViewStructureCompat viewStructureCompatNewVirtualViewStructure = contentCaptureSessionCompat.newVirtualViewStructure(autofillId2, semanticsNode.getId());
        if (viewStructureCompatNewVirtualViewStructure == null) {
            return null;
        }
        SemanticsConfiguration unmergedConfig$ui_release = semanticsNode.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        if (unmergedConfig$ui_release.contains(semanticsProperties.getPassword())) {
            return null;
        }
        List list = (List) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, semanticsProperties.getText());
        if (list != null) {
            viewStructureCompatNewVirtualViewStructure.setClassName(TextClassName);
            viewStructureCompatNewVirtualViewStructure.setText(ListUtilsKt.fastJoinToString$default(list, "\n", null, null, 0, null, null, 62, null));
        }
        AnnotatedString annotatedString = (AnnotatedString) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, semanticsProperties.getEditableText());
        if (annotatedString != null) {
            viewStructureCompatNewVirtualViewStructure.setClassName(TextFieldClassName);
            viewStructureCompatNewVirtualViewStructure.setText(annotatedString);
        }
        List list2 = (List) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, semanticsProperties.getContentDescription());
        if (list2 != null) {
            viewStructureCompatNewVirtualViewStructure.setContentDescription(ListUtilsKt.fastJoinToString$default(list2, "\n", null, null, 0, null, null, 62, null));
        }
        Role role = (Role) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, semanticsProperties.getRole());
        if (role != null && (strM4935toLegacyClassNameV4PA4sw = AndroidComposeViewAccessibilityDelegateCompat_androidKt.m4935toLegacyClassNameV4PA4sw(role.getValue())) != null) {
            viewStructureCompatNewVirtualViewStructure.setClassName(strM4935toLegacyClassNameV4PA4sw);
        }
        TextLayoutResult textLayoutResult = getTextLayoutResult(unmergedConfig$ui_release);
        if (textLayoutResult != null) {
            TextLayoutInput layoutInput = textLayoutResult.getLayoutInput();
            viewStructureCompatNewVirtualViewStructure.setTextStyle(layoutInput.getDensity().getFontScale() * layoutInput.getDensity().getDensity() * TextUnit.m5865getValueimpl(layoutInput.getStyle().m5223getFontSizeXSAIIZE()), 0, 0, 0);
        }
        Rect boundsInParent$ui_release = semanticsNode.getBoundsInParent$ui_release();
        viewStructureCompatNewVirtualViewStructure.setDimens((int) boundsInParent$ui_release.getLeft(), (int) boundsInParent$ui_release.getTop(), 0, 0, (int) boundsInParent$ui_release.getWidth(), (int) boundsInParent$ui_release.getHeight());
        return viewStructureCompatNewVirtualViewStructure;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void touchExplorationStateListener$lambda$1(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, boolean z) {
        androidComposeViewAccessibilityDelegateCompat.enabledServices = androidComposeViewAccessibilityDelegateCompat.accessibilityManager.getEnabledAccessibilityServiceList(-1);
    }

    private final boolean traverseAtGranularity(SemanticsNode node, int granularity, boolean forward, boolean extendSelection) {
        int accessibilitySelectionStart;
        int i10;
        int id = node.getId();
        Integer num = this.previousTraversedNode;
        if (num == null || id != num.intValue()) {
            this.accessibilityCursorPosition = -1;
            this.previousTraversedNode = Integer.valueOf(node.getId());
        }
        String iterableTextForAccessibility = getIterableTextForAccessibility(node);
        boolean z = false;
        if (iterableTextForAccessibility != null && iterableTextForAccessibility.length() != 0) {
            AccessibilityIterators.TextSegmentIterator iteratorForGranularity = getIteratorForGranularity(node, granularity);
            if (iteratorForGranularity == null) {
                return false;
            }
            int accessibilitySelectionEnd = getAccessibilitySelectionEnd(node);
            if (accessibilitySelectionEnd == -1) {
                accessibilitySelectionEnd = forward ? 0 : iterableTextForAccessibility.length();
            }
            int[] iArrFollowing = forward ? iteratorForGranularity.following(accessibilitySelectionEnd) : iteratorForGranularity.preceding(accessibilitySelectionEnd);
            if (iArrFollowing == null) {
                return false;
            }
            int i11 = iArrFollowing[0];
            z = true;
            int i12 = iArrFollowing[1];
            if (extendSelection && isAccessibilitySelectionExtendable(node)) {
                accessibilitySelectionStart = getAccessibilitySelectionStart(node);
                if (accessibilitySelectionStart == -1) {
                    accessibilitySelectionStart = forward ? i11 : i12;
                }
                i10 = forward ? i12 : i11;
            } else {
                accessibilitySelectionStart = forward ? i12 : i11;
                i10 = accessibilitySelectionStart;
            }
            this.pendingTextTraversedEvent = new PendingTextTraversedEvent(node, forward ? 256 : 512, granularity, i11, i12, SystemClock.uptimeMillis());
            setAccessibilitySelection(node, accessibilitySelectionStart, i10, true);
        }
        return z;
    }

    private final <T extends CharSequence> T trimToSize(T text, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size should be greater than 0");
        }
        if (text == null || text.length() == 0 || text.length() <= size) {
            return text;
        }
        int i10 = size - 1;
        if (Character.isHighSurrogate(text.charAt(i10)) && Character.isLowSurrogate(text.charAt(size))) {
            size = i10;
        }
        return (T) text.subSequence(0, size);
    }

    private final void updateContentCaptureBuffersOnAppeared(SemanticsNode node) {
        if (isEnabledForContentCapture()) {
            updateTranslationOnAppeared(node);
            bufferContentCaptureViewAppeared(node.getId(), toViewStructure(node));
            List<SemanticsNode> replacedChildren$ui_release = node.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            for (int i10 = 0; i10 < size; i10++) {
                updateContentCaptureBuffersOnAppeared(replacedChildren$ui_release.get(i10));
            }
        }
    }

    private final void updateContentCaptureBuffersOnDisappeared(SemanticsNode node) {
        if (isEnabledForContentCapture()) {
            bufferContentCaptureViewDisappeared(node.getId());
            List<SemanticsNode> replacedChildren$ui_release = node.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            for (int i10 = 0; i10 < size; i10++) {
                updateContentCaptureBuffersOnDisappeared(replacedChildren$ui_release.get(i10));
            }
        }
    }

    private final void updateHoveredVirtualView(int virtualViewId) {
        int i10 = this.hoveredVirtualViewId;
        if (i10 == virtualViewId) {
            return;
        }
        this.hoveredVirtualViewId = virtualViewId;
        sendEventForVirtualView$default(this, virtualViewId, 128, null, null, 12, null);
        sendEventForVirtualView$default(this, i10, 256, null, null, 12, null);
    }

    private final void updateSemanticsNodesCopyAndPanes() {
        SemanticsConfiguration unmergedConfig;
        t.f fVar = new t.f(0);
        t.f fVar2 = this.paneDisplayed;
        fVar2.getClass();
        t.a aVar = new t.a(fVar2);
        while (aVar.hasNext()) {
            int iIntValue = ((Number) aVar.next()).intValue();
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(Integer.valueOf(iIntValue));
            String str = null;
            SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
            if (semanticsNode == null || !AndroidComposeViewAccessibilityDelegateCompat_androidKt.hasPaneTitle(semanticsNode)) {
                fVar.add(Integer.valueOf(iIntValue));
                SemanticsNodeCopy semanticsNodeCopy = this.previousSemanticsNodes.get(Integer.valueOf(iIntValue));
                if (semanticsNodeCopy != null && (unmergedConfig = semanticsNodeCopy.getUnmergedConfig()) != null) {
                    str = (String) SemanticsConfigurationKt.getOrNull(unmergedConfig, SemanticsProperties.INSTANCE.getPaneTitle());
                }
                sendPaneChangeEvents(iIntValue, 32, str);
            }
        }
        t.f fVar3 = this.paneDisplayed;
        fVar3.getClass();
        int i10 = fVar.f21811m;
        for (int i11 = 0; i11 < i10; i11++) {
            fVar3.remove(fVar.f21810l[i11]);
        }
        this.previousSemanticsNodes.clear();
        for (Map.Entry<Integer, SemanticsNodeWithAdjustedBounds> entry : getCurrentSemanticsNodes().entrySet()) {
            if (AndroidComposeViewAccessibilityDelegateCompat_androidKt.hasPaneTitle(entry.getValue().getSemanticsNode()) && this.paneDisplayed.add(entry.getKey())) {
                sendPaneChangeEvents(entry.getKey().intValue(), 16, (String) entry.getValue().getSemanticsNode().getUnmergedConfig().get(SemanticsProperties.INSTANCE.getPaneTitle()));
            }
            this.previousSemanticsNodes.put(entry.getKey(), new SemanticsNodeCopy(entry.getValue().getSemanticsNode(), getCurrentSemanticsNodes()));
        }
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), getCurrentSemanticsNodes());
    }

    private final void updateTranslationOnAppeared(SemanticsNode node) {
        AccessibilityAction accessibilityAction;
        r7.l lVar;
        r7.l lVar2;
        SemanticsConfiguration unmergedConfig$ui_release = node.getUnmergedConfig();
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution());
        if (this.translateStatus == TranslateStatus.SHOW_ORIGINAL && kotlin.jvm.internal.p.a(bool, Boolean.TRUE)) {
            AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, SemanticsActions.INSTANCE.getShowTextSubstitution());
            if (accessibilityAction2 == null || (lVar2 = (r7.l) accessibilityAction2.getAction()) == null) {
                return;
            }
            return;
        }
        if (this.translateStatus != TranslateStatus.SHOW_TRANSLATED || !kotlin.jvm.internal.p.a(bool, Boolean.FALSE) || (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig$ui_release, SemanticsActions.INSTANCE.getShowTextSubstitution())) == null || (lVar = (r7.l) accessibilityAction.getAction()) == null) {
            return;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d3, code lost:
    
        if (ka.s0.a(100, r0) == r5) goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007c A[Catch: all -> 0x0038, TryCatch #0 {all -> 0x0038, blocks: (B:13:0x0033, B:25:0x0062, B:29:0x0074, B:31:0x007c, B:33:0x0085, B:34:0x0088, B:36:0x008e, B:38:0x0095, B:39:0x00a6, B:41:0x00ad, B:42:0x00b6, B:20:0x004f), top: B:51:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00d3 -> B:14:0x0036). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object boundsUpdatesEventLoop$ui_release(d7.d<? super x6.t0> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.boundsUpdatesEventLoop$ui_release(d7.d):java.lang.Object");
    }

    /* JADX INFO: renamed from: canScroll-0AR0LA0$ui_release, reason: not valid java name */
    public final boolean m4932canScroll0AR0LA0$ui_release(boolean vertical, int direction, long position) {
        if (kotlin.jvm.internal.p.a(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            return m4931canScrollmoWRBKg(getCurrentSemanticsNodes().values(), vertical, direction, position);
        }
        return false;
    }

    public final boolean dispatchHoverEvent$ui_release(MotionEvent event) {
        if (!isTouchExplorationEnabled()) {
            return false;
        }
        int action = event.getAction();
        if (action == 7 || action == 9) {
            int iHitTestSemanticsAt$ui_release = hitTestSemanticsAt$ui_release(event.getX(), event.getY());
            boolean zDispatchGenericMotionEvent = this.view.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(event);
            updateHoveredVirtualView(iHitTestSemanticsAt$ui_release);
            if (iHitTestSemanticsAt$ui_release == Integer.MIN_VALUE) {
                return zDispatchGenericMotionEvent;
            }
            return true;
        }
        if (action != 10) {
            return false;
        }
        if (this.hoveredVirtualViewId == Integer.MIN_VALUE) {
            return this.view.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(event);
        }
        updateHoveredVirtualView(Integer.MIN_VALUE);
        return true;
    }

    /* JADX INFO: renamed from: getAccessibilityForceEnabledForTesting$ui_release, reason: from getter */
    public final boolean getAccessibilityForceEnabledForTesting() {
        return this.accessibilityForceEnabledForTesting;
    }

    @Override // androidx.core.view.b
    public q0.l getAccessibilityNodeProvider(View host) {
        return this.nodeProvider;
    }

    /* JADX INFO: renamed from: getContentCaptureForceEnabledForTesting$ui_release, reason: from getter */
    public final boolean getContentCaptureForceEnabledForTesting() {
        return this.contentCaptureForceEnabledForTesting;
    }

    /* JADX INFO: renamed from: getContentCaptureSession$ui_release, reason: from getter */
    public final ContentCaptureSessionCompat getContentCaptureSession() {
        return this.contentCaptureSession;
    }

    /* JADX INFO: renamed from: getExtraDataTestTraversalAfterVal$ui_release, reason: from getter */
    public final String getExtraDataTestTraversalAfterVal() {
        return this.ExtraDataTestTraversalAfterVal;
    }

    /* JADX INFO: renamed from: getExtraDataTestTraversalBeforeVal$ui_release, reason: from getter */
    public final String getExtraDataTestTraversalBeforeVal() {
        return this.ExtraDataTestTraversalBeforeVal;
    }

    /* JADX INFO: renamed from: getHoveredVirtualViewId$ui_release, reason: from getter */
    public final int getHoveredVirtualViewId() {
        return this.hoveredVirtualViewId;
    }

    public final HashMap<Integer, Integer> getIdToAfterMap$ui_release() {
        return this.idToAfterMap;
    }

    public final HashMap<Integer, Integer> getIdToBeforeMap$ui_release() {
        return this.idToBeforeMap;
    }

    public final r7.l<AccessibilityEvent, Boolean> getOnSendAccessibilityEvent$ui_release() {
        return this.onSendAccessibilityEvent;
    }

    public final AndroidComposeView getView() {
        return this.view;
    }

    public final int hitTestSemanticsAt$ui_release(float x, float y10) {
        NodeChain nodes;
        androidx.compose.ui.node.e.f(this.view, false, 1, null);
        HitTestResult hitTestResult = new HitTestResult();
        LayoutNode.m4749hitTestSemanticsM_7yMNQ$ui_release$default(this.view.getRoot(), OffsetKt.Offset(x, y10), hitTestResult, false, false, 12, null);
        Modifier.Node node = (Modifier.Node) kotlin.collections.x.y0(hitTestResult);
        LayoutNode layoutNodeRequireLayoutNode = node != null ? DelegatableNodeKt.requireLayoutNode(node) : null;
        if (layoutNodeRequireLayoutNode != null && (nodes = layoutNodeRequireLayoutNode.getNodes()) != null && nodes.m4800hasH91voCI$ui_release(NodeKind.m4843constructorimpl(8)) && AndroidComposeViewAccessibilityDelegateCompat_androidKt.isVisible(SemanticsNodeKt.SemanticsNode(layoutNodeRequireLayoutNode, false)) && this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(layoutNodeRequireLayoutNode) == null) {
            return semanticsNodeIdToAccessibilityVirtualNodeId(layoutNodeRequireLayoutNode.getSemanticsId());
        }
        return Integer.MIN_VALUE;
    }

    public final boolean isEnabledForAccessibility$ui_release() {
        if (this.accessibilityForceEnabledForTesting) {
            return true;
        }
        return this.accessibilityManager.isEnabled() && !this.enabledServices.isEmpty();
    }

    public final void onClearTranslation$ui_release() {
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        clearTranslatedText();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onCreate(y yVar) {
        androidx.lifecycle.e.a(this, yVar);
    }

    public final void onCreateVirtualViewTranslationRequests$ui_release(long[] virtualIds, int[] supportedFormats, Consumer<ViewTranslationRequest> requestsCollector) {
        ViewTranslationHelperMethodsS.INSTANCE.onCreateVirtualViewTranslationRequests(this, virtualIds, supportedFormats, requestsCollector);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onDestroy(y yVar) {
        androidx.lifecycle.e.b(this, yVar);
    }

    public final void onHideTranslation$ui_release() {
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        hideTranslatedText();
    }

    public final void onLayoutChange$ui_release(LayoutNode layoutNode) {
        this.currentSemanticsNodesInvalidated = true;
        if (isEnabled()) {
            notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onPause(y yVar) {
        androidx.lifecycle.e.c(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onResume(y yVar) {
        androidx.lifecycle.e.d(this, yVar);
    }

    public final void onSemanticsChange$ui_release() {
        this.currentSemanticsNodesInvalidated = true;
        if (!isEnabled() || this.checkingForSemanticsChanges) {
            return;
        }
        this.checkingForSemanticsChanges = true;
        this.handler.post(this.semanticsChangeChecker);
    }

    public final void onShowTranslation$ui_release() {
        this.translateStatus = TranslateStatus.SHOW_TRANSLATED;
        showTranslatedText();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(y owner) {
        initContentCapture(true);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(y owner) {
        initContentCapture(false);
    }

    public final void onVirtualViewTranslationResponses$ui_release(LongSparseArray<ViewTranslationResponse> response) {
        ViewTranslationHelperMethodsS.INSTANCE.onVirtualViewTranslationResponses(this, response);
    }

    public final void setAccessibilityForceEnabledForTesting$ui_release(boolean z) {
        this.accessibilityForceEnabledForTesting = z;
        this.currentSemanticsNodesInvalidated = true;
    }

    public final void setContentCaptureForceEnabledForTesting$ui_release(boolean z) {
        this.contentCaptureForceEnabledForTesting = z;
    }

    public final void setContentCaptureSession$ui_release(ContentCaptureSessionCompat contentCaptureSessionCompat) {
        this.contentCaptureSession = contentCaptureSessionCompat;
    }

    public final void setHoveredVirtualViewId$ui_release(int i10) {
        this.hoveredVirtualViewId = i10;
    }

    public final void setIdToAfterMap$ui_release(HashMap<Integer, Integer> map) {
        this.idToAfterMap = map;
    }

    public final void setIdToBeforeMap$ui_release(HashMap<Integer, Integer> map) {
        this.idToBeforeMap = map;
    }

    public final void setOnSendAccessibilityEvent$ui_release(r7.l<? super AccessibilityEvent, Boolean> lVar) {
        this.onSendAccessibilityEvent = lVar;
    }
}

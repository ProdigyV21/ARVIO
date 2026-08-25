package io.ktor.util.internal;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0015\u001a\u00060\u0016j\u0002`\u0017*\u00020\u0001H\u0001\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001c\u0010\u0006\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\u0005\"\u0016\u0010\t\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0003\"\u001c\u0010\f\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000e\u0010\u0005\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0011\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0003\"\u0016\u0010\u0013\u001a\u00020\n8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0003*\n\u0010\u0018\"\u00020\u00192\u00020\u0019*\u001c\u0010\u001a\u001a\u0004\b\u0000\u0010\u001b\"\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\b\u0012\u0004\u0012\u0002H\u001b0\u001c*\f\b\u0002\u0010\u001d\"\u00020\u00162\u00020\u0016*\u001c\u0010\u001e\u001a\u0004\b\u0000\u0010\u001b\"\b\u0012\u0004\u0012\u0002H\u001b0\u001f2\b\u0012\u0004\u0012\u0002H\u001b0\u001f¨\u0006 "}, d2 = {"ALREADY_REMOVED", "", "getALREADY_REMOVED$annotations", "()V", "getALREADY_REMOVED", "()Ljava/lang/Object;", "CONDITION_FALSE", "getCONDITION_FALSE$annotations", "getCONDITION_FALSE", "FAILURE", "", "getFAILURE$annotations", "LIST_EMPTY", "getLIST_EMPTY$annotations", "getLIST_EMPTY", "NO_DECISION", "REMOVE_PREPARED", "SUCCESS", "getSUCCESS$annotations", "UNDECIDED", "getUNDECIDED$annotations", "unwrap", "Lio/ktor/util/internal/LockFreeLinkedListNode;", "Lio/ktor/util/internal/Node;", "AbstractAtomicDesc", "Lio/ktor/util/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "AddLastDesc", "T", "Lio/ktor/util/internal/LockFreeLinkedListNode$AddLastDesc;", "Node", "RemoveFirstDesc", "Lio/ktor/util/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LockFreeLinkedListKt {
    public static final int FAILURE = 2;
    public static final int SUCCESS = 1;
    public static final int UNDECIDED = 0;
    private static final Object CONDITION_FALSE = new Symbol("CONDITION_FALSE");
    private static final Object ALREADY_REMOVED = new Symbol("ALREADY_REMOVED");
    private static final Object LIST_EMPTY = new Symbol("LIST_EMPTY");
    private static final Object REMOVE_PREPARED = new Symbol("REMOVE_PREPARED");
    private static final Object NO_DECISION = new Symbol("NO_DECISION");

    public static final Object getALREADY_REMOVED() {
        return ALREADY_REMOVED;
    }

    public static /* synthetic */ void getALREADY_REMOVED$annotations() {
    }

    public static final Object getCONDITION_FALSE() {
        return CONDITION_FALSE;
    }

    public static /* synthetic */ void getCONDITION_FALSE$annotations() {
    }

    public static /* synthetic */ void getFAILURE$annotations() {
    }

    public static final Object getLIST_EMPTY() {
        return LIST_EMPTY;
    }

    public static /* synthetic */ void getLIST_EMPTY$annotations() {
    }

    public static /* synthetic */ void getSUCCESS$annotations() {
    }

    public static /* synthetic */ void getUNDECIDED$annotations() {
    }

    public static final LockFreeLinkedListNode unwrap(Object obj) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Removed removed = obj instanceof Removed ? (Removed) obj : null;
        return (removed == null || (lockFreeLinkedListNode = removed.ref) == null) ? (LockFreeLinkedListNode) obj : lockFreeLinkedListNode;
    }
}

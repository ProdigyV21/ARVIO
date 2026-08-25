package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.c;
import com.google.firebase.encoders.d;
import com.google.firebase.encoders.e;
import h5.a;
import h5.b;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class AutoCrashlyticsReportEncoder implements a {
    public static final int CODEGEN_VERSION = 2;
    public static final a CONFIG = new AutoCrashlyticsReportEncoder();

    public static final class CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder implements d<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> {
        static final CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder INSTANCE = new CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder();
        private static final c ARCH_DESCRIPTOR = c.a("arch");
        private static final c LIBRARYNAME_DESCRIPTOR = c.a("libraryName");
        private static final c BUILDID_DESCRIPTOR = c.a("buildId");

        private CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch buildIdMappingForArch, e eVar) throws IOException {
            eVar.f(ARCH_DESCRIPTOR, buildIdMappingForArch.getArch());
            eVar.f(LIBRARYNAME_DESCRIPTOR, buildIdMappingForArch.getLibraryName());
            eVar.f(BUILDID_DESCRIPTOR, buildIdMappingForArch.getBuildId());
        }
    }

    public static final class CrashlyticsReportApplicationExitInfoEncoder implements d<CrashlyticsReport.ApplicationExitInfo> {
        static final CrashlyticsReportApplicationExitInfoEncoder INSTANCE = new CrashlyticsReportApplicationExitInfoEncoder();
        private static final c PID_DESCRIPTOR = c.a("pid");
        private static final c PROCESSNAME_DESCRIPTOR = c.a("processName");
        private static final c REASONCODE_DESCRIPTOR = c.a("reasonCode");
        private static final c IMPORTANCE_DESCRIPTOR = c.a("importance");
        private static final c PSS_DESCRIPTOR = c.a("pss");
        private static final c RSS_DESCRIPTOR = c.a("rss");
        private static final c TIMESTAMP_DESCRIPTOR = c.a("timestamp");
        private static final c TRACEFILE_DESCRIPTOR = c.a("traceFile");
        private static final c BUILDIDMAPPINGFORARCH_DESCRIPTOR = c.a("buildIdMappingForArch");

        private CrashlyticsReportApplicationExitInfoEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.ApplicationExitInfo applicationExitInfo, e eVar) throws IOException {
            eVar.c(PID_DESCRIPTOR, applicationExitInfo.getPid());
            eVar.f(PROCESSNAME_DESCRIPTOR, applicationExitInfo.getProcessName());
            eVar.c(REASONCODE_DESCRIPTOR, applicationExitInfo.getReasonCode());
            eVar.c(IMPORTANCE_DESCRIPTOR, applicationExitInfo.getImportance());
            eVar.b(PSS_DESCRIPTOR, applicationExitInfo.getPss());
            eVar.b(RSS_DESCRIPTOR, applicationExitInfo.getRss());
            eVar.b(TIMESTAMP_DESCRIPTOR, applicationExitInfo.getTimestamp());
            eVar.f(TRACEFILE_DESCRIPTOR, applicationExitInfo.getTraceFile());
            eVar.f(BUILDIDMAPPINGFORARCH_DESCRIPTOR, applicationExitInfo.getBuildIdMappingForArch());
        }
    }

    public static final class CrashlyticsReportCustomAttributeEncoder implements d<CrashlyticsReport.CustomAttribute> {
        static final CrashlyticsReportCustomAttributeEncoder INSTANCE = new CrashlyticsReportCustomAttributeEncoder();
        private static final c KEY_DESCRIPTOR = c.a("key");
        private static final c VALUE_DESCRIPTOR = c.a("value");

        private CrashlyticsReportCustomAttributeEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.CustomAttribute customAttribute, e eVar) throws IOException {
            eVar.f(KEY_DESCRIPTOR, customAttribute.getKey());
            eVar.f(VALUE_DESCRIPTOR, customAttribute.getValue());
        }
    }

    public static final class CrashlyticsReportEncoder implements d<CrashlyticsReport> {
        static final CrashlyticsReportEncoder INSTANCE = new CrashlyticsReportEncoder();
        private static final c SDKVERSION_DESCRIPTOR = c.a("sdkVersion");
        private static final c GMPAPPID_DESCRIPTOR = c.a("gmpAppId");
        private static final c PLATFORM_DESCRIPTOR = c.a("platform");
        private static final c INSTALLATIONUUID_DESCRIPTOR = c.a("installationUuid");
        private static final c FIREBASEINSTALLATIONID_DESCRIPTOR = c.a("firebaseInstallationId");
        private static final c APPQUALITYSESSIONID_DESCRIPTOR = c.a("appQualitySessionId");
        private static final c BUILDVERSION_DESCRIPTOR = c.a("buildVersion");
        private static final c DISPLAYVERSION_DESCRIPTOR = c.a("displayVersion");
        private static final c SESSION_DESCRIPTOR = c.a(SettingsSessionManager.SETTINGS_KEY);
        private static final c NDKPAYLOAD_DESCRIPTOR = c.a("ndkPayload");
        private static final c APPEXITINFO_DESCRIPTOR = c.a("appExitInfo");

        private CrashlyticsReportEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport crashlyticsReport, e eVar) throws IOException {
            eVar.f(SDKVERSION_DESCRIPTOR, crashlyticsReport.getSdkVersion());
            eVar.f(GMPAPPID_DESCRIPTOR, crashlyticsReport.getGmpAppId());
            eVar.c(PLATFORM_DESCRIPTOR, crashlyticsReport.getPlatform());
            eVar.f(INSTALLATIONUUID_DESCRIPTOR, crashlyticsReport.getInstallationUuid());
            eVar.f(FIREBASEINSTALLATIONID_DESCRIPTOR, crashlyticsReport.getFirebaseInstallationId());
            eVar.f(APPQUALITYSESSIONID_DESCRIPTOR, crashlyticsReport.getAppQualitySessionId());
            eVar.f(BUILDVERSION_DESCRIPTOR, crashlyticsReport.getBuildVersion());
            eVar.f(DISPLAYVERSION_DESCRIPTOR, crashlyticsReport.getDisplayVersion());
            eVar.f(SESSION_DESCRIPTOR, crashlyticsReport.getSession());
            eVar.f(NDKPAYLOAD_DESCRIPTOR, crashlyticsReport.getNdkPayload());
            eVar.f(APPEXITINFO_DESCRIPTOR, crashlyticsReport.getAppExitInfo());
        }
    }

    public static final class CrashlyticsReportFilesPayloadEncoder implements d<CrashlyticsReport.FilesPayload> {
        static final CrashlyticsReportFilesPayloadEncoder INSTANCE = new CrashlyticsReportFilesPayloadEncoder();
        private static final c FILES_DESCRIPTOR = c.a("files");
        private static final c ORGID_DESCRIPTOR = c.a("orgId");

        private CrashlyticsReportFilesPayloadEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.FilesPayload filesPayload, e eVar) throws IOException {
            eVar.f(FILES_DESCRIPTOR, filesPayload.getFiles());
            eVar.f(ORGID_DESCRIPTOR, filesPayload.getOrgId());
        }
    }

    public static final class CrashlyticsReportFilesPayloadFileEncoder implements d<CrashlyticsReport.FilesPayload.File> {
        static final CrashlyticsReportFilesPayloadFileEncoder INSTANCE = new CrashlyticsReportFilesPayloadFileEncoder();
        private static final c FILENAME_DESCRIPTOR = c.a(ContentDisposition.Parameters.FileName);
        private static final c CONTENTS_DESCRIPTOR = c.a("contents");

        private CrashlyticsReportFilesPayloadFileEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.FilesPayload.File file, e eVar) throws IOException {
            eVar.f(FILENAME_DESCRIPTOR, file.getFilename());
            eVar.f(CONTENTS_DESCRIPTOR, file.getContents());
        }
    }

    public static final class CrashlyticsReportSessionApplicationEncoder implements d<CrashlyticsReport.Session.Application> {
        static final CrashlyticsReportSessionApplicationEncoder INSTANCE = new CrashlyticsReportSessionApplicationEncoder();
        private static final c IDENTIFIER_DESCRIPTOR = c.a("identifier");
        private static final c VERSION_DESCRIPTOR = c.a("version");
        private static final c DISPLAYVERSION_DESCRIPTOR = c.a("displayVersion");
        private static final c ORGANIZATION_DESCRIPTOR = c.a("organization");
        private static final c INSTALLATIONUUID_DESCRIPTOR = c.a("installationUuid");
        private static final c DEVELOPMENTPLATFORM_DESCRIPTOR = c.a("developmentPlatform");
        private static final c DEVELOPMENTPLATFORMVERSION_DESCRIPTOR = c.a("developmentPlatformVersion");

        private CrashlyticsReportSessionApplicationEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Application application, e eVar) throws IOException {
            eVar.f(IDENTIFIER_DESCRIPTOR, application.getIdentifier());
            eVar.f(VERSION_DESCRIPTOR, application.getVersion());
            eVar.f(DISPLAYVERSION_DESCRIPTOR, application.getDisplayVersion());
            eVar.f(ORGANIZATION_DESCRIPTOR, application.getOrganization());
            eVar.f(INSTALLATIONUUID_DESCRIPTOR, application.getInstallationUuid());
            eVar.f(DEVELOPMENTPLATFORM_DESCRIPTOR, application.getDevelopmentPlatform());
            eVar.f(DEVELOPMENTPLATFORMVERSION_DESCRIPTOR, application.getDevelopmentPlatformVersion());
        }
    }

    public static final class CrashlyticsReportSessionApplicationOrganizationEncoder implements d<CrashlyticsReport.Session.Application.Organization> {
        static final CrashlyticsReportSessionApplicationOrganizationEncoder INSTANCE = new CrashlyticsReportSessionApplicationOrganizationEncoder();
        private static final c CLSID_DESCRIPTOR = c.a("clsId");

        private CrashlyticsReportSessionApplicationOrganizationEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Application.Organization organization, e eVar) throws IOException {
            eVar.f(CLSID_DESCRIPTOR, organization.getClsId());
        }
    }

    public static final class CrashlyticsReportSessionDeviceEncoder implements d<CrashlyticsReport.Session.Device> {
        static final CrashlyticsReportSessionDeviceEncoder INSTANCE = new CrashlyticsReportSessionDeviceEncoder();
        private static final c ARCH_DESCRIPTOR = c.a("arch");
        private static final c MODEL_DESCRIPTOR = c.a("model");
        private static final c CORES_DESCRIPTOR = c.a("cores");
        private static final c RAM_DESCRIPTOR = c.a("ram");
        private static final c DISKSPACE_DESCRIPTOR = c.a("diskSpace");
        private static final c SIMULATOR_DESCRIPTOR = c.a("simulator");
        private static final c STATE_DESCRIPTOR = c.a("state");
        private static final c MANUFACTURER_DESCRIPTOR = c.a("manufacturer");
        private static final c MODELCLASS_DESCRIPTOR = c.a("modelClass");

        private CrashlyticsReportSessionDeviceEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Device device, e eVar) throws IOException {
            eVar.c(ARCH_DESCRIPTOR, device.getArch());
            eVar.f(MODEL_DESCRIPTOR, device.getModel());
            eVar.c(CORES_DESCRIPTOR, device.getCores());
            eVar.b(RAM_DESCRIPTOR, device.getRam());
            eVar.b(DISKSPACE_DESCRIPTOR, device.getDiskSpace());
            eVar.a(SIMULATOR_DESCRIPTOR, device.isSimulator());
            eVar.c(STATE_DESCRIPTOR, device.getState());
            eVar.f(MANUFACTURER_DESCRIPTOR, device.getManufacturer());
            eVar.f(MODELCLASS_DESCRIPTOR, device.getModelClass());
        }
    }

    public static final class CrashlyticsReportSessionEncoder implements d<CrashlyticsReport.Session> {
        static final CrashlyticsReportSessionEncoder INSTANCE = new CrashlyticsReportSessionEncoder();
        private static final c GENERATOR_DESCRIPTOR = c.a("generator");
        private static final c IDENTIFIER_DESCRIPTOR = c.a("identifier");
        private static final c APPQUALITYSESSIONID_DESCRIPTOR = c.a("appQualitySessionId");
        private static final c STARTEDAT_DESCRIPTOR = c.a("startedAt");
        private static final c ENDEDAT_DESCRIPTOR = c.a("endedAt");
        private static final c CRASHED_DESCRIPTOR = c.a("crashed");
        private static final c APP_DESCRIPTOR = c.a("app");
        private static final c USER_DESCRIPTOR = c.a("user");
        private static final c OS_DESCRIPTOR = c.a("os");
        private static final c DEVICE_DESCRIPTOR = c.a("device");
        private static final c EVENTS_DESCRIPTOR = c.a("events");
        private static final c GENERATORTYPE_DESCRIPTOR = c.a("generatorType");

        private CrashlyticsReportSessionEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session session, e eVar) throws IOException {
            eVar.f(GENERATOR_DESCRIPTOR, session.getGenerator());
            eVar.f(IDENTIFIER_DESCRIPTOR, session.getIdentifierUtf8Bytes());
            eVar.f(APPQUALITYSESSIONID_DESCRIPTOR, session.getAppQualitySessionId());
            eVar.b(STARTEDAT_DESCRIPTOR, session.getStartedAt());
            eVar.f(ENDEDAT_DESCRIPTOR, session.getEndedAt());
            eVar.a(CRASHED_DESCRIPTOR, session.isCrashed());
            eVar.f(APP_DESCRIPTOR, session.getApp());
            eVar.f(USER_DESCRIPTOR, session.getUser());
            eVar.f(OS_DESCRIPTOR, session.getOs());
            eVar.f(DEVICE_DESCRIPTOR, session.getDevice());
            eVar.f(EVENTS_DESCRIPTOR, session.getEvents());
            eVar.c(GENERATORTYPE_DESCRIPTOR, session.getGeneratorType());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationEncoder implements d<CrashlyticsReport.Session.Event.Application> {
        static final CrashlyticsReportSessionEventApplicationEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationEncoder();
        private static final c EXECUTION_DESCRIPTOR = c.a("execution");
        private static final c CUSTOMATTRIBUTES_DESCRIPTOR = c.a("customAttributes");
        private static final c INTERNALKEYS_DESCRIPTOR = c.a("internalKeys");
        private static final c BACKGROUND_DESCRIPTOR = c.a("background");
        private static final c CURRENTPROCESSDETAILS_DESCRIPTOR = c.a("currentProcessDetails");
        private static final c APPPROCESSDETAILS_DESCRIPTOR = c.a("appProcessDetails");
        private static final c UIORIENTATION_DESCRIPTOR = c.a("uiOrientation");

        private CrashlyticsReportSessionEventApplicationEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.Application application, e eVar) throws IOException {
            eVar.f(EXECUTION_DESCRIPTOR, application.getExecution());
            eVar.f(CUSTOMATTRIBUTES_DESCRIPTOR, application.getCustomAttributes());
            eVar.f(INTERNALKEYS_DESCRIPTOR, application.getInternalKeys());
            eVar.f(BACKGROUND_DESCRIPTOR, application.getBackground());
            eVar.f(CURRENTPROCESSDETAILS_DESCRIPTOR, application.getCurrentProcessDetails());
            eVar.f(APPPROCESSDETAILS_DESCRIPTOR, application.getAppProcessDetails());
            eVar.c(UIORIENTATION_DESCRIPTOR, application.getUiOrientation());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder implements d<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> {
        static final CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder();
        private static final c BASEADDRESS_DESCRIPTOR = c.a("baseAddress");
        private static final c SIZE_DESCRIPTOR = c.a(ContentDisposition.Parameters.Size);
        private static final c NAME_DESCRIPTOR = c.a(ContentDisposition.Parameters.Name);
        private static final c UUID_DESCRIPTOR = c.a("uuid");

        private CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage binaryImage, e eVar) throws IOException {
            eVar.b(BASEADDRESS_DESCRIPTOR, binaryImage.getBaseAddress());
            eVar.b(SIZE_DESCRIPTOR, binaryImage.getSize());
            eVar.f(NAME_DESCRIPTOR, binaryImage.getName());
            eVar.f(UUID_DESCRIPTOR, binaryImage.getUuidUtf8Bytes());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionEncoder implements d<CrashlyticsReport.Session.Event.Application.Execution> {
        static final CrashlyticsReportSessionEventApplicationExecutionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionEncoder();
        private static final c THREADS_DESCRIPTOR = c.a("threads");
        private static final c EXCEPTION_DESCRIPTOR = c.a("exception");
        private static final c APPEXITINFO_DESCRIPTOR = c.a("appExitInfo");
        private static final c SIGNAL_DESCRIPTOR = c.a("signal");
        private static final c BINARIES_DESCRIPTOR = c.a("binaries");

        private CrashlyticsReportSessionEventApplicationExecutionEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.Application.Execution execution, e eVar) throws IOException {
            eVar.f(THREADS_DESCRIPTOR, execution.getThreads());
            eVar.f(EXCEPTION_DESCRIPTOR, execution.getException());
            eVar.f(APPEXITINFO_DESCRIPTOR, execution.getAppExitInfo());
            eVar.f(SIGNAL_DESCRIPTOR, execution.getSignal());
            eVar.f(BINARIES_DESCRIPTOR, execution.getBinaries());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder implements d<CrashlyticsReport.Session.Event.Application.Execution.Exception> {
        static final CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder();
        private static final c TYPE_DESCRIPTOR = c.a(LinkHeader.Parameters.Type);
        private static final c REASON_DESCRIPTOR = c.a("reason");
        private static final c FRAMES_DESCRIPTOR = c.a("frames");
        private static final c CAUSEDBY_DESCRIPTOR = c.a("causedBy");
        private static final c OVERFLOWCOUNT_DESCRIPTOR = c.a("overflowCount");

        private CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Exception exception, e eVar) throws IOException {
            eVar.f(TYPE_DESCRIPTOR, exception.getType());
            eVar.f(REASON_DESCRIPTOR, exception.getReason());
            eVar.f(FRAMES_DESCRIPTOR, exception.getFrames());
            eVar.f(CAUSEDBY_DESCRIPTOR, exception.getCausedBy());
            eVar.c(OVERFLOWCOUNT_DESCRIPTOR, exception.getOverflowCount());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionSignalEncoder implements d<CrashlyticsReport.Session.Event.Application.Execution.Signal> {
        static final CrashlyticsReportSessionEventApplicationExecutionSignalEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionSignalEncoder();
        private static final c NAME_DESCRIPTOR = c.a(ContentDisposition.Parameters.Name);
        private static final c CODE_DESCRIPTOR = c.a("code");
        private static final c ADDRESS_DESCRIPTOR = c.a("address");

        private CrashlyticsReportSessionEventApplicationExecutionSignalEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Signal signal, e eVar) throws IOException {
            eVar.f(NAME_DESCRIPTOR, signal.getName());
            eVar.f(CODE_DESCRIPTOR, signal.getCode());
            eVar.b(ADDRESS_DESCRIPTOR, signal.getAddress());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionThreadEncoder implements d<CrashlyticsReport.Session.Event.Application.Execution.Thread> {
        static final CrashlyticsReportSessionEventApplicationExecutionThreadEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadEncoder();
        private static final c NAME_DESCRIPTOR = c.a(ContentDisposition.Parameters.Name);
        private static final c IMPORTANCE_DESCRIPTOR = c.a("importance");
        private static final c FRAMES_DESCRIPTOR = c.a("frames");

        private CrashlyticsReportSessionEventApplicationExecutionThreadEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread thread, e eVar) throws IOException {
            eVar.f(NAME_DESCRIPTOR, thread.getName());
            eVar.c(IMPORTANCE_DESCRIPTOR, thread.getImportance());
            eVar.f(FRAMES_DESCRIPTOR, thread.getFrames());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder implements d<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> {
        static final CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder();
        private static final c PC_DESCRIPTOR = c.a("pc");
        private static final c SYMBOL_DESCRIPTOR = c.a("symbol");
        private static final c FILE_DESCRIPTOR = c.a("file");
        private static final c OFFSET_DESCRIPTOR = c.a("offset");
        private static final c IMPORTANCE_DESCRIPTOR = c.a("importance");

        private CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame frame, e eVar) throws IOException {
            eVar.b(PC_DESCRIPTOR, frame.getPc());
            eVar.f(SYMBOL_DESCRIPTOR, frame.getSymbol());
            eVar.f(FILE_DESCRIPTOR, frame.getFile());
            eVar.b(OFFSET_DESCRIPTOR, frame.getOffset());
            eVar.c(IMPORTANCE_DESCRIPTOR, frame.getImportance());
        }
    }

    public static final class CrashlyticsReportSessionEventApplicationProcessDetailsEncoder implements d<CrashlyticsReport.Session.Event.Application.ProcessDetails> {
        static final CrashlyticsReportSessionEventApplicationProcessDetailsEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationProcessDetailsEncoder();
        private static final c PROCESSNAME_DESCRIPTOR = c.a("processName");
        private static final c PID_DESCRIPTOR = c.a("pid");
        private static final c IMPORTANCE_DESCRIPTOR = c.a("importance");
        private static final c DEFAULTPROCESS_DESCRIPTOR = c.a("defaultProcess");

        private CrashlyticsReportSessionEventApplicationProcessDetailsEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.Application.ProcessDetails processDetails, e eVar) throws IOException {
            eVar.f(PROCESSNAME_DESCRIPTOR, processDetails.getProcessName());
            eVar.c(PID_DESCRIPTOR, processDetails.getPid());
            eVar.c(IMPORTANCE_DESCRIPTOR, processDetails.getImportance());
            eVar.a(DEFAULTPROCESS_DESCRIPTOR, processDetails.isDefaultProcess());
        }
    }

    public static final class CrashlyticsReportSessionEventDeviceEncoder implements d<CrashlyticsReport.Session.Event.Device> {
        static final CrashlyticsReportSessionEventDeviceEncoder INSTANCE = new CrashlyticsReportSessionEventDeviceEncoder();
        private static final c BATTERYLEVEL_DESCRIPTOR = c.a("batteryLevel");
        private static final c BATTERYVELOCITY_DESCRIPTOR = c.a("batteryVelocity");
        private static final c PROXIMITYON_DESCRIPTOR = c.a("proximityOn");
        private static final c ORIENTATION_DESCRIPTOR = c.a("orientation");
        private static final c RAMUSED_DESCRIPTOR = c.a("ramUsed");
        private static final c DISKUSED_DESCRIPTOR = c.a("diskUsed");

        private CrashlyticsReportSessionEventDeviceEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.Device device, e eVar) throws IOException {
            eVar.f(BATTERYLEVEL_DESCRIPTOR, device.getBatteryLevel());
            eVar.c(BATTERYVELOCITY_DESCRIPTOR, device.getBatteryVelocity());
            eVar.a(PROXIMITYON_DESCRIPTOR, device.isProximityOn());
            eVar.c(ORIENTATION_DESCRIPTOR, device.getOrientation());
            eVar.b(RAMUSED_DESCRIPTOR, device.getRamUsed());
            eVar.b(DISKUSED_DESCRIPTOR, device.getDiskUsed());
        }
    }

    public static final class CrashlyticsReportSessionEventEncoder implements d<CrashlyticsReport.Session.Event> {
        static final CrashlyticsReportSessionEventEncoder INSTANCE = new CrashlyticsReportSessionEventEncoder();
        private static final c TIMESTAMP_DESCRIPTOR = c.a("timestamp");
        private static final c TYPE_DESCRIPTOR = c.a(LinkHeader.Parameters.Type);
        private static final c APP_DESCRIPTOR = c.a("app");
        private static final c DEVICE_DESCRIPTOR = c.a("device");
        private static final c LOG_DESCRIPTOR = c.a("log");
        private static final c ROLLOUTS_DESCRIPTOR = c.a("rollouts");

        private CrashlyticsReportSessionEventEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event event, e eVar) throws IOException {
            eVar.b(TIMESTAMP_DESCRIPTOR, event.getTimestamp());
            eVar.f(TYPE_DESCRIPTOR, event.getType());
            eVar.f(APP_DESCRIPTOR, event.getApp());
            eVar.f(DEVICE_DESCRIPTOR, event.getDevice());
            eVar.f(LOG_DESCRIPTOR, event.getLog());
            eVar.f(ROLLOUTS_DESCRIPTOR, event.getRollouts());
        }
    }

    public static final class CrashlyticsReportSessionEventLogEncoder implements d<CrashlyticsReport.Session.Event.Log> {
        static final CrashlyticsReportSessionEventLogEncoder INSTANCE = new CrashlyticsReportSessionEventLogEncoder();
        private static final c CONTENT_DESCRIPTOR = c.a("content");

        private CrashlyticsReportSessionEventLogEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.Log log, e eVar) throws IOException {
            eVar.f(CONTENT_DESCRIPTOR, log.getContent());
        }
    }

    public static final class CrashlyticsReportSessionEventRolloutAssignmentEncoder implements d<CrashlyticsReport.Session.Event.RolloutAssignment> {
        static final CrashlyticsReportSessionEventRolloutAssignmentEncoder INSTANCE = new CrashlyticsReportSessionEventRolloutAssignmentEncoder();
        private static final c ROLLOUTVARIANT_DESCRIPTOR = c.a("rolloutVariant");
        private static final c PARAMETERKEY_DESCRIPTOR = c.a("parameterKey");
        private static final c PARAMETERVALUE_DESCRIPTOR = c.a("parameterValue");
        private static final c TEMPLATEVERSION_DESCRIPTOR = c.a("templateVersion");

        private CrashlyticsReportSessionEventRolloutAssignmentEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.RolloutAssignment rolloutAssignment, e eVar) throws IOException {
            eVar.f(ROLLOUTVARIANT_DESCRIPTOR, rolloutAssignment.getRolloutVariant());
            eVar.f(PARAMETERKEY_DESCRIPTOR, rolloutAssignment.getParameterKey());
            eVar.f(PARAMETERVALUE_DESCRIPTOR, rolloutAssignment.getParameterValue());
            eVar.b(TEMPLATEVERSION_DESCRIPTOR, rolloutAssignment.getTemplateVersion());
        }
    }

    public static final class CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder implements d<CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant> {
        static final CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder INSTANCE = new CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder();
        private static final c ROLLOUTID_DESCRIPTOR = c.a("rolloutId");
        private static final c VARIANTID_DESCRIPTOR = c.a("variantId");

        private CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant, e eVar) throws IOException {
            eVar.f(ROLLOUTID_DESCRIPTOR, rolloutVariant.getRolloutId());
            eVar.f(VARIANTID_DESCRIPTOR, rolloutVariant.getVariantId());
        }
    }

    public static final class CrashlyticsReportSessionEventRolloutsStateEncoder implements d<CrashlyticsReport.Session.Event.RolloutsState> {
        static final CrashlyticsReportSessionEventRolloutsStateEncoder INSTANCE = new CrashlyticsReportSessionEventRolloutsStateEncoder();
        private static final c ASSIGNMENTS_DESCRIPTOR = c.a("assignments");

        private CrashlyticsReportSessionEventRolloutsStateEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.Event.RolloutsState rolloutsState, e eVar) throws IOException {
            eVar.f(ASSIGNMENTS_DESCRIPTOR, rolloutsState.getRolloutAssignments());
        }
    }

    public static final class CrashlyticsReportSessionOperatingSystemEncoder implements d<CrashlyticsReport.Session.OperatingSystem> {
        static final CrashlyticsReportSessionOperatingSystemEncoder INSTANCE = new CrashlyticsReportSessionOperatingSystemEncoder();
        private static final c PLATFORM_DESCRIPTOR = c.a("platform");
        private static final c VERSION_DESCRIPTOR = c.a("version");
        private static final c BUILDVERSION_DESCRIPTOR = c.a("buildVersion");
        private static final c JAILBROKEN_DESCRIPTOR = c.a("jailbroken");

        private CrashlyticsReportSessionOperatingSystemEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.OperatingSystem operatingSystem, e eVar) throws IOException {
            eVar.c(PLATFORM_DESCRIPTOR, operatingSystem.getPlatform());
            eVar.f(VERSION_DESCRIPTOR, operatingSystem.getVersion());
            eVar.f(BUILDVERSION_DESCRIPTOR, operatingSystem.getBuildVersion());
            eVar.a(JAILBROKEN_DESCRIPTOR, operatingSystem.isJailbroken());
        }
    }

    public static final class CrashlyticsReportSessionUserEncoder implements d<CrashlyticsReport.Session.User> {
        static final CrashlyticsReportSessionUserEncoder INSTANCE = new CrashlyticsReportSessionUserEncoder();
        private static final c IDENTIFIER_DESCRIPTOR = c.a("identifier");

        private CrashlyticsReportSessionUserEncoder() {
        }

        @Override // com.google.firebase.encoders.d
        public void encode(CrashlyticsReport.Session.User user, e eVar) throws IOException {
            eVar.f(IDENTIFIER_DESCRIPTOR, user.getIdentifier());
        }
    }

    private AutoCrashlyticsReportEncoder() {
    }

    @Override // h5.a
    public void configure(b<?> bVar) {
        CrashlyticsReportEncoder crashlyticsReportEncoder = CrashlyticsReportEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.class, crashlyticsReportEncoder);
        bVar.a(AutoValue_CrashlyticsReport.class, crashlyticsReportEncoder);
        CrashlyticsReportSessionEncoder crashlyticsReportSessionEncoder = CrashlyticsReportSessionEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.class, crashlyticsReportSessionEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session.class, crashlyticsReportSessionEncoder);
        CrashlyticsReportSessionApplicationEncoder crashlyticsReportSessionApplicationEncoder = CrashlyticsReportSessionApplicationEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Application.class, crashlyticsReportSessionApplicationEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Application.class, crashlyticsReportSessionApplicationEncoder);
        CrashlyticsReportSessionApplicationOrganizationEncoder crashlyticsReportSessionApplicationOrganizationEncoder = CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Application.Organization.class, crashlyticsReportSessionApplicationOrganizationEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Application_Organization.class, crashlyticsReportSessionApplicationOrganizationEncoder);
        CrashlyticsReportSessionUserEncoder crashlyticsReportSessionUserEncoder = CrashlyticsReportSessionUserEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.User.class, crashlyticsReportSessionUserEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_User.class, crashlyticsReportSessionUserEncoder);
        CrashlyticsReportSessionOperatingSystemEncoder crashlyticsReportSessionOperatingSystemEncoder = CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.OperatingSystem.class, crashlyticsReportSessionOperatingSystemEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_OperatingSystem.class, crashlyticsReportSessionOperatingSystemEncoder);
        CrashlyticsReportSessionDeviceEncoder crashlyticsReportSessionDeviceEncoder = CrashlyticsReportSessionDeviceEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Device.class, crashlyticsReportSessionDeviceEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Device.class, crashlyticsReportSessionDeviceEncoder);
        CrashlyticsReportSessionEventEncoder crashlyticsReportSessionEventEncoder = CrashlyticsReportSessionEventEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.class, crashlyticsReportSessionEventEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event.class, crashlyticsReportSessionEventEncoder);
        CrashlyticsReportSessionEventApplicationEncoder crashlyticsReportSessionEventApplicationEncoder = CrashlyticsReportSessionEventApplicationEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.Application.class, crashlyticsReportSessionEventApplicationEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_Application.class, crashlyticsReportSessionEventApplicationEncoder);
        CrashlyticsReportSessionEventApplicationExecutionEncoder crashlyticsReportSessionEventApplicationExecutionEncoder = CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.Application.Execution.class, crashlyticsReportSessionEventApplicationExecutionEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_Application_Execution.class, crashlyticsReportSessionEventApplicationExecutionEncoder);
        CrashlyticsReportSessionEventApplicationExecutionThreadEncoder crashlyticsReportSessionEventApplicationExecutionThreadEncoder = CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.Application.Execution.Thread.class, crashlyticsReportSessionEventApplicationExecutionThreadEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread.class, crashlyticsReportSessionEventApplicationExecutionThreadEncoder);
        CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder crashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder = CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.class, crashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame.class, crashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder);
        CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder crashlyticsReportSessionEventApplicationExecutionExceptionEncoder = CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.Application.Execution.Exception.class, crashlyticsReportSessionEventApplicationExecutionExceptionEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception.class, crashlyticsReportSessionEventApplicationExecutionExceptionEncoder);
        CrashlyticsReportApplicationExitInfoEncoder crashlyticsReportApplicationExitInfoEncoder = CrashlyticsReportApplicationExitInfoEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.ApplicationExitInfo.class, crashlyticsReportApplicationExitInfoEncoder);
        bVar.a(AutoValue_CrashlyticsReport_ApplicationExitInfo.class, crashlyticsReportApplicationExitInfoEncoder);
        CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder crashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder = CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.class, crashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder);
        bVar.a(AutoValue_CrashlyticsReport_ApplicationExitInfo_BuildIdMappingForArch.class, crashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder);
        CrashlyticsReportSessionEventApplicationExecutionSignalEncoder crashlyticsReportSessionEventApplicationExecutionSignalEncoder = CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.Application.Execution.Signal.class, crashlyticsReportSessionEventApplicationExecutionSignalEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal.class, crashlyticsReportSessionEventApplicationExecutionSignalEncoder);
        CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder crashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder = CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.class, crashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage.class, crashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder);
        CrashlyticsReportCustomAttributeEncoder crashlyticsReportCustomAttributeEncoder = CrashlyticsReportCustomAttributeEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.CustomAttribute.class, crashlyticsReportCustomAttributeEncoder);
        bVar.a(AutoValue_CrashlyticsReport_CustomAttribute.class, crashlyticsReportCustomAttributeEncoder);
        CrashlyticsReportSessionEventApplicationProcessDetailsEncoder crashlyticsReportSessionEventApplicationProcessDetailsEncoder = CrashlyticsReportSessionEventApplicationProcessDetailsEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.Application.ProcessDetails.class, crashlyticsReportSessionEventApplicationProcessDetailsEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails.class, crashlyticsReportSessionEventApplicationProcessDetailsEncoder);
        CrashlyticsReportSessionEventDeviceEncoder crashlyticsReportSessionEventDeviceEncoder = CrashlyticsReportSessionEventDeviceEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.Device.class, crashlyticsReportSessionEventDeviceEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_Device.class, crashlyticsReportSessionEventDeviceEncoder);
        CrashlyticsReportSessionEventLogEncoder crashlyticsReportSessionEventLogEncoder = CrashlyticsReportSessionEventLogEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.Log.class, crashlyticsReportSessionEventLogEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_Log.class, crashlyticsReportSessionEventLogEncoder);
        CrashlyticsReportSessionEventRolloutsStateEncoder crashlyticsReportSessionEventRolloutsStateEncoder = CrashlyticsReportSessionEventRolloutsStateEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.RolloutsState.class, crashlyticsReportSessionEventRolloutsStateEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_RolloutsState.class, crashlyticsReportSessionEventRolloutsStateEncoder);
        CrashlyticsReportSessionEventRolloutAssignmentEncoder crashlyticsReportSessionEventRolloutAssignmentEncoder = CrashlyticsReportSessionEventRolloutAssignmentEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.RolloutAssignment.class, crashlyticsReportSessionEventRolloutAssignmentEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment.class, crashlyticsReportSessionEventRolloutAssignmentEncoder);
        CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder crashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder = CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.class, crashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder);
        bVar.a(AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant.class, crashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder);
        CrashlyticsReportFilesPayloadEncoder crashlyticsReportFilesPayloadEncoder = CrashlyticsReportFilesPayloadEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.FilesPayload.class, crashlyticsReportFilesPayloadEncoder);
        bVar.a(AutoValue_CrashlyticsReport_FilesPayload.class, crashlyticsReportFilesPayloadEncoder);
        CrashlyticsReportFilesPayloadFileEncoder crashlyticsReportFilesPayloadFileEncoder = CrashlyticsReportFilesPayloadFileEncoder.INSTANCE;
        bVar.a(CrashlyticsReport.FilesPayload.File.class, crashlyticsReportFilesPayloadFileEncoder);
        bVar.a(AutoValue_CrashlyticsReport_FilesPayload_File.class, crashlyticsReportFilesPayloadFileEncoder);
    }
}

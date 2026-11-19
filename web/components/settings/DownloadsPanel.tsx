"use client";
import { premiumAllows } from "@/lib/premiumGate";
import {useSyncExternalStore} from 'react';
import { useAppPick } from '@/lib/store';
import {useTranslation} from '@/lib/i18n';
import {downloadEntries,downloadRevision,subscribeDownloads,pauseDownload,cancelDownload,resumeDownload,openDownloadedFile} from '@/lib/downloads';
export function DownloadsPanel(){const translateUi=useTranslation();const {auth,activeProfile,setToast}=useAppPick("auth", "activeProfile", "setToast");useSyncExternalStore(subscribeDownloads,downloadRevision,()=>0);const scope=`${auth?.userId??'local'}:${activeProfile?.id??'default'}`;
 const items=downloadEntries().filter(e=>e.scope===scope);
 const statuses={downloading:translateUi('Downloading'),paused:translateUi('Paused'),complete:translateUi('Complete'),failed:translateUi('Failed'),cancelled:translateUi('Cancelled'),handoff:translateUi('Sent to browser / VLC')};
 return <section className="settings-panel"><h2>{translateUi('Downloads')}</h2><p>{translateUi('Managed downloads show progress and can be paused or resumed. Browser and VLC downloads are managed by those apps.')}</p>{!items.length&&<p>{translateUi('No downloads yet. Choose Download from Sources.')}</p>}
 {items.map(e=><article className="download-entry" key={e.id}><strong>{e.title}</strong><p>{statuses[e.status]}{e.received>0&&` · ${(e.received/1048576).toFixed(1)} MB`}</p>{e.total>0&&<progress max={e.total} value={e.received}/>} {e.error&&<p role="alert">{translateUi(e.error)}</p>}<div className="actions">{e.status==='downloading'?<button className="secondary" onClick={()=>pauseDownload(e.id)}>{translateUi('Pause')}</button>:['paused','failed'].includes(e.status)&&<button className="secondary" onClick={()=>{ if (premiumAllows("download")) void resumeDownload(e.id); }}>{translateUi('Resume')}</button>}{['downloading','paused','failed'].includes(e.status)&&<button className="secondary" onClick={()=>cancelDownload(e.id)}>{translateUi('Cancel')}</button>}{e.status==='complete'&&<button className="secondary" onClick={()=>void openDownloadedFile(e.id).catch(error=>setToast(error.message))}>{translateUi('Open file')}</button>}</div></article>)}</section>;
}

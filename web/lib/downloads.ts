"use client";
import { loadStored, saveStored } from './storage';
import { assertTelegramSourceAvailable } from './config';
type FileHandle = {createWritable(options?:{keepExistingData?:boolean}):Promise<{write(data:Uint8Array):Promise<void>;seek(n:number):Promise<void>;truncate(n:number):Promise<void>;close():Promise<void>}>;getFile():Promise<File>;requestPermission(options:{mode:string}):Promise<string>};
export type DownloadEntry={id:string;scope:string;title:string;url:string;status:'downloading'|'paused'|'complete'|'failed'|'cancelled'|'handoff';received:number;total:number;error?:string;updatedAt:number};
const KEY='arvio.web.downloads.v1';const listeners=new Set<()=>void>();const running=new Map<string,AbortController>();let revision=0;
export const subscribeDownloads=(fn:()=>void)=>{listeners.add(fn);return()=>{listeners.delete(fn);};};
export const downloadRevision=()=>revision;
export function downloadEntries(){return loadStored<DownloadEntry[]>(KEY,[]).map(e=>({...e,status:e.status==='downloading'&&!running.has(e.id)?'paused' as const:e.status}));}
function save(entry:DownloadEntry){saveStored(KEY,[entry,...loadStored<DownloadEntry[]>(KEY,[]).filter(e=>e.id!==entry.id)].slice(0,100));revision++;listeners.forEach(fn=>fn());}
async function db(){return new Promise<IDBDatabase>((resolve,reject)=>{const r=indexedDB.open('arvio-download-files',1);r.onupgradeneeded=()=>r.result.createObjectStore('files');r.onsuccess=()=>resolve(r.result);r.onerror=()=>reject(r.error);});}
async function fileHandle(id:string,value?:FileHandle){const database=await db();try{return await new Promise<FileHandle|undefined>((resolve,reject)=>{const t=database.transaction('files',value?'readwrite':'readonly');const r=value?t.objectStore('files').put(value,id):t.objectStore('files').get(id);t.oncomplete=()=>resolve(value??r.result);t.onerror=()=>reject(t.error);t.onabort=()=>reject(t.error);});}finally{database.close();}}
export async function chooseDownloadFile(title:string,extension:'mp4'|'mkv'='mkv'):Promise<FileHandle|null>{
 const picker=(window as unknown as {showSaveFilePicker?:(options:unknown)=>Promise<FileHandle>}).showSaveFilePicker;
 if(!picker)return null;
 return picker({suggestedName:title.replace(/[<>:"/\\|?*]/g,'_')+'.'+extension});
}
export async function startManagedDownload(title:string,url:string,scope:string,handle:FileHandle){assertTelegramSourceAvailable({url});const entry:DownloadEntry={id:crypto.randomUUID(),scope,title,url,status:'paused',received:0,total:0,updatedAt:Date.now()};await fileHandle(entry.id,handle);save(entry);void resumeDownload(entry.id);}
export function recordDownloadHandoff(title:string,scope:string){save({id:crypto.randomUUID(),scope,title,url:'',status:'handoff',received:0,total:0,updatedAt:Date.now()});}
export function pauseDownload(id:string){running.get(id)?.abort();}
export function cancelDownload(id:string){running.get(id)?.abort();const entry=downloadEntries().find(e=>e.id===id);if(entry)save({...entry,status:'cancelled',updatedAt:Date.now()});}
export async function resumeDownload(id:string){
 if(running.has(id))return;
 const entry=downloadEntries().find(e=>e.id===id);if(!entry||entry.status==='handoff')return;
 const controller=new AbortController();running.set(id,controller);let writer:Awaited<ReturnType<FileHandle['createWritable']>>|undefined;
 try{
  assertTelegramSourceAvailable(entry);
  const handle=await fileHandle(id);if(!handle)throw new Error('Choose this source again to select a download file.');
  if(await handle.requestPermission({mode:'readwrite'})!=='granted')throw new Error('File permission is required to resume this download.');
  entry.received=Math.min(entry.received,(await handle.getFile()).size);
  controller.signal.throwIfAborted();
  entry.status='downloading';entry.error=undefined;save(entry);
  const response=await fetch(entry.url,{signal:controller.signal,headers:entry.received?{Range:`bytes=${entry.received}-`}:{}});
  if(!response.ok||!response.body)throw new Error(`Download failed (${response.status}). Retry or select a fresh source.`);
  if(response.status===206){const start=Number(response.headers.get('Content-Range')?.match(/^bytes (\d+)-/)?.[1]);if(start!==entry.received)throw new Error('The source returned an invalid resume range.');}
  else entry.received=0;
  const remaining=Number(response.headers.get('Content-Length')||0);
  entry.total=Number(response.headers.get('Content-Range')?.split('/')[1])||(remaining>0?entry.received+remaining:0);
  writer=await handle.createWritable({keepExistingData:true});if(!entry.received)await writer.truncate(0);await writer.seek(entry.received);
  const reader=response.body.getReader();let last=0;
  while(true){const {done,value}=await reader.read();if(done)break;await writer.write(value);entry.received+=value.byteLength;if(Date.now()-last>500){save({...entry});last=Date.now();}}
  if(entry.total&&entry.received!==entry.total)throw new Error('The download ended early. Resume to continue.');
  await writer.truncate(entry.received);await writer.close();writer=undefined;entry.status='complete';
 }catch(error){entry.status=controller.signal.aborted?'paused':'failed';entry.error=controller.signal.aborted?undefined:error instanceof Error?error.message:'Download failed';}
 finally{await writer?.close().catch(()=>undefined);running.delete(id);if(downloadEntries().find(e=>e.id===id)?.status==='cancelled')entry.status='cancelled';entry.updatedAt=Date.now();save(entry);}
}
export async function openDownloadedFile(id:string){const handle=await fileHandle(id);if(!handle)throw new Error('The download file is unavailable.');const file=await handle.getFile();const url=URL.createObjectURL(file);const link=document.createElement('a');link.href=url;link.target='_blank';link.rel='noopener';link.click();setTimeout(()=>URL.revokeObjectURL(url),60_000);}

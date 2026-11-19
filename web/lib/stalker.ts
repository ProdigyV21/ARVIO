import { jsonRequest, proxiedUrl } from './http';
import type { IptvChannel, IptvNowNext, IptvProgram } from './types';
type Portal = { base: string; mac: string; token: string; expires: number };
const sessions = new Map<string, Promise<Portal>>();
function headers(p: Portal) { return { Authorization: `Bearer ${p.token}`, Cookie: `mac=${encodeURIComponent(p.mac)}; stb_lang=en; timezone=UTC`, 'User-Agent': 'Mozilla/5.0 (QtEmbedded; U; Linux; C) AppleWebKit/533.3 MAG200 stbapp ver: 2 rev: 250 Safari/533.3', Referer: `${p.base}/c/` }; }
async function request(p: Portal, params: Record<string,string>) {
  const url=new URL(`${p.base}/server/load.php`);
  for(const [k,v] of Object.entries({...params,JsHttpRequest:'1-xml'}))url.searchParams.set(k,v);
  const data=await jsonRequest<{js?: any}>(proxiedUrl(url.toString(),headers(p)),{cache:'no-store'});
  if(!data || data.js==null)throw new Error('The IPTV portal returned an invalid response.');
  return data.js;
}
async function connect(url:string,mac:string):Promise<Portal>{
  const u=new URL(url);if(!/^https?:$/.test(u.protocol)||!/^([0-9a-f]{2}:){5}[0-9a-f]{2}$/i.test(mac))throw new Error('Enter a valid portal URL and MAC address.');
  const input=u.origin+u.pathname.replace(/\/(?:c\/?|server\/load\.php|portal\.php)$/,'').replace(/\/$/,'');
  const candidates=[...new Set([input,`${u.origin}/stalker_portal`,`${u.origin}/portal`,u.origin])];
  for(const base of candidates){
    const p={base,mac,token:'',expires:Date.now()+10*60_000};
    try {const data=await request(p,{type:'stb',action:'handshake',token:''});
      if(!data.token)continue;p.token=String(data.token);
      await request(p,{type:'stb',action:'get_profile'});
      return p;
    }catch { /* A portal can use a different standard base path. */ }
  }
  throw new Error('Could not connect to the IPTV portal. Check the URL, MAC address and provider access.');
}
async function session(url:string,mac:string){
  const key=JSON.stringify([url,mac]);let pending=sessions.get(key);
  if(pending){const p=await pending;if(p.expires>Date.now())return p;sessions.delete(key);}
  pending=connect(url,mac);sessions.set(key,pending);
  try{return await pending;}catch(e){sessions.delete(key);throw e;}
}
/**
 * Channels of one portal. Ids are Android's `stalker:<portalId>:<channel>` so
 * favourites, recents and channel order match the TV; the id older ARVIO Web
 * builds used (`stalker:<encoded url>:<channel>`) stays as a sync alias.
 */
export async function loadStalkerChannels(url:string,mac:string,portalId='stalker1'):Promise<IptvChannel[]> {
  const p=await session(url,mac);const genres=await request(p,{type:'itv',action:'get_genres'});
  // Same names as StalkerApi.getChannels: a genre without a title is "Unknown", a channel without a genre "Uncategorized".
  const names=new Map<string,string>((Array.isArray(genres)?genres:[]).filter((g:any)=>g?.id!=null).map((g:any)=>[String(g.id),g.title==null?'Unknown':String(g.title)]));
  const channels:IptvChannel[]=[];const seen=new Set<string>();
  // Older builds keyed ids by the address as typed, often with a trailing slash.
  const legacyBases=[...new Set([url,url.replace(/\/+$/,''),`${url.replace(/\/+$/,'')}/`])].map(base=>`stalker:${encodeURIComponent(base)}:`);
  for(let page=1;;page++){
    const response=await request(p,{type:'itv',action:'get_all_channels',p:String(page)});
    const rows=Array.isArray(response)?response:response.data;
    if(!Array.isArray(rows)||!rows.length)break;
    let added=0;
    for(const row of rows){
      if(row.id==null||!row.cmd||seen.has(String(row.id)))continue;seen.add(String(row.id));added++;
      const cmd=String(row.cmd);const direct=String(row.use_http_tmp_link)==='0'&&String(row.wowza_tmp_link??0)==='0'&&String(row.flussonic_tmp_link??0)==='0';
      const stream=cmd.replace(/^ffmpeg\s+/i,'').trim();
      const genre=row.tv_genre_id==null?undefined:names.get(String(row.tv_genre_id));
      channels.push({id:`stalker:${portalId}:${row.id}`,syncAliases:legacyBases.map(base=>`${base}${row.id}`),name:String(row.name||row.id),group:genre??'Uncategorized',logo:row.logo||undefined,streamUrl:stream,number:String(row.number||''),
        stalker:{portal:url,mac,cmd,direct:direct&&/^https?:\/\//i.test(stream)}});
    }
    if(!added||!response.total_items||channels.length>=Number(response.total_items))break;
  }
  return channels;
}
/**
 * A playable URL for a Stalker channel (create_link for temporary links).
 * `fresh` starts a new portal session first: the player asks for that when a
 * link stopped working, which is usually an expired play token or session.
 */
export async function resolveStalkerChannel(channel:IptvChannel,options:{fresh?:boolean}={}):Promise<IptvChannel>{
  const config=channel.stalker;if(!config)return channel;
  if(options.fresh)sessions.delete(JSON.stringify([config.portal,config.mac]));
  const p=await session(config.portal,config.mac);
  let url=channel.streamUrl;
  if(!config.direct){const result=await request(p,{type:'itv',action:'create_link',cmd:config.cmd,series:'0',forced_storage:'undefined',disable_ad:'0',download:'0'});url=String(result.cmd||'').replace(/^ffmpeg\s+/i,'').trim();}
  if(!/^https?:\/\//i.test(url))throw new Error('The IPTV portal did not return a browser-compatible stream URL.');
  // Portal cookies authenticate API requests; never forward them to an unrelated media host.
  const sameOrigin=new URL(url).origin===new URL(p.base).origin;
  return {...channel,streamUrl:url,requestHeaders:sameOrigin?headers(p):{'User-Agent':headers(p)['User-Agent']}};
}

/** One guide request per portal, shared by the visible channels. */
export async function loadStalkerGuide(channels:IptvChannel[]):Promise<Record<string,IptvNowNext>> {
  const result:Record<string,IptvNowNext>={};
  const groups=new Map<string,IptvChannel[]>();
  for(const ch of channels){if(!ch.stalker)continue;const key=JSON.stringify([ch.stalker.portal,ch.stalker.mac]);groups.set(key,[...(groups.get(key)||[]),ch]);}
  await Promise.all([...groups.values()].map(async rows=>{
    try {
      const config=rows[0].stalker!;const p=await session(config.portal,config.mac);
      const data=await request(p,{type:'itv',action:'get_epg_info',period:'6'});
      const raw=data.data??data;const now=Date.now();
      const guide:Record<string,any[]>=Array.isArray(raw)?{}:raw;
      if(Array.isArray(raw))for(const e of raw){const id=String(e.ch_id??e.channel_id??e.itv_id??'');(guide[id]??=[]).push(e);}
      for(const ch of rows){
        const entries=guide[ch.id.split(':').at(-1)!];if(!Array.isArray(entries))continue;
        const programs:IptvProgram[]=entries.map((e:any)=>({title:String(e.name||e.title||''),description:e.descr||e.description,
          startUtcMillis:Number(e.start_timestamp??e.start)*1000,endUtcMillis:Number(e.stop_timestamp??e.end_timestamp??e.end)*1000}))
          .filter((e:IptvProgram)=>e.title&&Number.isFinite(e.startUtcMillis)&&e.endUtcMillis>e.startUtcMillis).sort((a:IptvProgram,b:IptvProgram)=>a.startUtcMillis-b.startUtcMillis);
        const upcoming=programs.filter(e=>e.startUtcMillis>now);
        result[ch.id]={now:programs.find(e=>e.startUtcMillis<=now&&e.endUtcMillis>now),next:upcoming[0],later:upcoming[1],upcoming,recent:programs.filter(e=>e.endUtcMillis<=now)};
      }
    } catch { /* Guide availability must not prevent live playback. */ }
  }));return result;
}

import React, { useState } from 'react';
import { createRoot } from 'react-dom/client';
import { IptvGroupSettings } from '../../components/settings/IptvGroupSettings';
import type { AppSettings, IptvChannel } from '../../lib/types';

function Fixture() {
  const [settings, setSettings] = useState(() => JSON.parse(localStorage.getItem('groups') || 'null') || {
    iptvPlaylists: [{id:'p',name:'Main playlist'},{id:'other',name:'Other playlist'}], groupOrder:[], hiddenGroupIds:[],
    favoriteChannelIds:[], favoriteGroupIds:[]
  });
  const channels = ['News','Sports','Movies','A very long category name that should fit on a small phone']
    .map((group,index)=>({id:`p:${index}`,name:`Channel ${index}`,group,streamUrl:''}));
  channels.push({id:'other:1',name:'Other 1',group:'News',streamUrl:''});
  return <main className="st-shell" style={{display:'block',maxWidth:800,margin:'20px auto',padding:12}}>
    <section className="st-group">
      <IptvGroupSettings channels={channels as IptvChannel[]} settings={settings as AppSettings} updateSettings={patch=>setSettings((previous: AppSettings)=>{
        const next={...previous,...patch}; localStorage.setItem('groups',JSON.stringify(next)); return next;
      })}/>
    </section>
  </main>;
}
createRoot(document.getElementById('root')!).render(<Fixture/>);

const test = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const code = fs.readFileSync(path.join(__dirname, '../assets/premium-pricing.js'), 'utf8');
function run({cached, country, fail=false, blocked=false, language='pt-BR'}={}) {
  const requests=[];
  const context = {module:{exports:{}}, AbortController, setTimeout, clearTimeout,
    navigator:{language}, Intl, sessionStorage:{getItem(){if(blocked)throw Error('blocked');return cached ?? null;},setItem(){if(blocked)throw Error('blocked');}},
    fetch:async(url)=>{requests.push(url);if(fail)throw Error('offline');return {ok:true,json:async()=>({country})};}};
  vm.runInNewContext(code, context);
  return {api:context.module.exports, requests};
}
test('Public offers use the same country eligibility as the hosted app',()=>{
  const web=fs.readFileSync(path.join(__dirname,'../../web/lib/regionalPricing.ts'),'utf8');
  const set=web.match(/REGIONAL_COUNTRIES = new Set\(\[([\s\S]*?)\]\)/)[1];
  const allowed=[...set.matchAll(/"([A-Z]{2})"/g)].map(x=>x[1]);
  const {api}=run();
  for(let a=65;a<=90;a++)for(let b=65;b<=90;b++){
    const value=String.fromCharCode(a,b);assert.equal(api.regionalCountry(value),allowed.includes(value),value);
  }
  for(const value of [null,undefined,'BR?coupon=x','br','US',''])assert.equal(api.regionalCountry(value),false);
});
test('Unavailable geolocation never guesses a discount from browser language or timezone',async()=>{
  const {api,requests}=run({fail:true,language:'pt-BR'});
  const country=await api.loadCountry();
  assert.equal(country,null);assert.equal(api.regionalCountry(country),false);assert.equal(requests.length,1);
});
test('Valid cached ISO country avoids a new request; invalid cache cannot select an offer',async()=>{
  const cached=run({cached:'BR'});assert.equal(await cached.api.loadCountry(),'BR');assert.equal(cached.requests.length,0);
  const malformed=run({cached:'not-a-country',country:'US'});assert.equal(await malformed.api.loadCountry(),'US');assert.equal(malformed.requests.length,1);
});
test('Storage failure does not prevent country lookup and concurrent readers share one request',async()=>{
  const {api,requests}=run({blocked:true,country:'IN'});
  assert.deepEqual(await Promise.all([api.loadCountry(),api.loadCountry()]),['IN','IN']);assert.equal(requests.length,1);
});
test('Language changes preserve exact amounts and distinguish the two savings comparisons',()=>{
  const elements=new Map();
  const document={querySelectorAll(selector){if(!elements.has(selector))elements.set(selector,[{}]);return elements.get(selector);}};
  const copy={yearlyBadge:'Best value',regionalBadge:'Regional',yearlySaving:'44% versus $35.88',regionalSaving:'50% off $20; 72% versus $35.88',fromPrice:'$2.99 / $20',regionalFromPrice:'$2.99 / $10'};
  const {api}=run();api.apply(copy,true,document);
  assert.equal(elements.get('[data-yearly-price]')[0].textContent,'$10');assert.equal(elements.get('[data-yearly-was]')[0].hidden,false);
  assert.equal(elements.get('[data-yearly-saving]')[0].textContent,copy.regionalSaving);
  api.apply(copy,false,document);
  assert.equal(elements.get('[data-yearly-price]')[0].textContent,'$20');assert.equal(elements.get('[data-yearly-was]')[0].hidden,true);
  assert.equal(elements.get('[data-plan-price-summary]')[0].textContent,copy.fromPrice);
});

import { getStore } from '@netlify/blobs';
import metadata from './_sports-metadata.js';

// Use the modern function context. connectLambda's legacy bridge drops the
// uncached endpoint required for strong consistency and conditional leases.
const handler = metadata.createHandler(() => ({
  store: getStore({ name: 'sports-metadata', consistency: 'strong' }),
  apiKey: process.env.SPORTSDB_API_KEY,
}));

export default async request => {
  const result = await handler({ httpMethod: request.method });
  return new Response(result.statusCode === 204 ? null : result.body, {status: result.statusCode, headers: result.headers});
};

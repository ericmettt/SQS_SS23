import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  duration: '1m',
  vus: 25,
  thresholds: {
    http_req_failed: ['rate<0.10'], // http errors should be less than 1%
    http_req_duration: ['p(95)<500'], // 95 percent of response times must be below 500ms
  },
};

export default function () {
  // Request webpage 1
  
  const res = http.get('https://fruityvice.com/api/fruit/apple');
   // Request webpage 2
  const res2 = http.get('https://example.com/https://fruityvice.com/api/fruit/melon');

  // Request webpage 3
  const res3 = http.get('https://fruityvice.com/api/fruit/pear');
  sleep(1);
}

import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    duration: '30s',
    vus: 5,
    thresholds: {
        http_req_duration: ['p(95)<1000', 'avg<777'], // 95% of requests should be below 500ms, and average response time should be below 200ms
        checks: ['rate>0.75'], // Success rate of checks should be above 95%
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

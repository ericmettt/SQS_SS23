import { check } from "k6";
import http from "k6/http";
export const options = {
    // insecureSkipTLSVerify: true
};

//with k6 run --vus 50 --duration 30s C:\Users\Eric\IdeaProjects\Fruit\Java\src\test\java\fruitnew\performancetest\k6script.js works fine
//By 100+ user API ist bottleneck
export default function() {
    let res = http.get("http://localhost:8080/");

    check(res, {
        "is status 200": (r) => r.status === 200
    });
};
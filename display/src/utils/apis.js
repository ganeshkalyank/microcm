import axios from "axios";

const entryapi = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json",
    },
});

export { entryapi };

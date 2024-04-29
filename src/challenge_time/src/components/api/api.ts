import axios from "axios";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;

const api = axios.create({
    baseURL: 'http://localhost:8080'
})

api.interceptors.request.use(
    (config) => {
        const accessToken = localStorage.getItem("accessToken");
        if (accessToken) {
            config.headers.Authorization = `Bearer ${accessToken}`;
        } return Promise.reject(error);
    }
);

api.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        if (error.response && error.response.status === 401) {
            // Unauthorized error - redirect to login page
            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);

export default api;
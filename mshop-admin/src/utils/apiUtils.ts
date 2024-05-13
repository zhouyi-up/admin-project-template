import axios from "axios";
import {ElMessage} from "element-plus";
import router from "../router"

const API_HOST = import.meta.env.VITE_API_HOST || "";

const apiUtils = axios.create({
    baseURL: API_HOST,
    headers: {"r-source": "mshop-admin"}
})

apiUtils.interceptors.request.use(function (config){
    const token = localStorage.getItem(import.meta.env.VITE_TOKEN_KEY)
    config.headers.set("token", token)
    return config
}, function (error){
    console.error("The request fail",error)
})

apiUtils.interceptors.response.use(function (response){
    return new Promise((resolve, reject) => {
        const data = response.data
        const code = data.code
        if (code != 0){
            ElMessage({
                message: data.message,
                type: "error",
                plain: true
            })
            if (code == 403){
                localStorage.removeItem(import.meta.env.VITE_TOKEN_KEY)
                router.push({name: "login"})
            }
            return reject(data.message)
        }
        return resolve(data.data);
    })
}, function (error) {
    ElMessage.error("网络异常：服务异常")
    console.error("The response fail: ", error.message)
})

const get = (url:string, params:any) => {
    return new Promise((resolve, reject) => {
        apiUtils.get(url, {
            params: params
        }).then(res => {
            return resolve(res)
        }).catch(err => reject(err))
    })
}

const post = (url:string, params: any) => {
    return new Promise((resolve, reject) => {
        apiUtils.post(url, params)
            .then(res => resolve(res))
            .catch(err => reject(err))
    })
}

export { get, post }
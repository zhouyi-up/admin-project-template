import {get, post} from "@/utils/apiUtils";

export const useLogin = () => {
    return {
        code: () => {
            return get("/login/code", null)
        },
        login: (data:any) => {
            return post("/login", data)
        }
    }
}
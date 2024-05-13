import {get, post} from "@/utils/apiUtils";

export const useRoleApi = () => {
    return {
        list: (data:any) => {
            return get("/role/list", data)
        },
        save: (data:any) => {
            return post("/role/save", data)
        },
        del: (data:any) => {
            return get("/role/del", data)
        },
        auth: (data:any) => {
            return get("/role/auth", data)
        }
    }
}
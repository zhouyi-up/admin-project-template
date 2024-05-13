import {get, post} from "@/utils/apiUtils";

export const usePermissionApi = () =>{
    return {
        list: () =>{
            return get("/permission/list", null)
        },
        save: (data:any) => {
            return post("/permission/save", data)
        },
        del: (data:any) => {
            return get("/permission/del", data)
        },
        auth: () => {
            return get("/permission/auth", null)
        }
    }
}
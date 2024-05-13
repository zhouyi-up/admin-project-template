import {get, post} from "@/utils/apiUtils";

export const useUserApi = () => {
    return {
        list: (data:any) => {
            return get("auth/adminUser/list", data)
        },
        save: (data:any) =>{
            return post("auth/adminUser/save", data)
        }
    }
}
export const tree = (data:Array<any>) =>{
    if (data.length <= 0){
        return []
    }
    const root = searchItem(data, 0)
    getChildItem(root, data)
    return root
}

const getChildItem = (root: Array<any>, data:Array<any>) => {
    root.forEach(item => {
        const child = searchItem(data, item.id)
        if (child.length > 0){
            getChildItem(child, data)
        }
        item.children = child
    })
}

const searchItem = (data:Array<any>, id:any) =>{
    return data.filter((item:any) => item.parentId == id)
}
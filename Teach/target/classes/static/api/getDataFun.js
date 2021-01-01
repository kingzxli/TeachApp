export function getTableDataList(that,url,params,callback){
    that.$http.get(url,{
        params,
        headers:{
            session:""
        },
        _timeout:3000,
    }).then((res) => {
        if(res.status ===200){
            if(res.body.status === 200){
                callback(res.body);
            }else {
                that.$Message.error(res.body.statusText);
            }
        }
        else if (res.status ===204){
            that.$Message.error(res.data.statusText);
            setTimeout(()=>{
                that.$router.push({
                    path:"/"
                })
            },2000)
        }
        else {
            that.$Message.error(res.data.statusText);
            that.btnStatus = false;
        }
        that.loading=false;
    }, (res) => {
        if(res.body.code===401){
            that.$router.push({
                path:"/"
            })
        }else if(res.status===408){
            that.$Message.error("请求失败！");
        }else{
            that.$Message.error("网络不给力，请重试！");
        }
        that.loading=false;
    });
}

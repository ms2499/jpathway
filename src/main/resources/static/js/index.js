var page = 1

$('.funBtn').on('click', (event) => {
    let action = $(event.target).attr('id')
    let func = 1 //查詢
    let key = $('#WR-KEY').text()
    if (action == 'search')
    {
        key = "              "
        page = 1
    }
    if (action == 'pageup') 
    {
        if (page == 1)
            return
        func = 2 //上一頁
        key = $('#WR-FIRST-KEY').text()
        page--
    }
    else if (action == 'pagedn') 
    {
        func = 3 //下一頁
        page++
    }

    console.log(key)
    console.log($('#inputcomid').val())

    axios({
        method: 'post',
        url: 'http://172.16.82.2:9222/futures/pathsend?svrName=FUS101&funCode='+func,
        data: {
            wsExchId : $('#inputbroker').val(),
            wsCommodityId : $('#inputcomid').val(),
            wsFeeKind : $('#inputfee').val(),
            wsUpFeeKind : $('#inputupfee').val(),
            wsDnFeeKind : $('#inputdnfee').val(),
            wsStockNo : $('#inputstock').val(),
            wsProdKing : $('#inputprod').val(),
            wsKey : key
        }
    })
    .then((res) => {
        $('tbody').empty()

        $('#WR-KEY').text(res.data.wr_key)
        $('#WR-FIRST-KEY').text(res.data.wr_first_key)
        
        for(let i = 0; i < res.data.rows; i++) {
            let tr = $('tbody').append('<tr></tr>')

            $(tr).append(
                '<tr>'+
                    '<th scope="row">' + (i+1) + '</th>'+
                    '<td>'+res.data.wr_com_id[i]+'</td>'+
                    '<td>'+res.data.wr_com_name[i]+'</td>'+
                    '<td>'+res.data.wr_min_price[i]+'</td>'+
                    '<td>'+res.data.wr_up_dn[i]+'</td>'+
                    '<td>'+res.data.wr_value[i]+'</td>'+
                    '<td>'+res.data.wr_rate[i]+'</td>'+
                    '<td>'+res.data.wr_stock[i]+'</td>'+
                    '<td>'+res.data.wr_fee[i]+'</td>'+
                    '<td>'+res.data.wr_up_fee[i]+'</td>'+
                    '<td>'+res.data.wr_dn_fee[i]+'</td>'+
                    '<td>'+res.data.wr_range[i]+'</td>'+
                '</tr>'
            )
        }

        $('#page').children('p').text('第 '+page+' 頁')
        $('#page').show()
    })
    .catch((err) => {
        console.log(err)
    })
})
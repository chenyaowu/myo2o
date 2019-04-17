$(function() {
    var shopId = 1;
    var productName = '';
    getList();
    getProductSellDailyList();
    function getList() {
        var listUrl = '/myo2o/shop/listuserproductmapsbyshop?pageIndex=1&pageSize=9999&shopId=' + shopId + '&productName=' + productName;
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var userProductMapList = data.userProductMapList;
                var tempHtml = '';
                userProductMapList.map(function (item, index) {
                    tempHtml += ''
                         +      '<div class="row row-productbuycheck">'
                         +          '<div class="col-10">'+ item.product.productName +'</div>'
                         +          '<div class="col-40 productbuycheck-time">'+ new Date(item.createTime).Format("yyyy-MM-dd hh:mm:ss") +'</div>'
                         +          '<div class="col-20">'+ item.user.name +'</div>'
                         +          '<div class="col-10">'+ item.point +'</div>'
                         +          '<div class="col-20">'+ item.operator.name +'</div>'
                         +      '</div>';
                });

                $('.productbuycheck-wrap').html(tempHtml);
            }
        });
    }

    $('#search').on('input', function (e) {
        productName = e.target.value;
        $('.productbuycheck-wrap').empty();
        getList();
    });

    /**
     * 获取7天的销量
     */
    function getProductSellDailyList(){
        // 获取该店铺商品7天销量URL
        var listProductSellDailyUrl = '/myo2o/shop/listproductselldailyinfobyshop';

        $.getJSON(listProductSellDailyUrl, function(data){
            if(data.success){
                var myChar = echarts.init(document.getElementById('chart'));

                //生成动态的Echart信息的部分
                var option = generateStaticEchartPart();
                //遍历销量统计列表，动态设定echarts的值
                option.legend.data = data.legendData;
                option.xAxis = data.xAxis;
                option.series = data.series;
                myChar.setOption(option);
            }
        });
    }

    /**
     * 生成静态的Echart信息部分
     */
    function generateStaticEchartPart(){
        var option={
            // 提示框
            tooltip:{
                trigger: 'axis',
                axisPointer :{ //坐标轴指示器，坐标轴触发有效
                    type: 'shadow' //鼠标移动到轴的时候，显示阴影
                }
            },
            // 图例，每个图表最多仅有一个图例
            legend: {},
            // 直角坐标系内绘图网格
            grid:{
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true

            },
            // 直角坐标中横轴数组，数组中每一项代表一条横轴坐标
            xAxis:[{}],
            // 直角坐标系中纵轴数据，数组中每一项代表一条纵轴坐标轴
            yAxis:[{type: 'value'}]
        };
        return option;



    }

    getList();
});
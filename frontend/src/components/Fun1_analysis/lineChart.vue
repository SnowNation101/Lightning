<template>
    <div id="lineChart">
        <div id="text">
            <el-select v-model="type" placeholder="请选择" style="float:right;right:5px;">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
            </el-select>
            <p id="text1" style="margin: 0;padding: 0;color:black">{{ currentYear }}年每月发电量统计</p>

            <p id="text2" style="margin: 0;padding: 0;color:black">Statistical chart of monthly power generation</p>
        </div>
        <div id="chart">
            <div id="consumePerMonth" v-loading='$store.state.load1' style="height:100%;width:100%;"></div>
        </div>
    </div>
</template>

<script>
import * as  echarts from 'echarts'
export default {
    name: 'lineChart',
    data() {
        return {
            currentYear: (new Date()).getFullYear(),
            options: [{
                value: 'bar',
                label: '柱状图'
            }, {
                value: 'line',
                label: '折线图'
            }],
            type:'折线图',
        }
    },
    methods: {
        draw() {
            var myChart = echarts.init(document.getElementById('consumePerMonth'));
            this.$store.state.option_1_line && myChart.setOption(this.$store.state.option_1_line);
        },
        refreshChart() {
            document.getElementById('consumePerMonth').removeAttribute('_echarts_instance_');
            this.draw();
        },
    },
    mounted() {
        var base = new Date();
        var date = [];
        var year = base.getFullYear() - 1;
        var month = Number(base.getMonth()) + 1;
        for (let i = 0; i < 12; i++) {
            month = Number(month) + 1;
            if (month == 13) {
                month = Number(1);
                year = Number(year) + 1;
            }
            month = (month > 9) ? month : ("0" + month);
            date.push([year, month].join('/'));
            //data.push(Math.round((Math.random()) * 20));
        }
        //this.$store.state.option_1_line.series[0].data = data;
        this.$store.state.option_1_line.xAxis.data = date;
        this.draw();
    },
    watch : {
        type :{
            handler(newVal){
                this.$store.state.option_1_line.series[0].type=newVal;
                this.refreshChart();
            }
        }
    }
}
</script>

<style>
#lineChart {
    width: 100%;
    height: 100%;
}

#text {
    width: 100%;
    height: 20%;
}

#text1 {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 20px;
}

#text2 {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 10px;
}

#chart {
    width: 100%;
    height: 80%;
}
</style>
<template>
    <div id="barChart">
        <div id="text">
            <el-select v-model="type" placeholder="请选择" style="float:right;right:5px;">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
            </el-select>
            <p id="text1" style="margin: 0;padding: 0;color:black">近30日每日发电量统计</p>
            <p id="text2" style="margin: 0;padding: 0;color:black">Statistical chart of dayly power generation</p>
        </div>
        <div id="chart">
            <div id="consumePerDay" v-loading='$store.state.load1' style="height:100%;width:100%;"></div>
        </div>
    </div>
</template>

<script>
import * as  echarts from 'echarts'
export default {
    name: 'barChart',
    data() {
        return {
            options: [{
                value: 'bar',
                label: '柱状图'
            }, {
                value: 'line',
                label: '折线图'
            }],
            type: '柱状图',
        }
    },
    methods: {
        draw() {
            var myChart = echarts.init(document.getElementById('consumePerDay'));
            this.$store.state.option_1_bar && myChart.setOption(this.$store.state.option_1_bar);
        },
        refreshChart() {
            document.getElementById('consumePerDay').removeAttribute('_echarts_instance_');
            this.draw();
        },
    },
    mounted() {
        //var data = [];
        var date = [];
        var temp = [];
        var daysPerMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        var now = new Date();
        var month = now.getMonth();
        var day = now.getDate();
        for (let i = 0; i < 30; i++) {
            if (Number(day) == 1) {
                if (Number(month) == 0) {
                    month = 11;
                    day = 31;
                }
                else {
                    month = Number(month) - 1;
                    day = daysPerMonth[Number(now.getMonth()) - 1];
                }
            }
            else {
                day = Number(day) - 1;
            }
            temp.push([month+1, day].join('/'));
        }
        for (let i = 0; i < 30; i++) {
            let v = temp.pop();
            date.push(v);
            //data.push(Math.round((Math.random()) * 20));
        }
        //this.$store.state.option_1_bar.series[0].data = data;
        this.$store.state.option_1_bar.xAxis.data = date;
        this.draw();
    },
    watch: {
        type: {
            handler(newVal) {
                this.$store.state.option_1_bar.series[0].type = newVal;
                this.refreshChart();
            }
        }
    }
}
</script>

<style>
#barChart {
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
    height: 70%;
}
</style>
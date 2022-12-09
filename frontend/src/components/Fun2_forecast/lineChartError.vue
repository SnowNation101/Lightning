<template>
    <div id="lineChartError">
        <div id="text">
            <p id="text1" style="margin: 0;padding: 0;color:black">{{ dateFrom }}~{{ dateTo }}误差曲线</p>
        </div>
        <div id="chart">
            <div id="error" v-loading='$store.state.load2' style="height:100%;width:100%;"></div>
        </div>
    </div>
</template>

<script>
import * as  echarts from 'echarts'
export default {
    name: 'lineChartError',
    data() {
        return {
            dateFrom: '',
            dateTo: '',
        }
    },
    methods: {
        draw() {
            var myChart = echarts.init(document.getElementById('error'));
            this.$store.state.option_2_line_2 && myChart.setOption(this.$store.state.option_2_line_2);
        },
        refreshChart() {
            document.getElementById('error').removeAttribute('_echarts_instance_');
            this.draw();
        },
        xtime() {
            var now = new Date();
            var data = [];
            var date = [];
            var temp = [];
            var hour = Number(now.getHours());
            var minute;
            if (Number(now.getMinutes()) >= 45) {
                minute = 45;
            }
            else if (Number(now.getMinutes()) >= 30) {
                minute = 30;
            }
            else if (Number(now.getMinutes()) >= 15) {
                minute = 15;
            }
            else {
                minute = 0;
            }
            for (let i = 0; i < 96; i++) {
                if (hour == 0) hour = "00"
                if (hour == 1) hour = "01"
                if (hour == 2) hour = "02"
                if (hour == 3) hour = "03"
                if (hour == 4) hour = "04"
                if (hour == 5) hour = "05"
                if (hour == 6) hour = "06"
                if (hour == 7) hour = "07"
                if (hour == 8) hour = "08"
                if (hour == 9) hour = "09"
                if (minute == 0) {
                    minute = "00";
                }
                temp.push([hour, minute].join(':'));
                if (minute == 0) {
                    minute = Number(45);
                    if (hour == 0) {
                        hour = Number(23);
                    }
                    else {
                        hour = Number(hour) - 1;
                    }
                }
                else {
                    minute = Number(minute) - 15;
                }
            }
            for (let i = 0; i < 96; i++) {
                let v = temp.pop();
                date.push(v);
                data.push(Math.round((Math.random()) * 20));
            }
            var day = now.getDate();
            var month = now.getMonth();
            var year = now.getFullYear();
            var daysPerMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            this.dateTo = year + '/' + (month + 1) + '/' + day;
            if (Number(day) == 1) {
                if (Number(month) == 0) {
                    month = 11;
                    day = 31;
                    year -= 1;
                }
                else {
                    month = Number(month) - 1;
                    day = daysPerMonth[Number(now.getMonth()) - 1];
                }
            }
            else {
                day = Number(day) - 1;
            }
            this.dateFrom = year.toString() + '/' + (month + 1).toString() + '/' + day.toString();
            this.$store.state.option_2_line_2.xAxis.data = date;
        }
    },
    mounted() {
        this.xtime();
        this.draw();
    },
}
</script>

<style>
#lineChartError {
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


#chart {
    width: 100%;
    height: 80%;
}
</style>
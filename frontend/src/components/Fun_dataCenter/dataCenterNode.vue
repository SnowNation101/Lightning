<template>
    <div id="dataCenterNode">
        <div id="manualPredict">
            <div id="manualPredictZone">
                <!-- <p>{{ timedata }}</p> -->
                <div style="height: 100%;width:65%;float: left;">
                    <div style="height: 37%;width:100%">
                        <p
                            style="color:rgb(255, 255, 255);font-size:20px;font-family:Arial,Helvetica,sans-serif;margin-left: 4%;">
                            <b>实时预测</b>
                        </p>
                    </div>
                    <div style="height: 40%;margin-left: 4%;margin-bottom: 3%;width:100%">
                        <div
                            style="height: 100%;float: left;width:100%;color:rgb(255, 255, 255);font-size:18px;font-family:Arial,Helvetica,sans-serif;">
                            <div style="height:100%;width:21%;float: left;">
                                <el-input v-model="input_Light" placeholder="输入光强"
                                    style="width:70%;size='small'"></el-input>
                                <a> w/m<sup>2</sup></a>
                            </div>
                            <div style="height:100%;width:20%;float: left;">
                                <el-input v-model="input_Wind" placeholder="输入风速" style="width:70%;size='small'"></el-input>
                                <a> m/s</a>
                            </div>
                            <div style="height:100%;width:20%;float: left;">
                                <el-input v-model="input_Temper" placeholder="输入温度"
                                    style="width:70%;size='small'"></el-input>
                                <a> ℃</a>
                            </div>
                            <div style="height:100%;width:19%;float: left;">
                                <el-input v-model="input_Humid" placeholder="输入湿度"
                                    style="width:70%;size='small'"></el-input>
                                <a> %</a>
                            </div>
                            <div style="height:100%;width:19%;float:left;">
                                <el-button @click="startPredict">开始预测</el-button>
                            </div>
                        </div>
                    </div>
                </div>

                <div style="height: 100%;width:10%;float: left;border-radius: 10px;">
                    <div style="height: 100px;width:100px;border-radius: 10px;margin:auto;background: rgba(69, 133, 255, 1); border: 2px solid rgba(255, 255, 255, 0.15); box-shadow: 0px 25px 27px -6px rgba(0, 66, 190, 0.4);margin-top:10%;text-align: center;
                    font-family:Arial,Helvetica,sans-serif;" v-loading="this.predictLoad">
                        <p style="color:rgb(255, 213, 0);font-size:25px;display: inline-block;vertical-align: middle;"><b>{{
                            predictPower }}<br />kw</b></p>
                    </div>
                </div>


                <div style="height: 100%;width:25%;background-color:rgba(56, 125, 255, 1);float: left;border-radius: 10px;">
                    <div style="height: 50%;width:100%;">
                        <div style="width:85%;height:100%;float: left;">
                            <el-select v-model="barOrLine" placeholder="选择显示类型"
                                style="margin-top:5%;margin-left: 5%;width:80%;">
                                <el-option v-for="item in chart_Option" :key="item.value" :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </div>
                        <div style="width:15%;height:100%;float: left">
                            <el-button icon="el-icon-refresh" circle style="margin:auto;margin-top: 25%;"
                                @click="refresh"></el-button>
                        </div>
                    </div>
                    <div style="width:100%;height:50%;">
                        <el-slider v-model="changeRate" :min="500" show-input :max="10000" :step="500" show-stops
                            style="width:90%;margin-top:5%;margin-left: 5%;">
                        </el-slider>
                    </div>
                </div>
            </div>
        </div>
        <div id="left">
            <div id="lightPic" v-loading="this.chartsLoad">
                <div id="lightPicZone">

                </div>
            </div>
            <div id="windPic">
                <div id="windPicZone" v-loading="this.chartsLoad">

                </div>
            </div>
        </div>
        <div id="middle">
            <div id="powerPic" v-loading="this.chartsLoad">
                <div id="powerPicZone">

                </div>
            </div>
            <div id="digital" v-loading="this.chartsLoad">
                <div id="digitalZone" style="font-family:Arial,Helvetica,sans-serif;">
                    <div
                        style="height:70%;width:19%; border-right-style:dashed;float:left;margin-left:1%;margin-right:1%;margin-top:3%;border-width:1px;text-align: center;">
                        <p style="color:rgb(145, 165, 165);font-size:16px;"><b>光照</b></p>
                        <p style="color:rgb(27, 61, 212);font-size:25px;"><b>{{ cur_Light }} w/m2</b></p>
                        <el-tag :type=updown_Light>{{ percent_Light }}</el-tag>
                    </div>
                    <div
                        style="height:70%;width:19%; border-right-style:dashed;float:left;margin-right:1%;margin-top:3%;border-width:1px;text-align: center;">
                        <p style="color:rgb(145, 165, 165);font-size:16px;"><b>风速</b></p>
                        <p style="color:rgb(27, 61, 212);font-size:25px;"><b>{{ cur_Wind }} m/s</b></p>
                        <el-tag :type=updown_Wind>{{ percent_Wind }}</el-tag>
                    </div>
                    <div
                        style="height:70%;width:19%; border-right-style:dashed;float:left;margin-right:1%;margin-top:3%;border-width:1px;text-align: center;">
                        <p style="color:rgb(145, 165, 165);font-size:16px;"><b>温度</b></p>
                        <p style="color:rgb(27, 61, 212);font-size:24px;"><b>{{ cur_Temper }} ℃</b></p>
                        <el-tag :type=updown_Temper>{{ percent_Temper }}</el-tag>
                    </div>
                    <div
                        style="height:70%;width:19%; border-right-style:dashed;float:left;margin-right:1%;margin-top:3%;border-width:1px;text-align: center;">
                        <p style="color:rgb(145, 165, 165);font-size:16px;"><b>湿度</b></p>
                        <p style="color:rgb(27, 61, 212);font-size:25px;"><b>{{ cur_Humid }} %</b></p>
                        <el-tag :type=updown_Humid>{{ percent_Humid }}</el-tag>
                    </div>
                    <div style="height:70%;width:18%;float:left;margin-top:3%;text-align: center;">
                        <p style="color:rgb(145, 165, 165);font-size:16px;"><b>预测功率</b></p>
                        <p style="color:rgb(27, 61, 212);font-size:25px;"><b>{{ cur_Power }} kW</b></p>
                        <el-tag :type=updown_Power>{{ percent_Power }}</el-tag>
                    </div>
                </div>
            </div>
        </div>
        <div id="right">
            <div id="temperPic" v-loading="this.chartsLoad">
                <div id="temperPicZone">

                </div>
            </div>
            <div id="humidPic" v-loading="this.chartsLoad">
                <div id="humidPicZone">

                </div>
            </div>
        </div>

    </div>
</template>

<script>
import * as  echarts from 'echarts'
import axios from 'axios'
export default {
    name: 'dataCenterNode',
    data() {
        return {
            predictLoad: false,
            chartsLoad: false,
            timedata: 0,
            timer: '',
            myChart_Power: '',
            myChart_Light: '',
            myChart_Wind: '',
            myChart_Temper: '',
            myChart_humid: '',
            temp: [],
            changeRate: 2000,
            cur_Power: 0,
            cur_Light: 0,
            cur_Wind: 0,
            cur_Temper: 0,
            cur_Humid: 0,
            percent_Power: '+0.00%',
            percent_Light: '+0.00%',
            percent_Wind: '+0.00%',
            percent_Temper: '+0.00%',
            percent_Humid: '',
            updown_Power: 'success',
            updown_Light: 'success',
            updown_Wind: 'success',
            updown_Temper: 'success',
            updown_Humid: 'success',
            input_Wind: 0,
            input_Temper: 0,
            input_Humid: 0,
            input_Light: 0,
            predictPower: 0,
            chart_Option: [{
                label: '折线图',
                value: "line",
            }, {
                label: '柱状图',
                value: "bar",
            }],
            barOrLine: 'line',
            pre96: [],
        }
    },
    methods: {
        start() {
            this.timer = setInterval(this.valChange, this.changeRate)
        },
        timeline() {
            this.temp = []
            var now = new Date();
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
                this.temp.push([hour, minute].join(':'));
                if (minute == 0) {
                    minute = 45;
                    if (hour == 0) {
                        hour = 23;
                    }
                    else {
                        hour -= 1;
                    }
                }
                else {
                    minute -= 15;
                }
            }
        },
        valChange() {
            if (this.timedata % 96 == 0) {
                this.timedata = 0
                this.timeline();

                this.$store.state.Option_dc_power.series[0].data = []
                this.$store.state.Option_dc_power.xAxis.data = []

                this.$store.state.Option_dc_light.series[0].data = []
                this.$store.state.Option_dc_light.xAxis.data = []

                this.$store.state.Option_dc_wind.series[0].data = []
                this.$store.state.Option_dc_wind.xAxis.data = []

                this.$store.state.Option_dc_temper.series[0].data = []
                this.$store.state.Option_dc_temper.xAxis.data = []

                this.$store.state.Option_dc_humid.series[0].data = []
                this.$store.state.Option_dc_humid.xAxis.data = []
            }

            let v = this.temp.pop();
            this.timedata++;

            this.$store.state.Option_dc_power.series[0].data.push(this.pre96[this.timedata].predictVal)
            this.cur_Power = (this.pre96[this.timedata].predictVal).toFixed(2)
            this.$store.state.Option_dc_power.xAxis.data.push(v)
            this.myChart_Power.setOption(this.$store.state.Option_dc_power)

            this.$store.state.Option_dc_light.series[0].data.push(this.pre96[this.timedata].lightIntensity)
            this.cur_Light = (this.pre96[this.timedata].lightIntensity).toFixed(2)
            this.$store.state.Option_dc_light.xAxis.data.push(v)
            this.myChart_Light.setOption(this.$store.state.Option_dc_light)

            this.$store.state.Option_dc_wind.series[0].data.push(this.pre96[this.timedata].windSpeed)
            this.cur_Wind = (this.pre96[this.timedata].windSpeed).toFixed(2)
            this.$store.state.Option_dc_wind.xAxis.data.push(v)
            this.myChart_Wind.setOption(this.$store.state.Option_dc_wind)

            this.$store.state.Option_dc_temper.series[0].data.push(this.pre96[this.timedata].temperature)
            this.cur_Temper = (this.pre96[this.timedata].temperature).toFixed(2)
            this.$store.state.Option_dc_temper.xAxis.data.push(v)
            this.myChart_Temper.setOption(this.$store.state.Option_dc_temper)

            this.$store.state.Option_dc_humid.series[0].data.push(this.pre96[this.timedata].humidity)
            this.cur_Humid = (this.pre96[this.timedata].humidity).toFixed(2)
            this.$store.state.Option_dc_humid.xAxis.data.push(v)
            this.myChart_Humid.setOption(this.$store.state.Option_dc_humid)

            if (this.timedata != 0) {
                let diff_Predict = this.pre96[this.timedata].predictVal - this.pre96[this.timedata - 1].predictVal;
                let diff_Light = this.pre96[this.timedata].lightIntensity - this.pre96[this.timedata - 1].lightIntensity;
                let diff_Wind = this.pre96[this.timedata].windSpeed - this.pre96[this.timedata - 1].windSpeed;
                let diff_Temper = this.pre96[this.timedata].temperature - this.pre96[this.timedata - 1].temperature;
                let diff_Humid = this.pre96[this.timedata].humidity - this.pre96[this.timedata - 1].humidity;
                let temp1 = ((diff_Predict / this.pre96[this.timedata - 1].predictVal) * 100).toFixed(2)
                let temp2 = ((diff_Light / this.pre96[this.timedata - 1].lightIntensity) * 100).toFixed(2)
                let temp3 = ((diff_Wind / this.pre96[this.timedata - 1].windSpeed) * 100).toFixed(2)
                let temp4 = ((diff_Temper / this.pre96[this.timedata - 1].temperature) * 100).toFixed(2)
                let temp5 = ((diff_Humid / this.pre96[this.timedata - 1].humidity) * 100).toFixed(2)
                if (diff_Predict >= 0) {
                    this.percent_Power = "+" + temp1 + "%"
                    if (!Number.isFinite(temp1)) {
                        this.percent_Power = ">1000%"
                    }
                    if (Number.isNaN(temp1)) {
                        this.percent_Power = "0.00%"
                    }
                    this.updown_Power = "success"
                }
                else {
                    this.percent_Power = temp1 + "%"
                    if (Number.isNaN(temp1)) {
                        this.percent_Power = "0.00%"
                    }
                    this.updown_Power = "danger"
                }

                if (diff_Light >= 0) {
                    this.percent_Light = "+" + temp2 + "%"
                    this.updown_Light = "success"
                    if (!Number.isFinite(temp2)) {
                        this.percent_Light = ">1000%"
                    }
                    if (Number.isNaN(temp2)) {
                        this.percent_Light = "0.00%"
                    }
                }
                else {
                    this.percent_Light = temp2 + "%"
                    this.updown_Light = "danger"
                    if (Number.isNaN(temp2)) {
                        this.percent_Light = "0.00%"
                    }
                }

                if (diff_Wind >= 0) {
                    this.percent_Wind = "+" + temp3 + "%"
                    this.updown_Wind = "success"
                    if (!Number.isFinite(temp3)) {
                        this.percent_Wind = ">1000%"
                    }
                    if (Number.isNaN(temp3)) {
                        this.percent_Wind = "0.00%"
                    }
                }
                else {
                    this.percent_Wind = temp3 + "%"
                    this.updown_Wind = "danger"
                    if (Number.isNaN(temp3)) {
                        this.percent_Wind = "0.00%"
                    }
                }

                if (diff_Temper >= 0) {
                    this.percent_Temper = "+" + temp4 + "%"
                    this.updown_Temper = "success"
                    if (!Number.isFinite(temp4)) {
                        this.percent_Temper = ">1000%"
                    }
                    if (Number.isNaN(temp4)) {
                        this.percent_Temper = ""
                    }
                }
                else {
                    this.percent_Temper = temp4 + "%"
                    this.updown_Temper = "danger"
                    if (Number.isNaN(temp4)) {
                        this.percent_Temper = ""
                    }
                }
                if (diff_Humid >= 0) {
                    this.percent_Humid = "+" + temp5 + "%"
                    this.updown_Humid = "success"
                    if (!Number.isFinite(temp5)) {
                        this.percent_Humid = ">1000%"
                    }
                    if (Number.isNaN(temp5)) {
                        this.percent_Humid = ""
                    }
                }
                else {
                    this.percent_Humid = temp5 + "%"
                    this.updown_Humid = "danger"
                    if (Number.isNaN(temp5)) {
                        this.percent_Humid = ""
                    }
                }
            }
        },
        over() {
            this.timedata = 0;
            clearInterval(this.timer);
        },
        draw() {
            this.myChart_Power = echarts.init(document.getElementById('powerPicZone'));
            this.$store.state.Option_dc_power && this.myChart_Power.setOption(this.$store.state.Option_dc_power);

            this.myChart_Light = echarts.init(document.getElementById('lightPicZone'));
            this.$store.state.Option_dc_light && this.myChart_Light.setOption(this.$store.state.Option_dc_light);

            this.myChart_Wind = echarts.init(document.getElementById('windPicZone'));
            this.$store.state.Option_dc_wind && this.myChart_Wind.setOption(this.$store.state.Option_dc_wind);

            this.myChart_Temper = echarts.init(document.getElementById('temperPicZone'));
            this.$store.state.Option_dc_temper && this.myChart_Temper.setOption(this.$store.state.Option_dc_temper);

            this.myChart_Humid = echarts.init(document.getElementById('humidPicZone'));
            this.$store.state.Option_dc_humid && this.myChart_Humid.setOption(this.$store.state.Option_dc_humid);
        },
        startPredict() {
            this.predictLoad = true
            axios({
                url: 'http://localhost:8080/api/power/predict',
                method: 'POST',
                params: {
                    temperature: this.input_Temper,
                    humidity: this.input_Humid,
                    lightIntensity: this.input_Light,
                    windSpeed: this.input_Wind
                }
            }).then(res => {
                this.predictPower = res.data.toFixed(2)
                this.predictLoad = false
            })
        },
        refresh() {
            this.chartsLoad = true;
            axios({
                url: 'http://localhost:8080/api/power/96-predict',
                method: 'POST',
                params: { station: 1 }
            }).then(res => {
                this.pre96 = res.data
                console.log(this.pre96)
                this.$store.state.Option_dc_power.series[0].type = this.barOrLine;
                this.$store.state.Option_dc_light.series[0].type = this.barOrLine;
                this.$store.state.Option_dc_humid.series[0].type = this.barOrLine;
                this.$store.state.Option_dc_wind.series[0].type = this.barOrLine;
                this.$store.state.Option_dc_temper.series[0].type = this.barOrLine;
                this.over()
                this.start()
                this.chartsLoad = false;
            })
        }
    },
    mounted() {
        //this.start();
        this.draw();
    },
    watch: {

    },
    beforeDestroy() {
        this.over();
    }
}
</script>

<style>
#dataCenterNode {
    width: 100%;
    height: 85vh;
    /* background-color: aqua; */
    /* border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); */
}

#manualPredict {
    width: 100%;
    height: 18%;
}

#left {
    width: 23%;
    height: 82%;
    float: left;
}

#middle {
    width: 54%;
    height: 82%;
    float: left;
}

#right {
    width: 23%;
    height: 82%;
    float: left;
}

#lightPic {
    width: 100%;
    height: 50%;
}

#windPic {
    width: 100%;
    height: 50%;
}

#powerPic {
    width: 100%;
    height: 70%;
}

#digital {
    width: 100%;
    height: 30%;
}

#temperPic {
    width: 100%;
    height: 50%;
}

#humidPic {
    width: 100%;
    height: 50%;
}

#manualPredictZone {
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    margin-bottom: 1%;
    width: 100%;
    height: 98%;
    background-color: rgb(28, 98, 255);
    ;
}

#lightPicZone,
#windPicZone,
#powerPicZone,
#digitalZone,
#temperPicZone,
#humidPicZone {
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    margin-top: 1%;
    margin-left: 1%;
    margin-right: 1%;
    width: 98%;
    height: 98%;
    /* background-color: #0000ff; */
}

/* {
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    margin: 1%;
    width: 98%;
    height: 98%;
    background-color: #0000ff;
} */
</style>
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import * as  echarts from 'echarts'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    errorState: false,
    load1: false,
    load2: false,
    selectorOpt: [{
      value: '1',
      label: 'A001'
    }, {
      value: '2',
      label: 'A002'
    },],
    show1: true,
    show2: false,
    show3: false,
    show4: false,
    option_1_line: {
      tooltip: {
        trigger: 'axis',
        position: function (pt) {
          return [pt[0], '10%'];
        }
      },
      title: {
        left: 'center',
        text: '月度发电量统计'
      },
      toolbox: {
        feature: {
          dataZoom: {
            yAxisIndex: 'none'
          },
          restore: {},
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        boundaryGap: true,
        data: []
      },
      yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
      },
      dataZoom: [
        {
          type: 'inside',
          handleSize: 3,
          start: 0,
          end: 10,
          realtime: true,//拖动时实时更新
        },
        {
          start: 0,
          end: 10
        }
      ],
      series: [
        {
          name: '月度发电量',
          type: 'line',
          symbol: 'none',
          sampling: 'lttb',
          itemStyle: {
            color: 'rgb(88,163,246)'
          },
          areaStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: 'rgb(88,163,246)'
              },
              {
                offset: 1,
                color: 'rgb(255, 70, 131)'
              }
            ])
          },
          data: []
        }
      ]
    },
    option_1_bar: {
      tooltip: {
        trigger: 'axis',
        position: function (pt) {
          return [pt[0], '10%'];
        }
      },
      title: {
        left: 'center',
        text: '每日发电量统计'
      },
      toolbox: {
        feature: {
          dataZoom: {
            yAxisIndex: 'none'
          },
          restore: {},
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        boundaryGap: true,
        data: []
      },
      yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
      },
      dataZoom: [
        {
          type: 'inside',
          handleSize: 3,
          start: 0,
          end: 10,
          realtime: true,//拖动时实时更新
        },
        {
          start: 0,
          end: 10
        }
      ],
      series: [
        {
          name: '每日发电量',
          type: 'bar',
          symbol: 'none',
          sampling: 'lttb',
          itemStyle: {
            color: 'rgb(88,163,246)'
          },
          areaStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: 'rgb(88,163,246)'
              },
              {
                offset: 1,
                color: 'rgb(255, 70, 131)'
              }
            ])
          },
          data: []
        }
      ]
    },
    option_2_line_1: {
      tooltip: {
        trigger: 'axis',
        position: function (pt) {
          return [pt[0], '10%'];
        }
      },
      title: {
        left: 'center',
        text: '24h内预测曲线'
      },
      toolbox: {
        feature: {
          dataZoom: {
            yAxisIndex: 'none'
          },
          restore: {},
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        boundaryGap: true,
        data: []
      },
      yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
      },
      dataZoom: [
        {
          type: 'inside',
          handleSize: 3,
          start: 0,
          end: 10,
          realtime: true,//拖动时实时更新
        },
        {
          start: 0,
          end: 10
        }
      ],
      series: [
        {
          name: '功率预测',
          type: 'line',
          symbol: 'none',
          sampling: 'lttb',
          itemStyle: {
            color: 'rgb(88,163,246)'
          },
          areaStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: 'rgb(88,163,246)'
              },
              {
                offset: 1,
                color: 'rgb(255, 70, 131)'
              }
            ])
          },
          data: []
        }
      ]
    },
    option_2_line_2: {
      tooltip: {
        trigger: 'axis',
        position: function (pt) {
          return [pt[0], '10%'];
        }
      },
      title: {
        left: 'center',
        text: '24h内误差曲线'
      },
      toolbox: {
        feature: {
          dataZoom: {
            yAxisIndex: 'none'
          },
          restore: {},
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        boundaryGap: true,
        data: []
      },
      yAxis: {
        type: 'value',
        boundaryGap: [0, '100%']
      },
      dataZoom: [
        {
          type: 'inside',
          handleSize: 3,
          start: 0,
          end: 10,
          realtime: true,//拖动时实时更新
        },
        {
          start: 0,
          end: 10
        }
      ],
      series: [
        {
          name: '功率误差',
          type: 'line',
          symbol: 'none',
          sampling: 'lttb',
          itemStyle: {
            color: 'rgb(88,163,246)'
          },
          areaStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: 'rgb(88,163,246)'
              },
              {
                offset: 1,
                color: 'rgb(255, 70, 131)'
              }
            ])
          },
          data: []
        }
      ]
    },
    forecastFactor: [{
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    }, {
      time: '-',
      light: '-',
      wind: '-',
      temper: '-',
      humidity: '-',
      forecast: '-',
    },],
  },
  getters: {

  },
  //里面定义方法，操作state方发
  mutations: {
    show1(state) {
      state.show1 = true;
      state.show2 = false;
      state.show3 = false;
      state.show4 = false;
    },
    show2(state) {
      state.show1 = false;
      state.show2 = true;
      state.show3 = false;
      state.show4 = false;
    },
    show3(state) {
      state.show1 = false;
      state.show2 = false;
      state.show3 = true;
      state.show4 = false;
    },
    show4(state) {
      state.show1 = false;
      state.show2 = false;
      state.show3 = false;
      state.show4 = true;
    },
    // getSelectorOpt(state) { //获取电站复选框信息

    // },
    getOption_1_line(state, data) { //获取功能1折线图信息
      state.option_1_line.series[0].data = data
    },
    getOption_1_bar(state, data) { //获取功能1柱状图信息
      state.option_1_bar.series[0].data = data
    },
    getOption_2_line_1(state, data) { //获取功能2折线图1信息
      for (let i = 0; i < 96; i++) {
        state.option_2_line_1.series[0].data[i] = data[i].predictVal
        state.forecastFactor[i].forecast = data[i].predictVal
        state.forecastFactor[i].humidity = data[i].humidity
        state.forecastFactor[i].light = data[i].lightIntensity
        state.forecastFactor[i].wind = data[i].windSpeed
        state.forecastFactor[i].temper = data[i].temperature
      }
      state.forecastFactor.splice(1, 0)
    },
    getOption_2_line_2(state, data) { //获取功能2折线图2信息
      for (let i = 0; i < 96; i++) {
        state.option_2_line_2.series[0].data = data
      }
    },
    getErrorState(state) {
      axios({
        url: 'http://localhost:8088/api/test/is-error',
        method: 'GET',
      }).then(res => {
        state.errorState = res.data
      })
      state.errorState
    }
  },
  // 操作异步操作mutation
  actions: {
    /* eslint-disable */
    getOption_1_line(context, id) {
      axios({
        url: 'http://localhost:8088/api/generation/last-year',
        method: 'POST',
        params: { station: id }
      }).then(res => {
        context.commit("getOption_1_line", res.data)
      })
    },
    getOption_1_bar(context, id) {
      axios({
        url: 'http://localhost:8088/api/generation/last-year',
        method: 'POST',
        params: { station: id }
      }).then(res => {
        context.commit("getOption_1_bar", res.data)
      })
    },
    getOption_2_line_1(context, id) {
      // this.state.load2 = true;
      axios({
        url: 'http://localhost:8088/api/power/96-predict',
        method: 'POST',
        params: { station: id }
      }).then(res => {
        context.commit("getOption_2_line_1", res.data)
        // this.state.load2 = false;
      })
    },
    getOption_2_line_2(context, id) {
      axios({
        url: 'http://localhost:8088/api/power/96-error',
        method: 'POST',
        params: { station: id }
      }).then(res => {
        context.commit("getOption_2_line_2", res.data)
      })
    }
  },
  modules: {

  },
})
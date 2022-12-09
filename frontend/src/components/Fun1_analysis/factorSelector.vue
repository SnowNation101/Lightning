<template>
    <div id="factorSelector">
        <div id="select">
            <p style="margin: 0;padding: 0;font-family: Arial, Helvetica, sans-serif;font-size: 20px;">环境因素影响分析器</p>
            <p style="font-family: Arial, Helvetica, sans-serif;font-size: 10px;">Analyzer of the Influence of
                Environmental Factors</p>
            <el-radio-group v-model="variable">
                <el-radio-button :label="1">光照强度</el-radio-button>
                <el-radio-button :label="2">温度</el-radio-button>
                <el-radio-button :label="3">风速</el-radio-button>
                <el-radio-button :label="4">湿度</el-radio-button>
            </el-radio-group>
            <el-button @click="re" style="margin-left:20px">刷新</el-button>
        </div>
        <div id="img1" v-loading="load">
            <img id="singleFactor" :src="url" />
        </div>
        <div id="img2" v-loading="load">
            <img id="allFactors" src="../../assets/allFactor.png" />
        </div>
    </div>
</template>

<script>
export default {
    name: 'factorSelector',
    data() {
        return {
            variable: '1',
            url: "",
            load: false,
        }
    },
    methods: {
        re() {
            this.load = true;
            setTimeout(() => {   //设置延迟执行
                this.load = false;
            }, 1000);
        }
    },
    mounted() {
        this.url = require("../../assets/sunlight.png");
    },
    watch: {
        variable: {
            handler() {
                switch (this.variable) {
                    case 1:
                        this.url = require("../../assets/sunlight.png");
                        break;
                    case 2:
                        this.url = require("../../assets/temperture.png");
                        break;
                    case 3:
                        this.url = require("../../assets/wind.png");
                        break;
                    case 4:
                        this.url = require("../../assets/humidity.png");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
</script>

<style>
#factorSelector {
    text-align: center;
    width: 100%;
    height: 100%;
}

#select {
    width: 100%;
    height: 16%;
}

#img1 {
    width: 100%;
    height: 42%;
    text-align: center;
}

#img2 {
    width: 100%;
    height: 42%;
    text-align: center;
}

#singleFactor {
    width: 100%;
    height: 100%;
    object-fit: contain;
}

#allFactors {
    width: 100%;
    height: 100%;
    object-fit: contain;
}
</style>
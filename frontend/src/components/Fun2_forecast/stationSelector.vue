<template>
    <div id="stationSelector">
        <a>电站: </a>
        <el-select v-model="value" placeholder="请选择">
            <el-option v-for="item in $store.state.selectorOpt" :key="item.value" :label="item.label"
                :value="item.value">
            </el-option>
        </el-select>
        <el-button style="position:relative;left:5px" @click="selectStation(value)">预测</el-button>
    </div>
</template>

<script>
export default {
    name: 'stationSelector',
    data() {
        return {
            value: '',
        }
    },
    methods: {
        selectStation(id) {
            this.$store.state.load2 = true;
            //this.$store.commit("getForecastFactor", id);
            // this.$store.commit("getOption_2_line_1", id);
            this.$store.dispatch("getOption_2_line_1", id)
            this.$store.dispatch("getOption_2_line_2", id);
            setTimeout(() => {   //设置延迟执行
                this.$emit("child_p_method");
                this.$store.state.load2 = false;
            }, 5000);
        }
    },
    mounted() {
        // 先get

        // this.options = this.$store.state.selectorOpt;
    },

}
</script>

<style>

</style>
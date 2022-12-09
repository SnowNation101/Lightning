<template>
    <div id="stationSelector">
        <a>电站: </a>
        <el-select v-model="value" placeholder="请选择">
            <el-option v-for="item in $store.state.selectorOpt" :key="item.value" :label="item.label"
                :value="item.value">
            </el-option>
        </el-select>
        <el-button style="position:relative;left:5px" @click="selectStation(value)">查看</el-button>
    </div>
</template>

<script>
export default {
    name: 'stationSelector',
    data() {
        return {
            value: '',
            all: true,
        }
    },
    methods: {
        selectStation(id) {
            this.$store.state.load1=true;
            this.$store.dispatch("getOption_1_line", id);
            this.$store.dispatch("getOption_1_bar", id);
            setTimeout(() => {   //设置延迟执行
                this.$emit("child_p_method");
                this.$store.state.load1=false;
            }, 1000);
        },
    },
    mounted() {
        // 先get

    },
}
</script>

<style>

</style>
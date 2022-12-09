<template>
    <div id="factorTable" style="height:86vh;overflow-y:auto">
        <el-table :data="$store.state.forecastFactor" style="width: 100%">
            <el-table-column prop="time" label="时间" width="100">
            </el-table-column>
            <el-table-column prop="light" label="光照强度" width="100">
            </el-table-column>
            <el-table-column prop="wind" label="风速" width="100">
            </el-table-column>
            <el-table-column prop="temper" label="温度" width="100">
            </el-table-column>
            <el-table-column prop="humidity" label="湿度" width="100">
            </el-table-column>
            <el-table-column prop="forecast" label="预测值" width="100">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    name: 'factorTable',
    data() {
        return {
        }
    },
    mounted() {
        for (let i = 0; i < 96; i++) {
            this.$store.state.forecastFactor[i].time = this.$store.state.option_2_line_2.xAxis.data[i]
        }
    },
    methods: {

    }
}
</script>

<style>
#factorTable {
    width: 100%;
    height: 100%;
}
</style>
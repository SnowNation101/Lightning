<template>
    <div id="forecastNode">
        <div id="d1">
            <div id="selector">
                <stationSelector @child_p_method="refreshAll"></stationSelector>
            </div>
            <div id="line1">
                <lineChartForecast ref="l1"></lineChartForecast>
            </div>
            <div id="line2">
                <lineChartError ref="l2"></lineChartError>
            </div>
        </div>
        <div id="d2">
            <factorTable></factorTable>
        </div>
    </div>
</template>

<script>
import lineChartForecast from './lineChartForecast.vue';
import lineChartError from './lineChartError.vue';
import stationSelector from './stationSelector.vue';
import factorTable from './factorTable.vue';
export default {
    name: 'forecastNode',
    components: {
        lineChartForecast,
        lineChartError,
        stationSelector,
        factorTable,
    },
    methods: {
        refreshAll() {
            this.$refs.l1.xtime();
            this.$refs.l2.xtime();
            this.$refs.l1.refreshChart()
            this.$refs.l2.refreshChart()
        }
    }
}
</script>

<style>
#forecastNode {
    width: 100%;
    height: 86vh;
}

#d1 {
    float: left;
    width: 50%;
    height: 100%;

}

#d2 {
    float: left;
    width: 50%;
    height: 100%;
}

#selector {
    height: 10%;
    width: 100%;
}

#line1 {
    height: 45%;
    width: 100%;
}

#line2 {
    height: 45%;
    width: 100%;
}
</style>
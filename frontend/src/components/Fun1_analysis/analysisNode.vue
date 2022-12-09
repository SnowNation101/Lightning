<template>
    <div id="analysisNode">
        <div id="d1">
            <div id="selector">
                <stationSelector @child_p_method="refreshAll"></stationSelector>
            </div>
            <div id="line">
                <lineChart ref="l"></lineChart>
            </div>
            <div id="bar">
                <barChart ref="b"></barChart>
            </div>
        </div>
        <div id="d2">
            <factorSelector></factorSelector>
        </div>
    </div>
</template>

<script>
import lineChart from './lineChart.vue';
import barChart from './barChart.vue';
import stationSelector from './stationSelector.vue';
import factorSelector from './factorSelector.vue';
export default {
    name: 'analysisNode',
    components:{
        lineChart,
        barChart,
        stationSelector,
        factorSelector,
    },
    methods: {
        refreshAll(){
            this.$refs.l.refreshChart()
            this.$refs.b.refreshChart()
        }
    }
}
</script>

<style>
#analysisNode{
    width:100%;
    height:86vh;
}
#d1{
    float: left;
    width:50%;
    height:100%;
}
#d2{
    float: left;
    width:50%;
    height:100%;
}
#selector {
    height: 10%;
    width: 100%;
}
#line{
    height: 45%;
    width: 100%;
}
#bar{
    height: 45%;
    width: 100%;
}
</style>
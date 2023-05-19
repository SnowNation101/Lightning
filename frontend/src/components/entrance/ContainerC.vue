<template>
  <div id="ContainerC">
    <el-container style="position:absolute;left:0;right:0;top:0;bottom:0;">
      <el-header height="8vh" style="padding: 0px">
        <topNav></topNav>
      </el-header>
      <el-container>
        <el-aside width="10%">
          <sideNav></sideNav>
        </el-aside>
        <el-main>
          <analysisNode v-if="this.$store.state.show1"></analysisNode>
          <forecastNode v-if="this.$store.state.show2"></forecastNode>
          <dataCenterNode v-if="this.$store.state.show3"></dataCenterNode>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import sideNav from '../navigateBar/sideNav.vue';
import topNav from '../navigateBar/topNav.vue';
import analysisNode from '../Fun1_analysis/analysisNode.vue'
import forecastNode from '../Fun2_forecast/forecastNode.vue'
import dataCenterNode from '../Fun_dataCenter/dataCenterNode.vue';
export default {
  name: 'ContainerC',
  data() {
    return {

    }
  },
  components: {
    sideNav,
    topNav,
    analysisNode,
    forecastNode,
    dataCenterNode
  }
}
</script>

<style>
.el-header {
  background-color: white;
  z-index: 100;
}

#ContainerC {
  width: 100%;
  height: 100%;
}
</style>
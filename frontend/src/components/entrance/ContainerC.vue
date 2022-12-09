<template>
  <div id="ContainerC">
    <el-container style="position:absolute;left:0;right:0;top:0;bottom:0;">
      <el-header height="8vh">
        <topNav></topNav>
      </el-header>
      <el-container>
        <el-aside width="10%">
          <sideNav></sideNav>
        </el-aside>
        <el-main>
          <analysisNode v-if="this.$store.state.show1"></analysisNode>
          <forecastNode v-if="this.$store.state.show2"></forecastNode>
          <decisionNode v-if="this.$store.state.show3"></decisionNode>
          <elecMapNode v-if="this.$store.state.show4"></elecMapNode>
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
import decisionNode from '../Fun3_decision/decisionNode.vue'
import elecMapNode from '../Fun4_elecMap/elecMapNode.vue'
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
    decisionNode,
    elecMapNode,
  }
}
</script>

<style>
.el-header {
  background-color: #454f59;
}

#ContainerC {
  width: 100%;
  height: 100%;
}
</style>
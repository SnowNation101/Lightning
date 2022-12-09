<template>
    <div id="sideNav">
        <el-menu id="elm" default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
            background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
            <el-menu-item index="1" @click="show1()">
                <i class="el-icon-menu"></i>
                <span slot="title">数据分析</span>
            </el-menu-item>
            <el-menu-item index="2" @click="show2()">
                <i class="el-icon-document"></i>
                <span slot="title">功率预测</span>
            </el-menu-item>
            <el-menu-item index="3" @click="show3()">
                <i class="el-icon-thumb"></i>
                <span slot="title">辅助决策</span>
            </el-menu-item>
        </el-menu>

    </div>
</template>

<script>
export default {
    name: 'sideNav',
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        show1(){
            this.$store.commit("show1");
        },
        show2(){
            this.$store.commit("show2");
        },
        show3(){
            this.$store.commit("show3");
        },
        show4(){
            this.$store.commit("show4");
        },
    }
}
</script>

<style>
#elm {
    overflow:hidden;
    height: 92vh;
}
</style>